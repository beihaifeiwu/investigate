package investigate.jface.mvc;

import java.util.List;

public interface TreeElement{
	public String getName();//节点名称
	public boolean hasChildren();//是否有子孙
	public List getChildren();//获得所有子孙
}
