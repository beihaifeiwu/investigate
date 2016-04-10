package investigate.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * Created by liupin on 2016/4/9.
 */
public class TextSample {
  public static void main(String[] args) {
    Display display = new Display();
    final Shell parent = new Shell(display, SWT.SHELL_TRIM);
    parent.setText("文本框");
    parent.setSize(350, 250);

    Text content = new Text(parent, SWT.WRAP | SWT.V_SCROLL);
    content.setBounds(5, 5, 325, 200);

    Button selectAll = new Button(parent, SWT.NONE);
    selectAll.setText("全选");
    selectAll.setBounds(5, 225, 75, 25);
    selectAll.addSelectionListener(new SelectionAdapter() {
      @Override public void widgetSelected(SelectionEvent e) {
        //选中所有文本
        content.selectAll();
      }
    });

    Button cancel = new Button(parent, SWT.NONE);
    cancel.setText("取消全选");
    cancel.setBounds(90, 225, 75, 25);
    cancel.addSelectionListener(new SelectionAdapter() {
      @Override public void widgetSelected(SelectionEvent e) {
        // 如果有选中的文本
        if (content.getSelectionCount() > 0)
          content.clearSelection();
      }
    });

    Button copy = new Button(parent, SWT.NONE);
    copy.setText("复制");
    copy.setBounds(175, 225, 75, 25);
    copy.addSelectionListener(new SelectionAdapter() {
      @Override public void widgetSelected(SelectionEvent e) {
        // 复制到剪贴板
        content.copy();
      }
    });

    Button paste = new Button(parent, SWT.NONE);
    paste.setText("粘贴");
    paste.setBounds(260, 225, 75, 25);
    paste.addSelectionListener(new SelectionAdapter() {
      @Override public void widgetSelected(SelectionEvent e) {
        // 将剪贴板的内容粘贴
        content.paste();
      }
    });

    parent.pack();

    parent.open();
    while (!parent.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    display.dispose();
  }
}
