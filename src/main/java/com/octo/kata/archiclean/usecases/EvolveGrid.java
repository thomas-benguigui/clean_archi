package com.octo.kata.archiclean.usecases;

import com.octo.kata.archiclean.entities.Grid;
import org.springframework.stereotype.Component;

@Component
public class EvolveGrid {

    public Grid execute(Grid grid) {
        return grid.computeEvolutions();
    }
}
