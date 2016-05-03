package investigate.form;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.DetailsPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.MasterDetailsBlock;
import org.eclipse.ui.forms.SectionPart;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

import java.io.File;

public class FileMasterDetailsBlock extends MasterDetailsBlock {
  private FormPage page;

  public FileMasterDetailsBlock(FormPage page) {
    this.page = page;
  }

  //父类中的抽象方法，创建Master部分
  protected void createMasterPart(final IManagedForm managedForm, Composite parent) {
    FormToolkit toolkit = managedForm.getToolkit();
    //创建一个内容区
    Section section = toolkit.createSection(parent, Section.DESCRIPTION | Section.TITLE_BAR);
    section.setText("浏览文件");
    section.marginWidth = 10;
    section.marginHeight = 5;
    //创建内容区的面板
    Composite client = toolkit.createComposite(section, SWT.WRAP);
    //绘制该面板的边框，与表单的风格一致
    toolkit.paintBordersFor(client);
    GridLayout layout = new GridLayout();
    layout.numColumns = 2;
    layout.marginWidth = 2;
    layout.marginHeight = 2;
    client.setLayout(layout);
    //创建一个树，使用toolkit对象创建
    Tree tree = toolkit.createTree(client, SWT.NULL);
    GridData gd = new GridData(GridData.FILL_BOTH);
    gd.heightHint = 20;
    gd.widthHint = 100;
    tree.setLayoutData(gd);
    /*
		 IFormPart管理了整个Part的dirty state, saving, commit, focus, selection changes等等这样的事件。
		 并不是表单中的每一个-空间站都需要成为一个IFormPart，最好将一组control通过实现IFormPart变成一个Part.
	     一般来说Section就是一个自然形成的组，所以Eclipse Form提供了一个SectionPart的实现，
	     它包含一个Section的对象。   
	    */
    final SectionPart spart = new SectionPart(section);
    //注册该对象到IManagedForm表单管理器中
    managedForm.addPart(spart);
    //将普通的树包装成MVC的树
    TreeViewer viewer = new TreeViewer(tree);
    //注册树的选择事件监听器
    viewer.addSelectionChangedListener(new ISelectionChangedListener() {
      //当单击树中某一个节点时
      public void selectionChanged(SelectionChangedEvent event) {
        //通过IManagedForm来发布IFormPart所对应的事件
        managedForm.fireSelectionChanged(spart, event.getSelection());
      }
    });
    //设置树的内容
    viewer.setContentProvider(new MasterContentProvider());
    //设置树的标签
    viewer.setLabelProvider(new MasterLabelProvider());
    //设置初始化输入的类
    viewer.setInput(new File("E:\\Program Files"));
  }

  //注册详细页面部分
  protected void registerPages(DetailsPart detailsPart) {
    //将DirectoryDetailPage对象注册
    detailsPart.registerPage(File.class, new DirectoryDetailPage());
  }

  //创建表单区的Action
  protected void createToolBarActions(IManagedForm managedForm) {
    final ScrolledForm form = managedForm.getForm();
    //水平布局操作
    Action hAction = new Action("horizon", Action.AS_RADIO_BUTTON) {
      public void run() {
        sashForm.setOrientation(SWT.HORIZONTAL);
        form.reflow(true);
      }
    };
    hAction.setChecked(true);
    hAction.setToolTipText("水平布局");
    hAction.setImageDescriptor(ImageDescriptor.createFromFile(getClass(), "icons/hor.gif"));
    //垂直布局操作
    Action vAction = new Action("vertical", Action.AS_RADIO_BUTTON) {
      public void run() {
        sashForm.setOrientation(SWT.VERTICAL);
        form.reflow(true);
      }
    };
    vAction.setChecked(false);
    vAction.setToolTipText("垂直布局"); //$NON-NLS-1$
    vAction.setImageDescriptor(ImageDescriptor.createFromFile(getClass(), "icons/ver.gif"));
    //将这两个操作添加到表单的工具栏管理器中
    form.getToolBarManager().add(hAction);
    form.getToolBarManager().add(vAction);
  }

  public class MasterContentProvider implements ITreeContentProvider {

    public Object[] getChildren(Object element) {
      return ((File) element).listFiles();
    }

    public Object[] getElements(Object element) {
      return ((File) element).listFiles();
    }

    public boolean hasChildren(Object element) {
      Object[] obj = getChildren(element);
      return obj == null ? false : obj.length > 0;
    }

    public Object getParent(Object element) {
      return ((File) element).getParentFile();
    }

    public void dispose() {
    }

    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    }

  }

  class MasterLabelProvider implements ILabelProvider {

    public Image getImage(Object element) {
      File file = (File) element;
      if (file.isDirectory())
        return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FOLDER);
      else
        return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FILE);
    }

    public String getText(Object element) {
      String text = ((File) element).getName();
      if (text.length() == 0) {
        text = ((File) element).getPath();
      }
      return text;
    }

    public void addListener(ILabelProviderListener listener) {
    }

    public void dispose() {

    }

    public boolean isLabelProperty(Object element, String property) {
      return false;
    }

    public void removeListener(ILabelProviderListener listener) {
    }

  }

}
