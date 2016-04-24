package investigate.jface.resource;

import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.FontRegistry;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

public class FontAndColorTest extends ApplicationWindow {

	//使用的key常量
	private static final String FONT_ONE = "font_one";
	private static final String FONT_TWO = "font_two";
	private static final String COLOR_BLUE = "blue";
	private static final String COLOR_GREEN = "green";
	//FontRegistry对象
	private static FontRegistry  fontRegistry;
	//ColorRegistry对象
	private static ColorRegistry  colorRegistry;
	public FontAndColorTest() {
		super(null);
	}
	
	private void loadFontAndColor() {
		//初始化字体和颜色注册器对象
		fontRegistry = new FontRegistry();
		colorRegistry = new ColorRegistry();
		//添加字体
		FontData fontData = new FontData("隶书",24, SWT.BOLD);
		fontRegistry.put(FONT_ONE,new FontData[]{ fontData });
		fontData = new FontData("楷体",30, SWT.NORMAL);
		fontRegistry.put(FONT_TWO,new FontData[]{ fontData });
		//添加颜色
		colorRegistry.put(COLOR_BLUE, new RGB(0,128,255));
		colorRegistry.put(COLOR_GREEN, new RGB(128,255,128));
		
	}

	protected Control createContents(Composite parent) {
		//初始华字体和颜色
		loadFontAndColor();
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new FillLayout());
		//设置两个字体和颜色不同的标签
		Label label = new Label( composite , SWT.NONE);
		label.setText("样式一");
		label.setFont( fontRegistry.getItalic(FONT_ONE));
		label.setBackground( colorRegistry.get(COLOR_BLUE ));
		
		label = new Label( composite , SWT.NONE);
		label.setText("样式二");
		label.setFont( fontRegistry.getBold(FONT_TWO));
		label.setBackground( colorRegistry.get(COLOR_GREEN ));
		
		return parent;
	}

	public static void main(String[] args) {
		FontAndColorTest test = new FontAndColorTest();
		test.setBlockOnOpen(true);
		test.open();
		Display.getCurrent().dispose();
	}
}
