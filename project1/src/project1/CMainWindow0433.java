package project1.src.project1;
import javax.swing.*;
import java.awt.*;

public class CMainWindow0433 extends JFrame {
    
    // 左右容器
    private CLeftPnl0433 leftPanel;
    private CRightPnl0433 rightPanel;

    public CMainWindow0433() {
        // 窗口基础设置
        setTitle("主窗口");
        setSize(1000, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // 使用BorderLayout作为主布局
        setLayout(new BorderLayout());
        
        // 初始化左侧容器
        leftPanel = new CLeftPnl0433();
        //初始化右侧容器
        rightPanel = new CRightPnl0433();
        
        leftPanel.setMenuListener((int index) ->{
                switch (index) {
                    case 0: rightPanel.switchToCard("home");; break; // 首页
                    case 1: rightPanel.switchToCard("settings"); break; // 设置
                    case 2: rightPanel.switchToCard("help"); break; // 帮助
                
            }
        });
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
        
        // 初始化右侧容器
        
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CMainWindow0433 window = new CMainWindow0433();
            window.setVisible(true);
        });
    }
}