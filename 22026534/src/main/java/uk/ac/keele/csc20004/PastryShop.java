/* **********************
 * CSC-20004 COURSEWORK *
 * Re-sit July 2025     *
 * **********************/
package uk.ac.keele.csc20004;

import java.util.List;

import uk.ac.keele.csc20004.cakes.*;

/**
 * This is the main class that implements a pastry shop.
 * The class is abstract because it doesn't define all the data structures to
 * manage the objects, in partiular it does not implement a display
 * counter (this is part of the coursework).
 *
 * The only included data structures are the dispensers.
 *
 * @author marcoortolani
 */
public abstract class PastryShop {
    public static final int RED_VELVET_CAKE = 0;
    public static final int VICTORIA_SPONGE_CAKE = 1;
    public static final int CUP_CAKE = 2;

    protected final int dispenserCapacity;
    protected final int counterCapacity;

    protected final BatterDispenser batterDispenser;
    protected final FlavourDispenser flavourDispenser;
    protected final FrostingDispenser frostingDispenser;

    /**
     * A basic constructor initialising the 3 needed dispensers.
     * The capacity of the dispensers is set to the given paramenter
     * (same value for all dispensers).
     * 
     * @param dispenserCapacity the capacity of the dispensers
     * @param counterCapacity   the capacity of the display counter (number of cakes)
     */
    protected PastryShop(int dispenserCapacity, int counterCapacity) {
        batterDispenser = new BatterDispenser(dispenserCapacity);
        flavourDispenser = new FlavourDispenser(dispenserCapacity);
        frostingDispenser = new FrostingDispenser(dispenserCapacity);

        this.dispenserCapacity = dispenserCapacity;
        this.counterCapacity = counterCapacity;
    }

    /**
     * Method to simulate the preparation of a Cake. The method is abstract because it is not
     * possible to make a "generic" cake, so you will need to instantiate for a specific type of cake
     * as described in the text.
     * 
     * @param cakeType the type of cake to be made (see the constants in the class)
     *
     * @return a new Cake, among the different available types
     * @throws MissingIngredientException exception signalling that some necessary ingredient could not be retrieved
     */
    public abstract Cake bakeCake(int cakeType) throws MissingIngredientException;

    /**
     * Checks the state of the dispenser.
     *
     * @return true if dispenser is empty
     */
    public boolean isBatterDispenserEmpty() {
        return batterDispenser.isEmpty();
    }

    /**
     * Checks the state of the dispenser.
     *
     * @return true if dispenser is empty
     */
    public boolean isFlavourDispenserEmpty() {
        return flavourDispenser.isEmpty();
    }

    /**
     * Checks the state of the dispenser.
     *
     * @return true if dispenser is empty
     */
    public boolean isFrostingDispenserEmpty() {
        return frostingDispenser.isEmpty();
    }

    /**
     * Returns the number of ingredients left in the batter dispenser.
     * 
     * @return the number of ingredients left in the batter dispenser
     */
    public int getBatterDispenserSize() {
        return batterDispenser.getSize();
    }

    /**
     * Returns the number of ingredients left in the flavour dispenser.
     * 
     * @return the number of ingredients left in the flavour dispenser
     */
    public int getFlavourDispenserSize() {
        return flavourDispenser.getSize();
    }

    /**
     * Returns the number of ingredients left in the frosting dispenser.
     * 
     * @return the number of ingredients left in the frosting dispenser
     */
    public int getFrostingDispenserSize() {
        return frostingDispenser.getSize();
    }

    /**
     * Simulates positioning the cake on the display counter.
     * You will need to provide the data structure to implement the counter.
     *
     * @param cake the Cake just produced, to be set on the delivery table before
     *             selling
     */
    public abstract void setCakeOnCounter(Cake cake);

    /**
     * Get the number of cakes on the display counter.
     * @return the number of cakes on the display counter
     */
    public abstract int getNumcakesOnCounter();

    /**
     * Get the next cake to sell. Note that the method takes no parameter, as the
     * Cake to be sold will be determined by the strategy used to handle the display counter.
     *
     * @return the cake to be sold
     */
    public abstract Cake getCakeFromCounter();

    /**
     * Returns the cakes that are considered stale.
     * 
     * @return a List of stale cakes
     */
    public abstract List<Cake> getStaleCakes();
}
