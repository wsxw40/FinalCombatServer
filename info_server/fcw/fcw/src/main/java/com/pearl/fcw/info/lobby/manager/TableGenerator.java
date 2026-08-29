package com.pearl.fcw.info.lobby.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.pearl.fcw.info.core.persistence.config.ClassMetadata;
import com.pearl.fcw.info.core.persistence.config.ClassMetadataConfig;
import com.pearl.fcw.info.core.persistence.route.MultiSourceRouter;
import com.pearl.fcw.info.core.persistence.route.MultiSourceRouter.SqlExecutionCallback;
import com.pearl.fcw.info.core.persistence.route.RouteRule;
import com.pearl.fcw.info.core.persistence.route.SingleSourceRouter;
import com.pearl.fcw.info.core.persistence.tools.MySQLHelperPlus;

public class TableGenerator {

    private static final Logger logger = LoggerFactory.getLogger(TableGenerator.class);

    private int zoneId;

    private List<SingleSourceRouter> singleSourceRouterList = new ArrayList<>();
    private List<MultiSourceRouter> multiSourceRouterList = new ArrayList<>();

    public void setZoneId(int zoneId) {
        this.zoneId = zoneId;
    }

    public void setSingleSourceRouterList(List<SingleSourceRouter> singleSourceRouterList) {
        this.singleSourceRouterList = singleSourceRouterList;
    }

    public void setMultiSourceRouterList(List<MultiSourceRouter> multiSourceRouterList) {
        this.multiSourceRouterList = multiSourceRouterList;
    }

    public void generate() {

        logger.info("Creating tables if not exist...");

        final MySQLHelperPlus helper = new MySQLHelperPlus();

        for (SingleSourceRouter router : singleSourceRouterList) {
            List<ClassMetadata> cml = ClassMetadataConfig.getBySchema(router.getSchema());
            for (ClassMetadata cm : cml) {

                String createSql = helper.generateTableSql(cm);
                createSql = helper.replace(createSql, zoneId);
                router.execute(createSql);

//                System.out.println(createSql);

                String alterSql = helper.generateTableAutoIncrement(cm);
                alterSql = helper.replace(alterSql, zoneId);
                if (!"".equals(alterSql)) {
                    String checkSql = helper.generateAlterCheck(cm);
                    Map<String, Object> m = router.queryForMap(checkSql);
                    Object s = m.get("Auto_increment");
                    if ("1".equals(s) && zoneId != 0) {
                        router.execute(alterSql);
                        System.out.println(alterSql);
                    }
                }

            }
        }

        for (MultiSourceRouter router : multiSourceRouterList) {
            final RouteRule routeRule = router.getRouteRule();
            List<ClassMetadata> cml = ClassMetadataConfig.getBySchema(router.getSchema());
            for (final ClassMetadata cm : cml) {
                for (int i = 0; i < cm.getCountPerGroup(); i++) {
                    final int tableNum = i;
                    router.execute(new SqlExecutionCallback() {
                        @Override
                        public void create(int shardId, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {

                            String suffix = routeRule.getTableSuffix(shardId, tableNum);
                            String createSql = helper.generateTableSql(cm, suffix);
                            createSql = helper.replace(createSql, shardId, tableNum);
                            namedParameterJdbcTemplate.getJdbcOperations().execute(createSql);

//                            System.out.println(createSql);

                            long alterId = routeRule.getAutoIncrement(shardId, tableNum);
                            String checkSql = helper.generateAlterCheck(cm, suffix);
                            Map<String, Object> m = namedParameterJdbcTemplate.getJdbcOperations().queryForMap(checkSql);
                            Object s = m.get("Auto_increment");
                            if (Long.parseLong(String.valueOf(s)) < alterId) {
                                String alterSql = helper.generateTableAutoIncrement(cm, suffix);
                                alterSql = helper.replace(alterSql, shardId, tableNum);
                                namedParameterJdbcTemplate.getJdbcOperations().execute(alterSql);
                                System.out.println(alterSql);
                            }

                        }
                    });
                }
            }
        }

    }

}
