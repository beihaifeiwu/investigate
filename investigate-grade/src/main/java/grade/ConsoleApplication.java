package grade;

import grade.dao.DbUtils;
import grade.entity.GradeRecord;
import grade.service.ServiceFactory;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ConsoleApplication {

  public static void main(String[] args) {
    System.out.println("** Starting Grade Manager...");
    Scanner scanner = new Scanner(System.in);

    // setup datasource property
    System.setProperty(DbUtils.JDBC_URL, "jdbc:mysql://localhost:3306/record");
    System.setProperty(DbUtils.JDBC_USERNAME, "root");
    System.setProperty(DbUtils.JDBC_PASSWORD, "root");
    System.setProperty(DbUtils.JDBC_POOL_SIZE, "10");

    DbUtils.init();

    // main application loop
    boolean exit = false;
    while (!exit){
      printPrompt();
      Integer option = scanner.nextInt();
      switch (option){
        case 1: list(scanner); break;
        case 2: save(scanner); break;
        case 3: update(scanner); break;
        case 4: delete(scanner); break;
        case 5: query(scanner); break;
        case 6: export(scanner); break;
        case 7: exit = true; exit(); break;
        default:
          System.out.println("** not supported operation!");
      }
    }
  }

  static void list(Scanner scanner){
    List<GradeRecord> records = ServiceFactory.getRecordService().list();
    printResult(records);
  }

  static void save(Scanner scanner){
    System.out.print(" name: ");
    String name = scanner.next();
    System.out.print(" grade: ");
    double grade = scanner.nextDouble();
    GradeRecord gradeRecord = new GradeRecord();
    gradeRecord.setName(name);
    gradeRecord.setGrade(grade);
    gradeRecord.setCreateTime(new Date());
    Integer id = ServiceFactory.getRecordService().save(gradeRecord);
    gradeRecord.setId(id);
    System.out.println("save grade " + gradeRecord);
  }

  static void update(Scanner scanner){
    System.out.print(" input grade id: ");
    Integer id = scanner.nextInt();
    GradeRecord record = ServiceFactory.getRecordService().load(id);
    if(record == null){
      System.out.println("[WARN] grade with id[" + id + "] not exist");
      return;
    }
    System.out.print(" change name? [y/n]: ");
    String res = scanner.next();
    if(res.equalsIgnoreCase("y")){
      System.out.print(" new name: ");
      String name = scanner.next();
      record.setName(name);
    }

    System.out.print(" change grade? [y/n]: ");
    res = scanner.next();
    if(res.equalsIgnoreCase("y")){
      System.out.print(" new grade: ");
      Double grade = scanner.nextDouble();
      record.setGrade(grade);
    }

    ServiceFactory.getRecordService().update(record);
    System.out.println("update grade " + record);
  }

  static void delete(Scanner scanner){
    System.out.print(" input grade id: ");
    Integer id = scanner.nextInt();
    GradeRecord record = ServiceFactory.getRecordService().load(id);
    if(record == null){
      System.out.println("[WARN] grade with id[" + id + "] not exist");
      return;
    }
    ServiceFactory.getRecordService().delete(id);
    System.out.println("delete grade " + record);
  }

  static void query(Scanner scanner){
    System.out.print("  input name literal: ");
    String name = scanner.next();
    List<GradeRecord> records = ServiceFactory.getRecordService().queryByNameLike(name);
    printResult(records);
  }

  static void export(Scanner scanner){
    System.out.print(" input stored path: ");
    String path = scanner.next();
    File file = new File(path);
    List<GradeRecord> records = ServiceFactory.getRecordService().list();
    ServiceFactory.getExportService().exportGradeToXmlFile(records, file);
    System.out.println(" export task will be start in the background...");
  }

  static void exit(){
    DbUtils.getCurrent().destroy();
    System.out.println("** bye");
  }

  static void printPrompt(){
    System.out.println("** Please select your operation:");
    System.out.println("** 1. list all grade record");
    System.out.println("** 2. save new grade record");
    System.out.println("** 3. update grade record");
    System.out.println("** 4. delete grade record");
    System.out.println("** 5. query grade record by name like");
    System.out.println("** 6. export grade record to xml");
    System.out.println("** 7. exit");
  }

  static void printResult(List<GradeRecord> list){
    if (list == null){
      System.out.println("  no records found ");
      return;
    }
    System.out.println();
    list.forEach(System.out::println);
    System.out.println();
  }
}