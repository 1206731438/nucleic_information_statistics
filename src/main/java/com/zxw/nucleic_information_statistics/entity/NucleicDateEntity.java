package com.zxw.nucleic_information_statistics.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Data
public class NucleicDateEntity {

    private Integer dateId;

    private Date dateTime;

    private String dateTimeStr;

    private String datePlace;
}
