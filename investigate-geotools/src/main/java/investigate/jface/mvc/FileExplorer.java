package investigate.jface.mvc;

import investigate.swt.util.ImageFactory;
import org.eclipse.jface.viewers.*;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TableColumn;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileExplorer extends ApplicationWindow {

	private SashForm sash;//分割窗口
	private TreeViewer tree;//树
	private TableViewer table;//表格
	private OpenAction openAction;//事件处理对象

	public FileExplorer() {
		super(null);
		openAction = new OpenAction();
	}
	//初始化窗口内容
	protected Control createContents(Composite parent) {
		this.getShell().setText("简单文件浏览器");
		this.getShell().setMaximized(true);
		sash = new SashForm(parent, SWT.SMOOTH);
		sash.setLayoutData(new GridData(GridData.FILL_BOTH));
		initTree();//初始化树
		initTable();//初始化表格
		sash.setWeights(new int[] { 40, 60 });
		return parent;
	}
	//初始化表格
	private void initTable() {
		table = new TableViewer(sash);
		//创建表头
		new TableColumn(table.getTable(), SWT.LEFT).setText("名称");
		new TableColumn(table.getTable(), SWT.LEFT).setText("类型");
		new TableColumn(table.getTable(), SWT.LEFT).setText("大小");
		new TableColumn(table.getTable(), SWT.LEFT).setText("修改日期");
		for (int i = 0; i < table.getTable().getColumnCount(); i++) {
			table.getTable().getColumn(i).pack();
		}
		//设置表头和网格线可见
		table.getTable().setHeaderVisible(true);
		table.getTable().setLinesVisible(true);
		//设置表格内容提供器
		table.setContentProvider(new FileTableContentProvider());
		//设置表格标签提供器
		table.setLabelProvider(new FileTableLabelProvider());
		//设置排序对象
		table.setSorter(new FileSorter());
		//注册双击事件
		table.addDoubleClickListener(openAction);

	}
	//初始化树
	private void initTree() {
		tree = new TreeViewer(sash);
		//设置树内容提供器
		tree.setContentProvider(new FileTreeContentProvider());
		//设置树标签提供器
		tree.setLabelProvider(new FileTreeLabelProvider());
		//设置树初始化的数据，这里没有任何意义
		//使用的是根目录的数据在内容提供器中
		tree.setInput("root");
		//注册树选中事件
		tree.addSelectionChangedListener(openAction);
	};

	public static void main(String[] args) {
		FileExplorer test = new FileExplorer();
		test.setBlockOnOpen(true);
		test.open();
		Display.getCurrent().dispose();
	}

	public class FileTreeContentProvider implements ITreeContentProvider {

		public Object[] getChildren(Object element) {
			//过滤后只显示文件夹
			return ((File) element).listFiles(new AllowOnlyFoldersFilter());
		}

		public Object[] getElements(Object element) {
			File[] roots = File.listRoots();//获得根目录的文件
			List rootFolders = new ArrayList();
			//过滤根目录，只显示根目录中的文件夹
			for (int i = 0; i < roots.length; i++) {
				if (roots[i].isDirectory())
					rootFolders.add(roots[i]);
			}
			return rootFolders.toArray();
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

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {}

	}

	public class FileTreeLabelProvider implements ILabelProvider {

		public Image getImage(Object element) {
			File file = (File) element;
			if (file.isDirectory())
				return ImageFactory.loadImage(Display.getCurrent(), ImageFactory.FOLDER);
			return ImageFactory.loadImage(Display.getCurrent(),ImageFactory.FILE);
		}

		public String getText(Object element) {
			//对于根目录中的文件夹，没有名称，此时要显示路径
			String text = ((File) element).getName();
			if (text.length() == 0) {
				text = ((File) element).getPath();
			}
			return text;
		}

		public void addListener(ILabelProviderListener listener) {	}

		public void dispose() {
			ImageFactory.dispose();
		}

		public boolean isLabelProperty(Object element, String property) {
			return false;
		}

		public void removeListener(ILabelProviderListener listener) {}

	}

	class FileTableContentProvider implements IStructuredContentProvider {

		public void dispose() {}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {}

		public Object[] getElements(Object inputElement) {
			File file = (File) inputElement;
			return file.listFiles();
		}

	}

	class FileTableLabelProvider implements ITableLabelProvider {

		public Image getColumnImage(Object element, int columnIndex) {
			File file = (File) element;
			if (columnIndex == 0) {
				if (file.isDirectory())
					return ImageFactory.loadImage(Display.getCurrent(),ImageFactory.FOLDER);
				else
					return ImageFactory.loadImage(Display.getCurrent(),ImageFactory.FILE);
			}
			return null;
		}

		public String getColumnText(Object element, int columnIndex) {
			File file = (File) element;
			if (columnIndex == 0 )
				return file.getName();//显示文件名称
			else if (columnIndex == 1) {
				//显示文件类型
				if (file.isDirectory())
					return "文件夹";
				else
					return "文件";
			} else if (columnIndex == 2) {
				//如果是文件夹，则没有大小
				if (file.isDirectory())
					return "";
				else
					return file.length() + " KB";
			} else if (columnIndex == 3) {
				//显示日期
				Date date = new Date(file.lastModified());
				return date.toLocaleString();
			}
			return null;
		}

		public void addListener(ILabelProviderListener listener) {}

		public void dispose() {
			ImageFactory.dispose();
		}

		public boolean isLabelProperty(Object element, String property) {
			return false;
		}

		public void removeListener(ILabelProviderListener listener) {}

	}

	public class AllowOnlyFoldersFilter implements FileFilter {
		public boolean accept(File pathname) {
			return pathname.isDirectory();
		}

	}

	public class FileSorter extends ViewerSorter {
		public int category(Object element) {
			return ((File) element).isDirectory() ? 0 : 1;
		}
	}

	public class OpenAction implements ISelectionChangedListener, IDoubleClickListener {
		
		//选中树中一个节点时，所处理的事件
		public void selectionChanged(SelectionChangedEvent event) {
			table.setInput(getTreeSelection());
		}
		//双击表格中一列时
		public void doubleClick(DoubleClickEvent event) {
			Object selection =  getTableSelection();
			if  (selection==null)
				return;
			File file = (File) selection;
			if (file.isFile()) {
				//如果是文件，打开该文件
				Program.launch(file.getAbsolutePath());
			} else if (file.isDirectory()) {
				//如果是文件夹，在表格中打开文件夹
				table.setInput( selection );
			}
			
		}
	}
	//获得当前树选中的行
	public Object getTreeSelection(){
		IStructuredSelection selection = (IStructuredSelection) tree.getSelection();
		if (selection.size() != 1)
			return null;
		return selection.getFirstElement();
	}
	//获得当前表格选中的行
	public Object getTableSelection(){
		IStructuredSelection selection = (IStructuredSelection) table.getSelection();
		if (selection.size() != 1)
			return null;
		return selection.getFirstElement();
	}
}
