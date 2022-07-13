package db.product;

import java.util.List;

public interface ProductInterface {
	public String insert(ProductVo vo);
	public String update(ProductVo vo);
	public String delete(int sno);
	public List<ProductVo> select(String findStr);
	public ProductVo selectOne(int sno);
	public ProductVo selectOne(String code);
}
