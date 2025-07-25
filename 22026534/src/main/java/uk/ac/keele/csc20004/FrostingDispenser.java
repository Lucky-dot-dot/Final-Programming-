/* **********************
 * CSC-20004 COURSEWORK *
 * Re-sit July 2025     *
 * **********************/
package uk.ac.keele.csc20004;

import uk.ac.keele.csc20004.cakes.Frosting;

/**
 * A concrete implementation of a dispenser; in this case, for Icing. 
 *
 * @see Dispenser
 * 
 * @author marcoortolani
 */
public class FrostingDispenser extends Dispenser<Frosting> {

    /**
     * Constructor for the BatterDispenser.
     * 
     * @param capacity the initial number of ingredients that will be stored in the dispenser
     */
    public FrostingDispenser(int capacity) {
        super(capacity);
    }

    /**
     * Return the cost of the ingredient.
     * 
     * @return the cost of the ingredient
     */
    @Override
    public float getIngredientCost() {
        return SimulationUtils.FROSTING_COST;
    }

    /**
     * Create a new instance of the ingredient.
     * 
     * @return a new ingredient object
     */
    @Override
    protected Frosting createIngredient() {
        return new Frosting();
    }
}
