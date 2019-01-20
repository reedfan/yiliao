package com.reed.ustc.service;

import com.reed.ustc.pojo.Patient;

import java.util.List;

/**
 * created by reedfan on 2019/1/20 0020
 */
public interface PatientService {
    int addPatient(Patient patient);
    List<Patient> getPatientListByName(String patienName);
}
