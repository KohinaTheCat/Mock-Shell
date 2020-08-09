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
import helpers.FileSystem;
import helpers.Redirection;
import helpers.StandardError;

public class RedirectionTest {

  Redirection r;
  FileSystem fs;
  Field field;

  @Before
  public void setUp() throws Exception {
    r = new Redirection();
    fs = FileSystem.getInstanceOfFileSystem();

    field = (Redirection.class.getDeclaredField("fileSystem"));
    field.setAccessible(true);
    field.set(fs, fs);
  }

  @After
  public void tearDown() throws Exception {
    StandardError.errors.clear();

    field = (Redirection.class.getDeclaredField("fileSystem"));
    field.setAccessible(true);
    field.set(null, null);

    field = (FileSystem.class.getDeclaredField("fs"));
    field.setAccessible(true);
    field.set(null, null);
  }

  @Test
  public void testCheckErrors() {
    r.append("notvalid/path/lolz", "stuff");

    assertEquals(
        "Error: Invalid path: the directory notvalid/path/lolz does not exist\n",
        StandardError.errors.get(0));
  }

  @Test
  public void testCheckErrorsDirName() {
    fs.addChild("dir1");
    r.append("dir1", "stuff");

    assertEquals("Error: a file and a directory cannot have duplicate names\n",
        StandardError.errors.get(0));
  }

  @Test
  public void testCreate() {
    r.append("newFile", "stuff");
    assertEquals(0, StandardError.errors.size());
    assertEquals("stuff", fs.getChildFile("newFile").getFileContent());
  }

  @Test
  public void testAppend() {
    // this will create b/c newFile does not exist
    r.append("newFile", "stuff");
    // this will actually append b/c newFile now exists
    r.append("newFile", "overwritten hehhee");
    assertEquals(0, StandardError.errors.size());
    assertEquals("stuff\noverwritten hehhee",
        fs.getChildFile("newFile").getFileContent());
  }

  @Test
  public void testOverwrite() {
    // this will create b/c newFile does not exist
    r.append("newFile", "stuff");
    // this will actually overwrite b/c newFile now exists
    r.overwrite("newFile", "overwritten hehhee");
    assertEquals(0, StandardError.errors.size());
    assertEquals("overwritten hehhee",
        fs.getChildFile("newFile").getFileContent());
  }

  @Test
  public void testToPath() {
    fs.addChild("dir");
    r.append("dir/file", "inside dir");
    assertEquals(0, StandardError.errors.size());

    // go inside directory dir
    fs.traverse("dir");
    // check if file is made inside dir
    assertEquals("inside dir", fs.getChildFile("file").getFileContent());
  }
}
