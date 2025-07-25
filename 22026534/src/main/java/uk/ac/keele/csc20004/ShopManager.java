/* **********************
 * CSC-20004 COURSEWORK *
 * Re-sit July 2025     *
 * **********************/
package uk.ac.keele.csc20004;

import uk.ac.keele.csc20004.cakes.Cake;

/**
 * Abstract class that represents a shop manager. The shop manager is responsible
 * for selling cakes and keeping track of the ingredients used to make them.
 * 
 * The class is abstract because you should implement your own version of a
 * manager (even if only to print a message when a cake is sold). 
 * 
 * @see uk.ac.keele.csc20004.sample.SampleShopManager
 * 
 * @author marcoortolani
 */
public abstract class ShopManager {
    protected final PastryShop shop;

    protected int batter;
    protected int flavour;
    protected int frosting;

    protected float revenue;

    /**
     * Creates a new shop manager.
     * 
     * @param shop the shop managed by this manager
     */
    public ShopManager(PastryShop shop) {
        this.shop = shop;
        batter = 0;
        flavour = 0;
        frosting = 0;

        revenue = 0.0f;
    }

    /**
     * Simulates sale of one cake. Note that the method takes no parameter, as the
     * Cake to be sold will be determined by the strategy used to handle the display
     * counter.
     * 
     * @return the cake sold
     */
    public Cake sellCake() {
        Cake cake = shop.getCakeFromCounter();
        batter += cake.requiredBatterUnits();
        flavour += cake.requiredFlavourUnits();
        frosting += cake.requiredFrostingUnits();
        revenue += cake.getPrice();

        return cake;
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
