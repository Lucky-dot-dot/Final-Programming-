/* **********************
 * CSC-20004 COURSEWORK *
 * Re-sit July 2025     *
 * **********************/
package uk.ac.keele.csc20004;

import java.util.concurrent.ThreadLocalRandom;

/**
 * This class is provided for your convenience to store the main project
 * parameters all in one place.
 * The class also provides some utility methods to generate random values
 * and for pretty printing.
 * 
 * @author marcoortolani
 */
public class SimulationUtils {
    /**
     * number of chefs in the game
     */
    public static final int NUM_CHEFS = 3;

    /**
     * size of the delivery table
     */
    public static final int MAX_DISPLAYCOUNTER_SIZE = 20;

    /**
     * default capacity of ingredients dispensers
     */
    public static final int DISPENSER_CAPACITY = 20;

    /**
     * the time unit to use as a basis to compute the
     * preparation time for a cake (in ms)
     */
    public static final int BASE_CAKE_PREP_TIME = 100;

    /**
     * the time unit to use as a basis to simulate the
     * delay in getting an ingredient from a dispenser,
     * and to refill a dispenser
     */
    public static final int BASE_DISPENSE_TIME = 50;

    /**
     * the time unit to use as a basis for the delay to 
     * simulate the display counter cleaning time
     */
    public static final int COUNTER_CLEANING_TIME = 100;

    /**
     * the number of iterations a cake can stay on the 
     * display counter before it is considered stale
     */
    public static final int CAKE_SHELF_LIFE = 5;

    /**
     * The unit cost of batter.
     * This is used to compute the cost of a cake, 
     * and to simulate the time for the dispenser to dispense the ingredient.
     */
    public static final float BATTER_COST = 3.0f;

    /**
     * The unit cost of filling.
     * This is used to compute the cost of a cake, 
     * and to simulate the time for the dispenser to dispense the ingredient.
     */
    public static final float FLAVOUR_COST = 2.0f;

    /**
     * The unit cost of icing.
     * This is used to compute the cost of a cake, 
     * and to simulate the time for the dispenser to dispense the ingredient.
     */
    public static final float FROSTING_COST = 1.0f;

        /**
     * Generate a random integer value between 0 (inclusive) and the specified bound
     * 
     * @param bound the upper bound (exclusive)
     * @return a random integer value between 0 (inclusive) and the specified bound
     */
    public static int randomInt(int bound) {
        return ThreadLocalRandom.current().nextInt(bound);
    }

        /**
     * Generate a random boolean value.
     * 
     * @return a random boolean value.
     */
    public static boolean randomBoolean() {
        return ThreadLocalRandom.current().nextBoolean();
    }

    /**
     * Pretty print the statistics of the simulation.
     * 
     * @param shop the pastry shop
     * @param manager the shop manager
     * @param assistant the shop assistant
     */
    public static void printStats(PastryShop shop, ShopManager manager, ShopAssistant assistant) {
        int width = 10;

        System.out.println("*".repeat(50));
        System.out.print(String.format("%-10s"," "));
        System.out.print(String.format("%" + width + "s","Unused"));
        System.out.print(String.format("%" + width + "s","Sold"));
        System.out.print(String.format("%" + width + "s","Cleaned"));
        System.out.print(String.format("%" + width + "s","Initial"));
        System.out.println();

        // batter
        System.out.print(String.format("%-10s","Batter: "));
        System.out.print(String.format("%" + width + "d", shop.getBatterDispenserSize()));
        System.out.print(String.format("%" + width + "d", manager.processedBatter()));
        System.out.print(String.format("%" + width + "d", assistant.processedBatter()));
        System.out.print(String.format("%" + width + "d", SimulationUtils.DISPENSER_CAPACITY));
        System.out.println();

        // flavour
        System.out.print(String.format("%-10s","Flavour: "));
        System.out.print(String.format("%" + width + "d", shop.getFlavourDispenserSize()));
        System.out.print(String.format("%" + width + "d", manager.processedFlavour()));
        System.out.print(String.format("%" + width + "d", assistant.processedFlavour()));
        System.out.print(String.format("%" + width + "d", SimulationUtils.DISPENSER_CAPACITY));
        System.out.println();
        
        // frosting
        System.out.print(String.format("%-10s","Frosting: "));
        System.out.print(String.format("%" + width + "d", shop.getFrostingDispenserSize()));
        System.out.print(String.format("%" + width + "d", manager.processedFrosting()));
        System.out.print(String.format("%" + width + "d", assistant.processedFrosting()));
        System.out.print(String.format("%" + width + "d", SimulationUtils.DISPENSER_CAPACITY));
        System.out.println();

        System.out.println("*".repeat(50));
    }
}
