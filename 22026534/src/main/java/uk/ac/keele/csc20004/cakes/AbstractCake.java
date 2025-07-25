/* **********************
 * CSC-20004 COURSEWORK *
 * Re-sit July 2025     *
 * **********************/
package uk.ac.keele.csc20004.cakes;

import uk.ac.keele.csc20004.SimulationUtils;

import java.util.Arrays;

/**
 * Generic implementation of a Cake. Even though basic implementations for the
 * main methods are provided, the class is declared as abstract as it does not
 * make sense to instantiate a "generic cake". Rather, you should refer to the
 * concrete implementations, or provide your own.
 *
 * @author marcoortolani
 */
public abstract class AbstractCake implements Cake {
    private static int counter = 0;
    private static final int MAX_COUNTER = SimulationUtils.DISPENSER_CAPACITY;
    private final int id;

    private final Ingredient[] ingredients;
    private final float price;
    private int shelfLife;

    /**
     * Constructor for a cake, starting from the list (array) of its
     * ingredients. The cake will also initialise its price, as the
     * sum of the costs of all of its ingredients. The cake will also
     * have an initial shelf life (i.e. the number of time units it can
     * stay on the display counter before it is considered stale)
     *
     * @param ingredients      an array containing the ingredients for the cake
     * @param initialShelfLife the initial shelf life of the cake
     */
    protected AbstractCake(Ingredient[] ingredients, int initialShelfLife) {
        this.id = (counter++) % MAX_COUNTER;
        this.ingredients = new Ingredient[ingredients.length];

        int price = 0;
        for (int i = 0; i < ingredients.length; i++) {
            this.ingredients[i] = ingredients[i];
            price += ingredients[i].getCost();
        }

        this.price = price;

        this.shelfLife = initialShelfLife;
    }

    /**
     * Returns the price of the cake, computed as the sum of the costs
     * of all of its ingredients.
     *
     * @return the price of the cake
     */
    @Override
    public float getPrice() {
        return price;
    }

    /**
     * Computes the time it takes to simulate the preparation of the cake, as
     * a function of the price
     *
     * @return the time (in ms) to prepare the cake
     */
    @Override
    public final int getPreparationTime() {
        return (int) price * SimulationUtils.BASE_CAKE_PREP_TIME;
    }

    /**
     * Returns the shelf life of the cake, i.e., the number of time units
     * it can stay on the display counter before it is considered stale.
     * 
     * @return the shelf life of the cake
     */
    @Override
    public final int getShelfLife() {
        return shelfLife;
    }

    /**
     * Decreases the shelf life of the cake by one time unit.
     */
    public final void decreaseShelfLife() {
        shelfLife--;
    }

    @Override
    public String toString() {
        String description = "cake #" + id + " with " + Arrays.toString(ingredients) +
                ", price = £" + price;

        return description;
    }
}
