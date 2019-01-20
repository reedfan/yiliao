package com.reed.ustc.controller;

import com.reed.ustc.common.CommonRet;
import com.reed.ustc.pojo.Patient;
import com.reed.ustc.service.PatientService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * created by reedfan on 2019/1/20 0020
 */

@RestController("/patient")
public class PatientController {
    private static final Logger logger = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    private PatientService patientService;

    @ApiOperation(value = "添加一个病人")
    @PostMapping("/add")
    public CommonRet addPatient(Patient patient){
        CommonRet commonRet = new CommonRet();
        try {
            patientService.addPatient(patient);
            commonRet.setCode(CommonRet.SUCCESS);
            commonRet.setMsg("添加病人基本信息成功");
        } catch (Exception e) {
            logger.error("添加病人基本信息失败"+e.toString());
            commonRet.setCode(CommonRet.ERROR);
            commonRet.setMsg("添加病人基本信息失败");
        }
        return commonRet;
    }

    @ApiOperation(value = "获取病人基本信息")
    @GetMapping("/get")
    public List<Patient> patientList(String patientName){
        List<Patient> patientList = new ArrayList<>();

        try {
            patientList = patientService.getPatientListByName(patientName);

        } catch (Exception e) {
            logger.error("添加病人基本信息失败"+e.toString());
        }
        return patientList;
    }


}
