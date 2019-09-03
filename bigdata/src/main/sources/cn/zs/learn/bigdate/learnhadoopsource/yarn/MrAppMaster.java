package cn.zs.learn.bigdate.learnhadoopsource.yarn;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.service.CompositeService;
import org.apache.hadoop.yarn.event.AsyncDispatcher;
import org.apache.hadoop.yarn.event.Dispatcher;
import org.apache.hadoop.yarn.event.EventHandler;
import org.apache.hadoop.service.Service;

public class MrAppMaster extends CompositeService {
    private Dispatcher dispatcher;
    private String jobID;
    private int taskNumber;
    private String[] taskIDs;
    public MrAppMaster(String name, String jobID, int taskNumber) {
        super(name);
        this.jobID = jobID;
        this.taskNumber = taskNumber;
        taskIDs = new String[taskNumber];
        for (int i = 0; i < taskNumber; i++) {
            taskIDs[i] = new String(jobID + "_task_" + i);
        }
    }

    @Override
    protected void serviceInit(Configuration conf) throws Exception {
        dispatcher = new AsyncDispatcher();
        dispatcher.register(JobEventType.class, new JobEventDispacter());
        dispatcher.register(TaskEventType.class, new TaskEventDispacter());
        addService((Service)dispatcher);
        super.serviceInit(conf);
    }

    public Dispatcher getDispatcher() {
        return dispatcher;
    }

    private class JobEventDispacter implements EventHandler<JobEvent> {

        @Override
        public void handle(JobEvent event) {
            if (event.getType() == JobEventType.JOB_KILL) {
                System.out.println("receive job kill event,kill all the tasks");
                for (int i = 0; i < taskNumber; i++) {
                    dispatcher.getEventHandler().handle(new TaskEvent(taskIDs[i], TaskEventType.T_KILL));
                }
            } else if (event.getType() == JobEventType.JOB_KILL) {
                System.out.println("receive job init event,scheduling tasks");
                for (int i = 0; i < taskNumber; i++) {
                    dispatcher.getEventHandler().handle(new TaskEvent(taskIDs[i], TaskEventType.T_SCHEDULE));
                }
            }
        }
    }

    private class TaskEventDispacter implements EventHandler<TaskEvent> {
        @Override
        public void handle(TaskEvent event) {
            if (event.getType() == TaskEventType.T_KILL)
                System.out.println("receive Task kill event of tasks " + event.getTaskID());
            else if (event.getType() == TaskEventType.T_SCHEDULE)
                System.out.println("receive Task schedule event of task " + event.getTaskID());
        }
    }
}
