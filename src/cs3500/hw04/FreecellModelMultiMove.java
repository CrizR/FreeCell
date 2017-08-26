package cs3500.hw04;

import java.util.ArrayList;
import java.util.List;

import cs3500.hw02.Card;
import cs3500.hw02.FreecellModel;
import cs3500.hw02.Pile;
import cs3500.hw02.PileType;

/**
 * Created by ChrisRisley on 5/28/17.
 */

/**
 * Class that represents an implementation of FreecellOperations that allows
 * for multiple cards to be moved at a time. It extends the FreecellModel and
 * it overrides the move method to allow for this multi-move.
 */
public class FreecellModelMultiMove extends FreecellModel {

  @Override
  public void move(PileType source, int sourcePN, int cI, PileType dest, int destPN) {

    //checks if the arguments given are valid
    checkIndices(source, sourcePN, cI, dest, destPN);

    //references the correct piles
    Pile sourcePile = super.getPileType(source).get(sourcePN);
    Pile destPile = super.getPileType(dest).get(destPN);

    //create copies of the piles and cards
    Pile sourceCopy = new Pile(source,
            (ArrayList<Card>) super.getPileType(source).get(sourcePN).getCards().clone());
    Pile destCopy = new Pile(dest,
            (ArrayList<Card>) super.getPileType(dest).get(destPN).getCards().clone());
    List<Card> cardsToMove = new ArrayList<>((sourceCopy.getCards()
            .subList(cI, sourceCopy.getCards().size())));

    //determine the number of available moves
    int maxMoves = maxMoves();

    //checks to see if it's a valid move
    checkMultiMove(sourceCopy, destCopy, cardsToMove, maxMoves);
    //actually make the move
    makeMove(sourcePile, destPile, cardsToMove);
  }

  //determine the number of available moves
  private int maxMoves() {
    int k = getNumOpen(PileType.CASCADE);
    int n = getNumOpen(PileType.OPEN);
    return (int) Math.pow(2, k) * (n + 1);
  }

  //checks if the arguments given are valid
  private void checkIndices(PileType source, int sourcePN, int cI, PileType dest, int destPN) {
    if (!super.validIndices(super.getPileType(source), super.getPileType(dest), sourcePN, destPN)
            && cardExists(source, sourcePN, cI)) {
      throw new IllegalArgumentException("Invalid Index");
    }
  }

  //makes the move by removing the cards from the source pile and adding them to the destination
  private void makeMove(Pile sourcePile, Pile destPile, List<Card> cardsToMove) {
    //makes the actual move
    for (Card c : cardsToMove) {
      sourcePile.removeCard(c);
      destPile.addCard(c);
    }
  }

  //returns the number of open piles in the cascade and open piles
  private int getNumOpen(PileType type) {
    int total = 0;
    ArrayList<Pile> piles = new ArrayList<>();
    if (type == PileType.CASCADE) {
      piles = super.getPileType(PileType.CASCADE);
    } else if (type == PileType.OPEN) {
      piles = super.getPileType(PileType.OPEN);
    }
    for (Pile p : piles) {
      if (p.getCards().size() == 0) {
        total++;
      }
    }

    return total;
  }

  //Checks to see if each card of the pile of cards to move can on its own be moved
  //to the destination pile without it breaking any of the pile rules.
  private void checkMultiMove(Pile sourcePile, Pile destPile, List<Card> cTM, int maxMoves) {
    if (maxMoves < cTM.size() && cTM.size() > 1) {
      throw new IllegalArgumentException("Invalid Move");
    } else if (destPile.getType() == PileType.OPEN && cTM.size() > 1) {
      throw new IllegalArgumentException("Invalid Move");
    } else {
      for (Card c : cTM) {
        sourcePile.removeCard(c);
        if (destPile.getRules(sourcePile, c)) {
          destPile.addCard(c);
        } else {
          throw new IllegalArgumentException("Invalid Move");
        }
      }
    }
  }

  //Determines if the cardIndex is a valid number given the amount of cards in the pile.
  private boolean cardExists(PileType type, int pileNumber, int cardIndex) {
    return getPileType(type).get(pileNumber).getCards().size() - 1 >= cardIndex;
  }

}

