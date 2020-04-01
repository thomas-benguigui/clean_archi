package com.octo.kata.archiclean.usecases;

import com.octo.kata.archiclean.entities.State;

public class InitializeGrid {

    public static State[][] execute(int width, int height){
        State[][] grid = new State[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = State.DEAD;
            }
        }
        return grid;
    }
}
