package investigate.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class ClipboardSample {

	public static void main(String[] args) {
		Display display = new Display();
		//定义剪贴板对象
		final Clipboard cb = new Clipboard(display);
		Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		shell.setText("Clipboard Sample");
		//定义弹出式菜单
		final Text content = new Text(shell, SWT.BORDER | SWT.MULTI | SWT.WRAP);
		Menu menu = new Menu(shell, SWT.POP_UP);
		//复制菜单项
		final MenuItem copyItem = new MenuItem(menu, SWT.PUSH);
		copyItem.setText("复制");
		//注册复制菜单项事件
		copyItem.addSelectionListener(new SelectionAdapter() {
			//当选中该菜单项时
			public void widgetSelected(SelectionEvent e) {
				//如果此时未选中任何文字，则不执行复制操作
				String selection = content.getSelectionText();
				if (selection.length() == 0)
					return;
				//定义放入到剪切板中的数据类型，可同时设置多种类型，这里指设置String类型
				Object[] data = new Object[] { selection };
				Transfer[] types = new Transfer[] { TextTransfer.getInstance() };
				//将字符放入到剪切板上
				cb.setContents(data, types);
			}
		});
		//粘贴菜单项
		final MenuItem pasteItem = new MenuItem(menu, SWT.PUSH);
		pasteItem.setText("粘贴");
		//注册粘贴菜单项事件
		pasteItem.addSelectionListener(new SelectionAdapter() {
			//当选中该菜单时
			public void widgetSelected(SelectionEvent e) {
				//将字符串从剪贴板中取出
				String string = (String) (cb.getContents(TextTransfer.getInstance()));
				//若不为空，则插入到文本框中
				if (string != null)
					content.insert(string);
			}
		});
		//为菜单注册事件
		menu.addMenuListener(new MenuAdapter() {
			//当显示菜单时
			public void menuShown(MenuEvent e) {
				//获得选中的文本
				String selection = content.getSelectionText();
				//如果没有选中任何文本，则将复制菜单项置为不可用
				copyItem.setEnabled(selection.length() > 0);
				//检查剪贴板所是否支持文本数据类型
				TransferData[] available = cb.getAvailableTypes();
				boolean enabled = false;
				for (int i = 0; i < available.length; i++) {
					if (TextTransfer.getInstance().isSupportedType(available[i])) {
						enabled = true;
						break;
					}
				}
				//如果支持，设置粘贴菜单项为可用状态
				pasteItem.setEnabled(enabled);
			}
		});
		content.setMenu(menu);

		shell.setSize(300, 150);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		//剪贴板调用的是系统资源，使用完需手工释放掉
		cb.dispose();
		display.dispose();
	}

}
