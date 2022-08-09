package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OutputPage {

    public void bigDisplay(String s, String title) {
        JFrame frame = new JFrame(title);
        frame.setBounds(450, 50, 1250, 500);


        s = s.replaceAll("\n", "<br/>");
        JLabel label = new JLabel("<html>" + s + "</html>");
        label.setFont(new Font(Font.MONOSPACED, Font.PLAIN, label.getFont().getSize()));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);


        JScrollPane scroll = new JScrollPane(label);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        frame.add(scroll);


        JButton backButton = new JButton("Go Back");
        backButton.addActionListener(e -> frame.dispose());


        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public void smallDisplay(String info, String title) {
        JFrame frame = new JFrame(title);
        frame.setBounds(450, 50, 400, 250);
        info = info.replaceAll("\n", "<br/>");
        JLabel label = new JLabel("<html>" + info + "</html>");
        label.setFont(new Font(Font.MONOSPACED, Font.PLAIN, label.getFont().getSize()));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        JScrollPane scroll = new JScrollPane(label);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        frame.add(scroll);


        JButton backButton = new JButton("Go Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
