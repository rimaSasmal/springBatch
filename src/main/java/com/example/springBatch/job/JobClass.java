package com.example.springBatch.job;

import com.example.springBatch.processor.CartoonProcessor;
import com.example.springBatch.repo.Cartoon;
import com.example.springBatch.repo.CartoonDTO;
import com.example.springBatch.repo.CartoonFileRowMapper;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;


import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.Writer;

@Configuration
public class JobClass {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private CartoonProcessor cartoonProcessor;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private EntityManager em;


    @Qualifier(value = "job1")
    @Bean
    public Job demo1Job() throws Exception {
        return this.jobBuilderFactory.get("job1")
                .start(getStep1())
                .build();
    }

    @Bean
    public Step getStep1() throws Exception {
        return this.stepBuilderFactory.get("step1")
                .<Cartoon, CartoonDTO>chunk(5)
                .reader(dbReader())
                .processor(cartoonProcessor)
                .writer(dbWriter())
                .build();
    }

    @Bean
    @StepScope
    Resource inputFileResource(@Value("#{jobParameters[fileName]}") final String fileName) throws Exception {
        Resource outputResource=new FileSystemResource(fileName);
        return outputResource;
    }

    @Bean
    @StepScope
    public JpaPagingItemReader<Cartoon> dbReader() throws Exception {
        JpaPagingItemReader<Cartoon> reader=new JpaPagingItemReader();
        reader.setEntityManagerFactory(em.getEntityManagerFactory());
        reader.setQueryString("select c from Cartoon c");
        return reader;
    }

    @Bean
    public FlatFileItemWriter<CartoonDTO> dbWriter() throws Exception {
        FlatFileItemWriter<CartoonDTO> writer=new FlatFileItemWriter();
        writer.setResource(inputFileResource(null));

        DelimitedLineAggregator<CartoonDTO> lineAggregator=new DelimitedLineAggregator<>();
        BeanWrapperFieldExtractor<CartoonDTO> fieldExtractor=new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(new String[]{"id","showName","channel"});
        String header="id,show name,channel";
        writer.setHeaderCallback(new FlatFileHeaderCallback() {
            @Override
            public void writeHeader(Writer writer) throws IOException {
                writer.write(header);
            }
        });

        writer.setLineAggregator(lineAggregator);
        return writer;
    }
}
