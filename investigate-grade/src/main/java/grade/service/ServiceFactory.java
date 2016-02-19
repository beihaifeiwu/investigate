package grade.service;

import grade.service.impl.ExportServiceImpl;
import grade.service.impl.RecordServiceImpl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServiceFactory {

  private static IExportService exportService = new ExportServiceImpl();

  private static IRecordService recordService = new RecordServiceImpl();

  static ExecutorService executorService = Executors.newFixedThreadPool(10);

  // user thread pool to surround the export service
  public static IExportService getExportService(){
    Proxy.newProxyInstance(
        Thread.currentThread().getContextClassLoader(),
        new Class[]{IExportService.class},
        (proxy, method, args) -> {
          executorService.execute(() -> {
            try {
              method.invoke(exportService, args);
            } catch (IllegalAccessException | InvocationTargetException e) {
              e.printStackTrace();
            }
          });
          return null;
        }
    );
    return exportService;
  }

  public static IRecordService getRecordService(){
    return recordService;
  }

}
