package com.pearl.fcw.lobby.servlet.rpc.ranks;

import org.springframework.stereotype.Service;

import com.pearl.fcw.core.service.Servletable;
import com.pearl.o2o.servlet.client.BaseClientServlet;

/**
 * 获得玩家战斗力排行榜
 * @author zhaolianming
 */
@Service("c_fightnum_top1")
public class CGetPlayerFightNumTop extends BaseClientServlet implements Servletable {

    //    private static final long serialVersionUID = -5827934245624414605L;
    //    private Logger logger = LoggerFactory.getLogger(getClass());
    //    private static final String[] paramNames = {"pid","t","self","page"};
    //    @Resource
    //    private CGetPlayerFightNumTop c_fightnum_top1;
    //    @Resource
    //    private WRanksService wRanksService;
    //    @Override
    //    protected String innerService(String... strings) {
    //        try {
    //            return c_fightnum_top1.rpc(strings);
    //        } catch (Exception e) {
    //            logger.error("c_daily_discount_item has error : ", e);
    //        }
    //        return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
    //    }
    //    @Override
    //    public String rpc(String... args) throws Exception {
    //        int playerId = StringUtil.toInt(args[0]);
    //        String cid = args[1];
    //        int self = StringUtil.toInt(args[2]);
    //        int page = StringUtil.toInt(args[3]);
    //        return wRanksService.getPlayerFightNumTop(playerId,cid,self,page);
    //    }
    //    @Override
    //    protected String[] paramNames() {
    //        return  paramNames;
    //    }
    //    @Override
    //    public String getLockedKey(String... args) {
    //        return args[0];
    //    }
}
