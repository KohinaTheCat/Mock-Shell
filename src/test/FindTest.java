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
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import java.lang.reflect.Field;
import commands.Find;
import helpers.StandardError;
import helpers.Directory;
import helpers.File;
import helpers.Redirection;
import mockObjects.MockFileSystem;
import mockObjects.MockErrorCheck;

public class FindTest {

  Find find;
  MockFileSystem fs;
  MockErrorCheck errorCheck;
  Field field;

  @Before
  public void setUp() throws Exception {

    find = new Find();
    errorCheck = new MockErrorCheck();
    fs = new MockFileSystem();

    field = (Redirection.class.getDeclaredField("fileSystem"));
    field.setAccessible(true);
    field.set(fs, fs);

    field = (Redirection.class.getDeclaredField("errorCheck"));
    field.setAccessible(true);
    field.set(errorCheck, errorCheck);
  }

  @After
  public void tearDown() throws Exception {
    StandardError.errors.clear();
    fs.currDir = new Directory(fs.currPwd);
  }

  @Test
  public void testTypeArgument() {
    find.check(new String[] {"find", "/", "f", "-name", "\"abcd\""});
    assertEquals("find: '-type' argument is missing/in the wrong order\n",
        StandardError.errors.get(0));
    find.check(new String[] {"find", "/", "-name", "\"abcd\"", "-type", "f"});
    assertEquals("find: '-type' argument is missing/in the wrong order\n",
        StandardError.errors.get(0));
  }

  @Test
  public void testTypeSpecification() {
    find.check(new String[] {"find", "/", "-type", "a", "-name", "\"abcd\""});
    assertEquals("find: please specify type of search (f/d)\n",
        StandardError.errors.get(0));
    find.check(new String[] {"find", "/", "-type", "-name", "\"abcd\""});
    assertEquals("find: please specify type of search (f/d)\n",
        StandardError.errors.get(0));
  }

  @Test
  public void testNameArgument() {
    find.check(new String[] {"find", "/", "-type", "f", "d", "\"abcd\""});
    assertEquals("find: '-name' argument is missing/in the wrong order\n",
        StandardError.errors.get(0));
    find.check(new String[] {"find", "/", "-type", "d", "\"abcd\"", "-name"});
    assertEquals("find: '-name' argument is missing/in the wrong order\n",
        StandardError.errors.get(0));
  }

  @Test
  public void testNameSpecification() {
    find.check(new String[] {"find", "/", "-type", "f", "-name", "abcd"});
    assertEquals("Error: Strings need to be wrapped in double quotes\n",
        StandardError.errors.get(0));
    find.check(new String[] {"find", "/", "-type", "f", "-name", "\"ab\"cd\""});
    assertEquals("Error: Strings cannot contain double quotes within them\n",
        StandardError.errors.get(1));

  }

  @Test
  public void testNoPathsGiven() {
    find.check(new String[] {"find", "-type", "f", "-name", "\"abcd\""});
    assertEquals("find: no path(s) specified\n", StandardError.errors.get(0));
  }

  @Test
  public void testIncorrectPath() {
    find.check(
        new String[] {"find", "/dir", "-type", "f", "-name", "\"abcd\""});
    assertEquals("find: the path /dir does not exist\n",
        StandardError.errors.get(0));
  }

  @Test
  public void testEmptyDirectory() {

    String output = find
        .check(new String[] {"find", "/", "-type", "f", "-name", "\"abcd\""});
    assertEquals("/: \n", output);
  }

  @Test
  public void testFindFile() {
    fs.currDir.getChildren().add(new File("abcd", "hello!"));
    String output = find
        .check(new String[] {"find", "/", "-type", "f", "-name", "\"abcd\""});
    assertEquals("/: /abcd \n", output);
  }

  @Test
  public void testFindDirectory() {
    fs.currDir.getChildren().add(new Directory("abcd"));
    String output = find
        .check(new String[] {"find", "/", "-type", "d", "-name", "\"abcd\""});
    assertEquals("/: /abcd \n", output);
  }

}
