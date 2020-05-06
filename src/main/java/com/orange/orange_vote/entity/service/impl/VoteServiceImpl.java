package com.orange.orange_vote.entity.service.impl;

import com.google.common.collect.Lists;
import com.orange.orange_vote.entity.dao.VoteDao;
import com.orange.orange_vote.entity.dao.VoteOptionDao;
import com.orange.orange_vote.entity.dao.VoteTeamRelateDao;
import com.orange.orange_vote.entity.model.Team;
import com.orange.orange_vote.entity.model.Vote;
import com.orange.orange_vote.entity.model.VoteTeamRelate;
import com.orange.orange_vote.entity.service.VoteService;
import com.orange.orange_vote.enums.NumberEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import javax.transaction.Transactional;

@Service
@Transactional
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteDao voteDao;

    @Autowired
    private VoteTeamRelateDao voteTeamRelateDao;

    @Autowired
    private VoteOptionDao voteOptionDao;

    private AtomicInteger counter = new AtomicInteger(-1);

    @Override
    public String generateFunctionNo() {
        if (counter.get() == -1) {
            counter.set(voteDao.countVotes());
        }
        return NumberEnum.VOTE.valueOf() + counter.getAndIncrement();
    }

    @Override
    public List<Vote> getAllIsVisableVotesByMemberId(Integer memberId) {
        return voteDao.findVotesIsVisableByMemberIdAndIsOpen(memberId, new Date()).orElse(Lists.newArrayList());
    }

    @Override
    public Optional<Vote> getVoteByVoteUuidAndCreatorId(String voteUuid, Integer memberId) {
        return voteDao.findVoteByVoteUuidAndCreatorId(voteUuid, memberId);
    }

    @Override
    public Vote saveVote(Vote vote, Team team) {
        Vote tempVote = voteDao.save(vote);
        VoteTeamRelate voteTeamRelate = voteTeamRelateDao.save(new VoteTeamRelate(team, tempVote));
        tempVote.setVoteTeamRelates(Lists.newArrayList(voteTeamRelate));
        return tempVote;
    }

    @Override
    public Vote updateVote(Vote vote, Team team) {
        Vote tempVote = voteDao.save(vote);
        if (team != tempVote.getVoteTeamRelates().stream().map(VoteTeamRelate::getTeam).findFirst().orElse(null)) {
            VoteTeamRelate voteTeamRelate = vote.getVoteTeamRelates().stream().findFirst().orElse(null);
            if (voteTeamRelate != null) {
                voteTeamRelate.delete();
                voteTeamRelateDao.save(voteTeamRelate);
            }
            voteTeamRelateDao.save(new VoteTeamRelate(team, tempVote));
        }
        return tempVote;
    }

    @Override
    public Optional<Vote> getVoteByVoteUuidAndMemberId(String voteUuid, Integer memberId) {
        return voteDao.findVoteByVoteUuidAndMemberId(voteUuid, memberId);
    }

    @Override
    public Optional<Vote> getVoteByVoteUuidAndMemberIdAndCreatorId(String voteUuid, Integer memberId) {
        return voteDao.findVoteByVoteUuidAndMemberIdAndCreatorId(voteUuid, memberId);
    }

    @Override
    public List<Vote> getVotedByMemberId(Integer memberId) {
        return voteDao.findVotedByMemberId(memberId);
    }

    @Override
    public List<Vote> getVotesByCreatorId(Integer creatorId) {
        return voteDao.findVotesByCreatorId(creatorId);
    }
}
