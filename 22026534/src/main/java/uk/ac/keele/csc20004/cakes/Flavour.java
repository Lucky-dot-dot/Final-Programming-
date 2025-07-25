/* **********************
 * CSC-20004 COURSEWORK *
 * Re-sit July 2025     *
 * **********************/
package uk.ac.keele.csc20004.cakes;

import uk.ac.keele.csc20004.SimulationUtils;

/**
 * Concrete implementation of an ingredient: filling.
 * The purpose of the class is mainly to define the
 * ingredient cost and to provide a very basic description.
 *
 * @author marcoortolani
 */
public class Flavour implements Ingredient {
    @Override
    public float getCost() {
        return SimulationUtils.FLAVOUR_COST;
    }

    @Override
    public String toString() {
        return "filling";
    }
}
