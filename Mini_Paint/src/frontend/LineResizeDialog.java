package frontend;

import javax.swing.*;
import java.awt.event.*;

public class LineResizeDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField x2TF;
    private JTextField y2TF;
    private CanvasPanel canvas;
    private String status;

    public LineResizeDialog(CanvasPanel canvas, int x, int y) {
        this.canvas = canvas;
        setContentPane(contentPane);
        setTitle("Resize Line Segment");
        getRootPane().setDefaultButton(buttonOK);
        pack();
        setLocationRelativeTo(null);
        x2TF.setText(String.valueOf(x));
        y2TF.setText(String.valueOf(y));

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
        String x = x2TF.getText();
        String y = y2TF.getText();
        if (x.isEmpty() || y.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else if (!x.matches("^[0-9]*$") || !y.matches("^[0-9]*$")) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else if (Integer.parseInt(x) > canvas.getWidth() || Integer.parseInt(y) > canvas.getHeight()) {
            JOptionPane.showMessageDialog(this, "Coordinates out of bound!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        setVisible(false);
    }

    private void onCancel() {
        status = "Cancel";
        dispose();
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
