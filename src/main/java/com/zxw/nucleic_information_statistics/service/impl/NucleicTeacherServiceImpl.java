package com.zxw.nucleic_information_statistics.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxw.nucleic_information_statistics.entity.UserEntity;
import com.zxw.nucleic_information_statistics.mapper.NucleicTeacherMapper;
import com.zxw.nucleic_information_statistics.mapper.UserMapper;
import com.zxw.nucleic_information_statistics.publicClass.Page;
import com.zxw.nucleic_information_statistics.service.NucleicTeacherService;
import com.zxw.nucleic_information_statistics.vo.SelUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NucleicTeacherServiceImpl implements NucleicTeacherService {

    @Autowired
    private NucleicTeacherMapper teacherMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo<SelUserVo> selectPage(Page page) {
        if (page.getCurrentPage() != null && page.getPageSize() != null) {
            PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
        }
        List<SelUserVo> selUserVos = teacherMapper.selectPage(page.getSearch());
        for (SelUserVo selUserVo : selUserVos) {
            if (selUserVo.getUserClassId() != null && !selUserVo.getUserClassId().equals("")) {
                selUserVo.setClassName(teacherMapper.selectClasses(selUserVo.getUserClassId()));
            }
        }
        return new PageInfo<>(selUserVos);
    }

    @Override
    public Integer insert(UserEntity user) {
        //根据学号查询学生是否存在
        List<SelUserVo> userEntityList = userMapper.selUserByNumber(user.getUserNumber());
        if (userEntityList != null && userEntityList.size() != 0) {
            return 2;
        }
        return userMapper.insert(user);
    }

    @Override
    public Integer update(UserEntity userEntity) {
        //根据学号查询是否存在
        List<SelUserVo> userEntityList = userMapper.selUserByNumber(userEntity.getUserNumber());
        if (userEntityList != null && userEntityList.size() != 0) {
            SelUserVo user = userEntityList.get(0);
            if (user.getUserId() != userEntity.getUserId()) {
                return 2;
            }
        }
        return userMapper.update(userEntity);
    }

    @Override
    public Integer del(List<Integer> userIds) {
        return userMapper.del(userIds);
    }
}
