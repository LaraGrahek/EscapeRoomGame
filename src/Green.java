import java.awt.event.*;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JPanel;
public class Green extends RoomItems{
    Green(JFrame frame, JLabel label, JPanel panel){
        super(frame,label,panel);
        roomLabel=label;
        roomPanel=panel;
        JButton chair=createButton("chair.PNG",20,300,roomLabel);
        chair.addActionListener(e -> greenClicked("chairCloser.png","This is a chair.","Chair","this chair looks uncomfortable.","catHm.PNG"));

        JButton lamp=createButton("lamp.PNG",200,450,roomLabel);
        lamp.addActionListener(e -> greenClicked("lampCloser.png","A broken lamp stands before you.","Lamp","the lamp looks kinda weird.","catNorm.PNG"));

        JButton window=createButton("window.PNG",700,350,roomLabel);
        window.addActionListener(e -> greenClicked("windowCloser.png","You look out the window and all you see is an endless void of darkness.","Window","um, ok..maybe the clock will tell me more accurately what time it is.","catHm.PNG"));

        JButton bed=createButton("bed.PNG",510,80,roomLabel);
        bed.addActionListener(e -> greenClicked("bedCloser.png","This is the bed that you woke up in.","Bed","ugh, i think i have back problems after sleeping on this mattress.","catUgh.png"));
    }
    private void greenClicked(String imagePic, String textMessage, String title, String imageMessage, String textPic){
        JPanel newPanel=new JPanel();
        JLabel greenLabel=showImage(imagePic,roomPanel,newPanel);
        JButton okButton=new JButton("OK");
        JDialog msg=msgText(textMessage,title,okButton);
        okButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                msg.dispose();
                JButton returnButton=new JButton("RETURN");
                JDialog cat=catText(imageMessage,textPic,returnButton);
                returnButton.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        cat.dispose();
                        roomPanel.setVisible(true);
                        newPanel.setVisible(false);
                    }
                });
            }
        });
    }
}
