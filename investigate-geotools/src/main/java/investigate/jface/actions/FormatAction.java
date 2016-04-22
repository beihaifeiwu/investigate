package investigate.jface.actions;

import investigate.jface.MainWindow;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.FontDialog;
import org.eclipse.swt.widgets.Text;

public class FormatAction extends Action {
	public static final String TYPE_FORECOLOR = "FORECOLOR";
	public static final String TYPE_BGCOLOR = "BGCOLOR";
	public static final String TYPE_FONT = "FONT";
	private String formatType;// 通过不同的类型来构造不同的处理事件
	public FormatAction(String type) {
		super();
		this.formatType = type;
		initAction();
	}

	private void initAction() {
		if (formatType.equals(TYPE_FONT)) {
			this.setText("设置字体");
			this.setToolTipText("设置字体");
			setImageDescriptor(ImageDescriptor.createFromFile(NewAction.class, "icons/font.gif"));
		} else if (formatType.equals(TYPE_FORECOLOR)) {
			this.setText("设置前景色");
			this.setToolTipText("设置前景色");
			setImageDescriptor(ImageDescriptor.createFromFile(NewAction.class, "icons/foreColor.gif"));
		} else {
			this.setText("设置背景色");
			this.setToolTipText("设置背景色");
			setImageDescriptor(ImageDescriptor.createFromFile(NewAction.class, "icons/bgColor.gif"));
		}

	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see org.eclipse.jface.action.Action#run()
	 */
	public void run() {
		Text content = MainWindow.getApp().getContent();
		if (formatType.equals(TYPE_FONT)) {
			FontDialog fontDialog = new FontDialog(MainWindow.getApp().getShell());
			fontDialog.setFontList(content.getFont().getFontData());
			FontData fontData = fontDialog.open();
			if (fontData != null) {
				Font font = new Font(MainWindow.getApp().getShell().getDisplay(), fontData);
				content.setFont(font);
			}
		} else if (formatType.equals(TYPE_FORECOLOR)) {
			ColorDialog colorDialog = new ColorDialog(MainWindow.getApp().getShell());
			colorDialog.setRGB(content.getForeground().getRGB());
			RGB rgb = colorDialog.open();
			if (rgb != null) {
				Color foregroundColor = new Color(MainWindow.getApp().getShell().getDisplay(), rgb);
				content.setForeground(foregroundColor);
				foregroundColor.dispose();
			}
		} else {
			ColorDialog colorDialog = new ColorDialog(MainWindow.getApp().getShell());
			colorDialog.setRGB(content.getBackground().getRGB());
			RGB rgb = colorDialog.open();
			if (rgb != null) {
				Color bgColor = new Color(MainWindow.getApp().getShell().getDisplay(), rgb);
				content.setBackground(bgColor);
				bgColor.dispose();
			}
		}
	}
}
