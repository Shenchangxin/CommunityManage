package com.shenchangxin.community.controller;


import com.shenchangxin.community.entity.Result;
import com.shenchangxin.community.entity.StatusCode;
import com.shenchangxin.community.pojo.Transaction;
import com.shenchangxin.community.service.TransactionService;
import com.shenchangxin.community.utils.FormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/transaction")
@Controller
public class TransactionController {

    @Autowired
    private FormatUtil formatUtil;

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/addTransaction")
    public Result addTransaction(@RequestBody Transaction transaction){
        if (!formatUtil.checkObjectNull(transaction)){
            return Result.create(StatusCode.ERROR,"参数值为空，请检查");
        }
        try{
            transactionService.addTransaction(transaction);
            return Result.create(StatusCode.OK,"插入数据成功");
        }catch (RuntimeException e){
            return Result.create(StatusCode.ERROR,"插入失败");
        }
    }

    @PostMapping("/updateTransaction")
    public Result updateTransaction(@RequestBody Transaction transaction){
        if (!formatUtil.checkObjectNull(transaction)){
            return Result.create(StatusCode.ERROR,"参数值为空，请检查");
        }
        try{
            transactionService.updateTransaction(transaction);
            return Result.create(StatusCode.OK,"更新成功");
        }catch (RuntimeException e){
            return Result.create(StatusCode.ERROR,"插入数据失败");
        }
    }

    @DeleteMapping("/deleteTransaction")
    public Result deleteTransaction(@RequestParam("id") Integer id){
        if (!formatUtil.checkPositive(id)){
            return Result.create(StatusCode.ERROR,"参数值为空，请检查");
        }
        try{
            transactionService.deleteTransaction(id);
            return Result.create(StatusCode.OK,"删除成功");
        }catch (RuntimeException e){
            return Result.create(StatusCode.ERROR,"插入数据出错"+e.getMessage());
        }
    }

    @GetMapping("/getAllTransaction")
    public Result getAllTransaction(){
        try{
            List<Transaction> transactionList = transactionService.getAllTransaction();
            return Result.create(StatusCode.OK,"查询成功",transactionList);
        }catch (RuntimeException e){
            return Result.create(StatusCode.ERROR,"查询失败");
        }
    }
    @GetMapping("/getTransaction")
    public Result getTransaction(@RequestParam("id")Integer id){
        if (!formatUtil.checkPositive(id)){
            return Result.create(StatusCode.ERROR,"参数值为空，请检查");
        }
        try{
            Transaction transaction = transactionService.getTransactionById(id);
            return Result.create(StatusCode.OK,"查询成功",transaction);
        }catch (RuntimeException e){
            return Result.create(StatusCode.ERROR,"查询失败");
        }
    }


}
