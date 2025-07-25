/* **********************
 * CSC-20004 COURSEWORK *
 * Re-sit July 2025     *
 * **********************/
package uk.ac.keele.csc20004.cakes;

/**
 * A concrete implementation of a cake, implementing the "recipe"
 * for a CakeSlice, as described in the coursework text:
 * 2 units of batter, 1 filling, 2 units of icing.
 *
 * @author marcoortolani
 */
public class VictoriaSpongeCake extends AbstractCake {

    /**
     * Basic constructor to "force" use of correct type and number of
     * ingredients
     *
     * @param b spoonful of batter
     * @param f the frosting
     * @param initialShelfLife the initial shelf life of the cake
     */
    public VictoriaSpongeCake(Batter b, Frosting f, int initialShelfLife) {
        super(new Ingredient[] {b,f}, initialShelfLife);
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
        return 0;
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
        return "victoria sponge " + super.toString();
    }
}
