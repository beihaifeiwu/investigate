package investigate.jface.actions;

import investigate.jface.MainWindow;
import investigate.jface.util.FileManager;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;

public class SaveAction extends Action{
	public SaveAction() {
		super();
		setText("保存(&S)");
		setToolTipText("保存文件");
		setImageDescriptor( ImageDescriptor.createFromFile(NewAction.class,"icons/save.gif"));
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see org.eclipse.jface.action.Action#run()
	 */
	public void run() {
		final FileManager fileManager = MainWindow.getApp().getManager();
		//如果文件没有修改过，则不保存
		if (!fileManager.isDirty())
			return ;
		//如果是新建的文件，首先要选择保存的路径
		if ( fileManager.getFileName() == null ){
			FileDialog saveDialog = new FileDialog( MainWindow.getApp().getShell(),SWT.SAVE);
			saveDialog.setText("请选则所要保存的文件");
			saveDialog.setFilterPath("F:\\");
			saveDialog.setFilterExtensions(new String[] { "*.java", "*.*" });
			String saveFile = saveDialog.open();
			if ( saveFile != null )
			{
				fileManager.setFileName( saveFile );
				fileManager.setContent( MainWindow.getApp().getContent().getText() );
				fileManager.save( fileManager.getFileName());
			}
			fileManager.setDirty( false );
			return ;
		}
		//如果是打开的文件，弹出对话框确认保存
		if (fileManager.getFileName()!= null){
			MessageBox box = new MessageBox( MainWindow.getApp().getShell() ,SWT.ICON_QUESTION|SWT.YES|SWT.NO);
			box.setText("保存");
			box.setMessage("您确定要保存文件吗？");
			int choice = box.open();
			if (choice==SWT.NO)
				return;
			fileManager.setContent( MainWindow.getApp().getContent().getText() );
			fileManager.save( fileManager.getFileName());
			fileManager.setDirty( false );
			return ;
		}
	}

}
