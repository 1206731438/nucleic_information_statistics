package com.zxw.nucleic_information_statistics.mapper;

import com.zxw.nucleic_information_statistics.entity.NucleicDateEntity;
import com.zxw.nucleic_information_statistics.entity.NucleicNoticeEntity;
import com.zxw.nucleic_information_statistics.entity.UserEntity;
import com.zxw.nucleic_information_statistics.publicClass.Page;
import com.zxw.nucleic_information_statistics.vo.NucleicAcademyOrClassVo;
import com.zxw.nucleic_information_statistics.vo.NucleicRecordingVo;
import com.zxw.nucleic_information_statistics.vo.SelUserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * 根据学号查询用户
     * @param userNumber
     * @return
     */
    List<SelUserVo> selUserByNumber(@Param("userNumber") String userNumber);

    /**
     * 分页查询
     * @param page
     * @return
     */
    List<NucleicRecordingVo> selectPage(@Param("page") Page page);

    /**
     * 根据班级id查询班级名称
     * @param userClassId
     * @return
     */
    List<String> selectClassNameById(@Param("userClassId") String userClassId);

    /**
     * 添加
     * @param user
     * @return
     */
    Integer insert(@Param("user") UserEntity user);

    /**
     * 修改学生信息
     * @param userEntity
     * @return
     */
    Integer update(@Param("userEntity") UserEntity userEntity);

    /**
     * 删除/毗连删除
     * @param userIds
     * @return
     */
    Integer del(@Param("userIds") List<Integer> userIds);

    /**
     * 新增核酸检测
     * @param nucleicDateEntity
     * @return
     */
    Integer setNucleicTime(@Param("nucleicDateEntity") NucleicDateEntity nucleicDateEntity);

    /**
     *
     * @param nucleicNoticeEntity
     * @return
     */
    Integer insertNotice(@Param("nucleicNoticeEntity") NucleicNoticeEntity nucleicNoticeEntity,@Param("dateTime") Date dateTime);

    /**
     * 查询核酸时间下拉框
     * @return
     */
    List<NucleicDateEntity> selNucleicDate();

    /**
     * 查询班级下拉框
     * @param user
     * @return
     */
    List<NucleicAcademyOrClassVo> selClass(@Param("user") UserEntity user);

    /**
     * 查询所有学生
     * @return
     */
    List<UserEntity> selUserAll();

    /**
     * 批量插入
     * @param userEntityList
     * @param dateTime
     */
    void insertRecording(@Param("userEntityList") List<UserEntity> userEntityList,@Param("dateTime") Date dateTime);
}
