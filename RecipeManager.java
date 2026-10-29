package com.example.recipemanager;

import java.io.*;
import java.util.*;

public class RecipeManager {
    private List<Recipe> recipes = new ArrayList<>();
    private Stack<String> undoStack = new Stack<>();

    public void addRecipe(Recipe r) {
        recipes.add(r);
        undoStack.push("Added " + r.getName());
    }

    public void saveToFile(String filename) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            for (Recipe r : recipes) {
                pw.println(r.getName() + "," + r.getClass().getSimpleName());
                for (int i = 0; i < r.getIngredients().size(); i++) {
                    Ingredient ing = r.getIngredients().get(i);
                    pw.println(ing.getName() + "," + ing.getQuantity() + "," + ing.getUnit());
                }
                pw.println("END_ING");
                for (String step : r.getSteps()) {
                    pw.println(step);
                }
                pw.println("END_STEPS");
            }
        }
    }

    public void loadFromFile(String filename) throws IOException {
        recipes.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                String type = parts[1];
                Recipe r;
                if (type.equals("DessertRecipe")) {
                    r = new DessertRecipe(name, "Medium");
                } else {
                    r = new MainCourseRecipe(name, 30);
                }
                while (!(line = br.readLine()).equals("END_ING")) {
                    String[] ingParts = line.split(",");
                    r.getIngredients().add(new Ingredient(ingParts[0], Double.parseDouble(ingParts[1]), ingParts[2]));
                }
                while (!(line = br.readLine()).equals("END_STEPS")) {
                    r.getSteps().add(line);
                }
                recipes.add(r);
            }
        }
    }

    public List<Recipe> getRecipes() { return recipes; }
    public Stack<String> getUndoStack() { return undoStack; }
}