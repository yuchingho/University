//////////////////////////////////////////////////////////////
// From JAVA PROGRAMMING: FROM THE BEGINNING, by K. N. King //
// Copyright (c) 2000 W. W. Norton & Company, Inc.          //
// All rights reserved.                                     //
// This program may be freely distributed for class use,    //
// provided that this copyright notice is retained.         //
//                                                          //
// DrawableFrame.java (Appendix E, page 761)                //
//////////////////////////////////////////////////////////////

// A frame in which drawings can be displayed

package jpb;

import java.awt.*;
import java.awt.event.*;

public class DrawableFrame extends Frame {
  private Image imageBuffer = null;
  private Insets insets;

  // Constructor
  public DrawableFrame(String title) {
    super(title);
    addWindowListener(new WindowCloser());
  }

  // Called automatically to display the contents of the
  // frame
  public void paint(Graphics g) {
    if (imageBuffer != null)
      g.drawImage(imageBuffer, insets.left, insets.top, null);
  }

  // Called automatically by repaint. Used to reduce flicker.
  public void update(Graphics g) {
    paint(g);
  }

  // Sets the size of the frame. The width and height
  // parameters control the size of the drawable portion of
  // the frame. The frame itself is somewhat larger.
  public void setSize(int width, int height) {
    insets = getInsets();
    super.setSize(width + insets.left + insets.right,
                  height + insets.top + insets.bottom);
    imageBuffer = createImage(width, height);
  }

  // Returns the graphics context associated with the image
  // buffer
  public Graphics getGraphicsContext() {
    return imageBuffer.getGraphics();
  }

  // Listener for window
  class WindowCloser extends WindowAdapter {
    public void windowClosing(WindowEvent evt) {
      System.exit(0);
    }
  }
}
