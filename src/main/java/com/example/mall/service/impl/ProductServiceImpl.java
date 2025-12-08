package com.example.mall.service.impl;

import java.util.ArrayList;
import java.util.List;
import com.example.mall.dto.ProductCreateDTO;
import com.example.mall.entity.Product;
import com.example.mall.exception.BizException;
import com.example.mall.mapper.ProductMapper;
import com.example.mall.service.ProductService;
import com.example.mall.vo.ProductVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    public static final int STATUS_ENABLE = 1;
    public static final int STATUS_DISABLE = 0;

    private final ProductMapper productMapper;

    @Override
    public void createProduct(ProductCreateDTO dto) {

        Product p = new Product();
        p.setName(dto.getName());
        p.setPrice(dto.getPrice());
        p.setStock(dto.getStock());
        p.setDescription(dto.getDescription());
        p.setImageUrl(dto.getImageUrl());

        productMapper.insert(p);
    }

    @Override
    public ProductVO getProduct(Long id) {
        Product p = productMapper.findById(id);
        if (p == null) {
            throw new BizException(2001, "商品不存在");
        }
        return toVO(p);
    }

    @Override
    public List<ProductVO> listProducts() {
        List<Product> list = productMapper.findAll();
        List<ProductVO> result = new ArrayList<>();
        for (Product p : list) {
            result.add(toVO(p));
        }
        return result;
    }

    @Override
    public void enableProduct(Long id) {
        productMapper.updateStatus(id, STATUS_ENABLE);
    }

    @Override
    public void disableProduct(Long id) {
        productMapper.updateStatus(id, STATUS_DISABLE);
    }

    private ProductVO toVO(Product p) {
        ProductVO vo = new ProductVO();
        vo.setId(p.getId());
        vo.setName(p.getName());
        vo.setPrice(p.getPrice());
        vo.setStock(p.getStock());
        vo.setImageUrl(p.getImageUrl());
        vo.setDescription(p.getDescription());
        vo.setStatus(p.getStatus());
        return vo;
    }
}
