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
import mockObjects.MockRemoteData;
import commands.Curl;
import helpers.FileSystem;

public class CurlTest {
  MockRemoteData mock;
  Curl curl, mockCurl;
  String expected, output;

  @Before
  public void setUp() throws Exception {
    mock = new MockRemoteData();
    mockCurl = new Curl(mock);
    curl = new Curl();
    output = "";
    expected = "";
  }

  @Test
  public void testGetHTMLMockURL() {
    output = mock.getHTMLFromURL("wwww.thisissosad.com/sadder.txt");
    expected = "Success!";
    assertEquals(expected, output);
  }

  @Test
  public void testGetHTMLRealURL() {
    output =
        curl.getHTMLFromURL("http://www.cs.utoronto.ca/~trebla/CSCB09-2020-"
            + "Summer/l03/iron-man-spoiler.txt");
    expected = "Veba Zna vf Gbal Fgnex.";
    assertEquals(expected, output);
  }

  @Test
  public void testRealInvalidURL() {
    output = mockCurl.getHTMLFromURL("wwww.notawebsitelmao.com/sadder.txt");
    expected = null;
    assertEquals(expected, output);
  }

  @Test
  public void testIfFileIsCreatedRealFileSystem() {
    FileSystem fs = FileSystem.getInstanceOfFileSystem();
    curl.check(new String[] {"curl",
        "http://www.cs.utoronto.ca/~trebla/CSCB09-2020-Summer/l03/"
            + "iron-man-spoiler.txt"});

    assertEquals("Veba Zna vf Gbal Fgnex.",
        fs.getChildFile("iron-man-spoiler.txt").getFileContent());
  }

  @Test
  public void testIfFileIsCreatedMockFileSystem() {
    FileSystem fs = FileSystem.getInstanceOfFileSystem();
    mockCurl.check(new String[] {"curl", "wwww.thisissosad.com/sadder.txt"});

    assertEquals("Success!", fs.getChildFile("sadder.txt").getFileContent());
  }
}
