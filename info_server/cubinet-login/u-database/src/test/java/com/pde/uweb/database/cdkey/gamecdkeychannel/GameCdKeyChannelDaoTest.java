package com.pde.uweb.database.cdkey.gamecdkeychannel;

import com.pde.uweb.database.cdkey.gamecdkeytype.GameCdKeyTypeDaoImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lifengyang
 * Date: 12-9-26
 * Time: 下午5:48
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:META-INF/spring/spring-uweb-database.xml",
        "classpath:/META-INF/spring/spring-uweb-framework.xml"
})
public class GameCdKeyChannelDaoTest {

    @Resource(name = "cacheManager")
    SimpleCacheManager cacheManager;

    @Resource(name = "gameCdKeyChannelDao_database")
    GameCdKeyChannelDaoImpl gameCdKeyChannelDao_database;

    @Test
    public void testAdd() throws Exception {

    }

    @Test
    public void testUpdate() throws Exception {

    }

    @Test
    public void testUpdateForField() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {

    }

    @Test
    public void testDelete2() throws Exception {

    }

    @Test
    public void testSelect() throws Exception {

    }

    @Test
    public void testGetVersion() throws Exception {

    }

    @Test
    public void testSelectAll()throws Exception{
//        List<GameCdKeyChannelPojo> gameCdKeyChannelPojos = this.gameCdKeyChannelDao_database.selectAll();
//        System.out.println(gameCdKeyChannelPojos);
    }
}
