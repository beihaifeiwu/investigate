package com.freetmp.investigate.vaadin;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;

/**
 * Created by LiuPin on 2015/6/16.
 */
@Title("My UI") @Theme("valo")
public class HelloWorld extends UI {
  @Override protected void init(VaadinRequest vaadinRequest) {
    // Create the content root layout for the UI
    VerticalLayout content = new VerticalLayout();
    setContent(content);

    // Display the greeting
    content.addComponent(new Label("Hello World!"));

    // Have a clickable button
    content.addComponent(new Button("Push Me!",(event)-> Notification.show("Pushed!")));
  }
}
