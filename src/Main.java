import java.util.Scanner;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        AccountManagement management=new AccountManagement();

        System.out.println("Welcome to the JAVA Banking Console Appication!!\n");
        print();
        // Press Shift+F10 or click the green arrow button in the gutter to run the code.

        Scanner input=new Scanner(System.in);
        int opeartionType=Integer.parseInt(input.nextLine());

        while (true){
            switch (opeartionType){
                case 1:{
                    management.createAccount();
                    print();
                    opeartionType=Integer.parseInt(input.nextLine());
                    break;
                }

                case 2:{
                    management.displayAllAccount();
                    print();
                    opeartionType=Integer.parseInt(input.nextLine());
                    break;
                }

                case 8:
                    System.exit(0);
                    break;
                default:{
                    System.out.println("Invalid input. Type 1 to 8");
                    opeartionType=Integer.parseInt(input.nextLine());
                }

            }
        }

    }
    private static void print(){
        System.out.println("For Create a new Account input 1");
        System.out.println("For Display all accounts input 2");
        System.out.println("For Update an account input 3");
        System.out.println("For Delete an account input 4");
        System.out.println("For Deposit an amount into your account input 5");
        System.out.println("For Withdraw an amount from your account input 6");
        System.out.println("For Search for account input 7");
        System.out.println("For Exit input 8");
    }
}