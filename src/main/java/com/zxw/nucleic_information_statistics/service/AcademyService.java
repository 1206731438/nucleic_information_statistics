package com.zxw.nucleic_information_statistics.service;

import com.github.pagehelper.PageInfo;
import com.zxw.nucleic_information_statistics.entity.NucleicAcademyEntity;
import com.zxw.nucleic_information_statistics.publicClass.Page;
import com.zxw.nucleic_information_statistics.vo.NucleicAcademyVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AcademyService {
    /**
     * 分页查询
     * @param page
     * @return
     */
    PageInfo<NucleicAcademyVo> selectPage(Page page);

    /**
     * 添加学院
     * @param academyEntity
     * @return
     */
    Integer insert(NucleicAcademyEntity academyEntity);

    /**
     * 修改学院信息
     * @param academyEntity
     * @return
     */
    Integer update(NucleicAcademyEntity academyEntity);

    /**
     * 删除/批量删除
     * @param academyIds
     * @return
     */
    Integer del(List<Integer> academyIds);
}
