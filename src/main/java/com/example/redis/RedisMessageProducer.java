package com.example.redis;

import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

public class RedisMessageProducer {
    public void storeMessage(String key, String message) {
        try (StatefulRedisConnection<String, String> connection = RedisConnectionManager.getConnection()) {
            RedisCommands<String, String> commands = connection.sync();
            commands.set(key, message);
        } catch (Exception e) {
            // Handle connection or Redis operation errors
            e.printStackTrace();
        }
    }
}
