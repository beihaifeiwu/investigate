package grade.service.impl;

import grade.entity.GradeRecord;
import grade.service.IExportService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.List;

public class ExportServiceImpl implements IExportService {

  @XmlRootElement
  public static class GradeRecords {

    List<GradeRecord> records;

    public List<GradeRecord> getRecords() {
      return records;
    }

    public void setRecords(List<GradeRecord> records) {
      this.records = records;
    }

    public GradeRecords(List<GradeRecord> records) {
      this.records = records;
    }
  }

  public void exportGradeToXmlFile(List<GradeRecord> list, File file) {
    try {
      JAXBContext jbc = JAXBContext.newInstance(GradeRecords.class);
      Marshaller mar = jbc.createMarshaller();

      if(!file.exists()) file.mkdirs();
      mar.marshal(new GradeRecords(list), new File(file, "records.xml"));

    } catch (JAXBException e) {
      throw new RuntimeException(e);
    }
  }
}
