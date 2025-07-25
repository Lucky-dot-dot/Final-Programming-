/* **********************
 * CSC-20004 COURSEWORK *
 * Re-sit July 2025     *
 * **********************/
package uk.ac.keele.csc20004.sample;

import java.util.ArrayList;
import java.util.List;

import uk.ac.keele.csc20004.*;
import uk.ac.keele.csc20004.cakes.*;

/**
 * A concrete implementation of a PastryShop.
 * Not that this implementation *does not* satisfy the requirements for either
 * tasks of the coursework and
 * it is only provided to show an example of calling the relevant methods of the
 * provided classes, and of
 * producing the output.
 * For instance, in this case, I did not explicitly implemented the "seller" and
 * I simulated it directly in the
 * main method of the shop; you may want to choose otherwise.
 *
 * You are free to reuse any code you like, or to change it altogether.
 * 
 * @see PastryShop
 * 
 * @author marcoortolani
 */
public class SamplePastryShop extends PastryShop {
    private Cake[] displayCounter;
    private int numOfCakes = 0;

    /**
     * A basic constructor initialising the 3 needed dispensers and the delivery
     * table (implemented here as a simple array: you may want to change this).
     * 
     * @param dispenserCapacity the capacity of the dispensers
     * @param counterCapacity   the capacity of the display counter (number of cakes)
     */
    public SamplePastryShop(int dispenserCapacity, int counterCapacity) {
        super(dispenserCapacity, counterCapacity);

        displayCounter = new Cake[SimulationUtils.MAX_DISPLAYCOUNTER_SIZE];
        for (int i = 0; i < displayCounter.length; i++) {
            displayCounter[i] = null;
        }

    }

    @Override
    public int getNumcakesOnCounter() {
        return numOfCakes;
    }

    /**
     * Example of implementation of the method that accesses the delivery table.
     * In this implementation, the delivery table is an array, so I iterate to find
     * an empty spot, and store cake there. If no spot can be found, the cake is
     * simply
     * dropped!
     * Note that this is nor optimal, nor does it satisfy the coursework
     * requirements, so you need to provide your own implementation, depending on
     * how
     * you choose to implement the display counter.
     *
     * @param cake the Cake just produced, to be set on the delivery table before
     *             selling
     */
    @Override
    public void setCakeOnCounter(Cake cake) {
        if (numOfCakes == displayCounter.length) {
            // counter is full
            throw new IllegalStateException("Counter is full");
        }

        // look for an empty spot on the counter
        for (int i = 0; i < displayCounter.length; i++) {
            if (displayCounter[i] == null) {
                displayCounter[i] = cake;
                numOfCakes++;
                break;
            }
        }
    }

    /**
     * Gets one cake from the table and return it, redy to be sold.
     * Note that the cake is retrieved in no particular order; you may need to
     * change that!
     * 
     * @return the cake to be sold
     */
    @Override
    public Cake getCakeFromCounter() {
        // get the next cake to be sold from the coutner display
        for (int i = 0; i < displayCounter.length; i++) {
            if (displayCounter[i] != null) {
                Cake cake = displayCounter[i];
                displayCounter[i] = null;
                numOfCakes--;
                return cake;
            }
        }

        // if we get here, then the counter is empty
        return null;
    }

    @Override
    public Cake bakeCake(int cakeType) throws MissingIngredientException {
        if (cakeType == PastryShop.RED_VELVET_CAKE) {
            // Red Velvet
            Batter batter = batterDispenser.dispenseIngredient();
            Flavour flavour = flavourDispenser.dispenseIngredient();
            Frosting frosting = frostingDispenser.dispenseIngredient();

            FlavouredBatter flavouredBatter = new FlavouredBatter(batter, flavour);

            return new RedVelvetCake(flavouredBatter, frosting, SimulationUtils.CAKE_SHELF_LIFE);
        } else if (cakeType == PastryShop.VICTORIA_SPONGE_CAKE) {
            // Victoria Sponge
            Batter batter = batterDispenser.dispenseIngredient();
            Frosting frosting = frostingDispenser.dispenseIngredient();

            return new VictoriaSpongeCake(batter, frosting, SimulationUtils.CAKE_SHELF_LIFE);
        } else {
            // Cupcake (PastryShop.CUP_CAKE)
            Batter batter = batterDispenser.dispenseIngredient();
            Flavour flavour = flavourDispenser.dispenseIngredient();
            Frosting frosting = frostingDispenser.dispenseIngredient();

            FlavouredFrosting flavouredFrosting = new FlavouredFrosting(frosting, flavour);

            return new CupCake(batter, flavouredFrosting, SimulationUtils.CAKE_SHELF_LIFE);
        }
    }

    @Override
    public List<Cake> getStaleCakes() {
        ArrayList<Cake> staleCakes = new ArrayList<>();

        for (int i = 0; i < displayCounter.length; i++) {
            Cake cake = displayCounter[i];
            if (cake != null) {
                cake.decreaseShelfLife();
                if (cake.getShelfLife() == 0) {
                    staleCakes.add(cake);
                    displayCounter[i] = null;
                    numOfCakes--;
                }
            }
        }

        return staleCakes;
    }

    /*
     * *****
     * MAIN
     *
     * Just a sample code to show that the behaviour of all classes and methods is
     * as expected.
     * I create Chefs of different types to simulate the production of the different
     * types of cakes.
     * Then I added an infinite loop that:
     * - selects a random chef to produce a cake
     * - sets the cake on the delivery table
     * - randomly simulates a sale
     * - every once in a while prints out the summary about the chefs' earnings
     *
     * *****
     */
    public static void main(String[] args) {
        PastryShop shop = new SamplePastryShop(SimulationUtils.DISPENSER_CAPACITY,
                SimulationUtils.MAX_DISPLAYCOUNTER_SIZE);

        PastryChef[] chefs = new PastryChef[SimulationUtils.NUM_CHEFS];
        for (int i = 0; i < chefs.length; i++) {
            chefs[i] = new SampleChef("Chef" + i, shop);
        }

        ShopManager manager = new SampleShopManager(shop);

        ShopAssistant assistant = new SampleShopAssistant(shop);

        // Let's start the simulation. Note that this is a very simple simulation
        // Please customise it as needed for your own coursework.
        // The simulation will go on until one of the dispensers is empty
        do {
            // compute how many free spots are on the counter
            int freeSpots = SimulationUtils.MAX_DISPLAYCOUNTER_SIZE - shop.getNumcakesOnCounter();
            
            int numChefs = Integer.min(freeSpots, chefs.length);
            // Each chef makes a cake (up to the number of free spots on the counter)
            for (int i = 0; i < numChefs; i++) {
                PastryChef chef = chefs[i];

                try {
                    // make a cake
                    Cake cake = chef.makeCake();
                    // set cake on table
                    shop.setCakeOnCounter(cake);
                } catch (MissingIngredientException e) {
                    /*
                     * if we get here and this message is printed, then something was seriously
                     * flawed when handling/refilling dispensers
                     */
                    System.out.println("[" + chef.toString() + "]: cannot make cake. " + e.getMessage());
                    break;
                }
            }

            // now the shop manager will attempt to sell a random number of cakes
            // in this example: between 1 and the number of cakes on the counter
            int cakesSold = SimulationUtils.randomInt(shop.getNumcakesOnCounter() - 1) + 1;
            for (int i = 0; i < cakesSold; i++) {
                manager.sellCake();
            }

            // every once in a while, the shop assistant will clean the counter
            if (SimulationUtils.randomBoolean()) {
                assistant.cleanCounter();
            }
        } while (!shop.isBatterDispenserEmpty() && !shop.isFlavourDispenserEmpty() && !shop.isFrostingDispenserEmpty());


        // if there are still cakes left on counter, sell them all
        System.out.println("Selling remaining cakes");
        while (shop.getNumcakesOnCounter() > 0) {
            manager.sellCake();
        }
        // print the final status
        // shop.printDispenserStatus();
        // shop.printCounterStatus();

        // manager.printStatus();
        // assistant.printStatus();

        SimulationUtils.printStats(shop, manager, assistant);

    }
}
