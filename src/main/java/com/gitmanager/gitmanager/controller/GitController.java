package com.gitmanager.gitmanager.controller;

import com.gitmanager.gitmanager.model.GitBranchList;
import com.gitmanager.gitmanager.service.GitService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class GitController {

    private final GitService gitService;

    @GetMapping("/git/branches")
    public GitBranchList getAllGitBranchesFromRemote() {
        return gitService.getAllGitBranchesFromRemote();
    }

    @DeleteMapping("/git/branches/{branchName}")
    public void deleteGitBranchFromRemote(@PathVariable String branchName) {
        gitService.deleteGitBranchFromRemote(branchName);
    }

}
