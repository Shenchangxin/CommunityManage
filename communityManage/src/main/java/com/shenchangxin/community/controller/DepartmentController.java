package com.shenchangxin.community.controller;

import com.shenchangxin.community.entity.Result;
import com.shenchangxin.community.entity.StatusCode;
import com.shenchangxin.community.pojo.Department;
import com.shenchangxin.community.service.DepartmentService;
import com.shenchangxin.community.utils.FormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    private FormatUtil formatUtil;

    @Autowired
    private DepartmentService departmentService;


    @GetMapping("/getAllDepartment")
    public Result getAllDepartment(){
        try{
            List<Department> departmentList = departmentService.getAllDepartment();
            return Result.create(StatusCode.OK,"查询数据成功",departmentList);
        }catch (RuntimeException e){
            return Result.create(StatusCode.ERROR,"查询数据失败"+e.getMessage());
        }
    }

    @PostMapping("/addDepartment")
    public Result addDepartment(@RequestBody Department department){
        if (!formatUtil.checkObjectNull(department)) {

            return Result.create(StatusCode.ERROR,"参数为空,请重新填写");
        }
        try {
            departmentService.addDepartment(department);
            return Result.create(StatusCode.OK,"插入数据成功");
        }catch (RuntimeException e){
            return Result.create(StatusCode.ERROR,"插入数据失败"+e.getMessage());
        }
    }

    @PostMapping("/updateDepartment")
    public Result updateDepartment(Department department){
        if (!formatUtil.checkObjectNull(department)){
            return Result.create(StatusCode.ERROR,"参数值不合法，请重新填写");
        }
        try{
            departmentService.updateDepartment(department);
            return Result.create(StatusCode.OK,"更新数据成功");
        }catch (RuntimeException e){
            return Result.create(StatusCode.ERROR,"插入数据出错");
        }
    }


    @GetMapping("/getDepartment/{id}")
    public Result getDepartmentById(@PathVariable("id")Integer id){
        if (!formatUtil.checkPositive(id)){
            return Result.create(StatusCode.ERROR,"参数值欸空，请检查参数");
        }
        try{
            Department department = departmentService.findDepartmentById(id);
            return Result.create(StatusCode.OK,"查询成功",department);
        }catch (RuntimeException e){
            return Result.create(StatusCode.ERROR,"查询失败"+e.getMessage());
        }
    }

    @DeleteMapping("/deleteDepartment/{id}")
    public Result deleteDepartmentById(@PathVariable("id") Integer id){
        if (!formatUtil.checkPositive(id)){
            return Result.create(StatusCode.ERROR,"参数值为空，请检查参数");
        }
        try{
            departmentService.deleteDepartmentById(id);
            return Result.create(StatusCode.OK,"删除成功");
        }catch (RuntimeException e){
            return Result.create(StatusCode.ERROR,"插入数据失败"+e.getMessage());
        }
    }

}
