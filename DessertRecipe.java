package com.example.recipemanager;

public class DessertRecipe extends Recipe {
    private String sweetnessLevel;

    public DessertRecipe(String name, String sweetnessLevel) {
        super(name);
        this.sweetnessLevel = sweetnessLevel;
    }

    @Override
    public void display() {
        System.out.println("Dessert: " + name + " - " + sweetnessLevel);
        for (int i = 0; i < ingredients.size(); i++) {
            System.out.println(ingredients.get(i));
        }
    }

    @Override
    public void cook() {
        System.out.println("Cooking dessert: " + name);
    }
}