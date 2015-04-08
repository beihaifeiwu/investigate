package com.investigate.spring.jersey1;

import com.mysema.query.sql.MySQLTemplates;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.SQLTemplates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.ConnectionHolder;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by LiuPin on 2015/4/7.
 */
@Component
public class QueryDSL {

    private static final Logger LOG = LoggerFactory.getLogger(QueryDSL.class);

    @Autowired
    DataSource dataSource;

    ConnectionHolder connectionHolder;

    public SQLQuery query() {
        Connection connection = DataSourceUtils.getConnection(dataSource);
        connectionHolder = new ConnectionHolder(connection);
        SQLTemplates templates = new MySQLTemplates();
        return new SQLQuery(connection,templates);
    }

    public void release(){
        if(connectionHolder == null) return;
        Connection connection = connectionHolder.getConnection();
        try {
            DataSourceUtils.doReleaseConnection(connection, dataSource);
        } catch (SQLException e) {
            LOG.warn("cannot release connection",e);
        }
    }
}
