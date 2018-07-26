package com.imooc.o2o.config.quartz;

import com.imooc.o2o.service.ProductSellDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @Author: REX
 * @Date: Create in 14:16 2018/7/26
 */
@Configuration
public class QuartzConfiguration {
	@Autowired
	private ProductSellDailyService productSellDailyService;
	@Autowired
	private MethodInvokingJobDetailFactoryBean jobDetailFactory;
	@Autowired
	private CronTriggerFactoryBean productSellDailyTriggerFactory;

	/**
	 * 创建JobDetail 并返回
	 * @return
	 */
	@Bean(name = "jobDetailFactory")
	public MethodInvokingJobDetailFactoryBean createJobDetail(){
		// 创建出JobDetailFactory对象，用于创建JobDetail
		MethodInvokingJobDetailFactoryBean jobDetailFactoryBean = new MethodInvokingJobDetailFactoryBean();
		jobDetailFactoryBean.setName("product_sell_daily_job");
		jobDetailFactoryBean.setGroup("job_product_sell_daily_group");
		// 对于相同的JobDetail，当指定多个Trigger时，执行多个job不会并发运行
		jobDetailFactoryBean.setConcurrent(false);
		jobDetailFactoryBean.setTargetObject(productSellDailyService);
		jobDetailFactoryBean.setTargetMethod("dailyCalculate");
		return jobDetailFactoryBean;
	}

	/**
	 * 创建CronTrigger
	 * @return
	 */
	@Bean(name = "productSellDailyTriggerFactory")
	public CronTriggerFactoryBean createProductSellDailyTrigger() {
		CronTriggerFactoryBean triggerFactory = new CronTriggerFactoryBean();
		triggerFactory.setName("product_sell_daily_trigger");
		triggerFactory.setGroup("job_product_sell_daily_group");
		triggerFactory.setJobDetail(jobDetailFactory.getObject());
		triggerFactory.setCronExpression("0 0 0 * * ? *");
		return triggerFactory;
	}

	/**
	 * 创建调度工厂
	 * @return
	 */
	@Bean(name = "schedulerFactory")
	public SchedulerFactoryBean createSchedulerFactory(){
		SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
		schedulerFactory.setTriggers(productSellDailyTriggerFactory.getObject());
		return schedulerFactory;
	}
}
