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
import commands.History;
import commands.Load;
import commands.Ls;
import commands.Pushd;
import commands.Pwd;
import helpers.Parser;
import helpers.StandardError;

public class LoadTest {
  String output, expected;

  Load load;
  Pwd pwd;
  Ls ls;
  String[] inputLoad = {"load", ""};

  @Before
  public void setUp() throws Exception {
    ls = new Ls();
    pwd = new Pwd();
    load = new Load();
    output = "";
    expected = "";
    inputLoad[1] = "Assignment2/src/mockObjects/MockSaveData.ser";
  }

  @After
  public void tearDown() throws Exception {
    StandardError.errors.clear();
  }

  @Test
  public void testIncorrectFilePath() {
    Parser.canUseLoad = true;
    inputLoad[1] = "DoesntExist.ser";
    load.check(inputLoad).trim();
    expected = "Error: File Not Found\n";
    assertEquals(expected, StandardError.errors.get(0));
  }

  @Test
  public void testHistoryStack() {
    load.check(inputLoad).trim();
    int out = History.historyList.size();
    int expect = 0;
    assertEquals(expect, out);
  }

  @Test
  public void testCurrDir() {
    load.check(inputLoad).trim();
    output = pwd.runPwd();
    expected = "/";
    assertEquals(expected, output);
  }

  @Test
  public void testPushdStack() {
    load.check(inputLoad).trim();
    output = Pushd.directoryPathStack.pop();
    expected = "/";
    assertEquals(expected, output);
  }

  @Test
  public void testFileSystem() {
    load.check(inputLoad).trim();
    output = ls.check(new String[] {"ls", "-R"});
    expected = "/: a b\n\n/a: aa\n\n/a/aa: hi\n\n/b: bb\n\n/b/bb: bye";
    assertEquals(expected, output.trim());
  }

  @Test
  public void testLoadDisabled() {
    Parser.canUseLoad = false;
    inputLoad[1] = "Assignment2/src/mockObjects/MockSaveData.ser";
    load.check(inputLoad).trim();
    expected = "Error: Load can only be called if it is the first command\n";
    assertEquals(expected, StandardError.errors.get(0));
  }
}
