package com.zxw.nucleic_information_statistics.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxw.nucleic_information_statistics.entity.NucleicAcademyEntity;
import com.zxw.nucleic_information_statistics.mapper.AcademyMapper;
import com.zxw.nucleic_information_statistics.publicClass.Page;
import com.zxw.nucleic_information_statistics.service.AcademyService;
import com.zxw.nucleic_information_statistics.vo.NucleicAcademyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcademyServiceImpl implements AcademyService {

    @Autowired
    private AcademyMapper academyMapper;

    @Override
    public PageInfo<NucleicAcademyVo> selectPage(Page page) {
        if (page.getCurrentPage() != null && page.getPageSize() != 0) {
            PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
        }
        List<NucleicAcademyVo> nucleicAcademyVos = academyMapper.selectPage(page.getSearch());
        return new PageInfo<>(nucleicAcademyVos);
    }

    @Override
    public Integer insert(NucleicAcademyEntity academyEntity) {
        //根据学院名称查询学院是否存在
        List<NucleicAcademyEntity> academyEntities = academyMapper.selectByName(academyEntity.getAcademyName());
        if (academyEntities == null || academyEntities.size() == 0) {
            return 2;
        }
        return academyMapper.insert(academyEntity);
    }

    @Override
    public Integer update(NucleicAcademyEntity academyEntity) {
        //根据学院名称查询学院是否存在
        List<NucleicAcademyEntity> academyEntities = academyMapper.selectByName(academyEntity.getAcademyName());
        if (academyEntities != null && academyEntities.size() != 0) {
            NucleicAcademyEntity academyEntity1 = academyEntities.get(0);
            if (academyEntity1.getAcademyId() != academyEntity1.getAcademyId()) {
                return 2;
            }
        }
        return academyMapper.update(academyEntity);
    }

    @Override
    public Integer del(List<Integer> academyIds) {
        return academyMapper.del(academyIds);
    }
}
