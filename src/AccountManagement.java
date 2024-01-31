import java.util.ArrayList;
import java.util.Scanner;

public class AccountManagement {
    ArrayList<Account> AllAccounts=new ArrayList<>();

    public void createAccount(){
        Scanner input=new Scanner(System.in);

        System.out.println("In which Branch you want to create an account? Input 111->Dhaka, 222->Sylhet, 333->Chitagong");
        String branch=input.nextLine();
        while(!(branch.equals("111") || branch.equals("222") || branch.equals("333"))){
            System.out.println("Please provide correct branch code");
            branch=input.nextLine();
        }
        BankBranch Branch = null;

        if(branch.equals("111")){
            Branch=BankBranch.DHAKA;
        } else if (branch.equals("222")) {
            Branch=BankBranch.SYLHET;
        } else if (branch.equals("333")) {
            Branch=BankBranch.CHITAGONG;
        }

        System.out.println("Which type of Account you want to open? Input 1->Current, 2->Savings, 3->Salary: ");

        int type=Integer.parseInt(input.nextLine());
        while (type<1 || type>3){
            System.out.println("Print any number from 1 to 3");
            type=Integer.parseInt(input.nextLine());
        }
        AccountType Type=null;

        if(type==1){
            Type=AccountType.Current;
        } else if (type==2) {
            Type=AccountType.Savings;
        } else if (type==3) {
            Type=AccountType.Salary;
        }

        System.out.println("You have to deposit minimum "+Type.getMinimumBalance()+"$ to create account type: "+Type+". Input 1->Continue, 0->Not Now");
        int confirmation=Integer.parseInt(input.nextLine());
        System.out.println("Confirmation : "+confirmation);
        while (confirmation!=0 && confirmation!=1){
            System.out.println("Input 0 or 1\n");
            confirmation=Integer.parseInt(input.nextLine());
        }
        if (confirmation==1){
            System.out.println("Your name(max 20 char): ");
            String name=input.nextLine();
            while (name.length()>15 || name.length()==0){
                System.out.println("Name should be maximum 15 char length &  not empty");
                name=input.nextLine();
            }

            System.out.println("Your address(max 20 char): ");
            String address=input.nextLine();
            while (address.length()>20 || address.length()==0){
                System.out.println("Name should be maximum 15 char length & not empty");
                address=input.nextLine();
            }

            System.out.println("Your phone no(11 digit): ");
            String phone=input.nextLine();
            while (phone.length()!=11 && phone.matches("\\d+")){
                System.out.println("Phone should be 11 char length");
                phone=input.nextLine();
            }
            Account newAccount=new Account(name,Branch,Type,address,phone);
            AllAccounts.add(newAccount);
            System.out.println("Your account created successfullu. Your Account no : "+newAccount.getNo()+"\n");

        }else if (confirmation==0){
            System.out.println("No account created!\n");

        }

    }
    public void displayAllAccount(){
        System.out.printf("%11s %15s %15s", "NO", "NAME", "BRANCH");
        System.out.println();
        for(Account account : AllAccounts){
            System.out.printf("%11s %15s %15s", account.getNo(), account.getName(), account.getBranch());
        }
        System.out.println();
    }

}
