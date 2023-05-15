package com.shenchangxin.community.mapper;


import com.shenchangxin.community.pojo.Department;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DepartmentMapper {

    void addDepartment(Department department);

    void deleteDepartmentById(Integer id);

    void updateDepartment(Department department);

    Department findDepartmentById(Integer id);

    List<Department> getAllDepartment();
}
