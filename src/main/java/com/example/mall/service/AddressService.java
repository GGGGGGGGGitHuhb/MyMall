package com.example.mall.service;

import com.example.mall.dto.AddressCreateDTO;
import com.example.mall.dto.AddressUpdateDTO;
import com.example.mall.vo.AddressVO;
import java.util.List;

public interface AddressService {

    void addAddress(Long userId, AddressCreateDTO dto);

    void updateAddress(Long userId, Long addressId, AddressUpdateDTO dto);

    void deleteAddress(Long userId, Long addressId);

    List<AddressVO> listAddresses(Long userId);
}
