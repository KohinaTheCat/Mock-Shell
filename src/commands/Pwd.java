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

package commands;

import helpers.Directory;
import helpers.Redirection;
import interfaces.CommandInterface;

/**
 * Class Name: Pwd This class holds methods to print the current directory
 */
public class Pwd extends Redirection implements CommandInterface {
  /**
   * Print full path of current directory by traversing up to the root and
   * storing parent names in a string with required format
   * 
   * @return String Full path of the current working directory
   */
  public String runPwd() {
    // Storing current directory because we need to traverse to the root
    Directory startDir = fileSystem.getDir();

    if (fileSystem.isRootDir()) {
      return "/";
    }

    // Creating string that will store the full path
    StringBuilder fullPath = new StringBuilder();

    // Go up till you hit the root
    while (!fileSystem.isRootDir()) {
      String current = fileSystem.getDir().getDirName();
      fullPath.insert(0, current + "/");
      fileSystem.cdParent();
    }

    // Adding root directory at the end
    fullPath.insert(0, "/");

    // Resetting current node
    fileSystem.setDir(startDir);

    // return the whole path
    return fullPath.substring(0, fullPath.length() - 1);
  }

  /**
   * Run the Pwd command. Returns output of the command.
   * 
   * @param arg Array of arguments
   * @return String Output of the Pwd command for stdOut
   */
  public String check(String[] arr) {
    return runPwd() + "\n";
  }
}
