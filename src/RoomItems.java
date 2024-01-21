import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
public class RoomItems {
    protected JLabel roomLabel;
    protected JFrame baseFrame;
    protected JPanel roomPanel;

    RoomItems(JFrame frame, JLabel label, JPanel panel){
        baseFrame=frame;
        roomLabel=label;
        roomPanel=panel;
    }
    protected JLabel showImage(String imageName,JPanel ogPanel, JPanel newPanel){
        ImageIcon image = new ImageIcon(getClass().getResource(imageName));
        Image pic = image.getImage();
        Image newimg = pic.getScaledInstance(800, 600,Image.SCALE_SMOOTH);
        image = new ImageIcon(newimg);

        newPanel.setSize(800, 600);
        newPanel.setLayout(null);
        baseFrame.add(newPanel);

        JLabel imageLabel=new JLabel(image);
        imageLabel.setLayout(null);
        imageLabel.setSize(800,600);
        newPanel.add(imageLabel);
        ogPanel.setVisible(false);
        return imageLabel;
    }
    protected JLabel showImage(String imageName,JPanel panel){
        ImageIcon image = new ImageIcon(getClass().getResource(imageName));
        Image pic = image.getImage();
        Image newimg = pic.getScaledInstance(800, 600,Image.SCALE_FAST);
        image = new ImageIcon(newimg);

        JLabel imageLabel=new JLabel(image);
        imageLabel.setLayout(null);
        imageLabel.setSize(800,600);
        panel.add(imageLabel);
        return imageLabel;
    }
    protected JButton createButton(String imageName, int x, int y, JLabel label){
        JButton button=new JButton(new ImageIcon(getClass().getResource(imageName)));
        button.setBounds(x,y, button.getIcon().getIconWidth(), button.getIcon().getIconHeight());
        button.setBorderPainted(false); //maybe get rid of
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        label.add(button);
        return button;
    }
    protected JDialog catText(String message, String imageName,JButton button){
        JDialog catDialog = new JDialog(baseFrame, "You");
        ImageIcon cat=new ImageIcon(getClass().getResource(imageName));
        catDialog.setLayout(new BorderLayout());
        catDialog.setPreferredSize(new Dimension(600, 200)); //size of dialog
        JPanel catPanel=new JPanel();
        JLabel imageLabel=new JLabel(cat);
        catPanel.add(imageLabel);
        JLabel textLabel=new JLabel(message);
        catPanel.add(textLabel);

        int frameX = baseFrame.getLocation().x+100;
        int frameY = baseFrame.getLocation().y+220;

        catDialog.setLocation(frameX,frameY);
        catDialog.setUndecorated(true);
        catDialog.add(catPanel, BorderLayout.CENTER);
        catDialog.add(button, BorderLayout.SOUTH);
        catDialog.pack();
        catDialog.setVisible(true);
        return catDialog;
    }

    protected void leaveButton(JPanel oldPanel, JPanel newPanel, JLabel label){
        JButton leave=createButton("leaveEx.png",500,500,label);
        leave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                oldPanel.setVisible(false);
                newPanel.setVisible(true);
            }
        });
    }
}
