package investigate.form;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.editor.FormEditor;

public class MyMutiForm extends FormEditor {

	public static final String ID = "com.fengmanfei.myrcp.forms.MyMutiForm";
	public MyMutiForm() {
		super();
	}
	//实现父类中的抽象方法，
	//在该方法中创建每个页面
	protected void addPages() {
		try {
			//添加两个页面
			addPage(new FirstPage(this));
			addPage(new SecondPage(this));
			addPage(new MasterDetailPage(this));
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}
	//保存该编辑器时调用该方法
	public void doSave(IProgressMonitor monitor) {
	}
	//另存为该编辑器时
	public void doSaveAs() {
	}
	//是否允许保存
	public boolean isSaveAsAllowed() {
		return false;
	}

}
