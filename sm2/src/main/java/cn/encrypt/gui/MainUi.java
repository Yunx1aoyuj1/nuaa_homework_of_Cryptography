package cn.encrypt.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
/*
* main ui主要的入口ui
* 点击相应的button会出现子ui
* 可以产生公私钥
* */


public class MainUi extends JFrame implements ActionListener {

    //定义组件
    JButton jb1,jb2,jb3=null;
    JRadioButton jrb1,jrb2,jrb3,jrb4,jrb5=null;
    JPanel jp1,jp2,jp3,jp4,jp5,jp6=null;
    JTextField jtf1,jtf2,jtf3=null;
    JLabel jlb1,jlb2,jlb3,jlb4,jlb5=null;
    ButtonGroup bg=null;
    //教程是这么写的，我照猫画虎一下
    //原教程是学生管理系统

    public static void main(String[] args) {

        MainUi mUI=new MainUi();
    }

    public MainUi() {
        //创建组件
        jb1=new JButton("输入");
        jb2=new JButton("重置");
        jb3=new JButton("退出");

        //设置监听
        jb1.addActionListener(this);
        jb2.addActionListener(this);
        jb3.addActionListener(this);


        //添加单选button
        jrb1=new JRadioButton("加密");
        jrb2=new JRadioButton("解密");
        jrb3=new JRadioButton("签名");
        jrb4=new JRadioButton("认证");
        jrb5=new JRadioButton("产生密钥");
        bg=new ButtonGroup();
        bg.add(jrb1);
        bg.add(jrb2);
        bg.add(jrb3);
        bg.add(jrb4);
        bg.add(jrb5);
        jrb1.setSelected(true);  //初始页面默认选择权限为加密

        //行逻辑组件
        jp1=new JPanel();
        jp2=new JPanel();
        jp3=new JPanel();
        jp4=new JPanel();
        jp5=new JPanel();
        jp6=new JPanel();

        //文本标签
        jlb1=new JLabel("原           文：");
        jlb2=new JLabel("公           钥：");
        jlb3=new JLabel("私           钥：");
        jlb4=new JLabel("方           式：");
        jlb5=new JLabel("认证时公钥填认证公钥，私钥填签名，数据填原文。签名时私钥填签名私钥，数据填原文");


        //文本输入块
        jtf1=new JTextField(30);
        jtf2=new JTextField(30);
        jtf3=new JTextField(30);

        //加入到JPanel中
        jp1.add(jlb1);
        jp1.add(jtf1);//第一行

        jp2.add(jlb2);
        jp2.add(jtf2);// row 2

        jp3.add(jlb3);
        jp3.add(jtf3);// row 3


        jp4.add(jlb4);      //添加标签
        jp4.add(jrb1);
        jp4.add(jrb2);
        jp4.add(jrb3);
        jp4.add(jrb4);
        jp4.add(jrb5);//row 4



        jp5.add(jb1);       //添加按钮
        jp5.add(jb2);
        jp5.add(jb3);// row 5

        jp6.add(jlb5);

        //加入JFrame中
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);
        this.add(jp4);
        this.add(jp5);
        this.add(jp6);

        this.setLayout(new GridLayout(6,1));            //选择GridLayout布局管理器
        this.setTitle("密码学课设");
        this.setSize(600,250);
        this.setLocation(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //设置当关闭窗口时，保证JVM也退出
        this.setVisible(true);
        this.setResizable(true);
    }

    public void actionPerformed(ActionEvent e) {            //事件判断
        if(e.getActionCommand()=="输入")
        {
            if(jrb1.isSelected())
            {
                try {
                    encrypt();//连接到加密的方法 页面
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }else if(jrb2.isSelected())
            {
                try {
                    decrypt();                             //连接到解密的方法 页面
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }else if(jrb3.isSelected())
            {
                try {
                    Sign();//连接到签名的方法 页面
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }else if(jrb4.isSelected())
            {
                try {
                    Verify(); //连接到认证的方法 页面
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }else if(jrb5.isSelected())
            {
                try {
                    CreateKey();//连接到秘钥产生的方法 页面
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        }else if(e.getActionCommand()=="重置")
        {
            clear();
        }else if(e.getActionCommand()=="退出")
        {
            System.exit(0);
        }
    }


    void encrypt() throws IOException {//加密
        if( jtf1.getText().isEmpty()||jtf1.getText().isEmpty()){

            JOptionPane.showMessageDialog(null, "有参数为空！", "失败", JOptionPane.WARNING_MESSAGE);
        }else {
            JOptionPane.showMessageDialog(null, "成功", "成功", JOptionPane.INFORMATION_MESSAGE);
            EncryptUi ui = new EncryptUi(jtf1.getText(),jtf2.getText());
        }
    }

    void decrypt() throws IOException {//解密
        if( jtf1.getText().isEmpty()||jtf3.getText().isEmpty()){

            JOptionPane.showMessageDialog(null, "有参数为空！", "失败", JOptionPane.WARNING_MESSAGE);
        }else {
            JOptionPane.showMessageDialog(null, "成功", "成功", JOptionPane.INFORMATION_MESSAGE);
            DecryptUi ui = new DecryptUi(jtf1.getText(),jtf3.getText());
        }
    }

    void CreateKey() throws IOException {//产生秘钥对
            CreateUi ui = new CreateUi();
    }
    //清空文本框和密码框
    public  void clear(){
        jtf1.setText("");
        jtf2.setText("");
        jtf3.setText("");
    }

    void Sign()  throws Exception {//签名
        if( jtf1.getText().isEmpty()||jtf3.getText().isEmpty()){

            JOptionPane.showMessageDialog(null, "有参数为空！", "失败", JOptionPane.WARNING_MESSAGE);
        }else {
            JOptionPane.showMessageDialog(null, "成功", "成功", JOptionPane.INFORMATION_MESSAGE);
            SignUi ui = new SignUi(jtf1.getText(),jtf3.getText());
        }
    }

    void Verify() throws Exception {//验证
        if( jtf1.getText().isEmpty()||jtf2.getText().isEmpty()||jtf3.getText().isEmpty()){

            JOptionPane.showMessageDialog(null, "有参数为空！", "失败", JOptionPane.WARNING_MESSAGE);
        }else {
            JOptionPane.showMessageDialog(null, "成功", "成功", JOptionPane.INFORMATION_MESSAGE);
            VerifyUi ui = new VerifyUi(jtf1.getText(),jtf2.getText(),jtf3.getText());
        }
    }

}
/*
* //公钥加密
    public static String SM2Enc(String pubKey, String src) throws IOException {
        String encrypt = SM2EncDecUtils.encrypt(Util.hexStringToBytes(pubKey), src.getBytes());
        //删除04
        encrypt=encrypt.substring(2,encrypt.length());
        return encrypt;
    }

    //私钥解密
    public static String SM2Dec(String priKey, String encryptedData) throws IOException {
        //填充04
        encryptedData="04"+encryptedData;
        byte[] decrypt = SM2EncDecUtils.decrypt(Util.hexStringToBytes(priKey), Util.hexStringToBytes(encryptedData));
        return new String(decrypt);
    }

    //私钥签名,参数二:原串必须是hex!!!!因为是直接用于计算签名的,可能是SM3串,也可能是普通串转Hex

    public static SM2SignVO genSM2Signature(String priKey, String sourceData) throws Exception {
        SM2SignVO sign = SM2SignVerUtils.Sign2SM2(Util.hexToByte(priKey), Util.hexToByte(sourceData));
        return sign;
    }

    //公钥验签,参数二:原串必须是hex!!!!因为是直接用于计算签名的,可能是SM3串,也可能是普通串转Hex
    public static boolean verifySM2Signature(String pubKey, String sourceData, String hardSign) {
        SM2SignVO verify = SM2SignVerUtils.VerifySignSM2(Util.hexStringToBytes(pubKey), Util.hexToByte(sourceData), Util.hexToByte(hardSign));
        return verify.isVerify();
    }

    //SM2签名Hard转soft
    public static String SM2SignHardToSoft(String hardSign) {
        byte[] bytes = Util.hexToByte(hardSign);
        byte[] r = new byte[bytes.length / 2];
        byte[] s = new byte[bytes.length / 2];
        System.arraycopy(bytes, 0, r, 0, bytes.length / 2);
        System.arraycopy(bytes, bytes.length / 2, s, 0, bytes.length / 2);
        ASN1Integer d_r = new ASN1Integer(Util.byteConvertInteger(r));
        ASN1Integer d_s = new ASN1Integer(Util.byteConvertInteger(s));
        ASN1EncodableVector v2 = new ASN1EncodableVector();
        v2.add(d_r);
        v2.add(d_s);
        DERSequence sign = new DERSequence(v2);

        String result = null;
        try {
            result = Util.byteToHex(sign.getEncoded());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        //SM2加密机转软加密编码格式
        //return SM2SignHardKeyHead+hardSign.substring(0, hardSign.length()/2)+SM2SignHardKeyMid+hardSign.substring(hardSign.length()/2);
        return result;
    }

    //摘要计算
    public static String generateSM3HASH(String src) {
        byte[] md = new byte[32];
        byte[] msg1 = src.getBytes();
        //System.out.println(Util.byteToHex(msg1));
        SM3Digest sm3 = new SM3Digest();
        sm3.update(msg1, 0, msg1.length);
        sm3.doFinal(md, 0);
        String s = new String(Hex.encode(md));
        return s.toUpperCase();
    }
* */