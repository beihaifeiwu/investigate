package grade.service.impl;

import grade.entity.GradeRecord;
import grade.service.IRecordService;

import java.util.List;

public class RecordServiceImpl implements IRecordService {
  public List<GradeRecord> list() {
    System.out.println("** not supported operation!");
    return null;
  }

  public List<GradeRecord> queryByNameLike(String name) {
    System.out.println("** not supported operation!");
    return null;
  }

  public Integer save(GradeRecord gradeRecord) {
    System.out.println("** not supported operation!");
    return null;
  }

  @Override
  public GradeRecord load(Integer id) {
    System.out.println("** not supported operation!");
    return null;
  }

  public void update(GradeRecord gradeRecord) {
    System.out.println("** not supported operation!");
  }

  public void delete(Integer id) {
    System.out.println("** not supported operation!");
  }
}
