package com.example.avroschemaregistry.controller;

import com.example.avro.schema.StockHistory;
import com.example.avroschemaregistry.model.StockHistoryModel;
import com.example.avroschemaregistry.prodcuer.SpringAvroProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/stock")
public class SpringAvroProdcuerController {

    private final SpringAvroProducer springAvroProducer;

    @Autowired
    SpringAvroProdcuerController(SpringAvroProducer avroProducer){
        this.springAvroProducer=avroProducer;
    }

    @PostMapping("/history")
    public ResponseEntity<?> stockHistoryStore(@RequestBody StockHistoryModel stockHistoryModel){
       try{
           StockHistory stockHistory=StockHistory.newBuilder()
                   .setStockName(stockHistoryModel.getStockName())
                   .setAmount(stockHistoryModel.getAmount())
                   .setPrice(stockHistoryModel.getPrice())
                   .setTradeType(stockHistoryModel.getTradeType())
                   .setTradeMarket(stockHistoryModel.getTradeMarket())
                   .setTradeQuantity(stockHistoryModel.getTradeQuantity())
                   .setTradeId(new Random(100).nextInt()).build();

           return  ResponseEntity.ok(this.springAvroProducer.sendStockData(stockHistory));
       }catch (Exception e){
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("History not saved");
       }
    }
}
