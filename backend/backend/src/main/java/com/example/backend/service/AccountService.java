package com.example.backend.service;

import com.example.backend.dto.RestDto;
import com.example.backend.dto.account.*;
import com.example.backend.dto.mydata.GetMemberAccountDto;
import com.example.backend.dto.mydata.MemberAccountInfoDto;
import com.example.backend.entity.Account;
import com.example.backend.entity.Member;
import com.example.backend.repository.account.AccountCustomRepository;
import com.example.backend.repository.account.AccountRepository;
import com.example.backend.util.RedisUtil;
import com.example.backend.util.RestTemplateUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountCustomRepository accountCustomRepository;
    private final RestTemplateUtil restTemplateUtil;
    private final RedisUtil redisUtil;

    public void register(AccountRegisterDto.Request request, Member member) {

        for (AccountRegisterInfoDto info : request.getBankAccountList()) {
            System.out.println(info.toString());
            accountRepository.save(info.toAccount(member));
        }
    }

    public GetUserAccountDto.Response getAccountList(Member member) throws JsonProcessingException {
        List<Account> accountList = accountCustomRepository.findById(member.getId());
        List<String> accountNumberList = new ArrayList<>();

        for(Account account : accountList) {
            accountNumberList.add(account.getAccountNo());
        }

        System.out.println(accountNumberList.toString());

        List<MemberAccountInfoDto> memberAccountInfoDtoList = getMyDataAccount(accountNumberList, member.getUserId());

        System.out.println(memberAccountInfoDtoList.toString());

        Long sum = sumBalance(memberAccountInfoDtoList);

        return new GetUserAccountDto.Response(memberAccountInfoDtoList, sum);
    }

    public List<MemberAccountInfoDto> getMyDataAccount(List<String> accountNumberList, String memberId)
            throws JsonProcessingException {
        String token = redisUtil.getMyDataToken(memberId);

        ResponseEntity<String> response = restTemplateUtil.callMyData(token,
                new GetMemberAccountDto.Request(accountNumberList), "/my-data/member/account",
                HttpMethod.POST);
        RestDto<MemberAccountInfoDto> restDto = new RestDto<>(MemberAccountInfoDto.class, response);

        return (List<MemberAccountInfoDto>) restTemplateUtil.parseListBody(
                restDto, "bankAccountDtoList");
    }

    public Long sumBalance(List<MemberAccountInfoDto> restResponseList) {
        Long sum = 0L;
        for (MemberAccountInfoDto response : restResponseList) {
            sum += response.getAccount().getBalance();
        }

        return sum;
    }

    public GetUserAccountSimpleDto.Response getAccountSimpleList(Member member) {

//        List<Account> accountList = accountCustomRepository.findById(
//                member.getId());

        List<UserAccountSimpleDto> userAccountSimpleDtoList = new ArrayList<>();

//        for (Account account : accountList) {
//            userAccountSimpleDtoList.add(UserAccountSimpleDto.parseDto(account));
//        }

        return new GetUserAccountSimpleDto.Response(userAccountSimpleDtoList);
    }

    public GetOppositeAccountDto.Response getOppositeAccount(GetOppositeAccountDto.Request request, String memberId) throws JsonProcessingException {

        List<Account> accountList =  accountCustomRepository.findByOriginNo(request.getOriginNo());

        if(accountList.size() == 0) {
            GetOppositeAccountDto.Response response = getMyDataOppositeAccount(request, memberId);
            return response;
        }

        return null;
    }

    public GetOppositeAccountDto.Response getMyDataOppositeAccount(GetOppositeAccountDto.Request request, String memberId) throws JsonProcessingException {

        String token = redisUtil.getMyDataToken(memberId);

        ResponseEntity<String> response = restTemplateUtil.callMyData(token,
                request, "/my-data/opposite/account",
                HttpMethod.POST);

        RestDto<OppositeAccountDto> restDto = new RestDto<>(OppositeAccountDto.class, response);

        OppositeAccountDto oppositeAccountDto = (OppositeAccountDto) restTemplateUtil.parseBody(
                restDto, "oppositeAccountDto");

        return GetOppositeAccountDto.Response.builder().oppositeAccountDto(oppositeAccountDto).build();
    }
}
