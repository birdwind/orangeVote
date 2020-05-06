package com.orange.orange_vote.controller;

import com.orange.orange_vote.base.annotation.AuthForm;
import com.orange.orange_vote.entity.model.Team;
import com.orange.orange_vote.entity.service.TeamService;
import com.orange.orange_vote.view.team.TeamCreateForm;
import com.orange.orange_vote.view.team.TeamResource;
import com.orange.orange_vote.view.team.TeamUpdateForm;
import com.orange.orange_vote.view.team.converter.TeamCreateFormConverter;
import com.orange.orange_vote.view.team.converter.TeamFormConverter;
import com.orange.orange_vote.view.team.converter.TeamResourcePacker;
import com.orange.orange_vote.view.team.converter.TeamViewConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = {"/api/team"}, produces = "application/json;charset=utf-8")
@Api(tags = "團隊模組")
public class TeamApiController {

    @Autowired
    private TeamResourcePacker teamResourcePacker;

    @Autowired
    private TeamCreateFormConverter teamCreateFormConverter;

    @Autowired
    private TeamFormConverter teamFormConverter;

    @Autowired
    private TeamViewConverter teamViewConverter;

    @Autowired
    private TeamService teamService;

    @Transactional
    @PutMapping(value = "")
    @ApiOperation(value = "新增團隊")
    public TeamResource createTeam(@AuthForm @Valid TeamCreateForm teamCreateForm) {
        Team team = teamCreateFormConverter.convert(teamCreateForm);
        team = teamService.saveTeam(team);
        teamService.joinTeam(team);
        return teamResourcePacker.pack(teamViewConverter.convert(team));
    }

    @Transactional
    @PostMapping(value = "/status")
    @ApiOperation(value = "更新團隊")
    public TeamResource updateTeamStatus(@AuthForm @Valid TeamUpdateForm teamUpdateForm,
        @RequestParam(value = "status") @ApiParam(value = "團隊狀態") Boolean status) {
        Team teamLocal = teamFormConverter.convert(teamUpdateForm);
        teamLocal.setStatus(status);
        Team team = teamService.updateTeam(teamLocal);
        return teamResourcePacker.pack(teamViewConverter.convert(team));
    }

    @GetMapping(value = "/list")
    @ApiOperation(value = "獲得可見團隊列表")
    public TeamResource teamList() {
        return teamResourcePacker.pack(teamViewConverter.convert(teamService.getAllTeam()));
    }
}
