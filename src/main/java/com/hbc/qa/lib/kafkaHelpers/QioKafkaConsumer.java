/**
 * © TheCompany QA 2019. All rights reserved.
 * CONFIDENTIAL AND PROPRIETARY INFORMATION OF TheCompany.
 */
package com.hbc.qa.lib.kafkaHelpers;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import java.util.Properties;

public class QioKafkaConsumer {
    private KafkaConsumer<String, String> consumer;

    public QioKafkaConsumer(String bootstapServers) {
        Properties props = new Properties();
        props.put("bootstrap.servers", bootstapServers);
        props.put("group.id", "test1");
        props.put("enable.auto.commit", "false");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("auto.offset.reset", "earliest");
        consumer = new KafkaConsumer<>(props);
    }

    public void close() {
        consumer.close();
    }
}

