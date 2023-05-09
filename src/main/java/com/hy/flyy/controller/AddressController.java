package com.hy.flyy.controller;

import com.hy.flyy.entity.Address;
import com.hy.flyy.service.AddressService;
import com.hy.flyy.utils.R;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 黄勇
 * @since 2023/5/9
 */
@RestController
@RequestMapping("address")
@CrossOrigin
public class AddressController {

    private AddressService addressService;

    @Autowired
    public void setAddressService(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public R<?> addAddress(@RequestBody Address address) {
        return addressService.add(address);
    }

    @GetMapping
    public R<?> selectAll() {
        return addressService.selectAll();
    }

    @PutMapping
    public R<?> updateOne(@RequestBody Address address) {
        return addressService.updateOne(address);
    }

    @DeleteMapping("/{id}")
    public R<?> deleteOne(@PathVariable Integer id) {
        return addressService.deleteById(id);
    }
}
