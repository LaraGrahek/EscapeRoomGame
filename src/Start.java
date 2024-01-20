import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Start extends JPanel {
    JFrame frame;
    JPanel startPanel;
    JLabel startLabel;
    Start(){
        frame=new JFrame();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        startPanel=new JPanel();
        startPanel.setLayout(null);
        startPanel.setSize(800,600);
        ImageIcon image=new ImageIcon(getClass().getResource("startBg.png"));
        Image pic = image.getImage();
        Image newPic = pic.getScaledInstance(800, 600,Image.SCALE_DEFAULT);
        image = new ImageIcon(newPic);
        startLabel=new JLabel(image);
        startLabel.setSize(800,600);
        startLabel.setLayout(null);
        startPanel.add(startLabel);
        JButton play=new JButton(new ImageIcon(getClass().getResource("start.PNG")));
        setButton(play);
        play.setBounds(256,230,287,111);
        JButton instructions=new JButton(new ImageIcon(getClass().getResource("howToPlay.PNG")));
        setButton(instructions);
        instructions.setBounds(148,350,504,123);
        startLabel.add(play);
        startLabel.add(instructions);
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
    private void setButton(JButton button) {
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
    }

    public void startGame() {
        startPanel.setVisible(false);
        JPanel roomPanel = new JPanel();
        JPanel dialogPanel = new JPanel();
        roomPanel.setSize(800, 600);
        roomPanel.setLayout(null);

        ImageIcon awake = new ImageIcon(getClass().getResource("awakeEx.png"));
        JLabel awakeLabel=new JLabel(awake);
        awakeLabel.setSize(800,600);
        roomPanel.add(awakeLabel);
        frame.add(roomPanel);

        JDialog awakeDialog = new JDialog(frame, "You");
        awakeDialog.setUndecorated(true);
        awakeDialog.setLayout(new BorderLayout());
        awakeDialog.setPreferredSize(new Dimension(600, 200)); //size of dialog

        int frameX = frame.getLocation().x;
        int frameY = frame.getLocation().y;
        int dialogX = frameX+(200) / 2;
        int dialogY = frameY+350;

        awakeDialog.setLocation(dialogX,dialogY);
        ImageIcon cat = new ImageIcon(getClass().getResource("catTalkEx.png"));
        JLabel imageLabel = new JLabel(cat);
        dialogPanel.add(imageLabel,BorderLayout.CENTER);
        JLabel textLabel = new JLabel("ughhhghghgh where am i...???");
        dialogPanel.add(textLabel,BorderLayout.SOUTH);
        awakeDialog.add(dialogPanel, BorderLayout.CENTER);
        JButton next = new JButton("NEXT");
        awakeDialog.add(next, BorderLayout.SOUTH);
        awakeDialog.pack();
        awakeDialog.setVisible(true);
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                roomPanel.removeAll();
                awakeDialog.dispose();
                ImageIcon room = new ImageIcon(getClass().getResource("roomEx.png"));
                JLabel roomLabel=new JLabel(room);
                roomLabel.setSize(800,600);
                roomPanel.add(roomLabel);
                //ImageIcon bgImage = new ImageIcon(getClass().getResource("roomEx.png"));
                //roomPanel.setBackgroundImage("roomEx.png");
                Blue blue = new Blue(frame, roomLabel, startPanel, roomPanel);
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
