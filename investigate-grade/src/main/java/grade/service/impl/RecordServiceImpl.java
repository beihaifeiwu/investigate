package grade.service.impl;

import grade.dao.DbUtils;
import grade.entity.GradeRecord;
import grade.service.IRecordService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecordServiceImpl implements IRecordService {

  public List<GradeRecord> list() throws SQLException {
    List<GradeRecord> result = new ArrayList<>();
    Connection conn = DbUtils.getCurrent().getConnection();
    PreparedStatement ptmt = conn.prepareStatement("select id,name,grade,createTime from graderecords");
    ResultSet rs = ptmt.executeQuery();
    GradeRecord g = null;
    while (rs.next()) {
      g = new GradeRecord();
      g.setId(rs.getInt("id"));
      g.setName(rs.getString("name"));
      g.setGrade(rs.getDouble("grade"));
      g.setCreateTime(rs.getDate("createTime"));
      result.add(g);
    }
    return result;
  }

  public List<GradeRecord> queryByNameLike(String name) throws SQLException {
    List<GradeRecord> result = new ArrayList<>();
    Connection conn = DbUtils.getCurrent().getConnection();
    PreparedStatement ptmt=conn.prepareStatement("select id,name,grade,createTime from graderecords " + " where name like ? ");
    String newName = "%"+name+"%";
    ptmt.setString(1,newName);
    ResultSet rs=ptmt.executeQuery();
    GradeRecord g = null;
    while (rs.next()) {
      g = new GradeRecord();
      g.setId(rs.getInt("id"));
      g.setName(rs.getString("name"));
      g.setGrade(rs.getDouble("grade"));
      g.setCreateTime(rs.getDate("createTime"));
      result.add(g);
    }
    return result;
  }

  public Integer save(GradeRecord gradeRecord) throws SQLException {
    Connection conn = DbUtils.getCurrent().getConnection();
    String sql = ""+
        "insert into graderecords"+
        "(name,grade,createTime)"+
        "values("+
        "?,?,?)";
    PreparedStatement ptmt = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
    ptmt.setString(1, gradeRecord.getName());
    ptmt.setDouble(2, gradeRecord.getGrade());
    ptmt.setDate(3,new java.sql.Date(System.currentTimeMillis()));
    ptmt.executeUpdate();
    int key = -1;
    ResultSet rs = ptmt.getGeneratedKeys();
    if(rs.next()){
      key = rs.getInt(1);
    }
    return key;
  }

  @Override
  public GradeRecord load(Integer id) throws SQLException {
    Connection conn = DbUtils.getCurrent().getConnection();
    GradeRecord g = null;
    String sql = ""+
        " select id,name,grade,createTime from graderecords "+
        " where id=?";
    PreparedStatement ptmt=conn.prepareStatement(sql);
    ptmt.setInt(1, id);
    ResultSet rs=ptmt.executeQuery();
    while(rs.next()){
      g = new GradeRecord();
      g.setId(rs.getInt("id"));
      g.setName(rs.getString("name"));
      g.setGrade(rs.getDouble("grade"));
      g.setCreateTime(rs.getDate("createTime"));
    }
    return g;
  }

  public void update(GradeRecord gradeRecord) throws SQLException {
    Connection conn = DbUtils.getCurrent().getConnection();
    String sql = ""+
        " update graderecords "+
        " set name=?,grade=?,createTime=? "+
        " where id=?";
    PreparedStatement ptmt=conn.prepareStatement(sql);
    ptmt.setString(1,gradeRecord.getName());
    ptmt.setDouble(2, gradeRecord.getGrade());
    ptmt.setDate(3,new java.sql.Date(System.currentTimeMillis()));
    ptmt.setInt(4, gradeRecord.getId());
    ptmt.execute();
  }

  public void delete(Integer id) throws SQLException {
    Connection conn = DbUtils.getCurrent().getConnection();
    String sql = ""+
        " delete from graderecords "+
        " where id=?";
    PreparedStatement ptmt=conn.prepareStatement(sql);
    ptmt.setInt(1, id);
    ptmt.execute();
  }
}
