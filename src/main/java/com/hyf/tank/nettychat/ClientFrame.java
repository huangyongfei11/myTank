package com.hyf.tank.nettychat;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author hyf
 * @version [版本号, 2020/5/30]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ClientFrame extends Frame {

    public static ClientFrame INSTANCE = new ClientFrame();

    private TextArea ta = new TextArea();

    private TextField tf = new TextField();

    private Client c;
    private ClientFrame(){
        this.setSize(300,400);
        this.setLocation(400,20);
        this.add(ta,BorderLayout.CENTER);
        this.add(tf,BorderLayout.SOUTH);
        tf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.send(tf.getText());
                tf.setText("");
            }
        });
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e)
            {
                c.close("byte");
                System.exit(0);
            }
        });
    }
    public void connectServer(){
        c = new Client();
        c.connect();
    }

    public void update(String msg){
        ta.setText(ta.getText() + msg +"\r\n");
        tf.setText("");
    }



    public static void main(String[] args) {
        ClientFrame clientFrame =ClientFrame.INSTANCE;
        clientFrame.setVisible(true);
        clientFrame.connectServer();
    }
}
