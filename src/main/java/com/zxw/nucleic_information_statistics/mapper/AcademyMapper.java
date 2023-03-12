package com.zxw.nucleic_information_statistics.mapper;

import com.zxw.nucleic_information_statistics.entity.NucleicAcademyEntity;
import com.zxw.nucleic_information_statistics.vo.NucleicAcademyVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AcademyMapper {
    /**
     * 分页查询
     * @param search
     * @return
     */
    List<NucleicAcademyVo> selectPage(@Param("search") String search);

    /**
     * 根据名称查询
     * @param academyName
     * @return
     */
    List<NucleicAcademyEntity> selectByName(@Param("academyName") String academyName);

    /**
     * 添加学院
     * @param academyEntity
     * @return
     */
    Integer insert(@Param("academyEntity") NucleicAcademyEntity academyEntity);

    /**
     * 修改
     * @param academyEntity
     * @return
     */
    Integer update(@Param("academyEntity") NucleicAcademyEntity academyEntity);

    /**
     * 删除/批量删除
     * @param academyIds
     * @return
     */
    Integer del(@Param("academyIds") List<Integer> academyIds);
}
