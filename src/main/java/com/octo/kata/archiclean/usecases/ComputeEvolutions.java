package com.octo.kata.archiclean.usecases;

import com.octo.kata.archiclean.entities.Cell;
import com.octo.kata.archiclean.entities.State;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class ComputeEvolutions {
    private static Pair<Integer, Integer> getGridDimensions(State[][] grid) {
        return Pair.of(grid[0].length, grid.length);
    }

    private static Boolean willStayAlive(State[][] grid, Integer x, Integer y) {
        Integer living = CountLivingNeighbours.execute(grid, x, y);
        if (living == 3) {
            return true;
        }
        if (living == 2) {
            return grid[y][x] == State.ALIVE;
        }
        return false;
    }

    public static State[][] execute(State[][] grid) {
        Pair<Integer, Integer> dimensions = getGridDimensions(grid);
        Integer width = dimensions.getLeft();
        Integer height = dimensions.getRight();

        State[][] newGrid = InitializeGrid.execute(width, height);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                State nextState = State.DEAD;
                if (willStayAlive(grid, x, y)) {
                    nextState = State.ALIVE;
                }
                newGrid[y][x] = nextState;
            }
        }
        return newGrid;
    }
}
