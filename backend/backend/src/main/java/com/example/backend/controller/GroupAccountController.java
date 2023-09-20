package com.example.backend.controller;

import com.example.backend.dto.Response;
import com.example.backend.dto.groupaccount.AcceptGroupAccountDto;
import com.example.backend.dto.groupaccount.GroupAccountDto;
import com.example.backend.dto.groupaccount.RegistGroupAccountDto;
import com.example.backend.entity.Member;
import com.example.backend.security.UserDetailsImpl;
import com.example.backend.service.GroupAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class GroupAccountController {

    private final GroupAccountService groupAccountService;

    @GetMapping("/group/account/{groupAccountId}")
    public Response<GroupAccountDto> getGroupAccount(@PathVariable String groupAccountId) {
        GroupAccountDto response = groupAccountService.findByGroupAccountId(groupAccountId);
        return new Response<>(200, "그룹 계좌 조회 완료", response);
    }

    @PostMapping("/group/account")
    public Response registGroupAccount(@RequestBody RegistGroupAccountDto.Request request,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Member member = userDetails.getMember();
        String response = groupAccountService.save(request, member);
        return new Response<>(201, "계좌 생성 완료", response);
    }

    @PostMapping("/group/account/invite/agree")
    public Response<AcceptGroupAccountDto.Response> acceptInvite(@RequestBody AcceptGroupAccountDto.Request request,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Member member = userDetails.getMember();
        AcceptGroupAccountDto.Response response = groupAccountService.acceptInvite(request, member);
        return new Response<>(201, "그룹 계좌 가입 완료", response);
    }
}