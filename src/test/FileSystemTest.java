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
import org.junit.Before;
import org.junit.Test;
import helpers.Directory;
import helpers.File;
import helpers.FileSystem;

public class FileSystemTest {
  FileSystem tree;
  Field field;

  @Before
  public void setUp() throws Exception {
    tree = FileSystem.getInstanceOfFileSystem();
  }

  @Test
  public void testSingleton() {
    FileSystem fileTemp = FileSystem.getInstanceOfFileSystem();
    assertEquals(fileTemp, tree);
  }

  @Test
  public void testAddChildGetChildDir() {
    // child doesn't exist
    Directory dir = tree.getChildDir("a");
    // child exists but is a file
    tree.addChild(new File("b", "hello"));
    dir = tree.getChildDir("b");
    assertEquals(dir, null);
  }

  @Test
  public void testAddChildGetChildDirChildExists() {
    // child doesn't exist
    Directory dir = tree.getChildDir("a");
    // child exists but is a file
    tree.addChild(new File("b", "hello"));
    dir = tree.getChildDir("b");
    // child exists
    tree.addChild("c");
    dir = tree.getChildDir("c");
    assertEquals(dir.getDirName(), "c");
  }


  @Test
  public void testAddChildGetChildFile() {
    // does not exist
    File file = tree.getChildFile("d");
    assertEquals(file, null);
  }

  @Test
  public void testAddChildGetChildFileChildExists() {
    // does not exist
    File file = tree.getChildFile("d");
    // child exists, but is a directory
    tree.addChild("c");
    file = tree.getChildFile("c");
    // child exists
    tree.addChild(new File("b", "hello"));
    file = tree.getChildFile("b");
    assertEquals(file.getFileName(), "b");
  }

  @Test
  public void testTraverseRoot() {
    // path is root directory
    Boolean exists = tree.traverse("/");
    assertEquals(true, exists);
  }

  @Test
  public void testTraverseAbsolutePath() {
    // path is absolute
    Boolean exists = tree.traverse("/");
    tree.addChild("c");
    exists = tree.traverse("/c");
    assertEquals(true, exists);
  }

  @Test
  public void testTraverseRelativePath() {
    // path is relative
    // reset to root
    Boolean exists = tree.traverse("/");
    tree.traverse("/");
    exists = tree.traverse("c");
    assertEquals(true, exists);
  }

  @Test
  public void testTraversePathExists() {
    Boolean exists = tree.traverse("/");
    tree.addChild("c");
    tree.traverse("c");
    // path exists
    tree.addChild("d");
    // reset to root
    tree.traverse("/");
    exists = tree.traverse("c/d");
    assertEquals(true, exists);
  }

  @Test
  public void testTravsersePathDoesntExist() {
    // doesnt exist
    Boolean exists = tree.traverse("/");
    exists = tree.traverse("/c/d/a");
    assertEquals(false, exists);
  }

  @Test
  public void testCdParent() {
    tree.addChild("c");
    // current directory is root
    Directory dir = tree.getChildDir("c");
    assertEquals("c", dir.getDirName());
    // b/c the root directory is the only directory with the child c
  }

  @Test
  public void testCdParentCurrentDirectoryNotRoot() {
    tree.addChild("c");
    // current directory is root
    Directory dir = tree.getChildDir("c");

    // current directory is not root
    tree.traverse("/c"); // tested in last testcase

    tree.cdParent();
    dir = tree.getChildDir("c");
    assertEquals("c", dir.getDirName());
    // b/c the root directory is the only directory with the child c
  }

  @Test
  public void testIsRoot() {
    // this is tested in testTraverse
    tree.addChild("c");
    tree.traverse("/c");
    assertEquals(false, tree.isRootDir());
  }

}
