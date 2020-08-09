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

import java.net.*;
import helpers.StandardError;
import helpers.Redirection;
import java.io.*;
import interfaces.CommandInterface;
import interfaces.RemoteDataFetcher;

/**
 * Class Name: Curl This class gets the file at a URL and creates a file in the
 * current working directory
 */
public class Curl extends Redirection
    implements RemoteDataFetcher, CommandInterface {
  private RemoteDataFetcher rd;

  /*
   * Get instance of Pwd class to use method runPwd() to get the current working
   * directory
   */
  Pwd pwd = new Pwd();

  // Setters

  /**
   * Constructor for Curl command, designed for injection for purposes of
   * testing
   */
  public Curl(RemoteDataFetcher rd) {
    this.rd = rd;
  }

  /**
   * Constructor for Curl command
   */
  public Curl() {
    this.rd = this;
  }

  /**
   * Gets the HTML or text from url
   * 
   * @param url This is a URL to a text file or a web page
   */
  @Override
  public String getHTMLFromURL(String url) {
    try {
      URL oracle = new URL(url);
      URLConnection yc = oracle.openConnection();
      BufferedReader in =
          new BufferedReader(new InputStreamReader(yc.getInputStream()));
      String temp;
      String inputLine = "";

      while ((temp = in.readLine()) != null)
        inputLine += temp + "\n";

      in.close();

      return inputLine.trim();

    } catch (Exception e) {
      return null;
    }
  }

  private String runCurl(String url) {
    String fileName =
        url.substring(url.lastIndexOf("/") + 1, url.length()).trim();
    String content;

    // checks for proper file name that was parsed from the url
    if (errorCheck.invalidChar(fileName)) {
      return "";
    }

    // get content
    if ((content = rd.getHTMLFromURL(url)) == null) {
      StandardError.errors.add("Error: problem with the URL\n");
    } else {
      // use redirection class to create a file
      return overwrite(pwd.runPwd() + fileName, content);
    }

    return "";
  }

  /**
   * Runs the Curl command and returns output of the command.
   * 
   * @param arr Array of parsed input
   * @return String Output of the Curl command for stdOut
   */
  public String check(String[] arr) {
    return runCurl(arr[1]);
  }

}
