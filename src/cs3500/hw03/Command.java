package cs3500.hw03;

/**
 * Created by ChrisRisley on 5/22/17.
 */

import cs3500.hw02.FreecellOperations;
import cs3500.hw02.PileType;

/**
 * Represents a Command that signals a move in FreecellOperations.
 * It has five fields: a source, source pile number, card index, destination, dest pile number.
 */
public class Command {
  private PileType source;
  private int sPileNum;
  private int cardIndex;
  private PileType dest;
  private int dPileNum;


  /**
   * Builds a command by setting each field to either null or -1 depending on its type.
   */
  public Command() {
    source = null;
    sPileNum = -1;
    cardIndex = -1;
    dest = null;
    dPileNum = -1;
  }

  /**
   * Builds a command by using the given arguments.
   *
   * @param source    represents the source
   * @param sPileNum  represents the source pile num
   * @param cardIndex represents the card index
   * @param dest      represents the destination
   * @param dPileNum  represents the destination pile num
   */
  public Command(PileType source, int sPileNum, int cardIndex, PileType dest, int dPileNum) {
    if (sPileNum < 1 || cardIndex < 1 || dPileNum < 1) {
      throw new IllegalArgumentException("Must be Greater than 0");
    }
    this.source = source;
    this.sPileNum = sPileNum;
    this.cardIndex = cardIndex;
    this.dest = dest;
    this.dPileNum = dPileNum;
  }

  /**
   * Gets the source piletype from the object.
   * Mainly used for testing.
   *
   * @return the piletype of the source in the Object.
   */
  public PileType getSource() {
    return this.source;
  }

  /**
   * Gets the source number from the Object.
   * Mainly used for testing.
   *
   * @return the integer value of the source number.
   */
  public int getSourceNum() {
    return this.sPileNum;
  }

  /**
   * Gets the Card Index from the Object.
   * Mainly used for testing.
   *
   * @return the card index from the Command object
   */
  public int getCardIndex() {
    return this.cardIndex;
  }

  /**
   * Gets the destination piletype from the Object.
   * Mainly used for testing.
   *
   * @return the destination piletype of the Command object.
   */
  public PileType getDest() {
    return this.dest;
  }

  /**
   * Gets the destination pile number
   * Mainly used for testing.
   *
   * @return the destination pile number from the Command Object
   */
  public int getdDestNum() {
    return this.dPileNum;
  }

  /**
   * Sets the commands source pile.
   *
   * @param source represents the source to set the command to.
   * @return the command itself so that you can method chain.
   */
  public Command setSource(PileType source) {
    this.source = source;
    return this;
  }

  /**
   * Sets the command's source pile number.
   *
   * @param sPileNum represents the given pile number
   * @return the command itself so that you can method chain.
   */
  public Command setSourceNum(int sPileNum) {
    this.sPileNum = sPileNum - 1;
    return this;
  }

  /**
   * Sets the command's card index.
   *
   * @param cardIndex represents the Card Index to set the command to.
   * @return the command itself so that you can method chain.
   */
  public Command setCardIndex(int cardIndex) {
    this.cardIndex = cardIndex - 1;
    return this;
  }

  /**
   * Sets the command's destination pile.
   *
   * @param dest represents the new dest PileType to set the command to.
   * @return the command itself so that you can method chain.
   */
  public Command setDest(PileType dest) {
    this.dest = dest;
    return this;
  }

  /**
   * Sets the command's destination pile num.
   *
   * @param dPileNum represent the new Pile Num to set the command to.
   * @return the command itself so that you can method chain.
   */
  public Command setDestNum(int dPileNum) {
    this.dPileNum = dPileNum - 1;
    return this;
  }

  /**
   * Determines if the command is a valid command.
   *
   * @return a boolean that determines if the Command is a full command.
   */
  public boolean validCommand() {
    return source != null
            && sPileNum >= 0
            && cardIndex >= 0
            && dest != null
            && dPileNum >= 0;
  }

  /**
   * Determines the size of the command.
   *
   * @return the size of the command by looking at how many fields have been set.
   */
  public int size() {
    int size = 0;
    if (source != null) {
      size++;
    }
    if (sPileNum >= 0) {
      size++;
    }
    if (cardIndex >= 0) {
      size++;
    }
    if (dest != null) {
      size++;
    }
    if (dPileNum >= 0) {
      size++;
    }
    return size;
  }

  /**
   * Makes a move given the appropriate FreecellOperations.
   *
   * @param model represents the FreecellOperations model to use
   */
  public void move(FreecellOperations model) {
    model.move(this.source, this.sPileNum, this.cardIndex, this.dest, this.dPileNum);
  }


}
