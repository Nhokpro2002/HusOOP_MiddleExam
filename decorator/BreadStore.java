package hus.oop.decorator;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class BreadStore {
	private List<Bread> breads;

	public BreadStore() {
		this.breads = new ArrayList<>();
	}

	public void init() {
		Random rand = new Random();
		int n = rand.nextInt(6) + 5; // Random number between 5 and 10

		for (int i = 0; i < n; i++) {
			// Create ThickcrustBreads with various toppings
			breads.add(new Cheese(new ThickcrustBread())); // Cheese only
			breads.add(new Olives(new ThickcrustBread())); // Olives only
			breads.add(new Olives(new Cheese(new ThickcrustBread()))); // Cheese + Olives
			breads.add(new Cheese(new Olives(new ThickcrustBread()))); // Olives + Cheese

			// Create ThincrustBreads with various toppings
			breads.add(new Cheese(new ThincrustBread())); // Cheese only
			breads.add(new Olives(new ThincrustBread())); // Olives only
			breads.add(new Olives(new Cheese(new ThincrustBread()))); // Cheese + Olives
			breads.add(new Cheese(new Olives(new ThincrustBread()))); // Olives + Cheese
		}
	}

	public void add(Bread bread) {
		breads.add(bread);
	}

	public boolean sell(String breadDescription) {
		for (int i = 0; i < breads.size(); i++) {
			if (breads.get(i).getDescription().equals(breadDescription)) {
				breads.remove(i);
				return true;
			}
		}
		return false;
	}

	public void print(PrintWriter writer) {
		for (Bread bread : breads) {
			writer.println(bread.getDescription() + ", Cost " + bread.cost());
		}
	}

	public List<Bread> sort(boolean order) {
		List<Bread> sortedBreads = new ArrayList<>(breads);
		sortedBreads.sort(Comparator.comparingDouble(Bread::cost));
		if (!order) {
			Collections.reverse(sortedBreads); // Sort in descending order
		}
		return sortedBreads;
	}

	public List<Bread> filter(int howMany, boolean order) {
		List<Bread> sortedBreads = sort(order);
		return sortedBreads.subList(0, Math.min(howMany, sortedBreads.size()));
	}

	public static void main(String[] args) {
		try (PrintWriter writer = new PrintWriter(new FileWriter("output2.txt"))) {
			BreadStore store = new BreadStore();
			store.init(); // Initialize the store with random breads

			writer.println("BreadStore initialized with random breads:");

			// Print initial breads
			store.print(writer);

			// Demo: Selling 5 breads
			for (int i = 0; i < 5; i++) {
				Bread breadToSell = store.breads.get(0); // Get the first bread in the list
				store.sell(breadToSell.getDescription()); // Sell it
				writer.println("Sold: " + breadToSell.getDescription());

				writer.println("Remaining breads in store:");
				store.print(writer);

				writer.println("Breads sorted by price (descending):");
				List<Bread> sortedBreads = store.sort(false);
				for (Bread b : sortedBreads) {
					writer.println(b.getDescription() + ", Cost " + b.cost());
				}
			}

			// Print the top 10 most expensive breads remaining in the store
			writer.println("Top 10 most expensive breads:");
			List<Bread> expensiveBreads = store.filter(10, true);
			for (Bread bread : expensiveBreads) {
				writer.println(bread.getDescription() + ", Cost " + bread.cost());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
