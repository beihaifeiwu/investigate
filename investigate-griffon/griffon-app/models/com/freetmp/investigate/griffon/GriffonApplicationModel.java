package com.freetmp.investigate.griffon;

import griffon.core.artifact.GriffonModel;
import griffon.metadata.ArtifactProviderFor;
import griffon.transform.Observable;
import lombok.Data;
import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonModel;

@Data
@ArtifactProviderFor(GriffonModel.class)
public class GriffonApplicationModel extends AbstractGriffonModel {

  String scriptSource;
  @Observable Object scriptResult;
  @Observable boolean enabled = true;

}