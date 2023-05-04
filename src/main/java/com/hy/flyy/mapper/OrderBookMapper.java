package com.hy.flyy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hy.flyy.entity.OrderBook;
import org.apache.ibatis.annotations.Mapper;

/**
 * (OrderBook)表数据库访问层
 *
 * @author 黄勇
 * @since 2023-04-25 17:21:06
 */
@Mapper
public interface OrderBookMapper extends BaseMapper<OrderBook> {

}

