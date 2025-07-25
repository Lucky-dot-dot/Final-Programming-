/* **********************
 * CSC-20004 COURSEWORK *
 * Re-sit July 2025     *
 * **********************/
package uk.ac.keele.csc20004;

import java.util.ArrayList;

import uk.ac.keele.csc20004.cakes.Ingredient;
import uk.ac.keele.csc20004.cakes.MissingIngredientException;

/**
 * Abstract class implementing an ingredient dispenser.
 * The class is abstract because it does not specify an implementation for the
 * data structure holding the ingredients (and does not specify the type of ingredient,
 * which is a generic type here).
 * 
 * @param <T> the type of ingredient to be dispensed
 *
 * @see uk.ac.keele.csc20004.BatterDispenser
 * @see uk.ac.keele.csc20004.FlavourDispenser
 * @see uk.ac.keele.csc20004.FrostingDispenser
 * 
 * @author marcoortolani
 */
public abstract class Dispenser<T extends Ingredient> {
    private final ArrayList<T> storage;

    /**
     * Constructor for the Dispenser.
     * 
     * @param capacity the initial number of ingredients that will be stored in the dispenser
     */
    public Dispenser(int capacity) {
        storage = new ArrayList<>(capacity);

        for (int i = 0; i < capacity; i++) {
            storage.add(createIngredient());
        }
    }

    /**
     * Creates a new ingredient to be stored in the dispenser.
     * 
     * @return a new ingredient of the type held by the specific dispenser
     */
    protected abstract T createIngredient();


    /**
     * Return the cost of the ingredient held by the specific concrete dispenser
     * (this will need to be specified in the subclass)
     *
     * @return the cost of the ingredient stored in the concrete implementation
     */
    protected abstract float getIngredientCost();

    /**
     * Get the number of ingredients currently in the dispenser.
     * 
     * @return the number of ingredients currently in the dispenser
     */
    public int getSize() {
        return storage.size();
    }

    /**
     * Check if the dispenser contains no more ingredients.
     *
     * @return true if the dispenser currently holds no ingredient
     */
    public boolean isEmpty() {
        return storage.isEmpty();
    }

    /**
     * The actual method to dispense ingredients.
     * Note that the method is final, meaning that it *cannot* be overloaded in subclasses.
     * This is to avoid that the programmer "skips" the simulation delay.
     * Getting different types of ingredients can be achieved by overloading getIngredient()
     * instead
     *
     * @return a new ingredient from the dispenser
     * @throws MissingIngredientException if no ingredient can be returned at present time; this will need to be
     * handled
     */
    public final T dispenseIngredient() throws MissingIngredientException {
        if (this.isEmpty()) {
            throw new MissingIngredientException(this.getClass().getSimpleName() + " is empty!");
        }

        T i = storage.remove(0);

        long dispenseDelay = (long)getIngredientCost() * SimulationUtils.BASE_DISPENSE_TIME;

        try {
            Thread.sleep(dispenseDelay);
        } catch (InterruptedException e) {
            System.err.format("IOException: %s%n", e);
        }

        return i;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }   
}
