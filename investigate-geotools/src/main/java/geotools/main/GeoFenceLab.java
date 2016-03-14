package geotools.main;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.index.quadtree.Quadtree;
import org.geotools.data.Query;
import org.geotools.data.memory.MemoryDataStore;
import org.geotools.data.memory.MemoryFeatureSource;
import org.geotools.data.store.ContentEntry;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.geotools.feature.NameImpl;
import org.geotools.geojson.feature.FeatureJSON;
import org.geotools.geometry.jts.JTS;
import org.geotools.map.FeatureLayer;
import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.geotools.styling.SLD;
import org.geotools.styling.Style;
import org.geotools.swing.JMapFrame;
import org.opengis.feature.Feature;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.awt.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by LiuPin on 2016/2/29.
 */
@SuppressWarnings("unchecked")
public class GeoFenceLab {

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

  public static void showMap(FeatureCollection... collections) throws IOException {
    if(collections == null || collections.length < 1) return;
    MemoryDataStore dataStore = new MemoryDataStore(collections[0]);
    for(int i = 1; i < collections.length; i++)
      dataStore.addFeatures(collections[i]);

    ContentEntry entry = new ContentEntry(dataStore, new NameImpl("feature"));
    MemoryFeatureSource featureSource = new MemoryFeatureSource(entry, Query.ALL);

    MapContent map = new MapContent();
    map.setTitle("Palmap");
    Style style = SLD.createPolygonStyle(Color.BLUE, Color.MAGENTA, 0.3F);
    Layer layer = new FeatureLayer(featureSource, style);
    map.addLayer(layer);

    // Now display the map
    JMapFrame.showMap(map);
  }

  private static void checkGeoFence(Quadtree quadtree, GeometryFactory factory, FeatureCollection featureCollection) {
    double x = 13526579.652400002;
    double y = 3663436.222300001;

    Envelope envelope = new Envelope(x - 2,  x + 2, y - 2, y + 2);

    Polygon polygon1 = factory.createPolygon(new Coordinate[]{
        new Coordinate(x - 0.1, y + 0.1),
        new Coordinate(x + 0.1, y + 0.1),
        new Coordinate(x - 0.1, y - 0.1),
        new Coordinate(x + 0.1, y - 0.1),
        new Coordinate(x - 0.1, y + 0.1)

    });

    FeatureIterator iterator = featureCollection.features();
    Feature feature;
    while (iterator.hasNext()) {
      feature = iterator.next();
      Polygon polygon = JTS.toGeometry(feature.getBounds(), factory);
      if(feature.getIdentifier().getID().equals("1675")) {
        Envelope envelopeInternal = polygon.getEnvelopeInternal();
        System.out.println("1675 " + envelopeInternal.intersects(envelope));
        quadtree.insert(envelopeInternal, feature);
      }
      if(feature.getIdentifier().getID().equals("1674")){
        Envelope envelopeInternal = polygon.getEnvelopeInternal();
        System.out.println("1674 " + envelopeInternal.intersects(envelope));

        quadtree.insert(envelopeInternal, feature);
      }
      if(feature.getIdentifier().getID().equals("1689")){
        Envelope envelopeInternal = polygon.getEnvelopeInternal();
        System.out.println("1689 " + envelopeInternal.intersects(envelope));

        quadtree.insert(envelopeInternal, feature);
      }
    }

    List<Feature> list = quadtree.query(polygon1.getEnvelopeInternal());
    list.stream().map((f) -> f.getProperty("id").getValue()).forEach(System.out::println);
  }

  public static void main(String[] args) throws Exception {

    String json = getGeoJson();
    JsonReader reader = Json.createReader(new StringReader(json));
    JsonObject object = reader.readObject();

    JsonObject area = object.getJsonObject("Area");
    JsonObject frame = object.getJsonObject("Frame");
    JsonObject facility = object.getJsonObject("Facility");

    Quadtree quadtree = new Quadtree();

    GeometryFactory factory = new GeometryFactory();

    FeatureJSON featureJSON = new FeatureJSON();
    FeatureCollection featureCollection = featureJSON.readFeatureCollection(area.toString());

    showMap(featureCollection, featureJSON.readFeatureCollection(frame.toString()), featureJSON.readFeatureCollection(facility.toString()));

    checkGeoFence(quadtree, factory, featureCollection);
  }

}
