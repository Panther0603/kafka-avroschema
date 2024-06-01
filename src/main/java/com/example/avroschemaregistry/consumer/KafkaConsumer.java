package com.example.avroschemaregistry.consumer;

import com.example.avro.schema.StockHistory;
import com.example.avroschemaregistry.config.KafkaTopic;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {


    @KafkaListener(topics = KafkaTopic.TOPIC_1,groupId = "my-avro-group")
    public void  listener(ConsumerRecord<String, StockHistory> consumerRecord){
        System.out.println("topic consumer");
        System.out.println("key "+consumerRecord.key()+" \n all date :-->"+consumerRecord.value().toString());
    }
}
