/* **********************
 * CSC-20004 COURSEWORK *
 * Re-sit July 2025     *
 * **********************/
package uk.ac.keele.csc20004.cakes;

/**
 * A "combined ingredient" that represents frosting with a flavour.
 *
 * @author marcoortolani
 */
public class FlavouredFrosting implements Ingredient {
    private final Frosting frosting;
    private final Flavour flavour;

    /**
     * Constructor for a flavoured frosting (frosting with added flavour)
     * @param frosting the frosting
     * @param flavour the flavour to be added
     */
    public FlavouredFrosting(Frosting frosting, Flavour flavour) {
        this.frosting = frosting;
        this.flavour = flavour;
    }

    /**
     * Returns the cost of the ingredient.
     *
     * @return the cost of the ingredient
     */
    @Override
    public float getCost() {
        return frosting.getCost() + flavour.getCost();
    }

    @Override
    public String toString() {
        return "flavoured frosting";
    }
}
