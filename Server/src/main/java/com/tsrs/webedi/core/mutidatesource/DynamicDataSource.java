package com.tsrs.webedi.core.mutidatesource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 
 * 动态数据源
 *
 * @author Tsrs-WebEDI
 * @date 2017年3月5日 上午9:11:49
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return DataSourceContextHolder.getDataSourceType();
	}

}
