package cn.zs.learn.web.first.util;

import cn.zs.learn.web.first.good.vo.GoodsModel;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;



public class FileUtil {
	
	//������
	public static List<GoodsModel> input(String fileName) {
		File file=new File(fileName);
		List<GoodsModel> list=null;
		if(file.exists()) {
			FileInputStream fis=null;
			BufferedInputStream bis=null;
			ObjectInputStream ois=null;
			
			try {
				fis=new FileInputStream(file);
				bis=new BufferedInputStream(fis);
				ois=new ObjectInputStream(bis);
				list=(List)ois.readObject();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				FileUtil.closeInputStream(ois);
				FileUtil.closeInputStream(bis);
				FileUtil.closeInputStream(fis);
			}
			
		}else {
			list=new ArrayList<>();
		}
		
		return list;
	}
	
	
	//�����
	public static void output(List list,String fileName) {
		File file=new File(fileName);
		FileOutputStream fos=null;
		BufferedOutputStream bos=null;
		ObjectOutputStream oos=null;
		
		try {
			fos=new FileOutputStream(file);
			bos=new BufferedOutputStream(fos);
			oos=new ObjectOutputStream(bos);				
			oos.writeObject(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			FileUtil.closeOutputStream(oos);
			FileUtil.closeOutputStream(bos);
			FileUtil.closeOutputStream(fos);
		}
		
	}
	
	public static void closeInputStream(InputStream in) {
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void closeOutputStream(OutputStream out) {
		try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
