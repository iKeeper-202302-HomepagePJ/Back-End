package com.iKeeper.homepage.domain.ledger.controller;

import com.iKeeper.homepage.domain.ledger.dto.LedgerRequest;
import com.iKeeper.homepage.domain.ledger.entity.Ledger;
import com.iKeeper.homepage.domain.ledger.service.LedgerService;
import com.iKeeper.homepage.global.error.CustomException;
import com.iKeeper.homepage.global.error.ErrorCode;
import com.iKeeper.homepage.global.httpStatus.DefaultRes;
import com.iKeeper.homepage.global.httpStatus.ResponseMessage;
import com.iKeeper.homepage.global.httpStatus.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/ledgers")
@RequiredArgsConstructor
public class LedgerController {

    private final LedgerService ledgerService;

    @GetMapping(value = "")
    public ResponseEntity getLedgerList(@RequestParam(value = "page") int page) {

        Page<Ledger> paging = this.ledgerService.getLedgerList(page);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK,
                ResponseMessage.LEDGER_LIST_READ, paging), HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity createLedger(@RequestBody @Valid LedgerRequest ledgerRequest,
                                       BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new CustomException("error", ErrorCode.USER_INVALID_VALUE);
        }

        ledgerService.createLedger(ledgerRequest);
        return new ResponseEntity(DefaultRes.res(StatusCode.CREATED,
                ResponseMessage.LEDGER_POST), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "")
    public ResponseEntity deleteLedger(@PathVariable Long id) {

        ledgerService.deleteLedger(id);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.LEDGER_DELETE), HttpStatus.OK);
    }
}
