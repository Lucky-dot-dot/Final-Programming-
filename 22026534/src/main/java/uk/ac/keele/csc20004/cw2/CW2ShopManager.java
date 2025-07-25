/* **********************
 * CSC-20004 COURSEWORK *
 * Re-sit July 2025     *
 * **********************/
package uk.ac.keele.csc20004.cw2;

import uk.ac.keele.csc20004.PastryShop;
import uk.ac.keele.csc20004.ShopManager;
import uk.ac.keele.csc20004.SimulationUtils;
import uk.ac.keele.csc20004.cakes.Cake;

/**
 * A concurrent implementation of a ShopManager that runs in its own thread.
 * The manager continuously sells cakes from the display counter until
 * the simulation ends.
 * 
 * @author 22026534
 */
public class CW2ShopManager extends ShopManager implements Runnable {

    private final CW2PastryShop cw2Shop;

    /**
     * Constructor for the concurrent shop manager.
     * 
     * @param shop the pastry shop managed by this manager
     */
    public CW2ShopManager(PastryShop shop) {
        super(shop);
        this.cw2Shop = (CW2PastryShop) shop;
    }

    /**
     * The main execution method for the manager thread.
     * Continuously sells cakes until the simulation ends.
     */
    @Override
    public void run() {
        try {
            while (cw2Shop.isSimulationRunning() || cw2Shop.getNumcakesOnCounter() > 0) {
                try {
                    // Get a cake from the counter (this may block if counter is empty)
                    Cake cake = cw2Shop.getCakeFromCounter();
                    
                    if (cake != null) {
                        // Sell the cake
                        sellCake(cake);
                        
                        // Random delay between sales
                        Thread.sleep(SimulationUtils.randomInt(200) + 100);
                    }
                    
                } catch (InterruptedException e) {
                    // Check if simulation is still running
                    if (!cw2Shop.isSimulationRunning()) {
                        break;
                    }
                    // Otherwise continue
                }
            }
        } catch (Exception e) {
            System.err.println("[" + this.getClass().getSimpleName() + "]: Error in manager thread: " + e.getMessage());
        }
        
        System.out.println("[" + this.getClass().getSimpleName() + "]: finished working");
    }

    /**
     * Sells a specific cake and updates internal counters.
     * This version takes a cake parameter for use when selling remaining cakes.
     * 
     * @param cake the cake to sell
     * @return the cake that was sold
     */
    public Cake sellCake(Cake cake) {
        // Update internal counters
        batter += cake.requiredBatterUnits();
        flavour += cake.requiredFlavourUnits();
        frosting += cake.requiredFrostingUnits();
        revenue += cake.getPrice();
        
        // Announce the sale
        System.out.println("[" + this.getClass().getSimpleName() + "]: sold " + cake);
        
        return cake;
    }

    /**
     * Sells the next available cake from the counter.
     * 
     * @return the cake that was sold
     */
    @Override
    public Cake sellCake() {
        Cake cake = shop.getCakeFromCounter();
        if (cake != null) {
            return sellCake(cake);
        }
        return null;
    }
}