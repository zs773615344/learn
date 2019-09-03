package cn.zs.learn.web.first.good.dao.dao;

import cn.zs.learn.web.first.good.vo.GoodsModel;
import cn.zs.learn.web.first.good.vo.GoodsQueryModel;

import java.util.List;


public interface GoodsDao {
	
	public boolean create(GoodsModel gm);
	
	
	public boolean update(GoodsModel gm);
	
	public boolean delete(int uuid);
	
	public GoodsModel selectByUuid(int uuid);
	
	public List<GoodsModel> selectAll();
	
	public List<GoodsModel> selectByCondition(GoodsQueryModel gqm);

}
