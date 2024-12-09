package org.example.demoplugin.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;
import git4idea.GitUtil;
import com.intellij.openapi.project.Project;

import git4idea.repo.GitRepository;

public class HelpAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        Messages.showMessageDialog(e.getProject().getName(),"",Messages.getErrorIcon());

        Project project = e.getProject();
        if (project == null) {
            Messages.showErrorDialog("No project found.", "Error");
            return;
        }

        // 获取当前 Git 仓库
        GitRepository repository = GitUtil.getRepositoryManager(project).getRepositoryForFileQuick(project.getBaseDir());
        if (repository == null) {
            Messages.showErrorDialog("No Git repository found in this project.", "Error");
            return;
        }

        // 获取远程仓库 URL
        String remoteUrl = repository.getRemotes().stream()
                .findFirst()
                .map(remote -> remote.getFirstUrl())
                .orElse(null);

        if (remoteUrl == null) {
            Messages.showErrorDialog("No remote repository configured.", "Error");
            return;
        }
    }
}
