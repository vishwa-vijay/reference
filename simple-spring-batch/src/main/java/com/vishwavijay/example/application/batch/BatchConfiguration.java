package com.vishwavijay.example.application.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public @Configuration class BatchConfiguration extends DefaultBatchConfigurer {
	
    private @Autowired JobBuilderFactory jobBuilderFactory;
    private @Autowired StepBuilderFactory stepBuilderFactory;
    
    public @Bean ResourcelessTransactionManager resoucelessTransactionManager() {
        return new ResourcelessTransactionManager();
    }

    public @Override JobRepository createJobRepository() throws Exception {
        MapJobRepositoryFactoryBean factoryBean = new MapJobRepositoryFactoryBean();
        factoryBean.setTransactionManager(resoucelessTransactionManager());
        return factoryBean.getObject();
    }

	public @Bean ItemReader<String> itemReader() {
		return new NameReader();
	}

	public @Bean ItemProcessor<String, String> itemProcessor() {
		return new NameProcessor();
	}
	
	public @Bean ItemWriter<String> itemWriter() {
		return new NameWriter();
	}
	
	public @Bean Job importUserJob(Step step1) {
	  return jobBuilderFactory.get("importUserJob")
	    .incrementer(new RunIdIncrementer())
	    .flow(step1)
	    .end()
	    .build();
	}

	public @Bean Step step1(ItemReader<String> reader, ItemProcessor<String, String> processor, ItemWriter<String> writer) {
	  return stepBuilderFactory.get("step1")
	    .<String, String> chunk(10)
	    .reader(reader)
	    .processor(processor)
	    .writer(writer)
	    .build();
	}

}
