package ojt.management.data.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "attachment")
@EntityListeners(AuditingEntityListener.class)
public class Attachment implements Serializable {
    @Id
    @Column(name = "key")
    private String key;

    @Column(name = "name", nullable = false)
    private String name;

    @CreatedDate
    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "account_id")
    private Long accountId;

    @ManyToOne
    private Application application;

    @OneToOne
    private Account account;

    public Attachment(String key, String name, Long accountId) {
        this.key = key;
        this.name = name;
        this.accountId = accountId;
    }

    public Attachment(String key) {
        this.key = key;
    }
}
