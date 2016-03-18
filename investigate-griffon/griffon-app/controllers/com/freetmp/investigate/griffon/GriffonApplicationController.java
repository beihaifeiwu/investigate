package com.freetmp.investigate.griffon;

import griffon.core.artifact.GriffonController;
import griffon.metadata.ArtifactProviderFor;
import lombok.Setter;
import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonController;

import griffon.transform.Threading;

import javax.inject.Inject;

@ArtifactProviderFor(GriffonController.class)
public class GriffonApplicationController extends AbstractGriffonController {
  @Setter GriffonApplicationModel model;
  @Inject Evaluator evaluator;

  @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
  public void executeScript() {
    model.setEnabled(true);
    Object result = null;
    try {
      result = evaluator.evaluate(model.getScriptSource());
    } finally {
      model.setEnabled(false);
      model.setScriptResult(result);
    }
  }
}