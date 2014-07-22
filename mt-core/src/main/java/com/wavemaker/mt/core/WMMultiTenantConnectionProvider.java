package com.wavemaker.mt.core;

import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.service.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;


/**
 * @author gauravs
 *
 */
@SuppressWarnings("serial")
public class WMMultiTenantConnectionProvider extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {

    private Map<String, DataSource> dataSourceMap;

    @Override
    protected DataSource selectAnyDataSource() {
        return (DataSource) dataSourceMap.values().toArray()[0];
    }

    @Override
    protected DataSource selectDataSource(String tenantIdentifier) {
        return dataSourceMap.get(tenantIdentifier);
    }

    public Map<String, DataSource> getDataSourceMap() {
        return dataSourceMap;
    }

    public void setDataSourceMap(Map<String, DataSource> dataSourceMap) {
        this.dataSourceMap = dataSourceMap;
    }
}
