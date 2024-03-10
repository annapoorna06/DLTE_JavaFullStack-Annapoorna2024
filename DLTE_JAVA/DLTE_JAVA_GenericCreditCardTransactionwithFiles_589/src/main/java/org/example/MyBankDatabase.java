package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class MyBankDatabase<T> implements Activity {
    ArrayList<T> bankDataBase;
    @Override
    public Object create(Object object) {
        bankDataBase.add((T) object);
        return object;
    }
    public void writeTofile() throws IOException {
        //writing file using object output stream
        FileOutputStream fileOutputStream=new FileOutputStream("CreditCard");
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(bankDataBase);
        fileOutputStream.close();
        objectOutputStream.close();
    }
    public void readFromfile() throws IOException, ClassNotFoundException {
        //reading file and displaying it for verification
        FileInputStream fileInputStream=new FileInputStream("CreditCard");
        ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
        bankDataBase= (ArrayList<T>) objectInputStream.readObject();
        int size=bankDataBase.size();
        for(int index=0;index<size;index++) {
            if (bankDataBase.get(index) != null) {
                System.out.println(bankDataBase.get(index).toString());
            }
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        MyBankDatabase<CreditCard> storeCardData= new MyBankDatabase<>();
        storeCardData.bankDataBase=new ArrayList<>(10);
        //input datas to the data
        CreditCard creditCardOne=new CreditCard("Aru",4432,768,20000);
        CreditCard creditCardTwo=new CreditCard("Annapoorna", 6575, 123,10000);
        storeCardData.create(creditCardOne);
        storeCardData.create(creditCardTwo);
        // System.out.println(storeCardData.bankDataBase[1].getCreditCardHolder());
        storeCardData.writeTofile();
        storeCardData.readFromfile();
    }
}
