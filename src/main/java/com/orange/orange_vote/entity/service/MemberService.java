package com.orange.orange_vote.entity.service;

import com.orange.orange_vote.entity.model.Member;
import com.orange.orange_vote.entity.model.MemberRoleRelate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MemberService {

    String generateMemberNo();

    Optional<Member> getMemberByUsername(String username);

    void updateSession(String session, Integer memberId);

    Optional<Member> getMemberCoreBySession(String session);

    Member saveWithRoleRelate(Member member, Collection<MemberRoleRelate> memberRoleRelates);

    List<Member> delete(Collection<Member> member);

    Optional<Member> getMemberByMemberUuid(String memberUuid);

    Optional<Member> getMemberByOrangeId(String orangeId);
}
