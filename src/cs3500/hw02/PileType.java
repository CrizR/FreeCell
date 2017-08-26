package cs3500.hw02;

/**
 * Type for the three types of piles in a game of Freecell. <br>
 *
 * <p>Open: This pile can hold only one card. It is like a buffer to temporarily
 * hold cards <br>
 * Cascade: This is a pile of face-up cards. A build within a cascade
 * pile is a subset of cards that has monotonically decreasing values and suits
 * of alternating colors<br>
 * Foundation: Initially empty, there are 4 foundation
 * piles, one for each suit. Each foundation pile collects cards in increasing
 * order of value for one suit (Ace being the lowest). <br></p>
 *
 * <p>The goal of the game is to fill up all the foundation piles</p>
 */
public enum PileType {
  OPEN, CASCADE, FOUNDATION
}

