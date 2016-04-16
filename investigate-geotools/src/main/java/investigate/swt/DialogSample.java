package investigate.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.printing.PrintDialog;
import org.eclipse.swt.printing.Printer;
import org.eclipse.swt.printing.PrinterData;
import org.eclipse.swt.widgets.*;

import java.io.File;

public class DialogSample {

	public static void main(String[] args) {
		final Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout( new GridLayout());
		shell.setText("Dialog Sample");
		
		Button b1 = new Button ( shell,SWT.NONE);
		b1.setText("消息提示框");
		b1.addSelectionListener( new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				//创建消息框对象，使用警告图标并显示是和否按钮
				MessageBox box = new MessageBox( shell ,SWT.ICON_ERROR|SWT.YES|SWT.NO);
				//设置对话框的标题
				box.setText("错误消息对话框");
				//设置对话框显示的消息
				box.setMessage("读取文件发生错误!");
				//打开对话框，将返回值赋给choice
				int choice = box.open();
				//打印出所选择的值
				if (choice==SWT.YES)
					System.out.print("Yes");
				else if ( choice==SWT.NO)
					System.out.print("No");
			}
			
		});
		
		Button b2 = new Button ( shell,SWT.NONE);
		b2.setText("目录选取对话框");
		b2.addSelectionListener( new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog dialog = new DirectoryDialog(shell);
				//设置显示在窗口上方的提示信息
				dialog.setMessage("请选择所要保存的文件夹");
				//设置对话框的标题
				dialog.setText("选择文件目录");
				//设置打开时默认的文件目录
				dialog.setFilterPath("C:\\");
				//打开窗口，返回用户所选的文件目录
				String saveFile = dialog.open();
				if ( saveFile != null )
				{
					//创建一个File对象
					File directory = new File(saveFile);
					System.out.print(directory.getPath());
				}
			}
		});
		
		Button b3 = new Button ( shell,SWT.NONE);
		b3.setText("文件对话框");
		b3.addSelectionListener( new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				//创建一个打开对话框，样式设置为SWT.OPEN
				FileDialog dialog = new FileDialog(shell,SWT.OPEN);
				//设置打开默认的路径
				dialog.setFilterPath(System.getProperty("java.home"));
				//设置所打开文件的扩展名
				dialog.setFilterExtensions(new String[] {"*.txt", "*.*"});
				//设置显示到下拉框中的扩展名的名称
				dialog.setFilterNames( new String[]{"Text Files (*.txt)", "All Files (*.*)"});
				//打开窗口，返回用户所选的文件目录
				String file = dialog.open();
				if ( file != null )
				{
					System.out.print(file);
				}
			}
		});
		
		Button b4 = new Button ( shell,SWT.NONE);
		b4.setText("颜色对话框");
		b4.addSelectionListener( new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				//创建一个颜色对话框
				ColorDialog dialog = new ColorDialog(shell);
				//设置默认选中的颜色
				dialog.setRGB( new RGB( 255 ,255 ,128));
				//打开对话框，将选中的颜色返回给rgb对象
				RGB rgb = dialog.open();
				if ( rgb != null )
				{
					System.out.print(rgb);
					//创建颜色对象
					Color  color = new Color( display , rgb );
					//在使用完颜色对象后，释放资源
					color.dispose();
					
				}
			}
		});
		
		Button b5 = new Button ( shell,SWT.NONE);
		b5.setText("字体对话框");
		b5.addSelectionListener( new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				//创建一个字体对话框
				FontDialog  dialog = new FontDialog (shell);
				//设置默认选中的颜色
				dialog.setRGB( new RGB( 255 ,255 ,128));
				//打开对话框，将选中的字体返回给fontData对象
				FontData fontData = dialog.open();
				if ( fontData != null )
				{
					System.out.print(fontData);
					//创建颜色对象
					Font  font = new Font( display , fontData );
					//在使用完字体对象后，释放资源
					font.dispose();
				}
			}
		});
		
		Button b6 = new Button ( shell,SWT.NONE);
		b6.setText("打印对话框");
		b6.addSelectionListener( new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				//创建一个打印对话框
				PrintDialog  dialog = new PrintDialog (shell);
				//打开对话框，将选中的字体返回给fontData对象
				PrinterData printData = dialog.open();
				if ( printData != null )
				{
					//创建打印对象
					Printer  printer = new Printer( printData );
					//在使用打印对象后，释放资源
					printer.dispose();
				}
			}
		});
		
		//shell.setSize(200, 150);
		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();

	}
	
}
