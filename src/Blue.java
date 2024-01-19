import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
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
    private int scissors=1;
    int num=0;
    private JPanel deskPanel; //preserve big ones to not have to recreate them every time
    private JPanel closetPanel;
    private JPanel colourPanel;
    JButton file;


    Blue(JFrame frame, JPanel panel, JPanel start){
        super(frame, panel);
        roomPanel=panel;
        startPanel=start;
        roomPanel.setLayout(null);
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
        JButton carpet=createButton("boxEx.png",700,500,roomPanel);
        carpet.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                carpetClicked();
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
        JButton safe=createButton("boxEx.png",600,100,closetPanel);
        JButton box=createButton("boxEx.png",100,400,closetPanel);
        leaveButton(closetPanel,roomPanel);
        safe.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JPanel safePanel=showImage("boxEx.png",closetPanel);
                JButton nextButton=new JButton("NEXT");
                JDialog safeDialog=catText("wow ! i have a safe just like this at home ! i need a 6 digit code.","catTalkEx.png",nextButton);
                nextButton.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        String passcode=JOptionPane.showInputDialog("enter the passcode: ");
                        //int timesEntered=0;
                        while (passcode!=null && !passcode.equals("245473")) { //&& timesEntered<=5
                            passcode=JOptionPane.showInputDialog("enter the passcode: ");
                            //timesEntered++;
                            //add timer, if timer = 5 mins then timesEntered=0
                        }
                        if (passcode==null) {
                            safeDialog.dispose();
                            safePanel.setVisible(false);
                            closetPanel.setVisible(true);
                        }
                        else {
                            JPanel keyPanel = showImage("picEx.png", safePanel);
                            JButton keyButton = new JButton("OK");
                            safeDialog.dispose();
                            JDialog keyDialog = catText("yay! a key", "catTalkEx.png", keyButton);
                            keyButton.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    keyDialog.dispose();
                                    safePanel.setVisible(false);
                                    closetPanel.setVisible(true);
                                }
                            });
                        }
                    }
                });
            }
        });
        box.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JPanel boxPanel=showImage("boxEx.png",closetPanel);
                if (scissors==0) {
                    JButton boxButton = new JButton("NEXT");
                    JDialog boxDialog = catText("looks like it needs a 4 digit code.", "catTalkEx.png", boxButton);
                    boxButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            boxDialog.dispose();
                            String boxCode = JOptionPane.showInputDialog("enter the passcode: ");
                            while (boxCode != null && !boxCode.equals("3543")) {
                                boxCode = JOptionPane.showInputDialog("enter the passcode: ");
                            }
                            if (boxCode == null) {
                                boxPanel.setVisible(false);
                                closetPanel.setVisible(true);
                            } else {
                                scissors = 1;
                                JPanel scissorPanel = showImage("picEx.png", boxPanel);
                                JButton scissorButton = new JButton("OK");
                                JDialog scissorDialog = catText("who would keep scissors in a safe ??? i guess i'll take them..", "catTalkEx.png", scissorButton);
                                scissorButton.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        scissorDialog.dispose();
                                        boxPanel.setVisible(false);
                                        closetPanel.setVisible(true);
                                    }
                                });
                            }
                        }
                    });
                }
                else{
                    JButton gotButton=new JButton("OK");
                    JDialog gotScissors=catText("i've already taken the scissors that were in here.","catTalkEx.png",gotButton);
                    gotButton.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                            gotScissors.dispose();
                            boxPanel.setVisible(false);
                            closetPanel.setVisible(true);
                        }
                    });
                }
            }
        });
    }

    private void carpetClicked(){
        num=0;
        JPanel carpetPanel=showImage("boxEx.png",roomPanel);
        if (scissors==0){
            JButton scisButton=new JButton("LEAVE");
            JDialog noScisDialog=catText("i need something to open this box with...","catTalkEx.png",scisButton);
            scisButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    noScisDialog.dispose();
                    carpetPanel.setVisible(false);
                    roomPanel.setVisible(true);
                }
            });
        }
        else{
            if (colourPanel==null) {
                colourPanel = showImage("boxEx.png", carpetPanel);
            }
            else{
                colourPanel.setVisible(true);
                carpetPanel.setVisible(false);
            }
            int[] colourCode={0,0,0,0,0,0};
            int[] answerCode={4,1,4,3,2,3};
            JButton red=createButton("redEx.png",200,200,colourPanel);
            JButton yellow=createButton("yellowEx.png",400,200,colourPanel);
            JButton green=createButton("greenEx.png",200,400,colourPanel);
            JButton blue=createButton("blueEx.png",400,400,colourPanel);
            JOptionPane.showMessageDialog(colourPanel,"Click the buttons in the correct order.");
            red.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (num>5){
                        for (int i=0; i<6;i++){
                            colourCode[i]=0;
                        }
                        if (Arrays.equals(colourCode, answerCode)){
                            JPanel donePanel=showImage("colDoneEx.png",colourPanel);
                            leaveButton(donePanel,roomPanel);
                        }
                        num=0;
                        colourCode[num] = 1;
                    }
                    else{
                        colourCode[num] = 1;
                        num++;
                    }
                    System.out.println(Arrays.toString(colourCode));
                }
            });
            yellow.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (num>5){
                        for (int i=0; i<6;i++){
                            colourCode[i]=0;
                        }
                        if (Arrays.equals(colourCode, answerCode)){
                            JPanel donePanel=showImage("colDoneEx.png",colourPanel);
                            leaveButton(donePanel,roomPanel);
                        }
                        num=0;
                        colourCode[num] = 2;
                    }
                    else{
                        colourCode[num] = 2;
                        num++;
                    }
                    System.out.println(Arrays.toString(colourCode));
                }
            });
            green.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (num>5){
                        for (int i=0; i<6;i++){
                            colourCode[i]=0;
                        }
                        if (Arrays.equals(colourCode, answerCode)){
                            JPanel donePanel=showImage("colDoneEx.png",colourPanel);
                            leaveButton(donePanel,roomPanel);
                        }
                        num=0;
                        colourCode[num] = 3;
                    }
                    else{
                        colourCode[num] = 3;
                        num++;
                    }
                    System.out.println(Arrays.toString(colourCode));
                }
            });
            blue.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (num>5){
                        for (int i=0; i<6;i++){
                            colourCode[i]=0;
                        }
                        if (Arrays.equals(colourCode, answerCode)){
                            JPanel donePanel=showImage("colDoneEx.png",colourPanel);
                            leaveButton(donePanel,roomPanel);
                        }
                        num=0;
                        colourCode[num] = 4;
                    }
                    else{
                        colourCode[num] = 4;
                        num++;
                    }
                    System.out.println(Arrays.toString(colourCode));
                }
            });
            leaveButton(colourPanel,roomPanel);
        }
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
