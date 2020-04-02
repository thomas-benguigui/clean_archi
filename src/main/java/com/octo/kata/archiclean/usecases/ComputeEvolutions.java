package com.octo.kata.archiclean.usecases;

import com.octo.kata.archiclean.entities.Grid;
import com.octo.kata.archiclean.entities.State;
import org.apache.commons.lang3.tuple.Pair;

public class ComputeEvolutions {

    public static Grid execute(Grid grid) {
        Pair<Integer, Integer> dimensions = getGridDimensions(grid);
        Integer width = dimensions.getLeft();
        Integer height = dimensions.getRight();

        Grid newGrid = InitializeGrid.execute(width, height);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                State nextState = State.DEAD;
                if (willStayAlive(grid, x, y)) {
                    nextState = State.ALIVE;
                }
                newGrid.setState(x, y, nextState);
            }
        }
        return newGrid;
    }

    private static Pair<Integer, Integer> getGridDimensions(Grid grid) {
        return Pair.of(grid.width, grid.height);
    }

    private static Boolean willStayAlive(Grid grid, Integer x, Integer y) {
        Integer living = CountLivingNeighbours.execute(grid, x, y);
        if (living == 3) {
            return true;
        }
        if (living == 2) {
            return grid.isAlive(x, y);
        }
        return false;
    }
}
