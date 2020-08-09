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
import commands.Mkdir;
import helpers.FileSystem;
import helpers.StandardError;

public class MkdirTest {


  Mkdir mkdir;
  FileSystem fs;
  Field field;

  @Before
  public void setUp() throws Exception {
    mkdir = new Mkdir();

    fs = FileSystem.getInstanceOfFileSystem();

    field = (Mkdir.class.getDeclaredField("fileSystem"));
    field.setAccessible(true);
    field.set(fs, fs);

  }

  @After
  public void tearDown() throws Exception {
    field = (FileSystem.class.getDeclaredField("fs"));
    field.setAccessible(true);
    field.set(null, null); // setting the ref parameter to null
    StandardError.errors.clear();
  }

  @Test
  public void testCreateDirectory() {
    mkdir.check(new String[] {"mkdir", "dir"});
    // check if working directory remains the same
    assertEquals(true, fs.isRootDir());
    // check if directory was created
    assertEquals(true, fs.traverse("dir"));
  }

  @Test
  public void testMultipleInput() {
    mkdir.check(new String[] {"mkdir", "dir", "/dir/dir1", "dir/dir1/dir2"});
    assertEquals(true, fs.traverse("dir/dir1/dir2"));
  }

  @Test
  public void testCreateDuplicateDirectories() {
    mkdir.check(new String[] {"mkdir", "dir"});
    mkdir.check(new String[] {"mkdir", "dir", "dir1", "dir/ko", "dir/ko/bop"});
    assertEquals(StandardError.errors.get(0), "Error: 'dir' already exists\n");
  }

  @Test
  public void testCreateDirectoryWithInvaalidParent() {
    mkdir.check(new String[] {"mkdir", "dir/dir1"});
    assertEquals(StandardError.errors.get(0),
        "Error: Invalid path: the parent directory dir does not exist\n");
  }

  @Test
  public void testInvalidCharacter() {
    mkdir.check(new String[] {"mkdir", "dir@"});
    assertEquals(StandardError.errors.get(0),
        "Error: Invalid character(s): A file/directory cannot have @ in it's name\n");
  }


}
