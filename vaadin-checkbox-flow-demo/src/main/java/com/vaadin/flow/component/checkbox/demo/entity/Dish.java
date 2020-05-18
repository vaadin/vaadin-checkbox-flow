package com.vaadin.flow.component.checkbox.demo.entity;

import java.util.Objects;

public class Dish {
    private boolean vegetarian;
    private String name;

    public Dish(boolean vegetarian, String name) {
        this.vegetarian = vegetarian;
        this.name = name;
    }

    public boolean isVegetarian() { return vegetarian; }
    public String getName() { return name; }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Dish dish = (Dish) o;
        return vegetarian == dish.vegetarian &&
                Objects.equals(name, dish.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vegetarian, name);
    }

    @Override
    public String toString() {
        return String.valueOf(name);
    }
}
