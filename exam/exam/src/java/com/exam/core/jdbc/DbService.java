package com.exam.core.jdbc;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * gu主库连接服务 
 */
@Component
public class DbService extends AbstractJDBC {
	@Resource(name = "db")
	public void setDataSource(DataSource dataSource) {
		jdbc = new JdbcTemplate(dataSource);
		check();
	}
}