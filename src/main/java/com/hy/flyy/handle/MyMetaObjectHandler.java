package com.hy.flyy.handle;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author 黄勇
 * @since 2023/4/25
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {

        Object createAt = getFieldValByName("createAt", metaObject);
        if (Objects.isNull(createAt)) {
            setFieldValByName("createAt", LocalDateTime.now(), metaObject);
        }
        Object createBy = getFieldValByName("createBy", metaObject);
        if (Objects.isNull(createBy)) {
            setFieldValByName("createBy", "admin", metaObject);
        }
        updateAndInsert(metaObject);

    }

    public void updateAndInsert(MetaObject metaObject) {
        Object updateAt = getFieldValByName("updateAt", metaObject);
        if (Objects.isNull(updateAt)) {
            setFieldValByName("updateAt", LocalDateTime.now(), metaObject);
        }
        Object updateBy = getFieldValByName("updateBy", metaObject);
        if (Objects.isNull(updateBy)) {
            setFieldValByName("updateBy", "admin", metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        updateAndInsert(metaObject);
    }


}
