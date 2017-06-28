package com.conf.mybatis;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Author:Mr.Cris
 * Date:2017-06-23 11:04
 */
@Configuration
public class MybatisConfiguration implements EnvironmentAware {

    public static final Logger logger = LoggerFactory.getLogger(MybatisConfiguration.class);

    private Environment environment;

    @Bean(name = "masterDataSource")
    public DataSource masterDataSource() throws Exception {
        Properties props = new Properties();
        props.put("driverClassName", environment.getProperty("druid.master.driver-class-name"));
        props.put("url", environment.getProperty("druid.master.url"));
        props.put("username", environment.getProperty("druid.master.username"));
        props.put("password", environment.getProperty("druid.master.password"));
        return DruidDataSourceFactory.createDataSource(props);
    }

    @Bean(name = "slaveDataSource")
    public DataSource slaveDataSource() throws Exception {
        Properties props = new Properties();
        props.put("driverClassName", environment.getProperty("druid.slave.driver-class-name"));
        props.put("url", environment.getProperty("druid.slave.url"));
        props.put("username", environment.getProperty("druid.slave.username"));
        props.put("password", environment.getProperty("druid.slave.password"));
        return DruidDataSourceFactory.createDataSource(props);
    }

    @Bean
    @Primary
    public DynamicDataSource dataSource(@Qualifier("masterDataSource") DataSource masterDataSource,
                                        @Qualifier("slaveDataSource") DataSource slaveDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(com.conf.datasource.DataSource.master, masterDataSource);
        targetDataSources.put(com.conf.datasource.DataSource.slave1, slaveDataSource);

        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSources);// 该方法是AbstractRoutingDataSource的方法
        dataSource.setDefaultTargetDataSource(masterDataSource);// 默认的datasource设置为myTestDbDataSource

        return dataSource;
    }


    public void setEnvironment(final Environment environment) {
        this.environment = environment;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DynamicDataSource ds) throws Exception {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(ds);// 指定数据源(这个必须有，否则报错)
        // 下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
        fb.setTypeAliasesPackage("com.entity");// 指定基包
        fb.setMapperLocations(resolver.getResources("classpath:mappers/**/*.xml"));//

        return fb.getObject();
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
        scannerConfigurer.setBasePackage("com.dao");
        return scannerConfigurer;
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DynamicDataSource dataSource) throws Exception {
        return new DataSourceTransactionManager(dataSource);
    }

}
