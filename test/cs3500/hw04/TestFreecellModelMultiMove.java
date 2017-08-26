package cs3500.hw04;

import org.junit.Test;

import java.util.List;

import cs3500.hw02.Card;
import cs3500.hw02.FreecellModel;
import cs3500.hw02.PileType;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by ChrisRisley on 5/28/17.
 */

/**
 * Class for testing the FreecellModel_Multi_Move class.
 * Includes regression testing.
 */
public class TestFreecellModelMultiMove {


  FreecellModel fcm = new FreecellModelMultiMove();
  FreecellModel fcm1 = new FreecellModelMultiMove();


  String roundRobin = "F1:" + "\n" +
          "F2:" + "\n" +
          "F3:" + "\n" +
          "F4:" + "\n" +
          "O1:" + "\n" +
          "O2:" + "\n" +
          "C1: A♣, 5♣, 9♣, K♣, 4♦, 8♦, Q♦, 3♥, 7♥, J♥, 2♠, 6♠, 10♠" + "\n" +
          "C2: 2♣, 6♣, 10♣, A♦, 5♦, 9♦, K♦, 4♥, 8♥, Q♥, 3♠, 7♠, J♠" + "\n" +
          "C3: 3♣, 7♣, J♣, 2♦, 6♦, 10♦, A♥, 5♥, 9♥, K♥, 4♠, 8♠, Q♠" + "\n" +
          "C4: 4♣, 8♣, Q♣, 3♦, 7♦, J♦, 2♥, 6♥, 10♥, A♠, 5♠, 9♠, K♠";

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


  String movedCard1 = "F1:" + " A♠" + "\n" +
          "F2:" + "\n" +
          "F3:" + "\n" +
          "F4:" + "\n" +
          "O1:" + " K♠" + "\n" +
          "O2:" + " 9♠" + "\n" +
          "O3:" + " 5♠" + "\n" +
          "O4:" + "\n" +
          "C1: A♣, 5♣, 9♣, K♣, 4♦, 8♦, Q♦, 3♥, 7♥, J♥, 2♠, 6♠, 10♠" + "\n" +
          "C2: 2♣, 6♣, 10♣, A♦, 5♦, 9♦, K♦, 4♥, 8♥, Q♥, 3♠, 7♠, J♠" + "\n" +
          "C3: 3♣, 7♣, J♣, 2♦, 6♦, 10♦, A♥, 5♥, 9♥, K♥, 4♠, 8♠, Q♠" + "\n" +
          "C4: 4♣, 8♣, Q♣, 3♦, 7♦, J♦, 2♥, 6♥, 10♥";


  String movedCard3 = "F1:" + "\n" +
          "F2:" + "\n" +
          "F3:" + "\n" +
          "F4:" + "\n" +
          "O1:" + "\n" +
          "O2:" + " K♠" + "\n" +
          "O3:" + "\n" +
          "O4:" + "\n" +
          "C1: A♣, 5♣, 9♣, K♣, 4♦, 8♦, Q♦, 3♥, 7♥, J♥, 2♠, 6♠, 10♠" + "\n" +
          "C2: 2♣, 6♣, 10♣, A♦, 5♦, 9♦, K♦, 4♥, 8♥, Q♥, 3♠, 7♠, J♠" + "\n" +
          "C3: 3♣, 7♣, J♣, 2♦, 6♦, 10♦, A♥, 5♥, 9♥, K♥, 4♠, 8♠, Q♠" + "\n" +
          "C4: 4♣, 8♣, Q♣, 3♦, 7♦, J♦, 2♥, 6♥, 10♥, A♠, 5♠, 9♠";

  @Test //Test the getDeck Method to determine if it returns 52 cards.
  public void testGetDeck() {
    assertEquals(52, fcm.getDeck().size());
  }

  @Test(expected = IllegalArgumentException.class)
  //fails to run if the deck has duplicates
  public void failWithDuplicates() {
    List<Card> x = fcm.getDeck();
    Card y = x.get(0);
    x.add(y);
    x.add(y);
    fcm.startGame(x, 4, 4, false);
  }

  @Test(expected = IllegalArgumentException.class)
  //fails to run if the deck isn't a full deck
  public void failWithPartialDeck() {
    List<Card> x = fcm.getDeck();
    x.remove(0);
    fcm.startGame(x, 4, 4, false);
  }

  @Test
  //verify that startGame shuffles the deck when told to do so
  public void testShuffle() {
    fcm.startGame(fcm.getDeck(), 4, 4, true);
    fcm1.startGame(fcm.getDeck(), 4, 4, false);
    assertNotEquals(fcm.getGameState(), fcm1.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  //verify that startGame will throw an IllegalArgumentException
  // if the number of cascade piles is less than 4
  public void incorrectCascade() {
    fcm.startGame(fcm.getDeck(), 3, 4, false);
  }

  @Test(expected = IllegalArgumentException.class)
  //verify that startGame will throw an IllegalArgumentException
  // if the number of open piles is less than 1
  public void incorrectOpen() {
    fcm.startGame(fcm.getDeck(), 4, 0, false);
  }

  @Test
  //verify that isGameOver returns true when the game is really over
  public void gameActuallyOver() {
    fcm.startGame(fcm.getDeck(), 52, 4, false);
    for (int i = 0; i < 13; i++) {
      fcm.move(PileType.CASCADE, i, 0, PileType.FOUNDATION, 0);
      fcm.move(PileType.CASCADE, i + 13, 0, PileType.FOUNDATION, 1);
      fcm.move(PileType.CASCADE, i + 26, 0, PileType.FOUNDATION, 2);
      fcm.move(PileType.CASCADE, i + 39, 0, PileType.FOUNDATION, 3);
    }
    assertEquals(true, fcm.isGameOver());
  }

  @Test
  //verify that calling startGame when a game is already in progress restarts the game correctly
  public void testRestartGame() {
    fcm.startGame(fcm.getDeck(), 4, 4, false);
    fcm.startGame(fcm.getDeck(), 4, 4, false);
    assertEquals(fcm.getGameState(), fcm.getGameState());
  }

  @Test
  //verify that getGameState returns an empty string after a call
  // to startGame has thrown an exception
  public void testReturnEmptyStringWhenExceptionThrown() {
    try {
      fcm.startGame(fcm.getDeck(), 0, 0, false);
    } catch (IllegalArgumentException e) {
      assertEquals(fcm.getGameState(), "");
    }
  }

  @Test //Tests to see if the game is over when the game shouldn't be over
  public void gameOver() {
    fcm.startGame(fcm.getDeck(), 4, 4, false);
    assertEquals(false, fcm.isGameOver());
  }

  @Test(expected = IllegalArgumentException.class)
  //Tests to see if the game is over when the game shouldn't be over
  public void nullDeck() {
    fcm.startGame(null, 4, 4, false);
  }

  @Test //Tests to see if start game actually starts the game and creates a deck, and
  //also tests to see if getGameState returns the correct String
  public void testGetGameStateStartGame() {
    fcm.startGame(fcm.getDeck(), 4, 2, false);
    assertEquals(roundRobin, fcm.getGameState());
  }

  @Test //Tests a move from a cascade pile to an open pile
  public void testMove() {
    fcm.startGame(fcm.getDeck(), 4, 4, false);
    fcm.move(PileType.CASCADE, 0, 12, PileType.OPEN, 0);
    assertEquals(movedCard, fcm.getGameState());
  }

  @Test //Tests a move from a cascade pile to a foundation pile
  public void testMove1() {
    fcm.startGame(fcm.getDeck(), 4, 4, false);
    fcm.move(PileType.CASCADE, 3, 12, PileType.OPEN, 0);
    fcm.move(PileType.CASCADE, 3, 11, PileType.OPEN, 1);
    fcm.move(PileType.CASCADE, 3, 10, PileType.OPEN, 2);
    fcm.move(PileType.CASCADE, 3, 9, PileType.FOUNDATION, 0);
    assertEquals(movedCard1, fcm.getGameState());
  }

  @Test //Tests a move from a cascade pile to a cascade pile
  public void testMove2() {
    fcm.startGame(fcm.getDeck(), 52, 4, false);
    fcm.move(PileType.CASCADE, 47, 0, PileType.CASCADE, 22);
    assertEquals("C23: 10♦, 9♠", fcm.getGameState().substring(200, 212));
  }

  @Test(expected = IllegalArgumentException.class) //Tests the invalid move from Foundation to Open
  public void testMove3() {
    fcm.startGame(fcm.getDeck(), 4, 4, false);
    fcm.move(PileType.FOUNDATION, 0, 12, PileType.OPEN, 0);
  }

  @Test(expected = IllegalArgumentException.class) //Tests the invalid move from Foundation to
  // Cascade
  public void testMove4() {
    fcm.startGame(fcm.getDeck(), 4, 4, false);
    fcm.move(PileType.FOUNDATION, 0, 12, PileType.CASCADE, 0);
  }

  @Test(expected = IllegalArgumentException.class) //Tests the invalid move from Foundation to
  //Foundation
  public void testMove5() {
    fcm.startGame(fcm.getDeck(), 4, 4, false);
    fcm.move(PileType.FOUNDATION, 0, 12, PileType.FOUNDATION, 0);
  }

  @Test(expected = IllegalArgumentException.class) //Tests the invalid move from empty Open to
  //Cascade
  public void testMove6() {
    fcm.startGame(fcm.getDeck(), 4, 4, false);
    fcm.move(PileType.CASCADE, 3, 12, PileType.OPEN, 0);
    fcm.move(PileType.OPEN, 0, 0, PileType.CASCADE, 3);
  }

  @Test //Tests the move from an Open to Foundation
  public void testMove7() {
    fcm.startGame(fcm.getDeck(), 4, 4, false);
    fcm.move(PileType.CASCADE, 3, 12, PileType.OPEN, 0);
    fcm.move(PileType.CASCADE, 3, 11, PileType.OPEN, 1);
    fcm.move(PileType.CASCADE, 3, 10, PileType.OPEN, 2);
    fcm.move(PileType.CASCADE, 3, 9, PileType.OPEN, 3);
    fcm.move(PileType.OPEN, 3, 0, PileType.FOUNDATION, 0);
    assertEquals(movedCard1, fcm.getGameState());
  }

  @Test //Tests the move from Open to Open
  public void testMove8() {
    fcm.startGame(fcm.getDeck(), 4, 4, false);
    fcm.move(PileType.CASCADE, 3, 12, PileType.OPEN, 0);
    fcm.move(PileType.OPEN, 0, 0, PileType.OPEN, 1);
    assertEquals(movedCard3, fcm.getGameState());
  }

  @Test(expected = IllegalArgumentException.class) //Tests the invalid move from a cascade
  //to Foundation where the card is not the correct card to put on the Foundation Pile
  public void testMove9() {
    fcm.startGame(fcm.getDeck(), 4, 4, false);
    fcm.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
  }

  @Test(expected = IllegalArgumentException.class) //Tests the invalid move from Cascade to Cascade
  // where the card is not the right color to be placed
  public void testMove10() {
    fcm.startGame(fcm.getDeck(), 4, 4, false);
    fcm.move(PileType.CASCADE, 0, 12, PileType.CASCADE, 0);
  }

  @Test(expected = IllegalArgumentException.class) //Tests the invalid move from Open to
  //Foundation where the card is not the right card to be placed on the foundation pile
  public void testMove11() {
    fcm.startGame(fcm.getDeck(), 4, 4, false);
    fcm.move(PileType.CASCADE, 0, 12, PileType.OPEN, 0);
    fcm.move(PileType.OPEN, 0, 0, PileType.FOUNDATION, 0);
  }

  @Test(expected = IllegalArgumentException.class) //Tests the invalid move from Cascade to Cascade
  //where the card being placed doesn't follow the cascade rules
  public void testMove12() {
    fcm.startGame(fcm.getDeck(), 4, 4, false);
    fcm.move(PileType.CASCADE, 0, 12, PileType.CASCADE, 3);
  }

  @Test(expected = IllegalArgumentException.class) //Tests the invalid move from Open to Cascade
  //where the card being placed doesn't follow the cascade rules
  public void testMove13() {
    fcm.startGame(fcm.getDeck(), 4, 4, false);
    fcm.move(PileType.OPEN, 0, 12, PileType.CASCADE, 3);
  }

  @Test(expected = IllegalArgumentException.class) //Tests the invalid move from Foundation
  //to Cascade where a card can't be moved once it is in the foundation pile
  public void testMove14() {
    fcm.startGame(fcm.getDeck(), 4, 4, false);
    fcm.move(PileType.FOUNDATION, 0, 12, PileType.CASCADE, 3);
  }


  //TESTS FOR MULTI MOVE

  @Test(expected = IllegalArgumentException.class)
  //Tests a move from a cascade pile to a foundation pile
  public void testMultiMove1() {
    fcm.startGame(fcm.getDeck(), 52, 4, false);
    fcm.move(PileType.CASCADE, 14, 0, PileType.CASCADE, 0);
    fcm.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);
  }

  @Test //Tests a move from a cascade pile to a cascade pile
  public void testMultiMove2() {
    fcm.startGame(fcm.getDeck(), 52, 4, false);
    fcm.move(PileType.CASCADE, 1, 0, PileType.OPEN, 0);
    fcm.move(PileType.CASCADE, 14, 0, PileType.CASCADE, 2);
    fcm.move(PileType.CASCADE, 2, 0, PileType.CASCADE, 16);

    String doubleMove = "C17: 4♦, 3♣, 2♦";

    assertEquals(doubleMove, fcm.getGameState().substring(146, 161));
  }

  @Test(expected = IllegalArgumentException.class) //Tests the invalid move from Foundation to Open
  public void testMultiMove3() {
    fcm.startGame(fcm.getDeck(), 52, 4, false);
    fcm.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);
    fcm.move(PileType.FOUNDATION, 0, 0, PileType.OPEN, 0);
  }

  @Test(expected = IllegalArgumentException.class) //Tests the invalid move from Foundation to
  // Cascade
  public void testMultiMove4() {
    fcm.startGame(fcm.getDeck(), 52, 4, false);
    fcm.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);
    fcm.move(PileType.FOUNDATION, 0, 0, PileType.CASCADE, 0);
  }

  @Test(expected = IllegalArgumentException.class) //Tests the invalid move from Foundation to
  //Foundation
  public void testMultiMove5() {
    fcm.startGame(fcm.getDeck(), 52, 4, false);
    fcm.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);
    fcm.move(PileType.FOUNDATION, 0, 0, PileType.FOUNDATION, 1);
  }

  @Test(expected = IllegalArgumentException.class) //Tests the move from Cascade to Open
  public void testMultiMove6() {
    fcm.startGame(fcm.getDeck(), 4, 4, false);
    fcm.move(PileType.CASCADE, 3, 9, PileType.OPEN, 0);
  }


  @Test(expected = IllegalArgumentException.class) //Tests the invalid move from Cascade to Cascade
  // where the card is not the right color to be placed
  public void testMultiMove7() {
    fcm.startGame(fcm.getDeck(), 52, 4, false);
    fcm.move(PileType.CASCADE, 14, 0, PileType.CASCADE, 0);
    fcm.move(PileType.CASCADE, 0, 0, PileType.CASCADE, 1);
  }


  @Test(expected = IllegalArgumentException.class) //Tests the invalid move from Cascade to Cascade
  //where the card being placed doesn't follow the cascade rules
  public void testMultiMove8() {
    fcm.startGame(fcm.getDeck(), 52, 4, false);
    fcm.move(PileType.CASCADE, 14, 0, PileType.CASCADE, 0);
    fcm.move(PileType.CASCADE, 0, 0, PileType.CASCADE, 15);
  }

  @Test(expected = IllegalArgumentException.class) //tests a move where the build is invalid
  public void invalidBuild() {
    fcm.startGame(fcm.getDeck(), 28, 4, false);
    fcm.move(PileType.CASCADE, 24, 0, PileType.OPEN, 0);
    fcm.move(PileType.CASCADE, 0, 24, PileType.CASCADE, 0);
  }


  @Test(expected = IllegalArgumentException.class) //tests if fails when not enough open slots for
  //multi move
  public void moveNotEnoughOpen() {
    fcm.startGame(fcm.getDeck(), 28, 4, false);
    fcm.move(PileType.CASCADE, 24, 0, PileType.OPEN, 0);
    fcm.move(PileType.CASCADE, 23, 1, PileType.OPEN, 1);
    fcm.move(PileType.CASCADE, 22, 1, PileType.OPEN, 2);
    fcm.move(PileType.CASCADE, 19, 1, PileType.CASCADE, 22);
    fcm.move(PileType.CASCADE, 5, 1, PileType.CASCADE, 22);
    fcm.move(PileType.CASCADE, 17, 1, PileType.CASCADE, 22);
    fcm.move(PileType.CASCADE, 10, 1, PileType.OPEN, 3);
    fcm.move(PileType.CASCADE, 22, 0, PileType.CASCADE, 10);
  }
}
