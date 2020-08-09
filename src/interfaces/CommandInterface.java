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

package interfaces;

/**
 * Interface Name: CommandInterface This interface allows command classes to
 * implement a check method, which allows us to implement a hashmap
 */
public interface CommandInterface {

  /**
   * Each command checks if arg are valid, and runs the program accordingly.
   * Sends error to stfError if arg[1] is not a valid path, or returns output of
   * the command.
   * 
   * @param arg Array of arguments
   * @return String Output of the Command Class for stdOut
   */
  public String check(String[] arg);
}
