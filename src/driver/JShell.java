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

package driver;

import java.util.Scanner;
import commands.Pwd;
import helpers.Parser;

/**
 * Class Name: JShell This class starts the shell and allows the user to enter
 * input
 */
public class JShell {

  @SuppressWarnings("resource") // For scanner in
  /**
   * Runs the Mock Terminal
   *
   * @param arg Array of arguments
   */
  public static void main(String[] args) {
    Pwd pwd = new Pwd();
    /**
     * Creates an instance of Parser so that JShell can parse the user input
     */
    Parser parser = new Parser();

    try {
      while (true) {
        if (Parser.speak.isSpeak()) {
          System.out.print("speak mode ");
        }

        System.out.print(pwd.runPwd() + "$ ");
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        if (line.trim().length() > 0) {
          parser.parseCommand(line);
        }
      }
    } catch (Exception e) {
      System.out.print(e.getMessage());
    }
  }
}
