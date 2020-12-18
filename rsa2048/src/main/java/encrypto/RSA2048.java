
/**
 * RSA加密算法的演示验证
 * RSA是一种分组加密算法
 * 注意:密钥对采用的长度决定了加密块的长度，我这里取的是2048，即256byte
 * 由于加密块的长度固定为256，因此明文的长度至多为256 - 11 = 245byte
 * 我这里明文长度取的是128byte，密文长度为256byte，它适合于小文件加密
 */
package encrypto;

import java.lang.*;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * @author liuhuabai,liuhuabai@163.com
 *
 */
class RSADemo {
    /**
     * 公钥
     */
    private RSAPublicKey publicKey;
    /**
     * 私钥
     */
    private RSAPrivateKey privateKey;
    /**
     * 用于加解密
     */
    private Cipher cipher;
    /**
     * 明文块的长度 它必须小于密文块的长度 - 11
     */
    private int originLength = 128;
    /**
     * 密文块的长度
     */
    private int encrytLength = 256;

    /**
     * 得到初始化的公钥和私钥
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     */
    public void initKey() throws NoSuchAlgorithmException, NoSuchPaddingException {
        //RSA加密算法：创建密钥对，长度采用2048
        KeyPairGenerator kg = KeyPairGenerator.getInstance("RSA");
        kg.initialize(encrytLength * 8);
        KeyPair keypair = kg.generateKeyPair();
        //分别得到公钥和私钥
        publicKey = (RSAPublicKey) keypair.getPublic();
        privateKey = (RSAPrivateKey) keypair.getPrivate();
    }
    /**
     * 将公钥保存至文件
     * @param file 待写入的文件
     * @return true 写入成功;false 写入失败
     */
    public boolean savePublicKey(File file) {
        return this.saveKey(publicKey, file);
    }
    /**
     * 将私钥保持至文件
     * @param file 待写入的文件
     * @return true 写入成功;false 写入失败
     */
    public boolean savePrivateKey(File file) {
        return this.saveKey(privateKey, file);
    }
    //将Key文件保持到文件中
    private boolean saveKey(Key key,File file) {
        boolean write;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            //System.out.println(key.getFormat());
            //公钥默认使用的是X.509编码，私钥默认采用的是PKCS #8编码
            byte [] encode = key.getEncoded();
            //注意，此处采用writeObject方法，读取时也要采用readObject方法
            oos.writeObject(encode);
            write = true;
        } catch (IOException e) {
            write = false;
        } finally {
            try {
                if(fos != null) fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return write;
    }
    /**
     * 从文件中得到公钥
     * @param file 保存公钥的文件
     */
    public void getPublicKey(File file) {
        getKey(file,0);
    }
    /**
     * 从文件中得到私钥
     * @param file 保存私钥的文件
     */
    public void getPrivateKey(File file) {
        getKey(file,1);
    }

    private void getKey(File file,int mode) {
        FileInputStream fis;
        try {
            //读取数据
            fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            byte [] keybyte = (byte[]) ois.readObject();
            //关闭资源
            ois.close();
            //默认编码
            KeyFactory keyfactory = KeyFactory.getInstance("RSA");
            //得到公钥或是私钥
            if(mode == 0) {
                X509EncodedKeySpec x509eks= new X509EncodedKeySpec(keybyte);
                publicKey = (RSAPublicKey) keyfactory.generatePublic(x509eks);
                //System.out.println(pk.getAlgorithm());
            } else {
                PKCS8EncodedKeySpec pkcs8eks = new PKCS8EncodedKeySpec(keybyte);
                privateKey = (RSAPrivateKey) keyfactory.generatePrivate(pkcs8eks);
                //System.out.println(pk.getAlgorithm());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 数据RSA加密
     * @param origin 明文
     * @return 密文
     */
    protected byte [] encrypt(byte [] origin) {
        //byte [] enc = new byte [encrytLength];
        byte [] enc = null;
        try {
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            enc = cipher.doFinal(origin);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return enc;
    }
    /**
     * 数据RSA解密
     * @param enc 密文
     * @return 明文
     */
    protected byte [] decrypt(byte [] enc) {
        //byte [] origin = new byte [originLength];
        byte [] origin = null;
        try {
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            origin = cipher.doFinal(enc);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return origin;
    }
    /**
     * 加密文件
     * @param origin 明文件
     * @throws IOException
     */
    public void encryptFile(File origin) throws IOException {
        FileInputStream fis = null;
        FileOutputStream fos = null;

        //读入
        fis = new FileInputStream(origin);
        BufferedInputStream bis = new BufferedInputStream(fis);
        byte [] originbyte = new byte [originLength];
        //写出
        fos = new FileOutputStream(new File(origin+".encrypt"));
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        byte [] encryptbyte;
        //int k;
        while(bis.read(originbyte) > 0) {
            encryptbyte = this.encrypt(originbyte);
            bos.write(encryptbyte);
            originbyte = new byte[originLength];
        }
        //压入
        bos.flush();
        //关闭资源
        if(fis != null) fis.close();
        if(fos != null) fos.close();
    }
    /**
     * 解密文件
     * @param encrypt 密文件
     * @throws IOException
     */
    public void decryptFile(File encrypt) throws IOException {

        FileInputStream fis = null;
        FileOutputStream fos = null;
        //读入
        fis = new FileInputStream(encrypt);
        BufferedInputStream bis = new BufferedInputStream(fis);
        byte [] encryptbyte = new byte [encrytLength];
        //写出
        fos = new FileOutputStream(new File(encrypt+".decrypt"));
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        byte [] originbyte;

        //int k;
        while(bis.read(encryptbyte) > 0) {
            originbyte = this.decrypt(encryptbyte);
            bos.write(originbyte);
            encryptbyte = new byte [encrytLength];
        }
        //压入
        bos.flush();
        //关闭资源
        if(fis != null) fis.close();
        if(fos != null) fos.close();
    }

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws Exception {
        RSADemo rsa = new RSADemo();
        long startTime = System.currentTimeMillis(); //程序开始记录时间
        rsa.initKey();


        rsa.encryptFile(new File("C:/Users/16645/Desktop/RSA2048/hello.txt"));
        rsa.decryptFile(new File("C:/Users/16645/Desktop/RSA2048/hello.txt.encrypt"));
        rsa.savePublicKey(new File("public.key"));
        rsa.savePrivateKey(new File("private.key"));
        long endTime = System.currentTimeMillis(); //程序结束记录时间
        long TotalTime = endTime - startTime; //总消耗时间
        //rsa.getPublicKey(new File("E:/public.key"));
        //rsa.getPrivateKey(new File("E:/private.key"));
        //rsa.encryptFile(new File("E:/hello.txt"));
        //rsa.decryptFile(new File("E:/hello.txt.encrypt"));
        System.out.print(TotalTime);
    }
}