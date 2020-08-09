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
import helpers.ErrorCheck;
import helpers.File;
import helpers.FileSystem;
import helpers.StandardError;

public class ErrorCheckTest {

  private static ErrorCheck error;
  String output, expected;
  Boolean out, exp;
  FileSystem tree;

  @Before
  public void setUp() throws Exception {
    error = new ErrorCheck();
    tree = FileSystem.getInstanceOfFileSystem();
    output = "";
    expected = "";
  }

  @After
  public void tearDown() throws Exception {
    StandardError.errors.clear();
  }

  @Test
  public void testParsedInput() {
    // test one command with only argument count 0
    out = error.numArgCheck(new String[] {"history"});
    expected = "";
    assertEquals(expected, output);
  }

  @Test
  public void testParsedInput2Arg() {
    // test one command with only 2 arguments
    out = error.numArgCheck(new String[] {"echo", "second"});
    expected = "";
    assertEquals(expected, output);
  }

  @Test
  public void testParsedInputIncorrectArg() {
    // test command incorrect number of arguments
    out = error.numArgCheck(new String[] {"echo", "\"ok\"", "not"});
    exp = false;
    assertEquals(expected, output);
  }

  @Test
  public void testParsedInputCommandDontExist() {
    // test command doesn't exist
    out = error.numArgCheck(new String[] {"kohina", "\"ok\"", "not"});
    exp = false;
    assertEquals(exp, out);
  }

  @Test
  public void testNoInvalidChar() {
    // no invalid char
    out = error.invalidChar("qwertyuiop");
    exp = true;
    assertEquals(expected, output);
  }

  @Test
  public void testOneInvalidChar() {
    // one invalid char
    out = error.invalidChar("qwertyuiop@");
    exp = false;
    assertEquals(expected, output);
    assertEquals(
        "Error: Invalid character(s): A file/directory cannot have @ in it's name\n",
        StandardError.errors.get(0));

    StandardError.errors.clear(); // we know this works b/c "we can assume
                                  // Oracle
                                  // did their testing"
  }

  @Test
  public void testManyInvalidChar() {
    // many invalid char
    out = error.invalidChar("@#$%^&*(");
    exp = true;
    assertEquals(exp, out);
    assertEquals(
        "Error: Invalid character(s): A file/directory cannot have @, #, $, %, "
            + "^, &, *, ( in it's name\n",
        StandardError.errors.get(0));
  }

  @Test
  public void testIsProperStringQuotesInside() {
    // contains quote inside
    out =
        error.isProperString("\"qwertyuiopoiuytrewqwerty\"wertyuiopoiuytre\"");
    exp = false;
    assertEquals(exp, out);
    assertEquals("Error: Strings cannot contain double quotes within them\n",
        StandardError.errors.get(0));
    StandardError.errors.clear();
  }

  @Test
  public void testIsProperStringNoQuotes() {
    // no quotes
    out = error.isProperString("apple");
    exp = false;
    assertEquals(exp, out);
    assertEquals("Error: Strings need to be wrapped in double quotes\n",
        StandardError.errors.get(0));
    StandardError.errors.clear();
  }

  @Test
  public void testIsProperStringOneQuote() {
    // one quote
    out = error.isProperString("\"apple");
    exp = false;
    assertEquals(exp, out);
    assertEquals("Error: Strings need to be wrapped in double quotes\n",
        StandardError.errors.get(0));
    StandardError.errors.clear();
  }

  @Test
  public void testIsProperStringCorrectQuotes() {
    // correct quote
    out = error.isProperString("\"apple\"");
    exp = true;
    expected = "";
    assertEquals(exp, out);
    assertEquals(0, StandardError.errors.size());
  }

  @Test
  public void testDirExistsAbsolute() {
    tree.addChild("a");

    // directory doesn't exist absolute
    out = error.dirExists(tree, "/b");
    exp = false;
    assertEquals(exp, out);
  }

  @Test
  public void testDirDoesntExistLocalPath() {
    tree.addChild("a");
    // directory doesn't exist local path
    out = error.dirExists(tree, "b");
    exp = false;
    assertEquals(exp, out);
  }

  @Test
  public void testDirAbsolutePath() {
    tree.addChild("a");
    // directory exists absolute path
    out = error.dirExists(tree, "/a");
    exp = true;
    assertEquals(exp, out);
  }

  @Test
  public void testDirExistsLocalPath() {
    tree.addChild("a");
    // directory exists local path
    out = error.dirExists(tree, "a");
    exp = true;
    assertEquals(exp, out);
  }

  @Test
  public void testFileDoesntExistAbsolute() {
    File file = new File("b", "hello");
    tree.addChild(file);

    // file doesn't exist absolute
    out = error.fileExists(tree, "/a");
    exp = false;
    assertEquals(exp, out);
  }

  @Test
  public void testFileDoesntExistLocalPath() {
    File file = new File("b", "hello");
    tree.addChild(file);
    // file doesn't exist local path
    out = error.fileExists(tree, "a");
    exp = false;
    assertEquals(exp, out);
  }

  @Test
  public void testFileExistsAbsolutePath() {
    File file = new File("b", "hello");
    tree.addChild(file);
    // file exists absolute path
    out = error.fileExists(tree, "/b");
    exp = true;
    assertEquals(exp, out);
  }

  @Test
  public void testFileExistsLocalPath() {
    File file = new File("b", "hello");
    tree.addChild(file);
    // file exists local path
    out = error.fileExists(tree, "b");
    exp = true;
    assertEquals(exp, out);
  }

  @Test
  public void testParentPathDoesntExist() {
    tree.addChild("a");

    // parent in the path doesn't exist
    out = error.parentExists(tree, "/c/d");
    exp = false;
    assertEquals(exp, out);
  }

  @Test
  public void testParentPathExists() {
    tree.addChild("a");
    // parent in the path exists
    out = error.parentExists(tree, "/a/b");
    exp = true;
    assertEquals(exp, out);
  }

  @Test
  public void testGetNameNoBackslash() {
    // no backslash
    output = error.getName("a");
    expected = "a";
    assertEquals(expected, output);
  }

  @Test
  public void testGetNameBackslashAtStart() {
    // backslash at start only
    output = error.getName("/a");
    expected = "a";
    assertEquals(expected, output);
  }

  @Test
  public void testGetNameMultipleBackslashes() {
    // multiple backslashes
    output = error.getName("/a/b");
    expected = "b";
    assertEquals(expected, output);
  }

  @Test
  public void testGetPathNoBackslash() {
    // no backslash
    output = error.getPath("a");
    expected = "/";
    assertEquals(expected, output);
  }

  @Test
  public void testGetPathBackslashAtStart() {
    // backslash at start only
    output = error.getPath("/a");
    expected = "/";
    assertEquals(expected, output);
  }

  @Test
  public void testGetPathMultipleBackslashes() {
    // multiple backslashes
    output = error.getPath("/a/b");
    expected = "/a";
    assertEquals(expected, output);
  }
}
