package com.octo.kata.archiclean.entities;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Grid {

    public int width;
    public int height;

    public State[][] contents;

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
        Integer living = countLivingNeighbours(x, y);
        if (living == 3) {
            return true;
        }
        if (living == 2) {
            return this.isAlive(x, y);
        }
        return false;
    }

    private static Boolean hasNextRow(Grid grid, Integer y) {
        return y < (grid.height - 1);
    }

    private static Boolean hasNextColumn(Grid grid, Integer x) {
        return x < (grid.width - 1);
    }

    private static List<State> filterOutDeadCells(List<State> neighbours) {
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
        if (x > 0 && hasNextRow(this, y)) {
            neighbours.add(this.getState(x - 1, y + 1));
        }
        if (y > 0 && hasNextColumn(this, x)) {
            neighbours.add(this.getState(x + 1, y - 1));
        }
        if (hasNextColumn(this, x)) {
            neighbours.add(this.getState(x + 1, y));
        }
        if (hasNextColumn(this, x) && hasNextRow(this, y)) {
            neighbours.add(this.getState(x + 1, y + 1));
        }
        if (hasNextRow(this, y)) {
            neighbours.add(this.getState(x, y + 1));
        }

        return filterOutDeadCells(neighbours).size();
    }

    public Pair<Integer, Integer> getGridDimensions() {
        return Pair.of(this.width, this.height);
    }

}
