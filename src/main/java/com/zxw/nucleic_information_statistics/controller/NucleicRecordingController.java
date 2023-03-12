package com.zxw.nucleic_information_statistics.controller;

import com.github.pagehelper.PageInfo;
import com.zxw.nucleic_information_statistics.entity.NucleicRecordingEntity;
import com.zxw.nucleic_information_statistics.publicClass.Page;
import com.zxw.nucleic_information_statistics.service.NucleicRecordingService;
import com.zxw.nucleic_information_statistics.utils.JWTUtil;
import com.zxw.nucleic_information_statistics.utils.PageUtil;
import com.zxw.nucleic_information_statistics.utils.ResultUtil;
import com.zxw.nucleic_information_statistics.vo.SelNucleicRecordingVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/nucleicRecording")
public class NucleicRecordingController {

    @Autowired
    private NucleicRecordingService recordingService;

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
        PageInfo<SelNucleicRecordingVo> recordingVos = recordingService.selectPage(page);
        return ResultUtil.respinseSuccess("查询成功", PageUtil.ResultPage(recordingVos));
    }

    /**
     * 修改状态
     * @param request
     * @param recordingEntity
     * @return
     */
    @PostMapping("/updateState")
    public ResultUtil updateState(HttpServletRequest request, @RequestBody NucleicRecordingEntity recordingEntity) {
        //判断是否登录
        String token = request.getHeader("token");
        if (token == null || token.equals("")) {
            return ResultUtil.respinseFail(ResultUtil.NO_LOGIN,"请先登录");
        }
        //判断登录是否过期
        if (!JWTUtil.overTime(token)) {
            return ResultUtil.respinseFail(ResultUtil.HAVE_COLLECTION,"登录已过期");
        }
        Integer i = recordingService.updateState(recordingEntity);
        if (i == 0) {
            return ResultUtil.respinseFail("操作失败");
        }
        return ResultUtil.respinseSuccess("操作成功");
    }

}
