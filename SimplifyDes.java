import java.util.Arrays;
import java.util.Scanner;
public class SimplifyDes{
    public static int[][] box1 = new int[4][4];
    public static int[][] box2 = new int[4][4];
    
    public static void main(String[] args){
        //public int[][] box1 = new int[4][4];
        box1[0][0] = 1;
        box1[0][1] = 0;
        box1[0][2] = 3;
        box1[0][3] = 2;
        box1[1][0] = 3;
        box1[1][1] = 2;
        box1[1][2] = 1;
        box1[1][3] = 0;
        box1[2][0] = 0;
        box1[2][1] = 2;
        box1[2][2] = 1;
        box1[2][3] = 3;
        box1[3][0] = 1;
        box1[3][1] = 1;
        box1[3][2] = 3;
        box1[3][3] = 2;
        box2[0][0] = 0;
        box2[0][1] = 1;
        box2[0][2] = 2;
        box2[0][3] = 3;
        box2[1][0] = 2;
        box2[1][1] = 0;
        box2[1][2] = 1;
        box2[1][3] = 3;
        box2[2][0] = 3;
        box2[2][1] = 0;
        box2[2][2] = 1;
        box2[2][3] = 0;
        box2[3][0] = 2;
        box2[3][1] = 1;
        box2[3][2] = 0;
        box2[3][3] = 3;

        int[] llave = new int[10];
        llave[0] = 1;
        llave[1] = 1;
        llave[2] = 0;
        llave[3] = 1;
        llave[4] = 0;
        llave[5] = 1;
        llave[6] = 0;
        llave[7] = 0;
        llave[8] = 1;
        llave[9] = 1;

        int[] plain = new int[8];
        plain[0] = 0;
        plain[1] = 1;
        plain[2] = 0;
        plain[3] = 1;
        plain[4] = 1;
        plain[5] = 0;
        plain[6] = 1;
        plain[7] = 1;
        /*int[] s = new int[4];
        int[] a = new int[2];
        s[0] = 1;
        s[1] = 1;
        s[2] = 0;
        s[3] = 0;
        a = sBox(s,box2);
        System.out.println(Arrays.toString(a));
        */

        String plaintext;
        int[] key = new int[10];
        String temp;

        Scanner reader = new Scanner(System.in);
        System.out.println("Enter an 8-bit message: ");
        plaintext = reader.nextLine();

        System.out.println("Enter a 10-bit key: ");
        key = reader.nextInt();
        temp = reader.nextLine();

        int[] plainArray = new int[8];
        plainArray = convertInput(plaintext);

        int[] key1 = new int[8];
        int[] key2 = new int[8];
        key1 = getKey(key, 1);
        System.out.println("Key 1: " + Arrays.toString(key1));
        key2 = getKey(key, 2);
        System.out.println("Key 2: " + Arrays.toString(key2));
        int[] cipher = new int[8];
        System.out.println("Plano: " + Arrays.toString(plainArray));
        cipher = simplifyDES(plaintext, key1, key2);
        System.out.println("Cipher: " + Arrays.toString(cipher));
        //plain = simplifyDES(cipher, key2, key1);
        //System.out.println("Plano: " + Arrays.toString(plaintext));
        return;
    }
    public static int[] convertInput(String message){
        int number = message.length();
        int[] msgArray = new int[8];
        for(int i = 0; i< number; i++){
            msgArray[i] = Integer.parseInt(String.valueOf(message.charAt(i)));

        }
        //System.out.println(msgArray);
        return msgArray;
    }  
    public static int[] sBox(int[] arr,int[][] box){
        int x, y, res;
        if(arr[1] == 1){
            if(arr[2] == 1){
                x = 3;
            }else{
                x = 2;
            }
        }else{
            if(arr[2] == 1){
                x = 1;
            }else{
                x = 0;
            }
        }
        if(arr[0] == 1){
            if(arr[3] == 1){
                y = 3;
            }else{
                y = 2;
            }
        }else{
            if(arr[3] == 1){
                y = 1;
            }else{
                y = 0;
            }
        }
        res = box[y][x];
        int[] result = new int[2];
        switch (res) {
            case 0:
                result[0] = 0;
                result[1] = 0;
                break;
            case 1:
                result[0] = 0;
                result[1] = 1;
                break;
            case 2:
                result[0] = 1;
                result[1] = 0;
                break;
            case 3:
                result[0] = 1;
                result[1] = 1;
                break;
            default:
                break;
        }
        return result;
    }
    public static int[] getKey(int[] key,int offset){
        int[] temp = new int[10];
        temp[0] = key[2];
        temp[1] = key[4];
        temp[2] = key[1];
        temp[3] = key[6];
        temp[4] = key[3];
        temp[5] = key[9];
        temp[6] = key[0];
        temp[7] = key[8];
        temp[8] = key[7];
        temp[9] = key[5];
        int[] ls1 = new int[5];
        int[] ls2 = new int[5];
        for (int i = 0;i < 5;i++){
            ls1[i] = temp[i];
            ls2[i] = temp[i + 5];
        }
        for(int j = 1;j <= offset;j++){
            int[] save1 = new int[5];
            int[] save2 = new int[5];
            for(int i = 0; i < 5; i++){
                save1[i] = ls1[i];
                save2[i] = ls2[i];
            }
            for (int i = 0;i < 5;i++){
                if(i + j >= 5){
                    ls1[i] = save1[i + j - 5];
                    ls2[i] = save2[i + j - 5];
                }else{
                    ls1[i] = save1[i + j];
                    ls2[i] = save2[i + j];
                }
            }
        }
        int[] result = new int[8];
        result[0] = ls2[0];
        result[1] = ls1[2];
        result[2] = ls2[1];
        result[3] = ls1[3];
        result[4] = ls2[2];
        result[5] = ls1[4];
        result[6] = ls2[4];
        result[7] = ls2[3];
        return result; 
    }

    public static int[] XOR(int[] arr1, int[] arr2){
        int[] resultXOR = new int[arr2.length];

        for(int i = 0; i < arr2.length; i++){
            if (arr1[i] == arr2[i]){
                resultXOR[i] = 0;
            }else{
                resultXOR[i] = 1;
            }
        }
        return resultXOR;
    }
    public static int[] simplifyDES(int[] plain, int[] key1, int[] key2){
        int[] cipher = new int[8];
        int[] ip = new int[8];

        //Asignacion del IP
        ip[0] = plain[1];
        ip[1] = plain[5];
        ip[2] = plain[2];
        ip[3] = plain[0];
        ip[4] = plain[3];
        ip[5] = plain[7];
        ip[6] = plain[4];
        ip[7] = plain[6];
        System.out.println("IP: " + Arrays.toString(ip));
        int[] ep = new int [8];

        //Asignacion del E/P
        ep[0] = ip[7];
        ep[1] = ip[4];
        ep[2] = ip[5];
        ep[3] = ip[6];
        ep[4] = ip[5];
        ep[5] = ip[6];
        ep[6] = ip[7];
        ep[7] = ip[4];
        System.out.println("EP: " + Arrays.toString(ep));
        //Resultado del primer XOR
        int[] temp8bit = new int[8];
        temp8bit =  XOR(ep, key1);
        System.out.println("XOR 1: " + Arrays.toString(temp8bit));

        //Asignacion del primer SBox
        int[] sbox1 = new int[4];
        sbox1[0] = temp8bit[0];
        sbox1[1] = temp8bit[1];
        sbox1[2] = temp8bit[2];
        sbox1[3] = temp8bit[3];

        //Asignacion del segundo SBox
        int[] sbox2 = new int[4];
        sbox2[0] = temp8bit[4];
        sbox2[1] = temp8bit[5];
        sbox2[2] = temp8bit[6];
        sbox2[3] = temp8bit[7];

        //Sbox results
        int[] sbox1Result = new int[2];
        sbox1Result = sBox(sbox1, box1);
        int[] sbox2Result = new int[2];
        sbox2Result = sBox(sbox2, box2);
        int[] sboxResult = new int [4];
        for(int i = 0; i < 2; i++){
            sboxResult[i] = sbox1Result[i];
            sboxResult[i + 2] = sbox2Result[i];
        }

        int[] p4 = new int[4];
        p4[0] = sboxResult[1];
        p4[1] = sboxResult[3];
        p4[2] = sboxResult[2];
        p4[3] = sboxResult[0];
        System.out.println("P4: " + Arrays.toString(p4));
        int[] ip4 = new int[4];
        for(int i = 0; i < 4; i++){
            ip4[i] = ip[i];     
        }

        //Resultado del segundo XOR
        int[] temp4bit = new int[4];
        temp4bit = XOR(p4, ip4);

        int[] sw = new int[8];
        for(int i = 0;i < 4;i++){
            sw[i] = ip[i + 4];
            sw[i + 4] = temp4bit[i];
        }
        System.out.println("SW: " + Arrays.toString(sw));
        ep[0] = temp4bit[3];
        ep[1] = temp4bit[0];
        ep[2] = temp4bit[1];
        ep[3] = temp4bit[2];
        ep[4] = temp4bit[1];
        ep[5] = temp4bit[2];
        ep[6] = temp4bit[3];
        ep[7] = temp4bit[0];
        System.out.println("EP2: " + Arrays.toString(ep));
        temp8bit =  XOR(ep, key2);
        
        for(int i = 0;i < 4;i++){
            sbox1[i] = temp8bit[i];
            sbox2[i] = temp8bit[i + 4];
        }
        sbox1Result = sBox(sbox1, box1);
        sbox2Result = sBox(sbox2, box2);
        for(int i = 0; i < 2; i++){
            sboxResult[i] = sbox1Result[i];
            sboxResult[i + 2] = sbox2Result[i];
        }
        p4[0] = sboxResult[1];
        p4[1] = sboxResult[3];
        p4[2] = sboxResult[2];
        p4[3] = sboxResult[0];
        System.out.println("P4-2: " + Arrays.toString(p4));
        for(int i = 0; i < 4; i++){
            ip4[i] = sw[i];     
        }

        temp4bit = XOR(p4, ip4);

        for(int i = 0;i < 4;i++){
            sw[i] = temp4bit[i];
        }

        //Asigna el resultado del 
        //cuarto y del segundo XOR 
        /*temp8bit[0] = temp2[0];
        temp8bit[1] = temp2[1];
        temp8bit[2] = temp2[2];
        temp8bit[3] = temp2[3];
        temp8bit[4] = temp[0];
        temp8bit[5] = temp[1];
        temp8bit[6] = temp[2];
        temp8bit[7] = temp[3];*/

        //Acomoda el cipher
        System.out.println("SW: " + Arrays.toString(sw));
        cipher[0] = sw[3];
        cipher[1] = sw[0];
        cipher[2] = sw[2];
        cipher[3] = sw[4];
        cipher[4] = sw[6];
        cipher[5] = sw[1];
        cipher[6] = sw[7];
        cipher[7] = sw[5];

        return cipher;
    }
}
