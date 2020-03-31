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
@Table(name = "`team`")
@I18nPrefix(value = "Team")
public class Team extends AbstractModel {

    @Id
    @Column(name = "team_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teamId;

    @Column(name = "team_uuid", updatable = false, nullable = false)
    private String teamUuid;

    @Column(name = "team_no", updatable = false, nullable = false)
    private String teamNo;

    @Column(name = "team_value", updatable = false, nullable = false)
    private String teamValue;

    @Column(name = "verification")
    private String verification;

    @OneToOne
    @JoinColumn(name = "creator")
    private Member creator;

    @CreationTimestamp
    @Column(name = "create_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @UpdateTimestamp
    @Column(name = "update_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @Override
    public Integer getId() {
        return this.teamId;
    }
}
