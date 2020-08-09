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

import java.util.ArrayList;

/**
 * Class Name: Directory This class outlines a directory object
 */
public class Directory extends Node {

  // Class variables
  private Directory parent;
  // private String dirName;
  private final ArrayList<Node> children = new ArrayList<>();

  // Constructor
  /**
   * Set directory name
   * 
   * @param dirName Directory name
   */
  public Directory(String dirName) {
    setDirName(dirName);
  }

  // Getters and setters
  /**
   * Get parent directory
   * 
   * @return
   */
  protected Directory getParent() {
    return parent;
  }

  /**
   * Set parent directory
   * 
   * @param parent Parent directory
   */
  protected void setParent(Directory parent) {
    this.parent = parent;
  }

  /**
   * Get directory name
   * 
   * @return
   */
  public String getDirName() {
    return super.getName();
  }

  /**
   * Set directory name
   * 
   * @param dirName Directory name
   */
  private void setDirName(String dirName) {
    super.setName(dirName);
  }

  /**
   * Get list of children
   * 
   * @return
   */
  public ArrayList<Node> getChildren() {
    return children;
  }
}
