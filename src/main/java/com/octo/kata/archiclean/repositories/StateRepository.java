package com.octo.kata.archiclean.repositories;

import com.octo.kata.archiclean.entities.Grid;
import com.octo.kata.archiclean.entities.State;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;

@Component
public class StateRepository {

    public Grid getFromTemplate(String template) throws IOException {
        File file = new ClassPathResource("grids/" + template + ".grid").getFile();
        String fileContent = FileUtils.readFileToString(file, UTF_8);

        return initGridFromTemplate(fileContent);
    }

    private Grid initGridFromTemplate(String template) {
        String[] lines = template.split("\n");
        int height = lines.length;
        int width = lines[0].length();

        Grid grid = new Grid(width, height).initialize();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (lines[y].charAt(x) == State.ALIVE.getValue()) {
                    grid.setState(x, y, State.ALIVE);
                } else {
                    grid.setState(x, y, State.DEAD);
                }
            }
        }

        return grid;
    }
}
