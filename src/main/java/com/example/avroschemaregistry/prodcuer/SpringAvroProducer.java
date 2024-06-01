package com.example.avroschemaregistry.prodcuer;

import com.example.avro.schema.StockHistory;
import com.example.avroschemaregistry.config.KafkaTopic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;


import java.util.concurrent.CompletableFuture;

@Service
public class SpringAvroProducer {

    Logger logger= LoggerFactory.getLogger(SpringAvroProducer.class);
    @Autowired
    KafkaTemplate<String, StockHistory> kafkaTemplate;


    public String  sendStockData(StockHistory stockHistory){

       CompletableFuture<SendResult<String,StockHistory>> future= kafkaTemplate.send(KafkaTopic.TOPIC_1,String.valueOf(stockHistory.tradeId),stockHistory);
       future.whenComplete((result,exception)-> {
          try{
              logger.info("history stored  successfully");
          }catch (Exception e){
             logger.info("unable to store the history of stock date");
          }
        });

       return "history stored";
    }
}
