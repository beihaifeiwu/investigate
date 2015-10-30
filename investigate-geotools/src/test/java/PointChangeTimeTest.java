import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.junit.Test;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;
import java.util.concurrent.TimeUnit;

/**
 * Created by LiuPin on 2015/10/28.
 */
public class PointChangeTimeTest {

  @Test
  public void test(){
    OkHttpClient client = new OkHttpClient();
    double oldX = 0, oldY = 0;
    long count = 0;
    Long stay = null;
    while (true) {
      Request request = new Request.Builder()
          .url("http://location.palmap.cn/ws/protocol/position?mac=a0:86:c6:68:92:39")
          .build();
      try {
        long start = System.currentTimeMillis();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {

          String string = response.body().string();

          if (string.contains("error")) {
            System.out.println(String.format("%s - No result", count++));
          } else {
            JsonReader reader = Json.createReader(new StringReader(string.trim()));
            JsonObject object = reader.readObject();
            object = object.getJsonObject("query_location_response");
            if (object.isNull("location")) {
              System.out.println(String.format("%s - No result", count++));
            } else {
              object = object.getJsonObject("location");
              if (object == null) {
                System.out.println(String.format("%s - No result", count++));
              } else {
                double x = object.getJsonNumber("x").doubleValue();
                double y = object.getJsonNumber("y").doubleValue();

                String status;
                if (oldX != x || y != oldY) {
                  stay = null;
                  status = "Move";
                } else {
                  if (stay != null) {
                    stay++;
                  } else {
                    stay = 1L;
                  }
                  status = "Stay";
                }
                System.out.println(String.format("%s - %s - %s : x = %s y = %s, timespan = %sms", count++, stay == null ? 0 : stay, status, x, y, System.currentTimeMillis() - start));
                oldX = x;
                oldY = y;
              }
            }
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
