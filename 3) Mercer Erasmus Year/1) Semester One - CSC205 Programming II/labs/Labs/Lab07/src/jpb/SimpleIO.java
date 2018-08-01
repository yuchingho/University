//////////////////////////////////////////////////////////////
// From JAVA PROGRAMMING: FROM THE BEGINNING, by K. N. King //
// Copyright (c) 2000 W. W. Norton & Company, Inc.          //
// All rights reserved.                                     //
// This program may be freely distributed for class use,    //
// provided that this copyright notice is retained.         //
//                                                          //
// SimpleIO.java (Appendix E, page 763)                     //
//////////////////////////////////////////////////////////////

// Provides prompt and readLine methods for console input

package jpb;

import java.io.*;

public class SimpleIO {
  private static InputStreamReader streamIn =
    new InputStreamReader(System.in);
  private static BufferedReader in =
    new BufferedReader(streamIn, 1);

  // Displays the string s without terminating the current
  // line
  public static void prompt(String s) {
    System.out.print(s);
    System.out.flush();
  }

  // Reads and returns a single line of input entered by the
  // user; terminates the program if an exception is thrown
  public static String readLine() {
    String line = null;
    try {
      line = in.readLine();
    } catch (IOException e) {
      System.out.println("Error in SimpleIO.readLine: " +
                         e.getMessage());
      System.exit(-1);
    }
    return line;
  }
}
