package com.sturgis.bankapi.repository;

import com.sturgis.bankapi.entity.Account;
import com.sturgis.bankapi.entity.HistoryTransfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryTransferRepository extends JpaRepository<HistoryTransfer, Long> {

    List<HistoryTransfer> findBySender(Account source);
    List<HistoryTransfer> findByReceiver(Account receiver);
}
