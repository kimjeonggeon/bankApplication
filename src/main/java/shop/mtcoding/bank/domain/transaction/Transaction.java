package shop.mtcoding.bank.domain.transaction;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import shop.mtcoding.bank.domain.account.Account;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor//스프링이 user 객체 생성 시 빈 생성자로 new를 하기 때문
@Getter
@EntityListeners(AuditingEntityListener.class)
@Table(name= "transaction_tb")
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Account withdrawalAccount;
    @ManyToOne(fetch = FetchType.LAZY)
    private Account depositAccount;

    private Long amount;

    private Long withdrawAccountBalance;
    private Long depositAccountBalance;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionEnum gubun;

    //계좌가 사라져도 로그는 남아야한다
    private String sender;
    private String receiver;
    private String tel;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updateAt;

    @Builder
    public Transaction(Long id, Account withdrawalAccount, Account depositAccount, Long amount, Long withdrawAccountBalance, Long depositAccountBalance, TransactionEnum gubun, String sender, String receiver, String tel, LocalDateTime createdAt, LocalDateTime updateAt) {
        this.id = id;
        this.withdrawalAccount = withdrawalAccount;
        this.depositAccount = depositAccount;
        this.amount = amount;
        this.withdrawAccountBalance = withdrawAccountBalance;
        this.depositAccountBalance = depositAccountBalance;
        this.gubun = gubun;
        this.sender = sender;
        this.receiver = receiver;
        this.tel = tel;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }

}
