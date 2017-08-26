package cs3500.hw02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by ChrisRisley on 5/12/17.
 * Modified on May 24: Added check for null deck.
 * Changed validIndices to not allow for ints that are less than 0. Also made sure
 * that the last card is the only one allowed to move.
 */

/**
 * FreecellModel implements the FreecellOperations class and the methods getDeck, startGame,
 * move, isGameOver, and getGameState. It handles the three types of Piles by representing each
 * pile with an ArrayList of Pile. It differentiates from the interface in that only one card
 * can be moved instead of multiple. It is parametrized over the Card type Card
 */
public class FreecellModel implements FreecellOperations<Card> {

  protected final ArrayList<Pile> openPiles;
  protected final ArrayList<Pile> cascadePiles;
  protected final ArrayList<Pile> foundationPiles;
  private int totalPiles;

  /**
   * Constructs a Freecell Model by initializing the three ArrayList of Pile and the total pile num.
   */
  public FreecellModel() {
    this.openPiles = new ArrayList<>();
    this.cascadePiles = new ArrayList<>();
    this.foundationPiles = new ArrayList<>();
    this.totalPiles = 0;
  }

  @Override
  public List<Card> getDeck() {
    Card.Suit[] suits = Card.Suit.values();
    Card.Value[] values = Card.Value.values();

    ArrayList<Card> fullDeck = new ArrayList<>();
    for (Card.Suit suit : suits) {
      for (Card.Value value : values) {
        fullDeck.add(new Card(value, suit));
      }
    }
    return fullDeck;
  }

  @Override
  public void startGame(List deck, int numCascadePiles,
                        int numOpenPiles, boolean shuffle) throws IllegalArgumentException {
    if (validDeck(deck)) {
      if (numCascadePiles >= 4 && numOpenPiles > 0) {

        if (shuffle) {
          Collections.shuffle(deck);
        }

        //Initialize the Piles
        initializePiles(numOpenPiles, numCascadePiles);

        //Distribute the cards in round robin
        distributeCards(deck);

        totalPiles = openPiles.size() + cascadePiles.size() + foundationPiles.size();
      } else {
        throw new IllegalArgumentException("Invalid Params");
      }
    } else {
      throw new IllegalArgumentException("Invalid Deck");
    }
  }

  //Distributes the Cards in Round Robin Fashion
  private void distributeCards(List deck) {
    int i = 0;
    for (Card c : (ArrayList<Card>) deck) {
      if (i == cascadePiles.size()) {
        i = 0;
      }
      cascadePiles.get(i).addCard(c);
      i++;
    }
  }

  //Initializes the Card Piles
  private void initializePiles(int numOpenPiles, int numCascadePiles) {
    //Initialize Foundation
    foundationPiles.clear();
    for (int i = 0; i < 4; i++) {
      foundationPiles.add(new Pile(PileType.FOUNDATION, new ArrayList<>()));
    }

    //Initialize Open
    openPiles.clear();
    for (int i = 0; i < numOpenPiles; i++) {
      openPiles.add(new Pile(PileType.OPEN, new ArrayList<>()));
    }

    //Initialize Cascade
    cascadePiles.clear();
    for (int i = 0; i < numCascadePiles; i++) {
      cascadePiles.add(new Pile(PileType.CASCADE, new ArrayList<>()));
    }
  }


  //Determines if the given deck is valid.
  private boolean validDeck(List deck) {
    if (deck == null) {
      throw new IllegalArgumentException("Null Deck");
    }
    ArrayList<String> x = new ArrayList<>(Arrays.asList(
            "2♣", "3♣", "4♣", "5♣", "6♣", "7♣", "8♣", "9♣", "10♣", "J♣", "Q♣", "K♣", "A♣",
            "2♦", "3♦", "4♦", "5♦", "6♦", "7♦", "8♦", "9♦", "10♦", "J♦", "Q♦", "K♦", "A♦",
            "2♥", "3♥", "4♥", "5♥", "6♥", "7♥", "8♥", "9♥", "10♥", "J♥", "Q♥", "K♥", "A♥",
            "2♠", "3♠", "4♠", "5♠", "6♠", "7♠", "8♠", "9♠", "10♠", "J♠", "Q♠", "K♠", "A♠"));
    List<String> comp = toSArray(deck);
    return comp.containsAll(x) && comp.size() == x.size();
  }

  //Converts an List of Cards to a List of Strings.
  private List<String> toSArray(List<Card> arr) {
    ArrayList<String> res = new ArrayList<>();
    for (Card c : arr) {
      res.add(c.toString());
    }
    return res;
  }

  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination,
                   int destPileNumber) throws IllegalArgumentException {

    if (validIndices(getPileType(source), getPileType(destination), pileNumber, destPileNumber)
            && cardExists(source, pileNumber, cardIndex)) {

      Pile sourcePile = getPileType(source).get(pileNumber);
      Pile destPile = getPileType(destination).get(destPileNumber);

      Card cardToMove = sourcePile.getCards().get(cardIndex);
      sourcePile.removeCard(cardToMove);

      if (validMove(sourcePile, destPile, cardToMove)) {
        destPile.addCard(cardToMove);

      } else {
        sourcePile.addCard(cardToMove);
        throw new IllegalArgumentException("Invalid Move");
      }

    } else {
      throw new IllegalArgumentException("Invalid Index");
    }
  }

  //Determines if the given card exists in the pile
  boolean cardExists(PileType type, int pileNumber, int cardIndex) {
    return getPileType(type).get(pileNumber).getCards().size() - 1 == cardIndex;
  }

  //Determines what PileType to retrieve
  protected ArrayList<Pile> getPileType(PileType type) {
    if (type == PileType.CASCADE) {
      return cascadePiles;
    } else if (type == PileType.OPEN) {
      return openPiles;
    } else {
      return foundationPiles;
    }
  }

  //Checks if it's a valid index
  protected boolean validIndices(ArrayList<Pile> source, ArrayList<Pile> destination,
                                 int pileNumber, int destPileNumber) {
    return pileNumber < source.size() && destPileNumber < destination.size() && pileNumber >= 0
            && destPileNumber >= 0;
  }

  //Determines if the Move is a Valid Move
  protected boolean validMove(Pile sourcePile, Pile destPile, Card toMove) {
    return destPile.getRules(sourcePile, toMove);
  }

  @Override
  public boolean isGameOver() {
    int fPileNum = 0;
    for (int i = 0; i < foundationPiles.size(); i++) {
      fPileNum += foundationPiles.get(i).getCards().size();
    }

    return (fPileNum == 52);
  }


  @Override
  public String getGameState() {
    StringBuilder res = new StringBuilder();
    if (totalPiles > 0) {
      //Appends Foundation Piles
      for (int i = 0; i < 4; i++) {
        if (i == 0) {
          res.append("F" + (i + 1) + ":");
        } else {
          res.append("\n" + "F" + (i + 1) + ":");
        }
        res.append(foundationPiles.get(i).toString());
      }

      //Appends Open Piles
      for (int i = 0; i < openPiles.size(); i++) {
        res.append("\n" + "O" + (i + 1) + ":");
        res.append(openPiles.get(i).toString());
      }

      //Appends Cascade Piles
      for (int i = 0; i < cascadePiles.size(); i++) {
        res.append("\n" + "C" + (i + 1) + ":");
        res.append(cascadePiles.get(i).toString());
      }
    }
    return res.toString();
  }


}
