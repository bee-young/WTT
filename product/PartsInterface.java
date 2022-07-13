package db.product;

import java.util.List;

public interface PartsInterface {
	public boolean insert(PartsVo vo);
	public List<PartsVo> select(String findStr);
	public PartsVo selectOne(String code);
	public boolean update(PartsVo vo);
	public boolean delete(String code);
}
