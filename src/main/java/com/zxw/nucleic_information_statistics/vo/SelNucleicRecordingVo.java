package com.zxw.nucleic_information_statistics.vo;

import com.zxw.nucleic_information_statistics.entity.NucleicRecordingEntity;
import lombok.Data;

@Data
public class SelNucleicRecordingVo extends NucleicRecordingEntity {

    private String recordingStateName;

    private String userNumber;

    private String userName;

    private Integer userAcademyId;

    private String academyName;

    private String userClassId;

    private String className;

    private String recordingUserName;
}
