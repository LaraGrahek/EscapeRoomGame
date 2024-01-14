import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Start {
    JFrame frame;
    JFrame frame2;
    JPanel startPanel;
    int num;
    Start(){
        frame=new JFrame();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        startPanel=new JPanel();
        startPanel.setLayout(new BoxLayout(startPanel, BoxLayout.Y_AXIS));
        startPanel.setSize(800,600);
        JButton play=new JButton(new ImageIcon(getClass().getResource("startEx.png")));
        play.setBorderPainted(false);
        play.setContentAreaFilled(false);
        play.setFocusPainted(false);
        play.setOpaque(false);
        JButton instructions=new JButton(new ImageIcon(getClass().getResource("howToEx.png")));
        instructions.setBorderPainted(false);
        instructions.setContentAreaFilled(false);
        instructions.setFocusPainted(false);
        instructions.setOpaque(false);
        startPanel.add(play);
        startPanel.add(Box.createVerticalStrut(20));
        startPanel.add(instructions);
        frame.add(startPanel);
        frame.setVisible(true);
        play.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                startGame();
            }
        });
        instructions.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    howToPlay();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public void startGame() {
        startPanel.setVisible(false);
        JPanel roomPanel = new JPanel();
        JPanel dialogPanel = new JPanel();
        roomPanel.setSize(800, 600);
        ImageIcon awake = new ImageIcon(getClass().getResource("awakeEx.png"));
        roomPanel.add(new JLabel(awake));
        frame.add(roomPanel);
        JDialog awakeDialog = new JDialog(frame, "You");
        awakeDialog.setLayout(new BorderLayout());
        awakeDialog.setPreferredSize(new Dimension(600, 200)); //size of dialog
        ImageIcon cat = new ImageIcon(getClass().getResource("catTalkEx.png"));
        JLabel imageLabel = new JLabel(cat);
        dialogPanel.add(imageLabel);
        JLabel textLabel = new JLabel("ughhhghghgh where am i...???");
        dialogPanel.add(textLabel);
        awakeDialog.add(dialogPanel, BorderLayout.CENTER);
        JButton next = new JButton("NEXT");
        awakeDialog.add(next, BorderLayout.SOUTH);
        awakeDialog.pack();
        awakeDialog.setLocationRelativeTo(null); // Center the dialog on the screen
        awakeDialog.setVisible(true);
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                roomPanel.removeAll();
                awakeDialog.dispose(); // Close the dialog
                roomPanel.add(new JLabel(new ImageIcon("roomEx.png")));
                Blue blue = new Blue(frame, roomPanel);
                Green green = new Green();
                roomPanel.revalidate();
                roomPanel.repaint();
            }
        });
    }

    public void howToPlay() throws IOException {
        JPanel instructionPanel=new JPanel(new FlowLayout());
        JDialog instructionDialog = new JDialog(frame, "How To Play", false);
        instructionDialog.setPreferredSize(new Dimension(400, 300)); //size of dialog
        instructionDialog.setLayout(new BorderLayout());
        JLabel label=new JLabel(new ImageIcon(getClass().getResource("InstructionEx.png")));
        instructionPanel.add(label);
        instructionDialog.add(instructionPanel, BorderLayout.CENTER);
        instructionDialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        instructionDialog.pack();
        instructionDialog.setLocationRelativeTo(null); // Center the dialog on the screen
        instructionDialog.setVisible(true);
    }

}
