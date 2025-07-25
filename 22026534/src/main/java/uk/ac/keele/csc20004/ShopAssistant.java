/* **********************
 * CSC-20004 COURSEWORK *
 * Re-sit July 2025     *
 * **********************/
package uk.ac.keele.csc20004;

import java.util.List;

import uk.ac.keele.csc20004.cakes.Cake;

/**
 * Abstract class representing a shop assistant.
 * The shop assistant is responsible for cleaning the display counter and
 * keeping track of the ingredients used in the cakes that are removed.
 * 
 * @author marcoortolani
 */
public abstract class ShopAssistant {
    protected final PastryShop shop;

    protected int batter;
    protected int flavour;
    protected int frosting;

    /**
     * Creates a new shop assistant.
     * 
     * @param shop the shop where the assistant works
     */
    public ShopAssistant(PastryShop shop) {
        this.shop = shop;
        batter = 0;
        flavour = 0;
        frosting = 0;
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
    public List<Cake> cleanCounter() {
        List<Cake> staleCakes = shop.getStaleCakes();
        for (Cake cake : staleCakes) {
            batter += cake.requiredBatterUnits();
            flavour += cake.requiredFlavourUnits();
            frosting += cake.requiredFrostingUnits();
        }

        return staleCakes;
    }

    /**
     * Returns the total units of batter in sold cakes.
     * 
     * @return the total units of batter in sold cakes
     */
    public int processedBatter() {
        return batter;
    }

    /**
     * Returns the total units of flavour in sold cakes.
     * 
     * @return the total units of flavour in sold cakes
     */
    public int processedFlavour() {
        return flavour;
    }

    /**
     * Returns the total units of frosting in sold cakes.
     * 
     * @return the total units of frosting in sold cakes
     */
    public int processedFrosting() {
        return frosting;
    }
}
