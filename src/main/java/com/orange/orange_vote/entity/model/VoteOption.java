package com.orange.orange_vote.entity.model;

import com.orange.orange_vote.base.annotation.I18nPrefix;
import com.orange.orange_vote.base.repo.AbstractModel;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
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

    @Column(name = "option_value", updatable = false, nullable = false)
    private String voteOptionValue;

    @OneToOne
    @JoinColumn(name = "vote_id", updatable = false, nullable = false)
    private Vote vote;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @Override
    public Integer getId() {
        return this.voteOptionId;
    }
}