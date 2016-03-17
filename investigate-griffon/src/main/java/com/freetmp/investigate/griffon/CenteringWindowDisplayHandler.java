package com.freetmp.investigate.griffon;

import org.codehaus.griffon.runtime.swing.DefaultSwingWindowDisplayHandler;

import javax.annotation.Nonnull;
import java.awt.*;

import static griffon.swing.support.SwingUtils.centerOnScreen;

/**
 * Created by LiuPin on 2016/3/17.
 */
public class CenteringWindowDisplayHandler extends DefaultSwingWindowDisplayHandler {
  @Override public void show(@Nonnull String name, @Nonnull Window window) {
    centerOnScreen(window);
    super.show(name, window);
  }
}
