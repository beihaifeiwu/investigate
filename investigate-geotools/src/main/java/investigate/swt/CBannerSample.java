package investigate.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CBanner;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class CBannerSample {

	public static void main(String[] args) {
		final Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		shell.setText("CBanner");

		// 创建CBanner对象
		CBanner banner = new CBanner(shell, SWT.BORDER);
		banner.setLayout(new FillLayout());

		// 创建三个面板，分别放置到左侧，右侧和底部
		Composite left = new Composite(banner, SWT.NONE);
		left.setLayout(new FillLayout());
		new Text(left, SWT.MULTI).setText("左侧");

		Composite right = new Composite(banner, SWT.NONE);
		right.setLayout(new FillLayout());
		new Text(right, SWT.MULTI).setText("右侧");

		Composite bottom = new Composite(banner, SWT.NONE);
		bottom.setLayout(new FillLayout());
		new Text(bottom, SWT.MULTI).setText("下部");

		// 设置左侧的控件
		banner.setLeft(left);
		// 设置右侧的控件
		banner.setRight(right);
		// 设置底部的控件
		banner.setBottom(bottom);

		banner.setSimple(false);
		shell.setSize(200, 150);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();

	}

}
