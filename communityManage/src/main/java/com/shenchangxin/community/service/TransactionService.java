package com.shenchangxin.community.service;


import com.shenchangxin.community.pojo.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {

    void addTransaction(Transaction transaction);

    void deleteTransaction(Integer id);

    void updateTransaction(Transaction transaction);

    Transaction getTransactionById(Integer id);

    List<Transaction> getAllTransaction();
}
