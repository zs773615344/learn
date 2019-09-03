package cn.zs.learn.bigdate.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.mapred.ClusterStatus;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class ClusterStatusQuery extends Configured implements Tool {
    public static final String REDUCES_PER_HOST =
            "mapreduce.sort.reducesperhost";
    static {
        Configuration.addDefaultResource("hadoop/hdfs-site.xml");
        Configuration.addDefaultResource("hadoop/core-site.xml");
        Configuration.addDefaultResource("hadoop/yarn-site.xml");
        Configuration.addDefaultResource("hadoop/mapred-site.xml");
    }
    @Override
    public int run(String[] strings) throws Exception {
        Configuration conf = getConf();
        System.out.println(conf.get("mapreduce.framework.name"));
        System.out.println(conf.get("yarn.resourcemanager.admin.address.rm2"));
        String sort_reduces = conf.get(REDUCES_PER_HOST);
        System.out.println(sort_reduces);
        JobClient client = new JobClient(conf);
        ClusterStatus cluster = client.getClusterStatus();
        int num_reduces = (int) (cluster.getMaxReduceTasks() * 0.9);
        return 0;
    }

    public static void main(String[] args) throws Exception {
        int run = ToolRunner.run(new Configuration(), new ClusterStatusQuery(), args);
        System.exit(run);
    }
}
