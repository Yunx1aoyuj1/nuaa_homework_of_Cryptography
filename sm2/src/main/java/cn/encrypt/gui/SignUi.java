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

public class SignUi extends JFrame {
    //定义组件
    JPanel jp1,jp2,jp3=null;
    JLabel jlb1,jlb2,jlb3,jlb4,jlb5,jlb6=null;
    JTextArea jta1,jta2,jta3=null;

    public static void main(String[] args){
        SignUi  ui=new SignUi();
    }


    //****************************事件判断**********************

    //构造函数
    public  SignUi(){}
    public  SignUi(String Massage,String Psk) throws Exception { //不能申明为void!!!!!否则弹不出新界面
        //psk个人私钥
        //创建组件

        jp1=new JPanel();
        jp2=new JPanel();
        jp3=new JPanel();

        jlb1=new JLabel("原文：");
        jlb2=new JLabel("私有签名秘钥：");
        jlb3=new JLabel("签名：");


        String Massage_utf8 = URLEncoder.encode(Massage, "utf-8");//utf-8 to string
        //System.out.print(Psk+"\n");
        String massage = Util.byteToHex(Massage_utf8.getBytes());
        SM2SignVO sign = genSM2Signature(Psk,massage);

        jta1 =new JTextArea(Massage,1,30);
        jta2 =new JTextArea(Psk,1,30);

        //System.out.print(cipherText+"\n");
        jta3 =new JTextArea(sign.getSm2_signForSoft(),1,30);

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
        this.setTitle("签名");
        this.setSize(800,400);
        this.setLocation(200, 200);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }


    public static SM2SignVO genSM2Signature(String priKey, String sourceData) throws Exception {
        SM2SignVO sign = SM2SignVerUtils.Sign2SM2(Util.hexToByte(priKey), Util.hexToByte(sourceData));
        return sign;
    }
}