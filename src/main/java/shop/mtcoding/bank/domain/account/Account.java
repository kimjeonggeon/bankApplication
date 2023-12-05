package shop.mtcoding.bank.domain.account;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import shop.mtcoding.bank.domain.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor//스프링이 user 객체 생성 시 빈 생성자로 new를 하기 때문
@Getter
@EntityListeners(AuditingEntityListener.class)
@Table(name= "account_tb")
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false, length = 20)
    private Long number;//계좌번호
    @Column(nullable = false, length =4)
    private Long password;
    @Column(nullable = false)
    private Long balance;



    //항상 orm에서 fk의 주인은 many 쪽

    @ManyToOne(fetch = FetchType.LAZY)// account 부를 때 user를 호출 안하고 account.getuser()까지 호출안하고 뒤에 .아무필드호출() 시 발동
    private User user;
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updateAt;



}
