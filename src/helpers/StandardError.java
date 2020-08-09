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

package helpers;

import java.util.ArrayList;

/**
 * Class Name: StandardError This class prints out all error messages
 */
public class StandardError {

  /**
   * Creates an array list for errors that go to stdErr
   */
  public static ArrayList<String> errors = new ArrayList<String>();

  /**
   * Print out all the error messages to stdErr
   */
  public static void printErrors() {
    for (int i = 0; i < errors.size(); i++) {
      // Does not use .err b/c of spacing problems
      System.out.print(errors.get(i));
    }
    errors.clear();
  }
}
