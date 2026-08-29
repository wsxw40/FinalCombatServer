package com.pearl.fcw.info.core.persistence.tools;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.pearl.fcw.info.core.persistence.BaseShardingEntity;
import com.pearl.fcw.info.core.persistence.config.ClassMetadata;
import com.pearl.fcw.info.core.persistence.config.ClassMetadata.FieldMetadata;
import com.pearl.fcw.info.core.persistence.config.TableIndex;
import com.pearl.fcw.info.lobby.utils.Constants;

public class MySQLHelperPlus {

    public long districtOffset = (long) Math.pow(10, Long.toString(Long.MAX_VALUE).length() - 3);   // 可合并大区：0 ~ 921
    public long dbOffset = (long) Math.pow(10, Long.toString(Long.MAX_VALUE).length() - 6);         // 单区DB数：0 ~ 999
    public int idLength = Long.toString(Long.MAX_VALUE).length() - 8;                               // ID长度

    private final String NEW_LINE = Constants.NEW_LINE;
    private final String TAB = "    ";

    private final String DB_CHARSET = "utf8";
    private final String DB_ENGINE = "InnoDB";
    private final String ZONE_ID = "{{ZONE_ID}}";
    private final String TABLE_GROUP = "{{TABLE_GROUP}}";
    private final String TABLE_NUMBER = "{{TABLE_NUMBER}}";
    private final String DB_NUMBER_ZERO = "000";
    private final String TABLE_NUMBER_ZERO = "00";

    private Map<Class<?>, String> typeMap = new HashMap<>();

    public MySQLHelperPlus() {
        typeMap = new HashMap<>();
        typeMap.put(boolean.class, "bit");
        typeMap.put(Boolean.class, "bit");
        typeMap.put(char.class, "char(1)");
        typeMap.put(Character.class, "char(1)");
        typeMap.put(byte.class, "tinyint");
        typeMap.put(Byte.class, "tinyint");
        typeMap.put(short.class, "smallint");
        typeMap.put(Short.class, "smallint");
        typeMap.put(int.class, "integer");
        typeMap.put(Integer.class, "integer");
        typeMap.put(long.class, "bigint");
        typeMap.put(Long.class, "bigint");
        typeMap.put(float.class, "float");
        typeMap.put(Float.class, "float");
        typeMap.put(java.sql.Date.class, "date");
        typeMap.put(java.util.Date.class, "datetime");
        typeMap.put(String.class, "varchar(255)");
        typeMap.put(byte[].class, "varbinary(1024)");
        typeMap.put(Byte[].class, "varbinary(1024)");
    }

    public String generateTableSql(ClassMetadata cm) {
        return generateTableSql(cm, "", false);
    }

    public String generateTableSql(ClassMetadata cm, String suffix) {
        return generateTableSql(cm, suffix, false);
    }

    public String generateTableSql(ClassMetadata cm, boolean origin) {
        return generateTableSql(cm, "", origin);
    }

    public String generateTableSql(ClassMetadata cm, String suffix, boolean origin) {

        StringBuffer sb = new StringBuffer();
        sb.append("CREATE TABLE IF NOT EXISTS ").append(cm.getTableName()).append(suffix).append(" (").append(NEW_LINE);

        // id column
        FieldMetadata id = cm.getPrimaryKey();
        sb.append(TAB).append(id.getColumnName()).append(" ").append(getMappedType(id)).append(" NOT NULL");
        if (id.isAutoIncreaseId()) {
            sb.append(" AUTO_INCREMENT");
        }

        // column
        FieldMetadata isDeleted = null;
        for (FieldMetadata fm : cm.getColumns().values()) {
            if (fm.isPrimaryKey()) {
                continue;
            }
            if (fm.isDeleteFlag()) {
                isDeleted = fm;
            }
            sb.append(", ").append(NEW_LINE);
            sb.append(TAB).append(fm.getColumnName()).append(" ").append(getMappedType(fm));
            if (!fm.isNullable()) {
                sb.append(" NOT NULL");
            }
        }

        // unique
        sb.append(",").append(NEW_LINE);
        if (cm.getUniqueFieldNames() != null && cm.getUniqueFieldNames().length > 0) {
            sb.append(TAB).append("UNIQUE (");
            for (int i = 0; i < cm.getUniqueFieldNames().length; i++) {
                if (i > 0) {
                    sb.append(", ");
                }
                String fieldName = cm.getUniqueFieldNames()[i];
                FieldMetadata uniqueFieldMeatedata = cm.getColumns().get(fieldName);
                if (uniqueFieldMeatedata == null) {
                    throw new RuntimeException("指定字段没有对应的数据库信息" + fieldName);
                }
                sb.append(uniqueFieldMeatedata.getColumnName());
            }
            sb.append("),").append(NEW_LINE);
        }

        // primary key
        sb.append(TAB).append("PRIMARY KEY (").append(id.getColumnName()).append(")");

        // foreign key
        for (FieldMetadata fm : cm.getForeignKeys().values()) {
            if (fm.isNoIndex()) {
                continue;
            }
            String fkName = fm.getColumnName() + "_FK";
            sb.append(",").append(NEW_LINE);
            sb.append(TAB).append("INDEX ").append(fkName).append(" (").append(fm.getColumnName());
            if (isDeleted != null) {
                sb.append(", ").append(isDeleted.getColumnName());
            }
            sb.append(")");

        }

        // index
        for (Entry<String, TableIndex> e : cm.getIndexes().entrySet()) {
            sb.append(",").append(NEW_LINE);
            sb.append(TAB).append("KEY ").append(e.getKey()).append(" (");
            for (FieldMetadata fm : e.getValue().getColumns()) {
                sb.append(fm.getColumnName()).append(", ");
            }
            sb.delete(sb.length() - 2, sb.length());
            if (e.getValue().getLength() != 0) {
                sb.append("(").append(e.getValue().getLength()).append(")");
            }
            sb.append(")");
        }
        sb.append(NEW_LINE);

        // other
        sb.append(") ENGINE=" + DB_ENGINE).append(" DEFAULT CHARSET=" + DB_CHARSET);
        if (id.isAutoIncreaseId() && !origin) {
            sb.append(" AUTO_INCREMENT=").append(ZONE_ID);
            if (BaseShardingEntity.class.isAssignableFrom(cm.getJavaClass())) {
                sb.append(TABLE_GROUP).append(TABLE_NUMBER);
            } else {
                sb.append(DB_NUMBER_ZERO).append(TABLE_NUMBER_ZERO);
            }
            String startId = String.format("%0" + idLength + "d", (long) 1);
            sb.append(startId);
        }
        if (cm.isPartitioning() && !origin) {
            sb.append(NEW_LINE).append("PARTITION BY KEY (").append(cm.getPartitionHashColumn()).append(")");
            sb.append(NEW_LINE).append("PARTITIONS ").append(cm.getPartitions());

        }
        sb.append(";");

        return sb.toString();

    }

    public String generateAlterCheck(ClassMetadata cm) {
        return generateAlterCheck(cm, "");
    }

    public String generateAlterCheck(ClassMetadata cm, String suffix) {
        return "SHOW TABLE STATUS LIKE '" + cm.getTableName() + suffix + "'";
    }

    public String generateTableAutoIncrement(ClassMetadata cm) {
        return generateTableAutoIncrement(cm, "", false);
    }

    public String generateTableAutoIncrement(ClassMetadata cm, String suffix) {
        return generateTableAutoIncrement(cm, suffix, false);
    }

    public String generateTableAutoIncrement(ClassMetadata cm, boolean origin) {
        return generateTableAutoIncrement(cm, "", origin);
    }

    public String generateTableAutoIncrement(ClassMetadata cm, String suffix, boolean origin) {

        StringBuffer sb = new StringBuffer();

        FieldMetadata id = cm.getPrimaryKey();
        if (id.isAutoIncreaseId() && !origin) {
            sb.append("ALTER TABLE ").append(cm.getTableName()).append(suffix).append(" AUTO_INCREMENT=").append(ZONE_ID);
            if (BaseShardingEntity.class.isAssignableFrom(cm.getJavaClass())) {
                sb.append(TABLE_GROUP).append(TABLE_NUMBER);
            } else {
                sb.append(DB_NUMBER_ZERO).append(TABLE_NUMBER_ZERO);
            }
            String startId = String.format("%0" + idLength + "d", (long) 1);
            sb.append(startId);
            sb.append(";");
        }

        return sb.toString();
    }

    public String replace(String sql, int serverId) {
        return sql.replace(ZONE_ID, String.format("%03d", serverId));
    }

    public String replace(String sql, int shardId, int tableNumber) {
        return sql.replace(ZONE_ID + TABLE_GROUP, String.format("%06d", shardId)).replace(TABLE_NUMBER, String.format("%02d", tableNumber));
    }

    private String getMappedType(FieldMetadata fm) {
        if (fm.getColumnDefinition() != null && fm.getColumnDefinition().length() > 0) {
            if (fm.getColumnLength() > 255) {
                return fm.getColumnDefinition() + "(" + fm.getColumnLength() + ")";
            }
            return fm.getColumnDefinition();
        }
        String result = typeMap.get(fm.getJavaField().getType());
        if (result == null) {
            throw new RuntimeException("不支持的数据类型：" + fm.getJavaField().getType());
        }
        return result;
    }

}
