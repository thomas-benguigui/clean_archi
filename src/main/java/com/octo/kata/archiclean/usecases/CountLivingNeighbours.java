package com.octo.kata.archiclean.usecases;

import com.octo.kata.archiclean.entities.Grid;
import com.octo.kata.archiclean.entities.State;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class CountLivingNeighbours {

    private static List<State> filterOutDeadCells(List<State> neighbours) {
        return neighbours
                .stream()
                .filter(value -> value == State.ALIVE)
                .collect(toList());
    }

    private static Boolean hasNextRow(Grid grid, Integer y) {
        return y < (grid.height - 1);
    }

    private static Boolean hasNextColumn(Grid grid, Integer x) {
        return x < (grid.width - 1);
    }

    public static Integer execute(Grid grid, int x, int y) {
        List<State> neighbours = new ArrayList<>();
        if (x > 0) {
            neighbours.add(grid.getState(x - 1, y));
        }
        if (y > 0) {
            neighbours.add(grid.getState(x, y - 1));
        }
        if (x > 0 && y > 0) {
            neighbours.add(grid.getState(x - 1, y - 1));
        }
        if (x > 0 && hasNextRow(grid, y)) {
            neighbours.add(grid.getState(x - 1, y + 1));
        }
        if (y > 0 && hasNextColumn(grid, x)) {
            neighbours.add(grid.getState(x + 1, y - 1));
        }
        if (hasNextColumn(grid, x)) {
            neighbours.add(grid.getState(x + 1, y));
        }
        if (hasNextColumn(grid, x) && hasNextRow(grid, y)) {
            neighbours.add(grid.getState(x + 1, y + 1));
        }
        if (hasNextRow(grid, y)) {
            neighbours.add(grid.getState(x, y + 1));
        }

        return filterOutDeadCells(neighbours).size();
    }
}
