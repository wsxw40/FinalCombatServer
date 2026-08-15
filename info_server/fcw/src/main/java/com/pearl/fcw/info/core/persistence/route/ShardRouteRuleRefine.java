package com.pearl.fcw.info.core.persistence.route;


public class ShardRouteRuleRefine {

    private int capacity;

    private final String STR_MAX_ID = Long.toString(Long.MAX_VALUE);

    public final int LEN_ZONE = 3;
    public final int LEN_NODE = 3;
    public final int LEN_SPLIT_TABLE = 2;
    public final int LEN_SUB_ID = STR_MAX_ID.length() - LEN_ZONE - LEN_NODE - LEN_SPLIT_TABLE;      // 子ID最大长度（去掉大区号、节点号、分表号）

    public final long OFFSET_ZONE = (long) Math.pow(10, STR_MAX_ID.length() - LEN_ZONE);            // 大区偏置量
    public final long OFFSET_NODE = (long) Math.pow(10, STR_MAX_ID.length() - LEN_ZONE - LEN_NODE); // 节点偏置量
    public final long OFFSET_SPLIT_TABLE = (long) Math.pow(10, STR_MAX_ID.length() - LEN_ZONE - LEN_NODE - LEN_SPLIT_TABLE); // 节点偏置量

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * 确定唯一节点ID，该值应该保证全大区唯一（即，大区号 + 节点号）。
     *
     * @param key 数据库拆分键值
     * @return 唯一节点ID
     */
    public int determineNodeId(long key) {
        if (key < 0) {
            key = -key;
        }
        int zone = (int) (key / OFFSET_ZONE);                       // 大区号，三位数（0 ~ 921）
        int node = ((int) (key % OFFSET_ZONE) - 1) / capacity;      // 节点号，三位数（0 ~ 999）。由于ID从“区号 + 1”开始，此处“-1”没有问题
        return zone * 1000 + node;
    }

    /**
     * 确定节点内，指定键值所使用的分表号（不包含大区号、节点号）。
     *
     * @param key 数据库拆分键值
     * @param tableCountPerNode 单节点中该表数量
     * @return 分表号
     */
    public int determineTableNum(long key, int tableCountPerNode) {
        if (key < 0) {
            key = -key;
        }
        return (int) (key % tableCountPerNode);
    }

    /**
     * 取得指定节点的起始ID
     *
     * @param nodeId 节点ID
     * @return 起始ID
     */
    public long getAutoIncrement(int nodeId) {
        return nodeId * OFFSET_NODE + 1;    // TODO test
    }

    /**
     * 取得指定节点、分表的起始ID
     *
     * @param nodeId 节点ID
     * @param tableNum 分表号
     * @return 起始ID
     */
    public long getAutoIncrement(int nodeId, int tableNum) {
        return nodeId * OFFSET_NODE + tableNum * OFFSET_SPLIT_TABLE + 1;    // TODO test
    }

    //    public String getTableSuffix(int nodeId, int tableNum) {
    //        int tableId = nodeId * 100 + tableNum;
    //        return String.format("%08d", tableId);
    //    }


}
