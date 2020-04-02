package com.octo.kata.archiclean.entities;

public enum State {

    DEAD(' '),
    ALIVE('o');

    private char value;

    State(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }
}
