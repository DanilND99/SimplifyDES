import java.util.Arrays;
public class SimplifyDes{
    public static void main(String[] args){
        int[][] box1 = new int[4][4];
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
        int[][] box2 = new int[4][4];
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
        int[] s = new int[4];
        int[] a = new int[2];
        s[0] = 1;
        s[1] = 1;
        s[2] = 0;
        s[3] = 0;
        a = sBox(s,box2);
        System.out.println(Arrays.toString(a));
        /*int[] resultado = new int[8];
        int[] resultado2 = new int[8];
        resultado = getKey(llave, 1);
        System.out.println(Arrays.toString(resultado));
        resultado2 = getKey(llave, 2);
        System.out.println(Arrays.toString(resultado2));
        */
        return;
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
            if(i + 1 >= 5){
                ls1[i] = temp[i - 4];
            }else{
                ls1[i] = temp[i + 1];
            }
            if(i + 6 >= 10){
                ls2[i] = temp[i + 1];
            }else{
                ls2[i] = temp[i + 6];
            }
        }
        System.out.println(Arrays.toString(ls2));

        int[] save1 = new int[5];
        int[] save2 = new int[5];
        save1 = ls1;
        save2 = ls2;
        System.out.println(Arrays.toString(save2));


        
        if(offset != 1){
            for (int i = 0;i < 5;i++){
                if(i + offset >= 5){
                    ls1[i] = save1[i + offset - 5];
                    ls2[i] = save2[i + offset - 5];
                }else{
                    ls1[i] = save1[i + offset];
                    ls2[i] = save2[i + offset];
                }
            }
            System.out.println(Arrays.toString(ls2));
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
                resultXOR[i] = 1;
            }else{
                resultXOR[i] = 0;
            }
        }
        return resultXOR;
    }

    public static int[] simplifyDES(int[] plain){
        int[] cipher = new int[8];
        int[] temp = new int[8];
        int[] key = getKey(key1, offset);

        temp[0] = plain[1];
        temp[1] = plain[5];
        temp[2] = plain[2];
        temp[3] = plain[0];
        temp[4] = plain[3];
        temp[5] = plain[7];
        temp[6] = plain[4];
        temp[7] = plain[6];

        int[] ep = new int [8];

        ep[0] = temp[7];
        ep[1] = temp[4];
        ep[2] = temp[5];
        ep[3] = temp[6];
        ep[4] = temp[5];
        ep[5] = temp[6];
        ep[6] = temp[7];
        ep[7] = temp[4];

        XOR(ep, key);

        return cipher;
    }
}
/*
public byte [4][4] s0 =
public byte [4][4] s1 =

Bit[8] getKey(Bit[10] key,byte offset){
    Bit[10] adas = {key[1],key[3],key[9]...}
    return key;
}
Bit[8] S-DES(Bit[8] plain, key1, key2){
    return msg;
}
Bit[] xOR(Bit[] arr1, Bit[] arr2){
    Hacer XOR de arr1 con arr2
    return arr3;
}
Bit[2] Sbox(Bit[4] arr,byte[][] s){
    hacer lo de las cajitas
    return newArr;
}
*/
//Cindy7td