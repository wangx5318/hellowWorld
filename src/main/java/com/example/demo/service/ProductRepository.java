package com.example.demo.service;

import com.example.demo.entity.ProductVo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductRepository extends ElasticsearchRepository<ProductVo,String> {
}
