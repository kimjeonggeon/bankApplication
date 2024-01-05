package shop.mtcoding.bank.domain.account;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long> {
    Optional<Account> findByNumber(Long number);
    //개인 계정 id로 계정 찾기
    //select * from account wher usere_id =:id
    List<Account> findByUser_id(Long id);
}
