package com.example.recipemanager;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    private RecipeManager manager = new RecipeManager();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox();
        ListView<String> listView = new ListView<>();
        Button addButton = new Button("Add Recipe");
        Button saveButton = new Button("Save");
        Button loadButton = new Button("Load");

        // Anonymous class for event handler
        addButton.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                // Add a dummy recipe
                Recipe r = new DessertRecipe("Cake", "Sweet");
                r.getIngredients().add(new Ingredient("Flour", 2, "cups"));
                manager.addRecipe(r);
                updateList(listView);
            }
        });

        // Lambda expressions
        saveButton.setOnAction(e -> {
            try {
                manager.saveToFile("recipes.csv");
                System.out.println("Saved");
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        });

        loadButton.setOnAction(e -> {
            try {
                manager.loadFromFile("recipes.csv");
                updateList(listView);
                System.out.println("Loaded");
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        });

        root.getChildren().addAll(listView, addButton, saveButton, loadButton);
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updateList(ListView<String> listView) {
        listView.getItems().clear();
        for (Recipe r : manager.getRecipes()) {
            listView.getItems().add(r.getName());
        }
    }
}