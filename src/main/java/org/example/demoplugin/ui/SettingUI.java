package org.example.demoplugin.ui;

import javax.swing.*;
import java.io.File;

public class SettingUI {
    private JPanel mainpanel;
    private JPanel settingPanel;
    private JTextField urlTextFeild;
    private JLabel urlLabel;
    private JButton urlButton;



    public SettingUI() {
        // 给按钮添加一个选择文件的事件
        urlButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.showOpenDialog(settingPanel);
            File file = fileChooser.getSelectedFile();
            urlTextFeild.setText(file.getPath());
        });
    }

    public JComponent getComponent() {
        return mainpanel;
    }

    public JTextField getUrlTextField() {
        return urlTextFeild;
    }
}
