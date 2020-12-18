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

public class VerifyUi extends JFrame {
    //定义组件
    JPanel jp1,jp2,jp3=null;
    JLabel jlb1,jlb2,jlb3,jlb4,jlb5,jlb6=null;

    public static void main(String[] args){
        VerifyUi  ui=new VerifyUi();
    }


    //****************************事件判断**********************

    //构造函数
    public  VerifyUi(){}
    public  VerifyUi(String Massage,String Ppk,String sign) throws Exception { //不能申明为void!!!!!否则弹不出新界面
        //psk个人私钥
        //创建组件

        jp1=new JPanel();
        jp2=new JPanel();
        jp3=new JPanel();

        jlb1=new JLabel("原文：");
        jlb2=new JLabel("验证秘钥：");
        jlb3=new JLabel("是否一致");


        jlb4=new JLabel(Massage);
        jlb5=new JLabel(Ppk);
        //System.out.print(Psk+"\n");
        String Massage_utf8 = URLEncoder.encode(Massage, "utf-8");//utf-8 to string
        String massage = Util.byteToHex(Massage_utf8.getBytes());
        Boolean bool = verifySM2Signature(Ppk,massage,sign);

        //System.out.print(cipherText+"\n");
        if(bool.booleanValue() == Boolean.TRUE){
            jlb6=new JLabel("一致");
        }
        else{
            jlb6=new JLabel("不一致");
        }

        jp1.add(jlb1);
        jp1.add(jlb4);


        jp2.add(jlb2);
        jp2.add(jlb5);


        jp3.add(jlb3);
        jp3.add(jlb6);


        this.add(jp1);
        this.add(jp2);
        this.add(jp3);


        //设置布局管理器
        this.setLayout(new GridLayout(4,3));
        this.setTitle("验证");
        this.setSize(800,400);
        this.setLocation(200, 200);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }


    public static boolean verifySM2Signature(String pubKey, String sourceData, String Sign) {
        SM2SignVO verify = SM2SignVerUtils.VerifySignSM2(Util.hexStringToBytes(pubKey), Util.hexToByte(sourceData), Util.hexToByte(Sign));
        return verify.isVerify();
    }
}