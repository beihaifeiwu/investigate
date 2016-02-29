package geotools.main;

import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.geotools.geojson.feature.FeatureJSON;
import org.opengis.feature.Feature;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by LiuPin on 2016/2/29.
 */
public class GeoFenceTest {

  public static String getGeoJson() throws Exception {
    URL url = new URL("http://api.ipalmap.com/planar_graph/1672");
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestProperty("Accept", "application/json");
    connection.setRequestProperty("NAG-Key", "3474b094bcbb4279a2f2c1fa2945cae4");

    if (connection.getResponseCode() >= 300)
      throw new Exception("HTTP Request is not success, Response code is " + connection.getResponseCode());

    StringBuilder sb = new StringBuilder();

    try (InputStream inputStream = connection.getInputStream()) {
      BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
      String temp;
      while ((temp = reader.readLine()) != null)
        sb.append(temp);
    }

    return sb.toString();
  }

  public static void main(String[] args) throws Exception {

    String json = getGeoJson();
    JsonReader reader = Json.createReader(new StringReader(json));
    JsonObject object = reader.readObject().getJsonObject("Area");

    FeatureJSON featureJSON = new FeatureJSON();
    FeatureCollection featureCollection = featureJSON.readFeatureCollection(object.toString());
    FeatureIterator iterator = featureCollection.features();

    Feature feature;
    while (iterator.hasNext()){
      feature = iterator.next();
      System.out.println(feature.getBounds());
    }
  }

}
