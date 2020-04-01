package com.octo.kata.archiclean.entities;

public enum State {
    DEAD(' '),
    ALIVE('o');

    public char value;

    State(char value) {
        this.value = value;
    }
}
