import java.util.Arrays;
public class SimplifyDes{
    public static void main(String[] args){
        int[] llave = new int[10];
        llave[0] = 2;
        llave[1] = 1;
        llave[2] = 0;
        llave[3] = 1;
        llave[4] = 0;
        llave[5] = 1;
        llave[6] = 0;
        llave[7] = 0;
        llave[8] = 1;
        llave[9] = 1;
        int[] resultado = new int[8];
        int[] resultado2 = new int[8];
        resultado = getKey(llave, 1);
        System.out.println(Arrays.toString(resultado));
        resultado2 = getKey(llave, 2);
        System.out.println(Arrays.toString(resultado2));
        return;
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
        //int save1 = new int[5];
        //int save2 = new int[5];
        //System.out.println(Arrays.toString(save1));
        //System.out.println(Arrays.toString(save2));
        //save1 = ls1;
        //save2 = ls2;
        if(offset != 1){
            for (int i = 0;i < 5;i++){
                System.out.println(ls2[i]);
                if(i + offset >= 5){
                    ls1[i] = ls1[i + offset - 5];
                    ls2[i] = ls2[i + offset - 5];
                }else{
                    ls1[i] = ls1[i + offset];
                    ls2[i] = ls2[i + offset];
                }
                
                System.out.println(ls2[i]);
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