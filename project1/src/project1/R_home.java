package project1.src.project1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.nio.file.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class R_home extends JPanel {
    // 存储文件路径用于后续监听
    private List<String> filePaths = new ArrayList<>();
    private JPanel workArea;
    private JPanel gridPanel; 
    ActionListener addListener;
    ActionListener selctionListener;

    
    public R_home() {
        
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        
        // 北区 - 大标题
        JLabel title = new JLabel("我的Java文件列表", SwingConstants.CENTER);
        title.setFont(new Font("微软雅黑", Font.BOLD, 36));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // 增加标题间距
        add(title, BorderLayout.NORTH);
        
        // 工作区 - 带边框的滚动面板
        this.workArea = new JPanel(new BorderLayout());
        this.workArea.setBackground(Color.WHITE);
        this.workArea.setBorder(BorderFactory.createTitledBorder("工作区")); // 添加边框
        
        // 网格布局参数调整
        this.gridPanel = new JPanel(new GridLayout(0, 5, 10, 10)) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(super.getPreferredSize().width, 240); // 固定高度
            }
        };
    
        this.gridPanel.setBackground(Color.WHITE);
        this.gridPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // 增加内部边距
        this.workArea.add(this.gridPanel, BorderLayout.CENTER);
        add(this.workArea, BorderLayout.CENTER);

        setButtonListener();
        MyupdateUI();
        
   
    }
    //ui更新逻辑
     public void MyupdateUI() {

        // 仅清空网格面板
        if (gridPanel != null && gridPanel.getComponentCount() > 0) {
            gridPanel.removeAll();
        }
        else {
            System.out.println("gridPanel is null");
        }
        if (filePaths != null) {
            filePaths.clear();
        }
        else{
            System.out.println("filePaths is null");
        }

        // 加载文件列表
        File packageDir = new File("project1/java package");
        if (!packageDir.exists()) {
            packageDir.mkdirs();
        }

        // 生成文件按钮
        for (File file : packageDir.listFiles()) {
            if (file.isFile() && file.getName().endsWith(".java")) {
                JButton btn = createContentButton(file.getName(), selctionListener);
                gridPanel.add(btn);
                filePaths.add(file.getAbsolutePath());
            }
        }

        // 添加+号按钮
        JButton addBtn = createAddButton();
        gridPanel.add(addBtn);

        // 刷新界面
        gridPanel.revalidate();
        gridPanel.repaint();
    }

    // 创建+号按钮
    // 创建按钮
    private JButton createContentButton(String fileName, ActionListener listener) {
        // 创建带圆角的自定义按钮
        JButton button = new JButton(fileName) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // 绘制圆角背景
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                
                // 绘制文字（保持居中）
                super.paintComponent(g2);
                g2.dispose();
            }
        };
        
        // 设置按钮样式
        button.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        button.setForeground(new Color(60, 60, 60));
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setVerticalAlignment(SwingConstants.CENTER);
        button.setPreferredSize(new Dimension(120, 120)); // 与网格布局适配的尺寸
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        button.setBackground(new Color(240, 245, 255));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // 添加悬停效果
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(220, 230, 255));
                button.setForeground(new Color(40, 40, 40));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(240, 245, 255));
                button.setForeground(new Color(60, 60, 60));
            }
        });
        
        if (listener != null) {
            button.addActionListener(listener);
        }
        return button;
    }

    // 创建+号按钮
    private JButton createAddButton() {
        JButton addbutton = new JButton();
        addbutton =  createContentButton("+", addListener);
        addbutton.setFont(new Font("微软雅黑", Font.PLAIN, 36));
        return addbutton;
    }

    // 设置按钮监听器
    public void setButtonListener() {
        // 普通按钮监听器 - 执行Java文件
        this.selctionListener = e -> {
            JButton source = (JButton) e.getSource();
            String fileName = source.getText();
            String filePath = "project1/java package/" + fileName;

            // 创建执行窗口
            JDialog consoleDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this));
            consoleDialog.setTitle("执行结果 - " + fileName);
            consoleDialog.setSize(600, 400);
            consoleDialog.setMaximumSize(getParent().getSize());
            
            // 控制台界面
            JTextArea outputArea = new JTextArea();
            outputArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(outputArea);
            
            try {
                Process process = Runtime.getRuntime().exec("java " + filePath.replace(".java", ""));
                // 启动输出监控线程
                new Thread(() -> {
                    try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(process.getInputStream()))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            final String curentLine = line;
                            SwingUtilities.invokeLater(() -> {
                                outputArea.append(curentLine + "\n");
                            });
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }).start();

            // 添加关闭监听
            consoleDialog.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    process.destroy(); // 关闭窗口时终止进程
                }
            });
            
            consoleDialog.add(scrollPane);
            consoleDialog.setVisible(true);
            consoleDialog.setLocationRelativeTo(this);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "执行失败: " + ex.getMessage());
                }
            };


        // 添加按钮监听器
        this.addListener = e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileFilter(new FileNameExtensionFilter("Java Files", "java"));
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                try {
                    File src = chooser.getSelectedFile();
                    File destDir = new File("project1/java package");
                    if (!destDir.exists()) destDir.mkdirs();
                    
                    File dest = new File(destDir, src.getName());
                    Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    MyupdateUI();
                    JOptionPane.showMessageDialog(this, "添加成功!");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "添加失败: " + ex.getMessage());
                }
            }
        };
    };
}


