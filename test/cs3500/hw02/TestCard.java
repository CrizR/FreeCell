package cs3500.hw02;
/**
 * Created by ChrisRisley on 5/12/17.
 */

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Class for testing the Card Class.
 */
public class TestCard {


  Card card1 = new Card(Card.Value.ACE, Card.Suit.SPADES);

  @Test //Tests to see if ToString properly prints a card to the right String
  public void testToString() {
    assertEquals("A♠", card1.toString());
  }

  @Test //Tests to see if getValue actually returns the Card's value
  public void testGetValue() {
    assertEquals(0, card1.getValue());
  }

  @Test //Tests to see if getColor actually returns the Card's Color
  public void testGetColor() {
    assertEquals("Black", card1.getColor());
  }

  @Test //Tests to see if getSuit actually returns the Card's Suit
  public void testGetSuit() {
    assertEquals(Card.Suit.SPADES, card1.getSuit());
  }
}
