package com.kafka.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

@Slf4j
public class Consumer2 {

    public static void main(String[] args) {
        String groupId = "new_group3";
        extracted(groupId);
    }

    private static void extracted(String groupId) {
        Properties properties = new Properties();
        String bootstrapServer = "127.0.0.1:9092";

        String topic = "new_test2";
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

        TopicPartition topicPartition = new TopicPartition(topic, 1);
        long offsetToReadFrom = 16L;
        consumer.assign(Arrays.asList(topicPartition));

        consumer.seek(topicPartition, offsetToReadFrom);


        int numberOfMessageToRead = 5;
        while (numberOfMessageToRead > 0) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                log.info("key {}, Value {}", record.key(), record.value());
                log.info("Partition {}, Offset {}", record.partition(), record.offset());
                numberOfMessageToRead--;
                //        if(numberOfMessageToRead== 0){
                //          break;
                //    }
            }


        }
    }
}
