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
 * Class Name: Node This class aids for the use of Generics
 */
public class Node {
  // this is a superclass who's purpose is to be an identifier of the
  // object types that can be stored in a file system.

  private String nodeName;

  protected String getName() {
    return nodeName;
  }

  protected void setName(String nodeName) {
    this.nodeName = nodeName;
  }
}
