package com.gu.dao.db;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.gu.core.jdbc.AbstractJDBC;

/**
 * gu从库连接服务 
 */
@Component
public class GuSlaveDbService extends AbstractJDBC {
	@Resource(name = "GuSlave")
	public void setDataSource(DataSource dataSource) {
		jdbc = new JdbcTemplate(dataSource);
		check();
	}
}