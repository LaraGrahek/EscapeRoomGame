import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
public class Green extends RoomItems{
    JPanel greenPanel;
    Green(JFrame frame, JLabel label, JPanel panel){
        super(frame,label,panel);
        roomLabel=label;
        roomPanel=panel;
        JButton chair=createButton("chair.PNG",20,300,roomLabel);
        chair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        JButton lamp=createButton("lamp.PNG",200,450,roomLabel);
        lamp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //greenClicked();
            }
        });
        JButton window=createButton("window.PNG",700,350,roomLabel);
        window.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        JButton bed=createButton("bed.PNG",510,80,roomLabel);
    }
    private void greenClicked(String imagePic, String textMessage, String title, String imageMessage, String textPic){
        JLabel greenLabel;
    }
}
