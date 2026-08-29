package com.pearl.fcw.info.lobby.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.springframework.stereotype.Service;

import com.pearl.fcw.info.core.persistence.route.DataSourceInfo;
import com.pearl.fcw.info.core.persistence.route.MultiSourceFactory;
import com.pearl.fcw.info.lobby.dao.AsShardDao;
import com.pearl.fcw.info.lobby.pojo.AsShard;

@Service
public class AsShardService implements MultiSourceFactory {

    private final String DB_HOST = "{{db.host}}";
    private final String DB_SCHEMA = "{{db.schema}}";

    @Resource
    private AsShardDao asShardDao;
    private Map<String, String> dataSourceProperties = new HashMap<String, String>();

    public void setDataSourceProperties(Map<String, String> dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
    }

    @Override
    public List<DataSourceInfo> getDataSourceInfos() {

        String originUrl = dataSourceProperties.get("url");
        if (originUrl == null || originUrl.length() == 0) {
            throw new IllegalArgumentException("Empty database url");
        }

        List<AsShard> list = asShardDao.getAllList();

        Map<String, DataSourceInfo> map = new HashMap<>();
        for (AsShard shard : list) {

            String url = originUrl.replace(DB_HOST, shard.getDbHost()).replace(DB_SCHEMA, shard.getDbName());
            DataSourceInfo info = map.get(url);
            if (info == null) {
                Properties properties = new Properties();
                properties.putAll(dataSourceProperties);
                properties.put("url", url);
                properties.put("username", shard.getDbUsername());
                properties.put("password", shard.getDbPassword());
                DataSource ds;
                try {
                    ds = BasicDataSourceFactory.createDataSource(properties);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                info = new DataSourceInfo(ds);
                map.put(url, info);
            }

            for (int i = shard.getStartNodeId(); i <= shard.getEndNodeId(); i++) {
                int shardId = shard.getZoneId() * 1000 + i; // TODO 放入路由规则
                info.addShardId(shardId);
            }
        }

        return new ArrayList<>(map.values());

    }
}
