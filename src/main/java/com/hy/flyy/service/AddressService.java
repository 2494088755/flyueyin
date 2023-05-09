package com.hy.flyy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hy.flyy.entity.Address;
import com.hy.flyy.utils.R;

/**
 * @author 黄勇
 * @since 2023/5/9
 */
public interface AddressService extends IService<Address> {
    R<?> add(Address address);

    R<?> selectAll();

    R<?> deleteById(Integer id);

    R<?> updateOne(Address address);
}
