package com.pearl.fcw.gm.pojo.columnDescriptor;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.fcw.core.pojo.columnDescriptor.ColumnDescriptor;
import com.pearl.fcw.gm.pojo.WSysCharacter;
import com.pearl.fcw.utils.StringUtil;

/**
 * SysCharacter表的特殊字段描述
 */
public class WSysCharacterColumnDescriptor extends ColumnDescriptor<WSysCharacter> {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public WSysCharacter get(WSysCharacter m) {
        if (null == m) {
            return m;
        }

        try {
            if (!StringUtil.isEmpty(m.getResourceP())) {
                m.getResourcePList().addAll(Stream.of(m.getResourceP().split(",")).collect(Collectors.toList()));
            }
        } catch (Exception e) {
            logger.error("WSysItemColumnDescriptor error : ", e);
        }
        return m;
    }

    @Override
    public void set(WSysCharacter m) {
    }

}
