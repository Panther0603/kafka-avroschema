package com.example.avroschemaregistry.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;

public class KafkaTopic {

    public  static final String TOPIC_1="stock-history-topic";

    @Bean
    public NewTopic createNewTopic(){
        return  new NewTopic(TOPIC_1,1,(short) 1);
    }

}
