package com.gss.kafka.avro.config;

import com.gss.kafka.avro.model.Message;
import com.gss.kafka.avro.serializer.AvroSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value(value = "${kafka.bootstrap.address}")
    private String bootstrapAddress;

    @Bean
    public ProducerFactory<String, Message> populateProducerFactory() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, AvroSerializer.class);
        return new DefaultKafkaProducerFactory<>(configs);
    }

    @Bean
    public KafkaTemplate<String, Message> kafkaTemplate() {
        return new KafkaTemplate<>(populateProducerFactory());
    }
}