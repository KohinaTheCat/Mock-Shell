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

/**
 * Class Name: Redirection This class allows input to be redirected into files
 */
public class Redirection {

  /**
   * Creates an instance of errorCheck so that the subclasses can perform checks
   * for arguments
   */
  protected static ErrorCheck errorCheck = new ErrorCheck();

  /**
   * Creates an instance of fileSystem so that the subclasses access the same
   * fileSystem
   */
  protected static FileSystem fileSystem = FileSystem.getInstanceOfFileSystem();


  private boolean checkErrors(String path, String content) {
    // check if dir exists
    if (errorCheck.dirExists(fileSystem, path)) {
      StandardError.errors
          .add("Error: a file and a directory cannot have duplicate names\n");
      return false;
    }

    // check if path is valid
    if (!errorCheck.parentExists(fileSystem, path)) {
      StandardError.errors.add(
          "Error: Invalid path: the directory " + path + " does not exist\n");
      return false;
    }

    // check if we should create a file instead
    if (!errorCheck.fileExists(fileSystem, path)) {
      if (!errorCheck.invalidChar(errorCheck.getName(path))) {
        create(path, content);
        return false;
      }
      return false;
    }

    return true;
  }

  /**
   * Appends content to file at path
   *
   * @param path Path to a file
   * @param content Content to add to a file
   */
  public String append(String path, String content) {

    if (!checkErrors(path, content))
      return "";

    Directory storeDir = fileSystem.getDir();
    fileSystem.traverse(errorCheck.getPath(path));
    File file = fileSystem.getChildFile(errorCheck.getName(path));

    file.appendFileContent(content);

    fileSystem.setDir(storeDir);

    return "";
  }

  /**
   * Overwrites content to file at path
   *
   * @param path Path to a file
   * @param content Content to overwrite to a file
   */
  public String overwrite(String path, String content) {
    if (!checkErrors(path, content))
      return "";

    Directory storeDir = fileSystem.getDir();
    fileSystem.traverse(errorCheck.getPath(path));
    File file = fileSystem.getChildFile(errorCheck.getName(path));

    file.setFileContent(content);

    fileSystem.setDir(storeDir);

    return "";
  }

  /**
   * Creates a new file with content of str at path
   *
   * @param path Determines where the file is located.
   * @param str Determines the contents of the file.
   */
  private void create(String path, String content) {
    Directory storeDir = fileSystem.getDir();
    fileSystem.traverse(errorCheck.getPath(path));
    fileSystem.addChild(new File(errorCheck.getName(path), content));
    fileSystem.setDir(storeDir);
  }

}
