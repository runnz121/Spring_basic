package com.msystech.clone_hospital.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
@EnableScheduling//scehdule 기능 켜기
public class ScheduleConfig implements SchedulingConfigurer {
    private final int POOL_SIZE = 3;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegister) {
        ThreadPoolTaskScheduler taskScheduler = threadPoolTaskScheduler();
    }

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setThreadNamePrefix("schduler-task-pool-");//thread 이름 앞에 붙임
        taskScheduler.initialize();//executor service를 준비하겠다고 알림
        taskScheduler.setPoolSize(POOL_SIZE);//thread pool size 지정

        return taskScheduler;
    }
}
