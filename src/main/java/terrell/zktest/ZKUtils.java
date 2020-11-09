package terrell.zktest;
/**
 * @author: TerrellChen
 * @version: Created in 21:57 2020-11-09
 */

import com.alibaba.fastjson.JSONObject;
import org.apache.zookeeper.server.quorum.QuorumPeerConfig;
import org.apache.zookeeper.server.quorum.QuorumPeerMain;

import java.io.IOException;
import java.util.Properties;

/**
 * Description:
 */
public class ZKUtils {
    public static QuorumPeerMain quorumPeerMain;

    public static void startZkCluster(String myId, int clusterSize) throws IOException, QuorumPeerConfig.ConfigException {
        JSONObject zkConf = TestConfig.getClusterConfig(clusterSize).getJSONObject(myId);

        Properties properties = new Properties();
        properties.putAll(zkConf.getInnerMap());

        QuorumPeerConfig quorumPeerConfig = new QuorumPeerConfig();
        quorumPeerConfig.parseProperties(properties);



        quorumPeerMain = new QuorumPeerMain();
        quorumPeerMain.runFromConfig(quorumPeerConfig);
    }

    public static void stop() {
        quorumPeerMain.
    }
}
