package com.octo.kata.archiclean;

import com.octo.kata.archiclean.entities.Cell;
import com.octo.kata.archiclean.entities.State;
import com.octo.kata.archiclean.repositories.StateRepository;
import com.octo.kata.archiclean.usecases.ComputeEvolutions;
import com.octo.kata.archiclean.usecases.EvolveGrid;
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
        State[][] newGrid = EvolveGrid.execute(cells);
        return gridToCellArray(newGrid);
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
}
