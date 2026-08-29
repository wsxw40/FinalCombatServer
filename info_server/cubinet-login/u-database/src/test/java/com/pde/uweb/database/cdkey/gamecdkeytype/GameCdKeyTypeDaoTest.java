package com.pde.uweb.database.cdkey.gamecdkeytype;

import com.pde.uweb.database.cdkey.gamecdkeychannel.GameCdKeyChannelDaoImpl;
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
 * Time: 下午5:47
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:META-INF/spring/spring-uweb-database.xml",
        "classpath:/META-INF/spring/spring-uweb-framework.xml"
})
public class GameCdKeyTypeDaoTest {
    @Resource(name = "cacheManager")
    SimpleCacheManager cacheManager;

    @Resource(name = "GameCdKeyTypeDao_database")
    GameCdKeyTypeDaoImpl GameCdKeyTypeDao_database;


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
    public void testGetAll()throws Exception{
//        List<GameCdKeyTypePojo> gameCdKeyTypePojos = this.GameCdKeyTypeDao_database.selectAll();
//        System.out.println(gameCdKeyTypePojos);
    }
}
