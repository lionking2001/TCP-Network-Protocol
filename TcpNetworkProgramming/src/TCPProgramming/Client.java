package TCPProgramming;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

/**
 * Created by Administrator on 15/10/2559.
 */
public class Client extends JFrame implements ActionListener {
    JTextArea Txt_message = new JTextArea();
    JTextField Txt_send = new JTextField();
    PrintWriter toServer;
    DefaultCaret caret;


    Client() {

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(new JLabel("Conversation"), BorderLayout.WEST);
        panel.add(Txt_send, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
        add(new JScrollPane(Txt_message), BorderLayout.CENTER);
        setResizable(false);
        Font f = new Font("Tahoma", Font.BOLD, 20);
        Txt_send.setFont(f);
        Txt_message.setFont(f);
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Client Side");
        setLocationRelativeTo(null);
        setVisible(true);
        Txt_send.requestFocus();
        Txt_send.addActionListener(this);
        //ทำให้scroll เลื่อนได้
        caret = (DefaultCaret) Txt_message.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        runner();

    }

    public static void main(String[] args) {
        new Client();
    }

    public void runner() {
        //Connect to Server
        try {
            Socket client = new Socket(InetAddress.getLocalHost(), 8080);
            Txt_message.append("Connected to Server");
            //ส่งข้อมูล
            toServer = new PrintWriter(client.getOutputStream(), true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JTextField) {
            if (!Txt_send.getText().isEmpty()) {
                String Message = Txt_send.getText();
                Txt_message.append(Message + "\n");
                Txt_send.setText("");
                toServer.println(Message);
            }
        }
    }
}