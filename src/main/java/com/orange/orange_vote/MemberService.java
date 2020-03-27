package com.orange.orange_vote;

import com.orange.orange_vote.entity.model.Member;
import java.util.Optional;

public interface MemberService {

    Optional<Member> getMemberByUsername(String username);

    void updateSession(String session, Integer memberId);
}
