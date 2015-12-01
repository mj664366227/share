package com.gu.dao.db;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.gu.core.jdbc.AbstractJDBC;

/**
 * gu主库连接服务 
 */
@Component
public class GuMasterDbService extends AbstractJDBC {
	@Resource(name = "GuMaster")
	public void setDataSource(DataSource dataSource) {
		jdbc = new JdbcTemplate(dataSource);
		check();
	}
}