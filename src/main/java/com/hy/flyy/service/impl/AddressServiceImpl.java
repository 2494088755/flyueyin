package com.hy.flyy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.flyy.entity.Address;
import com.hy.flyy.enums.ResponseCodeEnums;
import com.hy.flyy.mapper.AddressMapper;
import com.hy.flyy.service.AddressService;
import com.hy.flyy.utils.ParameterVerificationUtils;
import com.hy.flyy.utils.R;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author 黄勇
 * @since 2023/5/9
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {
    @Override
    public R<?> add(Address address) {
        if (!ParameterVerificationUtils.checkPhone(address.getPhone())) {
            return R.fail("手机号格式有误");
        }

        this.save(address);
        return R.success("添加成功");
    }

    @Override
    public R<?> selectAll() {
        return R.success(list());
    }

    @Override
    public R<?> deleteById(Integer id) {
        return R.success(this.removeById(id));
    }

    @Override
    public R<?> updateOne(Address address) {
        return R.success(this.updateById(address));
    }

    @Override
    public R<?> findOne(Integer userId) {
        LambdaQueryWrapper<Address> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Address::getUserId, userId);

        Address queryAddress = getOne(queryWrapper);

        if (Objects.isNull(queryAddress)) {
            return new R<>(ResponseCodeEnums.BAD_REQUEST);
        }

        return R.success(queryAddress);
    }

}
