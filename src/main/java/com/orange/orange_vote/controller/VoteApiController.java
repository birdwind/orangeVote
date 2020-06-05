package com.orange.orange_vote.controller;

import com.orange.orange_vote.base.annotation.AuthForm;
import com.orange.orange_vote.base.security.model.SystemUser;
import com.orange.orange_vote.entity.model.Member;
import com.orange.orange_vote.entity.model.MemberVoteOptionRelate;
import com.orange.orange_vote.entity.model.Vote;
import com.orange.orange_vote.entity.model.VoteOption;
import com.orange.orange_vote.entity.service.MemberVoteOptionRelateService;
import com.orange.orange_vote.entity.service.TeamService;
import com.orange.orange_vote.entity.service.VoteOptionService;
import com.orange.orange_vote.entity.service.VoteService;
import com.orange.orange_vote.view.team.converter.TeamViewConverter;
import com.orange.orange_vote.view.vote.VoteCreateForm;
import com.orange.orange_vote.view.vote.VoteResource;
import com.orange.orange_vote.view.vote.VoteUpdateForm;
import com.orange.orange_vote.view.vote.converter.VoteCreateFormConverter;
import com.orange.orange_vote.view.vote.converter.VoteDetailViewConverter;
import com.orange.orange_vote.view.vote.converter.VoteResourcePacker;
import com.orange.orange_vote.view.vote.converter.VoteUpdateFormConverter;
import com.orange.orange_vote.view.vote.converter.VoteViewConverter;
import com.orange.orange_vote.view.vote.converter.VotedListItemConverter;
import com.orange.orange_vote.view.voteOption.MemberVoteOptionRelateForm;
import com.orange.orange_vote.view.voteOption.converter.MemberVoteOptionRelateFormConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = {"/api/vote"}, produces = "application/json;charset=utf-8")
@Api(tags = "投票模組")
public class VoteApiController {

    @Autowired
    private VoteResourcePacker voteResourcePacker;

    @Autowired
    private VoteViewConverter voteViewConverter;

    @Autowired
    private VoteCreateFormConverter voteCreateFormConverter;

    @Autowired
    private VoteUpdateFormConverter voteUpdateFormConverter;

    @Autowired
    private MemberVoteOptionRelateFormConverter memberVoteOptionRelateFormConverter;

    @Autowired
    private VoteDetailViewConverter voteDetailViewConverter;

    @Autowired
    private VotedListItemConverter votedListItemConverter;

    @Autowired
    private TeamViewConverter teamViewConverter;

    @Autowired
    private VoteService voteService;

    @Autowired
    private VoteOptionService voteOptionService;

    @Autowired
    private MemberVoteOptionRelateService memberVoteOptionRelateService;

    @Autowired
    private TeamService teamService;

    @GetMapping(value = "/list")
    @ApiOperation(value = "獲取投票列表")
    public VoteResource voteList() {
        Member member = SystemUser.getMember();
        List<Vote> voteList = voteService.getAllIsVisableVotesByMemberId(member.getMemberId());
        return voteResourcePacker.pack(voteViewConverter.convert(voteList));
    }

    @Transactional
    @PutMapping(value = "")
    @ApiOperation(value = "新增投票")
    public VoteResource createVote(@AuthForm @Valid VoteCreateForm voteCreateForm) {
        Vote voteLocal = voteCreateFormConverter.convert(voteCreateForm);
        Vote vote = voteService.saveVote(voteLocal, voteCreateForm.getTeam());
        List<VoteOption> voteOptionList = voteOptionService.saveAll(voteLocal.getAddVoteOptions().stream()
            .peek(voteOption -> voteOption.setVote(vote)).collect(Collectors.toList()));
        vote.setVoteOptions(voteOptionList);
        return voteResourcePacker.pack(voteViewConverter.convert(vote));
    }

    @Transactional
    @PostMapping(value = "")
    @ApiOperation(value = "更新投票")
    public VoteResource updateVote(@AuthForm @Valid VoteUpdateForm voteUpdateForm) {
        Vote voteLocal = voteUpdateFormConverter.convert(voteUpdateForm);

        Vote vote = voteService.updateVote(voteLocal, voteUpdateForm.getTeam());

        voteOptionService.saveAll(voteLocal.getUpdateVoteOptions());

        return voteResourcePacker.pack(voteDetailViewConverter.convert(vote));
    }

    @Transactional
    @PostMapping(value = "/option")
    @ApiOperation(value = "執行投票")
    public VoteResource choseVoteOption(@AuthForm @Valid MemberVoteOptionRelateForm memberVoteOptionRelateForm) {
        Member member = SystemUser.getMember();
        // 新增選項
        List<VoteOption> voteOptionLocalList = memberVoteOptionRelateForm.getAddOptions().stream()
            .map(optionValue -> new VoteOption(member, optionValue, memberVoteOptionRelateForm.getVote()))
            .collect(Collectors.toList());
        List<VoteOption> voteOptionList = voteOptionService.saveAll(voteOptionLocalList);

        // 建立票數與投票者關聯
        List<MemberVoteOptionRelate> memberVoteOptionRelates =
            memberVoteOptionRelateFormConverter.convertToList(memberVoteOptionRelateForm);
        memberVoteOptionRelates.addAll(voteOptionList.stream()
            .map(voteOption -> new MemberVoteOptionRelate(member, voteOption)).collect(Collectors.toList()));

        memberVoteOptionRelateService.saveAll(memberVoteOptionRelates);

        return voteResourcePacker.pack(voteViewConverter.convert(
            voteService.getVoteByVoteUuidAndMemberId(memberVoteOptionRelateForm.getVoteUuid(), member.getMemberId())
                .orElse(null)));
    }

    @GetMapping(value = "/detail/{voteUuid}")
    @ApiOperation(value = "獲取投票細節")
    public VoteResource voteDetail(@PathVariable(value = "voteUuid") Vote vote) {
        return voteResourcePacker.pack(voteDetailViewConverter.convert(vote));
    }

    @GetMapping(value = "/voted")
    @ApiOperation(value = "獲取已投票列表")
    public VoteResource votedList() {
        Member member = SystemUser.getMember();
        List<Vote> voteList = voteService.getVotedByMemberId(member.getMemberId());
        return voteResourcePacker.pack(votedListItemConverter.convert(voteList));
    }

    @GetMapping(value = "/create/list")
    @ApiOperation(value = "獲取建立的投票")
    public VoteResource createdVoteList() {
        Member member = SystemUser.getMember();
        List<Vote> voteList = voteService.getVotesByCreatorId(member.getMemberId());
        return voteResourcePacker.pack(votedListItemConverter.convert(voteList));
    }
}
