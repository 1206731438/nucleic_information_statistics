package com.zxw.nucleic_information_statistics.vo;

import com.zxw.nucleic_information_statistics.entity.UserEntity;
import lombok.Data;

import java.util.List;

@Data
public class SelUserVo extends UserEntity {

    private List<String> className;

    private String academyName;

    private String userTypeName;

    private String token;
}
