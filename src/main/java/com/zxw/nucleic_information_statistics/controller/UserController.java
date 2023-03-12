package com.zxw.nucleic_information_statistics.controller;

import com.github.pagehelper.PageInfo;
import com.zxw.nucleic_information_statistics.entity.NucleicDateEntity;
import com.zxw.nucleic_information_statistics.entity.UserEntity;
import com.zxw.nucleic_information_statistics.publicClass.Page;
import com.zxw.nucleic_information_statistics.service.UserService;
import com.zxw.nucleic_information_statistics.utils.JWTUtil;
import com.zxw.nucleic_information_statistics.utils.PageUtil;
import com.zxw.nucleic_information_statistics.utils.ResultUtil;
import com.zxw.nucleic_information_statistics.vo.NucleicAcademyOrClassVo;
import com.zxw.nucleic_information_statistics.vo.NucleicRecordingVo;
import com.zxw.nucleic_information_statistics.vo.SelUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * @param userEntity
     * @return
     */
    @PostMapping("/login")
    public ResultUtil login(@RequestBody SelUserVo userEntity) {
        if (userEntity.getUserNumber() == null || userEntity.getUserNumber().equals("")){
            return ResultUtil.respinseFail("请输入学号");
        }
        if(userEntity.getUserPassword() == null || userEntity.getUserPassword().equals("")) {
            return ResultUtil.respinseFail("请输入密码");
        }
        return userService.login(userEntity);
    }

    /**
     * 查询核酸记录
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
        PageInfo<NucleicRecordingVo> pageInfo = userService.selectPage(page);
        return ResultUtil.respinseSuccess("查询成功", PageUtil.ResultPage(pageInfo));
    }

    /**
     * 添加学生信息
     * @return
     */
    @PostMapping("/insert")
    public ResultUtil insert(HttpServletRequest request, @RequestBody UserEntity user) {
        //判断是否登录
        String token = request.getHeader("token");
        if (token == null || token.equals("")) {
            return ResultUtil.respinseFail(ResultUtil.NO_LOGIN,"请先登录");
        }
        //判断登录是否过期
        if (!JWTUtil.overTime(token)) {
            return ResultUtil.respinseFail(ResultUtil.HAVE_COLLECTION,"登录已过期");
        }
        Integer i = userService.insert(user);
        if (i == 0) {
            return ResultUtil.respinseFail("添加失败");
        }else if (i == 2) {
            return ResultUtil.respinseSuccess("添加失败，学好已存在");
        }
        return ResultUtil.respinseSuccess("添加成功");
    }

    /**
     * 修改学生信息
     * @param request
     * @param user
     * @return
     */
    @PostMapping("/update")
    public ResultUtil update(HttpServletRequest request, @RequestBody UserEntity user) {
        //判断是否登录
        String token = request.getHeader("token");
        if (token == null || token.equals("")) {
            return ResultUtil.respinseFail(ResultUtil.NO_LOGIN,"请先登录");
        }
        //判断登录是否过期
        if (!JWTUtil.overTime(token)) {
            return ResultUtil.respinseFail(ResultUtil.HAVE_COLLECTION,"登录已过期");
        }
        Integer i = userService.update(user);
        if (i == 0) {
            return ResultUtil.respinseFail("修改失败");
        }else if (i == 2) {
            return ResultUtil.respinseSuccess("修改失败，学好已存在");
        }
        return ResultUtil.respinseSuccess("修改成功");
    }

    /**
     * 删除/批量删除
     * @param request
     * @param userIds
     * @return
     */
    @PostMapping("/del")
    public ResultUtil del(HttpServletRequest request, @RequestBody List<Integer> userIds) {
        //判断是否登录
        String token = request.getHeader("token");
        if (token == null || token.equals("")) {
            return ResultUtil.respinseFail(ResultUtil.NO_LOGIN,"请先登录");
        }
        //判断登录是否过期
        if (!JWTUtil.overTime(token)) {
            return ResultUtil.respinseFail(ResultUtil.HAVE_COLLECTION,"登录已过期");
        }
        Integer i = userService.del(userIds);
        if (i == 0) {
            return ResultUtil.respinseFail("删除失败");
        }
        return ResultUtil.respinseSuccess("删除成功");
    }

    /**
     * 新增核酸检测并通知
     * @param request
     * @param nucleicDateEntity
     * @return
     */
    @PostMapping("/setNucleicTime")
    public ResultUtil setNucleicTime(HttpServletRequest request, @RequestBody NucleicDateEntity nucleicDateEntity) {
        //判断是否登录
        String token = request.getHeader("token");
        if (token == null || token.equals("")) {
            return ResultUtil.respinseFail(ResultUtil.NO_LOGIN,"请先登录");
        }
        //判断登录是否过期
        if (!JWTUtil.overTime(token)) {
            return ResultUtil.respinseFail(ResultUtil.HAVE_COLLECTION,"登录已过期");
        }
        Integer userId = JWTUtil.verifyToken(token);
        Integer i = userService.setNucleicTime(nucleicDateEntity,userId);
        if (i == 0) {
            return ResultUtil.respinseFail("设置失败");
        }
        return ResultUtil.respinseSuccess("设置成功");
    }

    /**
     * 查询核酸时间下拉框
     * @param request
     * @return
     */
    @PostMapping("/selNucleicDate")
    public ResultUtil selNucleicDate(HttpServletRequest request) {
        //判断是否登录
        String token = request.getHeader("token");
        if (token == null || token.equals("")) {
            return ResultUtil.respinseFail(ResultUtil.NO_LOGIN,"请先登录");
        }
        //判断登录是否过期
        if (!JWTUtil.overTime(token)) {
            return ResultUtil.respinseFail(ResultUtil.HAVE_COLLECTION,"登录已过期");
        }
        List<NucleicDateEntity> nucleicDateEntities = userService.selNucleicDate();
        return ResultUtil.respinseSuccess("查询成功",nucleicDateEntities);
    }

    /**
     * 查询班级下拉框
     * @param request
     * @param user
     * @return
     */
    @PostMapping("/selClass")
    public ResultUtil selClass(HttpServletRequest request,@RequestBody UserEntity user) {
        //判断是否登录
        String token = request.getHeader("token");
        if (token == null || token.equals("")) {
            return ResultUtil.respinseFail(ResultUtil.NO_LOGIN,"请先登录");
        }
        //判断登录是否过期
        if (!JWTUtil.overTime(token)) {
            return ResultUtil.respinseFail(ResultUtil.HAVE_COLLECTION,"登录已过期");
        }
        List<NucleicAcademyOrClassVo> classVos = userService.selClass(user);
        return ResultUtil.respinseSuccess("查询成功",classVos);
    }

}