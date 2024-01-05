package shop.mtcoding.bank.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.bank.domain.account.Account;
import shop.mtcoding.bank.domain.account.AccountRepository;
import shop.mtcoding.bank.domain.user.User;
import shop.mtcoding.bank.domain.user.UserRepository;
import shop.mtcoding.bank.dto.account.AccountReqDto;
import shop.mtcoding.bank.dto.account.AccountResDto;
import shop.mtcoding.bank.handler.ex.CustomApiException;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountService {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;



    public void 계좌목록보기_유저별(Long userId){
        User userPS = userRepository.findById(userId).orElseThrow(
                ()-> new CustomApiException("유저를 찾을 수 없습니다."));


        //유저의 모든 계좌목록
                List<Account> accountListPS = accountRepository.findByUser_id(userId);
    }

    @Getter
    @Setter
    public static class AccountListRespDto{
        private String fullname;
        private List<AccountDto> accounts = new ArrayList<>();

        public AccountListRespDto(User user, List<Account> accounts){
            this.fullname = user.getFullname();
          //  this.accounts = accounts.stream().map((account)-> new AccountDto(account)).collect(Collectors.toList());
            this.accounts = accounts.stream().map(AccountDto::new).collect(Collectors.toList());

        }
        @Setter
        @Getter
        public class AccountDto{
            private Long id;
            private Long number;
            private  Long balance;

            public AccountDto(Account account){
                this.id = account.getId();
                this.number = account.getNumber();
                this.balance = account.getBalance();
            }

        }
    }
    @Transactional
    public AccountResDto.AccountSaveRespDto 계좌등록(AccountReqDto.AccountSaveReqDto accountSaveReqDto, Long userId){

        User userPS = userRepository.findById(userId).orElseThrow(
                () -> new CustomApiException("유저를 찾을 수 없습니다")
        );
        Optional<Account> accountOP= accountRepository.findByNumber(accountSaveReqDto.getNumber());
        if(accountOP.isPresent()){
            throw new CustomApiException("해당계좌가 이미 존재합니다.");
        }

        Account accountPS = accountRepository.save(accountSaveReqDto.toEntity(userPS));
        return new AccountResDto.AccountSaveRespDto(accountPS);
    }




}