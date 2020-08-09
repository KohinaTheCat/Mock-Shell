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
package mockObjects;

import commands.Pwd;

/**
 * Class Name: MockPwd This class is meant to resemble the Pwd class for testing
 * purposes
 */
public class MockPwd extends Pwd {
  MockFileSystem fs;

  /**
   * Constructor for MockPwd
   * 
   * @param MockFileSystem the mock filesystem to run commands on
   */
  public MockPwd(MockFileSystem fs) {
    this.fs = fs;
  }

  /**
   * Runs the Pwd command
   * 
   * @return String full path of working directory
   */
  public String runPwd() {
    return fs.currPwd;
  }
}
