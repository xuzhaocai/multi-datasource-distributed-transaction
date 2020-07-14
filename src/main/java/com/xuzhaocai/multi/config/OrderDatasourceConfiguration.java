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
@MapperScan(basePackages = {"com.xuzhaocai.multi.dao.order"}, sqlSessionTemplateRef = "orderSqlSessionTemplate")
public class OrderDatasourceConfiguration {

    @Autowired
    private  OrderDatasourceProperties orderDatasourceProperties;
    @Bean(name = "orderDatasource")
    @Primary
    public DataSource orderDatasource() {
        DruidXADataSource datasource = new DruidXADataSource();
        BeanUtils.copyProperties(orderDatasourceProperties,datasource);
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(datasource);
        xaDataSource.setUniqueResourceName("orderDatasource");
        return xaDataSource;
    }
    //配置数据源
    @Bean(name = "orderSqlSessionFactory")
    @Primary
    public SqlSessionFactory orderSqlSessionFactory(@Qualifier("orderDatasource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
        Resource[] resources1 = pathMatchingResourcePatternResolver.getResources("classpath*:com/xuzhaocai/multi/dao/order/*.xml");
        factoryBean.setMapperLocations(resources1);
        factoryBean.setDataSource(dataSource);
        return factoryBean.getObject();

    }
   /* // 事务管理器
    @Bean(name = "orderTransactionManger")
    @Primary
    public DataSourceTransactionManager orderTransactionManger(@Qualifier("orderDatasource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }*/

    @Bean(name = "orderSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate orderSqlSessionTemplate(@Qualifier("orderSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
  /*  DruidXADataSource datasource = new DruidXADataSource();
        BeanUtils.copyProperties(datasourceProperties,datasource);
                AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
                xaDataSource.setXaDataSource(datasource);
                xaDataSource.setUniqueResourceName("orderDatasource");*/