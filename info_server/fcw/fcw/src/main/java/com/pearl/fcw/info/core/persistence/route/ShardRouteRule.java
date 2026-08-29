package com.pearl.fcw.info.core.persistence.route;

import com.pearl.fcw.info.core.persistence.tools.MySQLHelperPlus;


public class ShardRouteRule implements RouteRule {

    private int capacity;

    private long mask = 10000000000000000L;

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public int determineLookupKey(long key) {
        if (key < 0) {
            key = -key;
        }
        int zone = (int) (key / mask);                      // 三位数（0 ~ 921）
        int group = ((int) (key % mask) - 1) / capacity;    // ID从“区号 + 1”开始，此处“-1”没有问题
        return zone * 1000 + group;
    }

    @Override
    public String getTableSuffix(int shardId, long key, int tableCount) {
        if (key < 0) {
            key = -key;
        }
        return getTableSuffix(shardId, (int) (key % tableCount));
    }

    @Override
    public String getTableSuffix(int shardId, int tableNum) {
        int tableCode = shardId * 100 + tableNum;
        return String.format("%08d", tableCode);
    }

    @Override
    public long getAutoIncrement(int shardId, int tableNum) {
        String id = String.valueOf(shardId) + String.format("%02d", tableNum) + String.format("%0" + new MySQLHelperPlus().idLength + "d", Long.valueOf(1));
        return Long.parseLong(id);

    }

    public static void main(String[] args) {

        System.out.println("sdf" + 123 % 1);


        String a = String.format("%08d", 22001);
        System.out.println(a);


        ShardRouteRule rule = new ShardRouteRule();
        rule.setCapacity(4);
        //        System.out.println(rule.determineLookupKey(1, 2));
        //        System.out.println(rule.determineLookupKey(2, 2));
        //        System.out.println(rule.determineLookupKey(3, 2));
        //        System.out.println(rule.determineLookupKey(4, 2));

        System.out.println(rule.determineLookupKey(10000000000000001L));
        System.out.println(rule.determineLookupKey(10000000000000002L));
        System.out.println(rule.determineLookupKey(10000000000000003L));
        System.out.println(rule.determineLookupKey(20000000000000004L));
        System.out.println(rule.determineLookupKey(20000000000000005L));
        System.out.println(rule.determineLookupKey(10000000000000006L));
        System.out.println(rule.determineLookupKey(10000000000000007L));
        System.out.println(rule.determineLookupKey(20000000000000008L));
        System.out.println(rule.determineLookupKey(20000000000000009L));

        //        System.out.println(Long.MAX_VALUE);
        //
        //        DefaultRouteRule rule = new DefaultRouteRule();
        //        rule.setCapacity(4000000);
        //
        //        long s = System.currentTimeMillis();
        //        for (int i = 0; i < 1000000; i++) {
        //            rule.determineLookupKey(Long.MAX_VALUE);
        //        }
        //        System.out.println(System.currentTimeMillis() - s);

    }

}
