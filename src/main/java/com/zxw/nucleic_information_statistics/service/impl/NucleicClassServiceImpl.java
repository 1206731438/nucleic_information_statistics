package com.zxw.nucleic_information_statistics.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxw.nucleic_information_statistics.entity.NucleicAcademyEntity;
import com.zxw.nucleic_information_statistics.entity.NucleicClassEntity;
import com.zxw.nucleic_information_statistics.mapper.NucleicClassMapper;
import com.zxw.nucleic_information_statistics.publicClass.Page;
import com.zxw.nucleic_information_statistics.service.NucleicClassService;
import com.zxw.nucleic_information_statistics.vo.NucleicAcademyOrClassVo;
import com.zxw.nucleic_information_statistics.vo.NucleicClassVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NucleicClassServiceImpl implements NucleicClassService {

    @Autowired
    private NucleicClassMapper classMapper;


    @Override
    public PageInfo<NucleicClassVo> selectPage(Page page) {
        if (page.getCurrentPage() != null && page.getPageSize() != 0) {
            PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
        }
        List<NucleicClassVo> nucleicAcademyVos = classMapper.selectPage(page.getSearch());
        return new PageInfo<>(nucleicAcademyVos);
    }

    @Override
    public Integer insert(NucleicClassEntity classEntity) {
        //根据学院名称查询学院是否存在
        List<NucleicClassEntity> classEntities = classMapper.selectByName(classEntity.getClassName());
        if (classEntities == null || classEntities.size() == 0) {
            return 2;
        }
        return classMapper.insert(classEntity);
    }

    @Override
    public Integer update(NucleicClassEntity classEntity) {
        //根据学院名称查询学院是否存在
        List<NucleicClassEntity> classEntities = classMapper.selectByName(classEntity.getClassName());
        if (classEntities != null && classEntities.size() != 0) {
            NucleicClassEntity academyEntity1 = classEntities.get(0);
            if (academyEntity1.getClassId() != academyEntity1.getClassId()) {
                return 2;
            }
        }
        return classMapper.update(classEntity);
    }

    @Override
    public Integer del(List<Integer> classIds) {
        return classMapper.del(classIds);
    }

    @Override
    public List<NucleicAcademyEntity> selAcademyList() {
        return classMapper.selAcademyList();
    }
}
