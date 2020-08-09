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
import java.util.Arrays;
import commands.*;
import interfaces.ParserInterface;
import java.util.List;
import java.util.regex.*;

/**
 * Class Name: Parser This class holds methods to parse user input and call
 * their respective methods
 */
public class Parser implements ParserInterface {
  Pwd pwd = new Pwd();
  public static boolean canUseLoad = true;

  /**
   * Parses a user-inputted string and calls the respective command
   * 
   * @param input The user typed entry
   */
  public void parseCommand(String input) {
    input = input.trim();

    // Check if speak mode is active
    if (speak.isSpeak()) {
      input = input.replace("\\s+", " ");
      if (input.endsWith(" QUIT") || input.equals("QUIT")) {
        speak.addSpeakString(input.substring(0, input.indexOf("QUIT")));
        speak.runSpeakWithSpeakModeString();
      } else {
        speak.addSpeakString(input);
      }
    }

    // Not in speak mode
    else {
      // Adds command to the history list
      History.historyList.add(input);

      if ((input.length() - input.replace("\"", "").length()) < 2) {
        String[] userInput = input.split("\\s+");
        parseCheckArguments(userInput);

      } else {
        Pattern p = Pattern.compile("\\S*\".*?\"\\S*|\\S+");
        Matcher m = p.matcher(input);
        List<String> userInput = new ArrayList<>();

        while (m.find()) {
          userInput.add(m.group());
        }

        parseCheckArguments(userInput.toArray(new String[0]));
      }
    }
  }

  private void parseCheckRedirection(String[] arr) {
    String option, output;
    String[] command, path;

    if (Arrays.asList(arr).contains(">")) {
      option = ">";
    } else {
      option = ">>";
    }

    // parse the command
    command = Arrays.copyOfRange(arr, 0, Arrays.asList(arr).indexOf(option));
    // get path from arr
    path = Arrays.copyOfRange(arr, Arrays.asList(arr).indexOf(option),
        arr.length - 1);

    // check if the command is valid to run
    if (!(new ErrorCheck()).numArgCheck(command)) {
      StandardError.printErrors();
      return;
    }
    // the output of the command, without error messages
    output = commandsTable.get(arr[0]).check(command).trim();
    // check if output = ""
    if (output.equals("")) {
      StandardOutput.print(output);
      StandardError.printErrors();
      return;
    }
    // check if path is valid
    if (path.length == 1) {
      if (option == ">") {
        StandardOutput.print(stdOutCommandsTable.get(arr[0])
            .overwrite(arr[arr.length - 1], output));
        StandardError.printErrors();
      } else {
        StandardOutput.print(stdOutCommandsTable.get(arr[0])
            .append(arr[arr.length - 1], output));
        StandardError.printErrors();
      }
    } else {
      StandardError.errors.add("Error: invalid path name\n");
      StandardError.printErrors();
    }
  }

  /**
   * Checks if the command called has the correct number of arguments
   * 
   * @param input The user typed entry
   */
  private void parseCheckArguments(String[] arr) {

    if (commandsTable.containsKey(arr[0]) || arr[0].equals("exit")) {
      if (((Arrays.asList(arr).contains(">"))
          || Arrays.asList(arr).contains(">>"))) {
        parseCheckRedirection(arr);
        return;
      }

      if (!(new ErrorCheck()).numArgCheck(arr)) {
        StandardError.printErrors();
        return;
      }

      // Call commands here
      if (arr[0].equals("exit")) {
        System.exit(0);
      } else {
        // prints both errors and output
        int numErrors = StandardError.errors.size();
        StandardOutput.print(commandsTable.get(arr[0]).check(arr));
        // Check if first valid command has been called
        if (numErrors == StandardError.errors.size()) {
          canUseLoad = false;
        }
        StandardError.printErrors();
      }
    } else {
      StandardError.errors.add("Error: Invalid command: command '" + arr[0]
          + "' does " + "not exist\n");
      StandardError.printErrors();
    }
  }
}
