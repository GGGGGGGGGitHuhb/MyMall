package com.example.mall.service.impl;

import com.example.mall.dto.AddressCreateDTO;
import com.example.mall.dto.AddressUpdateDTO;
import com.example.mall.entity.Address;
import com.example.mall.exception.BizException;
import com.example.mall.mapper.AddressMapper;
import com.example.mall.service.AddressService;
import com.example.mall.vo.AddressVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressMapper addressMapper;

    @Override
    public void addAddress(Long userId, AddressCreateDTO dto) {
        Address a = new Address();
        a.setUserId(userId);
        a.setReceiverName(dto.getReceiverName());
        a.setReceiverPhone(dto.getReceiverPhone());
        a.setFullAddress(dto.getFullAddress());

        addressMapper.insert(a);
    }

    @Override
    public void updateAddress(Long userId, Long addressId, AddressUpdateDTO dto) {
        Address db = addressMapper.findById(addressId);
        if (db == null) {
            throw new BizException(4001, "地址不存在");
        }
        if (!db.getUserId().equals(userId)) {
            throw new BizException(1004, "无权限操作此地址");
        }

        db.setReceiverName(dto.getReceiverName());
        db.setReceiverPhone(dto.getReceiverPhone());
        db.setFullAddress(dto.getFullAddress());

        addressMapper.update(db);
    }

    @Override
    public void deleteAddress(Long userId, Long addressId) {
        Address db = addressMapper.findById(addressId);
        if (db == null) {
            throw new BizException(4001, "地址不存在");
        }
        if (!db.getUserId().equals(userId)) {
            throw new BizException(1004, "无权限操作此地址");
        }

        addressMapper.deleteById(addressId);
    }

    @Override
    public List<AddressVO> listAddresses(Long userId) {
        return addressMapper.findByUserId(userId).stream().map(a -> {
            AddressVO vo = new AddressVO();
            vo.setId(a.getId());
            vo.setReceiverName(a.getReceiverName());
            vo.setReceiverPhone(a.getReceiverPhone());
            vo.setFullAddress(a.getFullAddress());
            return vo;
        }).toList();
    }
}
