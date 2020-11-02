package com.geekerstar.mapper;

import com.geekerstar.entity.BsCity;
import com.geekerstar.entity.BsCityExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BsCityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bs_city
     *
     * @mbg.generated
     */
    long countByExample(BsCityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bs_city
     *
     * @mbg.generated
     */
    int deleteByExample(BsCityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bs_city
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bs_city
     *
     * @mbg.generated
     */
    int insert(BsCity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bs_city
     *
     * @mbg.generated
     */
    int insertSelective(BsCity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bs_city
     *
     * @mbg.generated
     */
    List<BsCity> selectByExample(BsCityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bs_city
     *
     * @mbg.generated
     */
    BsCity selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bs_city
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") BsCity record, @Param("example") BsCityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bs_city
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") BsCity record, @Param("example") BsCityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bs_city
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(BsCity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bs_city
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(BsCity record);
}
