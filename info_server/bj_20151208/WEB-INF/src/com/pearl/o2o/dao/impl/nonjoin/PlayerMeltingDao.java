/**
 * 
 */
package com.pearl.o2o.dao.impl.nonjoin;

import org.springframework.dao.DataAccessException;

import com.pearl.o2o.pojo.PlayerMelting;

/**
 * @author lifengyang
 * 
 */
public class PlayerMeltingDao extends BaseMappingDao {
	public PlayerMelting getPlayerMelting(final Integer playerId) throws DataAccessException {
		return queryMappingBeanById(PlayerMelting.class, playerId);
	}
	
	public PlayerMelting createPlayerMelting(PlayerMelting playerMelting) throws Exception {
		insertObjIntoDBAndCache(playerMelting);
		return playerMelting;
	}

	public PlayerMelting updatePlayerMelting(PlayerMelting playerMelting) throws Exception {
		updateMappingBeanInCache(playerMelting, playerMelting.getId());
		return playerMelting;
	}
}
