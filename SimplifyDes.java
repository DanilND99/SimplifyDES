import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.*;
//Daniel Armando Núñez Delgadillo A01561730
//Cindy Torres Domínguez A01566153
public class SimplifyDes{
    public static int[][] box1 = new int[4][4];
    public static int[][] box2 = new int[4][4];
    public static void main(String[] args){
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
        box1[3][0] = 3;
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
        Scanner reader = new Scanner(System.in);
        String text;
        int option;
        int[] key = new int[10];
        int[] plain = new int[8];
        int[] key1 = new int[8];
        int[] key2 = new int[8];
        int[] cipher = new int[8];
        do{
            System.out.println("Seleccione una opcion\n1. Encriptar\n2. Desencriptar\n3. Fuerza Bruta\n0. Salir\n");
            option = reader.nextInt();
            text = reader.nextLine();
            switch(option){
                case 1:
                    System.out.println("Introduzca el texto plano de 8-bits:");
                    text = reader.nextLine();
                    plain = convertInput(text);
                    System.out.println("Introduzca la llave de 10-bits:");
                    text = reader.nextLine();
                    key = convertInput(text);
                    key1 = getKey(key, 1);
                    System.out.println("Key 1: " + Arrays.toString(key1));
                    key2 = getKey(key, 2);
                    System.out.println("Key 2: " + Arrays.toString(key2));
                    System.out.println("Plain: " + Arrays.toString(plain));
                    cipher = simplifyDES(plain, key1, key2, false);
                    System.out.println("Cipher: " + Arrays.toString(cipher));
                    break;
                case 2:
                    System.out.println("Introduzca el texto cifrado de 8-bits:");
                    text = reader.nextLine();
                    plain = convertInput(text);
                    System.out.println("Introduzca la llave de 10-bits:");
                    text = reader.nextLine();
                    key = convertInput(text);
                    key1 = getKey(key, 1);
                    System.out.println("Key 1: " + Arrays.toString(key1));
                    key2 = getKey(key, 2);
                    System.out.println("Key 2: " + Arrays.toString(key2));
                    System.out.println("Cipher: " + Arrays.toString(plain));
                    cipher = simplifyDES(plain, key2, key1, false);
                    System.out.println("Plain: " + Arrays.toString(cipher));
                    break;
                case 3:
                    System.out.println("Introduce el nombre del archivo a revisar");
                    text = reader.nextLine();
                    try {
                        File myObj = new File(text);
                        Scanner myReader = new Scanner(myObj);
                        for(int i = 0;i < key.length;i++){
                            key[i] = 0;
                        }
                        while (myReader.hasNextLine()) {
                            String data = myReader.nextLine();
                            String[] pairs = new String[2];
                            pairs = data.split(",");
                            plain = convertInput(pairs[0]);
                            cipher = convertInput(pairs[1]);
                            key = fuerzaBruta(plain, cipher, key);  
                        }
                        myReader.close();
                        System.out.println("Your Key: " + Arrays.toString(key));
                    } catch (FileNotFoundException e) {
                        System.out.println("Error 404: File not found.");
                        e.printStackTrace();
                    }
                    break;
                default:
                    option = 0;
                    break;
            }
            System.out.println("\n\n");
        }while(option != 0);
        reader.close();
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
    public static int[] simplifyDES(int[] plain, int[] key1, int[] key2,boolean brute){
        int[] cipher = new int[8];
        int[] ip = new int[8];
        int[] ep = new int [8];
        int[] temp8bit = new int[8];
        int[] sbox1 = new int[4];
        int[] sbox2 = new int[4];
        int[] sbox1Result = new int[2];
        int[] sbox2Result = new int[2];
        int[] sboxResult = new int [4];
        int[] p4 = new int[4];
        int[] ip4 = new int[4];
        int[] temp4bit = new int[4];
        ip[0] = plain[1];
        ip[1] = plain[5];
        ip[2] = plain[2];
        ip[3] = plain[0];
        ip[4] = plain[3];
        ip[5] = plain[7];
        ip[6] = plain[4];
        ip[7] = plain[6];
        if(!brute){
            System.out.println("IP: " + Arrays.toString(ip));
        }
        for(int i = 1;i <= 2;i++){
            ep[0] = ip[7];
            ep[1] = ip[4];
            ep[2] = ip[5];
            ep[3] = ip[6];
            ep[4] = ip[5];
            ep[5] = ip[6];
            ep[6] = ip[7];
            ep[7] = ip[4];
            if(!brute){
                System.out.println("EP: " + Arrays.toString(ep));
            }
            if(i == 1){
                temp8bit =  XOR(ep, key1);
                if(!brute){
                    System.out.println("XOR 1: " + Arrays.toString(temp8bit));
                }
            }else{
                temp8bit =  XOR(ep, key2);
                if(!brute){
                    System.out.println("XOR 2: " + Arrays.toString(temp8bit));
                }
            }
            for(int a = 0;a < 4;a++){
                sbox1[a] = temp8bit[a];
                sbox2[a] = temp8bit[a + 4];
            }
            sbox1Result = sBox(sbox1, box1);
            sbox2Result = sBox(sbox2, box2);
            for(int a = 0; a < 2; a++){
                sboxResult[a] = sbox1Result[a];
                sboxResult[a + 2] = sbox2Result[a];
            }
            p4[0] = sboxResult[1];
            p4[1] = sboxResult[3];
            p4[2] = sboxResult[2];
            p4[3] = sboxResult[0];
            if(!brute){
                System.out.println("P4: " + Arrays.toString(p4));
            }
            for(int a = 0; a < 4; a++){
                ip4[a] = ip[a];     
            }
            temp4bit = XOR(p4, ip4);
            if(i == 1){
                for(int a = 0;a < 4;a++){
                    ip[a] = ip[a + 4];
                    ip[a + 4] = temp4bit[a];
                }
            }else{
                for(int a = 0;a < 4;a++){
                    ip[a] = temp4bit[a];
                }
            }
            
        }
        if(!brute){
            System.out.println("SW: " + Arrays.toString(ip));
        }
        cipher[0] = ip[3];
        cipher[1] = ip[0];
        cipher[2] = ip[2];
        cipher[3] = ip[4];
        cipher[4] = ip[6];
        cipher[5] = ip[1];
        cipher[6] = ip[7];
        cipher[7] = ip[5];
        return cipher;
    }
    public static int[] convertInput(String message){
        int number = message.length();
        int[] msgArray = new int[number];
        for(int i = 0; i< number; i++){
            msgArray[i] = Integer.parseInt(String.valueOf(message.charAt(i)));
        }
        return msgArray;
    }
    public static int[] arrayPlus1(int[] arr){
        int length = arr.length;
        int add = 1;
        for(int i = length - 1;i >= 0;i--){
            if(add == 0){
                break;
            }
            if(arr[i]  == 0){
                arr[i] = 1;
                add = 0;
            }else{
                arr[i] = 0;
            }
        }
        return arr;
    }
    public static int[] fuerzaBruta(int[] plain, int[] cipher, int[] key){
        int[] potentialCipher = new int[8];
        int[] key1 = new int[8];
        int[] key2 = new int[8];
        boolean flag = true;
        do{
            key1 = getKey(key, 1);
            key2 = getKey(key, 2);
            potentialCipher = simplifyDES(plain, key1, key2, true);
            if(Arrays.toString(potentialCipher).equals(Arrays.toString(cipher))){
                flag = false;
            }else{
                key = arrayPlus1(key);
            }
        }while(flag);
        return key;
    }
}