package com.octo.kata.archiclean.entities;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Grid {

    private int width;
    private int height;

    private State[][] contents;

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;

        this.contents = new State[height][width];
    }

    public void setState(int x, int y, State state) {
        contents[y][x] = state;
    }

    public boolean isAlive(int x, int y) {
        return contents[y][x] == State.ALIVE;
    }

    public State getState(int x, int y) {
        return contents[y][x];
    }

    public boolean willStayAlive(int x, int y) {
        int living = countLivingNeighbours(x, y);

        if (living == 3) {
            return true;
        }
        if (living == 2) {
            return this.isAlive(x, y);
        }
        return false;
    }

    public Grid initialize() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                setState(x, y, State.DEAD);
            }
        }

        return this;
    }

    public Grid computeEvolutions() {
        Grid newGrid = new Grid(width, height).initialize();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                State nextState = State.DEAD;

                if (willStayAlive(x, y)) {
                    nextState = State.ALIVE;
                }
                newGrid.setState(x, y, nextState);
            }
        }

        return newGrid;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private boolean hasNextRow(int y) {
        return y < (height - 1);
    }

    private boolean hasNextColumn(int x) {
        return x < (width - 1);
    }

    private List<State> filterOutDeadCells(List<State> neighbours) {
        return neighbours
                .stream()
                .filter(value -> value == State.ALIVE)
                .collect(toList());
    }

    private int countLivingNeighbours(int x, int y) {
        List<State> neighbours = new ArrayList<>();
        if (x > 0) {
            neighbours.add(this.getState(x - 1, y));
        }
        if (y > 0) {
            neighbours.add(this.getState(x, y - 1));
        }
        if (x > 0 && y > 0) {
            neighbours.add(this.getState(x - 1, y - 1));
        }
        if (x > 0 && hasNextRow(y)) {
            neighbours.add(this.getState(x - 1, y + 1));
        }
        if (y > 0 && hasNextColumn(x)) {
            neighbours.add(this.getState(x + 1, y - 1));
        }
        if (hasNextColumn(x)) {
            neighbours.add(this.getState(x + 1, y));
        }
        if (hasNextColumn(x) && hasNextRow(y)) {
            neighbours.add(this.getState(x + 1, y + 1));
        }
        if (hasNextRow(y)) {
            neighbours.add(this.getState(x, y + 1));
        }

        return filterOutDeadCells(neighbours).size();
    }

}
