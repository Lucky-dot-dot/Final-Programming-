/* **********************
 * CSC-20004 COURSEWORK *
 * Re-sit July 2025     *
 * **********************/

package uk.ac.keele.csc20004.cakes;

/**
 * Interface for a generic cake.
 *
 * @author marcoortolani
 */
public interface Cake {

  /**
   * Returns the price of the cake, computed as the sum of the costs
   * of all of its ingredients.
   *
   * @return the price of the cake
   */
  public float getPrice();

  /**
   * Computes the time it takes to simulate the preparation of the cake, as
   * a function of the price
   *
   * @return the time (in ms) to prepare the cake
   */
  public int getPreparationTime();

  /**
   * Checks the recipe for the cake and returns the required
   * amount of batter
   * 
   * @return the required amount of batter, according to the recipe for the cake
   */
  public int requiredBatterUnits();

  /**
   * Checks the recipe for the cake and returns the required
   * amount of flavour
   * 
   * @return the required amount of flavour, according to the recipe for the cake
   */
  public int requiredFlavourUnits();

  /**
   * Checks the recipe for the cake and returns the required
   * amount of frosting
   * 
   * @return the required amount of frosting, according to the recipe for the cake
   */
  public int requiredFrostingUnits();

  /**
   * Returns the shelf life of the cake, i.e., the number of time units
   * it can stay on the display counter before it is considered stale.
   * 
   * @return the shelf life of the cake
   */
  public int getShelfLife();

  /**
   * Decreases the shelf life of the cake by one time unit.
   */
  public void decreaseShelfLife();
}