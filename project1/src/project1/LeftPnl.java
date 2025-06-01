package project1.src.project1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LeftPnl extends JPanel {
    private JButton[] menuButtons;
    private int selectedIndex = 0;

    public LeftPnl() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(240, 240, 240));
        setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 10)); // 四周内边距
        setPreferredSize(new Dimension(200, 750));
        // 添加标题
        add(createTitleLabel());
        add(Box.createVerticalStrut(15)); // 标题与菜单间距

        // 添加作者
        add(createAuthor());
        add(Box.createVerticalStrut(60)); // 标题与菜单间距

        // 初始化菜单项
        String[] menuItems = {"首页", "设置", "帮助"};
        Icon[] icons = {
                loadScaledIcon("project1/icons/home.png", 24, 24),
                loadScaledIcon("project1/icons/settings.png", 24, 24),
                loadScaledIcon("project1/icons/help.png", 24, 24)
        };

        menuButtons = new JButton[menuItems.length];
        
        for (int i = 0; i < menuItems.length; i++) {
            menuButtons[i] = createMenuButton(menuItems[i], icons[i]);
            add(menuButtons[i]);
            add(Box.createVerticalStrut(10)); // 按钮间距
        }

        add(Box.createVerticalGlue()); // 将菜单项顶置
        setSelected(0); // 默认选中第一项
    }

    private JLabel createTitleLabel() {
        JLabel title = new JLabel("功能菜单");
        title.setFont(new Font("微软雅黑", Font.BOLD, 36));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        return title;
    }

    private JLabel createAuthor(){
        JLabel author = new JLabel("author：Sgggy");
        author.setFont(new Font("微软雅黑", Font.BOLD, 14));
        author.setAlignmentX(Component.CENTER_ALIGNMENT);
        return author;
    }

    private JButton createMenuButton(String text, Icon icon) {
         JButton button = new JButton(text) {
            // 添加圆角绘制
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // 绘制圆角背景
                if (getModel().isArmed() || isSelected()) {
                    g2.setColor(getBackground().darker());
                } else {
                    g2.setColor(getBackground());
                }
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                super.paintComponent(g2);
                g2.dispose();
            }
        };
        button.setIcon(icon);
        button.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFocusPainted(false);
        button.setBackground(Color.WHITE);
        button.setPreferredSize(new Dimension(180, 45)); // 设置首选尺寸
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45)); // 允许横向扩展
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setContentAreaFilled(false);

        // 鼠标悬停效果
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(225, 225, 225));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (button != menuButtons[selectedIndex]) {
                    button.setBackground(Color.WHITE);
                }
            }
        });

        // 点击事件
        button.addActionListener(e -> {
            int index = -1;
            for (int i = 0; i < menuButtons.length; i++) {
                if (menuButtons[i] == button) {
                    index = i;
                    break;
                }
            }
            if (index != -1) setSelected(index);
        });

        return button;
    }

    private void setSelected(int index) {
        menuButtons[selectedIndex].setBackground(Color.WHITE);
        menuButtons[selectedIndex].setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        selectedIndex = index;
        menuButtons[selectedIndex].setBackground(new Color(200, 220, 255));
        menuButtons[selectedIndex].setBorder(BorderFactory.createCompoundBorder(
                new SideBorder(new Color(67, 133, 244), 3), 
                BorderFactory.createEmptyBorder(10, 10, 10, 20)
        ));
    }

    // 加载缩放后的图标（需要替换为实际图标路径）
       private Icon loadScaledIcon(String path, int width, int height) {
        try {
            // 改用资源路径加载
            java.net.URL imgUrl = getClass().getResource("/" + path);
            System.out.println("尝试加载图标资源: " + imgUrl); // 调试输出
            
            if (imgUrl == null) {
                throw new Exception("资源路径不存在");
            }
            
            ImageIcon originalIcon = new ImageIcon(imgUrl);
            Image scaledImage = originalIcon.getImage()
                    .getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        } catch (Exception e) {
            System.out.println("图标加载失败: " + path + "，原因: " + e.getMessage());
            return new ImageIcon();
        }
    }

    private static class SideBorder extends javax.swing.border.AbstractBorder {
        private final Color color;
        private final int thickness;

        public SideBorder(Color color, int thickness) {
            this.color = color;
            this.thickness = thickness;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(color);
            g2d.fillRect(x, y, thickness, height); // 左侧绘制色条
            g2d.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(0, thickness, 0, 0); // 左边距保留给色条
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.left = thickness;
            return insets;
        }
    }
}