package com.orange.orange_vote.entity.model;

import com.orange.orange_vote.base.annotation.I18nPrefix;
import com.orange.orange_vote.base.repo.AbstractModel;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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

    public Member() {
        this.memberId = 0;
        this.memberUuid = UUID.randomUUID().toString();
        this.status = true;
        this.updateDate = new Date();
        this.createDate = new Date();
    }

    public Member(String orangeId, String password, String name) {
        this();
        this.orangeId = orangeId;
        this.username = orangeId;
        this.name = name;

        // 加密
        encodePasssword(password);
    }

    @Id
    @Column(name = "member_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer memberId;

    @Column(name = "member_uuid", updatable = false, nullable = false)
    private String memberUuid;

    @Column(name = "member_no", updatable = false, nullable = false)
    private String memberNo;

    @Column(name = "name", updatable = false, nullable = false)
    private String name;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "orange_id", updatable = false, nullable = false)
    private String orangeId;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "school")
    private String school;

    @Column(name = "major")
    private String major;

    @CreationTimestamp
    @Column(name = "create_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @UpdateTimestamp
    @Column(name = "update_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    @Column(name = "token", updatable = false, unique = true)
    private String token;

    @Column(name = "session")
    private String session;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = MemberTeamRelate.class, mappedBy = "member")
    @Where(clause = "status = true")
    private List<MemberTeamRelate> memberTeamRelates;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = MemberRoleRelate.class, mappedBy = "member")
    private List<MemberRoleRelate> memberRoleRelates;

    @Override
    public Integer getId() {
        return this.memberId;
    }

    public void encodePasssword(String password){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        this.password = bCryptPasswordEncoder.encode(password.trim());
    }
}
