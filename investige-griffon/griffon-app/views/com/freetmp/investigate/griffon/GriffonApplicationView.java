package com.freetmp.investigate.griffon;

import griffon.core.artifact.GriffonView;
import griffon.metadata.ArtifactProviderFor;
import org.codehaus.griffon.runtime.swing.artifact.AbstractSwingGriffonView;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collections;

import static java.util.Arrays.asList;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

@ArtifactProviderFor(GriffonView.class)
public class GriffonApplicationView extends AbstractSwingGriffonView {
    private GriffonApplicationModel model;
    private GriffonApplicationController controller;

    public void setModel(GriffonApplicationModel model) {
        this.model = model;
    }

    public void setController(GriffonApplicationController controller) {
        this.controller = controller;
    }

    @Override
    public void initUI() {
        JFrame window = (JFrame) getApplication()
            .createApplicationContainer(Collections.<String,Object>emptyMap());
        window.setName("mainWindow");
        window.setTitle(getApplication().getConfiguration().getAsString("application.title"));
        window.setSize(320, 120);
        window.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        window.setIconImage(getImage("/griffon-icon-48x48.png"));
        window.setIconImages(asList(
            getImage("/griffon-icon-48x48.png"),
            getImage("/griffon-icon-32x32.png"),
            getImage("/griffon-icon-16x16.png")
        ));
        getApplication().getWindowManager().attach("mainWindow", window);

        window.getContentPane().setLayout(new GridLayout(2, 1));

        final JLabel clickLabel = new JLabel(String.valueOf(model.getClickCount()));
        clickLabel.setName("clickLabel");
        clickLabel.setHorizontalAlignment(SwingConstants.CENTER);
        model.addPropertyChangeListener("clickCount", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                clickLabel.setText(String.valueOf(evt.getNewValue()));
            }
        });
        window.getContentPane().add(clickLabel);
        Action action = toolkitActionFor(controller, "click");
        JButton button = new JButton(action);
        button.setName("clickButton");
        window.getContentPane().add(button);
    }

    private Image getImage(String path) {
        return Toolkit.getDefaultToolkit().getImage(GriffonApplicationView.class.getResource(path));
    }
}