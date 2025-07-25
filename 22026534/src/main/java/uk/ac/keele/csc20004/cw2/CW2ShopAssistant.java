/* **********************
 * CSC-20004 COURSEWORK *
 * Re-sit July 2025     *
 * **********************/
package uk.ac.keele.csc20004.cw2;

import java.util.List;

import uk.ac.keele.csc20004.PastryShop;
import uk.ac.keele.csc20004.ShopAssistant;
import uk.ac.keele.csc20004.SimulationUtils;
import uk.ac.keele.csc20004.cakes.Cake;

/**
 * A concurrent implementation of a ShopAssistant that runs in its own thread.
 * The assistant periodically cleans the display counter, removing stale cakes
 * and updating their shelf life.
 * 
 * @author 22026534
 */
public class CW2ShopAssistant extends ShopAssistant implements Runnable {

    private final CW2PastryShop cw2Shop;
    private static final int CLEANING_INTERVAL = 1000; // Clean every 1 second

    /**
     * Constructor for the concurrent shop assistant.
     * 
     * @param shop the pastry shop where the assistant works
     */
    public CW2ShopAssistant(PastryShop shop) {
        super(shop);
        this.cw2Shop = (CW2PastryShop) shop;
    }

    /**
     * The main execution method for the assistant thread.
     * Periodically cleans the display counter until the simulation ends.
     */
    @Override
    public void run() {
        try {
            while (cw2Shop.isSimulationRunning()) {
                try {
                    // Wait before next cleaning cycle
                    Thread.sleep(CLEANING_INTERVAL);
                    
                    // Clean the counter
                    List<Cake> removedCakes = cleanCounter();
                    
                    // Only announce if cakes were actually removed
                    if (!removedCakes.isEmpty()) {
                        for (Cake cake : removedCakes) {
                            System.out.println("[" + this.getClass().getSimpleName() + "]: removed stale " + cake);
                        }
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
            System.err.println("[" + this.getClass().getSimpleName() + "]: Error in assistant thread: " + e.getMessage());
        }
        
        System.out.println("[" + this.getClass().getSimpleName() + "]: finished working");
    }

    /**
     * Cleans the display counter by removing stale cakes and updating shelf life.
     * 
     * @return the list of cakes that were removed from the display counter
     */
    @Override
    public List<Cake> cleanCounter() {
        List<Cake> staleCakes = super.cleanCounter();
        
        // Simulate cleaning time
        try {
            Thread.sleep(SimulationUtils.COUNTER_CLEANING_TIME);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            // Don't throw exception, just return what we have
        }
        
        return staleCakes;
    }
}