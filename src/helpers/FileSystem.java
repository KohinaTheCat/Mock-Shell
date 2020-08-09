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
 * Class Name: FileSystem This class keeps track of the current working
 * directory, and provides utilities to traverse the file system tree and
 * retrieve its contents
 */
public class FileSystem {

  // For singleton design
  private static FileSystem fs;

  // Create a root directory '/' and set current directory to it
  private Directory currDir;

  /**
   * Default constructor
   */
  protected FileSystem() {
    // Create a root directory '/' and set current directory to it
    currDir = new Directory("/");
  }

  /**
   * Gets instance of file system to support multiple shells running on the same
   * file system
   * 
   * @return FileSystem instance of file system
   */
  public static FileSystem getInstanceOfFileSystem() {
    // Check if a file system instance already exists
    if (fs == null) {

      // Create an instance if it doesn't exist
      fs = new FileSystem();
    }

    // Else return the instance
    return fs;
  }

  // Getters and Setters

  /**
   * Gets current directory
   * 
   * @return currDir Current directory
   */
  public Directory getDir() {
    return currDir;
  }

  /**
   * Sets current directory
   * 
   * @param newDir New directory
   * @return Nothing
   */
  public void setDir(Directory newDir) {
    currDir = newDir;
  }

  // Utilities:

  /**
   * Get sub directory if it exists
   * 
   * @param dirName name of child directory
   * @return Directory child directory
   */
  public Directory getChildDir(String dirName) {
    // Loop over the list of children
    for (Node child : currDir.getChildren()) {

      // Check if name of child matches parameter and child is of Directory type
      if (child instanceof Directory
          && ((Directory) child).getDirName().equals(dirName)) {

        // Return the child
        return (Directory) child;
      }
    }

    // Else return null
    return null;
  }

  /**
   * Adds a sub directory to the current directory
   * 
   * @param name Name of the sub directory to be created
   * @return Nothing
   */
  public void addChild(String name) {
    // Create a directory and set its name with the input parameter
    Directory childDir = new Directory(name);

    // Add created directory to list of children of current directory
    currDir.getChildren().add(childDir);

    // Add current directory as parent of created directory to allow two-way
    // traversal
    childDir.setParent(currDir);
  }

  /**
   * Adds a file to the current directory
   * 
   * @param file File type to be added
   * @return Nothing
   */
  public void addChild(File file) {
    currDir.getChildren().add(file);
  }

  /**
   * Get file from current directory if it exists
   * 
   * @param fileName name of file in the current directory
   * @return File the file from the current directory
   */
  public File getChildFile(String fileName) {
    // Loop through the children of the current directory
    for (Node child : currDir.getChildren()) {

      // Check if child is of type File and its name matches the parameter
      if (child instanceof File
          && ((File) child).getFileName().equals(fileName)) {
        // Return the child
        return (File) child;
      }

    }

    // Else return null
    return null;
  }

  // Traversing method and its helpers

  /**
   * Traverses to the given path, either absolute or relative to current
   * directory and returns boolean for existence of such a directory
   * 
   * @param path path of directory to traverse to (can be absolute or relative)
   * @return Boolean to convey success of traversal
   */
  public boolean traverse(String path) {
    // Store the current directory, because we might have to traverse back if
    // path doesn't exist
    Directory startDir = currDir;

    // Create boolean to store the result
    boolean exists;

    // Trivial case, root directory always exists
    if (path.equals("/")) {
      this.cdRoot();
      return true;
    }

    if (path.contains("//")) {
      StandardError.errors
          .add("Error: Cannot have consecutive slashes in a path name\n");
      return false;
    }
    // Create an array to store the directories of the path
    String[] directories;

    // Check if path is absolute
    if (path.startsWith("/")) {

      // Traverse to the root, so we can now treat the rest of the path as
      // relative as well
      this.cdRoot();

      // Trim the path based on the backslash delimiter
      directories = path.trim().substring(1).split("/");
    } else {
      directories = path.trim().split("/");
    }

    // Call helper function to traverse to the directory
    exists = traverseHelper(directories);

    // Revert to stored directory if path doesn't exist
    if (!exists) {
      this.currDir = startDir;
    }
    // return result
    return exists;
  }

  /**
   * Helper method for traversing. Traverses down and if it is the end of the
   * array then the path exists
   * 
   * @param directories list of directories in the path
   * @return Boolean to convey success of traversal
   */
  private boolean traverseHelper(String[] directories) {
    // Loop through the array, each element being a sub directory of the
    // preceding element
    for (String directory : directories) {

      // Check for '..' case
      if (directory.equals("..")) {
        // traverse to parent
        this.cdParent();
      }

      // Else, check if child exists by calling helper method, which already
      // does the traversing if the child exists
      else if (!this.cdChild(directory)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Checks if current directory is the root directory
   * 
   * @return Boolean returns true if current directory is root directory
   */
  public boolean isRootDir() {
    return currDir.getDirName().equals("/");
  }

  /**
   * Changed current directory to root directory
   * 
   * @return Nothing
   */
  protected void cdRoot() {
    // Keep traversing to parent till you reach the root directory
    while (!this.isRootDir()) {
      this.cdParent();
    }
  }

  /**
   * Changes current directory to parent of current directory unless current
   * directory is the root directory and returns true if the change is
   * successful
   * 
   * @return Nothing
   */
  public void cdParent() {
    // Check if already at root directory
    if (!this.isRootDir()) {
      this.setDir(currDir.getParent());
    }
  }

  /**
   * Changes directory to user specified sub directory if it exists, and returns
   * true if the change is successful
   * 
   * @param childName name of sub directory we want to traverse to
   * @return Boolean to convey success of traversal
   */
  private boolean cdChild(String childName) {
    // Check for '.' case which is always true, no traversal needed
    if (childName.equals(".")) {
      return true;
    }

    // Check if a sub directory with the given parameter as a name exists
    if (this.getChildDir(childName) != null) {
      // traverse to the sub directory
      this.setDir(this.getChildDir(childName));

      return true;
    }
    return false;
  }
}
