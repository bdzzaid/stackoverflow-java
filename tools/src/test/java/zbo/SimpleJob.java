package zbo;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalDateTime;

public class SimpleJob implements Job
{
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException
    {
        System.out.println("Hello " + LocalDateTime.now().toString());
    }
}
