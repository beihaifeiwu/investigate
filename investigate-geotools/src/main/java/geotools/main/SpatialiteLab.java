package geotools.main;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.SystemUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by LiuPin on 2016/4/11.
 */
public class SpatialiteLab {

  public static final String WORK_DIR = SystemUtils.USER_HOME + File.separator + ".investigate";
  public static final String LIB_DIR = WORK_DIR + File.separator + "lib";

  static {
    // setup working dir
    File work = new File(WORK_DIR);
    if (!work.exists()) work.mkdirs();

    // setup libs dir
    File lib = new File(LIB_DIR);
    if (!lib.exists()) lib.mkdirs();

    // load the spatialite libs
    if (SystemUtils.IS_OS_WINDOWS) {
      try {
        setUpLib(lib, "spatialite-libs-win-x86", "libgeos-3-1-1.dll");
        setUpLib(lib, "spatialite-libs-win-x86", "libgeos_c-1.dll");
        setUpLib(lib, "spatialite-libs-win-x86", "libiconv2.dll");
        setUpLib(lib, "spatialite-libs-win-x86", "libproj-0.dll");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  static void setUpLib(File lib, String resourceDir, String libName) throws IOException {
    ClassLoader classLoader = SpatialiteLab.class.getClassLoader();
    File dest = new File(lib, libName);
    FileUtils.copyInputStreamToFile(classLoader.getResourceAsStream(resourceDir + File.separator + libName), dest);
    System.load(dest.getAbsolutePath());
  }

}
