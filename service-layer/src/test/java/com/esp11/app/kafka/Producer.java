package com.esp11.app.kafka;

import java.util.Properties;
import java.util.UUID;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.esp11.app.Utils;
import java.util.ArrayList;
import java.util.List;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;

public class Producer {
    private final Properties properties = getProperties();

    public Producer() {
        Properties properties = getProperties2();
        AdminClient adminClient = AdminClient.create(properties);
        NewTopic newTopic = new NewTopic("gps", 1, (short)1); //new NewTopic(topicName, numPartitions, replicationFactor)
        NewTopic newTopic2 = new NewTopic("control", 1, (short)1);
        List<NewTopic> newTopics = new ArrayList<>();
        newTopics.add(newTopic);
        newTopics.add(newTopic2);

        adminClient.createTopics(newTopics);
     //new NewTopic(topicName, numPartitions, replicationFactor)
    }

    private Properties getProperties()
    {
        Properties properties = Utils.getConfigProperties();

        properties.put("acks", "all");
        properties.put("retries", 2);
        properties.put("batch.size", 16384);
        properties.put("linger.ms", 1);
        properties.put("buffer.memory", 33554432);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        return properties;
    }
    private Properties getProperties2()
    {
        Properties properties = Utils.getConfigProperties();
        
        properties.put("enable.auto.commit", true);
        properties.put("auto.commit.interval.ms", 1000);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        return properties;
    }

    public void emit( String topicName, String message )
    {
        String partitionKey = UUID.randomUUID().toString();
        try (KafkaProducer<Object, Object> kafkaProducerLocal = new KafkaProducer<>(properties)) {
            kafkaProducerLocal.send(new ProducerRecord<>(topicName, partitionKey, message));
        }
    }
}