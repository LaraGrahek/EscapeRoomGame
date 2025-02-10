import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/***
 * Class that represents items in the room, and contains common methods and properties across all items.
 * Customized by two classes.
 */
public class RoomItems {
    protected JLabel roomLabel;
    protected JFrame baseFrame;
    protected JPanel roomPanel;

    /**
     * Constructor that sets values of frame, label and panel
     * @param frame
     * @param label
     * @param panel
     */
    RoomItems(JFrame frame, JLabel label, JPanel panel){
        baseFrame=frame;
        roomLabel=label;
        roomPanel=panel;
    }

    /**
     * Creates and returns JLabel with image represented by imageName, added to newPanel. Hides ogPanel.
     * @param imageName name of the image to show
     * @param ogPanel panel to hide
     * @param newPanel new panel to add
     * @return JLabel representing image
     */
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
    /**
     * Creates and returns JLabel with image represented by imageName, added to panel.
     * @param imageName name of the image to show
     * @param panel new panel to add
     * @return JLabel representing image
     */
    protected JLabel showImage(String imageName,JPanel panel){
        ImageIcon image = new ImageIcon(getClass().getResource(imageName));
        Image pic = image.getImage();
        Image newimg = pic.getScaledInstance(800, 600,Image.SCALE_FAST);
        image = new ImageIcon(newimg);

        JLabel imageLabel=new JLabel(image);
        imageLabel.setLayout(null);
        imageLabel.setSize(800,600);
        imageLabel.setVisible(true);
        panel.add(imageLabel);
        return imageLabel;
    }

    /**
     *
     * @param imageName name of the image file to show in button
     * @param x x part of bounds
     * @param y y part of bounds
     * @param label JLabel to add the button to
     * @return JButton with bounds given by x and y, image and label given by imageName and label
     */
    protected JButton createButton(String imageName, int x, int y, JLabel label){
        JButton button=new JButton(new ImageIcon(getClass().getResource(imageName)));
        button.setBounds(x,y, button.getIcon().getIconWidth(), button.getIcon().getIconHeight());
        button.setBorderPainted(false); //maybe get rid of
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        label.add(button);
        return button;
    }

    /**
     * Create dialog with given message, image and button
     * @param message String message to show
     * @param imageName file name of image
     * @param button JButton to add to the dialog
     * @return JDialog
     */
    protected JDialog catText(String message, String imageName,JButton button){
        JDialog catDialog = new JDialog(baseFrame, "You");
        Image pic = (new ImageIcon(getClass().getResource(imageName))).getImage();
        Image newimg = pic.getScaledInstance(185, 140,Image.SCALE_FAST);
        ImageIcon cat = new ImageIcon(newimg);
        catDialog.setPreferredSize(new Dimension(610, 200)); //size of dialog
        JPanel catPanel=new JPanel();
        catPanel.setLayout(new BorderLayout());
        JLabel imageLabel=new JLabel(cat);
        catPanel.add(imageLabel,BorderLayout.WEST);
        JLabel textLabel=new JLabel(message);
        catPanel.add(textLabel,BorderLayout.CENTER);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        int frameX = baseFrame.getLocation().x+100;
        int frameY = baseFrame.getLocation().y+400;

        catDialog.setLocation(frameX,frameY);
        catDialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        catDialog.add(catPanel, BorderLayout.CENTER);
        catDialog.add(button, BorderLayout.SOUTH);
        catDialog.pack();
        catDialog.setVisible(true);
        return catDialog;
    }
    /**
     * Create dialog with given message, title and button
     * @param message String message to show
     * @param title title of the dialog
     * @param button JButton to add to the dialog
     * @return JDialog
     */
    protected JDialog msgText(String message, String title, JButton button){
        JDialog msgDialog=new JDialog(baseFrame,title);
        msgDialog.setPreferredSize(new Dimension(500, 100)); //size of dialog
        msgDialog.setLayout(new BorderLayout());
        JPanel msgPanel=new JPanel();
        msgPanel.add(new JLabel(message));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        int frameX = baseFrame.getLocation().x+150;
        int frameY = baseFrame.getLocation().y+400;

        msgDialog.setLocation(frameX,frameY);
        msgDialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        msgDialog.add(msgPanel);
        msgDialog.add(button,BorderLayout.SOUTH);
        msgDialog.pack();
        msgDialog.setVisible(true);
        return msgDialog;
    }

    protected void leaveButton(JPanel oldPanel, JPanel newPanel, JLabel label){
        JButton leave=createButton("back.PNG",570,450,label);
        leave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                oldPanel.setVisible(false);
                newPanel.setVisible(true);
            }
        });
    }
}
