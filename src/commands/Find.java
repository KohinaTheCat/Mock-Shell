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
import helpers.Node;
import helpers.StandardError;
import helpers.Redirection;
import interfaces.CommandInterface;

/**
 * Class Name: Find This class finds directories or files with a given name and
 * prints out a list
 */
public class Find extends Redirection implements CommandInterface {
  private String list = "";

  /*
   * Get instance of Pwd class to use method runPwd() to get the current working
   * directory
   */
  Pwd pwd = new Pwd();


  private void find(String path, String type, String expression) {
    Directory storeDir = fileSystem.getDir();
    fileSystem.traverse(path);
    recurse(fileSystem.getDir(), type, expression);
    fileSystem.setDir(storeDir);
  }

  private void recurse(Directory currDir, String type, String expression) {
    if (type.equals("f")) {
      for (Node child : fileSystem.getDir().getChildren()) {
        if (child instanceof File) {
          File childFile = (File) child;
          if (childFile.getFileName().equals(expression)) {
            if (fileSystem.isRootDir()) {
              list += "/" + expression + " ";
            } else
              list += pwd.runPwd() + "/" + expression + " ";
          }
        } else {
          fileSystem.setDir((Directory) child);
          recurse((Directory) child, type, expression);
          fileSystem.setDir(currDir);
        }
      }
    } else {
      for (Node child : fileSystem.getDir().getChildren()) {
        if (child instanceof Directory) {
          Directory childDir = (Directory) child;
          if (childDir.getDirName().equals(expression)) {
            if (fileSystem.isRootDir()) {
              list += "/" + expression + " ";
            } else
              list += pwd.runPwd() + "/" + expression + " ";
          }
          fileSystem.setDir((Directory) child);
          recurse((Directory) child, type, expression);
          fileSystem.setDir(currDir);
        }
      }
    }
  }

  /**
   * Checks if args are valid. Run the Find command. Send errors to stdErr, and
   * returns output of the command.
   * 
   * @param arg Array of arguments
   * @return String Output of the Find command for stdOut
   */
  public String check(String[] arg) {
    int len = arg.length;
    if (!checkSyntax(arg, len)) {
      return "";
    }
    int i = 1;
    String output = "";
    while (!arg[i].equals("-type")) {
      if (!errorCheck.dirExists(fileSystem, arg[i])) {
        StandardError.errors
            .add("find: the path " + arg[i] + " does not exist\n");
      } else {
        find(arg[i], arg[len - 3],
            arg[len - 1].substring(1, arg[len - 1].length() - 1));
        output += arg[i] + ": " + list + "\n";
        list = "";
      }
      i++;
    }
    if (i == 1) {
      StandardError.errors.add("find: no path(s) specified\n");
      return "";
    }
    return output;
  }

  private boolean checkSyntax(String[] arg, int len) {
    if (!arg[len - 4].equals("-type")) {
      StandardError.errors
          .add("find: '-type' argument is missing/in the wrong order\n");
      return false;
    }
    if (!(arg[len - 3].equals("f") || arg[len - 3].equals("d"))) {
      StandardError.errors.add("find: please specify type of search (f/d)\n");
      return false;
    }
    if (!arg[len - 2].equals("-name")) {
      StandardError.errors
          .add("find: '-name' argument is missing/in the wrong order\n");
      return false;
    }
    if (!errorCheck.isProperString(arg[len - 1])) {
      return false;
    }
    return true;
  }
}
