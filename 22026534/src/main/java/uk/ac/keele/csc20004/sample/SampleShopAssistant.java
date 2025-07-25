/* **********************
 * CSC-20004 COURSEWORK *
 * Re-sit July 2025     *
 * **********************/
package uk.ac.keele.csc20004.sample;

import java.util.List;

import uk.ac.keele.csc20004.PastryShop;
import uk.ac.keele.csc20004.ShopAssistant;
import uk.ac.keele.csc20004.SimulationUtils;
import uk.ac.keele.csc20004.cakes.Cake;

/**
 * A sample concrete implementation of a ShopAssistant.
 * 
 * @author marcoortolani
 */
public class SampleShopAssistant extends ShopAssistant {

    /**
     * Constructor for the SampleShopAssistant.
     * 
     * @param shop the shop where the assistant works
     */
    public SampleShopAssistant(PastryShop shop) {
        super(shop);
    }

    /**
     * Simulates cleaning of the display counter.
     * The assistant will go through all the cakes on the display counter, and
     * update their shelf life. If a cake's shelf life has reached 0, it will be
     * removed from the display counter. The assistant will also update the
     * internal counters for batter, flavour, and frosting to reflect the
     * ingredients used in the cakes that were removed from the display counter.
     * 
     *  @return the list of cakes that were removed from the display counter
     */
    @Override
    public List<Cake> cleanCounter() {
        List<Cake> staleCakes = super.cleanCounter();
        // System.out.println("[" + this.getClass().getSimpleName() + "]: removed " + staleCakes.size()
        //         + " stale cakes from the counter");

        try {
            Thread.sleep(SimulationUtils.COUNTER_CLEANING_TIME);
        } catch (InterruptedException e) {
            System.err.format("IOException: %s%n", e);
        }
        
        for (Cake cake : staleCakes) {
            System.out.println("[" + this.getClass().getSimpleName() + "]: removed stale " + cake);
        }        
        return staleCakes;
    }
}
