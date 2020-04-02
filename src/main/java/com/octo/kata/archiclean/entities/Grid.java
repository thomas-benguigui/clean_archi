package com.octo.kata.archiclean.entities;

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
}
