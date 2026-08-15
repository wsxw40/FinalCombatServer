package com.pearl.fcw.gm.pojo.columnDescriptor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.pearl.fcw.core.pojo.columnDescriptor.ColumnDescriptor;
import com.pearl.fcw.gm.pojo.WPayment;
import com.pearl.fcw.gm.pojo.WSysAchievement;
import com.pearl.fcw.proto.enums.EPayType;
import com.pearl.fcw.utils.StringUtil;

/**
 * SysAchievement表的特殊字段描述
 */
public class WSysAchievementColumnDescriptor extends ColumnDescriptor<WSysAchievement> {
	//    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public WSysAchievement get(WSysAchievement m) {
        if (null == m) {
            return m;
        }
        if (!StringUtil.isEmpty(m.getGift())) {
            List<WPayment> wPaymentList = Stream.of(m.getGift().split(";")).map(p -> {
                WPayment wPayment = new WPayment();
                wPayment.setPayType(EPayType.PAY_NONE.getNumber());
                List<Integer> list = Stream.of(p.split(",")).map(Integer::parseInt).collect(Collectors.toList());
                try {
                    wPayment.setItemId(list.get(0));
                    wPayment.setPayType(list.get(1));
                    wPayment.setUnitType(list.get(2));
                    wPayment.setCost(list.get(3));
                    wPayment.setUnit(list.get(4));
                } catch (Exception e) {
                }
                return wPayment;
            }).collect(Collectors.toList());
            m.getGiftList().addAll(wPaymentList);
        }
        return m;
    }

    @Override
    public void set(WSysAchievement m) {
    }

}
