package grade.service;

import grade.entity.GradeRecord;

import java.util.List;

/**
 * Created by liupin on 2015/9/20.
 */
public interface IRecordService {

  List<GradeRecord> list();

  List<GradeRecord> queryByNameLike(String name);

  Integer save(GradeRecord gradeRecord);

  GradeRecord load(Integer id);

  void update(GradeRecord gradeRecord);

  void delete(Integer id);

}
