package com.hy.flyy.dto;

import com.hy.flyy.entity.Book;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author 黄勇
 * @since 2023/5/4
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BookDto extends Book{

    //每页查询数量
    private Integer pageSize;

    //当前页
    private Integer curPage;

    //总记录数
    private Long total;

    //分页查询记录封装
    private List<Book> bookList;
}
