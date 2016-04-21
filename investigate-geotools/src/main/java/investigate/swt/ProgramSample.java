package investigate.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.awt.*;

public class ProgramSample {
	public static void main(String[] args) {
    programDemo();
    swtAwtSample();
	}

  private static void programDemo() {
    Display display = new Display ();
    String[] s = Program.getExtensions();
    for (int i =0;i<s.length;i++){
      System.out.println(s[i]);
    }
    Program[] programs =Program.getPrograms();
    for (int i =0;i<programs.length;i++){
      System.out.println(programs[i].getName());
    }
    Program p = Program.findProgram (".pdf");
    if (p != null)
      p.execute ("F:\\Text Print.pdf");
    Program.launch("D:\\Program Files\\Internet Explorer\\IEXPLORE.EXE");
    display.dispose ();
  }

  private static void swtAwtSample(){
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setText("SWT and Swing/AWT Example");
    shell.setLayout( new FillLayout());
    //定义一个面板类，样式设置为SWT.EMBEDDED
    Composite composite = new Composite(shell, SWT.EMBEDDED);
    //将该面板对象转化成AWT中的Frame对象
    java.awt.Frame awtFrame = SWT_AWT.new_Frame(composite);
    awtFrame.setSize(200,150);
    awtFrame.setVisible( true );
    //添加一个AWT标签
    java.awt.Label label = new Label();
    label.setText("这是一个AWT标签");
    awtFrame.add( label ,java.awt.BorderLayout.NORTH);
    ////添加一个AWT文本框
    TextField textField  = new TextField();
    textField.setText("这是一个AWT文本框");
    awtFrame.add( textField ,java.awt.BorderLayout.CENTER);
    shell.pack();
    shell.open();
    while(!shell.isDisposed()) {
      if (!display.readAndDispatch()) display.sleep();
    }
    display.dispose();
  }
}
