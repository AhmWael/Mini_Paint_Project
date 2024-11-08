package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SquarePropertiesDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField xTF;
    private JTextField yTF;
    private JTextField lengthTF;
    private String status;
    private Canvas canvas;

    public SquarePropertiesDialog(Canvas canvas) {
        this.canvas = canvas;
        setContentPane(contentPane);
        setTitle("Square Properties");
        getRootPane().setDefaultButton(buttonOK);
        pack();
        setLocationRelativeTo(null);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        setModal(true);
        setVisible(true);
    }

    private void onOK() {
        status = "OK";
        String x = xTF.getText();
        String y = yTF.getText();
        String length = lengthTF.getText();
        if (x.isEmpty() || y.isEmpty() || length.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else if (!x.matches("^[0-9]*$") || !y.matches("^[0-9]*$") || !length.matches("^[0-9]*$")) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else if (Integer.parseInt(x) > canvas.getWidth() || Integer.parseInt(y) > canvas.getHeight()
                || Integer.parseInt(x) + Integer.parseInt(length) > canvas.getWidth()
                || Integer.parseInt(y) + Integer.parseInt(length) > canvas.getHeight()) {
            JOptionPane.showMessageDialog(this, "Coordinates out of bound!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        setVisible(false);
    }

    private void onCancel() {
        status = "Cancel";
        dispose();
    }

    public String getxTF() {
        return xTF.getText();
    }

    public String getyTF() {
        return yTF.getText();
    }

    public String getLengthTF() {
        return lengthTF.getText();
    }

    public String getStatus() {
        return status;
    }
}
