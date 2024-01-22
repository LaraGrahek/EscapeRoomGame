import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import static javax.swing.JOptionPane.YES_NO_OPTION;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Start extends JPanel {
    JFrame frame;
    JPanel startPanel;
    JLabel startLabel;
    boolean saved;
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
        try {
            FileReader fr = new FileReader("SaveGame.txt");
            BufferedReader br = new BufferedReader(fr);
            br.mark(1000);
            if(br.readLine()==null){
                saved=false;
            }
            else{
                saved=true;
            }
            br.reset();
        }
        catch (IOException i){
            System.out.print("error "+i);
        }
        if (!saved) {
            play.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    startGame();
                }
            });
        }
        else if(saved){
            play.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    resumeGame();
                }
            });
        }
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
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
    }

    public void startGame() {
        startPanel.setVisible(false);
        JPanel roomPanel = new JPanel();
        roomPanel.setSize(800, 600);
        roomPanel.setLayout(null);

        JPanel dialogPanel = new JPanel();
        ImageIcon awake = new ImageIcon(getClass().getResource("awake.png"));
        Image awakePic = awake.getImage();
        Image newAwake = awakePic.getScaledInstance(800, 600, Image.SCALE_DEFAULT);
        awake = new ImageIcon(newAwake);
        JLabel awakeLabel = new JLabel(awake);
        awakeLabel.setSize(800, 600);
        roomPanel.add(awakeLabel);
        frame.add(roomPanel);

        JDialog awakeDialog = new JDialog(frame, "You");
        awakeDialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        awakeDialog.setLayout(new BorderLayout());

        int frameX = frame.getLocation().x;
        int frameY = frame.getLocation().y;
        int dialogX = frameX + (200) / 2;
        int dialogY = frameY + 350;

        awakeDialog.setLocation(dialogX, dialogY);
        //ImageIcon cat = new ImageIcon(getClass().getResource("catHm.PNG"));
        Image pic = (new ImageIcon(getClass().getResource("catUgh.png"))).getImage();
        Image newimg = pic.getScaledInstance(185, 140, Image.SCALE_FAST);
        ImageIcon cat = new ImageIcon(newimg);
        awakeDialog.setPreferredSize(new Dimension(610, 200));
        JLabel imageLabel = new JLabel(cat);
        dialogPanel.add(imageLabel, BorderLayout.WEST);
        JLabel textLabel = new JLabel("ughhhghghgh where am i...?");
        dialogPanel.add(textLabel, BorderLayout.CENTER);
        awakeDialog.add(dialogPanel, BorderLayout.CENTER);
        JButton next = new JButton("NEXT");
        next.setCursor(new Cursor(Cursor.HAND_CURSOR));
        awakeDialog.add(next, BorderLayout.SOUTH);
        awakeDialog.pack();
        awakeDialog.setVisible(true);
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                roomPanel.removeAll();
                awakeDialog.dispose();
                ImageIcon room = new ImageIcon(getClass().getResource("room.png"));
                Image roomPic = room.getImage();
                Image newRoom = roomPic.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
                room = new ImageIcon(newRoom);
                JLabel roomLabel = new JLabel(room);
                roomLabel.setSize(800, 600);
                roomPanel.add(roomLabel);
                Blue blue = new Blue(frame, roomLabel, startPanel, roomPanel, false);
                Green green = new Green(frame, roomLabel, roomPanel);
                roomPanel.revalidate();
                roomPanel.repaint();
            }
        });
    }
    private void resumeGame(){
        startPanel.setVisible(false);
        JPanel roomPanel = new JPanel();
        roomPanel.setSize(800, 600);
        roomPanel.setLayout(null);
        ImageIcon room = new ImageIcon(getClass().getResource("room.png"));
        Image roomPic = room.getImage();
        Image newRoom = roomPic.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
        room = new ImageIcon(newRoom);
        JLabel roomLabel = new JLabel(room);
        roomLabel.setSize(800, 600);
        roomPanel.add(roomLabel);
        Blue blue = new Blue(frame, roomLabel, startPanel, roomPanel, true);
        Green green = new Green(frame, roomLabel, roomPanel);
    }

    public void howToPlay() throws IOException {
        JPanel instructionPanel=new JPanel(new FlowLayout());
        JDialog instructionDialog = new JDialog(frame, "How To Play", false);
        instructionDialog.setPreferredSize(new Dimension(450, 260)); //size of dialog
        instructionDialog.setLayout(new BorderLayout());
        ImageIcon insBg = new ImageIcon(getClass().getResource("instructions.png"));
        Image insImg = (insBg).getImage();
        Image newImg = insImg.getScaledInstance(450, 240,Image.SCALE_DEFAULT);
        insBg = new ImageIcon(newImg);
        JLabel label=new JLabel(insBg);
        instructionPanel.add(label);
        instructionDialog.add(instructionPanel, BorderLayout.CENTER);
        instructionDialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        instructionDialog.pack();
        instructionDialog.setLocationRelativeTo(null); // Center the dialog on the screen
        instructionDialog.setVisible(true);
    }

}
