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
import java.lang.reflect.Field;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import commands.Cd;
import helpers.StandardError;
import mockObjects.MockErrorCheck;
import mockObjects.MockFileSystem;

public class CdTest {
  String output, expected;

  Cd cd;
  Field field;
  MockFileSystem file;
  MockErrorCheck error;

  @Before
  public void setUp() throws Exception {
    cd = new Cd();
    file = new MockFileSystem();
    error = new MockErrorCheck();

    /*
     * Use the mock fileSystem and errorCheck
     */

    field = (Cd.class.getDeclaredField("fileSystem"));
    field.setAccessible(true);
    field.set(file, file);

    field = (Cd.class.getDeclaredField("errorCheck"));
    field.setAccessible(true);
    field.set(error, error);
  }

  @After
  public void tearDown() throws Exception {
    StandardError.errors.clear();
  }

  @Test
  public void testCurrDir() {
    cd.check(new String[] {"cd", "doesntExist"});
    output = file.currPwd;
    expected = "/";
    assertEquals(expected, output);
  }

  @Test
  public void testDoesntExist() {
    cd.check(new String[] {"cd", "doesntExist"});
    output = file.currPwd;
    expected =
        "Error: Invalid directory: the directory doesntExist does not exist\n";
    assertEquals(expected, StandardError.errors.get(0));
  }

  @Test
  public void testToRoot() {
    cd.check(new String[] {"cd", "/"});
    output = file.currPwd;
    expected = "/";
    assertEquals(expected, output);
  }

  @Test
  public void testNormal() {
    cd.check(new String[] {"cd", "dir1"});
    output = file.currPwd;
    expected = "/dir1/";
    assertEquals(expected, output);
  }

  @Test
  public void testSimpleRootPath() {
    cd.check(new String[] {"cd", "/dir1"});
    output = file.currPwd;
    expected = "/dir1/";
    assertEquals(expected, output);
  }

  @Test
  public void testCdUp() {
    cd.check(new String[] {"cd", "dir1"});
    cd.check(new String[] {"cd", ".."});
    output = file.currPwd;
    expected = "/";
    assertEquals(expected, output);
  }

  @Test
  public void testContinousSlashes() {
    String inputCd[] = {"cd", "//////"};
    cd.check(inputCd);
    expected =
        "Error: Invalid directory: the directory ////// does not exist\n";
    assertEquals(expected, StandardError.errors.get(0));
  }

  @Test
  public void testCdUpDown() {
    cd.check(new String[] {"cd", "../../dir1"});
    output = file.currPwd;
    expected = "/dir1/";
    assertEquals(expected, output);
  }
}
