package investigate.jface.jseditor;

import investigate.jface.jseditor.action.*;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.preference.PreferenceStore;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.text.DefaultUndoManager;
import org.eclipse.jface.text.IUndoManager;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.text.source.VerticalRuler;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;


public class JSEditor extends ApplicationWindow implements IPropertyChangeListener {

  private PersistentDocument document;//数据文档对象
  private SourceViewer viewer;//显示文本控件对象
  private EventManager eventManager;//事件管理器
  private PreferenceStore preference;//保存首选项设置的对象
  private IUndoManager undoManager;//撤销与重复管理器
  private JSEditorConfiguration configuration;//文本控件的配置对象

  public JSEditor() {
    super(null);
    eventManager = new EventManager(this);//初始化事件管理器
    this.addMenuBar();//添加菜单
    this.addToolBar(SWT.FLAT);//添加工具栏
  }

  protected void configureShell(Shell shell) {
    super.configureShell(shell);
    shell.setText("JavaScript 编辑器");
    shell.setSize(600, 400);
  }

  protected Control createContents(Composite parent) {
    Composite top = new Composite(parent, SWT.NONE);
    top.setLayout(new FillLayout());
    //初始化文档对象
    document = new PersistentDocument();
    //初始化源文件显示控件对象
    viewer = new SourceViewer(top, new VerticalRuler(10), SWT.V_SCROLL | SWT.H_SCROLL);
    //初始化文档的配置对象
    configuration = new JSEditorConfiguration(this);
    viewer.configure(configuration);//设置文档配置
    viewer.setDocument(document);//设置文档
    undoManager = new DefaultUndoManager(100);//初始化撤销管理器对象，默认可撤销100次
    undoManager.connect(viewer);//将该撤销管理器应用于文档
    //初始化代码中的字体
    initCodeFont();
    return parent;
  }

  //初始化代码的字体
  private void initCodeFont() {
    //定义一个默认的字体
    FontData defaultFont = new FontData("Courier New", 10, SWT.NORMAL);
    //如果从首选项读出的字体有异常，则使用默认的字体
    FontData data = StringConverter.asFontData(ResourceManager.getPreferenceStore().getString(Constants.CODE_FONT), defaultFont);
    //创建字体
    Font font = new Font(this.getShell().getDisplay(), data);
    viewer.getTextWidget().setFont(font);//设置字体
  }

  //初始化菜单项
  protected MenuManager createMenuManager() {
    MenuManager top = new MenuManager();
    MenuManager fileMenu = new MenuManager("文件(&F)");
    MenuManager editMenu = new MenuManager("编辑(&E)");
    MenuManager helpMenu = new MenuManager("帮助(&H)");

    fileMenu.add(new OpenAction(this));
    fileMenu.add(new SaveAction(this));
    fileMenu.add(new Separator());
    fileMenu.add(new PrintAction(this));

    editMenu.add(new UndoAction(this));
    editMenu.add(new RedoAction(this));
    editMenu.add(new Separator());
    editMenu.add(new SearchAction(this));
    editMenu.add(new Separator());
    editMenu.add(new PreferenceAction(this));

    helpMenu.add(new HelpAction(this));
    top.add(fileMenu);
    top.add(editMenu);
    top.add(helpMenu);

    return top;
  }

  //初始化工具栏
  protected ToolBarManager createToolBarManager(int style) {
    ToolBarManager toolBar = new ToolBarManager(style);
    toolBar.add(new OpenAction(this));
    toolBar.add(new SaveAction(this));
    toolBar.add(new Separator());
    toolBar.add(new PrintAction(this));

    toolBar.add(new UndoAction(this));
    toolBar.add(new RedoAction(this));
    toolBar.add(new Separator());
    toolBar.add(new SearchAction(this));
    toolBar.add(new Separator());
    toolBar.add(new PreferenceAction(this));
    toolBar.add(new HelpAction(this));
    return toolBar;
  }

  public PersistentDocument getDocument() {
    return document;
  }

  public SourceViewer getViewer() {
    return viewer;
  }

  public EventManager getEventManager() {
    return eventManager;
  }

  public PreferenceStore getPreference() {
    return preference;
  }

  public void setPreference(PreferenceStore preference) {
    this.preference = preference;
  }

  public IUndoManager getUndoManager() {
    return undoManager;
  }

  public JSEditorConfiguration getConfiguration() {
    return configuration;
  }

  //为IPropertyChangeListener接口中的方法，当设置首选项的字体时
  //重新设置编辑器的字体
  public void propertyChange(PropertyChangeEvent event) {
    if (event.getNewValue() == null)
      return;
    if (event.getProperty().equals(Constants.CODE_FONT))
      eventManager.setCodeFont((FontData[]) event.getNewValue());
  }
}
