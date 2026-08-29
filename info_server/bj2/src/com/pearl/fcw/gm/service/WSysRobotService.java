package com.pearl.fcw.gm.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pearl.fcw.core.service.DmServiceImpl;
import com.pearl.fcw.gm.dao.WSysBannedWordDao;
import com.pearl.fcw.gm.dao.WSysRobotDao;
import com.pearl.fcw.gm.pojo.WSysRobot;
import com.pearl.fcw.lobby.dao.WPlayerDao;

/**
 * 机器人
 */
@Service
public class WSysRobotService extends DmServiceImpl<WSysRobot, Integer> {
	//	private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
	private WSysRobotDao wSysRobotDao;
	@Resource
	private WSysBannedWordDao wSysBannedWordDao;
	@Resource
	private WPlayerDao wPlayerDao;

	private static final int[][] charSelects = { { 48, 9 }, { 65, 25 }, { 97, 25 }, { 176, 39 }, { 161, 93 } };//数字，大写字母，小写字母，汉字，汉字

    @PostConstruct
    public void init() {
		this.genDao = wSysRobotDao;
    }

	/**
	 * 增加指定数量的机器人
	 * @param count
	 * @param minNameLen
	 * @param maxNameLen
	 * @throws Exception
	 */
	public void addRobots(int count, int minNameLen, int maxNameLen) throws Exception {
		//原机器人数据必须为空，才能生成新的机器人
		List<WSysRobot> wSysRobotList = findList(null);
		List<WSysRobot> newList = new ArrayList<>();
		Set<String> names = new HashSet<>();
		Set<String> banNameSet = wSysBannedWordDao.findList(null).stream().map(p -> p.getBannedWord().trim()).collect(Collectors.toSet());
		banNameSet.addAll(wPlayerDao.findList(null).stream().map(p -> p.getName().trim()).collect(Collectors.toSet()));
		banNameSet.addAll(wSysRobotList.stream().map(p -> p.getName()).collect(Collectors.toSet()));
		Random random = new Random();
		while (names.size() < count) {
			String name = getRandomRobotName(random, minNameLen, maxNameLen).trim();
			//不能出现禁言表的字符串，玩家表的昵称，已存在的机器人昵称
			if (banNameSet.contains(name)) {
				continue;
			}
			names.add(name);
		}
		names.forEach(p -> {
			WSysRobot wSysRobot = new WSysRobot();
			wSysRobot.setName(p);
			newList.add(wSysRobot);
		});
		wSysRobotDao.replace(newList);
	}

	private static String getRandomRobotName(Random random, int minNameLen, int maxNameLen) throws Exception {
		int len = random.nextInt(maxNameLen - minNameLen) + minNameLen;//名字的GBK编码的字符长度
		byte[] bs = new byte[len];//字符数组
		int counter = 0;//生成随机字符的长度计数器
		int defaultIndex = -1;//上一个字符的来源索引
		int index = 0;//当前字符的来源索引
		//字符来源索引从数字，大小写字母，汉字字符中随机。目前做法是整个字符数组的字符来源索引尽量是同一来源（汉字有些特殊）
		while (counter < len) {
			if (defaultIndex < 0) {//第一个字符
				double r = random.nextDouble();
				if (r < 0.25) {
					index = 0;
				} else if (r < 0.5) {
					index = 1;
				} else if (r < 0.75) {
					index = 2;
				} else {
					index = 3;
				}
				defaultIndex = index;
			} else {
				index = defaultIndex;
			}
			if (index >= charSelects.length - 1) {//汉字是双字节，索引总是取汉字的第一个字节
				index = charSelects.length - 2;
			}
			if (counter == len - 1) {//最后一个字符不能是中文字符
				index = random.nextInt(3);
				bs[counter++] = (byte) (charSelects[index][0] + random.nextInt(charSelects[index][1]));
				break;
			}
			if (index == charSelects.length - 2) {//随机字符的索引源是汉字，双字节处理
				bs[counter++] = (byte) (charSelects[index][0] + random.nextInt(charSelects[index][1]));
				bs[counter++] = (byte) (charSelects[index + 1][0] + random.nextInt(charSelects[index + 1][1]));
			} else {//随机字符的索引源不是汉字，单字节处理
				bs[counter++] = (byte) (charSelects[index][0] + random.nextInt(charSelects[index][1]));
			}
		}
		return new String(bs, "GBK");
	}

	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 1000; i++) {
			System.out.println(getRandomRobotName(new Random(), 4, 8));
		}
	}
}
