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

package interfaces;

/**
 * Interface Name: RemoteDataFetcher This interface allows the Curl to implement
 * getHTMLFromURL. Allows us to test the class Curl using injection
 */
public interface RemoteDataFetcher {

  /**
   * Gets the HTML or text from url
   * 
   * @param url This is a URL to a text file or a web page
   */
  String getHTMLFromURL(String url);
}
