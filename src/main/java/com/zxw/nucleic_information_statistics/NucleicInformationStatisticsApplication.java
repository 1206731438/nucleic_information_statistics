package com.zxw.nucleic_information_statistics;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@MapperScan("com.zxw.nucleic_information_statistics.mapper")
@SpringBootApplication
@EnableTransactionManagement
public class NucleicInformationStatisticsApplication {

    public static void main(String[] args) {
        SpringApplication.run(NucleicInformationStatisticsApplication.class, args);
    }

}
