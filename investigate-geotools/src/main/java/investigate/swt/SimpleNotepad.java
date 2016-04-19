package investigate.swt;

import investigate.swt.util.ImageFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.*;


public class SimpleNotepad {

	private Shell sShell = null;
	private ViewForm viewForm = null;
	private ToolBar toolBar = null;
	private Composite composite = null;
	private StyledText styledText = null;
	private void createViewForm() {
		viewForm = new ViewForm(sShell, SWT.NONE);
		createToolBar();
		viewForm.setTopLeft(toolBar);
		createComposite();
		viewForm.setContent(composite);
	}

	private void createToolBar() {
		//创建工具栏和工具栏按钮
		toolBar = new ToolBar(viewForm, SWT.FLAT);
		final ToolItem boldButton = new ToolItem(toolBar, SWT.PUSH);
		boldButton.setImage( ImageFactory.loadImage(toolBar.getDisplay(),ImageFactory.BOLD));
		final ToolItem italicButton = new ToolItem(toolBar, SWT.PUSH);
		italicButton.setImage( ImageFactory.loadImage(toolBar.getDisplay(),ImageFactory.ITALIC));
		final ToolItem underlineButton = new ToolItem(toolBar, SWT.PUSH);
		underlineButton.setImage( ImageFactory.loadImage(toolBar.getDisplay(),ImageFactory.UNDERLIN));
		
		new ToolItem(toolBar, SWT.SEPARATOR);
		final ToolItem bgColorButton = new ToolItem(toolBar, SWT.PUSH);
		bgColorButton.setImage( ImageFactory.loadImage(toolBar.getDisplay(),ImageFactory.BGCOLOR));
		final ToolItem forColorButton = new ToolItem(toolBar, SWT.PUSH);
		forColorButton.setImage( ImageFactory.loadImage(toolBar.getDisplay(),ImageFactory.FORCOLOR));
		//创建按钮事件处理对象
		Listener listener = new Listener(){
			public void handleEvent(Event event) {
				//如果当前未选中文本返回，不处理任何事件
				if (styledText.getSelectionCount()==0)
					return;
				System.out.println(styledText.getSelection().toString());
				System.out.println(styledText.getSelectionRange().toString());
				//声明一个StyleRange对象
				StyleRange styleRange=null;
				//获得当前所选择文本所在的开始位置和长度
				Point select = styledText.getSelectionRange();
				//首先要查找一下当前所选中文本中是否已经有设置过的StyleRange对象
				StyleRange[] ranges = styledText.getStyleRanges( select.x,select.y);
				//如果找到了，则将该选中字符串的第一个StyleRange样式作为当前的所要改变的样式
				//否则创建一个新样式
				if( ranges.length>0)
					styleRange=ranges[0];
				else 
					styleRange = new StyleRange();
				//设置样式的所作用的位置为选择文本的位置
				styleRange.start=select.x;
				styleRange.length=select.y;
				//如果单击的为加粗按钮，则要设置字体样式为SWT.BOLD
				if (event.widget==boldButton)
					styleRange.fontStyle=styleRange.fontStyle|SWT.BOLD;
				//如果单击的为倾斜按钮，则要设置字体样式为SWT.ITALIC
				else if (event.widget==italicButton)
					styleRange.fontStyle=styleRange.fontStyle|SWT.ITALIC;
				//如果单击的为加下划线按钮，则要设置underline属性为true
				else if (event.widget==underlineButton)
					styleRange.underline=true;
				//如果单击的为设置背景色按钮，则要设置background属性
				else if (event.widget==bgColorButton){
					ColorDialog dialog = new ColorDialog(sShell);
					RGB rgb = dialog.open();
					if ( rgb != null ){
						Color  color = new Color( sShell.getDisplay() , rgb );
						styleRange.background=color;
					}
				}
				//如果单击的为设置前景色按钮，则要设置foreground属性
				else if (event.widget==forColorButton){
					ColorDialog dialog = new ColorDialog(sShell);
					RGB rgb = dialog.open();
					if ( rgb != null ){
						Color  color = new Color( sShell.getDisplay() , rgb );
						styleRange.foreground=color;
					}
				}
				//最后将新的样式应用于文本
				styledText.setStyleRange( styleRange );
			}
			
		};
		//为几个按钮注册选中事件
		boldButton.addListener( SWT.Selection, listener);
		italicButton.addListener( SWT.Selection, listener);
		underlineButton.addListener( SWT.Selection, listener);
		bgColorButton.addListener( SWT.Selection, listener);
		forColorButton.addListener( SWT.Selection, listener);
	}

	private void createComposite() {
		composite = new Composite(viewForm, SWT.NONE);
		composite.setLayout(new FillLayout());
		styledText = new StyledText(composite, SWT.NONE);
		
		/*
		styledText.addVerifyListener( new VerifyListener(){
			public void verifyText(VerifyEvent e) {
				//System.out.println(e.toString());
				if (e.end - e.start == 0) {
					if (e.text.equals("class")) {
						e.text = "class";
					}
				}
				
			}
			
		});
		/*
		styledText.addModifyListener( new ModifyListener(){

			public void modifyText(ModifyEvent e) {
				System.out.print(e.toString());
			}
			
		});
		*/
		//注册ExtendedModify事件
		styledText.addExtendedModifyListener( new ExtendedModifyListener(){
			public void modifyText(ExtendedModifyEvent event) {
				//获得修改字符的最后的位置
		        int end = event.start + event.length - 1;
		        //如果为插入字符操作
		        if (event.start <= end) {
		        	//获得插入的字符StyleRange对象
		        	String text = styledText.getText(event.start, end);
		        	//创建一个list对象保存所有的
		        	java.util.List ranges = new java.util.ArrayList();
		        	//循环输入的每一个字符，如果有字符为数字，则将该字符的位置保存到一个StyleRange对象中
		        	for (int i = 0; i < text.length(); i++) {
		        		if ("0123456789".indexOf(text.charAt(i)) > -1) 
		        			ranges.add(new StyleRange(event.start + i, 1, event.display.getSystemColor( SWT.COLOR_RED), null, SWT.BOLD));
		        	}
		        	//如果保存StyleRange对象的list不为空，则将list中的StyleRange应用到格式化文本中
		        	if (!ranges.isEmpty()) 
		        		styledText.replaceStyleRanges(event.start, event.length, (StyleRange[]) ranges.toArray(new StyleRange[0]));
		        }
		   }	
		});
		//注册背景色改变事件
	    styledText.addLineBackgroundListener(new LineBackgroundListener() {
	        public void lineGetBackground(LineBackgroundEvent event) {
	        	//获得当前行的文本
	        	String text = event.lineText;
	        	//如果在文本中找到import关键字，则设置该行的背景色为灰色
	        	if ( text.indexOf("import")>-1)
	            event.lineBackground = styledText.getDisplay().getSystemColor(SWT.COLOR_GRAY);
	        }
	    });
	}

	public static void main(String[] args) {

		Display display = Display.getDefault();
		SimpleNotepad thisClass = new SimpleNotepad();
		thisClass.createSShell();
		thisClass.sShell.open();

		while (!thisClass.sShell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		ImageFactory.dispose();
		display.dispose();
	}

	private void createSShell() {
		sShell = new Shell();
		sShell.setText("Simple Notepad");
		sShell.setLayout(new FillLayout());
		createViewForm();
		sShell.setSize(new Point(300, 200));
	}

}
