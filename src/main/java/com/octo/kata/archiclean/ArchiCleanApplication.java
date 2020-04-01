package com.octo.kata.archiclean;

import com.octo.kata.archiclean.entities.Cell;
import com.octo.kata.archiclean.entities.State;
import com.octo.kata.archiclean.repositories.StateRepository;
import com.octo.kata.archiclean.usecases.ComputeEvolutions;
import com.octo.kata.archiclean.usecases.GetGridFromTemplate;
import com.octo.kata.archiclean.usecases.InitializeGrid;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@SpringBootApplication
@RestController
public class ArchiCleanApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArchiCleanApplication.class, args);
    }

    @GetMapping(value = "/grid", produces = APPLICATION_JSON_VALUE)
    public List<Cell> getFromTemplate(@RequestParam("template") String template) throws IOException {
        State[][] grid = GetGridFromTemplate.execute(template);
        return gridToCellArray(grid);
    }

    @PostMapping(value = "/grid", produces = APPLICATION_JSON_VALUE)
    public List<Cell> evolveGrid(@RequestBody List<Cell> cells) {
        Pair<Integer, Integer> cellArrayDimensions = getCellArrayDimensions(cells);
        Integer width = cellArrayDimensions.getLeft();
        Integer height = cellArrayDimensions.getRight();

        State[][] grid = cellArrayToGrid(cells, width, height);
        State[][] newGrid = ComputeEvolutions.execute(grid);
        return gridToCellArray(newGrid);
    }

    private State[][] cellArrayToGrid(List<Cell> cells, Integer width, Integer height) {
        State[][] grid = new State[height][width];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                grid[y][x] = State.DEAD;
            }
        }
        cells.forEach(cell -> {
            grid[cell.y][cell.x] = (cell.alive ? State.ALIVE : State.DEAD);
        });
        return grid;
    }

    private List<Cell> gridToCellArray(State[][] grid) {
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

    private Pair<Integer, Integer> getCellArrayDimensions(List<Cell> cells) {
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

    private Boolean hasNextRow(State[][] grid, Integer y) {
        return y < (grid.length - 1);
    }

    private Boolean hasNextColumn(State[][] grid, Integer x, Integer y) {
        return x < (grid[y].length - 1);
    }

    private List<State> filterOutDeadCells(List<State> neighbours) {
        return neighbours
                .stream()
                .filter(value -> value == State.ALIVE)
                .collect(toList());
    }

    private Pair<Integer, Integer> getGridDimensions(State[][] grid) {
        return Pair.of(grid[0].length, grid.length);
    }
}
