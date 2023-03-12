package com.zxw.nucleic_information_statistics.service;

import com.github.pagehelper.PageInfo;
import com.zxw.nucleic_information_statistics.entity.UserEntity;
import com.zxw.nucleic_information_statistics.publicClass.Page;
import com.zxw.nucleic_information_statistics.vo.SelUserVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NucleicTeacherService {

    /**
     * 分页查询
     * @param page
     * @return
     */
    PageInfo<SelUserVo> selectPage(Page page);

    /**
     * 添加
     * @param user
     * @return
     */
    Integer insert(UserEntity user);

    /**
     * 修改
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
}
