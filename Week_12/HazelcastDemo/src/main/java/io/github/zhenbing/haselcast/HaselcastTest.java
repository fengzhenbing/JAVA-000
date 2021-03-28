package io.github.zhenbing.haselcast;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

/**
 * @Description
 * @Author fzb
 * @date 2021.03.28 17:11
 */
public class HaselcastTest {
    public static void main(String[] args) {
        HazelcastInstance hz = HazelcastClient.newHazelcastClient();
        IMap<String, String> map = hz.getMap("my-distributed-map");
        map.put("key", "value");
        String current = map.get("key");
        map.putIfAbsent("somekey", "somevalue");
        map.replace("key", "value", "newvalue");
    }
}
