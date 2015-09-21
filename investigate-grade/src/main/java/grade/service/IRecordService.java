package grade.service;

import grade.entity.GradeRecord;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by liupin on 2015/9/20.
 */
public interface IRecordService {

  List<GradeRecord> list() throws SQLException;

  List<GradeRecord> queryByNameLike(String name) throws SQLException;

  Integer save(GradeRecord gradeRecord) throws SQLException;

  GradeRecord load(Integer id) throws SQLException;

  void update(GradeRecord gradeRecord) throws SQLException;

  void delete(Integer id) throws SQLException;

}
