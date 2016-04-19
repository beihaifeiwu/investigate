package investigate.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.*;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

import java.io.File;


@SuppressWarnings("Duplicates") public class DragSample {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
    showDragTree(shell);
    showShoppingCart(shell);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

  private static void showDragTree(Shell shell) {
    shell.setLayout(new FillLayout());
    //创建一个树对象
    final Tree tree = createFolderTree(shell);
    //定义拖放源对象
    DragSource dragSource = new DragSource(tree, DND.DROP_MOVE|DND.DROP_COPY);
    //设置传输的数据为文本型String类型
    dragSource.setTransfer(new Transfer[] { TextTransfer.getInstance()});
    //注册拖放源时的事件处理
    dragSource.addDragListener(new DragSourceListener() {
      public void dragStart(DragSourceEvent event) {
        if (tree.getSelectionCount() == 0)
          event.doit = false;
      }
      public void dragSetData(DragSourceEvent event) {
        if (TextTransfer.getInstance().isSupportedType(event.dataType)) {
          event.data = tree.getSelection()[0].getText(0);
        }
      }
      public void dragFinished(DragSourceEvent event) {

      }
    });
    //定义拖放目标对象
    DropTarget dropTarget = new DropTarget(tree, DND.DROP_MOVE | DND.DROP_DEFAULT|DND.DROP_COPY);
    //设置目标对象可传输的数据类型
    dropTarget.setTransfer(new Transfer[] { TextTransfer.getInstance()});
    //注册目标对象的事件处理
    dropTarget.addDropListener(new DropTargetListener() {
      public void dragEnter(DropTargetEvent event) {
        if (event.detail == DND.DROP_DEFAULT)
          event.detail = DND.DROP_COPY;
      }
      public void dragOperationChanged(DropTargetEvent event) {
        if (event.detail == DND.DROP_DEFAULT)
          event.detail = DND.DROP_COPY;
      }
      public void dragOver(DropTargetEvent event) {
        event.feedback = DND.FEEDBACK_EXPAND | DND.FEEDBACK_SELECT;
      }
      //当松开鼠标时触发的事件
      public void drop(DropTargetEvent event) {
        if (event.item == null)
          return;
        //首先获得目标对象中拖拽的树节点
        TreeItem eventItem = (TreeItem) event.item;
        if (TextTransfer.getInstance().isSupportedType(event.currentDataType)) {
          //获得数据源设置的字符串
          String s = (String) event.data;
          //在当前位置插入一个节点
          TreeItem newItem = null ;
          if (eventItem.getParentItem()==null)
            newItem = new TreeItem(eventItem.getParent(), SWT.NONE);
          else
            newItem = new TreeItem(eventItem.getParentItem(),SWT.NONE);
          newItem.setText(s);
        }
      }

      public void dragLeave(DropTargetEvent event) {
      }
      public void dropAccept(DropTargetEvent event) {
      }

    });
    shell.setSize(300, 150);
  }

  //创建树，利用目录结构来初始化树数据
	public static Tree createFolderTree(Composite parent) {
		Tree tree = new Tree(parent, SWT.BORDER | SWT.SINGLE);
		File root = new File("E:\\tools");
		File[] files = root.listFiles();
		for (int i = 0; i < files.length; i++) {
      TreeItem item = new TreeItem(tree, SWT.NONE);
      item.setText(files[i].getName());
      if (files[i].isDirectory())
        setDirectory(files[i], item);
    }
		return tree;

	}
	//设置子树的子孙数据，递归调用该方法
	public static void setDirectory(File file, TreeItem parent) {
		File[] files = file.listFiles();
		for (int i = 0; i < files.length; i++) {
			TreeItem item = new TreeItem(parent, SWT.NONE);
			item.setText(files[i].getName());
			if (files[i].isDirectory())
				setDirectory(files[i], item);
		}
	}


  public static void showShoppingCart(Shell parent){
    Shell shell = new Shell(parent);
    shell.setText("Drag and Drop Sample");
    GridLayout layout = new GridLayout();
    layout.numColumns = 2;
    shell.setLayout(layout);
    // 创建左侧的产品表格
    final Table productList = createProductTable(shell);
    // 创建右侧的产品表格
    final Table shoppingCart = createCartTable(shell);
    // 创建拖放源
    DragSource source = new DragSource(productList, DND.DROP_MOVE | DND.DROP_COPY);
    // 设置传输的数据为文本型String类型
    source.setTransfer(new Transfer[] { TextTransfer.getInstance() });
    // 注册当拖动源时触发的事件
    source.addDragListener(new DragSourceListener() {
      // 当开始拖动源控件时，
      public void dragStart(DragSourceEvent event) {
        if (productList.getSelectionCount() == 0)
          event.doit = false;
      }

      // 设置拖拽过程中传输的数据
      public void dragSetData(DragSourceEvent event) {
        if (TextTransfer.getInstance().isSupportedType(event.dataType)) {
          // 获得当前选中的一行
          TableItem item = productList.getSelection()[0];
          // 将产品和产品的单价表示成字符串的形式，用逗号分隔，
          // 例如"CPU，1200"，并赋值给event.data
          event.data = item.getText(0) + "，" + item.getText(2);
        }
      }

      // 当拖动完成后，释放光标所触发的事件
      public void dragFinished(DragSourceEvent event) {
      }
    });

    // 定义拖放的目标控件
    DropTarget target = new DropTarget(shoppingCart, DND.DROP_COPY | DND.DROP_DEFAULT);
    // 设置目标控件所接受的传输数据
    target.setTransfer(new Transfer[] { TextTransfer.getInstance() });
    target.addDropListener(new DropTargetListener() {
      // 当光标进入到目标控件的区域时
      public void dragEnter(DropTargetEvent event) {
        if (event.detail == DND.DROP_DEFAULT)
          event.detail = DND.DROP_COPY;
      }

      // 当光标离开目标控件的区域时
      public void dragLeave(DropTargetEvent event) {

      }

      // 当改变event.detail类型时触发的事件
      public void dragOperationChanged(DropTargetEvent event) {
        if (event.detail == DND.DROP_DEFAULT)
          event.detail = DND.DROP_COPY;
      }

      // 当光标在目标区域上时
      public void dragOver(DropTargetEvent event) {
      }

      // 拖放完成后触发的事件
      public void drop(DropTargetEvent event) {
        // 首先获得当然目标表格项
        TableItem item = (TableItem) event.item;
        if (item == null) {
          event.detail = DND.DROP_NONE;
          return;
        }
        // 如果支持文本型传输数据
        if (TextTransfer.getInstance().isSupportedType(event.currentDataType)) {
          // 首先获得源数据中的字符串数据，该数据是通过dragSetData()方法设置的
          // 例如此时获得的数据dataInfo为"CPU，1200";
          String dataInfo = (String) event.data;
          if (dataInfo == null)
            return;
          // 首先解析逗号分割的字符串，逗号之前的字符为产品名，逗号之后的字符为价格
          int index = dataInfo.indexOf("，");
          String name = dataInfo.substring(0, index);
          double price = 0.00d;
          try {
            price = Double.parseDouble(dataInfo.substring(index + 1));
          } catch (Exception e) {
            price = 0.00d;
          }
          // 获得目标表格
          Table parent = item.getParent();
          TableItem it = null;
          // 循环目标表格中的每一项，是否购物车中已经添加了该产品
          for (int i = 0; i < parent.getItemCount(); i++) {
            TableItem temp = parent.getItem(i);
            if (temp.getText(0).equals(name)) {
              it = temp;
              break;
            }
          }
          // 如果没找到，则新建一行，数量加1，并显示总价格
          if (it == null) {
            it = new TableItem(parent, SWT.NONE);
            it.setText(0, name);
            it.setText(1, "1");
            it.setText(2, "" + price);
          } else {// 如果找到，更新改产品的数量和总价格
            int number = Integer.parseInt(it.getText(1)) + 1;
            double total = number * price;
            it.setText(1, "" + number);
            it.setText(2, "" + total);
          }
          // 最后更新购物车的总价和数量
          TableItem sumItem = parent.getItem(0);
          sumItem.setText(1, Integer.parseInt(sumItem.getText(1)) + 1 + "");
          sumItem.setText(2, Double.parseDouble(sumItem.getText(2)) + price + "");
        }
      }

      public void dropAccept(DropTargetEvent event) {
      }
    });
    shell.pack();
    shell.open();
  }

  // 创建产品的表格
  public static Table createProductTable(Composite parent) {
    Table table = new Table(parent, SWT.FULL_SELECTION | SWT.SINGLE);
    String[] heads = { "产品名称", "单位", "价格" };
    for (int i = 0; i < heads.length; i++) {
      TableColumn col = new TableColumn(table, SWT.NONE);
      col.setText(heads[i]);
    }
    TableItem item = new TableItem(table, SWT.NONE);
    item.setText(new String[] { "CPU", "个", "1200.00" });
    item = new TableItem(table, SWT.NONE);
    item.setText(new String[] { "内存", "个", "255.00" });
    item = new TableItem(table, SWT.NONE);
    item.setText(new String[] { "笔记本", "台", "9800.00" });
    table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    table.setHeaderVisible(true);
    table.setLinesVisible(true);
    for (int i = 0; i < heads.length; i++) {
      table.getColumns()[i].pack();
    }
    return table;
  }

  // 创建购物车表格
  public static Table createCartTable(Composite parent) {
    Table table = new Table(parent, SWT.FULL_SELECTION);
    String[] heads = { "产品名称", "数量", "总价格" };
    for (int i = 0; i < heads.length; i++) {
      TableColumn col = new TableColumn(table, SWT.NONE);
      col.setText(heads[i]);
    }
    TableItem item = new TableItem(table, SWT.NONE);
    item.setText(new String[] { "", "0", "0.00" });
    table.setHeaderVisible(true);
    table.setLinesVisible(true);
    table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    for (int i = 0; i < heads.length; i++) {
      table.getColumns()[i].pack();
    }
    return table;
  }
}