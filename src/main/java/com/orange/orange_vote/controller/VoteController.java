package com.orange.orange_vote.controller;

import com.orange.orange_vote.base.annotation.AuthForm;
import com.orange.orange_vote.base.security.model.SystemUser;
import com.orange.orange_vote.entity.model.Member;
import com.orange.orange_vote.entity.model.Vote;
import com.orange.orange_vote.entity.service.MemberVoteOptionRelateService;
import com.orange.orange_vote.entity.service.VoteOptionService;
import com.orange.orange_vote.entity.service.VoteService;
import com.orange.orange_vote.view.vote.VoteForm;
import com.orange.orange_vote.view.vote.converter.VoteFormConverter;
import com.orange.orange_vote.view.vote.converter.VoteListItemConverter;
import com.orange.orange_vote.view.vote.converter.VoteResourcePacker;
import com.orange.orange_vote.view.vote.converter.VoteViewConverter;
import com.orange.orange_vote.view.voteOption.MemberVoteOptionRelateForm;
import com.orange.orange_vote.view.voteOption.converter.MemberVoteOptionRelateFormConverter;
import com.orange.orange_vote.view.voteOption.converter.VoteOptionFormConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping(value = {"/api/vote"})
public class VoteController {

    @Autowired
    private VoteResourcePacker voteResourcePacker;

    @Autowired
    private VoteListItemConverter voteListItemConverter;

    @Autowired
    private VoteViewConverter voteViewConverter;

    @Autowired
    private VoteFormConverter voteFormConverter;

    @Autowired
    private VoteOptionFormConverter voteOptionFormConverter;

    @Autowired
    private MemberVoteOptionRelateFormConverter memberVoteOptionRelateFormConverter;

    @Autowired
    private VoteService voteService;

    @Autowired
    private VoteOptionService voteOptionService;

    @Autowired
    private MemberVoteOptionRelateService memberVoteOptionRelateService;

    @GetMapping(value = "/list")
    public String voteList() {
        Member member = SystemUser.getMember();
        return voteResourcePacker
            .pack(voteListItemConverter.convert(voteService.getAllVotesByMemberId(member.getMemberId()))).toJson();
    }

    @Transactional
    @PutMapping(value = "")
    public String createVote(@AuthForm @Valid @RequestPart(value = "vote") VoteForm voteForm) {
        Vote vote = voteService.saveVote(voteFormConverter.convert(voteForm), voteForm.getTeam());
        voteOptionService.saveAll(voteOptionFormConverter.convertList(voteForm.getOptions(), vote));
        return voteResourcePacker.pack(voteViewConverter.convert(vote)).toJson();
    }

    @Transactional
    @PostMapping(value = "")
    public String updateVote(@AuthForm @Valid @RequestPart(value = "vote") VoteForm voteForm) {
        return voteResourcePacker.pack(
            voteViewConverter.convert(voteService.saveVote(voteFormConverter.convert(voteForm), voteForm.getTeam())))
            .toJson();
    }

    @Transactional
    @PostMapping(value = "/option")
    public String choseVoteOption(
        @AuthForm @Valid @RequestPart(value = "vote") MemberVoteOptionRelateForm memberVoteOptionRelateForm) {
        Member member = SystemUser.getMember();
        memberVoteOptionRelateService
            .saveAll(memberVoteOptionRelateFormConverter.convertToList(memberVoteOptionRelateForm));
        return voteResourcePacker
            .pack(voteListItemConverter.convert(voteService.getAllVotesByMemberId(member.getMemberId()))).toJson();
    }
}
