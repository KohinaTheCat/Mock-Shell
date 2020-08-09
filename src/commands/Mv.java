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
import interfaces.CommandInterface;

/**
 * Class Name: Mv This class moves a file/directory from OLDPATH to NEWPATH
 */
public class Mv extends Cp implements CommandInterface {

  private void moveFileToDir(String objName, String objParent,
      String targetPath) {
    // Gets file and traverses to parent
    Directory storeDir = fileSystem.getDir();
    super.copyFileToDir(objName, objParent, targetPath);
    fileSystem.traverse(objParent);
    // Gets the file and removes it from original location
    File toDel = fileSystem.getChildFile(objName);
    fileSystem.getDir().getChildren().remove(toDel);
    fileSystem.setDir(storeDir);
  }

  private void moveFileToFile(String objName, String objParent,
      String targetName, String targetParent) {
    // Gets file and traverses to parent
    Directory storeDir = fileSystem.getDir();
    super.copyFileToFile(objName, objParent, targetName, targetParent);
    fileSystem.traverse(objParent);
    // Gets the file and removes it from original location
    File toDel = fileSystem.getChildFile(objName);
    fileSystem.getDir().getChildren().remove(toDel);
    fileSystem.setDir(storeDir);
  }

  private void moveDirToDir(String objectPath, String targetPath) {
    // Uses parent function to copy directories
    super.copyDirToDir(objectPath, targetPath);
    // Removes the file from original location
    String[] rmInput = {"rm", objectPath};
    rm.check(rmInput);
  }

  /**
   * Checks if the paths in arg are files/directories and if they exist or not
   * and runs the proper methods, send error messages to stdErr accordingly, or
   * returns output of the command.
   * 
   * @param arg Array of parsed input
   * @return String Output of the Mv command for stdOut
   */
  public String check(String[] arg) {
    // Checks if the file/dir exists
    boolean objFileExists = errorCheck.fileExists(fileSystem, arg[1]);
    boolean objDirExists = errorCheck.dirExists(fileSystem, arg[1]);

    // Error message in the case that the first argument path doesn't exist
    if (!(objFileExists || objDirExists)) {
      StandardError.errors
          .add("mv: file/directory to be moved does not exist\n");
      return "";
    }

    else if (!errorCheck.parentExists(fileSystem, arg[2])) {
      StandardError.errors.add("mv: Target path parent does not exist\n");
      return "";
    }

    // Creates directory or file if target dir/file do not exist
    if (objDirExists)
      checkDir(arg[1], arg[2]);
    else if (objFileExists)
      checkFile(arg[1], arg[2]);
    return "";
  }

  private void checkDir(String objDir, String targetPath) {
    // Checks if the file exists in the path
    if (errorCheck.fileExists(fileSystem, targetPath)) {
      StandardError.errors.add("mv: Cannot move a directory into a file\n");
    } else if (errorCheck.dirExists(fileSystem, targetPath)) {
      if (!checkSubDir(objDir, targetPath)) {
        if (targetPath.equals("/") && objDir.charAt(0) != '/') {
          String[] rmInput = {"rm", objDir};
          rm.check(rmInput);
          return;
        }
        String objName = errorCheck.getName(objDir);
        String newPath;
        // Initializes newPath to the correct path
        if (targetPath.equals("/")) {
          newPath = targetPath + objName;
        } else
          newPath = targetPath + "/" + objName;
        if (errorCheck.dirExists(fileSystem, newPath)) {
          String[] remove = {"rm", newPath};
          rm.check(remove);
        }
        // Makes the directory and moves the directory
        super.makeDir(newPath);
        moveDirToDir(objDir, newPath);
      }
    } else if (!checkSubDir(objDir, errorCheck.getPath(targetPath))) {
      super.makeDir(targetPath);
      moveDirToDir(objDir, targetPath);
    }
  }

  private void checkFile(String objFile, String targetPath) {
    String objName = errorCheck.getName(objFile);
    String objParent = errorCheck.getPath(objFile);
    // Checks if the file exists
    if (errorCheck.fileExists(fileSystem, targetPath)) {
      if (objParent.equals(errorCheck.getPath(targetPath))
          && objName.equals(errorCheck.getName(targetPath))) {
        StandardError.errors.add("mv: '" + objFile + "' and '" + targetPath
            + "' are identical (not moved)\n");
        return;
      } else
        // Calls the move file to file function if the target is a file
        moveFileToFile(objName, objParent, errorCheck.getName(targetPath),
            errorCheck.getPath(targetPath));
    } else if (errorCheck.dirExists(fileSystem, targetPath)) {
      // Calls the move file to dir if target is a dir
      moveFileToDir(objName, objParent, targetPath);
    } else {
      super.makeFile(targetPath);
      moveFileToFile(objName, objParent, errorCheck.getName(targetPath),
          errorCheck.getPath(targetPath));
    }
  }

  private boolean checkSubDir(String objDir, String targetDir) {
    // Performs traversing to the path
    Directory storeDir = fileSystem.getDir();
    fileSystem.traverse(targetDir);
    String targetFullPath = pwd.runPwd();
    fileSystem.setDir(storeDir);
    fileSystem.traverse(objDir);
    String objFullPath = pwd.runPwd();
    fileSystem.setDir(storeDir);
    // Checks if the directory is being moved into its own child
    if (objFullPath.equals("/") || (targetFullPath.startsWith(objFullPath)
        && targetFullPath.charAt(objFullPath.length()) == '/')) {
      StandardError.errors.add("mv: Cannot move a directory into its child\n");
      return true;
    }
    return false;
  }
}
