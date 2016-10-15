package TCPProgramming;
import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
/**
 * Created by Administrator on 15/10/2559.
 */
public class Server extends JFrame {
    InetAddress host;
    BufferedReader fromClient;
    JTextArea Txt_message = new JTextArea();
    DefaultCaret caret;
   PrintWriter toClient;


    Server() {
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(new JScrollPane(Txt_message), BorderLayout.CENTER);
        setSize(800,800);
        Txt_message.setFont(new Font("Tahoma", Font.BOLD, 25));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Server Side");
        setLocationRelativeTo(null);
        setVisible(true);
        caret = (DefaultCaret) Txt_message.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        runner();
        setResizable(false);
    }

    public static void main(String[] args) {
        new Server();
    }


    private void runner() {
        Txt_message.append("Status:Ready:\n");
        Txt_message.append("Wait for Client:\n");
        try {
            //สร้างการเชื่อมต่อผ่าน port 8080
            ServerSocket server = new ServerSocket(8080);
            //รอให้Client มาทำการเชื่อมต่อ
            Socket connected = server.accept();
            //รอๆๆๆๆๆๆๆๆๆๆๆๆๆๆๆ
            Txt_message.append("Successful Connected\n");
            System.out.println("\n");
            fromClient = new BufferedReader(new InputStreamReader(connected.getInputStream()));

            while (true){
                String message = fromClient.readLine();
                Txt_message.append("Message :"+message+"\n");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}