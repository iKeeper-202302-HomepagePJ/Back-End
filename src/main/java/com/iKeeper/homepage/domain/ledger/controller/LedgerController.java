package com.iKeeper.homepage.domain.ledger.controller;

import com.iKeeper.homepage.domain.file.entity.File;
import com.iKeeper.homepage.domain.file.service.FileService;
import com.iKeeper.homepage.domain.ledger.dto.LedgerRequest;
import com.iKeeper.homepage.domain.ledger.entity.Ledger;
import com.iKeeper.homepage.domain.ledger.service.LedgerService;
import com.iKeeper.homepage.domain.post.dto.request.PostRequest;
import com.iKeeper.homepage.global.error.CustomException;
import com.iKeeper.homepage.global.error.ErrorCode;
import com.iKeeper.homepage.global.httpStatus.DefaultRes;
import com.iKeeper.homepage.global.httpStatus.ResponseMessage;
import com.iKeeper.homepage.global.httpStatus.StatusCode;
import com.iKeeper.homepage.global.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/ledgers")
@RequiredArgsConstructor
public class LedgerController {

    private final LedgerService ledgerService;
    private final FileService fileService;
    private final FileUtils fileUtils;

    @GetMapping(value = "")
    public ResponseEntity getLedgerList(@RequestParam(value = "page") int page) {

        Page<Ledger> paging = this.ledgerService.getLedgerList(page);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK,
                ResponseMessage.LEDGER_LIST_READ, paging), HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity createLedger(@RequestPart("ledger") @Valid LedgerRequest ledgerRequest,
                                       @RequestPart(value = "files", required = false) List<MultipartFile> fileList,
                                       BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new CustomException("error", ErrorCode.USER_INVALID_VALUE);
        }

        Long id = ledgerService.createLedger(ledgerRequest);

        List<File> files = fileUtils.uploadFiles(fileList);
        fileService.saveFiles(id, files);

        return new ResponseEntity(DefaultRes.res(StatusCode.CREATED,
                ResponseMessage.LEDGER_POST), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "")
    public ResponseEntity deleteLedger(@PathVariable Long id) {

        ledgerService.deleteLedger(id);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.LEDGER_DELETE), HttpStatus.OK);
    }
}
