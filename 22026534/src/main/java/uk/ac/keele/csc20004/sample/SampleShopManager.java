/* **********************
 * CSC-20004 COURSEWORK *
 * Re-sit July 2025     *
 * **********************/
package uk.ac.keele.csc20004.sample;

import uk.ac.keele.csc20004.PastryShop;
import uk.ac.keele.csc20004.ShopManager;
import uk.ac.keele.csc20004.cakes.Cake;

/**
 * A sample implementation of a ShopManager.
 * It extends the ShopManager class and overrides the sellCake method to print a message when a cake is sold.
 * 
 * @see ShopManager
 * 
 * @author marcoortolani
 */
public class SampleShopManager extends ShopManager {

    /**
     * Constructor for the SampleShopManager.
     * @param shop the shop managed by this manager
     */
    public SampleShopManager(PastryShop shop) {
        super(shop);
    }

    /**
     * Simulates sale of one cake. Note that the method takes no parameter, as the
     * Cake to be sold will be determined by the strategy used to handle the display
     * counter.
     * 
     * @return the cake sold
     */
    @Override
    public Cake sellCake() {
        Cake cake = super.sellCake();

        System.out.println("[" + this.getClass().getSimpleName() + "]: sold "  + cake);
        return cake;
    }

}
