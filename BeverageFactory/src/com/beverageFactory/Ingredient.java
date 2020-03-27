package com.beverageFactory;

public enum Ingredient {
	
	MILK("milk", 1), 
	SUGAR("sugar", 0.5), 
	WATER("water", 0.5), 
	SODA("soda", 0.5),
	MINT("mint", 0.5),
	COFFEE("coffee", 0), 
	TEA("tea", 0),
	BANANA("banana", 0),
	STRAWBERRY("strawberry", 0),
	LEMON("lemon", 0);
	
	String name;
	double price;
	
	private Ingredient(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return this.name;
	}

	public double getPrice() {
		return this.price;
	}

	public static Ingredient getIngredient(String name) {
		for (Ingredient ingredient : Ingredient.values()) {
			if (ingredient.name.equalsIgnoreCase(name)) {
				return ingredient;
			}
		}
		return null;
	}
	
}
