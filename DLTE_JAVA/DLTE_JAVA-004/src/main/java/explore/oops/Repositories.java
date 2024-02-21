package explore.oops;

public interface Repositories {
    SavingsAccount[] myBank = new SavingsAccount[10];// property
    public void viewAll();// abstract method

    default void listAll(){
        for(SavingsAccount each:myBank){
            System.out.println(each);
        }
    }
    static void listFew(){
        for (SavingsAccount each:myBank){

        }
    }
}
