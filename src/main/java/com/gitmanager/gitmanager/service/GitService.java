package com.gitmanager.gitmanager.service;

import com.gitmanager.gitmanager.model.GitBranchList;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GitService {

    public GitBranchList getAllGitBranchesFromRemote() {
        try {
            Process process1 = Runtime.getRuntime().exec("git remote update origin --prune");
            process1.waitFor();
            Process process2 = Runtime.getRuntime().exec("git branch -r --list");
            process2.waitFor();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process2.getInputStream()));

            List<String> branchList = reader.lines().collect(Collectors.toList());
            GitBranchList gitBranchList = new GitBranchList();
            gitBranchList.setGitBranches(branchList);
            return gitBranchList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteGitBranchFromRemote(String branchName) {

        try {
            Process process1 = Runtime.getRuntime().exec("git remote update origin --prune");
            process1.waitFor();
            Process process2 = Runtime.getRuntime().exec("git push origin -d " + branchName);
            process2.waitFor();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
