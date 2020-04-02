package com.octo.kata.archiclean.entities;

public class Cell {

    private Integer x;
    private Integer y;
    private Boolean alive;

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Boolean isAlive() {
        return alive;
    }

    public void setAlive(Boolean alive) {
        this.alive = alive;
    }
}
