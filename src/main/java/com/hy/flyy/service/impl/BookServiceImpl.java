package com.hy.flyy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.flyy.mapper.BookMapper;
import com.hy.flyy.entity.Book;
import com.hy.flyy.service.BookService;
import org.springframework.stereotype.Service;

/**
 * (Book)表服务实现类
 *
 * @author 黄勇
 * @since 2023-04-25 17:21:06
 */
@Service("bookService")
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

}

