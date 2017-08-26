package cs3500.hw04;

import cs3500.hw02.FreecellModel;

/**
 * Created by ChrisRisley on 5/28/17.
 */

/**
 * Factory class for the FreecellModel. It has one method that
 * creates a new freecell model depending on what type of Game you want to play.
 * This gametype is defined as either a SINGLEMOVE or a MULTIMOVE.
 */
public class FreecellModelCreator {

  /**
   * This enum represents the possible gamemode or gametypes.
   * There are currently two implements gametypes: SINGLEMOVE and MULTIMOVE.
   */
  public enum GameType {
    SINGLEMOVE, MULTIMOVE
  }

  /**
   * Creates a FreecellModel Object that's determined by the given GameType value.
   *
   * @param type Represents the type of Freecell model Object that the method will create.
   * @return The FreecellModel Object type (Multi or Single)
   * @throws IllegalArgumentException when you input a null GameType
   */
  public static FreecellModel create(GameType type) {
    if (type == null) {
      throw new IllegalArgumentException("Must enter valid type");
    }
    if (type == GameType.MULTIMOVE) {
      return new FreecellModelMultiMove();
    } else {
      return new FreecellModel();
    }
  }

}
