package com.octo.kata.archiclean.controller;

import com.octo.kata.archiclean.entities.Cell;
import com.octo.kata.archiclean.entities.Grid;
import com.octo.kata.archiclean.mapper.CellListToGridMapper;
import com.octo.kata.archiclean.presenter.GridToCell;
import com.octo.kata.archiclean.usecases.EvolveGrid;
import com.octo.kata.archiclean.usecases.GetGridFromTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class MainController {

    private final GridToCell gridToCell;
    private final EvolveGrid evolveGrid;
    private final GetGridFromTemplate getGridFromTemplate;
    private final CellListToGridMapper cellListToGridMapper;

    @Autowired
    public MainController(GridToCell gridToCell, EvolveGrid evolveGrid, GetGridFromTemplate getGridFromTemplate, CellListToGridMapper cellListToGridMapper) {
        this.gridToCell = gridToCell;
        this.evolveGrid = evolveGrid;
        this.getGridFromTemplate = getGridFromTemplate;
        this.cellListToGridMapper = cellListToGridMapper;
    }

    @GetMapping(value = "/grid", produces = APPLICATION_JSON_VALUE)
    public List<Cell> getFromTemplate(@RequestParam("template") String template) throws IOException {
        Grid grid = getGridFromTemplate.execute(template);
        return gridToCell.execute(grid);
    }

    @PostMapping(value = "/grid", produces = APPLICATION_JSON_VALUE)
    public List<Cell> evolveGrid(@RequestBody List<Cell> cells) {
        Grid grid = cellListToGridMapper.execute(cells);

        Grid newGrid = evolveGrid.execute(grid);
        return gridToCell.execute(newGrid);
    }

}
