package investigate.jface.actions;

import investigate.jface.MainWindow;
import investigate.jface.util.FileManager;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;


public class SaveAsAction extends Action {
	public SaveAsAction() {
		super();
		setText("另存为(&A)");
		setToolTipText("另存为");
		setImageDescriptor(ImageDescriptor.createFromFile(NewAction.class, "icons/saveas.gif"));
	}
	/*
	 * （非 Javadoc）
	 * 
	 * @see org.eclipse.jface.action.Action#run()
	 */
	public void run() {
		final FileManager fileManager = MainWindow.getApp().getManager();
		//弹出保存对话框
		FileDialog saveDialog = new FileDialog( MainWindow.getApp().getShell(),SWT.SAVE);
		saveDialog.setText("请选则所要保存的文件");
		saveDialog.setFilterPath("F:\\");
		saveDialog.setFilterExtensions(new String[] { "*.java", "*.*" });
		String saveFile = saveDialog.open();
		if ( saveFile != null )
		{
			fileManager.setFileName( saveFile );
			fileManager.setContent( MainWindow.getApp().getContent().getText() );
			fileManager.save( fileManager.getFileName());//保存文件
		}
		fileManager.setDirty( false );
		return ;
	}

}
