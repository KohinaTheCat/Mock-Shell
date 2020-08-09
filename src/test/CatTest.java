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
import org.junit.Before;
import org.junit.Test;
import commands.Cat;
import helpers.Redirection;
import mockObjects.MockFileSystem;
import mockObjects.MockErrorCheck;

public class CatTest {
  String output, expected;

  Cat cat;
  MockFileSystem file;
  MockErrorCheck error;
  String[] inputCat;

  @Before
  public void setUp() throws Exception {
    cat = new Cat();
    error = new MockErrorCheck();
    file = new MockFileSystem();
    output = "";
    expected = "";

    /*
     * b/c Cat inherits fileSystem from Redirection.
     * 
     * set Cat's fileSystem and errorCheck to the mock fs and errorCheck
     */

    Field field = (Redirection.class.getDeclaredField("fileSystem"));
    field.setAccessible(true);
    field.set(file, file);

    field = (Redirection.class.getDeclaredField("errorCheck"));
    field.setAccessible(true);
    field.set(error, error);
  }

  @Test
  public void testNoFile() {
    // Cat file that doesn't exist
    String[] inputCat = {"cat", "notAFile"};
    output = cat.check(inputCat).trim();
    expected = "";
    assertEquals(expected, output);
  }

  @Test
  public void testOneFile() {
    // Cat a file that does exist
    String[] inputCat = {"cat", "rootFile1"};
    output = cat.check(inputCat).trim();

    expected = "inside rootFile1";
    assertEquals(expected, output);
  }

  @Test
  public void testMultipleValidFiles() {
    // Cat a file that does exist
    String[] inputCat = {"cat", "rootFile1", "rootFile2"};
    output = cat.check(inputCat).trim();
    expected = "inside rootFile1\n\ninside rootFile2";
    assertEquals(expected, output);
  }

  @Test
  public void testMultipleMixedFiles() {
    // Cat a file that does exist
    String[] inputCat = {"cat", "rootFile2", "notAFile", "rootFile3"};
    output = cat.check(inputCat).trim();

    expected = "inside rootFile2\n\ninside rootFile3";
    assertEquals(expected, output);
  }

  @Test
  public void testMultipleMixedFilesWithFullPath() {
    // Cat a file that does exist
    String[] inputCat =
        {"cat", "/rootFile2", "/notAFile", "/rootFile3", "/dir1/dir1File1"};
    output = cat.check(inputCat).trim();

    expected = "inside rootFile2\n\ninside rootFile3\n\ninside dir1File1";
    assertEquals(expected, output);
  }

}
