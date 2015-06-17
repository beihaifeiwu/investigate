package com.freetmp.investigate.vaadin;

import com.vaadin.server.StreamResource;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MyImageSource implements StreamResource.StreamSource {
  ByteArrayOutputStream imagebuffer = null;
  int reloads = 0;

  /* We need to implement this method that returns
   * the resource as a stream. */
  public InputStream getStream() {
        /* Create an image and draw something on it. */
    BufferedImage image = new BufferedImage(200, 200,
        BufferedImage.TYPE_INT_RGB);
    Graphics drawable = image.getGraphics();
    drawable.setColor(Color.lightGray);
    drawable.fillRect(0, 0, 200, 200);
    drawable.setColor(Color.yellow);
    drawable.fillOval(25, 25, 150, 150);
    drawable.setColor(Color.blue);
    drawable.drawRect(0, 0, 199, 199);
    drawable.setColor(Color.black);
    drawable.drawString("Reloads=" + reloads, 75, 100);
    reloads++;

    try {
            /* Write the image to a buffer. */
      imagebuffer = new ByteArrayOutputStream();
      ImageIO.write(image, "png", imagebuffer);
            
            /* Return a stream from the buffer. */
      return new ByteArrayInputStream(
          imagebuffer.toByteArray());
    } catch (IOException e) {
      return null;
    }
  }
}