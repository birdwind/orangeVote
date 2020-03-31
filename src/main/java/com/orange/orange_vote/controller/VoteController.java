package com.orange.orange_vote.controller;

import com.orange.orange_vote.base.annotation.AuthForm;
import com.orange.orange_vote.base.security.model.SystemUser;
import com.orange.orange_vote.entity.model.Member;
import com.orange.orange_vote.entity.service.VoteService;
import com.orange.orange_vote.view.team.TeamForm;
import com.orange.orange_vote.view.vote.VoteForm;
import com.orange.orange_vote.view.vote.converter.VoteListItemConverter;
import com.orange.orange_vote.view.vote.converter.VoteResourcePacker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    private VoteService voteService;

    @GetMapping(value = "/list")
    public String voteList() {
        Member member = SystemUser.getMember();
        return voteResourcePacker
            .pack(voteListItemConverter.convert(voteService.getAllVotesByMemberId(member.getMemberId()))).toJson();
    }

    @PutMapping(value = "")
    public String createVote(@AuthForm @Valid @RequestPart(value = "vote") VoteForm voteForm){
        return voteResourcePacker.pack().toJson();
    }
}
