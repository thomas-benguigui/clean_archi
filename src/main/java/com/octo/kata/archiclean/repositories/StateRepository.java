package com.octo.kata.archiclean.repositories;

import com.octo.kata.archiclean.entities.Grid;
import com.octo.kata.archiclean.entities.State;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;

public class StateRepository {

    public static Grid getFromTemplate(String template) throws IOException {
        File file = new ClassPathResource("grids/" + template + ".grid").getFile();
        String fileContent = FileUtils.readFileToString(file, UTF_8);

        return initGridFromTemplate(fileContent);
    }

    public static Grid initializeGrid(int width, int height) {
        Grid grid = new Grid(width, height);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid.setState(x, y, State.DEAD);
            }
        }

        return grid;
    }

    private static Grid initGridFromTemplate(String template) {
        String[] lines = template.split("\n");
        int height = lines.length;
        int width = lines[0].length();

        Grid grid = initializeGrid(width, height);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (lines[y].charAt(x) == State.ALIVE.value) {
                    grid.setState(x, y, State.ALIVE);
                } else {
                    grid.setState(x, y, State.DEAD);
                }
            }
        }

        return grid;
    }
}
