package cn.encrypt.gui;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JButton;
import java.awt.color.*;
import javax.swing.JOptionPane;

import cn.encrypt.utils.Util;
import cn.encrypt.utils.sm2.SM2EncDecUtils;
import cn.encrypt.utils.sm2.SM2KeyVO;
import cn.encrypt.utils.sm2.SM2SignVO;
import cn.encrypt.utils.sm2.SM2SignVerUtils;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.util.encoders.Hex;

import java.io.IOException;
import java.net.URLEncoder;

public class EncryptUi extends JFrame {
    //定义组件
    JPanel jp1,jp2,jp3=null;
    JLabel jlb1,jlb2,jlb3=null;
    JTextArea jta1,jta2,jta3=null;

    public static void main(String[] args) throws IOException {
        EncryptUi  ui=new EncryptUi();
    }


    //****************************事件判断**********************

    //构造函数
    public  EncryptUi() throws IOException {
        //创建组件
        String Massage="阿巴阿巴";
        String Pk="04673c514841f05c1e2ca0dc02fa4b8760619900c54400ffecb3d4842c0c01884b5951e3cff806c9137a0da38317acef43ae79d4edc94f9fad874f29ef1ad01088";
        jp1=new JPanel();
        jp2=new JPanel();
        jp3=new JPanel();

        jlb1=new JLabel("原文：");
        jlb2=new JLabel("公钥：");
        jlb3=new JLabel("秘文：");

        String Massage_utf8 = URLEncoder.encode(Massage, "utf-8");

        jta1 =new JTextArea(Massage,1,30);
        jta2 =new JTextArea(Pk,1,30);
        System.out.print(Massage_utf8+"\n");

        String cipherText = SM2Enc(Pk,Massage_utf8);

        //System.out.print(cipherText+"\n");
        jta3 =new JTextArea(cipherText,1,30);

        jp1.add(jlb1);
        jp1.add(jta1);


        jp2.add(jlb2);
        jp2.add(jta2);


        jp3.add(jlb3);
        jp3.add(jta3);


        this.add(jp1);
        this.add(jp2);
        this.add(jp3);


        //设置布局管理器
        this.setLayout(new GridLayout(4,3));
        this.setTitle("加密");
        this.setSize(800,400);
        this.setLocation(200, 200);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public  EncryptUi(String Massage,String Pk) throws IOException { //不能申明为void!!!!!否则弹不出新界面
        //创建组件

        jp1=new JPanel();
        jp2=new JPanel();
        jp3=new JPanel();

        jlb1=new JLabel("原文：");
        jlb2=new JLabel("公钥：");
        jlb3=new JLabel("秘文：");


        String Massage_utf8 = URLEncoder.encode(Massage, "utf-8");//utf-8 to string

        jta1 =new JTextArea(Massage,1,30);
        jta2 =new JTextArea(Pk,1,30);
        //System.out.print(Massage_utf8+"\n");

        String cipherText = SM2Enc(Pk,Massage_utf8);

        //System.out.print(cipherText+"\n");
        jta3 =new JTextArea(cipherText,1,30);

        jp1.add(jlb1);
        jp1.add(jta1);


        jp2.add(jlb2);
        jp2.add(jta2);


        jp3.add(jlb3);
        jp3.add(jta3);


        this.add(jp1);
        this.add(jp2);
        this.add(jp3);


        //设置布局管理器
        this.setLayout(new GridLayout(4,3));
        this.setTitle("加密");
        this.setSize(800,400);
        this.setLocation(200, 200);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static String SM2Enc(String pubKey, String src) throws   IOException  {
        String encrypt = SM2EncDecUtils.encrypt(Util.hexStringToBytes(pubKey), src.getBytes());
        //删除04
        encrypt=encrypt.substring(2,encrypt.length());
        return encrypt;
    }
}
/*
public class JTextAreaDemo
{
    public static void main(String[] agrs)
    {
        JFrame frame=new JFrame("Java文本域组件示例");    //创建Frame窗口
        JPanel jp=new JPanel();    //创建一个JPanel对象
        JTextArea jta=new JTextArea("请输入内容",7,30);
        jta.setLineWrap(true);    //设置文本域中的文本为自动换行
        jta.setForeground(Color.BLACK);    //设置组件的背景色
        jta.setFont(new Font("楷体",Font.BOLD,16));    //修改字体样式
        jta.setBackground(Color.YELLOW);    //设置按钮背景色
        JScrollPane jsp=new JScrollPane(jta);    //将文本域放入滚动窗口
        Dimension size=jta.getPreferredSize();    //获得文本域的首选大小
        jsp.setBounds(110,90,size.width,size.height);
        jp.add(jsp);    //将JScrollPane添加到JPanel容器中
        frame.add(jp);    //将JPanel容器添加到JFrame容器中
        frame.setBackground(Color.LIGHT_GRAY);
        frame.setSize(400,200);    //设置JFrame容器的大小
        frame.setVisible(true);
    }
}
* */