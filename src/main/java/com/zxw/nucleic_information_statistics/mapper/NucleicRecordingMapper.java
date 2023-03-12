package com.zxw.nucleic_information_statistics.mapper;

import com.zxw.nucleic_information_statistics.entity.NucleicRecordingEntity;
import com.zxw.nucleic_information_statistics.publicClass.Page;
import com.zxw.nucleic_information_statistics.vo.SelNucleicRecordingVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NucleicRecordingMapper {
    /**
     * 分页查询
     * @param page
     * @return
     */
    List<SelNucleicRecordingVo> selectPage(@Param("page") Page page);

    /**
     * 修改状态
     * @param recordingEntity
     * @return
     */
    Integer updateState(@Param("recordingEntity") NucleicRecordingEntity recordingEntity);
}
