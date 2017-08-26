
package cs3500.hw03;

import java.util.List;

import cs3500.hw02.FreecellOperations;

/**
 * Interface for the FreecellController. It is parametrized over the card type.
 * @param <Card> Represents the Card Class
 */
public interface IFreecellController<Card> {

  /**
   * Starts a new game of Freecell using the provided model,
   * number of cascade and open piles and the provided deck.
   *
   * @param deck        Represents the given deck.
   * @param model       Represents the given model.
   * @param numCascades Represents the given number of cascades.
   * @param numOpens    Represents the given number of open piles.
   * @param shuffle     Determines if the deck should be shuffled (boolean)
   */
  void playGame(List<Card> deck, FreecellOperations<Card> model, int numCascades,
                int numOpens, boolean shuffle);


}
