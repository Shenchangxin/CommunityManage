package com.shenchangxin.community.mapper;


import com.shenchangxin.community.pojo.Transaction;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TransactionMapper {

    void addTransaction(Transaction transaction);

    void deleteTransactionById(Integer id);

    void updateTransaction(Transaction transaction);

    public Transaction findTransactionById(Integer id);

    public List<Transaction> findAllTransaction();
}
