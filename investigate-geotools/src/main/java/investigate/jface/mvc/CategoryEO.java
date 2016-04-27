package investigate.jface.mvc;

import java.util.ArrayList;
import java.util.List;

public class CategoryEO implements TreeElement{
	private String name ;
	private List lists ;//所有子孙
	public CategoryEO( String name ){
		this.name = name ;
		lists = new ArrayList();
	}
	public String getName() {
		return name;
	}

	public boolean hasChildren() {
		if ( lists.size()>0)
			return true;
		return false;
	}

	public List getChildren() {
		return lists;
	}
	//添加子孙，可以使类别也可以使产品
	public void add(TreeElement element ) {
		lists.add( element );
	}

}
