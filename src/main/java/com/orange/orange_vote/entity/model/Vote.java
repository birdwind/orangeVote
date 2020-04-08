package com.orange.orange_vote.entity.model;

import com.orange.orange_vote.base.annotation.I18nPrefix;
import com.orange.orange_vote.base.repo.AbstractModel;
import org.hibernate.annotations.CreationTimestamp;
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
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "`vote`")
@I18nPrefix(value = "Vote")
public class Vote extends AbstractModel {

    private static final long serialVersionUID = 1L;

    public Vote(){
        this.voteId = 0;
        this.status = true;
    }

    @Id
    @Column(name = "vote_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer voteId;

    @Column(name = "vote_uuid", updatable = false, nullable = false)
    private String voteUuid;

    @Column(name = "vote_no", updatable = false, nullable = false)
    private String voteNo;

    @Column(name = "vote_name", nullable = false)
    private String voteName;

    @Column(name = "content")
    private String content;

    @Column(name = "multi_selection")
    private Integer multiSelection;

    @Column(name = "expired_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiredDate;

    @OneToOne
    @JoinColumn(name = "creator")
    private Member creator;

    @CreationTimestamp
    @Column(name = "create_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = VoteTeamRelate.class, mappedBy = "vote")
    private List<VoteTeamRelate> voteTeamRelates;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = VoteOption.class, mappedBy = "vote")
    private List<VoteOption> voteOptions;

    @Override
    public Integer getId() {
        return this.voteId;
    }

    @Transient
    private List<VoteOption> deleteVoteOptions;

    @Transient
    private List<VoteOption> addVoteOptions;
}
