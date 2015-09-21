package grade.entity;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@XmlRootElement
public class GradeRecord implements Serializable {

  private Integer id;

  private Double grade;

  private String name;

  private Date createTime;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Double getGrade() {
    return grade;
  }

  public void setGrade(Double grade) {
    this.grade = grade;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public GradeRecord() {
  }

  @Override
  public String toString() {
    return "GradeRecord{" +
        "id=" + id +
        ", grade=" + grade +
        ", name='" + name + '\'' +
        ", createTime=" + createTime +
        '}';
  }
}
