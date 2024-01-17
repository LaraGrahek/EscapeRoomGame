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
    JButton file;


    Blue(JFrame frame, JPanel panel, JPanel start){
        super(frame, panel);
        roomPanel=panel;
        startPanel=start;
        //startPanel.setLayout(null);
        JButton door=createButton("doorEx.png",400,20,roomPanel);
        door.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                doorClicked();
            }
        });
        JButton desk=createButton("deskButtonEx.png",50,550,roomPanel);
        desk.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                deskClicked();
            }
        });
        JButton closet=createButton("doorEx.png",20,100,roomPanel);
        closet.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                closetClicked();
            }
        });
    }
    private void deskClicked(){
        if (deskPanel==null) {
            deskPanel = showImage("deskEx.png", roomPanel);
        }
        else{
            roomPanel.setVisible(false);
            deskPanel.setVisible(true);
        }
        JButton computer=createButton("computerEx.png",500,100,deskPanel);
        JButton water=createButton("waterEx.png",200,450,deskPanel);
        JButton picture=createButton("picEx.png",50,50,deskPanel);
        leaveButton(deskPanel,roomPanel);
        computer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel computerPanel=showImage("computerEx.png",deskPanel);
                JButton app=createButton("leaveEx.png",500,100,computerPanel);
                leaveButton(computerPanel,deskPanel);
                if(appNum==0) {
                    app.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            file = createButton("catTalkEx.png", 300, 200, computerPanel);
                            appNum = 1;
                            file.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    JOptionPane.showMessageDialog(computerPanel,"Where the clothes of which you lack resides, the length of names in photo, shall unlock the box underneath");
                                }
                            });
                        }
                    });
                }
            }
        });
        water.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JPanel waterPanel=showImage("waterEx.png",deskPanel);
                leaveButton(waterPanel,deskPanel);
                JOptionPane.showMessageDialog(deskPanel,"This is a glass of water.");
            }
        });
        picture.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JPanel photoPanel=showImage("picEx.png",deskPanel);
                JButton okButton=new JButton("OK");
                JDialog photoDialog=catText("why am i in this photo what","catTalkEx.png",okButton);
                okButton.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        photoDialog.dispose();
                        photoPanel.setVisible(false);
                        deskPanel.setVisible(true);
                    }
                });
            }
        });
    }
    private void closetClicked(){
        if (closetPanel==null){
            closetPanel=showImage("deskEx.png",roomPanel);
        }
        else if (closetPanel!=null){
            closetPanel.setVisible(true);
            roomPanel.setVisible(false);
        }
        JButton safe=createButton("boxEx.png",100,500,closetPanel);
        JButton box=createButton("boxEx.png",700,100,closetPanel);
        leaveButton(closetPanel,roomPanel);
        safe.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JPanel safePanel=showImage("boxEx.png",closetPanel);
                JButton nextButton=new JButton("NEXT");
                JDialog safeDialog=catText("wow ! i have a safe just like this at home ! i need a 6 digit code.","catTalkEx.png",nextButton);
                nextButton.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        String passcode=JOptionPane.showInputDialog("enter the passcode: ");
                        if (passcode.equals("245473")){

                        }
                    }
                });
            }
        });
        box.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

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
