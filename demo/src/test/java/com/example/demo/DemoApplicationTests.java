package com.example.demo;

import com.example.demo.job.HelloJob;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
	@Autowired
	@Qualifier("Scheduler")
	Scheduler scheduler;
	@Test
	public void contextLoads() throws SchedulerException {
		JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withDescription("with HelloJob in HelloJob job init....").withIdentity("HelloJob", "groupA").build();
		CronTrigger cronTrigger = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule("0 0/2 * * * ?")).build();
		scheduler.scheduleJob(jobDetail,cronTrigger);
		scheduler.start();
	}

}
