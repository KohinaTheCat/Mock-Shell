package mockObjects;

import helpers.FileSystem;
import helpers.Directory;
import helpers.File;

/**
 * Class Name: MockFileSystem This class is meant to resemble the actual
 * FileSystem for the purposes of testing
 */
public class MockFileSystem extends FileSystem {
  public String currPwd = "/";
  public Directory currDir = new Directory(currPwd);

  /**
   * Traverses to file system
   * 
   * @param String path of file/dir
   * @return Boolean value default true if path can be traversed to
   */
  public boolean traverse(String path) {
    if (path.equals("/dir1")) {
      currPwd = "/dir1/";
      return true;
    }

    if (path.equals("../../dir1")) {
      currPwd = "/dir1/";
      return true;
    }

    if (path.equals("dir1")) {
      currPwd = "/dir1/";
      return true;
    }

    if (path.equals(".")) {
      return true;
    }

    if (path.equals("..")) {
      currPwd = "/";
      return true;
    }

    // conditional case also used by malhar
    if (path.equals("/"))
      return true;

    if (path.equals("/dir2")) {
      currPwd = "/dir2/";
      return true;
    }

    return false;
  }

  /**
   * Gets the child file
   * 
   * @param String name of file
   * @return File child file of parent
   */
  public File getChildFile(String name) {
    if (name.equals("rootFile1"))
      return new File("rootFile1", "inside rootFile1");

    if (name.equals("rootFile2"))
      return new File("rootFile2", "inside rootFile2");

    if (name.equals("rootFile3"))
      return new File("rootFile3", "inside rootFile3");

    if (name.equals("dir1File1"))
      return new File("dir1File1", "inside dir1File1");

    return null;
  }

  /**
   * Gets current directory
   * 
   * @return Directory current directory
   */
  public Directory getDir() {
    return currDir;
  }

  /**
   * Sets current directory
   */
  public void setDir(Directory dir) {
    currDir = dir;
  }
}
