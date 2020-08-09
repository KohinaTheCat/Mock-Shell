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
import commands.Tree;
import helpers.File;
import helpers.FileSystem;
import helpers.Redirection;

public class TreeTest {

  Tree tree;
  FileSystem fs;
  Field field;

  @Before
  public void setUp() throws Exception {
    fs = FileSystem.getInstanceOfFileSystem();
    tree = new Tree();

    field = (Redirection.class.getDeclaredField("fileSystem"));
    field.setAccessible(true);
    field.set(fs, fs);
  }

  @After
  public void tearDown() throws Exception {
    field = (Redirection.class.getDeclaredField("fileSystem"));
    field.setAccessible(true);
    field.set(null, null);

    field = (FileSystem.class.getDeclaredField("fs"));
    field.setAccessible(true);
    field.set(null, null);
  }

  @Test
  public void testRoot() {
    assertEquals("/", tree.check(new String[] {}).trim());
  }

  @Test
  public void testDirectoriesSubDirectories() {
    fs.addChild("a");
    fs.addChild("b");
    fs.addChild("c");
    fs.addChild("d");
    fs.addChild("e");
    fs.addChild("f");
    fs.traverse("a");
    fs.addChild("a");
    fs.addChild("b");
    fs.addChild("c");
    fs.addChild("d");
    fs.addChild("e");
    fs.addChild("f");
    fs.traverse("a");
    fs.addChild("a");

    assertEquals(
        "/\n" + " a\n" + "  a\n" + "   a\n" + "  b\n" + "  c\n" + "  d\n"
            + "  e\n" + "  f\n" + " b\n" + " c\n" + " d\n" + " e\n" + " f",
        tree.check(new String[] {}).trim());
  }

  @Test
  public void testFilesAndDirectories() {
    fs.addChild("a");
    fs.addChild("b");
    fs.addChild("c");
    fs.addChild("d");
    fs.addChild("e");
    fs.addChild(new File("file", "content"));
    fs.addChild(new File("file2", "content"));
    fs.addChild(new File("file3", "content"));
    fs.addChild("f");
    fs.traverse("a");
    fs.addChild(new File("file", "content"));
    fs.addChild(new File("file2", "content"));
    fs.addChild("a");
    fs.addChild("b");
    fs.traverse("a");
    fs.addChild("a");
    fs.addChild(new File("file", "content"));

    assertEquals(
        "/\n" + " a\n" + "  file\n" + "  file2\n" + "  a\n" + "   a\n"
            + "   file\n" + "  b\n" + " b\n" + " c\n" + " d\n" + " e\n"
            + " file\n" + " file2\n" + " file3\n" + " f",
        tree.check(new String[] {}).trim());
  }

}
