package com.orange.orange_vote.controller;

import com.orange.orange_vote.base.annotation.AuthForm;
import com.orange.orange_vote.entity.service.TeamService;
import com.orange.orange_vote.view.team.TeamForm;
import com.orange.orange_vote.view.team.converter.TeamFormConverter;
import com.orange.orange_vote.view.team.converter.TeamResourcePacker;
import com.orange.orange_vote.view.team.converter.TeamViewConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping(value = {"/api/team"})
public class TeamController {

    @Autowired
    private TeamResourcePacker teamResourcePacker;

    @Autowired
    private TeamFormConverter teamFormConverter;

    @Autowired
    private TeamViewConverter teamViewConverter;

    @Autowired
    private TeamService teamService;

    @PutMapping(value = "")
    public String createTeam(@AuthForm @Valid @RequestPart(value = "team") TeamForm teamForm) {
        return teamResourcePacker
            .pack(teamViewConverter.convert(teamService.saveTeam(teamFormConverter.convert(teamForm)))).toJson();
    }

    @PostMapping(value = "/status")
    public String updateTeamStatus(@AuthForm @Valid @RequestPart(value = "team") TeamForm teamForm,
        @RequestParam(value = "status") Boolean status) {
        return teamResourcePacker
            .pack(teamViewConverter.convert(teamService.updateTeam(teamFormConverter.convert(teamForm, status))))
            .toJson();
    }

}
