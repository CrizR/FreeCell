package cs3500.hw02;

/**
 * Created by ChrisRisley on 5/12/17.
 */

/**
 * Represents a Card as it is defined by two fields: Value and Suit.
 */
public class Card {

  /**
   * Represents the value of a card. The different values are described below.
   */
  public enum Value {
    ACE("A"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5"),
    SIX("6"), SEVEN("7"), EIGHT("8"), NINE("9"),
    TEN("10"), JACK("J"), QUEEN("Q"), KING("K");

    private String val;

    /**
     * Constructs a Value which can be an ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE
     * TEN, JACK, QUEEN, or KING.
     */
    Value(String val) {
      this.val = val;
    }

    @Override
    public String toString() {
      return this.val;
    }
  }

  /**
   * Represents the Suit of a Card. The different values are described below.
   */
  public enum Suit {
    CLUBS("♣"), DIAMONDS("♦"), HEARTS("♥"), SPADES("♠");

    private String shape;

    /**
     * Constructs a Suit which can be a Clubs, Diamond, Hearts, or Spades.
     */
    Suit(String shape) {
      this.shape = shape;
    }

    @Override
    public String toString() {
      return this.shape;
    }
  }

  private final Value value;
  private final Suit suit;


  /**
   * Constructs a Card.
   *
   * @param Value the value of the card.
   * @param Suit  the suit of the card.
   */


  /**
   * Represents the Value of a Card Ace through King and Clubs through Spades.
   */
  public Card(Value value, Suit suit) {
    this.value = value;
    this.suit = suit;
  }

  @Override
  public String toString() {
    return this.value.toString() + this.suit.toString();
  }

  /**
   * The Suit value of the card. For example, a A♣ card would return ♣.
   *
   * @return the Suit value of the card.
   */
  public Suit getSuit() {
    return this.suit;
  }

  /**
   * Returns the value of the card.
   *
   * @return the value of the card as defined by its ordinal.
   */
  public int getValue() {
    return this.value.ordinal();
  }

  /**
   * Gets the color of the Card.
   * If it's a Spade or Diamonds it's Black, otherwise it's Red.
   *
   * @return the Color of the Card which can be Black or Red.
   */
  public String getColor() {
    if (this.suit.ordinal() == Suit.SPADES.ordinal()
            || this.suit.ordinal() == Suit.CLUBS.ordinal()) {
      return "Black";
    } else {
      return "Red";
    }
  }


}



