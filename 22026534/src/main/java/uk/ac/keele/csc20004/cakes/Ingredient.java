/* **********************
 * CSC-20004 COURSEWORK *
 * Re-sit July 2025     *
 * **********************/
package uk.ac.keele.csc20004.cakes;

/**
 * Interface for a generic ingredient.
 * The only characteristic of an ingredient that is 
 * relevant for the simulation is its cost.
 *
 * @see Batter
 * @see Flavour
 * @see Frosting
 *
 * @author marcoortolani
*/
public interface Ingredient {
    /**
     * Returns the cost of the ingredient.
     *
     * @return the cost of the ingredient
     */
    public float getCost();
}
