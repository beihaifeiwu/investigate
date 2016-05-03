package investigate.form;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IFormPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

import java.io.File;
import java.util.Date;

public class DirectoryDetailPage implements IDetailsPage {

	private IManagedForm mform;
	private File file;
	private Section fileSection;
	private Text fileName;
	private Text filePath;
	private Text lastModify;
	private Button isRead;
	private Button isWrite;
	private Composite client;
	public void createContents(Composite parent) {
		//设置父类面板的布局
		TableWrapLayout layout = new TableWrapLayout();
		layout.topMargin = 5;
		layout.leftMargin = 5;
		layout.rightMargin = 2;
		layout.bottomMargin = 2;
		parent.setLayout(layout);
		//获得表单工具对象
		FormToolkit toolkit = mform.getToolkit();
		//创建Detail的内容区
		fileSection = toolkit.createSection(parent, Section.DESCRIPTION|Section.TITLE_BAR);
		TableWrapData td = new TableWrapData(TableWrapData.FILL, TableWrapData.TOP);
		td.grabHorizontal = true;
		fileSection.setLayoutData(td);
		//创建内容区的所设置的面板
		client = toolkit.createComposite(fileSection);
		fileSection.setClient( client );
		GridLayout gridLayout = new GridLayout();
		gridLayout.marginWidth = 0;
		gridLayout.marginHeight = 0;
		gridLayout.numColumns = 2;
		gridLayout.horizontalSpacing=10;
		client.setLayout(gridLayout);
		//创建Detail部分具体的各种控件
		toolkit.createLabel( client , "名称:");
		fileName = toolkit.createText( client ,"");
		toolkit.createLabel( client , "路径:");
		filePath = toolkit.createText( client , "");
		toolkit.createLabel( client , "最后修改:");
		lastModify = toolkit.createText( client , file!=null?new Date(file.lastModified()).toLocaleString():"");
		isRead = toolkit.createButton( client , "是否可读" ,SWT.CHECK);
		isWrite = toolkit.createButton( client , "是否可写" ,SWT.CHECK);
		
	}

	public void initialize(IManagedForm form) {
		this.mform = form ;
	}
	public void dispose() {
		
	}
	public boolean isDirty() {
		return false;
	}
	public void commit(boolean onSave) {
		
	}
	public boolean setFormInput(Object input) {
		
		return false;
	}
	public void setFocus() {
		
	}
	public boolean isStale() {
		return false;
	}
	public void refresh() {
		
	}
	//当Master区域选中事件发生时
	public void selectionChanged(IFormPart part, ISelection selection) {
		//首先获得选中的对象
		IStructuredSelection currentSelection = (IStructuredSelection)selection;
		if (currentSelection.size()==1) 
			file = (File)currentSelection.getFirstElement();
		//如果选中的对象不为null,则刷新控件所显示的值
		if (file != null)
			update();
	}
	//刷新值
	public void update (){
		//如果选中的为文件夹
		if ( file.isDirectory()){
			fileSection.setText("文件夹");
			fileSection.setDescription("这是一个文件夹");
		}else{//否则
			fileSection.setText("文件");
			fileSection.setDescription("这是一个文件");	
		}
		//设置文件名
		fileName.setText(file.getName());
		//设置路径
		filePath.setText(file.getAbsolutePath());
		//设置上次修改
		lastModify.setText(new Date(file.lastModified()).toLocaleString());
		//设置是否只读
		isRead.setSelection( file.canRead());
		//设置是否可写
		isWrite.setSelection( file.canWrite() );
	}
}
