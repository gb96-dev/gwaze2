package com.session.memo.service;

import com.session.memo.dto.CreateMemoRequest;
import com.session.memo.dto.CreateMemoResponse;
import com.session.memo.entity.Memo;
import com.session.memo.repository.MemoRepository;
import com.session.user.dto.SessionUser;
import com.session.user.entity.User;
import com.session.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;
    private final UserRepository userRepository;

    @Transactional
    public CreateMemoResponse save(SessionUser sessionUser, CreateMemoRequest request) {
        User user = userRepository.findById(sessionUser.getId()).orElseThrow(
                () -> new IllegalStateException("없는 유저")
        );

        Memo memo = new Memo(request.getText(), user);
        Memo savedMemo = memoRepository.save(memo);
        return new CreateMemoResponse(
                savedMemo.getId(),
                savedMemo.getText()
        );
    }
}
