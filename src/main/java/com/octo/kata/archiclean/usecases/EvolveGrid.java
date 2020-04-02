package com.octo.kata.archiclean.usecases;

import com.octo.kata.archiclean.entities.Grid;

public class EvolveGrid {

    public static Grid execute(Grid grid) {
        return grid.computeEvolutions();
    }
}
