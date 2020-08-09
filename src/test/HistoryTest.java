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

public class HistoryTest {
  String output, expected;

  History hist;
  String[] inputHistory;

  @Before
  public void setUp() throws Exception {

    output = "";
    expected = "";

    hist = new History();
  }

  @After
  public void tearDown() throws Exception {
    History.historyList.clear();
  }

  @Test
  public void testOneCommand() {
    History.historyList.add("cat file1");

    output = hist.check(new String[] {"history"}).trim();

    expected = "1. cat file1";

    assertEquals(expected, output);
  }

  @Test
  public void testTwoCommandRequestHundred() {
    History.historyList.add("cat file1");
    History.historyList.add("cat file2");

    output = hist.check(new String[] {"history", "100"}).trim();

    expected = "1. cat file1\n2. cat file2";

    assertEquals(expected, output);
  }

  @Test
  public void testThreeCommandRequestTwo() {
    History.historyList.add("cat file1");
    History.historyList.add("cat file2");
    History.historyList.add("cat file3");

    output = hist.check(new String[] {"history", "2"}).trim();

    expected = "2. cat file2\n3. cat file3";

    assertEquals(expected, output);
  }

  @Test
  public void testThreeCommandRequestNegative() {
    History.historyList.add("cat file1");
    History.historyList.add("cat file2");
    History.historyList.add("cat file3");

    output = hist.check(new String[] {"history", "-1"});

    expected = ""; // CHECK IF THIS IS RIGHT

    assertEquals(expected, output);
  }

  @Test
  public void testThreeCommandRequestNotNumber() {
    History.historyList.add("cat file1");
    History.historyList.add("cat file2");
    History.historyList.add("cat file3");

    output = hist.check(new String[] {"history", "okok"}).trim();

    expected = "";

    // ErrorCheck stack is tested in ErrorCheck

    assertEquals(expected, output);
  }

}
