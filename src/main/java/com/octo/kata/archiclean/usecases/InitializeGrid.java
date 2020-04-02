package com.octo.kata.archiclean.usecases;

import com.octo.kata.archiclean.entities.State;
import com.octo.kata.archiclean.repositories.StateRepository;

public class InitializeGrid {

    public static State[][] execute(int width, int height) {
        return StateRepository.initializeGrid(width,height);
    }
}
