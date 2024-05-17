package com.iKeeper.homepage.domain.introducion.controller;

import com.iKeeper.homepage.domain.introducion.dto.AwardRequest;
import com.iKeeper.homepage.domain.introducion.dto.HistoryRequest;
import com.iKeeper.homepage.domain.introducion.dto.HyperlinkRequest;
import com.iKeeper.homepage.domain.introducion.dto.IntroduceRequest;
import com.iKeeper.homepage.domain.introducion.entity.Award;
import com.iKeeper.homepage.domain.introducion.entity.History;
import com.iKeeper.homepage.domain.introducion.entity.Hyperlink;
import com.iKeeper.homepage.domain.introducion.service.IntroduceService;
import com.iKeeper.homepage.global.error.CustomException;
import com.iKeeper.homepage.global.error.ErrorCode;
import com.iKeeper.homepage.global.httpStatus.DefaultRes;
import com.iKeeper.homepage.global.httpStatus.ResponseMessage;
import com.iKeeper.homepage.global.httpStatus.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/introduces")
@RequiredArgsConstructor
public class IntroduceController {

    private final IntroduceService introduceService;

    @GetMapping(value = "/introduce")
    public ResponseEntity searchIntroduce() {

        return new ResponseEntity(DefaultRes.res(StatusCode.OK,
                ResponseMessage.POST_POST_CATEGORYLARGE, introduceService.searchIntroduce()), HttpStatus.OK);
    }

    @GetMapping(value = "/hyperlink")
    public ResponseEntity hyperlinkList() {

        return new ResponseEntity(DefaultRes.res(StatusCode.OK,
                ResponseMessage.POST_POST_CATEGORYLARGE, introduceService.hyperlinkList()), HttpStatus.OK);
    }

    @GetMapping(value = "/history")
    public ResponseEntity historyList() {

        return new ResponseEntity(DefaultRes.res(StatusCode.OK,
                ResponseMessage.POST_POST_CATEGORYLARGE, introduceService.historyList()), HttpStatus.OK);
    }

    @GetMapping(value = "/award")
    public ResponseEntity awardList() {

        return new ResponseEntity(DefaultRes.res(StatusCode.OK,
                ResponseMessage.POST_POST_CATEGORYLARGE, introduceService.awardList()), HttpStatus.OK);
    }


    @PostMapping(value = "/hyperlink")
    public ResponseEntity createHyperlink(@RequestBody @Valid HyperlinkRequest hyperlinkRequest,
                                          BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new CustomException("error", ErrorCode.USER_INVALID_VALUE);
        }

        Hyperlink hyperlink = Hyperlink.createHyperlink(hyperlinkRequest);
        introduceService.createHyperlink(hyperlink);
        return new ResponseEntity(DefaultRes.res(StatusCode.CREATED,
                ResponseMessage.POST_POST_CATEGORYLARGE), HttpStatus.CREATED);
    }

    @PostMapping(value = "/history")
    public ResponseEntity createHistory(@RequestBody @Valid HistoryRequest historyRequest,
                                          BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new CustomException("error", ErrorCode.USER_INVALID_VALUE);
        }

        History history = History.createHistory(historyRequest);
        introduceService.createHistory(history);
        return new ResponseEntity(DefaultRes.res(StatusCode.CREATED,
                ResponseMessage.POST_POST_CATEGORYLARGE), HttpStatus.CREATED);
    }

    @PostMapping(value = "/award")
    public ResponseEntity createAward(@RequestBody @Valid AwardRequest awardRequest,
                                        BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new CustomException("error", ErrorCode.USER_INVALID_VALUE);
        }

        Award award = Award.createAward(awardRequest);
        introduceService.createAward(award);
        return new ResponseEntity(DefaultRes.res(StatusCode.CREATED,
                ResponseMessage.POST_POST_CATEGORYLARGE), HttpStatus.CREATED);
    }

    @PatchMapping(value = "/introduce")
    public ResponseEntity updateIntroduce(@RequestBody @Valid IntroduceRequest introduceRequest,
                                          BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new CustomException("error", ErrorCode.USER_INVALID_VALUE);
        }

        introduceService.updateIntroduce(introduceRequest);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.POST_PATCH), HttpStatus.OK);
    }

    @PatchMapping(value = "/history/{id}")
    public ResponseEntity updateIntroduce(@PathVariable Long id, @RequestBody @Valid HistoryRequest historyRequest,
                                          BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new CustomException("error", ErrorCode.USER_INVALID_VALUE);
        }

        introduceService.updateHistory(id, historyRequest);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.POST_PATCH), HttpStatus.OK);
    }

    @PatchMapping(value = "/award/{id}")
    public ResponseEntity updateAward(@PathVariable Long id, @RequestBody @Valid AwardRequest awardRequest,
                                          BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new CustomException("error", ErrorCode.USER_INVALID_VALUE);
        }

        introduceService.updateAward(id, awardRequest);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.POST_PATCH), HttpStatus.OK);
    }

    @DeleteMapping(value = "/hyperlink/{id}")
    public ResponseEntity deleteHyperlink(@PathVariable Long id) {
        introduceService.deleteHyperlink(id);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.POST_PATCH), HttpStatus.OK);
    }
}
