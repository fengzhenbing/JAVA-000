package org.fzb.multDatasource.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


/**
 * @Description
 * @Author fzb
 * @date 2020.09.23 16:03
 */
@Configuration
@EnableConfigurationProperties(MybatisProperties.class)
@Slf4j
public class DynamicDataSourceConfig{

    private final MybatisProperties properties;

    public static  ThreadLocal<String> datasourceKeyHolder = new ThreadLocal<String>();

    public DynamicDataSourceConfig(MybatisProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.master")
    DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    DataSource slaveDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    DataSource dynamicDataSource(DataSource masterDataSource,DataSource slaveDataSource) {
        AbstractRoutingDataSource dynamicDataSource = new AbstractRoutingDataSource() {
            @Override
            protected Object determineCurrentLookupKey() {
                String key = datasourceKeyHolder.get();
                if(StringUtils.isEmpty(key)) {
                    key =  Const.DATASOURCE_MASTER;
                }
                log.debug("当前使用数据源->{}",key);
                return key;
            }
        };
        Map<String,DataSource> targetDataSources = new HashMap<>();
        targetDataSources.put(Const.DATASOURCE_MASTER,masterDataSource);
        targetDataSources.put(Const.DATASOURCE_SLAVE,slaveDataSource);
        dynamicDataSource.setTargetDataSources(Collections.unmodifiableMap(targetDataSources));



        return  dynamicDataSource;
    }

     @Bean
    public DataSourceTransactionManager transactionManager(
             DataSource dynamicDataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(
                dynamicDataSource);
        return transactionManager;
    }



    @Bean
    @ConfigurationProperties(prefix = "mybatis")
    public SqlSessionFactory SqlSessionFactoryBean(@Qualifier("dynamicDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        factory.setVfs(SpringBootVFS.class);

        if (StringUtils.hasLength(this.properties.getTypeAliasesPackage())) {
            factory.setTypeAliasesPackage(this.properties.getTypeAliasesPackage());
        }
        if (this.properties.getTypeAliasesSuperType() != null) {
            factory.setTypeAliasesSuperType(this.properties.getTypeAliasesSuperType());
        }
        if (StringUtils.hasLength(this.properties.getTypeHandlersPackage())) {
            factory.setTypeHandlersPackage(this.properties.getTypeHandlersPackage());
        }

        if (!ObjectUtils.isEmpty(this.properties.resolveMapperLocations())) {
            factory.setMapperLocations(this.properties.resolveMapperLocations());
        }

        return factory.getObject();
    }
}
