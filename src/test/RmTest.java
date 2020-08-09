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
import commands.Rm;
import helpers.FileSystem;
import helpers.StandardError;

public class RmTest {


  Rm rm;
  FileSystem file;
  Field field;

  @Before
  public void setUp() throws Exception {
    rm = new Rm();

    file = FileSystem.getInstanceOfFileSystem();

    field = (Rm.class.getDeclaredField("fileSystem"));
    field.setAccessible(true);
    field.set(file, file);

  }

  @After
  public void tearDown() throws Exception {
    field = (FileSystem.class.getDeclaredField("fs"));
    field.setAccessible(true);
    field.set(null, null); // setting the ref parameter to null
    StandardError.errors.clear();
  }

  @Test
  public void testRmDirectory() {
    file.addChild("dir1");
    rm.check(new String[] {"rm", "dir"});
    // check if working directory remains the same
    assertEquals(true, file.isRootDir());
    // check if directory was created
    assertEquals(false, file.traverse("dir"));
  }

  @Test
  public void testRemoveRootDir() {
    rm.check(new String[] {"rm", "/"});
    assertEquals("Error: Can't remove the root directory\n",
        StandardError.errors.get(0));
  }

  @Test
  public void testCreateDirectoryWithInvalidParent() {
    rm.check(new String[] {"rm", "dir/dir1"});
    assertEquals("Error: Invalid directory: the directory dir/dir1 does not "
        + "exist\n", StandardError.errors.get(0));
  }

  @Test
  public void testNonexistentDir() {
    file.addChild("dir2");
    rm.check(new String[] {"rm", "dir1"});
    assertEquals(
        "Error: Invalid directory: the directory dir1 does not " + "exist\n",
        StandardError.errors.get(0));
  }
}
