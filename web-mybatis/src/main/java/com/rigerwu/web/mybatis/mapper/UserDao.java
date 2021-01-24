package com.rigerwu.web.mybatis.mapper;

import com.rigerwu.web.mybatis.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity com.rigerwu.web.mybatis.entity.User
 */
public interface UserDao {
    /**
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @mbg.generated
     */
    int insert(User record);

    /**
     * @mbg.generated
     */
    int insertSelective(User record);

    /**
     * @mbg.generated
     */
    User selectByPrimaryKey(Long id);

    /**
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * @mbg.generated
     */
    int updateByPrimaryKey(User record);

    List<User> queryByNameAndEmail(@Param("name") String name, @Param("email") String email);

}