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

import java.util.Stack;
import helpers.ErrorCheck;
import helpers.FileSystem;
import helpers.StandardError;
import interfaces.CommandInterface;

/**
 * Class Name: Pushd This class adds the current working directory to the
 * history stack and then changes the working directory to a specified path
 */
public class Pushd implements CommandInterface {
  /**
   * Creates a directory stack
   */
  public static Stack<String> directoryPathStack = new Stack<>();
  private static FileSystem fileSystem = FileSystem.getInstanceOfFileSystem();
  private static ErrorCheck errorCheck = new ErrorCheck();
  Pwd pwd;

  // Constructors
  // for injection testing purposes only
  public Pushd(Pwd pwd) {
    this.pwd = pwd;
  }

  // normally
  public Pushd() {
    this.pwd = new Pwd();
  }

  /**
   * Adds the current working directory to the history stack and changes the
   * current working directory to path
   * 
   * @param path
   */
  private void runPushd(String path) {
    directoryPathStack.push(pwd.runPwd());
    fileSystem.traverse(path);
  }

  /**
   * Checks if arg[1] is a path to a directory. Run the command, send errors to
   * stdErr, and returns output of the command.
   *
   * @param arg Determines the path
   * @return String Output of the Pushd command for stdOut
   */
  public String check(String[] arg) {
    String path = arg[1];

    if (!errorCheck.dirExists(fileSystem, path)) {
      StandardError.errors.add(
          "Error: Invalid path: the given path " + path + " does not exist\n");
      return "";
    }
    runPushd(path);
    return "";
  }
}
