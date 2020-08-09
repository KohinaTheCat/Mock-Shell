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
import commands.Echo;
import helpers.StandardError;

public class EchoTest {

  String output, expected;
  Echo echo;

  @Before
  public void setUp() throws Exception {
    echo = new Echo();

    output = "";
    expected = "";

    /*
     * The actual Echo command is just printing to terminal. Redirection is
     * testing in the RedirectionTes, and ParserTest
     */
  }

  @Test
  public void testEchoTerminal() {
    expected = "hello";
    output = echo.check(new String[] {"echo", "\"hello\""}).trim();
    assertEquals(expected, output);
  }

  @Test
  public void testEchoNoQuotes() {
    echo.check(new String[] {"echo", "hello"}).trim();
    expected = "Error: Strings need to be wrapped in double quotes\n";
    output = StandardError.errors.get(0);
    assertEquals(expected, output);
  }
}
