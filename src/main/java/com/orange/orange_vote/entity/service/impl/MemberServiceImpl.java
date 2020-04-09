package com.orange.orange_vote.entity.service.impl;

import com.orange.orange_vote.base.repo.BaseModel;
import com.orange.orange_vote.entity.dao.MemberDao;
import com.orange.orange_vote.entity.dao.MemberRoleRelateDao;
import com.orange.orange_vote.entity.model.Member;
import com.orange.orange_vote.entity.model.MemberRoleRelate;
import com.orange.orange_vote.entity.service.MemberService;
import com.orange.orange_vote.enums.NumberEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import javax.transaction.Transactional;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private MemberRoleRelateDao memberRoleRelateDao;

    private AtomicInteger counter = new AtomicInteger(-1);

    @Override
    public String generateMemberNo() {
        if (counter.get() == -1) {
            counter.set(memberDao.countMembers());
        }
        return NumberEnum.MEMBER.valueOf() + counter.getAndIncrement();
    }

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

    @Override
    public Member saveWithRoleRelate(Member member, Collection<MemberRoleRelate> memberRoleRelates) {
        Member newMember = memberDao.save(member);
        memberRoleRelateDao.saveAll(
            memberRoleRelates.stream().peek(relate -> relate.setMember(newMember)).collect(Collectors.toList()));
        return newMember;
    }

    @Override
    public List<Member> delete(Collection<Member> members) {
        return memberDao.saveAll(members.stream().peek(BaseModel::delete).collect(Collectors.toList()));
    }

    @Override
    public Optional<Member> getMemberByMemberUuid(String memberUuid) {
        return memberDao.findMemberByMemberUuid(memberUuid);
    }

    @Override
    public Optional<Member> getMemberByOrangeId(String orangeId) {
        return memberDao.findMemberByOrOrangeId(orangeId);
    }

    @Override
    public Member save(Member member) {
        return memberDao.save(member);
    }
}
