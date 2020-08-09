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

import helpers.Directory;
import helpers.File;
import helpers.StandardError;
import helpers.Redirection;
import interfaces.CommandInterface;
import java.util.Arrays;

/**
 * Class Name: Cat This class holds methods to display the contents of a file to
 * the terminal
 */
public class Cat extends Redirection implements CommandInterface {

  /**
   * Find the file located at path and then get the contents of the file and
   * prints it out to the terminal
   *
   * @param path Determines where the file is located.
   */
  protected static String runCat(String path) {
    String output;
    Directory storeDir = fileSystem.getDir();

    fileSystem.traverse(errorCheck.getPath(path));
    File file = fileSystem.getChildFile(errorCheck.getName(path));

    output = file.getFileContent();
    fileSystem.setDir(storeDir);

    return output;
  }

  /**
   * Checks if the paths in arr are valid paths. Send errors to stdErr, and
   * returns output of the command.
   * 
   * @param arr Array of paths to files
   * @return String output of the Cat command for stdOut
   */
  public String check(String[] arr) {
    String[] paths = Arrays.copyOfRange(arr, 1, arr.length);
    String output = "";
    for (String path : paths) {
      if (errorCheck.fileExists(fileSystem, path)) {
        output += Cat.runCat(path) + "\n\n";
      } else {
        StandardError.errors.add("Error: Invalid filepath: the given file '"
            + path + "' does not exist\n");
      }
    }

    return output;
  }
}
