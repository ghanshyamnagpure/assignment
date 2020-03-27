package com.beverageFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class BeverageFactory {

	static List<Beverage> beverages = new ArrayList<>();

	static {
		beverages.add(new Beverage("Coffee", 5,
				Arrays.asList(Ingredient.COFFEE, Ingredient.MILK, Ingredient.SUGAR, Ingredient.WATER),
				Arrays.asList(Ingredient.COFFEE)));
		beverages.add(new Beverage("Chai", 4,
				Arrays.asList(Ingredient.TEA, Ingredient.MILK, Ingredient.SUGAR, Ingredient.WATER),
				Arrays.asList(Ingredient.TEA)));
		beverages.add(new Beverage("Banana smoothie", 6,
				Arrays.asList(Ingredient.BANANA, Ingredient.MILK, Ingredient.SUGAR, Ingredient.WATER),
				Arrays.asList(Ingredient.BANANA)));
		beverages.add(new Beverage("Strawberry Shake", 5,
				Arrays.asList(Ingredient.STRAWBERRY, Ingredient.MILK, Ingredient.SUGAR, Ingredient.WATER),
				Arrays.asList(Ingredient.STRAWBERRY)));
		beverages.add(new Beverage("Mojito", 5,
				Arrays.asList(Ingredient.LEMON, Ingredient.SODA, Ingredient.SUGAR, Ingredient.WATER, Ingredient.MINT),
				Arrays.asList(Ingredient.MILK)));

	}

	public static void main(String[] args) {

		String order = "[\"chai,-sugar\",\"chai\",\"coffee,-milk\"]";
		
		BeverageFactory beverageFactory = new BeverageFactory();
		
		try {
			System.out.println("Order price : " + beverageFactory.calculateOrderValue(order));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public double calculateOrderValue(String userOrder) {

		List<String> orderItems = seperateOrderBeverages(userOrder);
		
		double orderValue = 0;

		for (String order : orderItems) {

			if (order == null || order.isEmpty()) {
				throw new RuntimeException("Invalid order");
			}
			String[] orderDetails = order.split(",");
			
			Optional<Beverage> beverage = beverages.stream()
					.filter(bvrg -> bvrg.getName().equalsIgnoreCase(orderDetails[0])).findFirst();

			if (beverage.isPresent()) {
				List<String> exclusions = new ArrayList();
				if (orderDetails.length > 1) {
					exclusions.addAll(Arrays.asList(orderDetails));
					exclusions.remove(0);
				}
				Double beveragePrice = calculatePrice(beverage.get(), exclusions);
				if (beveragePrice == null) {
					throw new RuntimeException("Invalid order");
				}
				orderValue = orderValue + beveragePrice;
			} else {
				throw new RuntimeException("Invalid order");
			}
		}
		
		return orderValue;
	}
	
	private List<String> seperateOrderBeverages(String order) {

		String[] items = order.replace("[", "").replace("]", "").split("\",");
		List<String> orderItems = new ArrayList();
		for (String item : items) {
			orderItems.add(item.replace("\"", ""));
		}
		return orderItems;
	}
	
	private Double calculatePrice(Beverage beverage, List<String> ingredientsToBeRemoved)  {
		if (ingredientsToBeRemoved.isEmpty()) {
			return beverage.getPrice();
		} else {
			double beveragePrice = beverage.getPrice();
			for (String receivedIngredient : ingredientsToBeRemoved) {
				Ingredient ingredient = Ingredient.getIngredient(receivedIngredient.substring(1, receivedIngredient.length()));
				// check if provided ingredient name is valid. If valid, then has not excluded non removable ingredient 
				if (ingredient == null || !beverage.getIngredients().stream().anyMatch(ingrd -> ingrd == ingredient)
						|| beverage.getNonRemovableIngredients().stream().anyMatch(ingrd -> ingrd == ingredient)) {
					return null;
				}
				beveragePrice = beveragePrice - ingredient.getPrice();
			}
			return beveragePrice;
		}
	}
}
