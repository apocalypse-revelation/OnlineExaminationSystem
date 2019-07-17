package cn.big.util;

public class VerifyIdValidUtil
{
   public static boolean validate(String no)
   {
      // �����֤�Ž��г��ȵȼ��ж�
      if (no == null || no.length() != 18 || !no.matches("\\d{17}[0-9X]"))
      {
         return false;
      }
      // 1-17λ�����������
      int[] factor = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
      // 18λ���������
      char[] random = "10X98765432".toCharArray();
      // ����1-17λ����Ӧ���ӳ˻�֮��
      int total = 0;
      for (int i = 0; i < 17; i++)
      {
         total += Character.getNumericValue(no.charAt(i)) * factor[i];
      }
      // �ж�������Ƿ����
      return random[total % 11] == no.charAt(17);
   }

   public static void main(String[] args)
   {
      // ��ȷ
      System.out.println(validate("432831196411150810"));
      // ����
      System.out.println(validate("432831196411150813"));
   }
}
