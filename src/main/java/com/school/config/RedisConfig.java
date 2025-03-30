package com.school.config;

import java.time.Duration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
@EnableCaching

public class RedisConfig {

	@Bean
	public LettuceConnectionFactory redisConnectionfactory() {
		return new LettuceConnectionFactory();
	}

	@Bean
	public RedisCacheManager cacheManager(LettuceConnectionFactory connectionManager) {
		RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
				.entryTtl(Duration.ofMinutes(4)).disableCachingNullValues();

		return RedisCacheManager.builder(connectionManager).cacheDefaults(cacheConfig).build();

	}
}
