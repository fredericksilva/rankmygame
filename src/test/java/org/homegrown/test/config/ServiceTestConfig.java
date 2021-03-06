package org.homegrown.test.config;

import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.util.fileloader.XlsDataFileLoader;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/**
 * Created by JoLe on 05/05/15.
 */
@Configuration
@ImportResource({"classpath:datasource-tx-jpa.xml", "classpath:jms-listener-app-context.xml", "classpath:jms-sender-app-context.xml"})
@ComponentScan(basePackages = {"org.homegrown"})
public class ServiceTestConfig {

    @Bean
    public DataSource dataSource(){
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).addScript("classpath:schema.sql").build();
    }

    @Bean(name = "databaseTester")
    public DataSourceDatabaseTester dataSourceDatabaseTester(){
        DataSourceDatabaseTester databaseTester = new DataSourceDatabaseTester(dataSource());
        return databaseTester;
    }

    @Bean(name = "xlsDataFileLoader")
    public XlsDataFileLoader xlsDataFileLoader(){
        return new XlsDataFileLoader();
    }

}
