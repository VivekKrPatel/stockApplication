package com.example.Stock.controllers;


import com.example.Stock.models.Stock;
import com.example.Stock.models.StockType;
import com.example.Stock.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/stock")
public class StockController {

    @Autowired
    StockService stockService;

    //get stocks by stock type
    @GetMapping(value = "/type/{stockType}")
    public List<Stock> getStocksBasedOnType(@PathVariable StockType stockType)
    {
        return stockService.getStocksByType(stockType);
    }

    //get using custom finder
    @GetMapping(value = "abovePrice/price/{price}/lowerDate/date/{date}")
    public List<Stock> getStocksAbovePriceAndLowerDate(@PathVariable Double price,@PathVariable String date)
    {
        return stockService.getStocksAbovePriceAndLowerDate(price,date);
    }

    //custom query
    @GetMapping(value = "/cap/{capPercentage}")
    public List<Stock> getAllStocksAboveMarketCap(@PathVariable Double capPercentage)
    {
        return stockService.getAllStocksAboveMarketCap(capPercentage);
    }

    //post
    @PostMapping(value = "/")
    public String insertStocks(@RequestBody List<Stock> stockList)
    {
        return stockService.addStocks(stockList);
    }

    //put
    @PutMapping(value = "/marketCap/{marketCap}/id/{id}")
    public void insertStocks(@PathVariable Double marketCap, @PathVariable Integer id)
    {
         stockService.updateMarketCap(marketCap,id);
    }

    //PUT USING CUSTOM QUERY :
    @PutMapping(value = "/stock/type/id")
    public void updateTypeById(@RequestParam StockType stockType, @RequestParam Integer id)
    {
        stockService.updateTypeById(stockType,id);
    }

    //put using Cq : stock, id
    @PutMapping(value = "/stock/{id}")
    public void updateStockById(@PathVariable Integer id, @RequestBody Stock myStock)
    {
        stockService.updateStockById(id,myStock);
    }

    //DELETE stock by owner count i.e stock_owner_count <= count
    @DeleteMapping(value = "/ownerCount/{count}")
    public void removeStocksByOwnerCount(@PathVariable  Integer count)
    {
        stockService.deleteStocksBasedOnCount(count);
    }





}
