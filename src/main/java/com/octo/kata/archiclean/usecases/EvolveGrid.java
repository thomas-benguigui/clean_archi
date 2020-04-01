package com.octo.kata.archiclean.usecases;

import com.octo.kata.archiclean.entities.Cell;
import com.octo.kata.archiclean.entities.State;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class EvolveGrid {

    public static List<Cell> execute(List<Cell> cells) {
        Pair<Integer, Integer> cellArrayDimensions = getCellArrayDimensions(cells);
        Integer width = cellArrayDimensions.getLeft();
        Integer height = cellArrayDimensions.getRight();

        State[][] grid = cellArrayToGrid(cells, width, height);
        State[][] newGrid = computeEvolutions(grid);
        return gridToCellArray(newGrid);
    }
}
