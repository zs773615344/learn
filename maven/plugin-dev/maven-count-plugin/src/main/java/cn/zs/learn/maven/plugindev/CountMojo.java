package cn.zs.learn.maven.plugindev;

import org.apache.maven.model.Resource;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Mojo(name = "Count", defaultPhase = LifecyclePhase.GENERATE_SOURCES)
public class CountMojo extends AbstractMojo {

    private static final String[] INCLUDES_DEFAULT = {"java", "xml", "properties"};

    @Parameter(defaultValue = "${project.basedir}", required = true, readonly = true)
    private File basedir;

    @Parameter(defaultValue = "${project.build.sourceDirectory}", required = true, readonly = true)
    private File sourceDirectory;

    @Parameter(defaultValue = "${project.build.testSourceDirectory}", required = true, readonly = true)
    private File testSourceDirectory;

    @Parameter(defaultValue = "${project.build.resources}", required = true, readonly = true)
    private List<Resource> resources;

    @Parameter(defaultValue = "${project.build.testResources}" , required = true, readonly = true)
    private List<Resource> testResources;

    @Parameter
    private String[] includes;

    public void execute() throws MojoFailureException {
        if (includes == null || includes.length == 0) {
          includes = INCLUDES_DEFAULT;
        }
        try {
            countDir(sourceDirectory);
            countDir(testSourceDirectory);
            for (Resource resource : resources) {
                countDir(new File(resource.getDirectory()));
            }
            for (Resource resource : testResources) {
                countDir(new File(resource.getDirectory()));
            }
        } catch (IOException e) {
            throw new MojoFailureException("Unable to count lines if code.", e);
        }
    }

    public void countDir(File dir) throws IOException {
        if (!dir.exists()) {
            return;
        }
        List<File> collected = new ArrayList<File>();
        collecteFiles(collected,dir);
        int lines = 0;
        for (File sourceFile : collected) {
            lines += countLine(sourceFile);
        }
        String path = dir.getAbsolutePath().substring(basedir.getAbsolutePath().length());
        getLog().info(path + ":" + lines + " lines of code in " + collected.size()
                + " files");
    }

    public void collecteFiles(List<File> collected,File file) {
        if (file.isFile()) {
            for (String include : includes) {
                if (file.getName().endsWith("."+include)) {
                    collected.add(file);
                    break;
                }
            }
        } else {
            for (File sub : file.listFiles()) {
                collecteFiles(collected,sub);
            }
        }
    }

    private int countLine(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        int line = 0;
        try {
            while (reader.ready()) {
                reader.readLine();
                line ++;
            }
        } finally {
            reader.close();
        }
        return  line;
    }
}
