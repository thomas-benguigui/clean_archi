package com.octo.kata.archiclean.usecases;

import com.octo.kata.archiclean.entities.Cell;
import com.octo.kata.archiclean.entities.Grid;
import com.octo.kata.archiclean.entities.State;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class EvolveGrid {

    public static Grid execute(List<Cell> cells) {
        Pair<Integer, Integer> cellArrayDimensions = getCellArrayDimensions(cells);
        Integer width = cellArrayDimensions.getLeft();
        Integer height = cellArrayDimensions.getRight();

        Grid grid = cellArrayToGrid(cells, width, height);
        return ComputeEvolutions.execute(grid);
    }

    private static Pair<Integer, Integer> getCellArrayDimensions(List<Cell> cells) {
        Integer maxX = cells.get(0).x;
        Integer maxY = cells.get(0).y;

        for (Cell cell : cells) {
            if (cell.x > maxX) {
                maxX = cell.x;
            }
            if (cell.y > maxY) {
                maxY = cell.y;
            }
        }
        return Pair.of(maxX + 1, maxY + 1);
    }

    private static Grid cellArrayToGrid(List<Cell> cells, Integer width, Integer height) {
        Grid grid = new Grid(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                grid.setState(x, y, State.DEAD);
            }
        }
        cells.forEach(cell -> {
            grid.setState(cell.x, cell.y, (cell.alive ? State.ALIVE : State.DEAD));
        });
        return grid;
    }
}
