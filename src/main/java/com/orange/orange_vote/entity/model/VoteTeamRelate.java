package com.orange.orange_vote.entity.model;

import com.orange.orange_vote.base.annotation.I18nPrefix;
import com.orange.orange_vote.base.repo.AbstractModel;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "`vote_team_relate`")
@I18nPrefix(value = "VoteTeamRelate")
public class VoteTeamRelate extends AbstractModel {

    private static final long serialVersionUID = 1L;

    public VoteTeamRelate() {
        this.relateId = 0;
        this.relateUuid = UUID.randomUUID().toString();
        this.status = true;
    }

    public VoteTeamRelate(Team team, Vote vote) {
        this();
        this.team = team;
        this.vote = vote;
    }

    @Id
    @Column(name = "relate_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer relateId;

    @Column(name = "relate_uuid", updatable = false, nullable = false)
    private String relateUuid;

    @OneToOne
    @JoinColumn(name = "vote_id", nullable = false)
    private Vote vote;

    @OneToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @Override
    public Integer getId() {
        return this.relateId;
    }
}
