package com.session.memo.controller;

import com.session.memo.dto.CreateMemoRequest;
import com.session.memo.dto.CreateMemoResponse;
import com.session.memo.service.MemoService;
import com.session.user.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@RestController
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    @PostMapping("/memos")
    public ResponseEntity<CreateMemoResponse> create(
            @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser,
            @RequestBody CreateMemoRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(memoService.save(sessionUser, request));
    }
}
