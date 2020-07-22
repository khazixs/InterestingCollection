package com.ic.learn.dao;

import com.ic.learn.entity.Table;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("tableDao")
public interface TableDao {
    Table selectById(@Param("id") Integer id);

    Table selectByName(@Param("name") String name);

    Integer insertNewData(@Param("table") Table table);

}
