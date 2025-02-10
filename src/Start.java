import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
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
    boolean saved;
    Start(){
        //creates frame
        frame=new JFrame();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        startPanel=new JPanel();
        startPanel.setLayout(null);
        startPanel.setSize(800,600);
        //creates and adds the start background
        ImageIcon image=new ImageIcon(getClass().getResource("startBg.png"));
        Image pic = image.getImage();
        Image newPic = pic.getScaledInstance(800, 600,Image.SCALE_DEFAULT);
        image = new ImageIcon(newPic);
        startLabel=new JLabel(image);
        startLabel.setSize(800,600);
        startLabel.setLayout(null);
        startPanel.add(startLabel);
        //creates and adds start button
        JButton play=new JButton(new ImageIcon(getClass().getResource("start.PNG")));
        setButton(play);
        play.setBounds(256,230,287,111);
        //creates and adds how to play button
        JButton howTo=new JButton(new ImageIcon(getClass().getResource("howToPlay.PNG")));
        setButton(howTo);
        howTo.setBounds(148,350,504,123);
        startLabel.add(play);
        startLabel.add(howTo);
        frame.add(startPanel);
        frame.setVisible(true);
        //seeing if there's anything saved to the file
        try {
            FileReader fr = new FileReader("SaveGame.txt");
            BufferedReader br = new BufferedReader(fr);
            br.mark(1000);
            if(br.readLine()==null){ //there is nothing saved
                saved=false;
            }
            else{ //there is something saved
                saved=true;
            }
            br.reset();//buffered reader set to top
        }
        catch (IOException i){
            System.out.print("error "+i);
        }
        if (!saved) { //not saved and play is clicked
            play.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    startGame();
                }
            });
        }
        else { //saved and play is clicked
            play.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    resumeGame();
                }
            });
        }
        //how to play is clicked
        howTo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    howToPlay();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    //sets buttons in constructor
    private void setButton(JButton button) {
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
    }
    //user starts game
    public void startGame() {
        //makes roomPanel
        startPanel.setVisible(false);
        JPanel roomPanel = new JPanel();
        roomPanel.setSize(800, 600);
        roomPanel.setLayout(null);

        //makes and adds background to panel
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

        //positioning relative to jframe
        int frameX = frame.getLocation().x;
        int frameY = frame.getLocation().y;
        int dialogX = frameX + (200) / 2;
        int dialogY = frameY + 350;

        //makes and adds dialog with cat and text
        awakeDialog.setLocation(dialogX, dialogY);
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
        //next is clicked on dialog
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                roomPanel.removeAll();//wiping the panel clean
                //adding the room to the panel
                awakeDialog.dispose();
                ImageIcon room = new ImageIcon(getClass().getResource("room.png"));
                Image roomPic = room.getImage();
                Image newRoom = roomPic.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
                room = new ImageIcon(newRoom);
                JLabel roomLabel = new JLabel(room);
                roomLabel.setSize(800, 600);
                roomPanel.add(roomLabel);
                //instantiating classes
                Blue blue = new Blue(frame, roomLabel, startPanel, roomPanel, false);
                Green green = new Green(frame, roomLabel, roomPanel);

                roomPanel.revalidate();
                roomPanel.repaint();
            }
        });
    }
    private void resumeGame(){
        //shows roomPanel with room pic as background
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
        frame.add(roomPanel);
        //instantiates classes
        Blue blue = new Blue(frame, roomLabel, startPanel, roomPanel, true);
        Green green = new Green(frame, roomLabel, roomPanel);
    }

    public void howToPlay() throws IOException {
        //dialog pop up with different panel and an image on it that is the instructions
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
        instructionDialog.setLocationRelativeTo(null);
        instructionDialog.setVisible(true);
    }

}
