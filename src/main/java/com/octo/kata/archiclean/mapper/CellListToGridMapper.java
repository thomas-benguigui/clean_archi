package com.octo.kata.archiclean.mapper;

import com.octo.kata.archiclean.entities.Cell;
import com.octo.kata.archiclean.entities.Grid;
import com.octo.kata.archiclean.entities.State;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class CellListToGridMapper {

    public static Grid execute(List<Cell> cells) {
        Pair<Integer, Integer> cellArrayDimensions = CellListToGridMapper.getCellArrayDimensions(cells);
        Integer width = cellArrayDimensions.getLeft();
        Integer height = cellArrayDimensions.getRight();

        Grid grid = new Grid(width, height);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                grid.setState(x, y, State.DEAD);
            }
        }

        cells.forEach(cell -> {
            grid.setState(cell.getX(), cell.getY(), (cell.isAlive() ? State.ALIVE : State.DEAD));
        });

        return grid;
    }

    private static Pair<Integer, Integer> getCellArrayDimensions(List<Cell> cells) {
        Integer maxX = cells.get(0).getX();
        Integer maxY = cells.get(0).getY();

        for (Cell cell : cells) {
            if (cell.getX() > maxX) {
                maxX = cell.getX();
            }
            if (cell.getY() > maxY) {
                maxY = cell.getY();
            }
        }
        return Pair.of(maxX + 1, maxY + 1);
    }
}
