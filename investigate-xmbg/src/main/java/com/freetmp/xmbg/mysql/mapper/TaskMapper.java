/**                                                                          
 * Copyright 2015-2015 the original author or authors.                         
 *                                                                           
 *       HaHa,I have the right to do anything!                               
 */
package com.freetmp.xmbg.mysql.mapper;

import com.freetmp.xmbg.mysql.entity.Task;
import com.freetmp.xmbg.mysql.entity.TaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaskMapper {

    int countByExample(TaskExample example);

    int deleteByExample(TaskExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Task record);

    int insertSelective(Task record);

    List<Task> selectByExample(TaskExample example);

    Task selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Task record, @Param("example") TaskExample example);

    int updateByExample(@Param("record") Task record, @Param("example") TaskExample example);

    int updateByPrimaryKeySelective(Task record);

    int updateByPrimaryKey(Task record);

    int batchInsert(List<Task> list);

    int batchUpdate(List<Task> list);

    int upsert(@Param("record") Task record, @Param("array") String[] array);

    int batchUpsert(@Param("records") List<Task> list, @Param("array") String[] array);
}
