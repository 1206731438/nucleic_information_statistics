package com.zxw.nucleic_information_statistics.mapper;

import com.zxw.nucleic_information_statistics.entity.NucleicAcademyEntity;
import com.zxw.nucleic_information_statistics.entity.NucleicClassEntity;
import com.zxw.nucleic_information_statistics.vo.NucleicAcademyOrClassVo;
import com.zxw.nucleic_information_statistics.vo.NucleicClassVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NucleicClassMapper {
    /**
     * 分页查询
     * @param search
     * @return
     */
    List<NucleicClassVo> selectPage(@Param("search") String search);

    /**
     * 根据名称查询班级
     * @param className
     * @return
     */
    List<NucleicClassEntity> selectByName(@Param("className") String className);

    /**
     * 添加班级
     * @param classEntity
     * @return
     */
    Integer insert(@Param("classEntity") NucleicClassEntity classEntity);

    /**
     * 修改班级
     * @param classEntity
     * @return
     */
    Integer update(@Param("classEntity") NucleicClassEntity classEntity);

    /**
     * 删除/批量删除
     * @param classIds
     * @return
     */
    Integer del(@Param("classIds") List<Integer> classIds);

    /**
     * 查询学院下拉框
     * @return
     */
    List<NucleicAcademyEntity> selAcademyList();
}
