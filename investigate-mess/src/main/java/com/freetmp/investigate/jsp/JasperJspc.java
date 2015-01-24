package com.freetmp.investigate.jsp;

import java.io.File;

import org.apache.jasper.JasperException;
import org.apache.jasper.JspC;

/**
 * 测试Jasper编译的效果
 * @author pin
 */
public class JasperJspc {

	public static void main(String[] args) throws JasperException {
		JspC jspc = new JspC();
		String path = new File("src/main/java/com/freetmp/investigate/jsp/").getAbsolutePath();
		jspc.setUriroot(path);
		jspc.setOutputDir(path);
		jspc.setPackage("");
		jspc.setJspFiles("test.jsp");
		jspc.setCompile(false);
		jspc.execute();
	}
}
