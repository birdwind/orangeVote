package com.orange.orange_vote.entity.model;

import com.orange.orange_vote.base.annotation.I18nPrefix;
import com.orange.orange_vote.base.repo.AbstractModel;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "`member_vote_option_relate`")
@I18nPrefix(value = "MemberVoteOptionRelate")
public class MemberVoteOptionRelate extends AbstractModel {

    private static final long serialVersionUID = 1L;

    public MemberVoteOptionRelate(){
        this.relateId = 0;
        this.status = true;
    }

    public MemberVoteOptionRelate(Member member, VoteOption voteOption){
        this();
        this.member = member;
        this.voteOption = voteOption;
        this.relateUuid = UUID.randomUUID().toString();
        this.createDate = new Date();
    }

    @Id
    @Column(name = "relate_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer relateId;

    @Column(name = "relate_uuid", updatable = false, nullable = false)
    private String relateUuid;

    @OneToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @OneToOne
    @JoinColumn(name = "vote_option_id", nullable = false)
    private VoteOption voteOption;

    @CreationTimestamp
    @Column(name = "create_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @Override
    public Integer getId() {
        return this.relateId;
    }
}
