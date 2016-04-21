package investigate.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.printing.PrintDialog;
import org.eclipse.swt.printing.Printer;
import org.eclipse.swt.printing.PrinterData;
import org.eclipse.swt.widgets.*;

import java.io.*;

public class PrintSample {
	public static void main(String[] args) {
    // simplePrint();
    Display display = Display.getDefault();
    PrintGUI thisClass = new PrintGUI();
    thisClass.createSShell();
    thisClass.sShell.open();

    while (!thisClass.sShell.isDisposed()) {
      if (!display.readAndDispatch())
        display.sleep();
    }
    display.dispose();
	}

  private static void simplePrint() {
    PrinterData data = Printer.getDefaultPrinterData();
    if (data == null) {
      return;
    }
    Printer printer = new Printer(data);
    //开始打印任务
    if (printer.startJob("Simple Print")) {
      Color black = printer.getSystemColor(SWT.COLOR_BLACK);
      Color gray = printer.getSystemColor(SWT.COLOR_GRAY);
      Color red = printer.getSystemColor(SWT.COLOR_RED);
      //计算左边踞和上边距的位置
      Rectangle trim = printer.computeTrim(0, 0, 0, 0);
      Point dpi = printer.getDPI();
      int leftMargin = dpi.x + trim.x;
      int topMargin = dpi.y / 2 + trim.y;
      //创建图形上下文对象
      GC gc = new GC(printer);
      Font font = gc.getFont();
      //打印第一页
      if (printer.startPage()) {
        gc.setBackground(gray);
        gc.setForeground(black);
        String testString = "Hello World!";
        //使用gc对象画字符串的方法显示字符
        gc.drawString(testString, leftMargin, topMargin + font.getFontData()[0].getHeight());
        printer.endPage();
      }
      //打印第二页
      if (printer.startPage()) {
        gc.setBackground(gray);
        gc.setForeground(black);
        String testString = "Hello World!";
        Point extent = gc.stringExtent(testString);
        gc.drawString(testString, leftMargin, topMargin + font.getFontData()[0].getHeight());
        gc.setForeground(red);
        gc.drawRectangle(leftMargin, topMargin, extent.x, extent.y);
        printer.endPage();
      }
      //释放gc对象
      gc.dispose();
      //结束打印任务
      printer.endJob();
    }
    printer.dispose();
  }

  private static class PrintGUI {

    private Shell sShell ;
    private Menu menu ;
    private Text content ;
    Font font;
    Color foregroundColor, backgroundColor;
    private void createMenu() {
      menu = new Menu ( sShell , SWT.BAR);
      sShell.setMenuBar( menu );
      MenuItem item = new MenuItem(menu, SWT.CASCADE);
      item.setText("文件(&F)");
      Menu fileMenu = new Menu(sShell, SWT.DROP_DOWN);
      item.setMenu(fileMenu);
      item = new MenuItem(fileMenu, SWT.PUSH);
      item.setText("打开(&O)");
      item.setAccelerator(SWT.CTRL + 'O');
      item.addSelectionListener(new SelectionAdapter() {
        public void widgetSelected(SelectionEvent event) {
          menuOpen();
        }
      });
      item = new MenuItem(fileMenu, SWT.PUSH);
      item.setText("选择字体");
      item.addSelectionListener(new SelectionAdapter() {
        public void widgetSelected(SelectionEvent event) {
          menuFont();
        }
      });
      item = new MenuItem(fileMenu, SWT.PUSH);
      item.setText("选择前景色");
      item.addSelectionListener(new SelectionAdapter() {
        public void widgetSelected(SelectionEvent event) {
          menuForegroundColor();
        }
      });
      item = new MenuItem(fileMenu, SWT.PUSH);
      item.setText("选择背景色");
      item.addSelectionListener(new SelectionAdapter() {
        public void widgetSelected(SelectionEvent event) {
          menuBackgroundColor();
        }
      });
      item = new MenuItem(fileMenu, SWT.PUSH);
      item.setText("打印(@P)");
      item.setAccelerator(SWT.CTRL + 'P');
      item.addSelectionListener(new SelectionAdapter() {
        public void widgetSelected(SelectionEvent event) {
          menuPrint();
        }
      });
      new MenuItem(fileMenu, SWT.SEPARATOR);
      item = new MenuItem(fileMenu, SWT.PUSH);
      item.setText("退出(&E)");
      item.addSelectionListener(new SelectionAdapter() {
        public void widgetSelected(SelectionEvent event) {
          font.dispose();
          foregroundColor.dispose();
          backgroundColor.dispose();
          System.exit(0);
        }
      });

    }
    protected void menuPrint() {
      PrintDialog dialog = new PrintDialog(sShell, SWT.NONE);
      PrinterData data = dialog.open();
      if (data == null) return;
      if (data.printToFile) {
        data.fileName = "print.out";
      }
      Printer printer = new Printer(data);
      TextPrinter textPrinter = new TextPrinter( "Print Job", content , printer);
      textPrinter.start();
    }
    protected void menuBackgroundColor() {
      ColorDialog colorDialog = new ColorDialog(sShell);
      colorDialog.setRGB(content.getBackground().getRGB());
      RGB rgb = colorDialog.open();
      if (rgb != null) {
        if (backgroundColor != null) backgroundColor.dispose();
        backgroundColor = new Color(sShell.getDisplay(), rgb);
        content.setBackground(backgroundColor);
      }

    }
    protected void menuForegroundColor() {
      ColorDialog colorDialog = new ColorDialog(sShell);
      colorDialog.setRGB(content.getForeground().getRGB());
      RGB rgb = colorDialog.open();
      if (rgb != null) {
        if (foregroundColor != null) foregroundColor.dispose();
        foregroundColor = new Color(sShell.getDisplay(), rgb);
        content.setForeground(foregroundColor);
      }

    }
    protected void menuFont() {
      FontDialog fontDialog = new FontDialog(sShell);
      fontDialog.setFontList(content.getFont().getFontData());
      FontData fontData = fontDialog.open();
      if (fontData != null) {
        if (font != null) font.dispose();
        font = new Font(sShell.getDisplay(), fontData);
        content.setFont(font);
      }

    }
    protected void menuOpen() {
      final String textString;
      FileDialog dialog = new FileDialog(sShell, SWT.OPEN);
      dialog.setFilterExtensions(new String[] {"*.java", "*.*"});
      String name = dialog.open();
      if ((name == null) || (name.length() == 0)) return;

      try {
        File file = new File(name);
        FileInputStream stream= new FileInputStream(file.getPath());
        try {
          Reader in = new BufferedReader(new InputStreamReader(stream));
          char[] readBuffer= new char[2048];
          StringBuffer buffer= new StringBuffer((int) file.length());
          int n;
          while ((n = in.read(readBuffer)) > 0) {
            buffer.append(readBuffer, 0, n);
          }
          textString = buffer.toString();
          stream.close();
        } catch (IOException e) {
          MessageBox box = new MessageBox(sShell, SWT.ICON_ERROR);
          box.setMessage("读文件出错:\n" + name);
          box.open();
          return;
        }
      } catch (FileNotFoundException e) {
        MessageBox box = new MessageBox(sShell, SWT.ICON_ERROR);
        box.setMessage("文件未找到:\n" + name);
        box.open();
        return;
      }
      content.setText(textString);
    }
    private void createSShell() {
      sShell = new Shell();
      sShell.setText("Shell");
      sShell.setLayout(new FillLayout());
      sShell.setSize(new Point(300, 200));
      content = new Text(sShell, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
      createMenu();
    }
  }

  static class TextPrinter extends Thread {

    private Text content;//显示字符的文本框对象
    private Printer printer;//打印对象
    private GC gc;//用于绘制的图形上下文对象
    private FontData[] printerFontData;//打印的字体数据
    private RGB printerForeground, printerBackground;//打印的前景色和背景色
    private int lineHeight = 0;//打印的行高
    private int tabWidth = 0;//Tab键的大小
    private int leftMargin, rightMargin, topMargin, bottomMargin;//上下左右的页边距
    private int x, y;//打印的行和列的值
    private int index, end;//字符当前打印的索引值和总长度
    private String textToPrint;//需要打印的字符
    private String tabs;//Tab键
    private StringBuffer wordBuffer;//打印字符的临时变量

    public TextPrinter(String name, Text text, Printer printer) {
      super(name);
      this.content = text;
      this.printer = printer;
      init();
    }
    //初始化属性的方法
    public void init() {
      textToPrint = content.getText();
      printerFontData = content.getFont().getFontData();
      printerForeground = content.getForeground().getRGB();
      printerBackground = content.getBackground().getRGB();
      //默认一个Tab键表示4个空格
      int tabSize = 4;
      StringBuffer tabBuffer = new StringBuffer(tabSize);
      for (int i = 0; i < tabSize; i++)
        tabBuffer.append(' ');
      tabs = tabBuffer.toString();

      //计算上下左右边距值
      Rectangle clientArea = printer.getClientArea();
      Rectangle trim = printer.computeTrim(0, 0, 0, 0);
      Point dpi = printer.getDPI();
      leftMargin = dpi.x + trim.x;
      rightMargin = clientArea.width - dpi.x + trim.x + trim.width;
      topMargin = dpi.y + trim.y;
      bottomMargin = clientArea.height - dpi.y + trim.y + trim.height;
    }

    public void run() {
      //启动打印，如果不能正常启动则返回
      if (!printer.startJob("Text Print"))
        return;
      //开始打印
      print();
    }

    public void print() {
      //创建图形上下文对象
      gc = new GC(printer);
      //创建字体，前景色，背景色对象
      Font printerFont = new Font(printer, printerFontData);
      Color printerForegroundColor = new Color(printer, printerForeground);
      Color printerBackgroundColor = new Color(printer, printerBackground);
      //将字体，前景色，背景色设置给gc对象
      gc.setFont(printerFont);
      gc.setForeground(printerForegroundColor);
      gc.setBackground(printerBackgroundColor);
      tabWidth = gc.stringExtent(tabs).x;
      lineHeight = gc.getFontMetrics().getHeight();
      //打印字符
      printText();
      //结束打印工作
      printer.endJob();
      //释放资源
      printerFont.dispose();
      printerForegroundColor.dispose();
      printerBackgroundColor.dispose();
      gc.dispose();
    }

    void printText() {
      //开始打印一页
      printer.startPage();
      wordBuffer = new StringBuffer();
      x = leftMargin;
      y = topMargin;
      index = 0;
      end = textToPrint.length();
      //循环每个字符串的每个字符
      while (index < end) {
        char c = textToPrint.charAt(index);
        System.out.println(c);
        index++;
        if (c == 0)
          continue;
        //如果字符是\n换行符或\r回车符
        if (c == '\n' || c == '\r'){
          //如果字符\r\n同时出现，则只打印一行
          if (c == '\r' && index < end && textToPrint.charAt(index) == '\n') {
            index++;
          }
          printWordBuffer();
          newline();
        } else {
          //如果字符是不是Tab键，则打印出字符
          if (c != '\t') {
            wordBuffer.append(c);
          }
          if (Character.isWhitespace(c)) {
            printWordBuffer();
            if (c == '\t') {//Tab键
              x += tabWidth;
            }
          }
        }
      }
      if (y + lineHeight <= bottomMargin) {
        printer.endPage();
      }
    }
    //打印字符，要判断是否已经到了行的末尾，如果到了末尾需要换行
    void printWordBuffer() {
      if (wordBuffer.length() > 0) {
        String word = wordBuffer.toString();
        int wordWidth = gc.stringExtent(word).x;
        if (x + wordWidth > rightMargin) {
          newline();
        }
        gc.drawString(word, x, y, false);
        x += wordWidth;
        wordBuffer = new StringBuffer();
      }
    }
    //换行，如果到了页尾，需要打印下一页
    void newline() {
      x = leftMargin;
      y += lineHeight;
      if (y + lineHeight > bottomMargin) {
        printer.endPage();
        if (index + 1 < end) {
          y = topMargin;
          printer.startPage();
        }
      }
    }

  }
}
