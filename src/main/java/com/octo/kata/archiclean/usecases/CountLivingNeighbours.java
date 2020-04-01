package com.octo.kata.archiclean.usecases;

import com.octo.kata.archiclean.entities.State;
import org.apache.commons.lang3.tuple.Pair;

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

    private static Boolean hasNextRow(State[][] grid, Integer y) {
        return y < (grid.length - 1);
    }

    private static Boolean hasNextColumn(State[][] grid, Integer x, Integer y) {
        return x < (grid[y].length - 1);
    }

    public static Integer execute(State[][] grid, int x, int y) {
        List<State> neighbours = new ArrayList<>();
        if (x > 0) {
            neighbours.add(grid[y][x - 1]);
        }
        if (y > 0) {
            neighbours.add(grid[y - 1][x]);
        }
        if (x > 0 && y > 0) {
            neighbours.add(grid[y - 1][x - 1]);
        }
        if (x > 0 && hasNextRow(grid, y)) {
            neighbours.add(grid[y + 1][x - 1]);
        }
        if (y > 0 && hasNextColumn(grid, x, y)) {
            neighbours.add(grid[y - 1][x + 1]);
        }
        if (hasNextColumn(grid, x, y)) {
            neighbours.add(grid[y][x + 1]);
        }
        if (hasNextColumn(grid, x, y) && hasNextRow(grid, y)) {
            neighbours.add(grid[y + 1][x + 1]);
        }
        if (hasNextRow(grid, y)) {
            neighbours.add(grid[y + 1][x]);
        }
        return filterOutDeadCells(neighbours).size();
    }
}
