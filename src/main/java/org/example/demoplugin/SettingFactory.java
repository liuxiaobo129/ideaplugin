package org.example.demoplugin;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.ui.Messages;
import git4idea.GitUtil;
import git4idea.commands.Git;
import git4idea.commands.GitCommand;
import git4idea.commands.GitLineHandler;
import git4idea.repo.GitRepository;
import org.example.demoplugin.ui.SettingUI;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.Objects;


import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import git4idea.repo.GitRepositoryManager;

public class SettingFactory implements SearchableConfigurable {

    private SettingUI settingUI = new SettingUI();

    @Override
    public @NotNull String getId() {
        return "test.id";
    }

    @Override
    public @Nls(capitalization = Nls.Capitalization.Title) String getDisplayName() {
        return "test-config";
    }

    @Override
    public @Nullable JComponent createComponent() {
        return settingUI.getComponent();
    }

    @Override
    public boolean isModified() {
        return true;
    }

    @Override
    public void apply() throws ConfigurationException {
        String url = settingUI.getUrlTextField().getText();
        // 设置文本信息
        try {
            File file = new File(url);
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            randomAccessFile.seek(0);

            byte[] bytes = new byte[1024 * 1024];
            int readSize = randomAccessFile.read(bytes);

            byte[] copy = new byte[readSize];
            System.arraycopy(bytes, 0, copy, 0, readSize);

            String str = new String(copy, StandardCharsets.UTF_8);

            // 设置内容
            Config.readUI.getTextContent().setText(str);

        } catch (Exception ignore) {
            System.out.println(ignore);
        }
    }

}




 class CustomGitAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent e) {
        var project = e.getProject();
        // 获取当前 Git 仓库
        GitRepository repositoryForFileQuick = GitUtil.getRepositoryManager(project).getRepositoryForFileQuick(project.getBaseDir());

        if (project != null) {
            var repositoryManager = GitRepositoryManager.getInstance(project);
            var repositories = repositoryManager.getRepositories();
            for (var repo : repositories) {
                repo.getRemotes();
                System.out.println("Repository: " + repo.getRoot());
            }
        }

    }
}