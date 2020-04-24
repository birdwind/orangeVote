package com.orange.orange_vote.controller;

import com.orange.orange_vote.base.annotation.AuthForm;
import com.orange.orange_vote.constans.TeamErrorConstants;
import com.orange.orange_vote.entity.model.Team;
import com.orange.orange_vote.entity.model.Vote;
import com.orange.orange_vote.entity.service.TeamService;
import com.orange.orange_vote.view.team.TeamForm;
import com.orange.orange_vote.view.team.TeamResource;
import com.orange.orange_vote.view.team.converter.TeamFormConverter;
import com.orange.orange_vote.view.team.converter.TeamListItemConverter;
import com.orange.orange_vote.view.team.converter.TeamResourcePacker;
import com.orange.orange_vote.view.team.converter.TeamViewConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping(value = {"/api/team"}, produces = "application/json;charset=utf-8")
public class TeamApiController {

    @Autowired
    private TeamResourcePacker teamResourcePacker;

    @Autowired
    private TeamFormConverter teamFormConverter;

    @Autowired
    private TeamViewConverter teamViewConverter;

    @Autowired
    private TeamListItemConverter teamListItemConverter;

    @Autowired
    private TeamService teamService;

    @PutMapping(value = "")
    public TeamResource createTeam(@AuthForm @Valid @RequestPart(value = "team") TeamForm teamForm) {
        return teamResourcePacker
            .pack(teamViewConverter.convert(teamService.saveTeam(teamFormConverter.convert(teamForm))));
    }

    @Transactional
    @PostMapping(value = "/status")
    public TeamResource updateTeamStatus(@AuthForm @Valid @RequestPart(value = "team") TeamForm teamForm,
        @RequestParam(value = "status") Boolean status) {
        return teamResourcePacker
            .pack(teamViewConverter.convert(teamService.updateTeam(teamFormConverter.convert(teamForm, status))));
    }

    @GetMapping(value = "/{teamUuid}")
    public TeamResource joinTeam(@RequestParam(value = "pass_code") String passCode,
                                 @PathVariable(value = "teamUuid") Team team) {
        Team teamWithPassCode = teamService.getTeamByPassCode(passCode, team.getTeamUuid());
        if (teamWithPassCode == null) {
            return teamResourcePacker.packNotFoundErrors(TeamErrorConstants.TEAM_NOT_FOUND);
        }
        return teamResourcePacker.pack(teamViewConverter.convert(teamService.joinTeam(team)));
    }

    @GetMapping(value = "/list")
    public TeamResource teamList() {
        return teamResourcePacker.pack(teamListItemConverter.convert(teamService.getAllTeam()));
    }
}
