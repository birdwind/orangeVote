package com.orange.orange_vote.entity.model;

import com.orange.orange_vote.base.annotation.I18nPrefix;
import com.orange.orange_vote.base.repo.AbstractModel;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "`member`")
@I18nPrefix(value = "Member")
public class Member extends AbstractModel {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "member_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer memberId;

    @Column(name = "member_uuid", updatable = false, nullable = false)
    private String memberUuid;

    @Column(name = "member_no", updatable = false, nullable = false)
    private String memberNo;

    @Column(name = "username", updatable = false, nullable = false)
    private String username;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "orange_id", updatable = false, nullable = false)
    private String orangeId;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "school")
    private String school;

    @CreationTimestamp
    @Column(name = "create_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @UpdateTimestamp
    @Column(name = "update_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    @Column(name = "token", updatable = false, nullable = false, unique = true)
    private String token;

    @Column(name = "session")
    private String session;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @Override
    public Integer getId() {
        return this.memberId;
    }
}
