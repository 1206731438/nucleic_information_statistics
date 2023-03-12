package com.zxw.nucleic_information_statistics.publicClass;

import lombok.Data;

import java.util.List;

@Data
public class Page {

    private Integer currentPage;

    private Integer pageSize;

    private String userType;

    private String recordingTime;

    private List<Integer> classIds;

    private String search;
}
