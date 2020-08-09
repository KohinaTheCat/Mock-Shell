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

import helpers.Node;
import helpers.Directory;
import helpers.File;
import helpers.StandardError;
import helpers.Redirection;
import interfaces.CommandInterface;
import java.util.Arrays;
import java.util.List;

/**
 * Class Name: Ls This class prints out the contents of the current directory or
 * a specified one
 */
public class Ls extends Redirection implements CommandInterface {
  /*
   * Get instance of Pwd class to use method runPwd() to get the current working
   * directory
   */
  Pwd pwd = new Pwd();

  /**
   * Prints out the contents of the current directory
   */
  private String runLs() {
    List<Node> children = fileSystem.getDir().getChildren();

    if (children.isEmpty()) {
      return "";
    }

    StringBuilder output = new StringBuilder();

    for (Node child : children) {
      if (child instanceof Directory) {
        output.append(((Directory) child).getDirName());
      } else {
        output.append(((File) child).getFileName());
      }
      output.append("\n");
    }

    return output.toString();
  }

  /**
   * Prints out the contents of the directory at path
   * 
   * @param path Determines the path of the directory
   */
  private String runLs(String path, boolean recurse) {

    Directory storeDir = fileSystem.getDir();
    fileSystem.traverse(path);

    String content = (pwd.runPwd() + ": ");
    // fix the printing of the parent directory

    List<Node> children = fileSystem.getDir().getChildren();

    for (Object child : children) {
      if (child instanceof Directory) {
        content += (((Directory) child).getDirName());
      } else {
        content += (((File) child).getFileName());
      }
      content += (" ");
    }

    content = (content.substring(0, content.length() - 1) + "\n\n");
    fileSystem.setDir(storeDir);

    storeDir = fileSystem.getDir();
    fileSystem.traverse(path);

    if (recurse) { // lmao is this even recrusion without a base case....
      for (Node child : children) {
        if (child instanceof Directory) {
          content += runLs(((Directory) child).getDirName(), true);
        }
      }
    }

    fileSystem.setDir(storeDir);
    return content;
  }

  /**
   * Prints out the contents of a file
   * 
   * @param path Determines the path to a file
   */
  private String getFileContent(String path) {
    return Cat.runCat(path) + "\n";
  }

  /**
   * Checks if the arguments in input and the validity of relative or absolute
   * paths are valid, and calls the correct method. Send errors to stdErr, and
   * returns output of the command.
   *
   * @param input An array of arguments
   * @return String Output of the Ls command for stdOut
   */
  public String check(String[] input) {
    String output = "";

    int r = 0;

    if (input.length == 1) {
      return runLs();
    } else {

      if (input[1].equals("-R")) {
        r = 1;
      }

      if (input.length == 2 && r == 1) {
        return runLs(fileSystem.getDir().getDirName(), true);
      }

      // error if at least one of the paths provided are invalid
      for (String path : Arrays.copyOfRange(input, 1 + r, input.length)) {
        if (!(errorCheck.dirExists(fileSystem, path)
            || errorCheck.fileExists(fileSystem, path))) {

          StandardError.errors.add("Error: Invalid path: the given path '"
              + path + "' does not exist\n");
          return "";
        }
      }

      for (String path : Arrays.copyOfRange(input, 1 + r, input.length)) {
        if (errorCheck.dirExists(fileSystem, path)) {
          output += runLs(path, r == 1);
        } else if (errorCheck.fileExists(fileSystem, path)) {
          output += getFileContent(path);
        }
      }
    }

    return output;
  }
}
