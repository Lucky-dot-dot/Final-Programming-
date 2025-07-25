/* **********************
 * CSC-20004 COURSEWORK *
 * Re-sit July 2025     *
 * **********************/
package uk.ac.keele.csc20004.cakes;

/**
 * A "combined ingredient" that represents batter with a flavour.
 *
 * @author marcoortolani
 */
public class FlavouredBatter implements Ingredient {
    private final Batter batter;
    private final Flavour flavour;

    /**
     * Constructor for a flavoured batter (batter with added flavour)
     * @param batter the batter
     * @param flavour the flavour to be added
     */
    public FlavouredBatter(Batter batter, Flavour flavour) {
        this.batter = batter;
        this.flavour = flavour;
    }

    /**
     * Returns the cost of the ingredient.
     *
     * @return the cost of the ingredient
     */
    @Override
    public float getCost() {
        return batter.getCost() + flavour.getCost();
    }

    @Override
    public String toString() {
        return "flavoured batter";
    }
}
