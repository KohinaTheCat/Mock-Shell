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

import commands.Pwd;

/**
 * Class Name: ErrorCheck This class holds methods of common checks done on user
 * input and command arguments
 */
public class ErrorCheck {
  Pwd pwd = new Pwd();

  // Input Checks

  /**
   * Checks if the number of arguments for the command is correct or not
   * 
   * @param parsedInput array of whitespace separated input
   * @return String
   */
  public boolean numArgCheck(String[] parsedInput) {
    String commands;
    if (parsedInput[0].equals("ls"))
      return true;
    if (parsedInput[0].equals("find") && parsedInput.length > 4)
      return true;
    if (parsedInput[0].equals("cat") && parsedInput.length > 1)
      return true;
    if (parsedInput[0].equals("mkdir") && parsedInput.length > 1)
      return true;
    if (parsedInput.length == 1) {
      commands = "exit speak tree pwd popd history";
    } else if (parsedInput.length == 2) {
      commands = "speak cd pushd history echo man rm curl save load";
    } else if (parsedInput.length == 3) {
      commands = "cp mv mkdir";
    } else {
      commands = "";
    }
    if (commands.contains(parsedInput[0])) {
      return true;
    }
    StandardError.errors.add("Error: Invalid arguments: please type 'man "
        + parsedInput[0] + "' to know more\n");
    return false;
  }

  /**
   * Check if file/directory to be created does not have invalid characters.
   * 
   * @param name
   * @return String
   */
  public boolean invalidChar(String name) {
    String[] invalidChars = {"/", " ", "!", "@", "#", "$", "%", "^", "&", "*",
        "(", ")", "{", "}", "~", "|", "<", ">", "?", "\""};
    StringBuilder invalid = new StringBuilder("");

    for (String invalidChar : invalidChars) {
      if (name.contains(invalidChar)) {
        invalid.append(invalidChar).append(", ");
      }
    }

    if (invalid.length() == 0) {
      return false;
    }

    StandardError.errors
        .add("Error: Invalid character(s): A file/directory cannot have "
            + invalid.substring(0, invalid.length() - 2) + " in it's name\n");

    return true;

  }

  /**
   * Check if string is wrapped by double quotes, and does not contain quotes
   * inside the double quotes
   * 
   * @param input
   * @return String
   */
  public boolean isProperString(String input) {
    if (input.length() < 2 || (input.charAt(0) != '"')
        || (input.charAt(input.length() - 1) != '"')) {
      StandardError.errors
          .add("Error: Strings need to be wrapped in double quotes\n");
      return false;
    }
    if (input.substring(1, input.length() - 1).contains("\"")) {
      StandardError.errors
          .add("Error: Strings cannot contain double quotes within them\n");
      return false;
    }
    return true;
  }

  /**
   * Checks if directory exists with given path relative to current directory
   * 
   * @param tree
   * @param path
   * @return Boolean
   */
  public boolean dirExists(FileSystem tree, String path) {
    Directory storeDir = tree.getDir();
    boolean exists = tree.traverse(path);
    tree.setDir(storeDir);
    return exists;
  }

  /**
   * Checks if file exists with the given path which is the directory where the
   * file is stored, with respect to the current directory
   * 
   * @param tree
   * @param fullPath
   * @return Boolean
   */
  public boolean fileExists(FileSystem tree, String fullPath) {
    Directory storeDir = tree.getDir();
    boolean exists = tree.traverse(getPath(fullPath));
    exists = exists && tree.getChildFile(getName(fullPath)) != null;
    tree.setDir(storeDir);
    return exists;
  }

  /**
   * Checks if path exists, either absolute or relative
   * 
   * @param tree
   * @param fullPath
   * @return Boolean
   */
  public boolean parentExists(FileSystem tree, String fullPath) {
    String path = this.getPath(fullPath);
    return this.dirExists(tree, path);
  }

  /**
   * Get the file/directory name from the path
   * 
   * @param pathString
   * @return String
   */
  public String getName(String pathString) {
    String name;

    int position = pathString.lastIndexOf("/");
    if (position == -1) {
      name = pathString;
    } else if (position == 0) {
      name = pathString.substring(1);
    } else {
      name = pathString.substring(pathString.lastIndexOf("/") + 1);
    }
    return name;
  }

  /**
   * Get the path of the parent of the given path
   * 
   * @param pathString
   * @return String
   */
  public String getPath(String pathString) {
    String path;

    int position = pathString.lastIndexOf("/");
    if (position == -1) {
      path = pwd.runPwd();
    } else if (position == 0) {
      path = "/";
    } else {
      path = pathString.substring(0, pathString.lastIndexOf("/"));
    }
    return path;
  }
}
