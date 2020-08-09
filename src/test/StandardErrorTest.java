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
import org.junit.Before;
import org.junit.Test;
import helpers.StandardError;

public class StandardErrorTest {

  // read what is printed to the console
  java.io.ByteArrayOutputStream out;

  @Before
  public void setUp() throws Exception {
    out = new java.io.ByteArrayOutputStream();
    System.setOut(new java.io.PrintStream(out));
  }


  @Test
  public void testErrorsStack() {
    // we can assume that the built-in stacks have been tested

    StandardError.errors.add("error1");
    assertEquals(1, StandardError.errors.size());
    assertEquals("error1", StandardError.errors.get(0));

    StandardError.errors.add("error2");
    assertEquals(2, StandardError.errors.size());
    assertEquals("error2", StandardError.errors.get(1));
  }

  @Test
  public void testPrintErrors() {
    // we can assume that the built-in stacks have been tested

    StandardError.errors.add("error1");
    StandardError.errors.add("error2");
    StandardError.printErrors();
    // check if errors are not printed on newlines
    assertEquals("error1error2", out.toString());
    // check if list is reset
    assertEquals(0, StandardError.errors.size());
  }

}
