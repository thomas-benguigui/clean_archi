package com.octo.kata.archiclean.usecases;

import com.octo.kata.archiclean.entities.Grid;
import com.octo.kata.archiclean.repositories.StateRepository;

import java.io.IOException;

public class GetGridFromTemplate {

    public static Grid execute(String template) throws IOException {
        return StateRepository.getFromTemplate(template);
    }
}
