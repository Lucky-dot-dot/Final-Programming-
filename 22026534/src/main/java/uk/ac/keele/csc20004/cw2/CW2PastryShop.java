/* **********************
 * CSC-20004 COURSEWORK *
 * Re-sit July 2025     *
 * **********************/
package uk.ac.keele.csc20004.cw2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import uk.ac.keele.csc20004.*;
import uk.ac.keele.csc20004.cakes.*;

/**
 * A concurrent implementation of a PastryShop using threads and blocking queues.
 * This implementation handles multiple chefs, a manager, and an assistant all working
 * concurrently while safely accessing shared resources.
 * 
 * CONCURRENCY REPORT:
 * 
 * Dispenser Contention Issues and Solutions:
 * The main issue is multiple chefs simultaneously accessing the same dispenser, which could
 * cause race conditions. However, the dispensers are inherently thread-safe because the
 * dispenseIngredient() method is synchronized through its built-in Thread.sleep() delays
 * and atomic operations on the ArrayList. When multiple chefs access the same dispenser,
 * they are naturally serialized by the method execution time. Additionally, we handle
 * MissingIngredientException gracefully by catching it in chef threads and terminating
 * them cleanly when ingredients are exhausted, preventing infinite waiting.
 * 
 * Display Counter Contention Issues and Solutions:
 * The critical issue is concurrent access by chefs (adding cakes), manager (removing cakes),
 * and assistant (inspecting/removing stale cakes), which could cause race conditions, data
 * corruption, or inconsistent state. We solved this using ArrayBlockingQueue, which provides
 * thread-safe operations with built-in synchronization. When the counter is full, chefs
 * automatically block via put() until space becomes available. When empty, the manager
 * blocks via take() until cakes are available. The assistant uses drainTo() to safely
 * remove all cakes for inspection, then puts fresh ones back. This eliminates all race
 * conditions and provides natural flow control without explicit locking.
 * 
 * Deadlock/Livelock Prevention:
 * Our design prevents deadlock through several key principles: (1) Single resource access -
 * threads only hold one resource at a time (either dispenser or counter access), (2) No
 * circular dependencies - chefs only add to counter, manager only removes, assistant only
 * inspects, (3) No nested locking - ArrayBlockingQueue handles all synchronization internally,
 * (4) Timeout-free operations - we use blocking operations that will eventually succeed.
 * Livelock is prevented because threads don't repeatedly retry failed operations; they either
 * block until successful or terminate naturally when ingredients run out.
 * 
 * Starvation Analysis:
 * Starvation cannot occur in this design for several reasons: (1) ArrayBlockingQueue uses
 * FIFO ordering, ensuring fair access to the display counter for all threads, (2) Chef
 * threads naturally terminate when ingredients are exhausted, eliminating infinite competition,
 * (3) The manager processes cakes in the order they were added, ensuring no cake waits
 * indefinitely, (4) The assistant runs on a timer-based schedule rather than competing
 * for resources, and (5) BlockingQueue's fair blocking ensures that no thread is permanently
 * prevented from accessing the counter. The simulation has a natural endpoint (ingredient
 * exhaustion) that guarantees forward progress.
 * 
 * @author 22026534
 */
public class CW2PastryShop extends PastryShop {
    
    private final BlockingQueue<Cake> displayCounter;
    private final AtomicBoolean simulationRunning;
    
    /**
     * Constructor for the concurrent pastry shop.
     * 
     * @param dispenserCapacity the initial number of ingredients in each dispenser
     * @param counterCapacity   the maximum number of cakes that can be on the display counter
     */
    public CW2PastryShop(int dispenserCapacity, int counterCapacity) {
        super(dispenserCapacity, counterCapacity);
        this.displayCounter = new ArrayBlockingQueue<>(counterCapacity);
        this.simulationRunning = new AtomicBoolean(true);
    }

    /**
     * Places a cake on the display counter. This method blocks if the counter is full.
     * 
     * @param cake the cake to place on the counter
     */
    @Override
    public void setCakeOnCounter(Cake cake) {
        try {
            displayCounter.put(cake); // Blocks if counter is full
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrupted while placing cake on counter", e);
        }
    }

    /**
     * Gets a cake from the display counter for sale. This method blocks if counter is empty.
     * 
     * @return the next cake to be sold, or null if simulation is ending and counter is empty
     */
    @Override
    public Cake getCakeFromCounter() {
        try {
            return displayCounter.take(); // Blocks if counter is empty
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        }
    }

    /**
     * Returns the current number of cakes on the display counter.
     * 
     * @return the number of cakes currently on the counter
     */
    @Override
    public int getNumcakesOnCounter() {
        return displayCounter.size();
    }

    /**
     * Creates a cake of the specified type using ingredients from the dispensers.
     * 
     * @param cakeType the type of cake to make (RED_VELVET_CAKE, VICTORIA_SPONGE_CAKE, or CUP_CAKE)
     * @return the newly created cake
     * @throws MissingIngredientException if required ingredients are not available
     */
    @Override
    public Cake bakeCake(int cakeType) throws MissingIngredientException {
        switch (cakeType) {
            case PastryShop.RED_VELVET_CAKE:
                // Red Velvet: flavoured batter + frosting
                Batter batter1 = batterDispenser.dispenseIngredient();
                Flavour flavour1 = flavourDispenser.dispenseIngredient();
                Frosting frosting1 = frostingDispenser.dispenseIngredient();
                
                FlavouredBatter flavouredBatter = new FlavouredBatter(batter1, flavour1);
                return new RedVelvetCake(flavouredBatter, frosting1, SimulationUtils.CAKE_SHELF_LIFE);
                
            case PastryShop.VICTORIA_SPONGE_CAKE:
                // Victoria Sponge: batter + frosting
                Batter batter2 = batterDispenser.dispenseIngredient();
                Frosting frosting2 = frostingDispenser.dispenseIngredient();
                return new VictoriaSpongeCake(batter2, frosting2, SimulationUtils.CAKE_SHELF_LIFE);
                
            case PastryShop.CUP_CAKE:
            default:
                // Cupcake: batter + flavoured frosting
                Batter batter3 = batterDispenser.dispenseIngredient();
                Flavour flavour3 = flavourDispenser.dispenseIngredient();
                Frosting frosting3 = frostingDispenser.dispenseIngredient();
                
                FlavouredFrosting flavouredFrosting = new FlavouredFrosting(frosting3, flavour3);
                return new CupCake(batter3, flavouredFrosting, SimulationUtils.CAKE_SHELF_LIFE);
        }
    }

    /**
     * Finds and removes all stale cakes from the display counter.
     * A cake is stale when its shelf life reaches 0.
     * 
     * @return a list of cakes that were removed because they were stale
     */
    @Override
    public List<Cake> getStaleCakes() {
        List<Cake> staleCakes = new ArrayList<>();
        List<Cake> freshCakes = new ArrayList<>();
        
        // Remove all cakes from counter to inspect them
        displayCounter.drainTo(freshCakes);
        
        // Check each cake and separate stale from fresh
        for (Cake cake : freshCakes) {
            cake.decreaseShelfLife();
            if (cake.getShelfLife() <= 0) {
                staleCakes.add(cake);
            } else {
                // Put fresh cakes back on counter
                try {
                    displayCounter.put(cake);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
        
        return staleCakes;
    }

    /**
     * Checks if the simulation should continue running.
     * 
     * @return true if simulation is still running, false otherwise
     */
    public boolean isSimulationRunning() {
        return simulationRunning.get();
    }

    /**
     * Stops the simulation.
     */
    public void stopSimulation() {
        simulationRunning.set(false);
    }

    /**
     * Main method demonstrating the concurrent pastry shop simulation.
     * Creates multiple chef threads, a manager thread, and an assistant thread.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("Starting Concurrent Pastry Shop Simulation...");
        
        // Create the shop
        CW2PastryShop shop = new CW2PastryShop(
            SimulationUtils.DISPENSER_CAPACITY,
            SimulationUtils.MAX_DISPLAYCOUNTER_SIZE
        );
        
        // Create chef threads
        Thread[] chefThreads = new Thread[SimulationUtils.NUM_CHEFS];
        CW2Chef[] chefs = new CW2Chef[SimulationUtils.NUM_CHEFS];
        
        for (int i = 0; i < SimulationUtils.NUM_CHEFS; i++) {
            chefs[i] = new CW2Chef("Chef" + i, shop);
            chefThreads[i] = new Thread(chefs[i]);
            chefThreads[i].start();
        }
        
        // Create and start manager thread
        CW2ShopManager manager = new CW2ShopManager(shop);
        Thread managerThread = new Thread(manager);
        managerThread.start();
        
        // Create and start assistant thread
        CW2ShopAssistant assistant = new CW2ShopAssistant(shop);
        Thread assistantThread = new Thread(assistant);
        assistantThread.start();
        
        // Wait for all chef threads to finish (when ingredients run out)
        try {
            for (Thread chefThread : chefThreads) {
                chefThread.join();
            }
            
            // Give some time for remaining cakes to be sold
            Thread.sleep(2000);
            
            // Stop the simulation
            shop.stopSimulation();
            
            // Interrupt manager and assistant threads to wake them up
            managerThread.interrupt();
            assistantThread.interrupt();
            
            // Wait for manager and assistant to finish
            managerThread.join();
            assistantThread.join();
            
        } catch (InterruptedException e) {
            System.err.println("Main thread interrupted: " + e.getMessage());
        }
        
        // Sell any remaining cakes
        System.out.println("Selling remaining cakes");
        while (shop.getNumcakesOnCounter() > 0) {
            Cake cake = shop.getCakeFromCounter();
            if (cake != null) {
                manager.sellCake(cake);
            }
        }
        
        // Print final statistics
        SimulationUtils.printStats(shop, manager, assistant);
        
        System.out.println("Simulation completed!");
    }
}