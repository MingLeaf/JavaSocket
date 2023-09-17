package C_S;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MyServer extends JFrame implements ActionListener{
JTextArea jta;
JTextField jtf;
JButton jb;
JPanel jp;

ServerSocket ss;
Socket socket;
//����������
Scanner sc;
//���������
PrintWriter pw;

public MyServer()
{

jta = new JTextArea();
jtf = new JTextField(15);
jb = new JButton("send");
jp = new JPanel();

jp.add(jtf);
jp.add(jb);

JScrollPane jsp = new JScrollPane(jta);
this.add(jsp,BorderLayout.CENTER);
this.add(jp,BorderLayout.SOUTH);

jb.addActionListener(this);
jtf.addActionListener(this);

//չ��
this.setTitle("Server");
this.setSize(330,210);
this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
this.setVisible(true);

// ���Ŷ˿�
try {
ss = new ServerSocket(7520);//�˿ں�
System.out.println("�ȴ�����");
socket = ss.accept();
System.out.println("������");
//����������
sc = new Scanner(socket.getInputStream());
//���������
pw = new PrintWriter(socket.getOutputStream(),true);

} catch (IOException e) {

e.printStackTrace();
}

while(true)
{
//���ܿͻ��˷�������
String str = sc.nextLine();
//��ʾ�����ı�����
jta.append("Client says:"+str+"\r\n");
}
}

public static void main(String[] args)
{
new MyServer();
}

public void actionPerformed(ActionEvent e) {

if(e.getSource()==jb || e.getSource()==jtf)
{
//��ͻ��˷�������
pw.println(jtf.getText());
//������ı���������ʷ�����¼��Ϣ
jta.append("Server says:"+jtf.getText()+"\r\n");
//��յ����ı�������
jtf.setText("");

}
}

}