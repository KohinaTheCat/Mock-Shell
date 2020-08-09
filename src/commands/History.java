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

import java.util.ArrayList;
import helpers.StandardError;
import helpers.Redirection;
import interfaces.CommandInterface;

/**
 * Class Name: History This class handles the stack of previous commands
 */
public class History extends Redirection implements CommandInterface {
  /**
   * Creates an array list of used commands
   */
  public static ArrayList<String> historyList = new ArrayList<>();

  /**
   * Outputs the list of previous commands in the history stack
   */
  private String runHistory() {
    String output = "";
    for (int i = 0; i < historyList.size(); i++) {
      output += (i + 1) + ". " + historyList.get(i) + "\n";
    }

    return output;
  }

  /**
   * Outputs the n-last commands to print from the history stack
   * 
   * @param n Determines the n-last commands
   */
  private String runHistory(int n) {
    String output = "";
    if (n <= historyList.size()) {

      for (int i = historyList.size() - n; i < historyList.size(); i++) {
        output += (i + 1) + ". " + historyList.get(i) + "\n";
      }

    } else {
      return runHistory();
    }

    return output;
  }

  /**
   * Checks if there are any arguments, or if the arguments are valid, then
   * calls the correct methods, and returns the output of the command. Send
   * errors to stdErr.
   *
   * @param arg An array of arguments
   * @return String Output of the History command for stdOut
   */
  public String check(String[] arg) {
    if (arg.length == 2) {
      try {
        return runHistory(Integer.parseInt(arg[1]));
      } catch (NumberFormatException nfe) {
        StandardError.errors
            .add("Error: Invalid argument: you did not enter an integer\n");
        return "";
      }
    }
    return runHistory() + "\n";
  }
}
