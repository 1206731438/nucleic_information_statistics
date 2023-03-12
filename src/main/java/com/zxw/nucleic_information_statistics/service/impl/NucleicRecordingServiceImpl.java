package com.zxw.nucleic_information_statistics.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxw.nucleic_information_statistics.entity.NucleicRecordingEntity;
import com.zxw.nucleic_information_statistics.mapper.NucleicRecordingMapper;
import com.zxw.nucleic_information_statistics.publicClass.Page;
import com.zxw.nucleic_information_statistics.service.NucleicRecordingService;
import com.zxw.nucleic_information_statistics.vo.SelNucleicRecordingVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NucleicRecordingServiceImpl implements NucleicRecordingService {

    @Autowired
    private NucleicRecordingMapper recordingMapper;

    @Override
    public PageInfo<SelNucleicRecordingVo> selectPage(Page page) {
        if (page.getCurrentPage() != null && page.getPageSize() != null) {
            PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
        }
        List<SelNucleicRecordingVo> recordingVos = recordingMapper.selectPage(page);
        return new PageInfo<>(recordingVos);
    }

    @Override
    public Integer updateState(NucleicRecordingEntity recordingEntity) {
        return recordingMapper.updateState(recordingEntity);
    }
}
