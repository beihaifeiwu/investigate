package geotools.main;

import org.apache.tomcat.jdbc.pool.DataSourceFactory;
import org.h2gis.h2spatial.CreateSpatialExtension;
import org.h2gis.utilities.SFSUtilities;

import javax.sql.DataSource;
import java.lang.management.ManagementFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by LiuPin on 2016/3/10.
 */
public class H2GisTest {
  public static DataSource createDataSource() throws Exception {
    Properties properties = new Properties();
    properties.put("driverClassName", "org.h2.Driver");
    properties.put("url", "jdbc:h2:mem:maple");
    properties.put("username", "sa");
    properties.put("password", "sa");

    DataSource dataSource = new DataSourceFactory().createDataSource(properties);
    return SFSUtilities.wrapSpatialDataSource(dataSource);
  }

  public static void main(String[] args) {

    // setup logging env
    System.setProperty("PID", ManagementFactory.getRuntimeMXBean().getName().split("@")[0]);

    try {

      // Open memory H2 table
      try (Connection connection = createDataSource().getConnection();
           Statement st = connection.createStatement()) {

        // Import spatial functions, domains and drivers
        // If you are using a file database, you have to do only that once.
        CreateSpatialExtension.initSpatialExtension(connection);
        // Create a table
        st.execute("CREATE TABLE ROADS (the_geom MULTILINESTRING, speed_limit INT)");
        // Add some roads
        st.execute("INSERT INTO ROADS VALUES ('MULTILINESTRING((15 5, 20 6, 25 7))', 80)");
        st.execute("INSERT INTO ROADS VALUES ('MULTILINESTRING((20 6, 21 15, 21 25))', 50)");
        // Compute the sum of roads length
        try (ResultSet rs = st.executeQuery("SELECT SUM(ST_LENGTH(the_geom)) total_length from ROADS")) {
          if (rs.next()) {
            System.out.println("Total length of roads: " + rs.getDouble("total_length") + " m");
          }
        }

      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
