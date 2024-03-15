package com.example.redis;

import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

public class RedisMessageConsumer {
    public String getMessage(String key) {
        String message = null;
        try (StatefulRedisConnection<String, String> connection = RedisConnectionManager.getConnection()) {
            RedisCommands<String, String> commands = connection.sync();
            message = commands.get(key);
        } catch (Exception e) {
            // Handle connection or Redis operation errors
            e.printStackTrace();
        }
        return message;
    }
}
