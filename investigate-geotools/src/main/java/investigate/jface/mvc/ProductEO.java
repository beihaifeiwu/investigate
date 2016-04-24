package investigate.jface.mvc;

import java.util.List;

public class ProductEO implements TreeElement{
	private String name ;
	public ProductEO( String name ){
		this.name =  name ;
	}
	public String getName() {
		return name;
	}
	public boolean hasChildren() {
		return false;
	}
	public List getChildren() {
		return null;
	}
}
