package com.octo.kata.archiclean.presenter;

import com.octo.kata.archiclean.entities.Cell;
import com.octo.kata.archiclean.entities.Grid;

import java.util.ArrayList;
import java.util.List;

public class GridToCell {

    public static List<Cell> execute(Grid grid) {
        List<Cell> cells = new ArrayList<>();
        for (int y = 0; y < grid.getHeight(); y++) {
            for (int x = 0; x < grid.getWidth(); x++) {
                Cell cell = new Cell();

                cell.setX(x);
                cell.setY(y);
                cell.setAlive(grid.isAlive(x, y));

                cells.add(cell);
            }

        }
        return cells;
    }
}
