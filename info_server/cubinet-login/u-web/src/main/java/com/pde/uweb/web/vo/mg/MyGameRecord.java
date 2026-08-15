package com.pde.uweb.web.vo.mg;

import com.pde.uweb.database.cdkey.gamecdkey.GameCdKeyPojo;
import com.pde.uweb.database.cdkey.gamecdkeychannel.GameCdKeyChannelPojo;
import com.pde.uweb.database.cdkey.gamecdkeytype.GameCdKeyTypePojo;
import com.pde.uweb.database.ws.channel.ChannelPojo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lifengyang
 * Date: 12-9-27
 * Time: 下午3:04
 * To change this template use File | Settings | File Templates.
 */
public class MyGameRecord {
    private ChannelPojo channel;
    private List<GameCdKeyTypePojo> gameCdKeyTypes;
    private List<GameCdKeyChannelPojo> gameCdKeyChannels;
    private List<GameCdKeyPojo> gameCdKeys;

    public MyGameRecord(ChannelPojo channel) {
        this.channel = channel;
    }

    public void addGameCdKeyChannel(GameCdKeyChannelPojo gameCdKeyChannel) {
        gameCdKeyChannels.add(gameCdKeyChannel);
    }

    public void addGameCdKey(GameCdKeyPojo gameCdKey) {
        gameCdKeys.add(gameCdKey);
    }

    public void addGameCdKeyType(GameCdKeyTypePojo gameCdKeyType) {
        gameCdKeyTypes.add(gameCdKeyType);
    }

    public ChannelPojo getChannel() {
        return channel;
    }

    public List<GameCdKeyTypePojo> getGameCdKeyTypes() {
        return gameCdKeyTypes;
    }

    public List<GameCdKeyChannelPojo> getGameCdKeyChannels() {
        return gameCdKeyChannels;
    }

    public List<GameCdKeyPojo> getGameCdKeys() {
        return gameCdKeys;
    }
}
