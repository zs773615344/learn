package cn.zs.learn.web.first.good.dao.factory;

import cn.zs.learn.web.first.good.dao.dao.GoodsDao;
import cn.zs.learn.web.first.good.dao.impl.GoodsImpl;

public class GoodsFactory {

	
	private GoodsFactory() {}
	
	public static GoodsDao CreateFactory() {		
		return new GoodsImpl();
	}
}
