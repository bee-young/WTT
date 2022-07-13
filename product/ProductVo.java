package db.product;

import java.text.DecimalFormat;
import java.util.Vector;

public class ProductVo {
	int sno;
	String code;
	String nal;
	int ea;
	int price;
	int amt;
	
	String codeName;
	String spec;
	
	DecimalFormat df = new DecimalFormat("###,###");
	
	public ProductVo() {}
	
	public ProductVo(int sno, String code, String nal, int ea, int price) {
		this.sno = sno;
		this.code = code;
		this.nal = nal;
		this.ea = ea;
		this.price = price;
		this.amt = ea*price;
	}
	
	public ProductVo(int sno, String code, String codeName, String nal,
									 int ea, int price) {
		
		this(sno, code, nal, ea, price);
		this.codeName = codeName;
		
	}
	
	public Vector<String> getVector(){
		Vector<String> v = new Vector<String>();
		
		v.add(sno+"");
		v.add(code);
		v.add(codeName);
		v.add(nal);
		v.add(df.format(ea));
		v.add(df.format(price));
		v.add(df.format(amt));
		
		return v;
		
	}

	
	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
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

	public String getNal() {
		return nal;
	}

	public void setNal(String nal) {
		this.nal = nal;
	}

	public int getEa() {
		return ea;
	}

	public void setEa(int ea) {
		this.ea = ea;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getAmt() {
		return amt;
	}

	public void setAmt(int amt) {
		this.amt = amt;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}
	
	
	
}
