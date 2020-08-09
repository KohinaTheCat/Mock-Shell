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

import java.util.HashMap;
import java.util.Map;
import helpers.StandardError;
import helpers.Redirection;
import interfaces.CommandInterface;

/**
 * Class Name: Man This class contains the manuals and methods for the Man
 * command
 **/
public class Man extends Redirection implements CommandInterface {

  String exit = "Command Name:  exit\n\n" + "Description: Exits the program";
  String speak = "Command Name:  speak [STRING]\n\n"
      + "Description: Converts text to audible speech. String is an optional \n"
      + "parameter. If no String is provided, the command waits for user \n"
      + "input. To quit Speak command, user must type QUIT.";
  String mkdir = "Command Name:  mkdir DIR\n\n"
      + "Description: Makes a directory in the current directory or a \n"
      + "specified path.";
  String cd = "Command Name:  cd DIR\n\n"
      + "Description: Changes the directory to a specified path.";
  String ls = "Command Name:  ls [-R] [PATH ...]\n\n"
      + "Description: Prints all contents of a directory if no path specified."
      + "\nIf path is provided, then prints all contents of that path.";
  String pwd = "Command Name:  pwd\n\n"
      + "Description: Prints current working directory (including the whole\n"
      + "path).";
  String pushd = "Command Name:  pushd DIR\n\n"
      + "Arguments: DIR a path to a directory\n\n"
      + "Description: Saves current working directory into directory stack\n"
      + "and then changes new current directory, to directory provided. The\n"
      + "pushd command saves the old current working directory in directory\n"
      + "stack so that it can be returned to at any time (via the popd\n"
      + "command). The size of the directory stack is dynamic and dependent\n"
      + "on the pushd and the popd commands.";
  String popd = "Command Name:  popd\n"
      + "Description: Removes the top entry from directory stack and then\n"
      + "changes the current working directory to that directory.";
  String history = "Command Name:  history [number]\n\n"
      + "Description: This command will print out the [number] most recent\n"
      + "commands in two columns. The first column is numbered such that the\n"
      + "line with the highest number is the most recent command. The most\n"
      + "recent command is history. The second column contains the actual\n"
      + "command.";
  String cat = "Command Name:  cat FILE1 [FILE2 ...]\n\n"
      + "Arguments: FILE1 is a path to a file. [FILE2 ...] means you may\n"
      + "include multiple paths to multiple files seperated with a space\n\n"
      + "Description: Display contents of the file provided. If multiple\n"
      + "files are provided, display all of them concatenated.";
  String echo = "Command Name:  echo STRING [> OUTFILE]\n\n"
      + "Arguments: STRING must be wrapped in quotes\n\n"
      + "Description: If OUTFILE is not provided, prints string on to shell\n\n"
      + "if given a string. Otherwise if file is provided, overwrites string\n"
      + "into the given file. Creates a new file if file does not exist\n\n"
      + "Command Name:  echo STRING [>> OUTFILE]\n\n"
      + "Arguments: STRING must be wrapped in quotes\n\n"
      + "Description: exactly like echo STRING [> OUTFILE], except it appends\n"
      + "instead of overwrite";
  String man = "Command Name:  man CMD\n\n"
      + "Arguments: CMD refers to a single command of the shell\n\n"
      + "Description: Prints documentation for command (CMD) given.";

  String save = "Command Name:  save [FILENAME].ser\n\n"
      + "Arguments: CMD refers name of the file you want to save as\n\n"
      + "Description: Saves the current state of the file system to the file";
  String load = "Command Name:  load [FILENAME].ser\n\n"
      + "Arguments: CMD refers name of the file you want to load\n\n"
      + "Description: Loads the state of the file system from the save file";
  String rm =
      "Command Name:  rm DIR \n\n" + "Arguments: DIR an existing directory\n\n"
          + "Description: Removes that directory from the file system";
  String mv = "Command Name:  mv OLDPATH NEWPATH \n\n"
      + "Arguments: OLDPATH existing path NEWPATH\n\n"
      + "Description: Moves OLDPATH to NEWPATH. OLDPATH cannot be a parent to\n"
      + "NEWPATH.";
  String cp = "Command Name:  cp OLDPATH NEWPATH \n\n"
      + "Arguments: OLDPATH existing path NEWPATH\n\n"
      + "Description: Copies OLDPATH to NEWPATH. OLDPATH cannot be a parent to\n"
      + "NEWPATH.";
  String curl = "Command Name: curl URL \n\n" + "Arguments: URL url\n\n"
      + "Description:  Retrieve the file at URL and add it to the current working \n"
      + "directory.";
  String find = "Command Name: find path ... -type [f|d] -name EXPRESSION\n\n"
      + "Arguments:   path path you want to search. [f|d] file | directory. "
      + "EXPRESSION keyword\n\n"
      + "Description:  Find file or directory that have the name exactly as "
      + "EXPRESSION";
  String tree = "Command Name: tree\n\n"
      + "Description:  Visually displays the current filesystem to the terminal";

  @SuppressWarnings("serial")
  Map<String, String> commands = new HashMap<String, String>() {
    {
      put("exit", exit);
      put("speak", speak);
      put("mkdir", mkdir);
      put("cd", cd);
      put("ls", ls);
      put("pwd", pwd);
      put("pushd", pushd);
      put("popd", popd);
      put("history", history);
      put("cat", cat);
      put("echo", echo);
      put("man", man);
      put("save", save);
      put("load", load);
      put("rm", rm);
      put("mv", mv);
      put("cp", cp);
      put("curl", curl);
      put("find", find);
      put("tree", tree);
    }
  };

  /**
   * Gets the manual for command
   * 
   * @param command
   */
  private String runMan(String command) {
    return commands.get(command);
  }

  /**
   * Checks if arg[1] is a valid command, if it is print the manual. Send errors
   * to stdErr, and returns output of the command.
   *
   * @param arg An array of the input
   * @return String Output of the Man command for stdOut
   */
  public String check(String[] arg) {
    String cmd = arg[1];

    if (!commands.containsKey(cmd)) {
      StandardError.errors.add("No manual entry found for " + cmd + "\n");
      return "";
    }

    return runMan(cmd) + "\n";
  }
}
