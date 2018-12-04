package MD5;

import java.security.MessageDigest;

public class MD5 {

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
        String result=md5Encode(password);
        System.out.println("password:"+password);
        System.out.println("MD5:"+result);
    }

}
