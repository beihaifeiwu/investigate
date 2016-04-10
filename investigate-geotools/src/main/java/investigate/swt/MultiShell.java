package investigate.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * Created by liupin on 2016/4/9.
 */
public class MultiShell {

  public static void main(String[] args) {
    Display display = new Display();
    final Shell parent = new Shell(display, SWT.SHELL_TRIM);
    parent.setText("多窗口示例");
    parent.setSize(300, 200);

    Button addShell = new Button(parent, SWT.CENTER);
    addShell.setText("创建子窗口");
    addShell.addSelectionListener(new SelectionAdapter() {
      @Override public void widgetSelected(SelectionEvent e) {
        createChildrenShell(parent, "子窗口");
      }
    });
    addShell.pack();

    parent.open();
    while (!parent.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    display.dispose();
  }

  public static Shell createChildrenShell(Shell parent, String childrenName) {
    Shell shell = new Shell(parent, SWT.DIALOG_TRIM);
    shell.setText(childrenName);
    shell.setSize(100, 100);
    shell.open();
    return shell;
  }
}
