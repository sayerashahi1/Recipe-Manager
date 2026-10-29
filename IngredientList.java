package com.example.recipemanager;

import java.util.ArrayList;

public class IngredientList<T extends Ingredient> {
    private ArrayList<T> list = new ArrayList<>();

    public void add(T item) { list.add(item); }
    public T get(int index) { return list.get(index); }
    public int size() { return list.size(); }
    public ArrayList<T> getAll() { return list; }
}