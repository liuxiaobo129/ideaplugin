package com.ppolivka;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.PathManager;



import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;

public class HelpAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        Messages.showMessageDialog(e.getProject().getName(),"",Messages.getErrorIcon());
    }
}



 class SettingsState {
    public static void main(String[] args) {
        String configPath = PathManager.getConfigPath();
        System.out.println("Config directory: " + configPath);
    }
}
