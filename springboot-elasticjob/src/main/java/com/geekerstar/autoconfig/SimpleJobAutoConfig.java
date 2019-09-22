package com.geekerstar.autoconfig;

import com.dangdang.ddframe.job.api.ElasticJob;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.event.JobEventConfiguration;
import com.dangdang.ddframe.job.event.rdb.JobEventRdbConfiguration;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.geekerstar.springbootelasticjob.listener.MyDistributeListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@Configuration
@ConditionalOnBean(CoordinatorRegistryCenter.class)
@AutoConfigureAfter(ZookeeperAutoConfig.class)
public class SimpleJobAutoConfig {

    @Autowired
    private CoordinatorRegistryCenter zkCenter;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void initSimpleJob() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(ElasticSimpleJob.class);
        for (Map.Entry<String, Object> entry : beans.entrySet()){
            Object instance = entry.getValue();
            Class<?>[] interfaces = instance.getClass().getInterfaces();
            for (Class<?> superInterface : interfaces){
                if (superInterface == SimpleJob.class){
                    ElasticSimpleJob annotation = instance.getClass().getAnnotation(ElasticSimpleJob.class);
                    String jobName = annotation.jobName();
                    String cron = annotation.cron();
                    int shardingTotalCount = annotation.shardingTotalCount();
                    boolean overwrite = annotation.overwrite();
                    Class<?> jobStrategy = annotation.jobStrategy();
                    boolean isJobEvent = annotation.jobEvent();
                    Class<? extends ElasticJobListener>[] listeners = annotation.jobListner();
                    ElasticJobListener[] listenerInstances = null;
                    if (listeners!=null && listeners.length>0){
                        listenerInstances = new ElasticJobListener[listeners.length];
                        int i = 0;
                        for (Class<? extends ElasticJobListener> listener : listeners){
                            ElasticJobListener listenerInstance = listener.getDeclaredConstructor().newInstance();
                            listenerInstances[i] = listenerInstance;
                            i++;
                        }
                    }else {
                        listenerInstances = new ElasticJobListener[0];
                    }


                    //job核心配置
                    JobCoreConfiguration jcc = JobCoreConfiguration
                            .newBuilder(jobName,cron,shardingTotalCount)
                            .build();
                    //job类型配置
                    SimpleJobConfiguration jtc = new SimpleJobConfiguration(jcc,
                            instance.getClass().getCanonicalName());

                    //job根的配置（LiteJobConfiguration）
                    LiteJobConfiguration ljc = LiteJobConfiguration
                            .newBuilder(jtc)
                            .jobShardingStrategyClass(jobStrategy.getCanonicalName())
                            .overwrite(overwrite)
                            .build();

//                    new JobScheduler(zkCenter,ljc).init();
                    if (isJobEvent){
                        JobEventConfiguration jec = new JobEventRdbConfiguration(dataSource);
                        new SpringJobScheduler((ElasticJob) instance,zkCenter,ljc,jec,listenerInstances).init();
                    }else {
                        new SpringJobScheduler((ElasticJob) instance,zkCenter,ljc,listenerInstances).init();
                    }
                }
            }
        }
    }

}
