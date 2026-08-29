package com.pearl.fcw.info.core.persistence.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.pearl.fcw.info.core.persistence.BaseShardingEntity;
import com.pearl.fcw.info.core.persistence.config.ClassMetadata;
import com.pearl.fcw.info.core.persistence.config.ClassMetadata.FieldMetadata;
import com.pearl.fcw.info.core.persistence.config.TableIndex;
import com.pearl.fcw.info.lobby.utils.Constants;


public class MySQLHelper {

    public long districtOffset = (long) Math.pow(10, Long.toString(Long.MAX_VALUE).length() - 3);   // 可合并大区：0 ~ 921
    public long dbOffset = (long) Math.pow(10, Long.toString(Long.MAX_VALUE).length() - 6);         // 单区DB数：0 ~ 999
    public int idLength = Long.toString(Long.MAX_VALUE).length() - 6;                               // ID长度

    private final String OUTPUT_FILE_ENCODE = "UTF-8";

    private final String DB_PREFIX = "go_";
    private final String NEW_LINE = Constants.NEW_LINE;
    private final String TAB = "    ";

    private final String DB_CHARSET = "utf8";
    private final String DB_ENGINE = "InnoDB";
    private final String DB_NAME = "{{DB_NAME}}";
    private final String ZONE_ID = "{{ZONE_ID}}";
    private final String TABLE_GROUP = "{{TABLE_GROUP}}";
    private final String DB_NUMBER_ZERO = "000";

    private Collection<ClassMetadata> classMetadatas;

    private Map<Class<?>, String> typeMap = new HashMap<Class<?>, String>();
    private Map<String, List<String>> createSqlMap = new LinkedHashMap<>();
    private Map<String, List<String>> alterSqlMap = new LinkedHashMap<>();

    private MySQLHelper() {
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

    public MySQLHelper(Collection<ClassMetadata> classMetadatas) {
        this();
        this.classMetadatas = classMetadatas;
    }

    public void generateCreationScript(String outputFolder) throws IOException {

        clear();

        for (ClassMetadata cm : classMetadatas) {
            generateTableSql(cm, false);
            generateForeignKeySql(cm, false);
            generateIndexSql(cm);
        }

        // print
        for (Entry<String, List<String>> e : createSqlMap.entrySet()) {
            File file = new File(outputFolder + File.separatorChar + DB_PREFIX + e.getKey() + ".sql");
//            FileUtils.touch(file);
            Writer writer = new OutputStreamWriter(new FileOutputStream(file), OUTPUT_FILE_ENCODE);

            writer.write(NEW_LINE);
            writer.write("CREATE DATABASE IF NOT EXISTS " + DB_NAME + " DEFAULT CHARACTER SET " + DB_CHARSET + ";" + NEW_LINE);
            writer.write("USE " + DB_NAME + ";");
            writer.write(NEW_LINE);

            for (String str : e.getValue()) {
                writer.write(NEW_LINE + str + NEW_LINE);
            }

            List<String> alterSql = alterSqlMap.get(e.getKey());
            if (alterSql != null) {
                for (String str : alterSql) {
                    writer.write(NEW_LINE + str + NEW_LINE);
                }
            }

            writer.close();
        }

    }

    public void generateOriginCreationScript(String outputFolder) throws IOException {

        clear();

        for (ClassMetadata cm : classMetadatas) {
            generateTableSql(cm, true);
            generateForeignKeySql(cm, true);
            generateIndexSql(cm);
        }

        // print
        File file = new File(outputFolder + File.separatorChar + "origin.sql");
//        FileUtils.touch(file);
        Writer writer = new OutputStreamWriter(new FileOutputStream(file), OUTPUT_FILE_ENCODE);
        for (Entry<String, List<String>> e : createSqlMap.entrySet()) {

            for (String str : e.getValue()) {
                writer.write(NEW_LINE + str + NEW_LINE);
            }

            // List<String> alterSql = alterSqlMap.get(e.getKey());
            // if (alterSql != null) {
            // for (String str : alterSql) {
            // writer.write(NEW_LINE + str + NEW_LINE);
            // }
            // }

        }
        for (Entry<String, List<String>> e : createSqlMap.entrySet()) {

            // for (String str : e.getValue()) {
            // writer.write(NEW_LINE + str + NEW_LINE);
            // }

            List<String> alterSql = alterSqlMap.get(e.getKey());
            if (alterSql != null) {
                for (String str : alterSql) {
                    writer.write(NEW_LINE + str + NEW_LINE);
                }
            }

        }
        writer.close();

    }

    private void generateTableSql(ClassMetadata cm, boolean origin) {

        StringBuffer sb = new StringBuffer();
        sb.append("CREATE TABLE ").append(cm.getTableName()).append(" (").append(NEW_LINE);

        FieldMetadata id = cm.getPrimaryKey();
        sb.append(TAB).append(id.getColumnName()).append(" ").append(getMappedType(id)).append(" NOT NULL");
        if (id.isAutoIncreaseId()) {
            sb.append(" AUTO_INCREMENT");
        }

        for (FieldMetadata fm : cm.getColumns().values()) {
            if (fm.isPrimaryKey()) {
                continue;
            }
            sb.append(", ").append(NEW_LINE);
            sb.append(TAB).append(fm.getColumnName()).append(" ").append(getMappedType(fm));
            if (!fm.isNullable()) {
                sb.append(" NOT NULL");
            }
        }
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
        sb.append(TAB).append("PRIMARY KEY (").append(id.getColumnName()).append(")").append(NEW_LINE);
        sb.append(") ENGINE=" + DB_ENGINE).append(" DEFAULT CHARSET=" + DB_CHARSET);
        if (id.isAutoIncreaseId() && !origin) {
            sb.append(" AUTO_INCREMENT=").append(ZONE_ID);
            if (BaseShardingEntity.class.isAssignableFrom(cm.getJavaClass())) {
                sb.append(TABLE_GROUP);
            } else {
                sb.append(DB_NUMBER_ZERO);
            }
            String startId = String.format("%0" + idLength + "d", Long.valueOf(1));
            sb.append(startId);
        }
        if (cm.isPartitioning() && !origin) {
            sb.append(NEW_LINE).append("PARTITION BY KEY (").append(cm.getPartitionHashColumn()).append(")");
            sb.append(NEW_LINE).append("PARTITIONS ").append(cm.getPartitions());

        }
        sb.append(";");

        List<String> createSqlList = getOrPutNew(createSqlMap, cm.getSchema());
        createSqlList.add(sb.toString());

    }

    private void generateForeignKeySql(ClassMetadata cm, boolean origin) {

        if (!origin) {
            for (FieldMetadata fm : cm.getForeignKeys().values()) {
                StringBuffer sb = new StringBuffer();
                sb.append("CREATE INDEX ").append(fm.getColumnName() + "_FK").append(" ON ").append(cm.getTableName()).append(" (");
                sb.append(fm.getColumnName()).append(");");

                List<String> alterSqlList = getOrPutNew(alterSqlMap, cm.getSchema());
                alterSqlList.add(sb.toString());
            }
        } else {
            for (FieldMetadata fm : cm.getForeignKeys().values()) {
                String fkName = fm.getColumnName() + "_FK";
                ClassMetadata referencedClass = fm.getReferencedClass();
                StringBuffer sb = new StringBuffer();
                sb.append("ALTER TABLE ").append(cm.getTableName()).append(NEW_LINE);
                sb.append(TAB).append("ADD INDEX ").append(cm.getTableName() + "_" + fkName).append(" (").append(fm.getColumnName()).append("),").append(NEW_LINE);
                sb.append(TAB).append("ADD CONSTRAINT ").append(cm.getTableName() + "_" + fkName).append(NEW_LINE);
                sb.append(TAB).append("FOREIGN KEY (").append(fm.getColumnName()).append(")").append(NEW_LINE);
                sb.append(TAB).append("REFERENCES ").append(referencedClass.getTableName()).append(" (").append(referencedClass.getPrimaryKey().getColumnName()).append(");");

                List<String> alterSqlList = getOrPutNew(alterSqlMap, cm.getSchema());
                alterSqlList.add(sb.toString());
            }
        }

    }

    private void generateIndexSql(ClassMetadata cm) {

        for (Entry<String, TableIndex> e : cm.getIndexes().entrySet()) {
            StringBuffer sb = new StringBuffer();
            sb.append("CREATE INDEX ").append(e.getKey()).append(" ON ").append(cm.getTableName()).append(" (");
            for (FieldMetadata fm : e.getValue().getColumns()) {
                sb.append(fm.getColumnName()).append(", ");
            }
            sb.delete(sb.length() - 2, sb.length());
            if (e.getValue().getLength() != 0) {
                sb.append("(").append(e.getValue().getLength()).append(")");
            }
            sb.append(");");
            List<String> alterSqlList = getOrPutNew(alterSqlMap, cm.getSchema());
            alterSqlList.add(sb.toString());
        }

    }

    private void clear() {
        createSqlMap.clear();
        alterSqlMap.clear();
    }

    private List<String> getOrPutNew(Map<String, List<String>> map, String key) {
        List<String> list = map.get(key);
        if (list == null) {
            list = new ArrayList<>();
            map.put(key, list);
        }
        return list;
    }

    private String getMappedType(FieldMetadata fm) {
        if (fm.getColumnDefinition() != null && fm.getColumnDefinition().length() > 0) {
            return fm.getColumnDefinition();
        }
        String result = typeMap.get(fm.getJavaField().getType());
        if (result == null) {
            throw new RuntimeException("不支持的数据类型：" + fm.getJavaField().getType());
        }
        return result;
    }

    /**
     * 生成数据字典。
     *
     * @param outputFolder 输出路径
     */
    public void generateDataDictionary(String outputFolder) {
		@SuppressWarnings("resource")
		Workbook wb = new SXSSFWorkbook(100);
        Sheet sh = wb.createSheet();
        int i = 0;
        Row row;
        Cell cell;
        for (ClassMetadata cm : classMetadatas) {

            row = sh.createRow(i);
            cell = row.createCell(0);
            cell.setCellValue(cm.getTableName());
            i++;

            row = sh.createRow(i);
            cell = row.createCell(0);
            cell.setCellValue("Column");
            cell = row.createCell(1);
            cell.setCellValue("Type");
            cell = row.createCell(2);
            cell.setCellValue("Nullable");
            cell = row.createCell(3);
            cell.setCellValue("PK");
            i++;

            // pk
            FieldMetadata pk = cm.getPrimaryKey();
            if (pk != null) {
                row = sh.createRow(i);
                row.createCell(0).setCellValue(pk.getColumnName());
                row.createCell(1).setCellValue(getMappedType(pk));
                row.createCell(2).setCellValue(pk.isNullable() ? "√" : "");
                row.createCell(3).setCellValue(pk.isPrimaryKey() ? "√" : "");
                i++;
            }

            for (FieldMetadata fm : cm.getColumns().values()) {
                if (fm.isPrimaryKey()) {
                    continue;
                }
                row = sh.createRow(i);
                row.createCell(0).setCellValue(fm.getColumnName());
                row.createCell(1).setCellValue(getMappedType(fm));
                row.createCell(2).setCellValue(fm.isNullable() ? "√" : "");
                row.createCell(3).setCellValue(fm.isPrimaryKey() ? "√" : "");
                i++;
            }

            i++;
        }

        try (FileOutputStream fos = new FileOutputStream(outputFolder + File.separatorChar + "DataDictionary.xls")) {
            wb.write(fos);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
