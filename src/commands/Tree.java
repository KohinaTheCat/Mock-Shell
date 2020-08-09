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

import java.util.List;
import helpers.Node;
import helpers.Directory;
import helpers.File;
import helpers.Redirection;
import interfaces.CommandInterface;

/**
 * Class Name: Tree This class holds methods to visually display the file system
 */
public class Tree extends Redirection implements CommandInterface {

  private String runTree(String root, int depth) {
    Directory storeDir = fileSystem.getDir();
    String output = "";
    fileSystem.traverse(root);
    List<Node> children = fileSystem.getDir().getChildren();

    // Get the correct amount of spacing based on depth
    String repeated = new String(new char[depth]).replace("\0", " ");

    // Base case, if object has no children it will keep recursing
    for (Node child : children) {
      if (child instanceof Directory) {
        output += repeated + ((Directory) child).getDirName() + "\n";
        output += runTree(((Directory) child).getDirName(), depth + 1);
      } else {
        output += repeated + ((File) child).getFileName() + "\n";
      }
    }

    fileSystem.setDir(storeDir);

    return output;
  }

  /**
   * Runs the Tree command. Returns output of the command.
   *
   * @param arg Array of arguments
   * @return String Output of the tree command for stdOut
   */
  public String check(String arr[]) {
    // Tree is always run starting at root
    return "/\n" + runTree("/", 1) + "\n";
  }
}
