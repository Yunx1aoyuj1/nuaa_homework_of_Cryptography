import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

public class testRSA {

    //待加密原文
    public static final String DATA = "abab";

    public static void main(String[] args) throws Exception {
        //long startTime = System.currentTimeMillis(); //程序开始记录时间
        Map<String, Object> keyMap = RSAUtil.initKey();

        RSAPublicKey rsaPublicKey = RSAUtil.getpublicKey(keyMap);
        RSAPrivateKey rsaPrivateKey = RSAUtil.getPrivateKey(keyMap);
        System.out.println("RSA PublicKey: " + rsaPublicKey);
        System.out.println("RSA PrivateKey: " + rsaPrivateKey);

        long startTime = System.currentTimeMillis(); //程序开始记录时间
        byte[] rsaResult = RSAUtil.encrypt(DATA.getBytes(), rsaPublicKey);
        System.out.println(DATA + "====>>>> RSA 加密>>>>====" + BytesToHex.fromBytesToHex(rsaResult));

        byte[] plainResult = RSAUtil.decrypt(rsaResult, rsaPrivateKey);
        System.out.println(DATA + "====>>>> RSA 解密>>>>====" + BytesToHex.fromBytesToHex(plainResult));
        long endTime = System.currentTimeMillis(); //程序结束记录时间
        long TotalTime = endTime - startTime; //总消耗时间
        System.out.print(TotalTime);
    }

    public void test() throws Exception {
        long startTime = System.currentTimeMillis(); //程序开始记录时间
        Map<String, Object> keyMap = RSAUtil.initKey();

        RSAPublicKey rsaPublicKey = RSAUtil.getpublicKey(keyMap);
        RSAPrivateKey rsaPrivateKey = RSAUtil.getPrivateKey(keyMap);
        System.out.println("RSA PublicKey: " + rsaPublicKey);
        System.out.println("RSA PrivateKey: " + rsaPrivateKey);
        //long startTime = System.currentTimeMillis(); //程序开始记录时间

        byte[] rsaResult = RSAUtil.encrypt(DATA.getBytes(), rsaPublicKey);
        System.out.println(DATA + "====>>>> RSA 加密>>>>====" + BytesToHex.fromBytesToHex(rsaResult));

        byte[] plainResult = RSAUtil.decrypt(rsaResult, rsaPrivateKey);
        System.out.println(DATA + "====>>>> RSA 解密>>>>====" + BytesToHex.fromBytesToHex(plainResult));
        long endTime = System.currentTimeMillis(); //程序结束记录时间
        long TotalTime = endTime - startTime; //总消耗时间
        System.out.print(TotalTime);
    }
}