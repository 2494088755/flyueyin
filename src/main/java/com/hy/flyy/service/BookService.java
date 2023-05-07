package com.hy.flyy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hy.flyy.dto.BookDto;
import com.hy.flyy.entity.Book;
import com.hy.flyy.utils.R;

import java.util.List;

/**
 * (Book)表服务接口
 *
 * @author 黄勇
 * @since 2023-05-04 18:07:12
 */
public interface BookService extends IService<Book> {

    R addBook(Book book);

    R conditionalPaginationQuery(BookDto bookDto);

    R delete(List<Book> ids);

    R update(Book book);

    R findOneBook(Integer id);
}

