package com.shenchangxin.community.service.impl;


import com.shenchangxin.community.mapper.TransactionMapper;
import com.shenchangxin.community.pojo.Transaction;
import com.shenchangxin.community.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionMapper transactionMapper;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addTransaction(Transaction transaction) {

        transactionMapper.addTransaction(transaction);

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteTransaction(Integer id) {

        transactionMapper.deleteTransactionById(id);

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateTransaction(Transaction transaction) {

        transactionMapper.updateTransaction(transaction);
    }

    @Override
    public Transaction getTransactionById(Integer id) {

        return transactionMapper.findTransactionById(id);

    }

    @Override
    public List<Transaction> getAllTransaction() {
        return transactionMapper.findAllTransaction();
    }
}
