package com.zxw.nucleic_information_statistics.controller;

import com.github.pagehelper.PageInfo;
import com.zxw.nucleic_information_statistics.entity.NucleicAcademyEntity;
import com.zxw.nucleic_information_statistics.publicClass.Page;
import com.zxw.nucleic_information_statistics.service.AcademyService;
import com.zxw.nucleic_information_statistics.utils.JWTUtil;
import com.zxw.nucleic_information_statistics.utils.PageUtil;
import com.zxw.nucleic_information_statistics.utils.ResultUtil;
import com.zxw.nucleic_information_statistics.vo.NucleicAcademyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 学院管理
 */
@RestController
@RequestMapping("/academy")
public class AcademyController {

    @Autowired
    private AcademyService academyService;

    /**
     * 分页查询
     * @param request
     * @param page
     * @return
     */
    @PostMapping("/selectPage")
    public ResultUtil selectPage(HttpServletRequest request, @RequestBody Page page) {
        //判断是否登录
        String token = request.getHeader("token");
        if (token == null || token.equals("")) {
            return ResultUtil.respinseFail(ResultUtil.NO_LOGIN,"请先登录");
        }
        //判断登录是否过期
        if (!JWTUtil.overTime(token)) {
            return ResultUtil.respinseFail(ResultUtil.HAVE_COLLECTION,"登录已过期");
        }
        PageInfo<NucleicAcademyVo> pageInfo = academyService.selectPage(page);
        return ResultUtil.respinseSuccess("查询成功", PageUtil.ResultPage(pageInfo));
    }


    /**
     * 添加学院
     * @param request
     * @param academyEntity
     * @return
     */
    @PostMapping("/insert")
    public ResultUtil insert(HttpServletRequest request, @RequestBody NucleicAcademyEntity academyEntity) {
        //判断是否登录
        String token = request.getHeader("token");
        if (token == null || token.equals("")) {
            return ResultUtil.respinseFail(ResultUtil.NO_LOGIN,"请先登录");
        }
        //判断登录是否过期
        if (!JWTUtil.overTime(token)) {
            return ResultUtil.respinseFail(ResultUtil.HAVE_COLLECTION,"登录已过期");
        }
        Integer i = academyService.insert(academyEntity);
        if (i == 0) {
            return ResultUtil.respinseFail("添加失败");
        }else if (i == 2) {
            return ResultUtil.respinseFail("添加失败，学院已存在");
        }
        return ResultUtil.respinseSuccess("添加成功");
    }

    /**
     * 修改学院信息
     * @param request
     * @param academyEntity
     * @return
     */
    @PostMapping("/update")
    public ResultUtil update(HttpServletRequest request, @RequestBody NucleicAcademyEntity academyEntity) {
        //判断是否登录
        String token = request.getHeader("token");
        if (token == null || token.equals("")) {
            return ResultUtil.respinseFail(ResultUtil.NO_LOGIN,"请先登录");
        }
        //判断登录是否过期
        if (!JWTUtil.overTime(token)) {
            return ResultUtil.respinseFail(ResultUtil.HAVE_COLLECTION,"登录已过期");
        }
        Integer i = academyService.update(academyEntity);
        if (i == 0) {
            return ResultUtil.respinseFail("修改失败");
        }else if (i == 2) {
            return ResultUtil.respinseFail("修改失败，学院已存在");
        }
        return ResultUtil.respinseSuccess("修改成功");
    }

    /**
     * 删除/批量删除
     * @param request
     * @param academyIds
     * @return
     */
    @PostMapping("/del")
    public ResultUtil del(HttpServletRequest request, @RequestBody List<Integer> academyIds) {
        //判断是否登录
        String token = request.getHeader("token");
        if (token == null || token.equals("")) {
            return ResultUtil.respinseFail(ResultUtil.NO_LOGIN,"请先登录");
        }
        //判断登录是否过期
        if (!JWTUtil.overTime(token)) {
            return ResultUtil.respinseFail(ResultUtil.HAVE_COLLECTION,"登录已过期");
        }
        Integer i = academyService.del(academyIds);
        if (i == 0) {
            return ResultUtil.respinseFail("删除失败");
        }
        return ResultUtil.respinseSuccess("删除成功");
    }
}
