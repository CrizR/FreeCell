package cs3500.hw03;

import org.junit.Test;

import cs3500.hw02.FreecellModel;
import cs3500.hw02.PileType;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by ChrisRisley on 5/23/17.
 */

/**
 * Class for testing the Command Class.
 */
public class TestCommand {
  Command x = new Command();
  FreecellModel model = new FreecellModel();

  String movedCard = "F1:" + "\n" +
          "F2:" + "\n" +
          "F3:" + "\n" +
          "F4:" + "\n" +
          "O1:" + " 10♠" + "\n" +
          "O2:" + "\n" +
          "O3:" + "\n" +
          "O4:" + "\n" +
          "C1: A♣, 5♣, 9♣, K♣, 4♦, 8♦, Q♦, 3♥, 7♥, J♥, 2♠, 6♠" + "\n" +
          "C2: 2♣, 6♣, 10♣, A♦, 5♦, 9♦, K♦, 4♥, 8♥, Q♥, 3♠, 7♠, J♠" + "\n" +
          "C3: 3♣, 7♣, J♣, 2♦, 6♦, 10♦, A♥, 5♥, 9♥, K♥, 4♠, 8♠, Q♠" + "\n" +
          "C4: 4♣, 8♣, Q♣, 3♦, 7♦, J♦, 2♥, 6♥, 10♥, A♠, 5♠, 9♠, K♠";

  @Test
  //tests to see if the method size works
  public void testSizeandSetMethods() {
    x.setSource(PileType.FOUNDATION)
            .setSourceNum(2)
            .setCardIndex(2)
            .setDest(PileType.CASCADE)
            .setDestNum(2);
    assertEquals(x.size(), 5);
  }

  @Test
  //tests the setSource method
  public void testSetSource() {
    x.setSource(PileType.CASCADE);
    assertEquals(x.getSource(), PileType.CASCADE);
  }

  @Test
  //tests the setSourceNum method
  public void testSetSourceNum() {
    x.setSourceNum(5);
    assertEquals(x.getSourceNum(), 4);
  }

  @Test
  //tests the setCardIndex method
  public void testSetCardIndex() {
    x.setCardIndex(5);
    assertEquals(x.getCardIndex(), 4);
  }

  @Test
  //test the setDest method
  public void testSetDest() {
    x.setDest(PileType.CASCADE);
    assertEquals(x.getDest(), PileType.CASCADE);
  }

  @Test
  //test the setDestNum method
  public void testSetDestNum() {
    x.setDestNum(5);
    assertEquals(x.getdDestNum(), 4);
  }

  @Test
  //test the move within the command class
  public void testMove() {
    model.startGame(model.getDeck(), 4, 4, false);
    x.setSource(PileType.CASCADE)
            .setSourceNum(1)
            .setCardIndex(13)
            .setDest(PileType.OPEN)
            .setDestNum(1);
    x.move(model);
    assertEquals(movedCard, model.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  //tests to see if it throws an exception if the constructor is given a number less than 1
  public void testInvalidConstructor() {
    Command y = new Command(PileType.FOUNDATION, 0, 0, PileType.CASCADE, 0);
  }

}
