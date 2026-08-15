package com.pearl.fcw.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.pearl.fcw.lobby.pojo.RandomWeight;

public class RandomUnit {

	private static final int MODULUS = 10000;// 目前定义的小数点后四位,过了4位的丢弃
    public static final Random RANDOM = new Random();

    /**
	 * 从list中按照权重随机获取一个成员
	 * @param list
	 * @return
	 */
    public static <T extends RandomWeight> T getRandomObjectByWeight(final List<T> list) {
        if (list.size() == 0) {
            return null;
        }
        float total = 0f;
        for (T t : list) {
            total += (null == t.getWeightRate() || t.getWeightRate() <= 0) ? 0 : t.getWeightRate();
        }
        if (total <= 0) {
            return list.get(0);
        }

        float result = RANDOM.nextFloat();
        T o = null;
        float sum = 0f;
        for (T t : list) {
            float value = ((null == t.getWeightRate() || t.getWeightRate() <= 0) ? 0 : t.getWeightRate()) / total;
            if (result >= sum && result < sum + value) {
                o = t;
                break;
            }
            sum += value;
        }
        if (o == null) {
            o = list.get(list.size() - 1);
        }

        return o;
    }

    /**
	 * 在指定的列表中按权重获取count个不重复的成员。指定的列表成员数量少于count时，返回空列表
	 * @param list
	 * @param count
	 * @return
	 */
    public static <T extends RandomWeight> List<T> getRandomObjectListByWeight(final List<T> list, int count) {
		list.removeIf(p -> p.getWeightRate() == 0);
        List<T> objList = new ArrayList<>();
		if (null == list || list.isEmpty() || count > list.size()) {
            return objList;
        }
        if (count == list.size()) {
            return list;
        }
		List<T> tmpList = new ArrayList<>();//中转list，防止原生list发生成员变化
        tmpList.addAll(list);
        while (objList.size() < count) {
            T p = getRandomObjectByWeight(tmpList);
            tmpList.remove(p);
            if (!objList.contains(p)) {
                objList.add(p);
            }
        }
        return objList;
    }

    /**
	 * 在指定的列表中按概率获取不重复的成员
	 * @param list
	 * @return
	 */
    public static <T extends RandomWeight> List<T> getRandomObjectListByProbability(final List<T> list) {
        List<T> objList = new ArrayList<>();
        list.forEach(p -> {
            if (null != p.getProbability() && p.getProbability() > 0 && (null == p.getWeightRate() || p.getWeightRate() <= 0)) {
                if (!objList.contains(p) && random(0f, 1f) <= p.getProbability()) {
                    objList.add(p);
                }
            }
        });
        return objList;
    }

    /**
	 * 在指定的列表中按概率获取指定数量的不重复的成员
	 * @param list
	 * @param count
	 * @return
	 */
    public static <T extends RandomWeight> List<T> getRandomObjectListByProbability(final List<T> list, int count) {
        List<T> objList = new ArrayList<>();
        if (count < list.size() || count > list.size()) {
            return getRandomObjectListByProbability(list);
        }
        while (objList.size() < count) {
            for (T p : list) {
                if (null != p.getProbability() && p.getProbability() > 0 && (null == p.getWeightRate() || p.getWeightRate() <= 0)) {
                    if (!objList.contains(p) && random(0f, 1f) <= p.getProbability()) {
                        objList.add(p);
                        if (objList.size() >= count) {
                            return objList;
                        }
                    }
                }
            }
        }
        return objList;
    }

    /**
	 * 获得指定范围内的随机数，只保留4位小数
	 * @param min 最小值
	 * @param max 最大值
	 * @return float随机数
	 */
    public static float random(float min, float max) {
        int minInt = (int) (min * MODULUS);
        int maxInt = (int) (max * MODULUS);
        return ((RANDOM.nextInt(maxInt - minInt)) + minInt) / (float) MODULUS;
    }

	/**
	 * 获得指定范围内（左闭右闭区间）的随机数
	 * @param min
	 * @param max
	 * @return
	 */
	public static int random(int min, int max) {
		return (RANDOM.nextInt(max - min + 1)) + min;
	}

    public static void main(String[] args) {
        System.out.println(RANDOM.nextFloat());
    }
}
