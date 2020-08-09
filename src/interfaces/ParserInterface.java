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

import java.util.HashMap;
import java.util.Map;
import commands.*;
import helpers.Redirection;

/**
 * Interface Name: ParserInterface This interface holds all the hashmaps for the
 * Parser Class
 */
public interface ParserInterface {

  /**
   * Creates an instance of speak so that the user could continuously enter
   * words to convert for audio, until they type QUIT
   */
  public Speak speak = new Speak();

  /**
   * Create an one instance of the command classes so they be called through our
   * hash map and run the respective commands depending on user input
   */
  @SuppressWarnings("serial")
  Map<String, CommandInterface> commandsTable =
      new HashMap<String, CommandInterface>() {
        {
          put("speak", speak);
          put("mkdir", new Mkdir());
          put("cd", new Cd());
          put("ls", new Ls());
          put("pushd", new Pushd());
          put("popd", new Popd());
          put("history", new History());
          put("cat", new Cat());
          put("echo", new Echo());
          put("man", new Man());
          put("tree", new Tree());
          put("cp", new Cp());
          put("rm", new Rm());
          put("save", new Save());
          put("load", new Load());
          put("pwd", new Pwd());
          put("find", new Find());
          put("curl", new Curl());
          put("mv", new Mv());
        }
      };

  /**
   * Create an one instance of the command classes that extends Redirection and
   * has output to stdOut, so they be called through our hash map and redirect
   * the output to a file
   */
  @SuppressWarnings("serial")
  Map<String, Redirection> stdOutCommandsTable =
      new HashMap<String, Redirection>() {
        {
          put("ls", new Ls());
          put("history", new History());
          put("cat", new Cat());
          put("echo", new Echo());
          put("man", new Man());
          put("tree", new Tree());
          put("pwd", new Pwd());
          put("find", new Find());
        }
      };

  /**
   * Parses a user-inputted string and calls the respective command
   * 
   * @param input The user typed entry
   */
  public void parseCommand(String input);
}
