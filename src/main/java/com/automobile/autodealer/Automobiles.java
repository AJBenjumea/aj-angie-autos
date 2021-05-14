package com.automobile.autodealer;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class Automobiles {
    private List<Auto> automobiles;

    public Automobiles() {
    }

    public Automobiles(List<Auto> automobiles) {
        this.automobiles = automobiles;
    }

    public List<Auto> getAutomobiles() {
        return automobiles;
    }

    public void setAutomobiles(List<Auto> automobiles) {
        this.automobiles = automobiles;
    }

    @Override
    public String toString() {
        return "Automobiles{" +
                "automobiles=" + automobiles +
                '}';
    }

    @JsonIgnore
    public boolean isEmpty() {
        return automobiles.size() == 0;
    }
}
