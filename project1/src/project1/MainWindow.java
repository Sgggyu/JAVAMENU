package project1.src.project1;
import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    
    // 左右容器
    private LeftPnl leftPanel;
    private JPanel rightPanel;

    public MainWindow() {
        // 窗口基础设置
        setTitle("主窗口");
        setSize(1000, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // 使用BorderLayout作为主布局
        setLayout(new BorderLayout());
        
        // 初始化左侧容器
        leftPanel = new LeftPnl();
        
        add(leftPanel, BorderLayout.WEST);
        
        // 初始化右侧容器
        rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(800, 750));
        rightPanel.setBackground(Color.WHITE);
        add(rightPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainWindow window = new MainWindow();
            window.setVisible(true);
        });
    }
}