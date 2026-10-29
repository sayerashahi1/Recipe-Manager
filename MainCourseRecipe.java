package com.example.recipemanager;

public class MainCourseRecipe extends Recipe {
    private int prepTime;

    public MainCourseRecipe(String name, int prepTime) {
        super(name);
        this.prepTime = prepTime;
    }

    @Override
    public void display() {
        System.out.println("Main Course: " + name + " - Prep time: " + prepTime + " min");
        for (int i = 0; i < ingredients.size(); i++) {
            System.out.println(ingredients.get(i));
        }
    }

    @Override
    public void cook() {
        System.out.println("Cooking main course: " + name);
    }
}