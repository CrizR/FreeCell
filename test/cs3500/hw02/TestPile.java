package cs3500.hw02;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


/**
 * Created by ChrisRisley on 5/16/17.
 */

/**
 * Class for testing the Pile class.
 */
public class TestPile {
  FreecellModel fcm = new FreecellModel();
  ArrayList<String> validDeck = new ArrayList<>(Arrays.asList(
          "A♣", "2♣", "3♣", "4♣", "5♣", "6♣", "7♣", "8♣", "9♣", "10♣", "J♣", "Q♣", "K♣",
          "A♦", "2♦", "3♦", "4♦", "5♦", "6♦", "7♦", "8♦", "9♦", "10♦", "J♦", "Q♦", "K♦",
          "A♥", "2♥", "3♥", "4♥", "5♥", "6♥", "7♥", "8♥", "9♥", "10♥", "J♥", "Q♥", "K♥",
          "A♠", "2♠", "3♠", "4♠", "5♠", "6♠", "7♠", "8♠", "9♠", "10♠", "J♠", "Q♠", "K♠"));

  Pile pile1 = new Pile(PileType.OPEN, (ArrayList<Card>) fcm.getDeck());
  Card card1 = new Card(Card.Value.ACE, Card.Suit.SPADES);

  @Test //Tests to see if the getCards method returns the cards in the class
  public void testGetCards() {
    assertEquals(validDeck, toSArray(pile1.getCards()));
  }

  @Test //Tests to see if addCard and removeCard actually add and remove a card
  public void testAddAndRemove() {
    pile1.addCard(card1);
    pile1.removeCard(card1);
    assertEquals(validDeck, toSArray(pile1.getCards()));
  }

  @Test //Tests to see if getType actually returns the type of the pile
  public void testGetType() {
    assertEquals(PileType.OPEN, pile1.getType());
  }

  @Test //Tests to see if getRules returns whether or not the move to that destination follows
  //the rules
  public void testGetRules() {
    assertEquals(false, pile1.getRules(pile1, card1));
  }

  @Test //Tests to see if ToString actually prints out the full pile of cards as a String
  public void testToString() {
    assertEquals(validDeck.toString(), pile1.getCards().toString());
  }

  private List<String> toSArray(List<Card> arr) {
    ArrayList<String> res = new ArrayList<>();
    for (Card c : arr) {
      res.add(c.toString());
    }
    return res;
  }
}
