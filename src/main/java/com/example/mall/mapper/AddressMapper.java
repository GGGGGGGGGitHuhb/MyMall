package com.example.mall.mapper;

import com.example.mall.entity.Address;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AddressMapper {

    int insert(Address address);

    Address findById(Long id);

    List<Address> findByUserId(Long userId);

    int deleteById(Long id);

    int update(Address address);
}
