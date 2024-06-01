package com.example.avroschemaregistry.model;

import lombok.Data;

@Data
public class StockHistoryModel {

     public int tradeId;
     public int tradeQuantity;
     public String tradeMarket;
     public String stockName;
     public String tradeType;
     public float price;
     public float amount;
}
