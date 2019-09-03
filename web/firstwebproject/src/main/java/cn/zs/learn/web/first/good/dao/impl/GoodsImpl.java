package cn.zs.learn.web.first.good.dao.impl;

import java.util.List;
import java.util.ArrayList;
import cn.zs.learn.web.first.good.dao.dao.GoodsDao;
import cn.zs.learn.web.first.good.vo.GoodsModel;
import cn.zs.learn.web.first.good.vo.GoodsQueryModel;
import cn.zs.learn.web.first.util.FileUtil;

public class GoodsImpl implements GoodsDao{
	
	private final String FILE_NAME="D:\\firstwebproject.txt";
	private List<GoodsModel> list=new ArrayList<GoodsModel>();

	public boolean create(GoodsModel gm) {
		// TODO Auto-generated method stub
		list=FileUtil.input(FILE_NAME);
		if(list.contains(gm)) {
			return false;
		}
		list.add(gm);
		FileUtil.output(list, FILE_NAME);
		
		return true;
	}

	public boolean update(GoodsModel gm) {
		// TODO Auto-generated method stub
		list=FileUtil.input(FILE_NAME);
		if(!list.contains(gm)) {
			return false;
		}
		list.remove(gm);
		list.add(gm);
		FileUtil.output(list, FILE_NAME);
		
		return true;
	}

	public boolean delete(int uuid) {
		// TODO Auto-generated method stub
		list=FileUtil.input(FILE_NAME);
		boolean success=false;
		for(GoodsModel li:list) {
			if(li.getUuid()==uuid) {
				list.remove(li);
				success=true;
			}
		}
		FileUtil.output(list, FILE_NAME);
		return success;
	}

	public GoodsModel selectByUuid(int uuid) {
		// TODO Auto-generated method stub
		list=FileUtil.input(FILE_NAME);
		GoodsModel gm=null;
		for(GoodsModel li:list) {
			if(li.getUuid()==uuid) {
				gm=li;
			}
		}
		return gm;
	}

	public List<GoodsModel> selectAll() {
		// TODO Auto-generated method stub
		list=FileUtil.input(FILE_NAME);
		return list;
	}

	public List<GoodsModel> selectByCondition(GoodsQueryModel gqm) {
		// TODO Auto-generated method stub
		list=FileUtil.input(FILE_NAME);
		if(gqm.getUuid()>0) {
			
		}
		return null;
	}
	
	public static void main(String[] args) {
		GoodsModel gm=new GoodsModel();
		gm.setDesc("si");
		gm.setName("li");
		gm.setUuid(2);
		gm.setPrice(15f);
		GoodsImpl gi=new GoodsImpl();
//		gi.create(gm);
//		gi.update(gm);
//		gi.delete(1);
		List<GoodsModel> li = gi.selectAll();
		for(GoodsModel l:li) {
			System.out.println(l.toString());
		}
	}
	

}
