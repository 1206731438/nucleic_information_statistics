package com.zxw.nucleic_information_statistics.service;

import com.github.pagehelper.PageInfo;
import com.zxw.nucleic_information_statistics.entity.NucleicDateEntity;
import com.zxw.nucleic_information_statistics.entity.UserEntity;
import com.zxw.nucleic_information_statistics.publicClass.Page;
import com.zxw.nucleic_information_statistics.utils.ResultUtil;
import com.zxw.nucleic_information_statistics.vo.NucleicAcademyOrClassVo;
import com.zxw.nucleic_information_statistics.vo.NucleicRecordingVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    /**
     * 用户登录
     * @param userEntity
     * @return
     */
    ResultUtil login(UserEntity userEntity);

    /**
     * 分页查询
     * @param page
     * @return
     */
    PageInfo<NucleicRecordingVo> selectPage(Page page);

    /**
     * 添加
     * @param user
     * @return
     */
    Integer insert(UserEntity user);

    /**
     * 修改学生信息
     * @param user
     * @return
     */
    Integer update(UserEntity user);

    /**
     * 删除/批量删除
     * @param userIds
     * @return
     */
    Integer del(List<Integer> userIds);

    /**
     * 新增核酸检测
     * @param nucleicDateEntity
     * @return
     */
    Integer setNucleicTime(NucleicDateEntity nucleicDateEntity,Integer userId);

    /**
     * 查询核算时间下拉框
     * @return
     */
    List<NucleicDateEntity> selNucleicDate();

    /**
     * 查询班级下拉框
     * @param user
     * @return
     */
    List<NucleicAcademyOrClassVo> selClass(UserEntity user);
}
