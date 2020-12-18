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
import java.net.URLDecoder;
import java.net.URLEncoder;

public class DecryptUi extends JFrame {
    //定义组件
    JPanel jp1,jp2,jp3=null;
    JLabel jlb1,jlb2,jlb3=null;
    JTextArea jta1,jta2,jta3=null;

    public static void main(String[] args) throws IOException {
        DecryptUi  ui=new DecryptUi();
    }


    //****************************事件判断**********************

    //构造函数
    public  DecryptUi() throws IOException {
        //创建组件
        //接口测试
        String Sk="09be52a23ea407748ed8aa78720dd9e30b9c70eefe633c7e621af67927c46720" ;
        String Massage_utf8="87cb1f53f8e62025d7927d66a378be8ada765b9345dad9ae59926232b44e8133c4ae8cfcf36569a141ec53216d05ddce58578bdb34035dc7b4d8d86b4bca17c6a0a2ee88d3ede613dcf6108899da8688e638ee726769f3adf0a7f135ac7a70361a13fb0deea413b6d4cbfc5772c0e12a8831e889b6dd9e0ce9892641c2cf959816ec19c1";
        jp1=new JPanel();
        jp2=new JPanel();
        jp3=new JPanel();

        jlb1=new JLabel("原文：");
        jlb2=new JLabel("私钥：");
        jlb3=new JLabel("秘文：");


        jta1 =new JTextArea(Massage_utf8,1,30);
        jta2 =new JTextArea(Sk,1,30);



        String cipherText = SM2Dec(Sk,Massage_utf8);
        cipherText=URLDecoder.decode(cipherText,"utf-8");
        System.out.print(cipherText+"\n");
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
        this.setTitle("解密");

        this.setSize(800,400);
        this.setLocation(200, 200);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public  DecryptUi(String Massage_utf8,String Sk) throws IOException { //不能申明为void!!!!!否则弹不出新界面
        //创建组件

        jp1=new JPanel();
        jp2=new JPanel();
        jp3=new JPanel();

        jlb1=new JLabel("秘文：");
        jlb2=new JLabel("私钥：");
        jlb3=new JLabel("原文：");


        jta1 =new JTextArea(Massage_utf8,1,30);
        jta2 =new JTextArea(Sk,1,30);



        String cipherText = SM2Dec(Sk,Massage_utf8);
        cipherText=URLDecoder.decode(cipherText,"utf-8");
        System.out.print(cipherText+"\n");
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
        this.setTitle("解密");
        this.setSize(800,400);
        this.setLocation(200, 200);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static String SM2Dec(String priKey, String encryptedData) throws IOException {
        //填充04
        encryptedData="04"+encryptedData;
        byte[] decrypt = SM2EncDecUtils.decrypt(Util.hexStringToBytes(priKey), Util.hexStringToBytes(encryptedData));
        return new String(decrypt);
    }
}