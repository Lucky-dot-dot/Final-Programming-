/* **********************
 * CSC-20004 COURSEWORK *
 * Re-sit July 2025     *
 * **********************/
package uk.ac.keele.csc20004.sample;

import uk.ac.keele.csc20004.PastryChef;
import uk.ac.keele.csc20004.PastryShop;
import uk.ac.keele.csc20004.SimulationUtils;
import uk.ac.keele.csc20004.cakes.Cake;
import uk.ac.keele.csc20004.cakes.MissingIngredientException;

/**
 * A sample implementation of a PastryChef.
 * Note that this class doeas not necessarily meet the requirements of the coursework.
 * It is only provided as an exaple of use of the basic methods
 * 
 * @author marcoortolani
 */
public class SampleChef extends PastryChef {

    /**
     * Constructor for the SampleChef; just calls the superclass constructor
     * @param name the name of the chef
     * @param shop the shop where the chef works
     */
    public SampleChef(String name, PastryShop shop) {
        super(name, shop);
    }

    @Override
    public Cake makeCake() throws MissingIngredientException {
        int cakeType = SimulationUtils.randomInt(3);

        Cake cake = shop.bakeCake(cakeType);

        try {
            Thread.sleep(cake.getPreparationTime());
        } catch (InterruptedException e) {
            System.err.format("IOException: %s%n", e);
        }

        System.out.println("[" + super.name + "]: made " + cake);

        return cake;
    }

}
