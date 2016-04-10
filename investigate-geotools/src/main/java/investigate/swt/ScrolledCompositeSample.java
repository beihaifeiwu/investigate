package investigate.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class ScrolledCompositeSample {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		shell.setText("ScrolledComposite");

		// 创建一个滚动面板对象
		final ScrolledComposite sc = new ScrolledComposite(shell, SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.BORDER);
		// 创建一个普通的面板
		final Composite c = new Composite(sc, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 4;
		c.setLayout(layout);
		for (int i = 0; i < 20; i++) {
			Button bt = new Button(c, SWT.PUSH);
			bt.setText("按钮" + i);
			c.setSize(c.computeSize(SWT.DEFAULT, SWT.DEFAULT));

		}
		// 将普通面板设置为受控的滚动面板
		sc.setContent(c);
		// sc.setAlwaysShowScrollBars( true );
		// sc.setExpandHorizontal( true );
		shell.setSize(200, 150);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();

	}

}
