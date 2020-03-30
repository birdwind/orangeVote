package com.orange.orange_vote.entity.service.impl;

import com.orange.orange_vote.entity.dao.MemberDao;
import com.orange.orange_vote.entity.model.Member;
import com.orange.orange_vote.entity.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import javax.transaction.Transactional;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Override
    public Optional<Member> getMemberByUsername(String username) {
        return memberDao.findMemberCoreByUsername(username);
    }

    @Override
    public void updateSession(String session, Integer memberId) {
        memberDao.updateSession(session, memberId);
    }

    @Override
    public Optional<Member> getMemberCoreBySession(String session) {
        return memberDao.findMemberCoreBySession(session);
    }
}
