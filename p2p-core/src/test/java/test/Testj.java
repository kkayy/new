package test;

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class Testj {
@Test
	public void text1() {
		Jedis jedis = new Jedis("127.0.0.1",6888);
		jedis.set("k1", "hello");

	}
@Test
 	public void text2() {
	Jedis jedis = new Jedis("127.0.0.1",6888);
	
	System.out.println(jedis.get("k1"));
}
}
