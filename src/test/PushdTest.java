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
import commands.Pushd;
import helpers.FileSystem;
import helpers.StandardError;
import mockObjects.MockErrorCheck;
import mockObjects.MockFileSystem;
import mockObjects.MockPwd;

public class PushdTest {
  String output, expected;

  Pushd pushd;
  MockFileSystem file;
  MockErrorCheck error;
  MockPwd pwd;
  Field field;

  @Before
  public void setUp() throws Exception {
    error = new MockErrorCheck();
    file = new MockFileSystem();
    pushd = new Pushd(new MockPwd(file));
    output = "";
    expected = "";

    field = (FileSystem.class.getDeclaredField("fs"));
    field.setAccessible(true);
    field.set(file, file);

    field = (Pushd.class.getDeclaredField("fileSystem"));
    field.setAccessible(true);
    field.set(file, file);

    field = (Pushd.class.getDeclaredField("errorCheck"));
    field.setAccessible(true);
    field.set(error, error);
  }

  @After
  public void tearDown() throws Exception {
    Pushd.directoryPathStack.clear();
    StandardError.errors.clear();
  }

  @Test
  public void testNonExistingDir() {
    pushd.check(new String[] {"pushd", "invalidDir"});
    assertEquals(
        "Error: Invalid path: the given path invalidDir does not exist\n",
        StandardError.errors.get(0));
    assertEquals("/", file.currPwd);
  }

  @Test
  public void testExistingDir() {
    pushd.check(new String[] {"pushd", "dir1"});
    assertEquals(0, StandardError.errors.size());
    assertEquals("/", Pushd.directoryPathStack.peek());
    assertEquals("/dir1/", file.currPwd);
  }

  @Test
  public void testMultiple() {
    pushd.check(new String[] {"pushd", "dir1"});
    assertEquals(0, StandardError.errors.size());
    assertEquals("/", Pushd.directoryPathStack.peek());
    assertEquals("/dir1/", file.currPwd);
    pushd.check(new String[] {"pushd", ".."});
    assertEquals(0, StandardError.errors.size());
    assertEquals("/dir1/", Pushd.directoryPathStack.peek());
    assertEquals("/", file.currPwd);
  }


}
