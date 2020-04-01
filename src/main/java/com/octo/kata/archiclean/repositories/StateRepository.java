package com.octo.kata.archiclean.repositories;

import com.octo.kata.archiclean.entities.State;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;

public class StateRepository {

    public static State[][] getFromTemplate(String template) throws IOException {
        File file = new ClassPathResource("grids/" + template + ".grid").getFile();
        String fileContent = FileUtils.readFileToString(file, UTF_8);
        return initGridFromTemplate(fileContent);
    }

    private static State[][] initGridFromTemplate(String template) {
        String[] lines = template.split("\n");
        int height = lines.length;
        int width = lines[0].length();

        State[][] grid = initializeGrid(width, height);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = (lines[y].charAt(x) == State.ALIVE.value ? State.ALIVE : State.DEAD);
            }
        }
        return grid;
    }

    private static State[][] initializeGrid(int width, int height) {
        State[][] grid = new State[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = State.DEAD;
            }
        }
        return grid;
    }
}
