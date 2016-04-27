package investigate.jface.preference;

import org.apache.commons.lang.SystemUtils;
import org.eclipse.jface.dialogs.IPageChangedListener;
import org.eclipse.jface.dialogs.PageChangedEvent;
import org.eclipse.jface.preference.*;
import org.eclipse.swt.widgets.Display;

import java.io.File;
import java.io.IOException;

public class PreferenceTest {

	public static void main(String[] args) {
		Display display = new Display();
		//创建一个PreferenceManager对象
		PreferenceManager manager = new PreferenceManager();
		//创建一个节点对象
		PreferenceNode nodeOne = new PreferenceNode("System", "系统设置", null, SystemSettingPage.class.getName());
		//将该节点添加到根节点中
		manager.addToRoot(nodeOne);
		//创建两个节点对象
		PreferenceNode one = new PreferenceNode("one", "第一页", null, PageOne.class.getName());
		PreferenceNode two = new PreferenceNode("two", "第二页", null, PageTwo.class.getName());
		//将第一页节点附加到System路径下
		manager.addTo("System",one);
		//将第二页节点附加到System.one路径下
		manager.addTo("System.one",two);
		PreferenceNode editorOne = new PreferenceNode("one", "编辑字段", null, FieldEditorPage.class.getName());
		manager.addToRoot(editorOne);
		/*********************
		//第二页节点为第一页节点的子节点
		one.add( two );
		//第一页节点为系统设置节点的子节点
		nodeOne.add(one);
		**********************/
		//manager.addTo("System",one);
		//manager.addTo("System.one",two);
		//定义一个首选项对话框，并将manager作为参数传入
		PreferenceDialog dlg = new PreferenceDialog(null, manager);
		//注册页面切换事件
		dlg.addPageChangedListener( new IPageChangedListener(){
			//当页面切换时
			public void pageChanged(PageChangedEvent event) {
				//获得当前页面
				IPreferencePage page = (IPreferencePage)event.getSelectedPage();
				//输出当前页面的标题
				System.out.println(page.getTitle());
			}
			
		});
		//创建保存选项设置值对象
    String path = new File(SystemUtils.getUserDir(), "investigate-geotools\\src\\main\\resources\\myPreference.properties").getAbsolutePath();
		PreferenceStore preferenceStore = new PreferenceStore(path);
		try {
			//装载该文件中的设置值
			preferenceStore.load();
			//将该设置赋值给该对话框
			dlg.setPreferenceStore(preferenceStore);
			//打开对话框
			dlg.open();
			//最后关闭对话框后，保存当前设置
			preferenceStore.save();
		} catch (IOException e) {
			e.printStackTrace();
		}
		display.dispose();
	}
}
