/* **********************
 * CSC-20004 COURSEWORK *
 * Re-sit July 2025     *
 * **********************/
package uk.ac.keele.csc20004.cw2;

import uk.ac.keele.csc20004.PastryChef;
import uk.ac.keele.csc20004.PastryShop;
import uk.ac.keele.csc20004.SimulationUtils;
import uk.ac.keele.csc20004.cakes.Cake;
import uk.ac.keele.csc20004.cakes.MissingIngredientException;

/**
 * A concurrent implementation of a PastryChef that runs in its own thread.
 * The chef continuously makes random cakes until ingredients run out or
 * the simulation is stopped.
 * 
 * @author 22026534
 */
public class CW2Chef extends PastryChef implements Runnable {

    private final CW2PastryShop cw2Shop;

    /**
     * Constructor for the concurrent chef.
     * 
     * @param name the name of the chef
     * @param shop the pastry shop where the chef works
     */
    public CW2Chef(String name, PastryShop shop) {
        super(name, shop);
        this.cw2Shop = (CW2PastryShop) shop;
    }

    /**
     * The main execution method for the chef thread.
     * Continuously makes cakes until ingredients run out or simulation stops.
     */
    @Override
    public void run() {
        try {
            while (cw2Shop.isSimulationRunning()) {
                try {
                    // Make a cake
                    Cake cake = makeCake();
                    
                    // Place it on the display counter (this may block if counter is full)
                    cw2Shop.setCakeOnCounter(cake);
                    
                    // Small random delay before making next cake
                    Thread.sleep(SimulationUtils.randomInt(100) + 50);
                    
                } catch (MissingIngredientException e) {
                    // Ingredients have run out - this chef stops working
                    System.out.println("[" + name + "]: cannot make cake. " + e.getMessage());
                    break;
                } catch (InterruptedException e) {
                    // Thread was interrupted - stop working
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("[" + name + "]: Error in chef thread: " + e.getMessage());
        }
        
        System.out.println("[" + name + "]: finished working");
    }

    /**
     * Makes a random type of cake.
     * 
     * @return a newly created cake
     * @throws MissingIngredientException if required ingredients are not available
     */
    @Override
    public Cake makeCake() throws MissingIngredientException {
        // Choose a random cake type
        int cakeType = SimulationUtils.randomInt(3);
        
        // Bake the cake using the shop's facilities
        Cake cake = shop.bakeCake(cakeType);
        
        // Simulate the time to prepare the cake
        try {
            Thread.sleep(cake.getPreparationTime());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrupted while preparing cake", e);
        }
        
        // Announce the cake is ready
        System.out.println("[" + name + "]: made " + cake);
        
        return cake;
    }
}