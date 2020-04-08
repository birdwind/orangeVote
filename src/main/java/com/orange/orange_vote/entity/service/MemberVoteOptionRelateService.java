package com.orange.orange_vote.entity.service;

import com.orange.orange_vote.entity.model.MemberVoteOptionRelate;
import java.util.Collection;
import java.util.List;

public interface MemberVoteOptionRelateService {

    List<MemberVoteOptionRelate> saveAll(Collection<MemberVoteOptionRelate> memberVoteOptionRelates);

    Boolean isVoteAlready(Integer voteId);

}
