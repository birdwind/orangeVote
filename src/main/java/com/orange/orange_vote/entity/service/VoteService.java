package com.orange.orange_vote.entity.service;

import com.orange.orange_vote.entity.model.Vote;
import java.util.List;

public interface VoteService {

    List<Vote> getAllVotesByMemberId(Integer memberId);

}
