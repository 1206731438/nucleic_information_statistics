package com.zxw.nucleic_information_statistics.entity;

import lombok.Data;

@Data
public class NucleicNoticeEntity {

    private Integer noticeId;

    private String noticeContent;

    private String noticeTime;

    private Integer noticeUserId;

    private String noticeClassId;
}
