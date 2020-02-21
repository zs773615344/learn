package cn.zs.learn.java.zeppelinclient;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;




public class notebook {
	
//	static URI uri = new URI("http://192.168.1.229:8090/api/notebook");
	 private static String uri="http://192.168.1.231:8090/api/notebook";
	 private static String InterpretersUri="http://192.168.1.224:8090/api/interpreter";

	public static void main(String[] args) throws Exception  {
//		File notebook=new File("D:\\notebook.json");
//		File interpreter=new File("D:\\interpreter.json");
//		createNote(notebook);
//		createNewInterConf(interpreter);
		
	}
	
	//删除依赖关系解析的存储库
	public static void deleteRepo(String RepoID) throws Exception {
		responseContent(request.delete(InterpretersUri+"/repository/"+RepoID));
	}
	
	//添加用于依赖关系解析的新存储库
	public static void addRepo(File file) throws Exception {
		responseContent(request.post(InterpretersUri+"/repository", new FileEntity(file)));
	}
	
	//重新启动解释器
	public static void rebootInter(String interID) throws Exception {
		responseContent(request.put(InterpretersUri+"/setting/restart/"+interID));
	}
	
	//删除解释器设置
	public static void deleteInter(String interID) throws Exception {
		responseContent(request.delete(InterpretersUri+"/setting/"+interID));
	}
	
	//更新解释器设置
	public static void updataNewInterConf(String interID,File file) throws Exception {
		responseContent(request.put(InterpretersUri+"/setting/"+interID,new FileEntity(file)));
	}
	
	//创建一个新的解释器设置
	public static void createNewInterConf(File file) throws Exception {
		responseContent(request.post(InterpretersUri+"/setting",new FileEntity(file)));
	}
	
	//通过设置ID获取注册的解释器设置
	public static void getSingleInterConf(String settingID) throws Exception {
		responseContent(request.get(InterpretersUri+"/setting/"+settingID));
	}
	
	//所有解释器设定清单
	public static void getAllInterConf() throws Exception {
		responseContent(request.get(InterpretersUri+"/setting"));
	}
	
	//解释器名单
	public static void getInterList() throws Exception {
		responseContent(request.get(InterpretersUri));
	}
	
	//全文搜索所有笔记中的段落
	public static void allSearchPara(String query) throws Exception {
		String queryUri=uri+"/search?q="+query;
		responseContent(request.get(queryUri));
	}
	
	//将段落移动到特定索引
	public static void moveIndex(String noteID,String paraID,String newIndex) throws Exception {
		String indexUri=uri+"/"+noteID+"/paragraph/"+paraID+"/move/"+newIndex;
		responseContent(request.post(indexUri));
	}
	
	//停止一段
	public static void stopSinglePara(String noteID,String paraID) throws Exception {
		String paraUri=uri+"/job/"+noteID+"/"+paraID;
		responseContent(request.delete(paraUri));
	}
	
	//同步运行段落  可根据段落执行结果返回success或error
	public static void someRunPara(String noteID,String paraID) throws Exception {
		String paraUri=uri+"/run/"+noteID+"/"+paraID;
		responseContent(request.post(paraUri));
	} 
	
	//异步运行段落  即使段落运行失败，该API也会始终返回success
	public static void RunPara(String noteID,String paraID) throws Exception{
		String paraUri=uri+"/job/"+noteID+"/"+paraID;
		responseContent(request.post(paraUri));
	}
	
	//删除一段 
	public static void deletePara(String noteID,String paraID) throws Exception {
		String paraUri=uri+"/"+noteID+"/paragraph/"+paraID;
		responseContent(request.delete(paraUri));
	}
	
	//更新段落配置
	public static void updataParaConf(String noteID,String paraID) throws Exception {
		String confUri=uri+"/"+noteID+"/paragraph/"+paraID+"/config";
		responseContent(request.post(confUri));
	}
	
	//获取单个段落状态
	public static void getSingleParaStatus(String noteID,String paraID) throws Exception {
		String paraUri=uri+"/job/"+noteID+"/"+paraID;
		responseContent(request.get(paraUri));
	}
	
	//获取段落信息
	public static void getParaInfo(String noteID,String paraID) throws Exception {
		String paraUri=uri+"/"+noteID+"/paragraph/"+paraID;
		responseContent(request.get(paraUri));
	}
	
	//创建段落
	public static void createPara(String noteID,File file) throws Exception {
		String paraUri=uri+"/"+noteID+"/paragraph";
		responseContent(request.post(paraUri, new FileEntity(file)));
	}
	 
	//清除所有段落结果
	public static void clearAllParaRes(String noteID) throws Exception {
		String noteUri=uri+"/"+noteID+"/clear";
		responseContent(request.put(noteUri));
	}
	
	//停止所有段落
	public static void stopAllPara(String noteID) throws Exception {
		String noteUri=uri+"/job/"+noteID;
		responseContent(request.delete(noteUri));
	}
	
	//运行所有段落
	public static void runAllPara(String noteID) throws Exception {
		String noteUri=uri+"/job/"+noteID;
		responseContent(request.post(noteUri));
	}
	
	//导出笔记
	public static void exportNote(String noteID) throws Exception {
		String noteUri=uri+"/export/"+noteID;
		responseContent(request.get(noteUri));
	}
	
	//导入笔记
	public static void importNote(File file) throws Exception {
		String noteUri=uri+"/import";
		responseContent(request.post(noteUri, new FileEntity(file)));
	}
	
	//克隆笔记
	public static void cloneNote(String noteID,String cloneName) throws Exception {
		String noteUri=uri+"/"+noteID;
		StringEntity entity=new StringEntity("{\"name\":\""+cloneName+"\"}") ; 
		responseContent(request.post(noteUri, entity));
	}
	
	//获取现有的笔记信息
	public static void getNoteInfo(String noteID) throws Exception {
		String noteUri=uri+"/"+noteID;
		responseContent(request.get(noteUri));
	}
	
	//获取所有段落状态
	public static void getParaStatus(String noteID) throws Exception {
		String noteUri=uri+"/job/"+noteID;
		responseContent(request.get(noteUri));
	}
	
	//创建notebook
	public static void createNote(File file) throws Exception {
		if(file.exists()) {	
			responseContent(request.post(uri, new FileEntity(file)));
		}else {
			throw new FileNotFoundException();
		}
	}
	
	//删除notebook
	public static void deleteNote(String noteID) throws Exception {
		String noteUri=uri+"/"+noteID;
		responseContent(request.delete(noteUri));
	}
	
	// 获取notebook列表
	public static void getNoteList() throws Exception {
		responseContent(request.get(uri));
	}
	
	
	
	//获取返回值信息
	public static void responseContent(HttpResponse result) throws Exception{
		System.out.println(result.getStatusLine().getStatusCode());
		if(result.getStatusLine().getStatusCode()==200) {
			System.out.println("success");
		}	
		 InputStream in = result.getEntity().getContent();
		if(in!=null) {
			BufferedReader responseReader=new BufferedReader(new InputStreamReader(in));
			String readLine=null;
			StringBuffer responseSb=new StringBuffer();
			while((readLine=responseReader.readLine())!=null){
//				System.out.println(readLine);	
				responseSb.append(readLine);
			}
			System.out.println(responseSb.toString());	
		}
//		File file=new File("D:\\haha.json");
//		OutputStream os=new FileOutputStream(file);
//		result.getEntity().writeTo(os);
		
		
	}
}
