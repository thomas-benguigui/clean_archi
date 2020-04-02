package com.octo.kata.archiclean.presenter;

import com.octo.kata.archiclean.entities.Cell;
import com.octo.kata.archiclean.entities.State;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class GridToCell {

    public static List<Cell> execute(State[][] grid) {
        List<Cell> cells = new ArrayList<>();
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                Cell cell = new Cell();
                cell.x = x;
                cell.y = y;
                cell.alive = grid[y][x] == State.ALIVE;
                cells.add(cell);
            }

        }
        return cells;
    }
}
