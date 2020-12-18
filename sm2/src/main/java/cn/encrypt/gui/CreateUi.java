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

public class CreateUi extends JFrame {
    //定义组件
    JPanel jp1,jp2,jp3=null;
    JLabel jlb2,jlb3,jlb4,jlb5=null;
    JTextArea jta1,jta2=null;

    public static void main(String[] args) throws IOException {
        CreateUi  ui=new CreateUi();
    }


    //****************************事件判断**********************

    //构造函数
    public  CreateUi() throws IOException { //不能申明为void!!!!!否则弹不出新界面
        //创建组件

        //jp1=new JPanel();
        jp2=new JPanel();
        jp3=new JPanel();

        //jlb1=new JLabel("原文：");
        jlb2=new JLabel("公钥：");
        jlb3=new JLabel("秘钥：");

         SM2KeyVO key = generateSM2Key();


        //System.out.print(key.getPubHexInSoft().toString()+"\n");

        jta1 =new JTextArea(key.getPubHexInSoft().toString(),1,30);
        jta2 =new JTextArea(key.getPriHexInSoft().toString(),1,30);



        //jp1.add(jlb1);
        //jp1.add(jlb4);


        jp2.add(jlb2);
        jp2.add(jta1);


        jp3.add(jlb3);
        jp3.add(jta2);


        //this.add(jp1);
        this.add(jp2);
        this.add(jp3);


        //设置布局管理器
        this.setLayout(new GridLayout(2,2));
        this.setTitle("秘钥生成");
        this.setSize(800,400);
        this.setLocation(200, 200);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static SM2KeyVO generateSM2Key() throws IOException {
        SM2KeyVO sm2KeyVO = SM2EncDecUtils.generateKeyPair();
        return sm2KeyVO;
    }

}