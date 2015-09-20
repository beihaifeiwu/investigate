package grade.dao;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

public class DbUtils {

  public static final String JDBC_URL = "grade.jdbc.url";
  public static final String JDBC_USERNAME = "grade.jdbc.username";
  public static final String JDBC_PASSWORD = "grade.jdbc.password";
  public static final String JDBC_POOL_SIZE = "grade.jdbc.pool.size";


  private final DataSource dataSource;
  private final ConcurrentLinkedQueue pool;

  private static DbUtils INSTANCE;

  public static synchronized void init() {
    INSTANCE = new DbUtils();
  }

  private DbUtils() {
    System.out.println("** init datasource");
    MysqlDataSource mysqlDataSource = new MysqlDataSource();
    if (System.getProperty(JDBC_URL) != null) {
      mysqlDataSource.setURL(System.getProperty(JDBC_URL));
    }
    if (System.getProperty(JDBC_USERNAME) != null) {
      mysqlDataSource.setUser(System.getProperty(JDBC_USERNAME));
    }
    if (System.getProperty(JDBC_PASSWORD) != null) {
      mysqlDataSource.setPassword(System.getProperty(JDBC_PASSWORD));
    }

    // init connection pool
    System.out.println("** init connection pool");
    dataSource = mysqlDataSource;
    pool = new ConcurrentLinkedQueue();
    Integer poolSize = 5;
    if (System.getProperty(JDBC_POOL_SIZE) != null) {
      poolSize = Integer.parseInt(System.getProperty(JDBC_POOL_SIZE));
    }
    for (int i = 0; i < poolSize; i++) {
      try {
        pool.add(dataSource.getConnection());
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }
  }

  public static DbUtils getCurrent(){
    return INSTANCE;
  }

  public Connection getConnection() {

    return (Connection) Proxy.newProxyInstance(
        Thread.currentThread().getContextClassLoader(),
        new Class<?>[]{Connection.class},
        (proxy, method, args) -> {
          // skip close method
          if (method.getName().equals("close")) return null;

          Connection connection = (Connection) pool.poll();
          Object object;
          try {
            object = method.invoke(connection, args);
          } finally {
            pool.offer(connection);
          }
          return object;
        });
  }

  public void destroy() {
    System.out.println("** destroy connection pool");
    Iterator<Connection> iterator = pool.iterator();
    while (iterator.hasNext()) {
      try {
        iterator.next().close();
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
