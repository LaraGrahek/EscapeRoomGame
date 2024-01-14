import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import static javax.swing.JOptionPane.YES_NO_OPTION;

public class Blue extends RoomItems   {
    JFrame baseFrame;
    JPanel startPanel;
    JPanel roomPanel;
    private int appNum=0;
    private int key=0;
    private int scissors=0;
    int num;
    private JPanel deskPanel; //preserve big ones to not have to recreate them every time
    private JPanel closetPanel;
    private JPanel carpetPanel;

    Blue(JFrame frame, JPanel panel, JPanel start){
        super(frame, panel);
        roomPanel=panel;
        startPanel=start;
        JButton door=this.createButton("doorEx.png",400,20,panel);
        door.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                doorClicked();
            }
        });
    }
    private void doorClicked(){
        JPanel doorPanel=showImage("doorUpEx.png",roomPanel);
        if (key==0){
            JButton okButton=new JButton("OK");
            JDialog keyDialog=this.catText("hm...... looks like i need a key to open this door.", "catTalkEx.png", okButton );
            okButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    doorPanel.setVisible(false);
                    roomPanel.setVisible(true);
                    keyDialog.dispose(); // Close the dialog
                }
            });
        }
        else{
            num=0;
            int answer=JOptionPane.showConfirmDialog(doorPanel,"You have a key. Use it ?", "Door", YES_NO_OPTION );
            if(answer==JOptionPane.YES_OPTION){
                JPanel free=showImage("freeEx.png",doorPanel);
                JButton nextButton=new JButton("NEXT");
                JDialog freeDialog=catText("yay ! i'm finally free !!!!!!", "catTalkEx.png", nextButton);
                nextButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        freeDialog.dispose(); // Close the dialog
                        JPanel wait=showImage("waitEx.png",free);
                        JButton next2Button = new JButton("NEXT");
                        JDialog waitDialog=catText("wait....... what ?", "catTalkEx.png", next2Button);
                        next2Button.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                waitDialog.dispose();
                                JPanel end=showImage("endEx.png",wait);
                                JButton leave=createButton("awakeEx.png",200,300,end);
                                leave.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        end.setVisible(false);
                                        startPanel.setVisible(true);
                                    }
                                });
                            }
                        });
                        waitDialog.setVisible(true);
                    }
                });
            }
            else if(answer==JOptionPane.NO_OPTION){
                doorPanel.setVisible(false);
                roomPanel.setVisible(true);
                KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
            }
        }
    }
}
