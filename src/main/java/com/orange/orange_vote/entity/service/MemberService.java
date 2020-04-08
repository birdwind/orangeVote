package com.orange.orange_vote.entity.service;

import com.orange.orange_vote.entity.model.Member;
import com.orange.orange_vote.entity.model.MemberRoleRelate;
import java.util.Collection;
import java.util.Optional;

public interface MemberService {

    String generateMemberNo();

    Optional<Member> getMemberByUsername(String username);

    void updateSession(String session, Integer memberId);

    Optional<Member> getMemberCoreBySession(String session);

    Member save(Member member, Collection<MemberRoleRelate> memberRoleRelates);

//    Member update(Member member, Collection<MemberRoleRelate> memberRoleRelates);

    Optional<Member> getMemberByMemberUuid(String memberUuid);
}
