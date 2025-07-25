/* **********************
 * CSC-20004 COURSEWORK *
 * Re-sit July 2025     *
 * **********************/
package uk.ac.keele.csc20004;

import uk.ac.keele.csc20004.cakes.Cake;
import uk.ac.keele.csc20004.cakes.MissingIngredientException;

/**
 * An abstract chef, characterised by a name and a reference to the PastryShop they belong to.
 * 
 * @author marcoortolani
 */
public abstract class PastryChef {
    protected final PastryShop shop;
    protected final String name;

    /**
     * Constructor to initialise the chef's name and their earnings to 0.
     *
     * @param name a String to be used to identify this chef (mainly for the output)
     * @param shop the PastryShop the chef belongs to
     */
    public PastryChef(String name, PastryShop shop) {
        this.name = name;
        this.shop = shop;
    }

    /**
     * Method to simulate the preparation of a Cake. The method is abstract because it is not
     * possible to make a "generic" cake, so you will need to instantiate for a specific type of cake
     * as described in the text.
     *
     * @return a new Cake, among the different available types
     * @throws MissingIngredientException exception signalling that some necessary ingredient could not be retrieved
     */
    public abstract Cake makeCake() throws MissingIngredientException;

    @Override
    public String toString() {
        return name;
    }

}
