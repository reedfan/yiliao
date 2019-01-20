package com.reed.ustc.service.impl;

import com.reed.ustc.mapper.PatientMapper;
import com.reed.ustc.pojo.Patient;
import com.reed.ustc.pojo.PatientExample;
import com.reed.ustc.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * created by reedfan on 2019/1/20 0020
 */
@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientMapper patientMapper;

    @Override
    public int addPatient(Patient patient) {
        int id = patientMapper.insertSelective(patient);
        return id;
    }

    @Override
    public List<Patient> getPatientListByName(String patienName) {
        PatientExample patientExample = new PatientExample();
        PatientExample.Criteria criteria = patientExample.createCriteria();
        criteria.andNameEqualTo(patienName);
        List<Patient> list = patientMapper.selectByExample(patientExample);
        return list;
    }
}
