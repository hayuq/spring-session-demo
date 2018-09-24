package com.june.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.RedisHttpSessionConfiguration;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfiguration {
	
	@Value("${redis.dev.host}")
	private String host;
	
	@Value("${redis.dev.port}")
	private int port;
	
	@Value("${redis.dev.password}")
	private String password;
	
	@Value("${redis.pool.maxTotal}")
	private int maxTotal;
	
	@Value("${redis.pool.maxIdle}")
	private int maxIdle;
	
	@Value("${redis.pool.maxWaitMillis}")
	private int maxWaitMillis;
	
	@Value("${redis.pool.testOnBorrow}")
	private boolean testOnBorrow;
	
	@Bean
	public JedisPoolConfig jedisPoolConfig() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxIdle(maxIdle);
		jedisPoolConfig.setMaxTotal(maxTotal);
		jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
		jedisPoolConfig.setTestOnBorrow(testOnBorrow);
		return jedisPoolConfig;
	}
	
	@Bean
	public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig) {
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(jedisPoolConfig);
		if (host != null) {			
			jedisConnectionFactory.setHostName(host);
		}
		if (port != 0) {
			jedisConnectionFactory.setPort(port);
		}
		if (password != null) {
			jedisConnectionFactory.setPassword(password);
		}
		return jedisConnectionFactory;
	}
	
}
