package com.example.recipemanager;

import java.util.Queue;

public abstract class Recipe implements Cookable {
    protected String name;
    protected IngredientList<Ingredient> ingredients;
    protected Queue<String> steps;

    public Recipe(String name) {
        this.name = name;
        this.ingredients = new IngredientList<>();
        this.steps = new java.util.LinkedList<>();
    }

    public String getName() { return name; }
    public IngredientList<Ingredient> getIngredients() { return ingredients; }
    public Queue<String> getSteps() { return steps; }

    public abstract void display();

    // Recursion: calculate total quantity of ingredients
    public double getTotalQuantity() {
        return getTotalQuantityRecursive(0);
    }

    private double getTotalQuantityRecursive(int index) {
        if (index >= ingredients.size()) return 0;
        return ingredients.get(index).getQuantity() + getTotalQuantityRecursive(index + 1);
    }
}