package com.zxw.nucleic_information_statistics.vo;

import lombok.Data;

import java.util.List;

@Data
public class NucleicAcademyOrClassVo {

    private Integer value;

    private String label;

    private List<NucleicAcademyOrClassVo> children;
}
