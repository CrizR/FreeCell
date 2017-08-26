package cs3500.hw04;

/**
 * Created by ChrisRisley on 5/28/17.
 */

import org.junit.Test;

import cs3500.hw02.FreecellModel;

import static junit.framework.TestCase.assertEquals;

/**
 * Class for testing the FreecellModelCreator.
 */
public class TestFreecellModelCreator {
  FreecellModelCreator model;


  @Test
  //Tests the create method to makes sure it creates a Model of type multi
  public void testCreateMulti() {
    assertEquals(true,
            (model.create(FreecellModelCreator.GameType.MULTIMOVE)
                    instanceof FreecellModelMultiMove));
  }

  @Test
  //Tests to the create method to make sure it creates a Model of type single
  public void testCreateSingle() {
    assertEquals(true, (
            model.create(FreecellModelCreator.GameType.SINGLEMOVE)
                    instanceof FreecellModel));
  }


  @Test(expected = IllegalArgumentException.class)
  //Tests the create method with a null GameType
  public void testCreateNull() {
    assertEquals(true, (
            model.create(null)
                    instanceof FreecellModel));
  }

}
