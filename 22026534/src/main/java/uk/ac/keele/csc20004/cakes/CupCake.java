/* **********************
 * CSC-20004 COURSEWORK *
 * Re-sit July 2025     *
 * **********************/
package uk.ac.keele.csc20004.cakes;

/**
 * A concrete implementation of a cake, implementing the "recipe"
 * for a CupCake, as described in the coursework text:
 * 1 unit of batter, 1 unit of icing.
 *
 * @author marcoortolani
 */
public class CupCake extends AbstractCake {
    /**
     * Basic constructor to "force" use of correct type and number of
     * ingredients
     *
     * @param b                spoonful of batter
     * @param f                scoop of frosting, already flavoured
     * @param initialShelfLife the initial shelf life of the cake
     */
    public CupCake(Batter b, FlavouredFrosting f, int initialShelfLife) {
        super(new Ingredient[] { b, f }, initialShelfLife);
    }

    /**
     * Checks the recipe for the cake and returns the required
     * amount of batter
     * 
     * @return the required amount of batter, according to the recipe for the cake
     */
    @Override
    public int requiredBatterUnits() {
        return 1;
    }

    /**
     * Checks the recipe for the cake and returns the required
     * amount of flavour
     * 
     * @return the required amount of flavour, according to the recipe for the cake
     */
    @Override
    public int requiredFlavourUnits() {
        return 1;
    }

    /**
     * Checks the recipe for the cake and returns the required
     * amount of frosting
     * 
     * @return the required amount of frosting, according to the recipe for the cake
     */
    @Override
    public int requiredFrostingUnits() {
        return 1;
    }

    @Override
    public String toString() {
        return "cup" + super.toString();
    }

}
