package com.kafka.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

@Slf4j
public class Producer {

    public static void main(String[] args) {
        String bootstrapPup = "127.0.0.1:9092";
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapPup);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        log.info("start");
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        log.info("start producer");
        for (int i = 0; i < 3; i++) {
            String topic = "new_test2";
            String value = "gss" + i;
            String key = "id_" + i;
            ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);
            log.info("create record " + i);
            producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    log.info("Response\n" +
                                    "topic : {}\npartition : {}\noffset : {}\ntimestamp : {}\n",
                            recordMetadata.topic(), recordMetadata.partition(), recordMetadata.offset(), recordMetadata.timestamp());
                }
            });
        }
        log.info("send record");
        producer.close();
    }
}
