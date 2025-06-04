package project1.src.project1;

import javax.swing.*;
import java.awt.*;

public class CRightPnl0433 extends JPanel {
    private CardLayout cardLayout;
    
    public CRightPnl0433() {
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        setBackground(new Color(255, 255, 255));
        setPreferredSize(new Dimension(800, 750));
        
        // 初始化默认页面
        addCard("home", createHomePage());
        addCard("settings", createSettingsPage());
        addCard("help", createHelpPage());
    }
    
    public void addCard(String name, Component component) {
        add(component, name);
    }
    
    public void switchToCard(String name) {
        cardLayout.show(this, name);
    }
    
    private JPanel createHomePage() {
        R_home homePanel = new R_home(); 
        return homePanel;
    }
    
    private JPanel createSettingsPage() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("设置内容"));
        return panel;
    }
    
    private JPanel createHelpPage() {
        R_help helpPanel = new R_help(); 
        return helpPanel;
    }
    
}
