package com.xuzhaocai.multi.config;

import com.alibaba.druid.pool.xa.DruidXADataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;


import javax.sql.DataSource;



@Configuration
@MapperScan(basePackages = {"com.xuzhaocai.multi.dao.driver"}, sqlSessionTemplateRef = "driverSqlSessionTemplate")
public class DriverDatasourceConfiguration {
    @Autowired
    private DriverDatasourceProperties driverDatasourceProperties;
    @Bean(name = "driverDatasource")
    public DataSource driverDruidDatasource() {
        DruidXADataSource datasource = new DruidXADataSource();
        BeanUtils.copyProperties(driverDatasourceProperties,datasource);
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(datasource);
        xaDataSource.setUniqueResourceName("driverDatasource");
        //return DataSourceBuilder.create().build();
        return xaDataSource;
    }

    //配置数据源
    @Bean(name = "driverSqlSessionFactory")
    public SqlSessionFactory driverSqlSessionFactory(@Qualifier("driverDatasource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
             //mapper.xml 的位置
        Resource[] resources1 = pathMatchingResourcePatternResolver.getResources("classpath*:com/xuzhaocai/multi/dao/driver/*.xml");
        factoryBean.setMapperLocations(resources1);
        factoryBean.setDataSource(dataSource);
        return factoryBean.getObject();
    }
    // 事务管理器
   /* @Bean(name = "driverTransactionManger")
    public DataSourceTransactionManager driverTransactionManger(@Qualifier("driverDatasource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }*/

    @Bean(name = "driverSqlSessionTemplate")
    public SqlSessionTemplate driverSqlSessionTemplate(@Qualifier("driverSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
   /* DruidXADataSource datasource = new DruidXADataSource();
        BeanUtils.copyProperties(driverDatasourceProperties,datasource);
    AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(datasource);
        xaDataSource.setUniqueResourceName("driverDatasource");*/


// 配置文件
//factoryBean.setConfigLocation(pathMatchingResourcePatternResolver.getResource("classpath:mybatis/mybatis-config.xml"));
