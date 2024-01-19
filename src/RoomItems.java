import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
public class RoomItems {
    protected JLabel roomLabel;
    protected JFrame frame;
    protected JPanel roomPanel;
    RoomItems(JFrame baseFrame, JLabel label, JPanel panel){
        frame=baseFrame;
        roomLabel=label;
        roomPanel=panel;
    }
    protected JLabel showImage(String imageName,JPanel ogPanel, JPanel newPanel){
        ImageIcon image = new ImageIcon(getClass().getResource(imageName));
        Image pic = image.getImage();
        Image newimg = pic.getScaledInstance(800, 600,Image.SCALE_DEFAULT);
        image = new ImageIcon(newimg);
        JLabel imageLabel=new JLabel(image);
        imageLabel.setLayout(null);
        imageLabel.setSize(800,600);
        newPanel.add(imageLabel);
        ogPanel.setVisible(false);
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
        JDialog catDialog = new JDialog(frame, "You");
        ImageIcon cat=new ImageIcon(getClass().getResource(imageName));
        catDialog.setLayout(new BorderLayout());
        catDialog.setPreferredSize(new Dimension(600, 300)); //size of dialog
        JPanel catPanel=new JPanel();
        JLabel imageLabel=new JLabel(cat);
        catPanel.add(imageLabel);
        JLabel textLabel=new JLabel(message);
        catPanel.add(textLabel);
        catDialog.add(catPanel, BorderLayout.CENTER);
        catDialog.add(button, BorderLayout.SOUTH);
        catDialog.pack();
        catDialog.setLocationRelativeTo(null); // Center the dialog on the screen
        catDialog.setVisible(true);
        return catDialog;
    }
    protected void messageText(String message, String title, String buttonLabel){

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
