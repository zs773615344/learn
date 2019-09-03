package cn.zs.learn.bigdate.learnhadoopsource.yarn;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.yarn.conf.YarnConfiguration;

public class MrAppMasterTest {
    public static void main(String[] args) throws Exception{
        String jobId = "job_20190610";
        MrAppMaster mrAppMaster = new MrAppMaster("diy", jobId, 5);
        YarnConfiguration yarnConfiguration = new YarnConfiguration(new Configuration());
        mrAppMaster.serviceInit(yarnConfiguration);
        System.out.println(mrAppMaster.getServiceState());
        mrAppMaster.start();
        mrAppMaster.getDispatcher().getEventHandler().handle(
                new JobEvent(jobId,JobEventType.JOB_KILL));
        mrAppMaster.getDispatcher().getEventHandler().handle(
                new JobEvent(jobId,JobEventType.JOB_INIT));
    }
}
