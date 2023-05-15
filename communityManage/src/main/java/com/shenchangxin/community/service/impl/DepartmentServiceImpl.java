package com.shenchangxin.community.service.impl;

import com.shenchangxin.community.mapper.DepartmentMapper;
import com.shenchangxin.community.pojo.Department;
import com.shenchangxin.community.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addDepartment(Department department) {

        departmentMapper.addDepartment(department);

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteDepartmentById(Integer id) {
        departmentMapper.deleteDepartmentById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateDepartment(Department department) {
        departmentMapper.updateDepartment(department);
    }

    @Override
    public Department findDepartmentById(Integer id) {
        return departmentMapper.findDepartmentById(id);
    }

    @Override
    public List<Department> getAllDepartment() {
        return departmentMapper.getAllDepartment();
    }
}
