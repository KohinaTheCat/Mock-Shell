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

import java.util.Arrays;
import helpers.Directory;
import helpers.ErrorCheck;
import helpers.FileSystem;
import helpers.StandardError;
import interfaces.CommandInterface;

/**
 * Class Name: Mkdir This class makes directories
 */
public class Mkdir implements CommandInterface {
  /**
   * Gets an instance of fileSystem so that the class can access the fileSystem
   */
  private static FileSystem fileSystem = FileSystem.getInstanceOfFileSystem();
  /**
   * Creates an instance of errorCheck so that the class can access the
   * fileSystem
   */
  private static ErrorCheck errorCheck = new ErrorCheck();

  /**
   * Creates a directory at path
   * 
   * @param path Determines the path of the new directory
   */
  private void runMkdir(String path) {
    Directory storeDir = fileSystem.getDir();
    // Changes currDir to the directory given in the path
    fileSystem.traverse(errorCheck.getPath(path));
    // Now that we are in the required parent directory, we can add the node

    fileSystem.addChild(errorCheck.getName(path));
    // Now we come back to current directory
    fileSystem.setDir(storeDir);
  }

  /**
   * Check if arg[1] is a valid path, then call the method to create a
   * directory. Send error messages to stdErr accordingly, or returns output of
   * the command.
   *
   * @param arg Array of arguments
   * @return String Output of the Mkdir command for stdOut
   */
  public String check(String[] arg) {

    // will run for all valid paths.
    for (String path : Arrays.copyOfRange(arg, 1, arg.length)) {
      if (errorCheck.dirExists(fileSystem, path)
          || errorCheck.fileExists(fileSystem, path)) {
        StandardError.errors.add("Error: '" + path + "' already exists\n");
      } else if (!errorCheck.parentExists(fileSystem, path)) {
        StandardError.errors.add("Error: Invalid path: the parent directory "
            + errorCheck.getPath(path) + " does not exist\n");
      } else if (!errorCheck.invalidChar(errorCheck.getName(path))) {
        runMkdir(path);
      }
    }

    return "";
  }
}
