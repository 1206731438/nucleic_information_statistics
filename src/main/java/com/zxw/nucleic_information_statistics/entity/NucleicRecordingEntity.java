package com.zxw.nucleic_information_statistics.entity;

import lombok.Data;

@Data
public class NucleicRecordingEntity {

    private Integer recordingId;

    private String recordingTime;

    private Integer userId;

    private String recordingState;

    private String recordingImg;

    private String  recordingUserId;
}
