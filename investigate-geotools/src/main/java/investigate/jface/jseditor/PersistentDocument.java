package investigate.jface.jseditor;

import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocumentListener;

import java.io.*;

public class PersistentDocument extends Document implements IDocumentListener {
	private String fileName;//打开的文件名
	private boolean dirty;//文件是否修改过
	public PersistentDocument() {
		this.addDocumentListener(this);//为该文档注册文档监听器
	}
	
	//保存到指定的文件
	public void save() throws IOException {
		if (fileName == null)
			throw new IllegalStateException("文件名不为空！");
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter(fileName));
			out.write(get());
			dirty = false;
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
			}
		}
	}
	//打开文件的方法
	public void open() throws IOException {
		if (fileName == null)
			throw new IllegalStateException("文件名不为空！");
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(fileName));
			StringBuffer buf = new StringBuffer();
			int n;
			while ((n = in.read()) != -1) {
				buf.append((char) n);
			}
			set(buf.toString());
			dirty = false;
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
			}
		}
	}
	public boolean isDirty() {
		return dirty;
	}
	public void setDirty(boolean dirty) {
		this.dirty = dirty;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	//接口中的方法，空实现
	public void documentAboutToBeChanged(DocumentEvent arg0) {

	}
	//接口中的方法，当文档修改后，设置已修改状态
	public void documentChanged(DocumentEvent arg0) {
		dirty = true;
	}
}
