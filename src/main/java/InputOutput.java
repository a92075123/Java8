import java.io.*;


public class InputOutput {
    public static void main(String[] args) throws IOException {
        /*
            BufferedReader=字符流，有緩衝區，當緩衝區滿了，才會進行輸出，或打印
            FileInputStream=字節流，讀取檔案
            兩個是不相通的，因為不知道多少個字節組成一個字符
            InputStreamReader=轉換流，要組成字符才會傳輸到BufferedReader，因為是UTF-8要有三個字節才會變成一個字符
         */
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("path"), "UTF-8"));
        String s = null;

        while ((s=in.readLine())!=null){
            System.out.println(s);
        }
    }
}
