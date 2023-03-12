package com.zxw.nucleic_information_statistics.mapper;

import com.zxw.nucleic_information_statistics.vo.SelUserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NucleicTeacherMapper {
    /**
     * 分页查询
     * @param search
     * @return
     */
    List<SelUserVo> selectPage(@Param("search") String search);

    /**
     * 查询班级
     * @param userClassId
     * @return
     */
    List<String> selectClasses(@Param("userClassId") String userClassId);

}
