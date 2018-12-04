package MD5_rand_salt;

import java.security.MessageDigest;
import java.util.Random;

public class MD5_rand_salt {

	public static String md5Encode(String msg) throws Exception{

        byte[] msgBytes = msg.getBytes("utf-8");
        /**
         * ����ʹ��Md5�㷨,���MessaDigest����
         */
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        /**
         * ʹ��ָ�����ֽڸ���ժҪ
         */
        md5.update(msgBytes);
        /**
         * ��ɹ�ϣ����,�������
         */
        byte[] digest = md5.digest();
        /**
         * �������д����ͬ�� byte[] digest = md5.digest(msgBytes);
         */
        return byteArr2hexString(digest);
    }
    /**
     * ��byte����ת��Ϊ16�����ַ�����ʽ
     * @param bys
     * @return
     */
    public static String byteArr2hexString(byte[] bys){
        StringBuffer hexVal=new StringBuffer();
        int val=0;
        for (int i = 0; i < bys.length; i++) {
            //��byteת��Ϊint  ���byte��һ�������ͱ���Ҫ��16���Ƶ�0xff��һ��������
            val=((int)bys[i]) & 0xff;
            if(val<16){
                hexVal.append("0");
            }
            hexVal.append(Integer.toHexString(val));
        }

        return hexVal.toString();

    }

    public static void main(String[] args) throws Exception {
        String password="password";
        Random rand = new Random(); 
        int salt=rand.nextInt(100);
        String result=md5Encode(password+salt);//��������
        System.out.println("password:"+password);
        System.out.println("��:"+salt);
        System.out.println("MD5:"+result);
    }

}
