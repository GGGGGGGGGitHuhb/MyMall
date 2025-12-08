package com.example.mall.controller;

import com.example.mall.dto.AddressCreateDTO;
import com.example.mall.dto.AddressUpdateDTO;
import com.example.mall.service.AddressService;
import com.example.mall.vo.AddressVO;
import com.example.mall.common.Result;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    private Long getUserId(HttpServletRequest request) {
        Object obj = request.getAttribute("userId");
        return obj == null ? null : (Long) obj;
    }

    @PostMapping
    public Result<Void> add(HttpServletRequest req,
                            @RequestBody AddressCreateDTO dto) {

        Long userId = getUserId(req);
        addressService.addAddress(userId, dto);
        return Result.success();
    }

    @PutMapping("/{id}")
    public Result<Void> update(HttpServletRequest req,
                               @PathVariable Long id,
                               @RequestBody AddressUpdateDTO dto) {

        Long userId = getUserId(req);
        addressService.updateAddress(userId, id, dto);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(HttpServletRequest req,
                               @PathVariable Long id) {

        Long userId = getUserId(req);
        addressService.deleteAddress(userId, id);
        return Result.success();
    }

    @GetMapping
    public Result<List<AddressVO>> list(HttpServletRequest req) {
        Long userId = getUserId(req);
        return Result.success(addressService.listAddresses(userId));
    }
}
