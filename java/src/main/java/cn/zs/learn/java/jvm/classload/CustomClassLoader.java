package cn.zs.learn.java.jvm.classload;

public class CustomClassLoader {
}

class PathClassLoader extends ClassLoader {
    private String classPath;
    private String packageName;

    public PathClassLoader(String classPath) {
        this.setClassPath(classPath);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if (packageName.startsWith(name)) {

        }
        return null;
    }

	public String getClassPath() {
		return classPath;
	}

	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}
}