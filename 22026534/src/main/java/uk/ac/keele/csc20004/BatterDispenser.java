/* **********************
 * CSC-20004 COURSEWORK *
 * Re-sit July 2025     *
 * **********************/
package uk.ac.keele.csc20004;

import uk.ac.keele.csc20004.cakes.Batter;

/**
 * A concrete implementation of a dispenser; in this case, for Batter. 
 *
 * @see Dispenser
 * 
 * @author marcoortolani
 */
public class BatterDispenser extends Dispenser<Batter> {

    /**
     * Constructor for the BatterDispenser.
     * 
     * @param capacity the initial number of ingredients that will be stored in the dispenser
     */
    public BatterDispenser(int capacity) {
        super(capacity);
    }

    /**
     * Return the cost of the ingredient.
     * 
     * @return the cost of the ingredient
     */
    @Override
    public float getIngredientCost() {
        return SimulationUtils.BATTER_COST;
    }

    /**
     * Create a new instance of the ingredient.
     * 
     * @return a new ingredient object
     */
    @Override
    protected Batter createIngredient() {
        return new Batter();
    }
}
