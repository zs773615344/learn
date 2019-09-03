package cn.zs.learn.bigdate.hadoop.hdfs;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.apache.hadoop.io.IOUtils;

public class Hdfs {
	private static Configuration conf = null;
	private static FileSystem fs = null;
	static {
		// fs = FileSystem.get(new URI("hdfs://itcast01:9000"),
		// new Configuration(), "root");

		conf = new Configuration();
		String str = "hdfs://172.26.40.74:9000";
		conf.set("fs.defaultFS", str);
		try {
			fs = FileSystem.get(conf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void CreateDir(String Dir) throws IOException {
		Path dfs = new Path(Dir);
		fs.mkdirs(dfs);
	}

	public static void DeleteDir(String Dir) throws IOException {
		Path dfs = new Path(Dir);
		// fs.delete(dfs); 
		fs.delete(dfs, false); 
		// fs.delete(dfs, true);
	}

	public static boolean IsExist(String name) throws IOException {
		Path dfs = new Path(name);
		return fs.exists(dfs);
	}

	public static boolean Rename(String src, String dst) throws IOException {

		if (!Hdfs.IsExist(src)) {
			throw new FileNotFoundException("rename source " + src + " not found.");
		} else {
			Path oldsrc = new Path(src);
			Path newdst = new Path(dst);
			return fs.rename(oldsrc, newdst);
		}
	}

	public static void GetModificationTime(String name)
			throws FileNotFoundException, IllegalArgumentException, IOException {
		if (!Hdfs.IsExist(name)) {
			throw new FileNotFoundException("rename source " + name + " not found.");
		} else {
			Path dfs = new Path(name);
			FileStatus status = fs.getFileStatus(dfs);
			Long gmt = status.getModificationTime();
			// System.out.println(gmt); 
			Date date = new Date(gmt);
			// System.out.println(date); 
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println(name + "����޸�ʱ�䣺" + df.format(date));
			System.out.println(name + "�������ʱ�䣺" + df.format(new Date(status.getAccessTime())));

			// System.out.println("getGroup "+status.getGroup());
			// System.out.println("getOwner "+status.getOwner());
			// System.out.println("isFile "+status.isFile());
			// System.out.println("isDirectory "+status.isDirectory());
			// System.out.println("getAccessTime "+status.getAccessTime());
			// System.out.println("getModificationTime "+status.getModificationTime());
			// System.out.println("getLen "+status.getLen());
			// System.out.println("getBlockSize "+status.getBlockSize());
		}
	}

	public static void LsR(String name) throws IOException {
		Path dfs = new Path(name);
		if (!fs.isDirectory(dfs)) {
			throw new FileNotFoundException(name + "����Ŀ¼");
		} else {
			RemoteIterator<LocatedFileStatus> it = fs.listFiles(dfs, true);

			// while(it.hasNext()){
			// LocatedFileStatus lfs=(LocatedFileStatus) it.next();
			// System.out.println(lfs.getPath());
			// }
			do {
				LocatedFileStatus lfs = (LocatedFileStatus) it.next();
				System.out.println(lfs.getPath());
			} while (it.hasNext());
		}
	}

	public static void Ls1(String name) throws IOException {
		Path dfs = new Path(name);
		RemoteIterator<LocatedFileStatus> it = fs.listLocatedStatus(dfs);
		while (it.hasNext()) {
			LocatedFileStatus lfs = (LocatedFileStatus) it.next();
			System.out.println(lfs.getPath());
		}
	}

	public static void Ls2(String name) throws FileNotFoundException, IOException {
		Path dfs = new Path(name);
		FileStatus[] status = fs.listStatus(dfs);
		for (int i = 0; i < status.length; i++) {
			Path src = status[i].getPath();
			System.out.println(src);
		}
	}

	public static void Ls3(String name) throws FileNotFoundException, IOException {
		Path dfs = new Path(name);
		FileStatus[] status = fs.listStatus(dfs);
		for (int i = 0; i < status.length; i++) {
			System.out.println(status[i].getPath());
			if (fs.isDirectory(status[i].getPath())) {
				Hdfs.Ls3(status[i].getPath().toString());
			}
		}
	}

	public static void CopyFile(String src, String dst) throws IOException {
		Path srcP = new Path(src);
		Path dstP = new Path(dst);
		fs.copyFromLocalFile(srcP, dstP);
	}

	public static void DownloadFile(String src, String dst) throws IOException {
		Path srcP = new Path(src);
		Path dstP = new Path(dst);
		fs.copyToLocalFile(srcP, dstP);
	}

	public static void CreateFile(String name) throws IOException {
		Path dfs = new Path(name);
		fs.create(dfs); 
	}

	public static void upload(String src, String dst1, String dst2) throws IllegalArgumentException, IOException {
		InputStream in = fs.open(new Path(src));
		fs.create(new Path(dst1));
		OutputStream out2 = new FileOutputStream(dst2);
		// IOUtils.copyBytes(in, out1, 4096,false); 
		// IOUtils.copyBytes(in, System.out, 4096,false); 
		IOUtils.copyBytes(in, out2, 4096, false); 
		IOUtils.closeStream(in);
	}

	public static void append(String src, String dst) throws IllegalArgumentException, IOException {
		InputStream in = fs.open(new Path(src));
		OutputStream out = fs.append(new Path(dst));
		IOUtils.copyBytes(in, out, 4096, true);
	}

	public static void main(String[] args) throws IOException {
		// FileSystem localfs=FileSystem.get(conf).getLocal(conf);

	}
}
