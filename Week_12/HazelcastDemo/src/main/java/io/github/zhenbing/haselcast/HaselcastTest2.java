package io.github.zhenbing.haselcast;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

/**
 * @Description
 * @Author fzb
 * @date 2021.03.28 17:11
 */
public class HaselcastTest2 {
    public static void main(String[] args) {
        HazelcastInstance hz = HazelcastClient.newHazelcastClient();
        IMap<String, String> map = hz.getMap("my-distributed-map");
        String current = map.get("key");
        System.out.println(current);
    }
}
