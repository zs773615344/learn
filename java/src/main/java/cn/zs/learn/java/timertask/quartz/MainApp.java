package cn.zs.learn.java.timertask.quartz;

import java.util.Date;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class MainApp {
    	public static void main(String[] args) throws SchedulerException, InterruptedException {
	    
    	    Scheduler scheduler = new StdSchedulerFactory().getScheduler();
	    	
    	    JobDetail job = JobBuilder.newJob(TestJob.class)
    	            .withIdentity("job1","group1").build();
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1","group1")
//	    	          .startNow()
	    	          .startAt(new Date(System.currentTimeMillis()+3000))
	    	          .withSchedule(SimpleScheduleBuilder.simpleSchedule()
	    	                  .withIntervalInSeconds(1).repeatForever())
	    	          .build();
	    	  scheduler.scheduleJob(job,trigger);
	    	  scheduler.start();
	    	
	    	  Thread.sleep(50000);
	    	
	    	  scheduler.shutdown(true);
	}
}
