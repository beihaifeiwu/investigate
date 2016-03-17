package com.freetmp.investigate.griffon;

import griffon.core.artifact.GriffonView;
import griffon.metadata.ArtifactProviderFor;
import lombok.Setter;
import org.codehaus.griffon.runtime.swing.artifact.AbstractSwingGriffonView;

import java.awt.*;

@ArtifactProviderFor(GriffonView.class)
public class GriffonApplicationView extends AbstractSwingGriffonView {
    @Setter GriffonApplicationModel model;
    @Setter GriffonApplicationController controller;

    @Override
    public void initUI() {

    }

    private Image getImage(String path) {
        return Toolkit.getDefaultToolkit().getImage(GriffonApplicationView.class.getResource(path));
    }
}