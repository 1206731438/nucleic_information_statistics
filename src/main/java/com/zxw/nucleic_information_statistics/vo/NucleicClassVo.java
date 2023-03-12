package com.zxw.nucleic_information_statistics.vo;

import com.zxw.nucleic_information_statistics.entity.NucleicClassEntity;
import lombok.Data;

@Data
public class NucleicClassVo extends NucleicClassEntity {

    private String academyName;

    private Integer studentNumber;
}
