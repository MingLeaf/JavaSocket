package C_S;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//�ͻ��˳���
public class MyClient extends JFrame implements ActionListener{

JTextArea jta;    //�ı�����
JTextField jtf;   //�ı���
JButton jb;       //��ť
JPanel jp;

Socket socket;
//����������
Scanner sc;
//���������
PrintWriter pw;

public MyClient()
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
this.setTitle("Client");
this.setSize(330,210);
this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
this.setVisible(true);

try {
//��������
socket = new Socket("localhost",7520);//IP��˿ں�,IPΪ������IP
//����������
sc = new Scanner(socket.getInputStream());

//���������
pw = new PrintWriter(socket.getOutputStream(),true);

} catch (UnknownHostException e) {

e.printStackTrace();
} catch (IOException e) {

e.printStackTrace();
}

while(true)
{
//���ܷ�������������
String str = sc.nextLine();
//��ʾ�ڶ����ı���
jta.append("Server says:"+str+"\r\n");
}

}

public static void main(String[] args) {
new MyClient();
}

public void actionPerformed(ActionEvent e) {

if(e.getSource()==jb || e.getSource()==jtf)
{
//���������������
pw.println(jtf.getText());
//��ʾ�ڶ����ı���
jta.append("Client says:"+jtf.getText()+"\r\n");
//��յ����ı���
jtf.setText("");
}
}
}

