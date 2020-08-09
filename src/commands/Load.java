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

import interfaces.CommandInterface;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import helpers.Parser;
import helpers.StandardError;

/**
 * Class Name: Load This class loads a save file and changes the state of the
 * terminal
 */
public class Load implements CommandInterface {

  /**
   * Sets current system state to what was written in fileToRead
   * 
   * @param fileToRead The file the user wants to load a save file from
   */
  @SuppressWarnings("unchecked")
  public void readFunction(String fileToRead) {
    String fileName = fileToRead;
    // Serialization
    try {
      // Saving of object in a file
      FileInputStream file = new FileInputStream(fileName);
      ObjectInputStream in = new ObjectInputStream(file);

      // History Stack
      History.historyList = (ArrayList<String>) in.readObject();
      // Push/Pop Stack
      Pushd.directoryPathStack = (Stack<String>) in.readObject();
      // Current Directory
      String currWorkingDir = (String) in.readObject();
      // File System Tree
      String fileSystem = (String) in.readObject();
      List<String> tree = Arrays.asList(fileSystem.split("\\s+"));
      // HashMap for Files
      HashMap<String, String> fileMap = new HashMap<String, String>();
      fileMap = (HashMap<String, String>) in.readObject();

      makeFileAndDir(tree, fileMap);

      // Cd into the original working directory
      Cd cd = new Cd();
      String[] cdInput = {"cd", currWorkingDir};
      cd.check(cdInput);

      in.close();
      file.close();
    } catch (IOException ex) {
      StandardError.errors.add("Error: File Not Found\n");
    } catch (ClassNotFoundException ex) {
      StandardError.errors.add("Error: File Not Compatible For Loading\n");
    }
  }

  private void makeFileAndDir(List<String> tree,
      HashMap<String, String> fileMap) {
    Echo echo = new Echo();
    Mkdir mkdir = new Mkdir();
    // Create files or directories respectively
    String fullPath = tree.get(0).substring(0, tree.get(0).length() - 1);
    String filePath;
    for (int i = 1; i < tree.size(); i++) {
      // Check if element is directory path
      if (!tree.get(i).contains("/")) {
        // Avoid issues with double slash
        if (fullPath.equals("/")) {
          filePath = fullPath + tree.get(i);
        } else {
          filePath = fullPath + "/" + tree.get(i);
        }

        // Check if subelement is a file based on if key is in hashmap
        if (fileMap.containsKey(filePath)) {
          // Use echo to create a file
          echo.append(filePath, fileMap.get(filePath));
        }
      } else {
        // If element is directory path, create that directory
        fullPath = tree.get(i).substring(0, tree.get(i).length() - 1);;
        String[] mkdirInput = {"mkdir", fullPath};
        mkdir.check(mkdirInput);
      }
    }
  }

  /**
   * Runs the Load command, send errors to stdErr, and returns output of the
   * command.
   * 
   * @param arg Array of parsed input
   * @return String Output of the Load command for stdOut
   */
  public String check(String[] arg) {
    if (Parser.canUseLoad) {
      readFunction(arg[1]);
    } else {
      StandardError.errors
          .add("Error: Load can only be called if it is the first command\n");
    }
    return "";
  }
}
