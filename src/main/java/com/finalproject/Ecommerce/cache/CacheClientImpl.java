package com.finalproject.Ecommerce.cache;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finalproject.Ecommerce.config.ApplicationProperties;
import com.finalproject.Ecommerce.utils.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;

@Slf4j
@Component
@RequiredArgsConstructor
public class CacheClientImpl<T> implements CacheClient<T> {

    private final RedisTemplate<String, T> redisTemplate;
    private final ApplicationProperties properties;
    private HashOperations<String, String, String> hashOperations;
    private final ObjectMapper mapper;

    @PostConstruct
    void setHashOperations(){
        hashOperations = this.redisTemplate.opsForHash();
        this.redisTemplate.expire(Constants.NAME_MAP_USER, Duration.ofMillis(properties.getTimeOfLife()));
    }

    @Override
    public T save(String key, T data) {
        try{
            hashOperations.put(Constants.NAME_MAP_USER, key, serializeItem(data));
            return data;
        } catch (JsonProcessingException e){
            log.error("Unexpected error trying to convert {} instance to String", data.getClass().getSimpleName());
        }
        return data;
    }

    @Override
    public T recover(String key, Class<T> classValue) {
        try{
            var item = hashOperations.get(Constants.NAME_MAP_USER, key);
            if(item == null) return null;
            return deserializeItem(item, classValue);
        } catch(JsonProcessingException e) {
//            log.error("Unexpected error trying to convert String to {}", classValue);
            log.error("Unexpected error trying to convert String to User");
        }
        return null;
    }


    private String serializeItem(T item) throws JsonProcessingException{
        String serializedItem = mapper.writeValueAsString(item);
        log.info("String format: {} ", serializedItem);
        return serializedItem;
    }

    private T deserializeItem(String item, Class<T> classValue) throws JsonProcessingException{
        return mapper.readValue(item, classValue);
    }
}
