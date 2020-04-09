package com.orange.orange_vote.entity.model;

import com.orange.orange_vote.base.annotation.I18nPrefix;
import com.orange.orange_vote.base.repo.AbstractModel;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.JoinFormula;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "`vote_option`")
@I18nPrefix(value = "VoteOption")
public class VoteOption extends AbstractModel {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "option_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer voteOptionId;

    @Column(name = "option_uuid", updatable = false, nullable = false)
    private String voteOptionUuid;

    @Column(name = "option_value", nullable = false)
    private String voteOptionValue;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator", nullable = false)
    private Member creator;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vote_id", updatable = false, nullable = false)
    private Vote vote;

    @CreationTimestamp
    @Column(name = "create_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @UpdateTimestamp
    @Column(name = "update_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = MemberVoteOptionRelate.class, mappedBy = "voteOption")
    @Where(clause = "status = true")
    private List<MemberVoteOptionRelate> memberVoteOptionRelates;

    @Formula(value = "(SELECT COUNT(*) FROM member_vote_option_relate AS mvor "
        + "WHERE mvor.status = 1 AND mvor.vote_option_id = option_id)")
    private BigDecimal selectedCount;

    @Override
    public Integer getId() {
        return this.voteOptionId;
    }
}
