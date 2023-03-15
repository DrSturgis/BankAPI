package com.sturgis.bankapi.service;

import com.sturgis.bankapi.entity.Account;
import com.sturgis.bankapi.entity.HistoryTransfer;
import com.sturgis.bankapi.repository.HistoryTransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryTransferService {
    @Autowired
    private HistoryTransferRepository historyTransferRepository;
    @Autowired
    private AccountService accountService;

    //@Transactional
    public void newTransfer(Long sender, double value, Long receiver){
        Account senderAccount = accountService.findAccountById(sender).get();
        Account receiverAccount = accountService.findAccountById(receiver).get();
        HistoryTransfer transfer = new HistoryTransfer();
        transfer.setSender(senderAccount);
        transfer.setReceiver(receiverAccount);
        transfer.setAmount(value);
        historyTransferRepository.save(transfer);
    }

    public List<HistoryTransfer> listAllTransfer(){
        return historyTransferRepository.findAll();
    }

    public ResponseEntity<?> listTransferSenderByIdAccount(Long id){
        Account account = accountService.findAccountById(id).get();
        List<HistoryTransfer> history = historyTransferRepository.findBySender(account);
        if (history.isEmpty()){
            return ResponseEntity.badRequest().body("This Account does not have transactions");
        }else {
            return ResponseEntity.ok(history);
        }
    }

    public ResponseEntity<?> listTransferReceiverByIdAccount(Long id){
        Account account = accountService.findAccountById(id).get();
        List<HistoryTransfer> history = historyTransferRepository.findByReceiver(account);
        if (history.isEmpty()){
            return ResponseEntity.badRequest().body("This Account does not have transactions");
        }else {
            return ResponseEntity.ok(history);
        }
    }

}
