package com.pearl.fcw.gm.service;

import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Service;

import com.pearl.o2o.pojo.GmUpdateLog;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.LogUtils;
import com.pearl.o2o.utils.ServiceLocator;

/**
 * 日志
 */
@Service
public class WLogService {
    public void writeGmLog(GmUpdateLog gmUpdateLog) throws Exception {
        Map<String, Object[]> vals = CommonUtil.getDifferences(gmUpdateLog.getOlder(), gmUpdateLog.getNewer());
        if (!vals.isEmpty()) {
            StringBuilder diffs = new StringBuilder();
            Iterator<String> keyItt = vals.keySet().iterator();
            for (; keyItt.hasNext();) {
                String key = keyItt.next();
                Object[] arrs = vals.get(key);
                if (arrs.length == 2)
                    diffs.append(String.format("%s:%s->%s,", key, arrs[0], arrs[1]));
            }
            String msg = LogUtils.JoinerByTab.join(gmUpdateLog.getGm().getName(), gmUpdateLog.getOlder().getClass().getSimpleName(),
                    PropertyUtils.isReadable(gmUpdateLog.getOlder(), "id") ? PropertyUtils.getProperty(gmUpdateLog.getOlder(), "id") : "null", diffs.toString());
            //          infoLogger.log(LogServerMessage.gmUpdateLog.name(),Level.INFO_INT, msg);
            ServiceLocator.gmUpdateLog.info(msg);
            //          infoLogger.log(LogServerMessage.xunleiLog.name(),Level.INFO_INT, msg);
        }
    }
}
