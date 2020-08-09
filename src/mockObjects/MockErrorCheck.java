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
package mockObjects;

import helpers.ErrorCheck;
import helpers.FileSystem;
import helpers.StandardError;

/**
 * Class Name: MockErrorcheck This class is a mock object to resemble the
 * ErrorCheck class
 */
public class MockErrorCheck extends ErrorCheck {

  /**
   * Returns the full path based on the given path
   * 
   * @param String path
   * @return String path
   */
  public String getPath(String path) {
    if (path.equals("/../../dir1"))
      return "/dir1";

    if (path.equals("dir1/"))
      return "/dir1";

    if (path.equals("dir2/"))
      return "/dir2";

    if (path.equals("rootFile1") || path.equals("rootFile2")
        || path.equals("rootFile3"))
      return "/";

    if (path.equals("/rootFile1") || path.equals("/rootFile2")
        || path.equals("/rootFile3") || path.equals("/dir1/dir1File1"))
      return "/";

    if (path.contains("/"))
      return "";

    return "/";
  }

  /**
   * Checks if parent exists of the path
   * 
   * @param FileSystem Instance of fileSystem
   * @param String path
   * @return Boolean value of parentExists
   */
  public boolean parentExists(FileSystem fs, String path) {
    if (path.startsWith("/dir1"))
      return true;

    if (path.startsWith("/dir2"))
      return true;

    if (path.contains("/"))
      return false;

    return true;
  }

  /**
   * Checks if file exists
   * 
   * @param String path of file
   * @return Boolean value of fileExists
   */
  public boolean fileExists(String path) {
    if (path.equals("/dir1/dir1File1"))
      return true;

    if (path.equals("/rootFile1") || path.equals("/rootFile2")
        || path.equals("/rootFile3"))
      return true;

    return false;
  }

  /**
   * Checks if directory exists
   * 
   * @param FileSystem Instance of fileSystem
   * @param String path
   * @return Boolean value of dirExists
   */
  public boolean dirExists(FileSystem fs, String path) {
    if (path.equals("."))
      return true;

    if (path.equals(".."))
      return true;

    if (path.equals("dir1"))
      return true;

    if (path.equals("/dir1"))
      return true;

    if (path.equals("dir2"))
      return true;

    if (path.equals("/dir2"))
      return true;

    if (path.endsWith("/dir1"))
      return true;

    // Malhar's test cases
    if (path.equals("/"))
      return true;

    return false;
  }

  /**
   * Checks if the input is a proper string
   * 
   * @param String input string
   * @return Boolean value if input is valid string
   */
  public boolean isProperString(String input) {
    if (input.equals("\"ab\"cd\"")) {
      StandardError.errors
          .add("Error: Strings cannot contain double quotes within them\n");
      return false;
    }
    if (input.equals("abcd")) {
      StandardError.errors
          .add("Error: Strings need to be wrapped in double quotes\n");
      return false;
    }
    return true;
  }
}
