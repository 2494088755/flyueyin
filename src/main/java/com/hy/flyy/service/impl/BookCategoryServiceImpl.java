package com.hy.flyy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.flyy.mapper.BookCategoryMapper;
import com.hy.flyy.entity.BookCategory;
import com.hy.flyy.service.BookCategoryService;
import org.springframework.stereotype.Service;

/**
 * (BookCategory)表服务实现类
 *
 * @author 黄勇
 * @since 2023-04-25 17:21:06
 */
@Service("bookCategoryService")
public class BookCategoryServiceImpl extends ServiceImpl<BookCategoryMapper, BookCategory> implements BookCategoryService {

}

