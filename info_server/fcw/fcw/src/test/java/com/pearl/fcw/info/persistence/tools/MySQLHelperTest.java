package com.pearl.fcw.info.persistence.tools;

import java.io.File;
import java.util.Collection;

import org.junit.Test;

import com.pearl.fcw.info.core.persistence.config.ClassMetadata;
import com.pearl.fcw.info.core.persistence.config.ClassMetadataScanner;
import com.pearl.fcw.info.core.persistence.tools.MySQLHelper;
import com.pearl.fcw.info.core.persistence.tools.MySQLHelperPlus;

public class MySQLHelperTest {

    public static final String[] ENTITY_PACKAGES = new String[] { "com.pearl.fcw.info.lobby.pojo", "com.pearl.fcw.info.gm.pojo" };

    @Test
    public void normal() throws Exception {

        ClassMetadataScanner scanner = new ClassMetadataScanner();
        scanner.addPojoPackageNames(ENTITY_PACKAGES);
        Collection<ClassMetadata> cml = scanner.parse().values();

        MySQLHelper helper = new MySQLHelper(cml);
        String path = new File("target").getAbsolutePath() + File.separatorChar + "sql";
        helper.generateCreationScript(path);
        helper.generateOriginCreationScript(path);
        helper.generateDataDictionary(path);

        MySQLHelperPlus h = new MySQLHelperPlus();
        for (ClassMetadata cm : cml) {
            String sql = h.generateTableSql(cm);
            System.out.println(sql);
        }

    }

}
