package terrell.zktest;
/**
 * @author: TerrellChen
 * @version: Created in 22:06 2020-11-09
 */

import com.alibaba.fastjson.JSONObject;

/**
 * Description:
 */
public class TestConfig {
    public static JSONObject getZkConfig(int nodeId, int clusterSize) {
        JSONObject zkConf = new JSONObject();
        zkConf.put("myid", String.valueOf(nodeId));
        zkConf.put("tickTime", "2000");
        zkConf.put("dataDir", "/tmp/zookeeper/data/" + (nodeId));
        zkConf.put("clientPort", 2181 + nodeId - 1);
        zkConf.put("initLimit", 10);
        zkConf.put("syncLimit", 5);
        for (int i=0;i<clusterSize;i++) {
            zkConf.put("server." + (i+1), "127.0.0.1:" + (2888 + i) + ":" + (3888 + i));
        }
        return zkConf;
    }

    public static JSONObject getClusterConfig(int clusterSize) {
        JSONObject clusterConfig = new JSONObject();
        for (int i=0;i<clusterSize;i++) {
            clusterConfig.put(String.valueOf(i+1), getZkConfig(i+1, clusterSize));
        }
        return clusterConfig;
    }

    public static void main(String[] args) {
    }
}
