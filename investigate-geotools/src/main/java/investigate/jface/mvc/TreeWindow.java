package investigate.jface.mvc;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.widgets.*;

import java.util.ArrayList;
import java.util.List;

public class TreeWindow extends ApplicationWindow {
	private TreeViewer tree;//声明树对象
	private Menu popUpMenu;//上下文菜单
	private List data ;//树的初始数据
	public TreeWindow() {
		super(null);
		initData();
	}
	//初始化数据
	private void initData() {
		data = new ArrayList();
		CategoryEO cate1 =  new CategoryEO("图书");
		cate1.add( new CategoryEO("小说"));
		cate1.add( new ProductEO("Eclispe"));
		cate1.add( new ProductEO("SWT"));
		data.add( cate1 );
		CategoryEO cate2 =  new CategoryEO("音像");
		data.add( cate2 );
		ProductEO product1 =  new ProductEO("服装");
		data.add( product1 );
	}
	//设置窗口属性
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setSize(200, 300);
		shell.setText("TreeViewer程序示例");
	}
	//创建控件
	protected Control createContents(Composite parent) {
		initTree(parent);//初始化树
		createContextMenu(parent);//创建上下文菜单
		return parent;
	}
	private void initTree(Composite parent) {
		tree = new TreeViewer(parent);//创建树
		tree.setContentProvider(new TreeContentProvider());//设置树内容提供器 
		tree.setLabelProvider(new TreeLabelProvider());//设置标器提供器
		tree.setInput(data);//设置初始化数据

	}
	//创建初始化菜单
	private void createContextMenu(Composite parent) {
		MenuManager top = new MenuManager();
		top.add( new NewProductAction ());
		top.add( new NewCategoryAction());
		top.add( new Separator());
		top.add( new DeleteAction());
		//创建上下文菜单
		popUpMenu = top.createContextMenu( parent );
		tree.getTree().setMenu(popUpMenu);

	}
	//自定义方法，获得树当前选中的节点
	private TreeElement getSelectElement() {
		StructuredSelection select = (StructuredSelection) tree.getSelection();
		TreeElement element = (TreeElement) select.getFirstElement();
		return element;
	}

	public static void main(String[] args) {
		TreeWindow test = new TreeWindow();
		test.setBlockOnOpen(true);
		test.open();
		Display.getCurrent().dispose();
	}
	public class NewProductAction extends Action{
		NewProductAction (){
			super("新建产品");
		}
		public void run(){
			TreeElement element = getSelectElement();
			InputDialog input = new InputDialog(getShell(), " 请输入产品", "产品名", "", null);
			int rt = input.open();
			if (rt == OK)
				tree.add(element, new ProductEO(input.getValue()));
		}
	}
	public class NewCategoryAction extends Action{
		NewCategoryAction (){
			super("新建类别");
		}
		public void run(){
			TreeElement element = getSelectElement();
			if (element instanceof ProductEO)
				return;
			InputDialog input = new InputDialog(getShell(), " 请输入类别", "类别名", "", null);
			int rt = input.open();
			if (rt == OK)
				tree.add(element, new CategoryEO(input.getValue()));
		}
	}
	public class DeleteAction extends Action{
		DeleteAction (){
			super("删除");
		}
		public void run(){
			TreeElement element = getSelectElement();
			tree.remove(element );
		}
	}
}
