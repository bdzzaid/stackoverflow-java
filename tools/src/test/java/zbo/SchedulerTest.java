package zbo;

import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.concurrent.TimeUnit;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class SchedulerTest
{
    private SimpleScheduleBuilder getScheduler()
    {
        return SimpleScheduleBuilder.repeatMinutelyForTotalCount(60 * 8).withRepeatCount(100);
    }

    // https://stackoverflow.com/questions/64460502/quartz-trigger-reset-after-every-hour
    @Test
    public void testSimpleScheduler() throws Exception
    {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        Trigger trigger = newTrigger()
                .withIdentity("trigger1", "group1")
                .withSchedule(cronSchedule("0,4/8 * * * * ?"))
                .build();
        JobDetail job = newJob(SimpleJob.class)
                .withIdentity("job1", "group1")
                .build();
        // and start it off
        scheduler.start();
        scheduler.scheduleJob(job, trigger);
        TimeUnit.MINUTES.sleep(5);
        scheduler.shutdown();
    }
}
