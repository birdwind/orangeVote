package com.orange.orange_vote.entity.service.impl;

import com.orange.orange_vote.base.repo.BaseModel;
import com.orange.orange_vote.base.security.model.SystemUser;
import com.orange.orange_vote.entity.dao.VoteOptionDao;
import com.orange.orange_vote.entity.model.Member;
import com.orange.orange_vote.entity.model.Vote;
import com.orange.orange_vote.entity.model.VoteOption;
import com.orange.orange_vote.entity.service.VoteOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;

@Service
@Transactional
public class VoteOptionServiceImpl implements VoteOptionService {

    @Autowired
    private VoteOptionDao voteOptionDao;

    @Override
    public Boolean getVoteOptionBeSelect(Integer voteId, Integer voteOptionId) {
        Member member = SystemUser.getMember();
        return voteOptionDao.findVoteOptionBeSelect(voteId, voteOptionId, member.getMemberId()) != null;
    }

    @Override
    public Optional<VoteOption> getVoteOptionByVoteOptionUuidAndVote(String voteOptionUuid, Vote vote) {
        return voteOptionDao.findVoteOptionByVoteOptionUuidAndVoteId(voteOptionUuid, vote.getVoteId());
    }

    @Override
    public List<VoteOption> saveAll(Collection<VoteOption> voteOptions) {
        return voteOptionDao.saveAll(voteOptions);
    }

    @Override
    public List<VoteOption> deleteAll(Collection<VoteOption> voteOptions) {
        return voteOptionDao.saveAll(voteOptions.stream().peek(BaseModel::delete).collect(Collectors.toList()));
    }

    @Override
    public BigDecimal countOptionBeSelectedByVoteId(Integer voteId) {
        return voteOptionDao.countOptionBeSelectedByVoteId(voteId);
    }
}
