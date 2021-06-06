package com.example.demo.controller;

import com.example.demo.entity.JsoupUtil;
import com.example.demo.entity.ProductVo;
import com.example.demo.entity.ResultVo;
import com.example.demo.service.ProductRepository;
import com.google.common.collect.Lists;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.support.AbstractElasticsearchRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {


    @Autowired
    ProductRepository productRepository;

    @RequestMapping("getAllProductByEs")
    @ResponseBody
    public ResultVo getAllProductByEs(){
        Iterable<ProductVo> data = productRepository.findAll();
        return ResultVo.success(Lists.newArrayList(data));
    }

    @GetMapping("/save/{keyword}")
    @ResponseBody
    public ResultVo save(@PathVariable String keyword){
        List<ProductVo> data = JsoupUtil.getProDuctListByJD(keyword);
        productRepository.saveAll(data);
        return ResultVo.success();
    }

    @GetMapping("/search/{keyword}")
    @ResponseBody
    public ResultVo search(@PathVariable String keyword) throws InterruptedException {

        QueryBuilder queryBuilder = QueryBuilders.fuzzyQuery("title", keyword);

        List<ProductVo> list = new ArrayList<>();
        Iterable<ProductVo> aIterable = productRepository.search(queryBuilder);
        aIterable.forEach(e-> list.add(e) );
        return ResultVo.success(list);
    }






}
