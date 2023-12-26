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
import shop.mtcoding.bank.handler.ex.CustomApiException;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountService {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    @Transactional
    public void 계좌등록(AccountSaveReqDto accountSaveReqDto, Long userId){

        User userPS = userRepository.findById(userId).orElseThrow(
                () -> new CustomApiException("유저를 찾을 수 없습니다")
        );
        Optional<Account> accountOP= accountRepository.findByNumber(accountSaveReqDto.getNumber());
        if(accountOP.isPresent()){
            throw new CustomApiException("해당계좌가 이미 존재합니다.");
        }

        Account accountPS = accountRepository.save(accountSaveReqDto.toEntity(userPS));

    }

    @Setter
    @Getter
    public static class AccountSaveRespDto{
        private Long id;
        private Long number;
        private Long balance;

        public AccountSaveRespDto(Account account) {
            this.id = account.getId();
            this.number = account.getNumber();
            this.balance = account.getBalance();
        }
    }

    @Getter
    @Setter
    public static class AccountSaveReqDto{
        @NotNull
        @Digits(integer =4, fraction = 4)
        private Long number;
        @NotNull
        @Digits(integer =4, fraction = 4)
        private Long password;

        public Account toEntity(User user){
            return Account.builder()
                    .number(number)
                    .password(password)
                    .balance(1000L)
                    .user(user)
                    .build();
        }
    }

}