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
import helpers.FileSystem;
import helpers.Parser;
import helpers.StandardError;

public class ParserTest {

  Parser p;
  java.io.ByteArrayOutputStream out;
  FileSystem fs;

  @Before
  public void setUp() throws Exception {
    p = new Parser();
    out = new java.io.ByteArrayOutputStream();
    System.setOut(new java.io.PrintStream(out));
    fs = FileSystem.getInstanceOfFileSystem();

    /*
     * only parseCommand() is public, and it calls on private methods
     */
  }

  @After
  public void tearDown() throws Exception {
    History.historyList.clear();
    StandardError.errors.clear();
  }

  @Test
  public void testCallWrongCommand() {
    p.parseCommand("sometimes i wonder");
    assertEquals("sometimes i wonder", History.historyList.get(0));
    assertEquals("Error: Invalid command: command 'sometimes' does not exist",
        out.toString().trim());
  }

  @Test
  public void testCorrectCommandWrongArgs() {
    p.parseCommand("pwd saddendend");
    assertEquals("pwd saddendend", History.historyList.get(0));
    assertEquals("Error: Invalid arguments: please type 'man pwd' to know more",
        out.toString().trim());
  }

  @Test
  public void testCorrectCommandCallOutput() {
    p.parseCommand("pwd");
    assertEquals("pwd", History.historyList.get(0));
    assertEquals("/", out.toString().trim());
  }

  @Test
  public void testCorrectCommandCallRedirection() {
    p.parseCommand("pwd > file1.txt");
    assertEquals("pwd > file1.txt", History.historyList.get(0));
    assertEquals("", out.toString().trim());
    assertEquals("/", fs.getChildFile("file1.txt").getFileContent());
  }

}
