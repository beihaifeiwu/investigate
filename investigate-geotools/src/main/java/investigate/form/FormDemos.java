package investigate.form;

import com.google.common.base.Strings;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.forms.HyperlinkGroup;
import org.eclipse.ui.forms.events.*;
import org.eclipse.ui.forms.widgets.*;

/**
 * Created by LiuPin on 2016/5/3.
 */
public class FormDemos {

  private static ImageRegistry registry;

  public static void main(String[] args) {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setText("SWT 表单示例");
    shell.setLayout(new FillLayout());

    registry = new ImageRegistry(display);

    // 创建表单工具对象
    FormToolkit toolkit = new FormToolkit(shell.getDisplay());
    // 通过表单工具对象创建可滚动的表单对象
    ScrolledForm form = toolkit.createScrolledForm(shell);
    // 设置表单文本
    form.setText("Hello, SWT 表单");
    // 设置表单背景图片
    form.setBackgroundImage(loadImage("investigate/form/cat.jpg"));
    // 设置表单布局
    form.getBody().setLayout(new TableWrapLayout());

    addExpandableComposite(toolkit, form);
    addSection(toolkit, form);
    addLink(toolkit, form);
    addFormText(toolkit, form);

    shell.pack();
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) display.sleep();
    }
    toolkit.dispose();
    display.dispose();
  }

  public static Image loadImage(String path) {
    if (!Strings.isNullOrEmpty(path)) {
      Image image = registry.get(path);
      if (image == null) {
        image = new Image(Display.getCurrent(), FormDemos.class.getClassLoader().getResourceAsStream(path));
        registry.put(path, image);
      }
      return image;
    }
    return null;
  }

  private static void addSection(FormToolkit toolkit, final ScrolledForm form) {
    // 创建内容区域，使用TWISTIE样式，并且使用TITLE_BAR显示背景的标题
    Section section = toolkit.createSection(form.getBody(), Section.TWISTIE | Section.TITLE_BAR | Section.DESCRIPTION);
    // 设置标题文字
    section.setText("这是一个内容区（Section）");
    // 设置描述信息
    section.setDescription("这是在标题栏下的一段描述");
    // 通过工具类来创建分割线
    toolkit.createCompositeSeparator(section);
    // 创建一个面板，作为内容折叠区放置的控件
    Composite sectionClient = toolkit.createComposite(section);
    sectionClient.setLayout(new GridLayout());
    Button b1 = toolkit.createButton(sectionClient, "CheckBox 1", SWT.CHECK);
    Button b2 = toolkit.createButton(sectionClient, "CheckBox 2", SWT.CHECK);
    // 设置内容区控件
    section.setClient(sectionClient);
    section.addExpansionListener(new ExpansionAdapter() {
      @Override public void expansionStateChanged(ExpansionEvent e) {
        // 根据部件的新尺寸重新定位和更新滚动条
        form.reflow(true);
      }
    });

    // 创建带图标的超链接
    ImageHyperlink imageHyperlink = toolkit.createImageHyperlink(section, SWT.CENTER);
    // 设置超链接的图标
    imageHyperlink.setImage(loadImage("icons/dtool16/new_wiz.gif"));
    // 将该图标超链接添加到内容区的工具栏中
    section.setTextClient(imageHyperlink);
  }

  private static void addExpandableComposite(FormToolkit toolkit, final ScrolledForm form) {
    // 创建可折叠的面板
    ExpandableComposite expcomp = toolkit.createExpandableComposite(form.getBody(), ExpandableComposite.TREE_NODE);
    // 定义字符串
    String txt = "在Web网页的UI体系中，最常见也是效果最好的就是使页面中的部分区域中的内容可以折叠和展开， Eclipse表单也提供了可折叠" +
        "的面板（ExpandableComposite）来实现相同的功能.";
    // 创建一个标签并显示字符串
    Label client = toolkit.createLabel(expcomp, txt, SWT.WRAP);
    // 为折叠面板设置标题
    expcomp.setText("这是一个可折叠的面板（ExpandableComposite）");
    // 将Label作为折叠面板折叠区的内容
    expcomp.setClient(client);
    // 为折叠面板添加展开/折叠时的监听器
    expcomp.addExpansionListener(new ExpansionAdapter() {
      @Override public void expansionStateChanged(ExpansionEvent e) {
        // 根据部件的新尺寸重新定位和更新滚动条
        form.reflow(true);
      }
    });
  }

  private static void addLink(FormToolkit toolkit, final ScrolledForm form) {
    // 创建内容区域，使用TWISTIE样式，并且使用TITLE_BAR显示背景的标题
    Section section = toolkit.createSection(form.getBody(), Section.TWISTIE | Section.TITLE_BAR);
    // 设置标题文字
    section.setText("这是一个内容区（Section）");

    // 创建一个面板，作为内容折叠区放置的控件
    Composite sectionClient = toolkit.createComposite(section);
    sectionClient.setLayout(new GridLayout());

    // 创建超链接，文字可以折行显示
    Hyperlink textLink = toolkit.createHyperlink(sectionClient, "这是一个文本超链接", SWT.WRAP);
    // 注册超链接事件监听器
    textLink.addHyperlinkListener(new IHyperlinkListener() {
      @Override public void linkEntered(HyperlinkEvent hyperlinkEvent) {
        System.out.println("光标进入超链接区域");
      }

      @Override public void linkExited(HyperlinkEvent hyperlinkEvent) {
        System.out.println("光标离开超链接区域");
      }

      @Override public void linkActivated(HyperlinkEvent hyperlinkEvent) {
        System.out.println("超链接被激活");
      }
    });

    // 创建带有图片的超链接
    ImageHyperlink imageLink = toolkit.createImageHyperlink(sectionClient, SWT.WRAP);
    // 设置超链接的图标
    imageLink.setImage(loadImage("icons/dtool16/help_contents.gif"));
    // 设置超链接的文本
    imageLink.setText("这是一个图片超链接");
    // 设置当鼠标放到超链接上时的图标
    imageLink.setHoverImage(loadImage("icons/dlcl16/linkto_help.gif"));
    // 注册超链接监听器，使用HyperlinkAdapter适配器对象
    imageLink.addHyperlinkListener(new HyperlinkAdapter() {
      @Override public void linkActivated(HyperlinkEvent e) {
        System.out.println("超链接被激活");
      }
    });

    // 获得超链接组
    HyperlinkGroup group = toolkit.getHyperlinkGroup();
    group.setActiveBackground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));

    // 设置内容区控件
    section.setClient(sectionClient);
    section.addExpansionListener(new ExpansionAdapter() {
      @Override public void expansionStateChanged(ExpansionEvent e) {
        // 根据部件的新尺寸重新定位和更新滚动条
        form.reflow(true);
      }
    });
  }

  private static void addFormText(FormToolkit toolkit, final ScrolledForm form) {
    // 创建一个表单文本对象， true表示当该表单获得焦点时，将超级链接的焦点设置为可见
    FormText formText = toolkit.createFormText(form.getBody(), false);
    // 定义一个字符串，其中包含http://格式的字符串
    String text = "这是无格式的文本，这是带有超链接的文本 http://www.eclipse.org 将自动转换为超链接";
    // 设置文本false表示不转换tag标记， true表示转换超链接
    formText.setText(text, false, true);
    // 注册单击超链接监听器
    formText.addHyperlinkListener(new HyperlinkAdapter(){
      @Override public void linkActivated(HyperlinkEvent e) {
        System.out.println("单击了该超链接：" + e.getHref());
      }
    });
  }
}
