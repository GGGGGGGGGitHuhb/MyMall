package com.example.mall.service;

import java.util.List;
import com.example.mall.dto.ProductCreateDTO;
import com.example.mall.vo.ProductVO;

public interface ProductService {

    void createProduct(ProductCreateDTO dto);

    ProductVO getProduct(Long id);

    List<ProductVO> listProducts();

    void enableProduct(Long id);

    void disableProduct(Long id);
}
