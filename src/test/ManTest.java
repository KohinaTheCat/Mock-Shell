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

package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import commands.Man;

public class ManTest {
  String output, expected;

  Man man;

  @Before
  public void setUp() {
    man = new Man();

    output = "";
    expected = "";
  }

  @Test
  public void testBadArg() {
    output = man.check(new String[] {"man", "exi"}).trim();
    expected = "";

    // Error message is pushed into ErrorCheck.errors
    // stack and is tested in ErrorCheckTest

    assertEquals(expected, output);
  }

  @Test
  public void testExit() {
    output = man.check(new String[] {"man", "exit"}).trim();
    expected = "Command Name:  exit\n\n" + "Description: Exits the program";

    assertEquals(expected, output);
  }

  @Test
  public void testSpeak() {
    output = man.check(new String[] {"man", "speak"}).trim();
    expected = "Command Name:  speak [STRING]\n\n"
        + "Description: Converts text to audible speech. String is an optional \n"
        + "parameter. If no String is provided, the command waits for user \n"
        + "input. To quit Speak command, user must type QUIT.";

    assertEquals(expected, output);
  }

  @Test
  public void testMkdir() {
    output = man.check(new String[] {"man", "mkdir"}).trim();
    expected = "Command Name:  mkdir DIR\n\n"
        + "Description: Makes a directory in the current directory or a \n"
        + "specified path.";

    assertEquals(expected, output);
  }

  @Test
  public void testCd() {
    output = man.check(new String[] {"man", "cd"}).trim();
    expected = "Command Name:  cd DIR\n\n"
        + "Description: Changes the directory to a specified path.";

    assertEquals(expected, output);
  }

  @Test
  public void testLs() {
    output = man.check(new String[] {"man", "ls"}).trim();
    expected = "Command Name:  ls [-R] [PATH ...]\n\n"
        + "Description: Prints all contents of a directory if no path specified."
        + "\nIf path is provided, then prints all contents of that path.";

    assertEquals(expected, output);
  }

  @Test
  public void testPwd() {
    output = man.check(new String[] {"man", "pwd"}).trim();
    expected = "Command Name:  pwd\n\n"
        + "Description: Prints current working directory (including the whole\n"
        + "path).";

    assertEquals(expected, output);
  }

  @Test
  public void testPushd() {
    output = man.check(new String[] {"man", "pushd"}).trim();
    expected = "Command Name:  pushd DIR\n\n"
        + "Arguments: DIR a path to a directory\n\n"
        + "Description: Saves current working directory into directory stack\n"
        + "and then changes new current directory, to directory provided. The\n"
        + "pushd command saves the old current working directory in directory\n"
        + "stack so that it can be returned to at any time (via the popd\n"
        + "command). The size of the directory stack is dynamic and dependent\n"
        + "on the pushd and the popd commands.";

    assertEquals(expected, output);
  }

  @Test
  public void testPopd() {
    output = man.check(new String[] {"man", "popd"}).trim();
    expected = "Command Name:  popd\n"
        + "Description: Removes the top entry from directory stack and then\n"
        + "changes the current working directory to that directory.";

    assertEquals(expected, output);
  }

  @Test
  public void testHistory() {
    output = man.check(new String[] {"man", "history"}).trim();
    expected = "Command Name:  history [number]\n\n"
        + "Description: This command will print out the [number] most recent\n"
        + "commands in two columns. The first column is numbered such that the\n"
        + "line with the highest number is the most recent command. The most\n"
        + "recent command is history. The second column contains the actual\n"
        + "command.";

    assertEquals(expected, output);
  }

  @Test
  public void testCat() {
    output = man.check(new String[] {"man", "cat"}).trim();
    expected = "Command Name:  cat FILE1 [FILE2 ...]\n\n"
        + "Arguments: FILE1 is a path to a file. [FILE2 ...] means you may\n"
        + "include multiple paths to multiple files seperated with a space\n\n"
        + "Description: Display contents of the file provided. If multiple\n"
        + "files are provided, display all of them concatenated.";

    assertEquals(expected, output);
  }

  @Test
  public void testEcho() {
    output = man.check(new String[] {"man", "echo"}).trim();
    expected = "Command Name:  echo STRING [> OUTFILE]\n\n"
        + "Arguments: STRING must be wrapped in quotes\n\n"
        + "Description: If OUTFILE is not provided, prints string on to shell\n\n"
        + "if given a string. Otherwise if file is provided, overwrites string\n"
        + "into the given file. Creates a new file if file does not exist\n\n"
        + "Command Name:  echo STRING [>> OUTFILE]\n\n"
        + "Arguments: STRING must be wrapped in quotes\n\n"
        + "Description: exactly like echo STRING [> OUTFILE], except it appends\n"
        + "instead of overwrite";

    assertEquals(expected, output);
  }

  @Test
  public void testMan() {
    output = man.check(new String[] {"man", "man"}).trim();
    expected = "Command Name:  man CMD\n\n"
        + "Arguments: CMD refers to a single command of the shell\n\n"
        + "Description: Prints documentation for command (CMD) given.";

    assertEquals(expected, output);
  }

  @Test
  public void testSave() {
    output = man.check(new String[] {"man", "save"}).trim();
    expected = "Command Name:  save [FILENAME].ser\n\n"
        + "Arguments: CMD refers name of the file you want to save as\n\n"
        + "Description: Saves the current state of the file system to the file";

    assertEquals(expected, output);
  }

  @Test
  public void testLoad() {
    output = man.check(new String[] {"man", "load"}).trim();
    expected = "Command Name:  load [FILENAME].ser\n\n"
        + "Arguments: CMD refers name of the file you want to load\n\n"
        + "Description: Loads the state of the file system from the save file";

    assertEquals(expected, output);
  }

  @Test
  public void testRm() {
    output = man.check(new String[] {"man", "rm"}).trim();
    expected = "Command Name:  rm DIR \n\n"
        + "Arguments: DIR an existing directory\n\n"
        + "Description: Removes that directory from the file system";

    assertEquals(expected, output);
  }

  @Test
  public void testMv() {
    output = man.check(new String[] {"man", "mv"}).trim();
    expected = "Command Name:  mv OLDPATH NEWPATH \n\n"
        + "Arguments: OLDPATH existing path NEWPATH\n\n"
        + "Description: Moves OLDPATH to NEWPATH. OLDPATH cannot be a parent to\n"
        + "NEWPATH.";

    assertEquals(expected, output);
  }

  @Test
  public void testCp() {
    output = man.check(new String[] {"man", "cp"}).trim();
    expected = "Command Name:  cp OLDPATH NEWPATH \n\n"
        + "Arguments: OLDPATH existing path NEWPATH\n\n"
        + "Description: Copies OLDPATH to NEWPATH. OLDPATH cannot be a parent to\n"
        + "NEWPATH.";

    assertEquals(expected, output);
  }

  @Test
  public void testCurl() {
    output = man.check(new String[] {"man", "curl"}).trim();
    expected = "Command Name: curl URL \n\n" + "Arguments: URL url\n\n"
        + "Description:  Retrieve the file at URL and add it to the current working \n"
        + "directory.";

    assertEquals(expected, output);
  }

  @Test
  public void testFind() {
    output = man.check(new String[] {"man", "find"}).trim();
    expected = "Command Name: find path ... -type [f|d] -name EXPRESSION\n\n"
        + "Arguments:   path path you want to search. [f|d] file | directory. "
        + "EXPRESSION keyword\n\n"
        + "Description:  Find file or directory that have the name exactly as "
        + "EXPRESSION";

    assertEquals(expected, output);
  }

  @Test
  public void testTree() {
    output = man.check(new String[] {"man", "tree"}).trim();
    expected = "Command Name: tree\n\n"
        + "Description:  Visually displays the current filesystem to the terminal";

    assertEquals(expected, output);
  }
}
