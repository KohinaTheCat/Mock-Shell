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

import interfaces.RemoteDataFetcher;

/**
 * Class Name: MockRemoteData This class is meant to perform the simple function
 * of getting HTML from a URL for testing purposes
 */
public class MockRemoteData implements RemoteDataFetcher {

  @Override
  /**
   * Gets HTML from URL
   * 
   * @param String url address
   * @return String success/error message
   */
  public String getHTMLFromURL(String url) {
    if (url.equals("wwww.thisissosad.com/sadder.txt")) {
      return "Success!";
    }
    return "Wow, you really messed up didn't ya?";
  }

}
