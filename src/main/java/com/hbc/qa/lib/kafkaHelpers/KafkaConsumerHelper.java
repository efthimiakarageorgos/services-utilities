/**
 * © TheCompany QA 2019. All rights reserved.
 * CONFIDENTIAL AND PROPRIETARY INFORMATION OF TheCompany.
 */
package com.hbc.qa.lib.kafkaHelpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.apache.log4j.Logger;


public class KafkaConsumerHelper {
    final static Logger logger = Logger.getRootLogger();

    public List getMessagesContainingString(String bootstapServers, String topicName, String searchString) {
        //TODO Cannot make the call below work
        //QioKafkaConsumer consumer = new QioKafkaConsumer(bootstapServers);

        Properties props = new Properties();
        props.put("bootstrap.servers", bootstapServers);
        props.put("group.id", "test1");
        props.put("enable.auto.commit", "false");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("auto.offset.reset", "earliest");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        consumer.subscribe(Arrays.asList(topicName));
        List kafka_message = new ArrayList();
        List matching_kafka_message = new ArrayList();
        ConsumerRecords<String, String> records = consumer.poll(1000000000);
        for (ConsumerRecord<String, String> record : records){
            //System.out.printf(" offset = %d, key = %s, value = %s \n", record.offset(), record.key(), record.value());
            kafka_message.add(record.value());
        }
        for (int i = 0; i < kafka_message.size(); i++) {
            //System.out.println(kafka_message.get(i));
            if (kafka_message.get(i).toString().contains(searchString)){
                Arrays.asList(kafka_message).contains(searchString);
                //System.out.println(kafka_message.get(i));
                matching_kafka_message.add(kafka_message.get(i));
            }
        }
        consumer.close();
        return matching_kafka_message;
    }


    public List searchForMessagesContainingStringUntilStringFoundOrTimeElapses(String bootstapServers, String topicName, String searchString, int elapsedMinutes, int searchSecondsInterval) {
        DateTimeZone.setDefault(DateTimeZone.UTC);
        DateTimeFormatter parser = ISODateTimeFormat.dateHourMinuteSecondMillis();
        DateTime nowdt=new DateTime();
        DateTime comparedt=nowdt.plusMinutes(elapsedMinutes);

        List msgsFoundOnKafka = null;

        while (nowdt.isBefore(comparedt)) {
            try {
                TimeUnit.SECONDS.sleep(searchSecondsInterval);
                logger.info("                          WAIT");
            } catch (InterruptedException e) {}

            msgsFoundOnKafka = getMessagesContainingString(bootstapServers, topicName, searchString);
            if (!msgsFoundOnKafka.isEmpty()) break;
            nowdt = new DateTime();
        }
        return msgsFoundOnKafka;
    }
}