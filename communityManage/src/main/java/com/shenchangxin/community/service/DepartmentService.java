package com.shenchangxin.community.service;


import com.shenchangxin.community.pojo.Department;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {

    void addDepartment(Department department);

    void deleteDepartmentById(Integer id);

    void updateDepartment(Department department);

    Department findDepartmentById(Integer id);

    List<Department> getAllDepartment();
}
