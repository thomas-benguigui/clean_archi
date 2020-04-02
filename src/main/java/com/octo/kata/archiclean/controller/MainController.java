package com.octo.kata.archiclean.controller;

import com.octo.kata.archiclean.entities.Cell;
import com.octo.kata.archiclean.entities.Grid;
import com.octo.kata.archiclean.presenter.GridToCell;
import com.octo.kata.archiclean.usecases.EvolveGrid;
import com.octo.kata.archiclean.usecases.GetGridFromTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class MainController {

    @GetMapping(value = "/grid", produces = APPLICATION_JSON_VALUE)
    public List<Cell> getFromTemplate(@RequestParam("template") String template) throws IOException {
        Grid grid = GetGridFromTemplate.execute(template);
        return GridToCell.execute(grid);
    }

    @PostMapping(value = "/grid", produces = APPLICATION_JSON_VALUE)
    public List<Cell> evolveGrid(@RequestBody List<Cell> cells) {
        Grid newGrid = EvolveGrid.execute(cells);
        return GridToCell.execute(newGrid);
    }

}
