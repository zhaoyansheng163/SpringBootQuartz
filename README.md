# SpringBootQuartz

- Quartz with Spring Boot
- 相关文章介绍：<http://blog.csdn.net/u012907049/article/details/73801122>
- 启动项目后访问：<http://127.0.0.1:8080/JobManager.html>
- 添加的时候：
    - 任务名称填写 Job 类完整包路径 + 类名，比如：com.example.demo.job.NewJob
    - 任务分组名称自己任意填写字符串。其中：**分组名+任务名称组成一个完整的唯一任务**。如果两者一样已经存在一样的，则无法添加成功，会抛异常：because one already exists with this identification
    - 表达式填写Cron表达式，比如：`0/3 * * * * ?`，在线表达：<http://cron.qqe2.com/>

1、增加通过单元测试添加任务
2、修复页面因为引用的vue.js失效 导致页面打不开的问题
3、集群方式启动
如何启动：调整端口及org.quartz.scheduler.instanceId
启动两个实例
如何观察：
a、观察日志，任意关闭实例，另一个实例可以看到一下日志
2023-05-28 20:51:03.957  INFO 14284 --- [           main] com.example.demo.DemoApplication         : Started DemoApplication in 5.844 seconds (JVM running for 7.309)
2023-05-28 20:51:32.358  INFO 14284 --- [_ClusterManager] org.quartz.impl.jdbcjobstore.JobStoreTX  : ClusterManager: detected 1 failed or restarted instances.
2023-05-28 20:51:32.358  INFO 14284 --- [_ClusterManager] org.quartz.impl.jdbcjobstore.JobStoreTX  : ClusterManager: Scanning for instance "124"'s failed in-progress jobs.
2023-05-28 20:51:33.902  INFO 14284 --- [_ClusterManager] org.quartz.impl.jdbcjobstore.JobStoreTX  : ClusterManager: detected 1 failed or restarted instances.
2023-05-28 20:51:33.902  INFO 14284 --- [_ClusterManager] org.quartz.impl.jdbcjobstore.JobStoreTX  : ClusterManager: Scanning for instance "124"'s failed in-progress jobs.
2023-05-28 20:52:00.154  INFO 14284 --- [actory_Worker-1] com.example.demo.job.HelloJob            : Hello Job执行时间: Sun May 28 20:52:00 CST 2023
b、观察DB
查看这个表 qrtz_scheduler_state
可以看到每个实例
