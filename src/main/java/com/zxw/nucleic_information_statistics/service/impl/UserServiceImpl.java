package com.zxw.nucleic_information_statistics.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxw.nucleic_information_statistics.entity.NucleicDateEntity;
import com.zxw.nucleic_information_statistics.entity.NucleicNoticeEntity;
import com.zxw.nucleic_information_statistics.entity.UserEntity;
import com.zxw.nucleic_information_statistics.mapper.UserMapper;
import com.zxw.nucleic_information_statistics.publicClass.Page;
import com.zxw.nucleic_information_statistics.service.UserService;
import com.zxw.nucleic_information_statistics.utils.JWTUtil;
import com.zxw.nucleic_information_statistics.utils.ResultUtil;
import com.zxw.nucleic_information_statistics.vo.NucleicAcademyOrClassVo;
import com.zxw.nucleic_information_statistics.vo.NucleicRecordingVo;
import com.zxw.nucleic_information_statistics.vo.SelUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResultUtil login(UserEntity userEntity) {
        //根据学号号查询用户是否存在
        List<SelUserVo> userEntityList = userMapper.selUserByNumber(userEntity.getUserNumber());
        if (userEntityList == null || userEntityList.size() == 0) {
            return ResultUtil.respinseFail("用户不存在");
        }
        //如果存在，判断密码是否正确
        SelUserVo user = userEntityList.get(0);
        if (!user.getUserPassword().equals(userEntity.getUserPassword())) {
            return ResultUtil.respinseFail("密码错误");
        }
        //查询班级
        if (user.getUserClassId() != null && user.getUserClassId() != "") {
            List<String> classNames = userMapper.selectClassNameById(user.getUserClassId());
            user.setClassName(classNames);
        }
        //生成token
        String token = JWTUtil.createToken(user.getUserId());
        user.setToken(token);
        return ResultUtil.respinseSuccess("登录成功",user);
    }

    @Override
    public PageInfo<NucleicRecordingVo> selectPage(Page page) {
        if (page.getCurrentPage() != null && page.getPageSize() != null) {
            PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
        }
        List<NucleicRecordingVo> nucleicRecordingVos = userMapper.selectPage(page);
        for (NucleicRecordingVo nucleicRecordingVo : nucleicRecordingVos) {
            List<String> classNames = userMapper.selectClassNameById(nucleicRecordingVo.getUserClassId());
            nucleicRecordingVo.setClassName(classNames);
        }
        return new PageInfo<>(nucleicRecordingVos);
    }

    @Override
    public Integer insert(UserEntity user) {
        //根据学号查询学生是否存在
        List<SelUserVo> userEntityList = userMapper.selUserByNumber(user.getUserNumber());
        if (userEntityList != null && userEntityList.size() != 0) {
            return 2;
        }
        user.setUserType("xs");
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
            }else {
                return userMapper.update(userEntity);
            }
        }
        return userMapper.update(userEntity);
    }

    @Override
    public Integer del(List<Integer> userIds) {
        return userMapper.del(userIds);
    }

    @Override
    @Transactional
    public Integer setNucleicTime(NucleicDateEntity nucleicDateEntity,Integer userId) {
        Integer i = 0;
        //设置核酸采集时间
        i = userMapper.setNucleicTime(nucleicDateEntity);
        if (i != 0) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
            //发布通知
            NucleicNoticeEntity nucleicNoticeEntity = new NucleicNoticeEntity();
            nucleicNoticeEntity.setNoticeContent("请同学们于"+sdf.format(nucleicDateEntity.getDateTime())+"前往"+nucleicDateEntity.getDatePlace()+
                    "完成核酸检测，并将结果上传至系统");
            nucleicNoticeEntity.setNoticeUserId(userId);
            //查询学生信息
            List<UserEntity> userEntityList = userMapper.selUserAll();
            if (userEntityList != null && userEntityList.size() !=0) {
                userMapper.insertRecording(userEntityList,nucleicDateEntity.getDateTime());
            }
            return userMapper.insertNotice(nucleicNoticeEntity,nucleicDateEntity.getDateTime());
        }
        return i;
    }

    @Override
    public List<NucleicDateEntity> selNucleicDate() {
        return userMapper.selNucleicDate();
    }

    @Override
    public List<NucleicAcademyOrClassVo> selClass(UserEntity user) {
        return userMapper.selClass(user);
    }
}
