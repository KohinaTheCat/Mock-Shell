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
// UTORID user_name: 1006104430
// UT Student #: khans295
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

// BLOCKED
// 1) File Exists check
// 2) For testing, needs CAT and LS

package commands;

import helpers.Redirection;
import interfaces.CommandInterface;

/**
 * Class Name: Echo This class repeats the string to terminal
 */
public class Echo extends Redirection implements CommandInterface {

  // Parse the quotes
  private String runEcho(String output) {
    return output.substring(1, output.length() - 1);
  }

  /**
   * Checks if the arguments in arg are valid, then runs the command. Send
   * errors to stdErr, and returns output of the command.
   *
   * @param arg Array of arguments
   * @return String Output of the Echo command for stdOut
   */
  public String check(String[] arg) {
    if (!errorCheck.isProperString(arg[1])) {
      return "";
    }

    if (arg.length == 2) {
      return runEcho(arg[1]) + "\n";
    }

    return "";

  }
}
