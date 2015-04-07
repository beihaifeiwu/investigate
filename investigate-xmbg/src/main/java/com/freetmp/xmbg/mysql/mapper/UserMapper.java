/**                                                                          
 * Copyright 2015-2015 the original author or authors.                         
 *                                                                           
 *       HaHa,I have the right to do anything!                               
 */
package com.freetmp.xmbg.mysql.mapper;

import com.freetmp.xmbg.mysql.entity.User;
import com.freetmp.xmbg.mysql.entity.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int batchInsert(List<User> list);

    int batchUpdate(List<User> list);

    int upsert(@Param("record") User record, @Param("array") String[] array);

    int batchUpsert(@Param("records") List<User> list, @Param("array") String[] array);
}