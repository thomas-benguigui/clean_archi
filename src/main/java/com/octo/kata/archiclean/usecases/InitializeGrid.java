package com.octo.kata.archiclean.usecases;

import com.octo.kata.archiclean.entities.Grid;
import com.octo.kata.archiclean.repositories.StateRepository;

public class InitializeGrid {

    public static Grid execute(int width, int height) {
        return StateRepository.initializeGrid(width, height);
    }
}
