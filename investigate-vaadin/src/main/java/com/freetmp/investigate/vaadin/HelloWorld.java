package com.freetmp.investigate.vaadin;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.StreamResource;
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
    content.addComponent(new Button("Push Me!", (event) -> Notification.show("Pushed!")));

    // Create an instance of our stream source
    StreamResource.StreamSource imageSource = new MyImageSource();

    // Create a resource that uses the stream source and give it a name.
    // The constructor will automatically register the resource in
    // the application.
    StreamResource resource = new StreamResource(imageSource, System.currentTimeMillis() + ".png");

    // Create an image component that gets its contents from the resource.
    Image image = new Image("Image title", resource);
    content.addComponent(image);
    content.addComponent(new Button("Reload image", event -> image.setSource(new StreamResource(imageSource, System.currentTimeMillis() + ".png"))));
  }
}
