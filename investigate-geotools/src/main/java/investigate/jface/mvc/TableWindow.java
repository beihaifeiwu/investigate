package investigate.jface.mvc;

import investigate.swt.util.ImageFactory;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.*;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.*;

import java.util.ArrayList;
import java.util.List;

public class TableWindow extends ApplicationWindow {

  private TableViewer table;//声明一个表格对象
  private List persons;//表格所存储的数据
  //表格中列的索引号
  public static final int ID = 0;
  public static final int NAME = 1;
  public static final int GENDER = 2;
  public static final int COLOR = 3;
  //表格个的列名称
  public static final String[] COLUMN_NAME = {"ID号", "姓名", "性别", "喜欢颜色"};

  public TableWindow() {
    super(null);
    initPersons();
  }

  //初始化表格数据，通常是从数据库中读出
  private void initPersons() {
    persons = new ArrayList();
    persons.add(new PersonEO(1, "张三", "男", "灰色"));
    persons.add(new PersonEO(2, "李四", "女", "白色"));
  }

  //设置窗口的标题和大小
  protected void configureShell(Shell shell) {
    super.configureShell(shell);
    shell.setSize(300, 200);
    shell.setText("TableViewer程序示例");
  }

  //创建窗口的控件
  protected Control createContents(Composite parent) {
    //创建TableViewer对象
    table = new TableViewer(parent, SWT.FULL_SELECTION);
    //table = CheckboxTableViewer.newCheckList( parent , SWT.FULL_SELECTION);
    //创建表头
    for (int i = 0; i < COLUMN_NAME.length; i++) {
      new TableColumn(table.getTable(), SWT.LEFT).setText(COLUMN_NAME[i]);
      table.getTable().getColumn(i).pack();
    }

    //设置排序器
    table.setSorter(new TableSorter());
    //分别为表头的每一列注册事件
    TableColumn column = table.getTable().getColumn(0);
    column.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent e) {
        //单击时，重新设置排序对象属性
        ((TableSorter) table.getSorter()).doSort(TableWindow.ID);
        //刷新表格数据
        table.refresh();
      }
    });
    column = table.getTable().getColumn(1);
    column.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent e) {
        ((TableSorter) table.getSorter()).doSort(TableWindow.NAME);
        table.refresh();
      }
    });
    column = table.getTable().getColumn(2);
    column.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent e) {
        ((TableSorter) table.getSorter()).doSort(TableWindow.GENDER);
        table.refresh();
      }
    });
    column = table.getTable().getColumn(3);
    column.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent e) {
        ((TableSorter) table.getSorter()).doSort(TableWindow.COLOR);
        table.refresh();
      }
    });
    //设置表头和表格线可见
    table.getTable().setHeaderVisible(true);
    table.getTable().setLinesVisible(true);
    //设置数据
    table.setContentProvider(new MyContentProvider());
    //设置视图
    table.setLabelProvider(new MyLabelProvider());
    //设置表格数据对象，该方法非常重要，是表格数据入口
    table.setInput(persons);
    createContextMenu();
    //设置列属性
    table.setColumnProperties(COLUMN_NAME);

    //设置单元格编辑器对象数组
    CellEditor[] editors = new CellEditor[4];
    editors[0] = null;
    editors[1] = new TextCellEditor(table.getTable());
    editors[2] = new TextCellEditor(table.getTable());
    editors[3] = new TextCellEditor(table.getTable());
    //设置单元格编辑器
    table.setCellEditors(editors);
    //设置单元个修改器
    table.setCellModifier(new ICellModifier() {

      //如果该列为第一列，设置不能修改
      public boolean canModify(Object element, String property) {
        if (property.equals(COLUMN_NAME[0]))
          return false;
        return true;
      }

      //当处于编辑状态时所显示的值
      public Object getValue(Object element, String property) {
        PersonEO p = (PersonEO) element;
        if (property.equals(COLUMN_NAME[1]))
          return p.getName();
        else if (property.equals(COLUMN_NAME[2]))
          return p.getGender();
        else if (property.equals(COLUMN_NAME[3]))
          return p.getColor();
        return null;
      }

      //当修改后设置更新数据
      public void modify(Object element, String property, Object value) {
        if (element instanceof Item)
          element = ((Item) element).getData();
        PersonEO p = (PersonEO) element;
        if (property.equals(COLUMN_NAME[1]))
          p.setName((String) value);
        else if (property.equals(COLUMN_NAME[2]))
          p.setGender((String) value);
        else if (property.equals(COLUMN_NAME[3]))
          p.setColor((String) value);
        //刷新表格
        table.refresh();
      }

    });

    table.addDoubleClickListener(new IDoubleClickListener() {
      public void doubleClick(DoubleClickEvent event) {
        StructuredSelection select = (StructuredSelection) event.getSelection();
        PersonEO p = (PersonEO) select.getFirstElement();
        System.out.println(p.getName());
      }

    });
    return parent;
  }

  //创建上下文菜单
  private void createContextMenu() {
    MenuManager menu = new MenuManager();
    menu.add(new AddAction());
    menu.add(new DelAction());
    menu.add(new FileterAction());
    //获得一个Menu对象
    Menu m = menu.createContextMenu(getShell());
    //将该对象设置为表格的菜单
    table.getTable().setMenu(m);
  }

  public static void main(String[] args) {
    TableWindow test = new TableWindow();
    test.setBlockOnOpen(true);
    test.open();
    Display.getCurrent().dispose();

  }

  public class MyContentProvider implements IStructuredContentProvider {

    //将初始化数据的入口对象转换成表格使用的数组对象
    public Object[] getElements(Object inputElement) {
      if (inputElement instanceof List)
        return ((List) inputElement).toArray();
      return null;
    }

    //释放该对象时调用的方法
    public void dispose() {

    }

    //当表格中的数据改变时调用该方法
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

    }

  }

  public class MyLabelProvider implements ITableLabelProvider {

    //设置每个单元格所显示的图标
    public Image getColumnImage(Object element, int columnIndex) {
      //如果是性别所在的列
      if (columnIndex == GENDER) {
        //类型转换，element代表表格中的一行
        PersonEO person = (PersonEO) element;
        //根据不同的值设置不同的图标
        if (person.getGender().equals("男"))
          return ImageFactory.loadImage(Display.getCurrent(), ImageFactory.ICON_BOY);
        else if (person.getGender().equals("女"))
          return ImageFactory.loadImage(Display.getCurrent(), ImageFactory.ICON_GIRL);
      }
      return null;
    }

    //设置每个单元格所显示的文字
    public String getColumnText(Object element, int columnIndex) {
      //类型转换，element代表表格中的一行
      PersonEO person = (PersonEO) element;
      if (columnIndex == ID)//如果是第一列　
        return person.getID() + "";
      else if (columnIndex == NAME)//如果是第二列　
        return person.getName() + "";
      else if (columnIndex == GENDER)//如果是第三列　
        return person.getGender();
      else if (columnIndex == COLOR)//如果是第四列　
        return person.getColor() + "";
      return "";
    }

    //释放对象时释放图像资源
    public void dispose() {
      ImageFactory.dispose();
    }

    //以下方法为空实现
    public void addListener(ILabelProviderListener listener) {

    }

    public boolean isLabelProperty(Object element, String property) {
      return false;
    }

    public void removeListener(ILabelProviderListener listener) {

    }

  }

  public class AddAction extends Action {
    public AddAction() {
      setText("添加");
    }

    public void run() {
      //新建一个实体对象
      PersonEO person = new PersonEO();
      person.setID(table.getTable().getItemCount() + 1);
      person.setName("Janet");
      person.setGender("女");
      person.setColor("红色");
      //添加一行数据
      table.add(person);
    }

  }

  public class DelAction extends Action {
    public DelAction() {
      setText("删除");
    }

    public void run() {
      //获得当前的所选中的行
      StructuredSelection selection = (StructuredSelection) table.getSelection();
      //注意，同时选中的可能是多行，这是返回的是数组对象
      //获得数组对象的一个元素，也就是只有选中一行的情况
      PersonEO p = (PersonEO) selection.getFirstElement();
      //从表中删除该行
      table.remove(p);
    }

  }

  public class FileterAction extends Action {
    //声明一个ViewerFilter对象
    ViewerFilter femaleFilter;

    public FileterAction() {
      //设置标题，样式为复选框样式
      super("筛选", AS_CHECK_BOX);
      //内部类创建该对象
      femaleFilter = new ViewerFilter() {
        //需实现ViewerFilter类中的抽象方法
        public boolean select(Viewer viewer, Object parentElement, Object element) {
          PersonEO p = (PersonEO) element;
          return p.getGender().equals("女");
        }
      };
      //初始化时设置为取消选中状态
      this.setChecked(false);
    }

    public void run() {
      //如果此时选中，则为表格设置过滤器
      if (isChecked())
        table.addFilter(femaleFilter);
      else//否则，移除表格过滤器
        table.removeFilter(femaleFilter);
      //刷新表格数据
      table.refresh();
    }
  }

}
