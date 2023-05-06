package com.hy.flyy.controller;

import com.hy.flyy.dto.BookDto;
import com.hy.flyy.dto.UserDto;
import com.hy.flyy.entity.Book;
import com.hy.flyy.entity.User;
import com.hy.flyy.service.BookService;
import com.hy.flyy.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 黄勇
 * @since 2023/5/4
 */
@RestController
@RequestMapping("/book")
@Slf4j
@Api(value = "Book控制层", tags = "书本相关接口")
@CrossOrigin
public class BookController {

    @Autowired
    private BookService bookService;

    @ApiOperation("添加书本")
    @PostMapping
    public R addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @PostMapping("/page")
    @ApiOperation("分页条件查询书本")
    public R conditionalPaginationQuery(@RequestBody BookDto bookDto) {
        return bookService.conditionalPaginationQuery(bookDto);
    }

    @DeleteMapping
    @ApiOperation("删除书本")
    public R deleteUser(@RequestBody List<Book> ids) {
        return bookService.delete(ids);
    }

    @PutMapping
    @ApiOperation("修改书本信息")
    public R update(@RequestBody Book book) {
        return bookService.update(book);
    }

}
