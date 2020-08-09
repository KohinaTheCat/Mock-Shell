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

import helpers.FileSystem;
import helpers.StandardError;
import interfaces.CommandInterface;

/**
 * Class Name: Popd This class holds methods removes the directory from the
 * history stack, and changes working directory to it
 */
public class Popd implements CommandInterface {
  private static FileSystem fileSystem = FileSystem.getInstanceOfFileSystem();

  /**
   * Remove the top item of the history stack, then changes current working
   * directory to it.
   */
  private void runPopd() {
    String poppedDir = Pushd.directoryPathStack.pop();
    fileSystem.traverse(poppedDir);
  }

  /**
   * Checks if the history stack is empty. If it is not run the popd command.
   * Send errors to stdErr, and returns output of the command.
   *
   * @param arg Here in order to implement the hash map
   * @return String Output of the Popd command for stdOut
   */
  public String check(String[] arg) {
    if (Pushd.directoryPathStack.empty()) {
      StandardError.errors.add("popd: directory stack empty\n");
      return "";
    }
    runPopd();
    return "";
  }
}
