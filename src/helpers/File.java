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
 * Class Name: File This class outlines a file object
 */
public class File extends Node {

  // Class variables
  // private String fileName;
  private String fileContent;

  /**
   * Default constructor
   * 
   * @param fileName Name of file
   * @param fileContent Content of file
   */
  public File(String fileName, String fileContent) {
    super.setName(fileName);
    setFileContent(fileContent);
  }

  /**
   * Gets file name
   * 
   * @return fileName Name of file
   */
  public String getFileName() {
    return super.getName();
  }

  /**
   * Sets file name.
   * 
   * @param fileName Name of file
   */
  public void setFileName(String fileName) {
    super.setName(fileName);
  }

  /**
   * Gets file content
   * 
   * @return fileContent Content of file
   */
  public String getFileContent() {
    return fileContent;
  }

  /**
   * Sets fileContent, but also doubles as an overwriting function
   * 
   * @param fileContent Content of file
   */
  public void setFileContent(String fileContent) {
    this.fileContent = fileContent;
  }

  /**
   * Appends given string to content
   * 
   * @param fileContent Content of file
   */
  public void appendFileContent(String fileContent) {
    if (this.fileContent != null) {
      this.fileContent += "\n" + fileContent;
    } else {
      setFileContent(fileContent);
    }
  }

}
