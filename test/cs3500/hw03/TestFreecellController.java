package cs3500.hw03;


import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import cs3500.hw02.FreecellModel;
import cs3500.hw04.FreecellModelMultiMove;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by ChrisRisley on 5/16/17.
 * Modified 5/29/17: Added additional tests to account for the multi-move model.
 */

/**
 * Tests the FreecellController class and how it interacts with the model.
 */
public class TestFreecellController {
  FreecellModel model = new FreecellModel();
  FreecellModel multiModel = new FreecellModelMultiMove();
  StringBuffer ap = new StringBuffer();


  @Test
  //Tests to see if the model quits if Q is entered
  public void testQuit() {
    Reader r = new StringReader("Q");
    new FreecellController(r, ap).playGame(model.getDeck(), model, 8, 4, false);
    assertEquals(model.getGameState() + "\n" + "Game quit prematurely.", ap.toString());
  }

  @Test
  //Tests to see if the game starts properly if it has already been started
  public void testStartsGameProperlyWhenAlreadyStarted() {
    Reader r = new StringReader("C1 6 O1 Q");
    new FreecellController(r, ap).playGame(model.getDeck(), model, 8, 4, false);
    r = new StringReader("Q");
    String firstState = ap.toString();
    new FreecellController(r, ap).playGame(model.getDeck(), model, 8, 4, false);
    assertNotEquals(firstState, ap.toString());
  }


  @Test
  //Tests to see if Shuffle works from the controller
  public void testShuffleFromController() {
    Reader r = new StringReader("Q");
    Reader p = new StringReader("Q");
    StringBuffer ap2 = new StringBuffer();
    FreecellController x = new FreecellController(r, ap);
    FreecellController y = new FreecellController(p, ap2);
    x.playGame(model.getDeck(), model, 8, 4, true);
    y.playGame(model.getDeck(), model, 8, 4, false);
    assertNotEquals(ap.toString(), ap2.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  //Tests to see if it throws an exception for a null deck
  public void testNullDeck() {
    Reader r = new StringReader("Q");
    new FreecellController(r, ap).playGame(null, model, 8, 4, false);
  }

  @Test
  //Tests to see if it doesn't start game with a partial deck
  public void testPartialDeck() {
    Reader r = new StringReader("Q");
    List x = model.getDeck();
    x.remove(2);
    x.remove(1);
    new FreecellController(r, ap).playGame(x, model, 8, 4, false);
    assertEquals("Could not start game.", ap.toString());
  }

  @Test
  //Tests to see if it doesn't start game with a partial deck
  public void testDuplicatesDeck() {
    Reader r = new StringReader("Q");
    List x = model.getDeck();
    Object dup = x.get(1);
    x.add(dup);
    x.add(dup);
    new FreecellController(r, ap).playGame(x, model, 8, 4, false);
    assertEquals("Could not start game.", ap.toString());
  }


  @Test(expected = IllegalArgumentException.class)
  //tests to see if it throws an exception for a null model
  public void testNullModel() {
    Reader r = new StringReader("Q");
    new FreecellController(r, ap)
            .playGame(model.getDeck(), null, 8, 4, false);
  }

  @Test(expected = IllegalStateException.class)
  //tests to see if throws an exception if FreecellController isn't properly initialized
  public void testObjectNotInitialized() {
    Reader r = new StringReader(" Q");
    new FreecellController(null, ap);
  }

  @Test(expected = IllegalStateException.class)
  //tests to see if throws an exception if FreecellController isn't properly initialized
  public void testObjectNotInitialized1() {
    Reader r = new StringReader(" Q");
    new FreecellController(r, null);
  }

  @Test
  //tests to see if it doesn't start game if given an invalid start game with numOpens < 1
  public void cannotStartGame() {
    Reader r = new StringReader("Q");
    new FreecellController(r, ap)
            .playGame(model.getDeck(), model, 8, 0, false);
    assertEquals("Could not start game.", ap.toString());
  }

  @Test
  //test for an empty deck
  public void cannotStartGame0() {
    Reader r = new StringReader("Q");
    new FreecellController(r, ap)
            .playGame(new ArrayList(), model, 8, 0, false);
    assertEquals("Could not start game.", ap.toString());
  }


  @Test
  //tests to see if it doesn't start game if given an invalid start game with numCascades < 4
  public void cannotStartGame1() {
    Reader r = new StringReader("Q");
    new FreecellController(r, ap).playGame(model.getDeck(), model, 3, 4, false);
    assertEquals("Could not start game.", ap.toString());
  }


  @Test
  //Tests to see if it still quits if Q is preceded by an empty space
  public void testEmptyInput() {
    Reader r = new StringReader(" Q");
    new FreecellController(r, ap).playGame(model.getDeck(), model, 8, 4, false);
    assertEquals(model.getGameState() + "\n" + "Game quit prematurely.", ap.toString());
  }

  @Test
  //Tests to see if it still quits with a lowercase letter
  public void testQuitLowerCase() {
    Reader r = new StringReader("q");
    new FreecellController(r, ap).playGame(model.getDeck(), model, 8, 4, false);
    assertEquals(model.getGameState() + "\n" + "Game quit prematurely.", ap.toString());
  }


  @Test
  //Tests if it properly moves the object
  public void testBasicMove() {
    Reader r = new StringReader("C1 7 O1 Q");
    new FreecellController(r, ap).playGame(model.getDeck(), model, 8, 4, false);
    assertEquals(model.getGameState() + "\n" + "Game " +
            "quit prematurely.", ap.substring(model.getGameState().length() + 2));
  }

  @Test
  //Tests if it properly moves an object when lowercase letters are typed
  public void testBasicMoveLowerCase() {
    Reader r = new StringReader("c1 7 o1 q");
    new FreecellController(r, ap).playGame(model.getDeck(), model, 8, 4, false);
    assertEquals(model.getGameState() + "\n" + "Game " +
            "quit prematurely.", ap.substring(model.getGameState().length() + 2));
  }

  @Test
  //Tests to see if it responds correctly to an invalid move
  public void testInvalidMove() {
    Reader r = new StringReader("C1 7 F1 Q");
    new FreecellController(r, ap).playGame(model.getDeck(), model, 8, 4, false);
    assertEquals(model.getGameState() + "\n" + "Invalid move. Try again." + "\n" + "Game " +
            "quit prematurely.", ap.toString());
  }

  @Test
  //Tests to see if it properly makes a move when given the correct input after an invalid move
  public void testInvalidMoveWithFix() {
    Reader r = new StringReader("C1 7 F1 C1 7 O1 Q");
    new FreecellController(r, ap).playGame(model.getDeck(), model, 8, 4, false);
    assertEquals("Invalid move. Try again." + "\n" + model.getGameState() + "\n" + "Game " +
            "quit prematurely.", ap.substring(model.getGameState().length() + 2));
  }


  @Test
  //tests to see if it can handle to moves in a row
  public void testDoubleMove() {
    Reader r = new StringReader("C1 7 O1 O1 1 O2 Q");
    new FreecellController(r, ap).playGame(model.getDeck(), model, 8, 4, false);
    assertEquals(model.getGameState() + "\n" + "Game " +
            "quit prematurely.", ap.substring((model.getGameState().length() * 2) + 3));
  }

  @Test
  //tests to see if it can handle to moves in a row
  public void testDoubleMoveFirstWrong() {
    Reader r = new StringReader("C1 7 F1 C1 7 O2 Q");
    new FreecellController(r, ap).playGame(model.getDeck(), model, 8, 4, false);
    assertEquals("Invalid move. Try again." + "\n" + model.getGameState() + "\n" + "Game " +
            "quit prematurely.", ap.substring(model.getGameState().length() + 2));
  }

  @Test
  //Tests to see if it can still make a move with new line characters in between
  public void testNewLineCommands() {
    Reader r = new StringReader("C1" + "\n" + "7" + "\n" + "O1 Q");
    new FreecellController(r, ap).playGame(model.getDeck(), model, 8, 4, false);
    assertEquals(model.getGameState() + "\n" + "Game " +
            "quit prematurely.", ap.substring(model.getGameState().length() + 2));
  }

  @Test
  //tests to see if replaces an invalid character with a valid one if provided with one later
  public void testPartiallyInvalidInput() {
    Reader r = new StringReader("C1 A 7 O1 Q");
    new FreecellController(r, ap).playGame(model.getDeck(), model, 8, 4, false);
    assertEquals("Re-enter Card Index" + "\n" + model.getGameState() + "\n" + "Game " +
            "quit prematurely.", ap.substring(model.getGameState().length() + 2));
  }

  @Test
  //test a basic invalid cardindex input
  public void testInvalidInput() {
    Reader r = new StringReader("C1 A Q");
    new FreecellController(r, ap).playGame(model.getDeck(), model, 8, 4, false);
    assertEquals(model.getGameState() + "\n" + "Re-enter Card Index" + "\n" + "Game " +
            "quit prematurely.", ap.toString());
  }

  @Test
  //tests if the card index that is too big is an invalid move
  public void testInvalidCardIndex() {
    Reader r = new StringReader("C1 100 O1 Q");
    new FreecellController(r, ap).playGame(model.getDeck(), model, 8, 4, false);
    assertEquals(model.getGameState() + "\n" + "Invalid move. Try again." + "\n" + "Game " +
            "quit prematurely.", ap.toString());
  }

  @Test
  //tests if the card index that is too big is an invalid move
  public void testInvalidCardIndex1() {
    Reader r = new StringReader("C1 -1 1 Q");
    new FreecellController(r, ap).playGame(model.getDeck(), model, 8, 4, false);
    assertEquals(model.getGameState() + "\n" + "Re-enter Card Index" + "\n" + "Game " +
            "quit prematurely.", ap.toString());
  }

  @Test
  //tests to see if it asks the user for a new source pile/source num if given an invalid one
  public void testInvalidSource() {
    Reader r = new StringReader("CA C1 7 O1 Q");
    new FreecellController(r, ap).playGame(model.getDeck(), model, 8, 4, false);
    assertEquals("Re-enter Source Type and Pile Number" + "\n" + model.getGameState() +
            "\n" + "Game quit prematurely.", ap.substring(model.getGameState().length() + 2));
  }

  @Test
  //tests to see if it asks the user for a new dest pile/dest num if given an invalid one
  public void testInvalidDest() {
    Reader r = new StringReader("C1 7 OA O1 Q");
    new FreecellController(r, ap).playGame(model.getDeck(), model, 8, 4, false);
    assertEquals("Re-enter Destination Type and Pile Number" + "\n"
            + model.getGameState() + "\n" + "Game " +
            "quit prematurely.", ap.substring(model.getGameState().length() + 2));
  }

  @Test
  //tests to see if it asks the user for a new source pile/source num if given an invalid one
  public void testInvalidSourceDest() {
    Reader r = new StringReader("CA C1 7 Z1 Q");
    new FreecellController(r, ap).playGame(model.getDeck(), model, 8, 4, false);
    assertEquals(model.getGameState() + "\n" + "Re-enter Source Type and Pile Number"
            + "\n" + "Re-enter Destination Type and Pile Number" + "\n" + "Game " +
            "quit prematurely.", ap.toString());
  }

  @Test
  //test a move from cascade
  public void testMove0() {
    Reader r = new StringReader("C2 7 O1 Q");
    new FreecellController(r, ap).playGame(model.getDeck(), model, 8, 4, false);
    assertEquals(model.getGameState() + "\n" + "Game " +
            "quit prematurely.", ap.substring(model.getGameState().length() + 2));
  }

  @Test
  //test a move from foundation
  public void testMove1() {
    Reader r = new StringReader("F1 7 O1 Q");
    new FreecellController(r, ap).playGame(model.getDeck(), model, 8, 4, false);
    assertEquals(model.getGameState() + "\n" + "Invalid move. Try again." + "\n" + "Game " +
            "quit prematurely.", ap.toString());
  }

  @Test
  //test a move from an open pile
  public void testMove2() {
    Reader r = new StringReader("O1 7 O1 Q");
    new FreecellController(r, ap).playGame(model.getDeck(), model, 8, 4, false);
    assertEquals(model.getGameState() + "\n" + "Invalid move. Try again." + "\n" + "Game " +
            "quit prematurely.", ap.toString());
  }


  @Test
  //test a move to cascade
  public void testMove3() {
    Reader r = new StringReader("C2 7 O1 Q");
    new FreecellController(r, ap).playGame(model.getDeck(), model, 8, 4, false);
    assertEquals(model.getGameState() + "\n" + "Game " +
            "quit prematurely.", ap.substring(model.getGameState().length() + 2));
  }

  @Test
  //test a move to foundation
  public void testMove4() {
    Reader r = new StringReader("F1 7 C1 Q");
    new FreecellController(r, ap).playGame(model.getDeck(), model, 8, 4, false);
    assertEquals(model.getGameState() + "\n" + "Invalid move. Try again." + "\n" + "Game " +
            "quit prematurely.", ap.toString());
  }

  @Test
  //test a move to an open pile
  public void testMove5() {
    Reader r = new StringReader("O1 7 O1 Q");
    new FreecellController(r, ap).playGame(model.getDeck(), model, 8, 4, false);
    assertEquals(model.getGameState() + "\n" + "Invalid move. Try again." + "\n" + "Game " +
            "quit prematurely.", ap.toString());
  }

  @Test
  //test a move to an open pile shuffle true
  public void testMoveWithShuffle6() {
    Reader r = new StringReader("C1 7 O1 Q");
    new FreecellController(r, ap).playGame(model.getDeck(), model, 8, 4, true);
    assertEquals(model.getGameState() + "\n" + "Game " +
            "quit prematurely.", ap.substring(model.getGameState().length() + 2));
  }

  @Test
  //test a move to a full open pile
  public void testMoveWithFullOpen() {
    Reader r = new StringReader("C1 7 O1 C1 6 O1 Q");
    new FreecellController(r, ap).playGame(model.getDeck(), model, 8, 4, false);
    assertEquals(model.getGameState() + "\n" + "Invalid move. Try again." + "\n" + "Game " +
            "quit prematurely.", ap.substring(model.getGameState().length() + 2));
  }


  @Test
  //test a move to a foundation pile that it can't move to
  public void testInvalidMoveToFoundation() {
    Reader r = new StringReader("C1 7 F1 Q");
    new FreecellController(r, ap).playGame(model.getDeck(), model, 8, 4, false);
    assertEquals("Invalid move. Try again." + "\n" + "Game " +
            "quit prematurely.", ap.substring(model.getGameState().length() + 1));
  }

  @Test
  //test a move to cascade pile that it can't move to
  public void testInvalidMoveToCascade() {
    Reader r = new StringReader("C1 7 C2 Q");
    new FreecellController(r, ap).playGame(model.getDeck(), model, 4, 4, false);
    assertEquals("Invalid move. Try again." + "\n" + "Game " +
            "quit prematurely.", ap.substring(model.getGameState().length() + 1));
  }


  @Test
  //test to see if the game actually ends when
  public void testGameOver() {
    String f1 = "";
    String f2 = "";
    String f3 = "";
    String f4 = "";

    for (int i = 1; i < 14; i++) {
      f1 += "C" + Integer.toString(i) + " 1 " + "F1 ";
      f2 += "C" + Integer.toString(i + 13) + " 1 " + "F2 ";
      f3 += "C" + Integer.toString(i + 26) + " 1 " + "F3 ";
      f4 += "C" + Integer.toString(i + 39) + " 1 " + "F4 ";
    }

    Reader r = new StringReader(f1 + f2 + f3 + f4);
    new FreecellController(r, ap).playGame(model.getDeck(), model, 52, 4, false);
    assertEquals(true, model.isGameOver());
    assertEquals("Game over.", ap.substring(ap.length() - 10));
  }

  @Test
  //test quitting halfway
  public void testQuittingHalfway() {
    Reader r = new StringReader("O1 Q O1");
    new FreecellController(r, ap).playGame(model.getDeck(), model, 52, 4, false);
    assertEquals("Game quit prematurely.", ap.substring(model.getGameState().length() + 1));
  }

  @Test
  //test negative source num
  public void testNegativeSource() {
    Reader r = new StringReader("O0 O1 Q");
    new FreecellController(r, ap).playGame(model.getDeck(), model, 52, 4, false);
    assertEquals("Re-enter Source Type and Pile Number" + "\n" + "Game quit prematurely.",
            ap.substring(model.getGameState().length() + 1));
  }

  @Test
  //test negative destnum
  public void testNegativeDest() {
    Reader r = new StringReader("C1 7 O0 Q");
    new FreecellController(r, ap).playGame(model.getDeck(), model, 52, 4, false);
    assertEquals("Re-enter Destination Type and Pile Number" + "\n" + "Game quit prematurely.",
            ap.substring(model.getGameState().length() + 1));
  }


  //TESTS FOR MULTI MOVE

  @Test
  //Tests a move from a cascade pile to a foundation pile
  public void testMultiMove1() {
    Reader r = new StringReader("C15 1 C1 C1 1 F1 Q");
    new FreecellController(r, ap)
            .playGame(multiModel.getDeck(), multiModel, 52, 4, false);
    assertEquals("Invalid move. Try again." + "\n"
                    + multiModel.getGameState() + "\n" + "Game quit prematurely.",
            ap.substring(multiModel.getGameState().length() + 1));
  }

  @Test
  //Tests to see if the multiModel quits if Q is entered
  public void testMultiQuit() {
    Reader r = new StringReader("Q");
    new FreecellController(r, ap).playGame(multiModel.getDeck(), multiModel, 8, 4, false);
    assertEquals(multiModel.getGameState() + "\n" + "Game quit prematurely.", ap.toString());
  }

  @Test
  //Tests to see if the game starts properly if it has already been started
  public void testMultiStartsGameProperlyWhenAlreadyStarted() {
    Reader r = new StringReader("C1 6 O1 Q");
    new FreecellController(r, ap).playGame(multiModel.getDeck(), multiModel, 8, 4, false);
    r = new StringReader("Q");
    String firstState = ap.toString();
    new FreecellController(r, ap).playGame(multiModel.getDeck(), multiModel, 8, 4, false);
    assertNotEquals(firstState, ap.toString());
  }


  @Test
  //Tests to see if Shuffle works from the controller
  public void testMultiShuffleFromController() {
    Reader r = new StringReader("Q");
    Reader p = new StringReader("Q");
    StringBuffer ap2 = new StringBuffer();
    FreecellController x = new FreecellController(r, ap);
    FreecellController y = new FreecellController(p, ap2);
    x.playGame(multiModel.getDeck(), multiModel, 8, 4, true);
    y.playGame(multiModel.getDeck(), multiModel, 8, 4, false);
    assertNotEquals(ap.toString(), ap2.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  //Tests to see if it throws an exception for a null deck
  public void testMultiNullDeck() {
    Reader r = new StringReader("Q");
    new FreecellController(r, ap).playGame(null, multiModel, 8, 4, false);
  }

  @Test
  //Tests to see if it doesn't start game with a partial deck
  public void testMultiPartialDeck() {
    Reader r = new StringReader("Q");
    List x = multiModel.getDeck();
    x.remove(2);
    x.remove(1);
    new FreecellController(r, ap).playGame(x, multiModel, 8, 4, false);
    assertEquals("Could not start game.", ap.toString());
  }

  @Test//Tests to see if it doesn't start game with a partial deck
  public void testMultiDuplicatesDeck() {
    Reader r = new StringReader("Q");
    List x = multiModel.getDeck();
    Object dup = x.get(1);
    x.add(dup);
    x.add(dup);
    new FreecellController(r, ap).playGame(x, multiModel, 8, 4, false);
    assertEquals("Could not start game.", ap.toString());
  }

  //tests to see if it throws an exception for a null multiModel
  @Test(expected = IllegalArgumentException.class)
  public void testMultiNullmultiModel() {
    Reader r = new StringReader("Q");
    new FreecellController(r, ap).playGame(multiModel.getDeck(), null, 8, 4, false);
  }

  //tests to see if throws an exception if FreecellController isn't properly initialized
  @Test(expected = IllegalStateException.class)
  public void testMultiObjectNotInitialized() {
    Reader r = new StringReader(" Q");
    new FreecellController(null, null);
  }

  //tests to see if it doesn't start game if given an invalid start game with numOpens < 1
  @Test
  public void cannotStartMultiGame() {
    Reader r = new StringReader("Q");
    new FreecellController(r, ap).playGame(multiModel.getDeck(), multiModel, 8, 0, false);
    assertEquals("Could not start game.", ap.toString());
  }

  @Test //test for an empty deck
  public void cannotStartMultiGame0() {
    Reader r = new StringReader("Q");
    new FreecellController(r, ap).playGame(new ArrayList(), multiModel, 8, 0, false);
    assertEquals("Could not start game.", ap.toString());
  }

  //tests to see if it doesn't start game if given an invalid start game with numCascades < 4
  @Test
  public void cannotStartMultiGame1() {
    Reader r = new StringReader("Q");
    new FreecellController(r, ap).playGame(multiModel.getDeck(), multiModel, 3, 4, false);
    assertEquals("Could not start game.", ap.toString());
  }

  @Test//Tests to see if it still quits if Q is preceded by an empty space
  public void testEmptyInputMulti() {
    Reader r = new StringReader(" Q");
    new FreecellController(r, ap).playGame(multiModel.getDeck(), multiModel, 8, 4, false);
    assertEquals(multiModel.getGameState() + "\n" + "Game quit prematurely.", ap.toString());
  }

  @Test//Tests to see if it still quits with a lowercase letter
  public void testQuitLowerCaseMulti() {
    Reader r = new StringReader("q");
    new FreecellController(r, ap).playGame(multiModel.getDeck(), multiModel, 8, 4, false);
    assertEquals(multiModel.getGameState() + "\n" + "Game quit prematurely.", ap.toString());
  }


  @Test//Tests if it properly moves the object
  public void testBasicMoveMulti() {
    Reader r = new StringReader("C1 7 O1 Q");
    new FreecellController(r, ap).playGame(multiModel.getDeck(), multiModel, 8, 4, false);
    assertEquals(multiModel.getGameState() + "\n" + "Game " +
            "quit prematurely.", ap.substring(multiModel.getGameState().length() + 2));
  }

  @Test//Tests if it properly moves an object when lowercase letters are typed
  public void testBasicMoveLowerCaseMulti() {
    Reader r = new StringReader("c1 7 o1 q");
    new FreecellController(r, ap).playGame(multiModel.getDeck(), multiModel, 8, 4, false);
    assertEquals(multiModel.getGameState() + "\n" + "Game " +
            "quit prematurely.", ap.substring(multiModel.getGameState().length() + 2));
  }

  @Test//Tests to see if it responds correctly to an invalid move
  public void testInvalidMoveMulti() {
    Reader r = new StringReader("C1 7 F1 Q");
    new FreecellController(r, ap).playGame(multiModel.getDeck(), multiModel, 8, 4, false);
    assertEquals(multiModel.getGameState() + "\n" + "Invalid move. Try again." + "\n" + "Game " +
            "quit prematurely.", ap.toString());
  }

  @Test//Tests to see if it properly makes a move when given the correct input after an invalid move
  public void testInvalidMoveWithFixMulti() {
    Reader r = new StringReader("C1 7 F1 C1 7 O1 Q");
    new FreecellController(r, ap).playGame(multiModel.getDeck(), multiModel, 8, 4, false);
    assertEquals("Invalid move. Try again." + "\n" + multiModel.getGameState() + "\n" + "Game " +
            "quit prematurely.", ap.substring(multiModel.getGameState().length() + 2));
  }


  @Test//tests to see if it can handle to moves in a row
  public void testDoubleMoveMulti() {
    Reader r = new StringReader("C1 7 O1 O1 1 O2 Q");
    new FreecellController(r, ap).playGame(multiModel.getDeck(), multiModel, 8, 4, false);
    assertEquals(multiModel.getGameState() + "\n" + "Game " +
            "quit prematurely.", ap.substring((multiModel.getGameState().length() * 2) + 3));
  }

  @Test//tests to see if it can handle to moves in a row
  public void testDoubleMoveFirstWrongMulti() {
    Reader r = new StringReader("C1 7 F1 C1 7 O2 Q");
    new FreecellController(r, ap).playGame(multiModel.getDeck(), multiModel, 8, 4, false);
    assertEquals("Invalid move. Try again." + "\n" + multiModel.getGameState() + "\n" + "Game " +
            "quit prematurely.", ap.substring(multiModel.getGameState().length() + 2));
  }

  @Test//Tests to see if it can still make a move with new line characters in between
  public void testNewLineCommandsMulti() {
    Reader r = new StringReader("C1" + "\n" + "7" + "\n" + "O1 Q");
    new FreecellController(r, ap).playGame(multiModel.getDeck(), multiModel, 8, 4, false);
    assertEquals(multiModel.getGameState() + "\n" + "Game " +
            "quit prematurely.", ap.substring(multiModel.getGameState().length() + 2));
  }

  @Test//tests to see if replaces an invalid character with a valid one if provided with one later
  public void testPartiallyInvalidInputMulti() {
    Reader r = new StringReader("C1 A 7 O1 Q");
    new FreecellController(r, ap).playGame(multiModel.getDeck(), multiModel, 8, 4, false);
    assertEquals("Re-enter Card Index" + "\n" + multiModel.getGameState() + "\n" + "Game " +
            "quit prematurely.", ap.substring(multiModel.getGameState().length() + 2));
  }

  @Test//test a basic invalid cardindex input
  public void testInvalidInputMulti() {
    Reader r = new StringReader("C1 A Q");
    new FreecellController(r, ap).playGame(multiModel.getDeck(), multiModel, 8, 4, false);
    assertEquals(multiModel.getGameState() + "\n" + "Re-enter Card Index" + "\n" + "Game " +
            "quit prematurely.", ap.toString());
  }

  @Test//tests if the card index that is too big is an invalid move
  public void testInvalidCardIndexMulti() {
    Reader r = new StringReader("C1 100 O1 Q");
    new FreecellController(r, ap).playGame(multiModel.getDeck(), multiModel, 8, 4, false);
    assertEquals(multiModel.getGameState() + "\n" + "Invalid move. Try again." + "\n" + "Game " +
            "quit prematurely.", ap.toString());
  }

  @Test//tests if the card index that is too big is an invalid move
  public void testInvalidCardIndex1Multi() {
    Reader r = new StringReader("C1 -1 1 Q");
    new FreecellController(r, ap).playGame(multiModel.getDeck(), multiModel, 8, 4, false);
    assertEquals(multiModel.getGameState() + "\n" + "Re-enter Card Index" + "\n" + "Game " +
            "quit prematurely.", ap.toString());
  }

  @Test//tests to see if it asks the user for a new source pile/source num if given an invalid one
  public void testInvalidSourceMulti() {
    Reader r = new StringReader("CA C1 7 O1 Q");
    new FreecellController(r, ap).playGame(multiModel.getDeck(), multiModel, 8, 4, false);
    assertEquals("Re-enter Source Type and Pile Number" + "\n" + multiModel.getGameState() +
            "\n" + "Game quit prematurely.", ap.substring(multiModel.getGameState().length() + 2));
  }

  @Test//tests to see if it asks the user for a new dest pile/dest num if given an invalid one
  public void testInvalidDestMulti() {
    Reader r = new StringReader("C1 7 OA O1 Q");
    new FreecellController(r, ap).playGame(multiModel.getDeck(), multiModel, 8, 4, false);
    assertEquals("Re-enter Destination Type and Pile Number" + "\n"
            + multiModel.getGameState() + "\n" + "Game " +
            "quit prematurely.", ap.substring(multiModel.getGameState().length() + 2));
  }

  @Test//tests to see if it asks the user for a new source pile/source num if given an invalid one
  public void testInvalidSourceDestMulti() {
    Reader r = new StringReader("CA C1 7 Z1 Q");
    new FreecellController(r, ap).playGame(multiModel.getDeck(), multiModel, 8, 4, false);
    assertEquals(multiModel.getGameState() + "\n" + "Re-enter Source Type and Pile Number"
            + "\n" + "Re-enter Destination Type and Pile Number" + "\n" + "Game " +
            "quit prematurely.", ap.toString());
  }

  @Test//test a move from cascade
  public void testMove0Multi() {
    Reader r = new StringReader("C2 7 O1 Q");
    new FreecellController(r, ap).playGame(multiModel.getDeck(), multiModel, 8, 4, false);
    assertEquals(multiModel.getGameState() + "\n" + "Game " +
            "quit prematurely.", ap.substring(multiModel.getGameState().length() + 2));
  }

  @Test//test a move from foundation
  public void testMove1Multi() {
    Reader r = new StringReader("F1 7 O1 Q");
    new FreecellController(r, ap).playGame(multiModel.getDeck(), multiModel, 8, 4, false);
    assertEquals(multiModel.getGameState() + "\n" + "Invalid move. Try again." + "\n" + "Game " +
            "quit prematurely.", ap.toString());
  }

  @Test//test a move from an open pile
  public void testMove2Multi() {
    Reader r = new StringReader("O1 7 O1 Q");
    new FreecellController(r, ap).playGame(multiModel.getDeck(), multiModel, 8, 4, false);
    assertEquals(multiModel.getGameState() + "\n" + "Invalid move. Try again." + "\n" + "Game " +
            "quit prematurely.", ap.toString());
  }

  @Test//test a move to cascade
  public void testMove3Multi() {
    Reader r = new StringReader("C2 7 O1 Q");
    new FreecellController(r, ap).playGame(multiModel.getDeck(), multiModel, 8, 4, false);
    assertEquals(multiModel.getGameState() + "\n" + "Game " +
            "quit prematurely.", ap.substring(multiModel.getGameState().length() + 2));
  }

  @Test//test a move to foundation
  public void testMove4Multi() {
    Reader r = new StringReader("F1 7 C1 Q");
    new FreecellController(r, ap).playGame(multiModel.getDeck(), multiModel, 8, 4, false);
    assertEquals(multiModel.getGameState() + "\n" + "Invalid move. Try again." + "\n" + "Game " +
            "quit prematurely.", ap.toString());
  }

  @Test//test a move to an open pile
  public void testMove5Multi() {
    Reader r = new StringReader("O1 7 O1 Q");
    new FreecellController(r, ap).playGame(multiModel.getDeck(), multiModel, 8, 4, false);
    assertEquals(multiModel.getGameState() + "\n" + "Invalid move. Try again." + "\n" + "Game " +
            "quit prematurely.", ap.toString());
  }

  @Test//test a move to an open pile shuffle true
  public void testMoveWithShuffle6Multi() {
    Reader r = new StringReader("C1 7 O1 Q");
    new FreecellController(r, ap).playGame(multiModel.getDeck(), multiModel, 8, 4, true);
    assertEquals(multiModel.getGameState() + "\n" + "Game " +
            "quit prematurely.", ap.substring(multiModel.getGameState().length() + 2));
  }

  @Test//test a move to a full open pile
  public void testMoveWithFullOpenMulti() {
    Reader r = new StringReader("C1 7 O1 C1 6 O1 Q");
    new FreecellController(r, ap).playGame(multiModel.getDeck(), multiModel, 8, 4, false);
    assertEquals(multiModel.getGameState() + "\n" + "Invalid move. Try again." + "\n" + "Game " +
            "quit prematurely.", ap.substring(multiModel.getGameState().length() + 2));
  }

  @Test//test a move to a foundation pile that it can't move to
  public void testInvalidMoveToFoundationMulti() {
    Reader r = new StringReader("C1 7 F1 Q");
    new FreecellController(r, ap).playGame(multiModel.getDeck(), multiModel, 8, 4, false);
    assertEquals("Invalid move. Try again." + "\n" + "Game " +
            "quit prematurely.", ap.substring(multiModel.getGameState().length() + 1));
  }

  @Test//test a move to cascade pile that it can't move to
  public void testInvalidMoveToCascadeMulti() {
    Reader r = new StringReader("C1 7 C2 Q");
    new FreecellController(r, ap)
            .playGame(multiModel.getDeck(), multiModel, 4, 4, false);
    assertEquals("Invalid move. Try again." + "\n" + "Game " +
            "quit prematurely.", ap.substring(multiModel.getGameState().length() + 1));
  }

  @Test//test to see if the game actually ends when
  public void testGameOverMulti() {
    String f1 = "";
    String f2 = "";
    String f3 = "";
    String f4 = "";

    for (int i = 1; i < 14; i++) {
      f1 += "C" + Integer.toString(i) + " 1 " + "F1 ";
      f2 += "C" + Integer.toString(i + 13) + " 1 " + "F2 ";
      f3 += "C" + Integer.toString(i + 26) + " 1 " + "F3 ";
      f4 += "C" + Integer.toString(i + 39) + " 1 " + "F4 ";
    }

    Reader r = new StringReader(f1 + f2 + f3 + f4);
    new FreecellController(r, ap).playGame(multiModel.getDeck(), multiModel, 52, 4, false);
    assertEquals(true, multiModel.isGameOver());
    assertEquals("Game over.", ap.substring(ap.length() - 10));
  }

  @Test//test quitting halfway
  public void testQuittingHalfwayMulti() {
    Reader r = new StringReader("O1 Q O1");
    new FreecellController(r, ap).playGame(multiModel.getDeck(), multiModel, 52, 4, false);
    assertEquals("Game quit prematurely.", ap.substring(multiModel.getGameState().length() + 1));
  }

  @Test//test negative source num
  public void testNegativeSourceMulti() {
    Reader r = new StringReader("O0 O1 Q");
    new FreecellController(r, ap).playGame(multiModel.getDeck(), multiModel, 52, 4, false);
    assertEquals("Re-enter Source Type and Pile Number" + "\n" + "Game quit prematurely.",
            ap.substring(multiModel.getGameState().length() + 1));
  }

  @Test//test negative destnum
  public void testNegativeDestMulti() {
    Reader r = new StringReader("C1 7 O0 Q");
    new FreecellController(r, ap)
            .playGame(multiModel.getDeck(), multiModel, 52, 4, false);
    assertEquals("Re-enter Destination Type and Pile Number" + "\n" + "Game quit prematurely.",
            ap.substring(multiModel.getGameState().length() + 1));
  }

  @Test //Tests a move from a cascade pile to a cascade pile
  public void testFullMultiMove2() {
    Reader r = new StringReader("C2 1 O1 C15 1 C3 C3 1 C17 Q");
    new FreecellController(r, ap)
            .playGame(multiModel.getDeck(), multiModel, 52, 4, false);
    assertEquals(multiModel.getGameState() + "\n" + "Game " +
                    "quit prematurely.",
            ap.substring(ap.length() - multiModel.getGameState().length() - 23));
  }

  @Test //Tests the invalid move from Foundation to Open
  public void testMultiMove3() {
    Reader r = new StringReader("C1 1 F1 F1 1 O1 Q");
    new FreecellController(r, ap)
            .playGame(multiModel.getDeck(), multiModel, 52, 4, false);
    assertEquals("Invalid move. Try again." + "\n" + "Game " +
            "quit prematurely.", ap.substring(multiModel.getGameState().length() * 2 + 2));
  }

  @Test //Tests the invalid move from Foundation to Cascade
  public void testMultiMove4() {
    Reader r = new StringReader("C1 1 F1 F1 1 C1 Q");
    new FreecellController(r, ap)
            .playGame(multiModel.getDeck(), multiModel, 52, 4, false);
    assertEquals("Invalid move. Try again." + "\n" + "Game " +
            "quit prematurely.", ap.substring(multiModel.getGameState().length() * 2 + 2));
  }

  @Test //Tests the invalid move from Foundation to Foundation
  public void testMultiMove5() {
    Reader r = new StringReader("C1 1 F1 F1 1 F2 Q");
    new FreecellController(r, ap)
            .playGame(multiModel.getDeck(), multiModel, 52, 4, false);
    assertEquals("Invalid move. Try again." + "\n" + "Game " +
            "quit prematurely.", ap.substring(multiModel.getGameState().length() * 2 + 2));
  }

  @Test //Tests the invalid move from Cascade to Cascade
  // where the card is not the right color to be placed
  public void testMultiMove7() {
    Reader r = new StringReader("C2 1 O1 C15 1 C3 C3 1 C4 Q");
    new FreecellController(r, ap)
            .playGame(multiModel.getDeck(), multiModel, 52, 4, false);
    assertEquals("Invalid move. Try again." + "\n" + "Game " +
            "quit prematurely.", ap.substring(multiModel.getGameState().length() * 3 + 1));
  }

  @Test //Tests the invalid move from Cascade to Cascade
  //where the card being placed doesn't follow the cascade rules (less than prev card by 1)
  public void testMultiMove8() {
    Reader r = new StringReader("C2 1 O1 C15 1 C3 C3 1 C19 Q");
    new FreecellController(r, ap)
            .playGame(multiModel.getDeck(), multiModel, 52, 4, false);
    assertEquals("Invalid move. Try again." + "\n" + "Game " +
            "quit prematurely.", ap.substring(multiModel.getGameState().length() * 3 + 1));
  }

  @Test //tests a move where the build is invalid
  public void invalidBuild() {
    Reader r = new StringReader("C25 1 O1 C1 25 C1 Q");
    new FreecellController(r, ap)
            .playGame(multiModel.getDeck(), multiModel, 28, 4, false);
    assertEquals("Invalid move. Try again." + "\n" + "Game " +
            "quit prematurely.", ap.substring(multiModel.getGameState().length() * 2 + 2));
  }

  @Test //tests if fails when not enough open slots for multi move
  public void moveNotEnoughOpen() {
    Reader r = new StringReader("C25 1 O1 " +
            "C24 2 O2 C23 2 O3 C20 2 C23 C6 2 C23 C18 2 C23 C11 2 O4 C23 1 C10 Q");
    new FreecellController(r, ap)
            .playGame(multiModel.getDeck(), multiModel, 28, 4, false);
    assertEquals("Invalid move. Try again." + "\n" + "Game " +
            "quit prematurely.", ap.substring(multiModel.getGameState().length() * 8 + 20));
  }


}
