package com.zxw.nucleic_information_statistics.service;

import com.github.pagehelper.PageInfo;
import com.zxw.nucleic_information_statistics.entity.NucleicAcademyEntity;
import com.zxw.nucleic_information_statistics.entity.NucleicClassEntity;
import com.zxw.nucleic_information_statistics.publicClass.Page;
import com.zxw.nucleic_information_statistics.vo.NucleicAcademyOrClassVo;
import com.zxw.nucleic_information_statistics.vo.NucleicClassVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NucleicClassService {
    /**
     * 分页查询
     * @param page
     * @return
     */
    PageInfo<NucleicClassVo> selectPage(Page page);

    /**
     * 添加班级
     * @param classEntity
     * @return
     */
    Integer insert(NucleicClassEntity classEntity);

    /**
     * 修改班级
     * @param classEntity
     * @return
     */
    Integer update(NucleicClassEntity classEntity);

    /**
     * 删除/批量删除
     * @param classIds
     * @return
     */
    Integer del(List<Integer> classIds);

    /**
     * 查询学院下拉框
     * @return
     */
    List<NucleicAcademyEntity> selAcademyList();
}
