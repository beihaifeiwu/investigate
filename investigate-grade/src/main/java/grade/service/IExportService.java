package grade.service;

import grade.entity.GradeRecord;

import java.io.File;
import java.util.List;

/**
 * Created by liupin on 2015/9/20.
 */
public interface IExportService {

  void exportGradeToXmlFile(List<GradeRecord> list, File file);
}
