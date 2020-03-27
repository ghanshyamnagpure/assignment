package com.beverageFactory;

import java.util.ArrayList;
import java.util.List;

public class Beverage {
	String name;
	double price;
	List<Ingredient> ingredients = new ArrayList<>();
	// nonRemovableIngredients represent must have ingredient in a beverage e.g. coffee in Coffee beverage
	List<Ingredient> nonRemovableIngredients = new ArrayList<>();

	public Beverage(String name, double price, List<Ingredient> ingredients, List<Ingredient> nonRemovableIngredients) {
		this.name = name;
		this.price = price;
		this.ingredients = ingredients;
		this.nonRemovableIngredients = nonRemovableIngredients;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public List<Ingredient> getNonRemovableIngredients() {
		return nonRemovableIngredients;
	}

	public void setNonRemovableIngredients(List<Ingredient> nonRemovableIngredients) {
		this.nonRemovableIngredients = nonRemovableIngredients;
	}
	
}
