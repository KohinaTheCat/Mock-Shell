// **********************************************************
// Assignment2:
// Student1: Clara Chick
// UTORID user_name: chickcla
// UT Student #: 1005946764
// Author: Clara Chick
//
// Student2: Malhar Pandya
// UTORID user_name: pandyam8
// UT Student #: 1005893008
// Author: Malhar Pandya
//
// Student3: Sameer Khan
// UTORID user_name: khans295
// UT Student #: 1006104430
// Author: Sameer Khan
//
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************

package test;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import commands.Load;
import commands.Ls;
import commands.Save;
import helpers.StandardError;
import java.io.File;

public class SaveTest {
  String output, expected;

  Save save;
  Load load;
  Ls ls;
  String[] inputSave = {"save", ""};

  @Before
  public void setUp() throws Exception {
    ls = new Ls();
    save = new Save();
    load = new Load();
    output = "";
    expected = "";
  }

  @After
  public void tearDown() throws Exception {
    StandardError.errors.clear();
  }

  @Test
  public void testIncorrectFilePath() {
    inputSave[1] = "Assignmnet2/src/apple/DoesntExist.ser";
    save.check(inputSave).trim();
    expected = "Error: Invalid Path\n";
    assertEquals(expected, StandardError.errors.get(0));
  }

  @Test
  public void testDifferentFileExtension() {
    inputSave[1] = "Assignmnet2/src/mockObjects/MockSaveData.txt";
    save.check(inputSave).trim();
    boolean expect = false;
    File temp = new File("Assignmnet2/src/mockObjects/MockSaveData.txt");
    boolean out = temp.exists();
    assertEquals(expect, out);
  }
}
