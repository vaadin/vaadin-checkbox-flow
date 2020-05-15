package com.vaadin.flow.component.checkbox.demo.data;

import com.vaadin.flow.component.checkbox.demo.entity.Dish;

import java.util.Arrays;
import java.util.Collection;

public class DishData {

    private final static Dish DISH_OF_THE_DAY =
            new Dish(false, "Caprese Pasta Salad");

    private final static Collection<Dish> DISH_LIST = createDishList();

    private static Collection<Dish> createDishList() {
        return Arrays.asList(
                new Dish(false, "Pasta Carbonara"),
                new Dish(true, "Vegetarian Tortilla Soup"),
                new Dish(false, "Beef steak"),
                DISH_OF_THE_DAY);
    }

    public static Collection<Dish> getDishes() {
        return DISH_LIST;
    }

    public static Dish getDishOfTheDay() { return DISH_OF_THE_DAY; }
}
