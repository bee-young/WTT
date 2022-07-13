package db.product;

import java.text.DecimalFormat;
import java.util.Vector;

public class PartsVo {
	int   no;
	String code;
	String codeName;
	String spec;
	int price;
	DecimalFormat df = new DecimalFormat("###,###");
	
	public PartsVo() {}
	public PartsVo(String code, String codeName, String spec, int price) {
		this.code = code;
		this.codeName = codeName;
		this.spec = spec;
		this.price = price;
	}
	
	public Vector<String> getVector(){
		Vector<String> v = new Vector<String>();
		v.add(no+"");
		v.add(code);
		v.add(codeName);
		v.add(spec);
		v.add(df.format(price));
		return v;		
	}
	
	public String[] getArray() {
		String[] array = new String[5];
		array[0] = no+ "";
		array[1] = this.code;
		array[2] = this.codeName;
		array[3] = this.spec;
		array[4] = df.format(price);
		
		return array;
	}
	
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
