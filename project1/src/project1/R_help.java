package project1.src.project1;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class R_help extends JPanel {
    JLabel label;
    private String loadHelpText(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            return "帮助文件加载失败: " + e.getMessage();
        }
    }

    public R_help() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(800, 750));
        
        // 创建带边距的文本区域
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setText(loadHelpText("project1/help/help.txt"));
        textArea.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        textArea.setMargin(new Insets(20, 40, 20, 40)); // 上下左右内边距
        
        // 创建滚动面板并添加边距
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        add(scrollPane, BorderLayout.CENTER);
    }
    
}