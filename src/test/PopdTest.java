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
import commands.Popd;
import commands.Pushd;
import helpers.FileSystem;
import helpers.StandardError;
import mockObjects.MockFileSystem;

public class PopdTest {

  Popd popd;
  String expected, output;
  MockFileSystem file;
  Field field;

  @Before
  public void setUp() throws Exception {
    popd = new Popd();
    file = new MockFileSystem();
    output = "";
    expected = "";

    field = (FileSystem.class.getDeclaredField("fs"));
    field.setAccessible(true);
    field.set(file, file);

    field = (Popd.class.getDeclaredField("fileSystem"));
    field.setAccessible(true);
    field.set(file, file);

    /*
     * note:
     * 
     * Since stacks are built in, we can assume that the creators checked that
     * it works correctly
     */
  }

  @After
  public void tearDown() throws Exception {
    Pushd.directoryPathStack.clear();
    StandardError.errors.clear();
  }

  @Test
  public void testStackPopEmpty() {
    popd.check(new String[] {"popd"});
    // check error message
    assertEquals("popd: directory stack empty\n", StandardError.errors.get(0));
  }

  @Test
  public void testOneItemOnePop() {
    Pushd.directoryPathStack.push("dir1");
    popd.check(new String[] {"popd"});
    // check size of stack
    assertEquals(0, StandardError.errors.size());
    // check if directory changed
    assertEquals("/dir1/", file.currPwd);
  }

  @Test
  public void testMultiplePops() {
    Pushd.directoryPathStack.push("dir1");
    Pushd.directoryPathStack.push("/");
    Pushd.directoryPathStack.push("dir2");
    popd.check(new String[] {"popd"});
    popd.check(new String[] {"popd"});
    popd.check(new String[] {"popd"});
    // check size of stack
    assertEquals(0, StandardError.errors.size());
    // check if directory changed
    assertEquals("/dir1/", file.currPwd);
  }

}
