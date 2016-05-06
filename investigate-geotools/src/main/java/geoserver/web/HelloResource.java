package geoserver.web;

import org.geoserver.rest.AbstractResource;
import org.geoserver.rest.format.DataFormat;
import org.geoserver.rest.format.StringFormat;
import org.restlet.data.MediaType;
import org.restlet.data.Request;
import org.restlet.data.Response;

import java.util.Collections;
import java.util.List;

/**
 * Created by LiuPin on 2016/5/6.
 */
public class HelloResource extends AbstractResource {

  @Override protected List<DataFormat> createSupportedFormats(Request request, Response response) {

    return Collections.singletonList(new StringFormat(MediaType.TEXT_PLAIN));
  }

  @Override public void handleGet() {
    //get the appropriate format
    DataFormat format = getFormatGet();

    //transform the string "Hello World" to the appropriate response
    getResponse().setEntity(format.toRepresentation("Hello World"));
  }
}
