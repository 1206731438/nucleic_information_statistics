package com.zxw.nucleic_information_statistics.service;

import com.github.pagehelper.PageInfo;
import com.zxw.nucleic_information_statistics.entity.NucleicRecordingEntity;
import com.zxw.nucleic_information_statistics.publicClass.Page;
import com.zxw.nucleic_information_statistics.vo.SelNucleicRecordingVo;
import org.springframework.stereotype.Service;

@Service
public interface NucleicRecordingService {
    /**
     * 分页查询
     * @param page
     * @return
     */
    PageInfo<SelNucleicRecordingVo> selectPage(Page page);

    /**
     * 修改状态
     * @param recordingEntity
     * @return
     */
    Integer updateState(NucleicRecordingEntity recordingEntity);
}
