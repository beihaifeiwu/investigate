package investigate.swt;

import investigate.swt.util.ImageFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.TreeEvent;
import org.eclipse.swt.events.TreeListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;


public class TreeSample {

	public static void main(String[] args) {

		final Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setText("Tree Sample");
		shell.setLayout(new FillLayout());
		// 创建一个树对象
		final Tree tree = new Tree(shell, SWT.BORDER | SWT.SINGLE);
		// 创建树的一个根节点
		TreeItem root = new TreeItem(tree, SWT.NULL);
		root.setText("根节点");
		// 创建子孙节点
		TreeItem child1 = new TreeItem(root, SWT.NULL);
		child1.setText("子孙1");
		TreeItem child2 = new TreeItem(root, SWT.NULL);
		child2.setText("子孙2");
		TreeItem child3 = new TreeItem(root, SWT.NULL);
		child3.setText("子孙3");

		TreeItem child11 = new TreeItem(child1, SWT.NULL);
		child11.setText("子孙11");
		TreeItem child12 = new TreeItem(child1, SWT.NULL);
		child12.setText("子孙12");

		TreeItem child111 = new TreeItem(child11, SWT.NULL);
		child111.setText("子孙111");
		TreeItem child112 = new TreeItem(child11, SWT.NULL);
		child112.setText("子孙112");
		// 调用convertImage方法来设置树的图标
		convertImage(tree);
		// 为树注册树监听事件
		tree.addTreeListener(new TreeListener() {
			// 当折叠树节点时
			public void treeCollapsed(TreeEvent e) {
				// 首先获得触发事件的TreeItem
				TreeItem item = (TreeItem) e.item;
				// 将该节点的图标设置为关闭状态
				item.setImage(ImageFactory.loadImage(tree.getDisplay(),
						ImageFactory.TOC_CLOSED));
			}

			// 当展开树节点时
			public void treeExpanded(TreeEvent e) {
				TreeItem item = (TreeItem) e.item;
				item.setImage(ImageFactory.loadImage(tree.getDisplay(),
						ImageFactory.TOC_OPEN));
			}

		});
		/********************************************************
		//创建一个可编辑的TreeEditor对象
		final TreeEditor editor = new TreeEditor(tree);
		editor.horizontalAlignment = SWT.LEFT;
		editor.grabHorizontal = true;
		editor.minimumWidth = 30;
		//注册选中事件
		tree.addSelectionListener(new SelectionAdapter() {
			//当鼠标双击节点时使节点可编辑
			public void widgetDefaultSelected(SelectionEvent e) {
				//释放之前编辑的控件
				Control oldEditor = editor.getEditor();
				if (oldEditor != null) oldEditor.dispose();
				//获得触发事件的TreeItem，如果为null，返回
				TreeItem item = (TreeItem)e.item;
				if (item == null) return;
				//创建一个文本框，作为编辑节点时输入的文字
				Text newEditor = new Text(tree, SWT.NONE);
				//将树节点的值赋值给文本框
				newEditor.setText(item.getText());
				//当文本框的值改变时，也相应的该把你树节点数据的值
				newEditor.addModifyListener(new ModifyListener() {
					public void modifyText(ModifyEvent e) {
						Text text = (Text)editor.getEditor();
						editor.getItem().setText(text.getText());
					}
				});
				newEditor.selectAll();//选中所有文本框
				newEditor.setFocus();//并将焦点设置为该文本框
				//将树节点与文本框节点绑定
				editor.setEditor(newEditor, item);
			}

		});
		****************************************************************/
		/***************************************************************
		//为树创建5列
		for ( int i=0;i<5 ;i++){
			TreeColumn column = new TreeColumn( tree , SWT.NONE);
			column.setText("column"+i);
		}
		for ( int i=0;i<tree.getColumnCount() ;i++)
		  tree.getColumn(i).pack();
		//设置网格线可见
		tree.setLinesVisible( true );
		//设置表头可见
		tree.setHeaderVisible( true );
		***************************************************************/
		shell.setSize(200, 150);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		ImageFactory.dispose();
		display.dispose();
	}

	// 设置树图标的方法
	public static void convertImage(Tree tree) {
		// 这里假设只有一个根节点
		TreeItem[] items = tree.getItems();
		// 首先根据根节点的状态设置图标
		if (items[0].getExpanded())// 如果该节点为展开状态
			items[0].setImage(ImageFactory.loadImage(tree.getDisplay(),
					ImageFactory.TOC_OPEN));
		else
			// 否则，如果为折叠状态
			items[0].setImage(ImageFactory.loadImage(tree.getDisplay(),
					ImageFactory.TOC_CLOSED));
		// 设置该根节点的图标
		setChildImage(items[0]);
	}

	// 设置一个节点的方法，该方法非常重要，要理解该方法的递归用法
	// 参数item可以把单独看作是树中的某一个TreeItem
	public static void setChildImage(TreeItem item) {
		// 首先获得该TreeItem的所有子TreeItem
		TreeItem[] items = item.getItems();
		// 循环每一个TreeItem
		for (int i = 0; i < items.length; i++) {
			// 如果这个TreeItem下没有子孙
			if (items[i].getItems().length == 0)
				items[i].setImage(ImageFactory.loadImage(item.getDisplay(),
						ImageFactory.TOPIC));
			else {// 如果这个TreeItem有多个子孙
				// 如果这个TreeItem是展开状态，则设置展开的图片
				if (items[i].getExpanded())
					items[i].setImage(ImageFactory.loadImage(item.getDisplay(),
							ImageFactory.TOC_OPEN));
				else
					// 否则，则设置折叠的图片
					items[i].setImage(ImageFactory.loadImage(item.getDisplay(),
							ImageFactory.TOC_CLOSED));
				// 要为该TreeItem得子孙设置图标，递归调用setChildImage方法
				setChildImage(items[i]);
			}
		}
	}

}
