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
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;


import javax.sql.DataSource;

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
                .<CartoonDTO, Cartoon>chunk(5)
                .reader(employeeReader())
                .processor(cartoonProcessor)
                .writer(cartoonDBWriter())
                .build();
    }

    @Bean
    @StepScope
    Resource inputFileResource(@Value("#{jobParameters[fileName]}") final String fileName) throws Exception {
        return new ClassPathResource(fileName);
    }

    @Bean
    @StepScope
    public ItemReader<CartoonDTO> employeeReader() throws Exception {
//         FlatFileItemReader<CartoonDTO> reader = new FlatFileItemReader<>();
//         reader.setResource(inputFileResource(null));
//         reader.setLineMapper(new DefaultLineMapper<CartoonDTO>() {{
//             setLineTokenizer(new DelimitedLineTokenizer() {{
//                 setNames("id","showName","channel");
//                 setDelimiter(",");
//             }});
//             setFieldSetMapper(new CartoonFileRowMapper());
//         }});
        ItemReader<CartoonDTO> reader=null;
        return reader;
    }

    @Bean
    public ItemWriter<Cartoon> cartoonDBWriter() {
//         JdbcBatchItemWriter<Cartoon> itemWriter = new JdbcBatchItemWriter<>();
//         itemWriter.setDataSource(dataSource);
//         itemWriter.setSql("insert into Cartoon values (:id, :showName, :channel)");
//         itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        ItemWriter<Cartoon> writer=null;
        return itemWriter;
    }
}
