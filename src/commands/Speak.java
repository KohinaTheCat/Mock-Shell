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

import java.util.Locale;
import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import helpers.ErrorCheck;
import interfaces.CommandInterface;

/**
 * Class Name: Speak This class holds methods to convert user input to audio
 */
public class Speak implements CommandInterface {
  /*
   * Get instance of ErrorCheck class to use methods
   */
  private ErrorCheck errorCheck = new ErrorCheck();

  private boolean speak = false;
  private String speakModeString = "";
  private Synthesizer synthesizer;

  /**
   * Getter for speak
   * 
   * @return speak
   */
  public boolean isSpeak() {
    return speak;
  }

  /**
   * Setter for speak
   * 
   * @param speak
   */
  public void setSpeak(boolean speak) {
    this.speak = speak;
  }

  /**
   * Appends appendString to speakString
   * 
   * @param speakString
   */
  public void addSpeakString(String appendString) {
    this.speakModeString += " " + appendString;
  }

  /**
   * Converts speakString to audio
   */
  public void runSpeakWithSpeakModeString() {
    runSpeak(speakModeString);
  }

  /**
   * Converts input into audio
   * 
   * @param input Determines the audio
   */
  public void runSpeak(String input) {
    try {
      System.setProperty("freetts.voices",
          "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");

      Central.registerEngineCentral(
          "com.sun.speech.freetts.jsapi." + "FreeTTSEngineCentral");

      synthesizer =
          Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));

      synthesizer.allocate();

      synthesizer.resume();

      synthesizer.speakPlainText(input, null);
      synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);

      this.speakModeString = "";
      this.speak = false;

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Checks if arg are formatted correctly, and if it should continue to wait
   * for input. Run Speak command accordingly, and returns the output of the
   * command.
   *
   * @param arg Array of arguments
   * @return String Output of the Speak command for stdOut
   */
  public String check(String[] arg) {
    if (arg.length == 1) {
      speak = true;
      return "";
    }

    if (!errorCheck.isProperString(arg[1])) {
      return "";
    }

    runSpeak(arg[1].replaceAll("\"", ""));
    return "";
  }
}
