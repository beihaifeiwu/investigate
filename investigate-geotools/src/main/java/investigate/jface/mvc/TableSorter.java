package investigate.jface.mvc;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

public class TableSorter extends ViewerSorter {
	private static final int ASCENDING = 0;
	private static final int DESCENDING = 1;
	
	private int order;//判断是升序还是降序
	private int column;//判断排序的列

	public void doSort(int column) {
		// 如果是同一列，改变排列的顺序
		if (column == this.column) {
			order = 1 - order;
		} else {// 如果是另一列，则默认为升序排列
			this.column = column;
			order = ASCENDING;
		}
	}
	//覆盖父类的方法，返回比较结果有-1,0,1三种情况
	public int compare(Viewer viewer, Object e1, Object e2) {
		int result = 0;

		PersonEO p1 = (PersonEO) e1;
		PersonEO p2 = (PersonEO) e2;
		
		//默认是升序
		switch (column) {
		case TableWindow.ID:
			result = p1.getID() > p2.getID() ? 1 : -1;
			break;
		case TableWindow.NAME:
			result = collator.compare(p1.getName(), p2.getName());
			break;
		case TableWindow.GENDER:
			result = collator.compare(p1.getGender(), p2.getGender());
			break;
		case TableWindow.COLOR:
			result = collator.compare(p1.getColor(), p2.getColor());
			break;
		}
		//如果此时为降序
		if (order == DESCENDING)
			result = -result;
		return result;
	}
}
