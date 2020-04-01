package com.octo.kata.archiclean;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
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
        File file = new ClassPathResource("grids/" + template + ".grid").getFile();
        String fileContent = FileUtils.readFileToString(file, UTF_8);
        State[][] grid = initGridFromTemplate(fileContent);
        return gridToCellArray(grid);
    }

    @PostMapping(value = "/grid", produces = APPLICATION_JSON_VALUE)
    public List<Cell> evolveGrid(@RequestBody List<Cell> cells) {
        Pair<Integer, Integer> cellArrayDimensions = getCellArrayDimensions(cells);
        Integer width = cellArrayDimensions.getLeft();
        Integer height = cellArrayDimensions.getRight();

        State[][] grid = cellArrayToGrid(cells, width, height);
        State[][] newGrid = computeEvolutions(grid);
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

    private State[][] initializeGrid(int width, int height) {
        State[][] grid = new State[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = State.DEAD;
            }
        }
        return grid;
    }

    private State[][] initGridFromTemplate(String template) {
        String[] lines = template.split("\n");
        int height = lines.length;
        int width = lines[0].length();

        State[][] grid = initializeGrid(width, height);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = (lines[y].charAt(x) == State.ALIVE.value ? State.ALIVE : State.DEAD);
            }
        }
        return grid;
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

    private Integer countLivingNeighbours(State[][] grid, int x, int y) {
        List<State> neighbours = new ArrayList<>();
        if (x > 0) {
            neighbours.add(grid[y][x - 1]);
        }
        if (y > 0) {
            neighbours.add(grid[y - 1][x]);
        }
        if (x > 0 && y > 0) {
            neighbours.add(grid[y - 1][x - 1]);
        }
        if (x > 0 && hasNextRow(grid, y)) {
            neighbours.add(grid[y + 1][x - 1]);
        }
        if (y > 0 && hasNextColumn(grid, x, y)) {
            neighbours.add(grid[y - 1][x + 1]);
        }
        if (hasNextColumn(grid, x, y)) {
            neighbours.add(grid[y][x + 1]);
        }
        if (hasNextColumn(grid, x, y) && hasNextRow(grid, y)) {
            neighbours.add(grid[y + 1][x + 1]);
        }
        if (hasNextRow(grid, y)) {
            neighbours.add(grid[y + 1][x]);
        }
        return filterOutDeadCells(neighbours).size();
    }

    private Boolean willStayAlive(State[][] grid, Integer x, Integer y) {
        Integer living = countLivingNeighbours(grid, x, y);
        if (living == 3) {
            return true;
        }
        if (living == 2) {
            return grid[y][x] == State.ALIVE;
        }
        return false;
    }

    private Pair<Integer, Integer> getGridDimensions(State[][] grid) {
        return Pair.of(grid[0].length, grid.length);
    }

    private State[][] computeEvolutions(State[][] grid) {
        Pair<Integer, Integer> dimensions = getGridDimensions(grid);
        Integer width = dimensions.getLeft();
        Integer height = dimensions.getRight();

        State[][] newGrid = initializeGrid(width, height);
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

    enum State {
        DEAD(' '),
        ALIVE('o');

        public char value;

        State(char value) {
            this.value = value;
        }
    }

    static class Cell {
        public Integer x;
        public Integer y;
        public Boolean alive;
    }
}
