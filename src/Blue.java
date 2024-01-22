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
    private int key=0;
    private int scissors=0;
    private boolean carpetDone=false;
    int num=0;
    private int bTable=0;
    JLabel doorLabel;
    JLabel flashLabel;
    JButton flashButton;
    JLabel deskLabel;
    JLabel closetLabel;
    JLabel colourLabel;
    JLabel computerLabel;
    private JPanel donePanel;
    private JPanel deskPanel; //preserve big ones to not have to recreate them every time
    private JPanel closetPanel;
    private JPanel colourPanel;
    JButton file;
    JLabel roomLabel;


    Blue(JFrame frame, JLabel label, JPanel start, JPanel panel){
        super(frame, label,panel);
        baseFrame=frame;
        baseFrame.setVisible(true);
        roomPanel=panel;
        startPanel=start;
        roomLabel=label;
        roomLabel.setLayout(null);
        roomPanel.setLayout(null);

        JButton nextButton=new JButton("NEXT");
        JDialog timeText=catText("...what time even is it?","catHm.PNG",nextButton);
        nextButton.addActionListener(e -> timeText.dispose());

        JButton door=createButton("door.PNG",210,30,roomLabel);
        door.addActionListener(e -> doorClicked());

        JButton desk=createButton("desk.PNG",10,430,roomLabel);
        desk.addActionListener(e -> deskClicked());

        JButton closet=createButton("closet.PNG",5,20,roomLabel);
        closet.addActionListener(e -> closetClicked());

        JButton carpet=createButton("carpet.PNG",450,410,roomLabel);
        carpet.addActionListener(e -> carpetClicked());

        JButton clock=createButton("clock.PNG",400,35,roomLabel);
        clock.addActionListener(e -> clockClicked());

        JButton bedTable=createButton("bedTable.PNG",430,140,roomLabel);
        bedTable.addActionListener(e -> bedTableClicked());
    }
    private void deskClicked(){
        if (deskPanel==null) {
            deskPanel=new JPanel();
            deskLabel = showImage("deskCloser.png",roomPanel, deskPanel);
        }
        else {
            roomPanel.setVisible(false);
            deskPanel.setVisible(true);
        }
        leaveButton(deskPanel,roomPanel,deskLabel);
        JButton computer=createButton("computer.PNG",330,0,deskLabel);
        JButton water=createButton("water.PNG",50,350,deskLabel);
        JButton picture=createButton("photo.PNG",20,0,deskLabel);
        computer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel computerPanel=new JPanel();
                computerLabel=showImage("compBg.png",deskPanel,computerPanel);
                JButton app=createButton("app.PNG",500,100,computerLabel);
                leaveButton(computerPanel,deskPanel,computerLabel);
                app.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        file = createButton("mail.PNG", 300, 200, computerLabel);
                        file.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JOptionPane.showMessageDialog(baseFrame,"Where the clothes of which you lack resides, the length of names in photo, shall unlock the box underneath");
                            }
                        });
                    }
                });
            }
        });
        water.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JPanel waterPanel=new JPanel();
                JLabel waterLabel=showImage("waterCloser.png",deskPanel,waterPanel);
                JButton okButton=new JButton("OK");
                JDialog waterDialog=msgText("This is a glass of water.","Water Glass",okButton);
                okButton.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        waterDialog.dispose();
                        waterPanel.setVisible(false);
                        deskPanel.setVisible(true);
                    }
                });
            }
        });
        picture.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JPanel photoPanel=new JPanel();
                JLabel photoLabel=showImage("photoCloser.png",deskPanel,photoPanel);
                JButton okButton=new JButton("OK");
                JDialog photoDialog=catText("why am i in this photo what","catHm.PNG",okButton);
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
        if (closetPanel==null || closetLabel==null){
            closetPanel=new JPanel();
            closetLabel = showImage("closetCloser.png",roomPanel, closetPanel);
        }
        else {
            closetPanel.setVisible(true);
            roomPanel.setVisible(false);
        }
        JButton safe=createButton("safe.PNG",440,55,closetLabel);
        JButton box=createButton("box.PNG",145,420,closetLabel);
        leaveButton(closetPanel,roomPanel,closetLabel);
        safe.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JPanel safePanel=new JPanel();
                JLabel safeLabel=showImage("safeCloser.PNG",closetPanel,safePanel);
                JButton nextButton=new JButton("NEXT");
                JDialog safeDialog=catText("wow ! i have a safe just like this at home ! i need a 6 digit code.","catHappy.PNG",nextButton);
                nextButton.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        String passcode=JOptionPane.showInputDialog("enter the passcode: ");
                        while (passcode!=null && !passcode.equals("245473")) {
                            passcode=JOptionPane.showInputDialog("enter the passcode: ");
                        }
                        if (passcode==null) {
                            safeDialog.dispose();
                            safePanel.setVisible(false);
                            closetPanel.setVisible(true);
                        }
                        else {
                            safeLabel.setVisible(false);
                            JLabel keyLabel = showImage("key.png",safePanel);
                            JButton keyButton = new JButton("OK");
                            safeDialog.dispose();
                            JDialog keyDialog = catText("yay! a key", "catHappier.PNG", keyButton);
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
                JPanel boxPanel=new JPanel();
                JLabel boxLabel=showImage("boxCloser.png",closetPanel,boxPanel);
                if (scissors==0) {
                    JButton boxButton = new JButton("NEXT");
                    JDialog boxDialog = catText("looks like it needs a 4 digit code.", "catHm.PNG", boxButton);
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
                                boxLabel.setVisible(false);
                                JLabel scissorLabel = showImage("scissors.png", boxPanel);
                                JButton scissorButton = new JButton("OK");
                                JDialog scissorDialog = catText("who would keep scissors in a safe ??? i guess i'll take them..", "catNorm.PNG", scissorButton);
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
                    JDialog gotScissors=catText("i've already taken the scissors that were in here.","catNorm.PNG",gotButton);
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
        JPanel carpetPanel=new JPanel();
        JLabel carpetLabel=showImage("carpetCloser.png",roomPanel,carpetPanel);
        if (scissors==0){
            JButton scisButton=new JButton("LEAVE");
            JDialog noScisDialog=catText("i need something to open this box with...","catNorm.PNG",scisButton);
            scisButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    noScisDialog.dispose();
                    carpetPanel.setVisible(false);
                    roomPanel.setVisible(true);
                }
            });
        }
        else if (!carpetDone){
            JButton colourButton=new JButton("OPEN");
            JDialog colourDialog=catText("i can open this with the scissors !","catHappy.PNG",colourButton);
            colourButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    colourDialog.dispose();
                    if (colourPanel == null) {
                        colourPanel = new JPanel();
                        colourLabel = showImage("colour.png", carpetPanel, colourPanel);
                    } else {
                        colourPanel.setVisible(true);
                        carpetPanel.setVisible(false);
                    }
                    int[] colourCode = {0, 0, 0, 0, 0, 0};
                    int[] answerCode = {4, 1, 4, 3, 2, 3};
                    JButton red = createButton("red.PNG", 100, 30, colourLabel);
                    JButton yellow = createButton("yellow.PNG", 410, 25, colourLabel);
                    JButton green = createButton("green.PNG", 110, 312, colourLabel);
                    JButton blue = createButton("blue.PNG", 420, 300, colourLabel);
                    JOptionPane.showMessageDialog(colourPanel, "Click the buttons in the correct order.");
                    red.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            colourDone(num,1,red);
                            if (num > 5) {
                                for (int i = 0; i < 6; i++) {
                                    colourCode[i] = 0;
                                }
                                num=1;
                                colourCode[0] = 1;
                            } else {
                                colourCode[num] = 1;
                                num++;
                            }
                            System.out.println(Arrays.toString(colourCode));

                        }
                    });
                    yellow.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            colourDone(num,2,yellow);
                            if (num > 5) {
                                for (int i = 0; i < 6; i++) {
                                    colourCode[i] = 0;
                                }
                                num=1;
                                colourCode[0] = 2;
                            }
                            else {
                                colourCode[num] = 2;
                                num++;
                            }
                            System.out.println(Arrays.toString(colourCode));
                        }
                    });
                    green.addActionListener(new ActionListener() { //CHECK TO SEE TO FIX NUM=0
                        public void actionPerformed(ActionEvent e) {
                            colourDone(num,3,green);
                            if (num >= 5) {
                                if (num==5) {
                                    colourCode[5] = 3;
                                }
                                if (Arrays.equals(colourCode, answerCode)) {
                                    donePanel=new JPanel();
                                    JLabel doneLabel = showImage("clrDone.png",colourPanel,donePanel);
                                    leaveButton(donePanel, roomPanel, doneLabel);
                                    carpetDone=true;
                                }
                                for (int i = 0; i < 6; i++) {
                                    colourCode[i] = 0;
                                }
                                num=0;
                            }
                            else {
                                colourCode[num] = 3;
                                num++;
                            }
                            System.out.println(Arrays.toString(colourCode));
                        }
                    });
                    blue.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            colourDone(num,4,blue);
                            if (num > 5) {
                                for (int i = 0; i < 6; i++) {
                                    colourCode[i] = 0;
                                }
                                colourCode[0]=4;
                                num=1;
                            }
                            else {
                                colourCode[num] = 4;
                                num++;
                            }
                            System.out.println(Arrays.toString(colourCode));
                        }
                    });
                    leaveButton(colourPanel, roomPanel, colourLabel);
                }
            });
        }
        else{
            donePanel.setVisible(true);
        }
    }
    private void colourDone(int num,int colour, JButton button){
        switch(colour){
            case 1: button.setIcon(new ImageIcon(getClass().getResource("redClick.png")));
                    break;
            case 2: button.setIcon(new ImageIcon(getClass().getResource("yellowClick.png")));
                    break;
            case 3: button.setIcon(new ImageIcon(getClass().getResource("greenClick.png")));
                    break;
            case 4: button.setIcon(new ImageIcon(getClass().getResource("blueClick.png")));
                    break;
        }
        Timer timer1 = new Timer(250, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(num==5) {
                    colourLabel.setVisible(false);
                    flashLabel = showImage("clrSix.png", colourPanel);
                    Timer timer2 = new Timer(500, new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            colourLabel.setVisible(true);
                            flashLabel.setVisible(false);
                            flashLabel = null;
                        }

                    });
                    timer2.start();
                    timer2.setRepeats(false);
                }
                switch(colour){
                    case 1: button.setIcon(new ImageIcon(getClass().getResource("red.png")));
                            break;
                    case 2: button.setIcon(new ImageIcon(getClass().getResource("yellow.png")));
                            break;
                    case 3: button.setIcon(new ImageIcon(getClass().getResource("green.png")));
                            break;
                    case 4: button.setIcon(new ImageIcon(getClass().getResource("blue.png")));
                            break;
                }
            }

        });
        timer1.start();
        timer1.setRepeats(false);
    }

    private void doorClicked(){
        JPanel doorPanel=new JPanel();
        doorLabel=showImage("doorCloser.png",roomPanel,doorPanel);
        if (key==0){
            JButton okButton=new JButton("OK");
            JDialog keyDialog=catText("hm...... looks like i need a key to open this door.", "catHm.PNG", okButton );
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
                doorLabel.setVisible(false);
                doorLabel=showImage("free.png",doorPanel);
                JButton nextButton=new JButton("NEXT");
                JDialog freeDialog=catText("yay ! i'm finally free !!!!!!", "catHappier.PNG", nextButton);
                nextButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        freeDialog.dispose(); // Close the dialog
                        doorLabel.setVisible(false);
                        doorLabel=showImage("what.png",doorPanel);
                        JButton next2Button = new JButton("NEXT");
                        JDialog waitDialog=catText("wait....... what ?", "catShock.PNG", next2Button);
                        next2Button.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                waitDialog.dispose();
                                doorLabel.setVisible(false);
                                doorLabel=showImage("end.png",doorPanel);
                                JButton leave=createButton("awakeEx.png",200,300,doorLabel);
                                leave.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        doorPanel.setVisible(false);
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
    private void clockClicked(){
        JPanel clockPanel=new JPanel();
        clockPanel.setLayout(null);
        clockPanel.setSize(800,600);
        baseFrame.add(clockPanel);
        Icon clockIcon = new ImageIcon(this.getClass().getResource("clock.GIF"));
        JLabel label = new JLabel(clockIcon);
        label.setBounds(0, 0, 800, 600);
        clockPanel.add(label);
        clockPanel.setVisible(true);
        leaveButton(clockPanel,roomPanel,label);
        roomPanel.setVisible(false);
    }
    private void bedTableClicked(){
        JPanel bedTablePanel=new JPanel();
        JLabel bedTableLabel=showImage("book.png",roomPanel,bedTablePanel);
        JButton ok=new JButton("OK");
        if (bTable==0) {
            JDialog bedTableDialog = catText("this is a weird book, all the pages are ripped out except for this one.", "catUgh.png", ok);
            ok.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    bedTableDialog.dispose();
                    bTable = 1;
                    bedTablePanel.setVisible(false);
                    roomPanel.setVisible(true);
                }
            });
        }
        else{
            JDialog bedTableDialog = catText("hm...maybe these colours can help me escape.", "catHm.PNG", ok);
            ok.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    bedTableDialog.dispose();
                    bTable=0;
                    bedTablePanel.setVisible(false);
                    roomPanel.setVisible(true);
                }
            });
        }
    }
}
