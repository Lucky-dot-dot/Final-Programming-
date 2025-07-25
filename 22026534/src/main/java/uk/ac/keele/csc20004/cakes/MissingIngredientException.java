/* **********************
 * CSC-20004 COURSEWORK *
 * Re-sit July 2025     *
 * **********************/
package uk.ac.keele.csc20004.cakes;

/**
 * A specialised Exception to signal that some ingredient could not be retrieved.
 * See any of the Toy*Dispenser classes and the ToyShop class for an example of how to use it
 *
 * @see uk.ac.keele.csc20004.BatterDispenser
 * @see uk.ac.keele.csc20004.FlavourDispenser
 * @see uk.ac.keele.csc20004.FrostingDispenser
 * @see uk.ac.keele.csc20004.sample.SamplePastryShop
 *
 * @author marcoortolani
 */
public class MissingIngredientException extends Exception {
    /**
     * Constructor for the MissingIngredientException.
     * @param message the message to be displayed
     */
    public MissingIngredientException(String message) {
        super(message);
    }
}
