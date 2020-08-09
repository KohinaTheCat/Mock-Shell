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
import commands.Mv;
import commands.Rm;
import commands.Cp;
import commands.Mkdir;
import commands.Tree;
import helpers.ErrorCheck;
import helpers.File;
import helpers.FileSystem;
import helpers.Redirection;
import helpers.StandardError;

public class MvTest {
  Tree tree;
  Field field;
  FileSystem fs;
  ErrorCheck e;
  Mv mv;

  @Before
  public void setUp() throws Exception {
    mv = new Mv();
    tree = new Tree();
    fs = FileSystem.getInstanceOfFileSystem();

    /*
     * for tree
     */
    field = (Redirection.class.getDeclaredField("fileSystem"));
    field.setAccessible(true);
    field.set(fs, fs);

    /*
     * this is because Mv inherits filesystem from Cp
     */
    field = (Cp.class.getDeclaredField("fileSystem"));
    field.setAccessible(true);
    field.set(fs, fs);

    /*
     * this is because Mv uses Rm
     */
    field = (Rm.class.getDeclaredField("fileSystem"));
    field.setAccessible(true);
    field.set(fs, fs);

    /*
     * this is just b/c Cp uses Mkdir, so we reset it as well
     */
    field = (Mkdir.class.getDeclaredField("fileSystem"));
    field.setAccessible(true);
    field.set(fs, fs);
  }

  @After
  public void tearDown() throws Exception {
    field = (FileSystem.class.getDeclaredField("fs"));
    field.setAccessible(true);
    field.set(null, null);

    StandardError.errors.clear();
  }

  @Test
  public void testOne() {
    fs.addChild("a");
    fs.addChild("b");

    mv.check(new String[] {"mv", "b", "a"});

    assertEquals("/\n" + " a\n" + "  b",
        tree.check(new String[] {"tree"}).trim());
  }

  @Test
  public void testSubDirectories() {
    fs.addChild("a");
    fs.addChild("b");

    fs.traverse("a");

    fs.addChild("c");
    fs.addChild(new File("file", "content"));

    fs.traverse("/");
    mv.check(new String[] {"mv", "a", "b"});

    assertEquals("/\n" + " b\n" + "  a\n" + "   c\n" + "   file",
        tree.check(new String[] {"tree"}).trim());
  }

  @Test
  public void testCpParent() {
    fs.addChild("a");
    fs.addChild("b");

    fs.traverse("a");

    fs.addChild("c");
    fs.addChild(new File("file", "content"));

    fs.traverse("/");
    mv.check(new String[] {"mv", "/", "a"});

    assertEquals("/\n" + " a\n" + "  c\n" + "  file\n" + " b",
        tree.check(new String[] {"tree"}).trim());

    assertEquals("mv: Cannot move a directory into its child",
        StandardError.errors.get(0).trim());
  }

  @Test
  public void testOldExistNewPathDNE() {
    fs.addChild("a");
    fs.addChild("b");

    fs.traverse("a");

    fs.addChild("c");
    fs.addChild(new File("file", "content"));

    fs.traverse("/");
    mv.check(new String[] {"mv", "a", "/not/a/path"});

    assertEquals("/\n" + " a\n" + "  c\n" + "  file\n" + " b",
        tree.check(new String[] {"tree"}).trim());

    assertEquals("mv: Target path parent does not exist",
        StandardError.errors.get(0).trim());
  }

  @Test
  public void testOldDNENewExists() {
    fs.addChild("a");
    fs.addChild("b");

    fs.traverse("a");

    fs.addChild("c");
    fs.addChild(new File("file", "content"));

    fs.traverse("/");
    mv.check(new String[] {"mv", "/not/a/path", "a"});

    assertEquals("/\n" + " a\n" + "  c\n" + "  file\n" + " b",
        tree.check(new String[] {"tree"}).trim());

    assertEquals("mv: file/directory to be moved does not exist",
        StandardError.errors.get(0).trim());
  }

  @Test
  public void testOldExistsNewPathExistsNewFolder() {
    fs.addChild("a");
    fs.addChild("b");

    fs.traverse("a");

    fs.addChild("c");
    fs.addChild(new File("file", "content"));

    fs.traverse("/");
    mv.check(new String[] {"mv", "a", "b/new"});

    assertEquals("/\n" + " b\n" + "  new\n" + "   c\n" + "   file",
        tree.check(new String[] {"tree"}).trim());
  }

  @Test
  public void testOldExistsNewPathExistsFolderExists() {
    fs.addChild("a");
    fs.addChild("b");

    fs.traverse("a");

    fs.addChild("c");
    fs.addChild(new File("file1", "content"));

    fs.traverse("/");

    fs.traverse("b");

    fs.addChild("a");
    fs.addChild(new File("file2", "content"));

    fs.traverse("/");

    mv.check(new String[] {"mv", "a", "b"});

    assertEquals("/\n" + " b\n" + "  file2\n" + "  a\n" + "   c\n" + "   file1",
        tree.check(new String[] {"tree"}).trim());
  }

  @Test
  public void testMvRename() {
    fs.addChild("a");
    fs.addChild("b");

    fs.traverse("a");

    fs.addChild("c");
    fs.addChild(new File("file1", "content"));

    fs.traverse("/");

    fs.traverse("b");

    fs.addChild("a");
    fs.addChild(new File("file2", "content"));

    fs.traverse("/");

    mv.check(new String[] {"mv", "a", "newA"});

    assertEquals("/\n" + " b\n" + "  a\n" + "  file2\n" + " newA\n" + "  c\n"
        + "  file1", tree.check(new String[] {"tree"}).trim());
  }
}
