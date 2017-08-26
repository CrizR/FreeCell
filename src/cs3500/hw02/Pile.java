package cs3500.hw02;

import java.util.ArrayList;

/**
 * Created by ChrisRisley on 5/16/17.
 * Modified May 26, added additional javadoc descriptions.
 */

/**
 * Represents a Pile. A pile contains a PileType and an ArrayList of Cards.
 * It serves as a placeholder for each pile and provides extra methods for convenience.
 */
public class Pile {

  /**
   * Constructs a Pile.
   *
   * @param type  represents the PileType
   * @param cards represents the List of Cards within the Pile
   */

  public Pile(PileType type, ArrayList<Card> cards) {
    this.type = type;
    this.cards = cards;
  }

  private final PileType type;
  private final ArrayList<Card> cards;

  /**
   * Returns the ArrayList of cards within the Pile.
   * It does this by accessing the Cards field and returning it.
   *
   * @return ArrayList
   */
  public ArrayList<Card> getCards() {
    return this.cards;
  }

  /**
   * Determines the PileType of the card.
   * It does this by accessing the PileType field and returning it.
   *
   * @return PileType
   */
  public PileType getType() {
    return this.type;
  }

  /**
   * Adds the given card to the Cards field.
   *
   * @param c Represents a Card
   */
  public void addCard(Card c) {
    this.cards.add(c);
  }

  /**
   * Removes the given card from the Cards field.
   *
   * @param c Represents a Card
   */
  public void removeCard(Card c) {
    this.cards.remove(c);
  }


  /**
   * Helper method for move that helps determine if the move
   * is a valid move.
   *
   * @param sourcePile Represents the Source Pile
   * @param toMove     represents the Card to Move
   * @return a boolean that is determined by whether or not a move is valid.
   */
  public boolean getRules(Pile sourcePile, Card toMove) {
    boolean emptyDest = this.cards.isEmpty();

    if (sourcePile.getType() == PileType.FOUNDATION || (this.type == PileType.OPEN && !emptyDest)) {
      return false;
    }

    if (this.type == PileType.FOUNDATION) {
      if (!emptyDest) {
        Card destCard = this.cards.get(this.cards.size() - 1);
        boolean sameSuit = destCard.getSuit() == toMove.getSuit();
        boolean oneMore = destCard.getValue() + 1 == toMove.getValue();
        return sameSuit && oneMore;
      } else {
        boolean isAce = toMove.getValue() == Card.Value.ACE.ordinal();
        return isAce;
      }
    }

    if (this.type == PileType.CASCADE && !emptyDest) {
      Card destCard = this.cards.get(this.cards.size() - 1);
      boolean differentColor = !destCard.getColor().equals(toMove.getColor());
      boolean smallerVal = destCard.getValue() == toMove.getValue() + 1;
      return differentColor && smallerVal;
    }

    return true;

  }


  @Override
  public String toString() {
    StringBuilder res = new StringBuilder();
    for (int k = 0; k < this.getCards().size(); k++) {
      if (k == this.getCards().size() - 1) {
        res.append(" " + this.getCards().get(k));
      } else {
        res.append(" " + this.getCards().get(k) + ",");
      }
    }
    return res.toString();
  }
}
