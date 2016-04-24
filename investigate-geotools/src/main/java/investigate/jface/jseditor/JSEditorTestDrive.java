package investigate.jface.jseditor;

import org.eclipse.swt.widgets.Display;

import java.io.IOException;

public class JSEditorTestDrive {
	public static void main(String[] args) {
		//创建主窗口对象
		JSEditor jsApp = new JSEditor();
		//为主窗口对象设置首选项对象
		//首选项对象通过ResourceManager中的静态方法获得
		jsApp.setPreference(ResourceManager.getPreferenceStore() );
		//并为首选项保存对象注册选项改变监听器
		ResourceManager.getPreferenceStore().addPropertyChangeListener(jsApp);
		jsApp.setBlockOnOpen(true);
		jsApp.open();
		Display.getCurrent().dispose();
		//窗口关闭后保存设置的首选项
		try {
			ResourceManager.getPreferenceStore().save();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
