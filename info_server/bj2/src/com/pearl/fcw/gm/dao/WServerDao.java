package com.pearl.fcw.gm.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.pearl.fcw.core.dao.StableCacheDao;
import com.pearl.fcw.core.pojo.BaseEntity;
import com.pearl.fcw.gm.pojo.WServer;

@Repository
public class WServerDao extends StableCacheDao<WServer, Integer> {

    /**
     * 因Channel表对Server表有外键关联，所以不能直接采用父类的replaceBatch方法
     */
    @Override
    public void replaceBatch(List<BaseEntity> list) throws Exception {
        List<Integer> idList = findList(null).stream().map(WServer::getId).collect(Collectors.toList());
        List<BaseEntity> updateList = list.stream().filter(m -> idList.contains(((WServer) m).getId())).collect(Collectors.toList());
        List<BaseEntity> replaceList = list.stream().filter(m -> !idList.contains(((WServer) m).getId())).collect(Collectors.toList());
        for (BaseEntity m : updateList) {
            update((WServer) m);
        }
        super.replaceBatch(replaceList);
    }

}
