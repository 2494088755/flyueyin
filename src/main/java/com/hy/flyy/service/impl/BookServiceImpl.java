package com.hy.flyy.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.flyy.dto.BookDto;
import com.hy.flyy.dto.UserDto;
import com.hy.flyy.entity.Book;
import com.hy.flyy.entity.User;
import com.hy.flyy.mapper.BookMapper;
import com.hy.flyy.service.BookService;
import com.hy.flyy.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * (Book)表服务实现类
 *
 * @author makejava
 * @since 2023-05-04 18:07:17
 */
@Service("bookService")
@Slf4j
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

    @Override
    public R addBook(Book book) {
        log.info("添加书本：{}", book);
        LambdaQueryWrapper<Book> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Book::getName, book.getName());
        Book queryBook = getOne(wrapper);
        if (Objects.equals(queryBook, book)) {
            return R.fail("已存在，请勿重复添加");
        }

        if (book.getPrice() < 0) {
            return R.fail("价格有误");
        }

        if (book.getStock() < 0) {
            return R.fail("库存有误");
        }

        return R.success(save(book));
    }

    @Override
    public R conditionalPaginationQuery(BookDto bookDto) {
        Page<Book> page = new Page<>(bookDto.getCurPage(), bookDto.getPageSize());
        LambdaQueryWrapper<Book> queryWrapper = new LambdaQueryWrapper<>();

        if (!StrUtil.isBlank(bookDto.getName())) {
            queryWrapper.like(Book::getName, bookDto.getName());
        }

        if (!StrUtil.isBlank(bookDto.getAuthor())) {
            queryWrapper.like(Book::getAuthor, bookDto.getAuthor());
        }

        if (bookDto.getCategoryId()!=null) {
            queryWrapper.eq(Book::getCategoryId, bookDto.getCategoryId());
        }

        page(page, queryWrapper);

        List<Book> books = page.getRecords();
        long total = page.getTotal();

        BookDto queryBooks = new BookDto();
        queryBooks.setBookList(books);
        queryBooks.setTotal(total);

        return R.success(queryBooks);
    }

    @Override
    public R delete(List<Book> ids) {
        log.info("删除书本{}", ids);
        return R.success(removeByIds(ids));
    }

    @Override
    public R update(Book book) {
        LambdaQueryWrapper<Book> queryWrapper = new LambdaQueryWrapper<>();
        if (!StrUtil.isBlank(book.getName())) {
            queryWrapper.like(Book::getName, book.getName());
        }

        if (!StrUtil.isBlank(book.getAuthor())) {
            queryWrapper.like(Book::getAuthor, book.getAuthor());
        }

        if (book.getCategoryId()!=null) {
            queryWrapper.eq(Book::getCategoryId, book.getCategoryId());
        }

        return R.success(updateById(book));
    }

}

