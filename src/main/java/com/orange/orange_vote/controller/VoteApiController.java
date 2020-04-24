package com.orange.orange_vote.controller;

import com.orange.orange_vote.base.annotation.AuthForm;
import com.orange.orange_vote.base.security.model.SystemUser;
import com.orange.orange_vote.entity.model.Member;
import com.orange.orange_vote.entity.model.MemberVoteOptionRelate;
import com.orange.orange_vote.entity.model.Vote;
import com.orange.orange_vote.entity.model.VoteOption;
import com.orange.orange_vote.entity.service.MemberVoteOptionRelateService;
import com.orange.orange_vote.entity.service.VoteOptionService;
import com.orange.orange_vote.entity.service.VoteService;
import com.orange.orange_vote.view.vote.VoteForm;
import com.orange.orange_vote.view.vote.VoteResource;
import com.orange.orange_vote.view.vote.converter.VoteDetailViewConverter;
import com.orange.orange_vote.view.vote.converter.VoteFormConverter;
import com.orange.orange_vote.view.vote.converter.VoteListItemConverter;
import com.orange.orange_vote.view.vote.converter.VoteResourcePacker;
import com.orange.orange_vote.view.vote.converter.VoteViewConverter;
import com.orange.orange_vote.view.vote.converter.VotedListItemConverter;
import com.orange.orange_vote.view.voteOption.MemberVoteOptionRelateForm;
import com.orange.orange_vote.view.voteOption.converter.MemberVoteOptionRelateFormConverter;
import com.orange.orange_vote.view.voteOption.converter.VoteOptionFormConverter;
import com.orange.orange_vote.view.voteOption.converter.VoteOptionFormDeleteConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

@RestController
@RequestMapping(value = {"/api/vote"}, produces = "application/json;charset=utf-8")
public class VoteApiController {

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
    private VoteOptionFormDeleteConverter voteOptionFormDeleteConverter;

    @Autowired
    private MemberVoteOptionRelateFormConverter memberVoteOptionRelateFormConverter;

    @Autowired
    private VoteDetailViewConverter voteDetailViewConverter;

    @Autowired
    private VotedListItemConverter votedListItemConverter;

    @Autowired
    private VoteService voteService;

    @Autowired
    private VoteOptionService voteOptionService;

    @Autowired
    private MemberVoteOptionRelateService memberVoteOptionRelateService;

    @GetMapping(value = "/list")
    public VoteResource voteList() {
        Member member = SystemUser.getMember();
        // return voteResourcePacker
//         .pack(voteListItemConverter.convert(voteService.getAllIsVisableVotesByMemberId(member.getMemberId())));
        return voteResourcePacker
            .pack(voteViewConverter.convert(voteService.getAllIsVisableVotesByMemberId(member.getMemberId()), member));
    }

    @Transactional
    @PutMapping(value = "")
    public VoteResource createVote(@AuthForm @Valid @RequestPart(value = "vote") VoteForm voteForm) {
        Vote vote = voteService.saveVote(voteFormConverter.convert(voteForm), voteForm.getTeam());
        vote.setVoteOptions(
            voteOptionService.saveAll(voteOptionFormConverter.convertList(voteForm.getOptions(), vote)));
        return voteResourcePacker.pack(voteViewConverter.convertToList(vote));
        // return voteResourcePacker.pack(voteViewConverter.convert(vote);
    }

    @Transactional
    @PostMapping(value = "")
    public VoteResource updateVote(@AuthForm @Valid @RequestPart(value = "vote") VoteForm voteForm) {
        Vote vote = voteService.updateVote(voteFormConverter.convert(voteForm), voteForm.getTeam());
        voteOptionService.saveAll(voteOptionFormConverter.convertList(voteForm.getOptions(), vote));
        voteOptionService.deleteAll(voteOptionFormDeleteConverter.convertList(voteForm.getDeleteOptions(), vote));
        return voteResourcePacker.pack(voteViewConverter.convert(vote));
    }

    @Transactional
    @PostMapping(value = "/option")
    public VoteResource choseVoteOption(
        @AuthForm @Valid @RequestPart(value = "vote") MemberVoteOptionRelateForm memberVoteOptionRelateForm) {
        Member member = SystemUser.getMember();
        // 新增選項
        List<VoteOption> voteOptions = voteOptionService.saveAll(voteOptionFormConverter
            .convertList(memberVoteOptionRelateForm.getAddOptions(), memberVoteOptionRelateForm.getVote()));

        // 建立票數與投票者關聯
        List<MemberVoteOptionRelate> memberVoteOptionRelates =
            memberVoteOptionRelateFormConverter.convertToList(memberVoteOptionRelateForm);
        memberVoteOptionRelates.addAll(voteOptions.stream()
            .map(voteOption -> new MemberVoteOptionRelate(member, voteOption)).collect(Collectors.toList()));

        memberVoteOptionRelateService.saveAll(memberVoteOptionRelates);

        return voteResourcePacker.pack(voteViewConverter.convert(
            voteService.getVoteByVoteUuidAndMemberId(memberVoteOptionRelateForm.getVoteUuid(), member.getMemberId())
                .orElse(null)));
    }

    @GetMapping(value = "/detail/{voteUuid}")
    public VoteResource voteDetail(@PathVariable(value = "voteUuid") Vote vote) {
        return voteResourcePacker.pack(voteDetailViewConverter.convert(vote));
    }

    @GetMapping(value = "/voted")
    public VoteResource votedList() {
        Member member = SystemUser.getMember();
        return voteResourcePacker
            .pack(votedListItemConverter.convert(voteService.getVotedByMemberId(member.getMemberId())));
    }

    @GetMapping(value = "/create/list")
    public VoteResource createdVoteList() {
        Member member = SystemUser.getMember();
        return voteResourcePacker
            .pack(votedListItemConverter.convert(voteService.getVotesByCreatorId(member.getMemberId())));
    }
}
