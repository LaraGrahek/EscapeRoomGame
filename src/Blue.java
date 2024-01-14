import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import static javax.swing.JOptionPane.YES_NO_OPTION;

public class Blue extends RoomItems   {
    JFrame baseFrame;
    JPanel roomPanel;
    private int appNum=0;
    private int key=1;
    private int scissors=0;
    private JPanel deskPanel; //preserve big ones to not have to recreate them every time
    private JPanel closetPanel;
    private JPanel carpetPanel;

    Blue(JFrame frame, JPanel panel){
        super(frame, panel);
        roomPanel=panel;
        JButton door=this.createButton("doorEx.png",400,20,panel);
        door.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                doorClicked();
            }
        });
    }
    private void doorClicked(){
        JPanel doorPanel=showImage("doorUpEx.png");
        if (key==0){
            this.catText("hm...... looks like i need a key to open this door.", "catTalkEx.png", "OK",doorPanel,roomPanel );
        }
        else{
            int answer=JOptionPane.showConfirmDialog(doorPanel,"You have a key. Use it ?", "Door", YES_NO_OPTION );
            if(answer==JOptionPane.YES_OPTION){
                doorPanel.setVisible(false);
                JPanel free=showImage("freeEx.png");
                JPanel wait=showImage("waitEx.png");
                JPanel end=showImage("endEx.png");
                this.catText("yay ! i'm finally free !!!!!!", "catTalkEx.png", "NEXT",free,wait);

                this.catText("wait....... what ?","catTalk.png", "NEXT",wait, end);
            }
            else if(answer==JOptionPane.NO_OPTION){
                doorPanel.setVisible(false);
                roomPanel.setVisible(true);
                KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
            }
            /*JButton yesButton=new JButton("YES");
            JButton noButton=new JButton("NO");
            JDialog dialog = new JDialog(frame, "Door");
            dialog.setLayout(new GridLayout(3,3,20,20));
            dialog.setPreferredSize(new Dimension(600, 300)); //size of dialog
            JPanel dialogPanel=new JPanel();
            JLabel text=new JLabel("You have a key. Do you want to use it ?");
            dialogPanel.add(text);
            dialog.add(dialogPanel);
            dialog.add(yesButton);
            dialog.add(noButton);
            noButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    doorPanel.setVisible(false);
                    roomPanel.setVisible(true);
                    dialog.dispose(); // Close the dialog
                }
            });
            noButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dialog.dispose(); // Close the dialog
                }
            });
            yesButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    doorPanel.setVisible(false);
                    JPanel free=showImage("freeEx.png");
                    JPanel wait=showImage("waitEx.png");
                    JPanel end=showImage("endEx.png");
                    this.catText("yay ! i'm finally free !!!!!!", "catTalkEx.png", "NEXT",free,wait);
                    this.catText("wait....... what ?","catTalk.png", "NEXT",wait, end);
                }
                private void catText(String s, String image, String next, JPanel free, JPanel wait) {
                }
            });
            dialog.pack();
            dialog.setLocationRelativeTo(null); // Center the dialog on the screen
            dialog.setVisible(true);*/
        }
    }
}
