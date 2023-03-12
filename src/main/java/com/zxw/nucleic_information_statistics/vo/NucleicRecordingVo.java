package com.zxw.nucleic_information_statistics.vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class NucleicRecordingVo extends SelUserVo{

    private Integer recordingId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String recordingTime;

    private String recordingState;

    private String recordingStateName;

    private String recordingImg;

    private Integer recordingUserId;

    private String recordingUserName;
}
