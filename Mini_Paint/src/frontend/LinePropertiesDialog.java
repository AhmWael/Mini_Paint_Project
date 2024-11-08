package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LinePropertiesDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField x1TF;
    private JTextField y1TF;
    private JTextField x2TF;
    private JTextField y2TF;
    private String status;
    private Canvas canvas;

    public LinePropertiesDialog(Canvas canvas) {
        this.canvas = canvas;
        setContentPane(contentPane);
        setTitle("Line Segment Properties");
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
        String x1 = x1TF.getText();
        String y1 = y1TF.getText();
        String x2 = x2TF.getText();
        String y2 = y2TF.getText();
        if (x1.isEmpty() || y1.isEmpty() || x2.isEmpty() || y2.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else if (!x1.matches("^[0-9]*$") || !y1.matches("^[0-9]*$") || !x2.matches("^[0-9]*$") || !y2.matches("^[0-9]*$")) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else if (Integer.parseInt(x1) > canvas.getWidth() || Integer.parseInt(y1) > canvas.getHeight() || Integer.parseInt(x2) > canvas.getWidth() || Integer.parseInt(y2) > canvas.getHeight()) {
            JOptionPane.showMessageDialog(this, "Coordinates out of bound!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        setVisible(false);
    }

    private void onCancel() {
        status = "Cancel";
        dispose();
    }

    public String getx1TF() {
        return x1TF.getText();
    }

    public String gety1TF() {
        return y1TF.getText();
    }

    public String getx2TF() {
        return x2TF.getText();
    }

    public String gety2TF() {
        return y2TF.getText();
    }

    public String getStatus() {
        return status;
    }
}
