package com.octo.kata.archiclean.usecases;

import com.octo.kata.archiclean.entities.Grid;
import com.octo.kata.archiclean.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GetGridFromTemplate {

    private final StateRepository stateRepository;

    @Autowired
    public GetGridFromTemplate(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    public Grid execute(String template) throws IOException {
        return stateRepository.getFromTemplate(template);
    }
}
