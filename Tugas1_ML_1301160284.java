import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Tugas1_ML_1301160284 {

    static double plebih50(String[][] train){
        int a=0;
        for (int i = 0; i < train.length; i++) {
            if (">50K".equals(train[i][8])) a++;
        }
        return (double) a/train.length;
    }
    static double patribut(String a,String c, int b,double p, String[][] train){
        int z=0;
        for (int i = 0; i < train.length; i++) {
            if ((train[i][b].equals(a)) && (train[i][8].equals(c))) z++;
        }
        return (double) z/(p*train.length);
    }
    static String choosemax(double a, double b){
        if (a>b) return ">50K";
        else return "<=50K";
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        File filetest= new File("TestsetTugas1ML.csv");
        File filetrain= new File("TrainsetTugas1ML.csv");
        Scanner test= new Scanner(filetest);
        test.nextLine();
        Scanner train = new Scanner(filetrain);
        train.nextLine();
        String[][] t=new String[160][8];
        int i =0;
        while (train.hasNext()){
            t[i]=train.nextLine().split(",");
            i++;
        }
        double plebih = plebih50(t);
        double pkurang = 1-plebih50(t);
        FileWriter fw = new FileWriter("TebakanTugas1ML.csv");
        while (test.hasNext()){
            String[] tes=test.nextLine().split(",");
            double q=1;
            double r=1;
            for (int j = 1; j < tes.length; j++) {
                q=q*patribut(tes[j],">50K",j,plebih,t);
                r=r*patribut(tes[j],"<=50K",j,pkurang,t);
            }
            q=q*plebih;
            r=r*pkurang;
            try{
               fw.append(String.valueOf(choosemax(q,r))+'\n');
            }catch(Exception e){}
        }
        fw.flush();
        fw.close();
    }  
}
