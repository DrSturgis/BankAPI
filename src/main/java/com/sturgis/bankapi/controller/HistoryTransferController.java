package com.sturgis.bankapi.controller;

import com.sturgis.bankapi.entity.HistoryTransfer;
import com.sturgis.bankapi.service.HistoryTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transfer")
public class HistoryTransferController {

    @Autowired
    private HistoryTransferService historyTransferService;

    @GetMapping("/list")
    public List<HistoryTransfer> listAllTransfer(){
        return historyTransferService.listAllTransfer();
    }

    @GetMapping("/sent/{id}")
    public ResponseEntity<?> listTransferSenderByIdAccount(@PathVariable("id") Long id){
        return historyTransferService.listTransferSenderByIdAccount(id);
    }

    @GetMapping("/receive/{id}")
    public ResponseEntity<?> listTransferReceiverByIdAccount(@PathVariable("id") Long id){
        return historyTransferService.listTransferReceiverByIdAccount(id);
    }

}
