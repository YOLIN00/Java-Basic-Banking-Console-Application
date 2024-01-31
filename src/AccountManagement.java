import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
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
//        System.out.println("Confirmation : "+confirmation);
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
//            System.out.println("Phone: "+phone +" "+phone.matches("\\d+"));
            while (phone.length()!=11 || !phone.matches("\\d+")){
                System.out.println("Phone should be 11 char length & only numerical digit");
                phone=input.nextLine();
            }
            Account newAccount=new Account(name,Branch,Type,address,phone);
            AllAccounts.add(newAccount);
            System.out.println("Your account created successfully. Your Account no : "+newAccount.getNo()+"\n");

        }else if (confirmation==0){
            System.out.println("No account created!\n");

        }
//        input.close();

    }
    public void displayAllAccount(){
        if(AllAccounts.size()==0){
            System.out.println("No account in the list.\n");
        }else {
            System.out.printf("%11s %15s %15s", "NO", "NAME", "BRANCH");
            System.out.println();
            for(Account account : AllAccounts){
                System.out.printf("%11s %15s %15s", account.getNo(), account.getName(), account.getBranch());
                System.out.println();
            }
            System.out.println();
        }

    }

    public void deposit(){
        System.out.println("Type your account no:");
        Scanner input=new Scanner(System.in);
        Account account=null;
        String accountNo=input.nextLine();
        while (!accountNo.matches("\\d+")){
            System.out.println("Account no consist of only digits");
            accountNo=input.nextLine();
        }

        for(Account acc:AllAccounts){
            if(accountNo.equals(acc.getNo())){
                account=acc;
            }
        }
        if(account==null){
            System.out.println("Sorry! No account found!\n");
        }else{
            System.out.println("Type deposited amount: ");
            String amount=input.nextLine();

            while (!amount.matches("\\d+")){
                System.out.println("Deposit amount consist of only digits");
                amount=input.nextLine();
            }

            String curBalance=account.deposit(amount);
            System.out.println("Your deposited amount : "+amount+". Your current balance is "+curBalance+"$\n");
        }
    }

    public void withdraw(){
        System.out.println("Type your account no:");
        Scanner input=new Scanner(System.in);
        Account account=null;
        String accountNo=input.nextLine();
        while (!accountNo.matches("\\d+")){
            System.out.println("Account no consist of only digits");
            accountNo=input.nextLine();
        }

        for(Account acc:AllAccounts){
            if(accountNo.equals(acc.getNo())){
                account=acc;
            }
        }
        if(account==null){
            System.out.println("Sorry! No account found!\n");
        }else{
            System.out.println("Type withdraw amount: ");
            String amount=input.nextLine();

            while (!amount.matches("\\d+")){
                System.out.println("Withdraw amount consist of only digits");
                amount=input.nextLine();
            }

            int minBalance= account.getMinBalance();
            BigInteger curBalance=account.getBalance();
//            System.out.println("Cur balance: "+curBalance.toString());

            curBalance=curBalance.subtract(new BigInteger(amount));
//            System.out.println("Cur balance: "+curBalance.toString());

            int result=curBalance.compareTo(BigInteger.valueOf(minBalance));
            curBalance=curBalance.add(new BigInteger(amount));

//            System.out.println("MInBal: "+minBalance+" cur "+curBalance.toString()+" result "+result);

            if(result<0){
                curBalance=curBalance.subtract(BigInteger.valueOf(minBalance));
                System.out.println("Sorry!! Your should always keep minimum balance "+minBalance+"$ in your account.You can withdraw maximum "+curBalance.toString()+"$\n");
            }else{
                curBalance=new BigInteger(account.withdraw(amount));
                System.out.println("Your withdraw amount : "+amount+". Your current balance is "+curBalance+"$\n");
            }

        }
    }

    public void deleteAccount(){
        System.out.println("Type your account no:");
        Scanner input=new Scanner(System.in);
        Account account=null;
        String accountNo=input.nextLine();
        while (!accountNo.matches("\\d+")){
            System.out.println("Account no consist of only digits");
            accountNo=input.nextLine();
        }
        Iterator<Account> iterator=AllAccounts.iterator();
        while(iterator.hasNext()){
            Account acc=iterator.next();
            if(accountNo.equals(acc.getNo())){
                account=acc;
                iterator.remove();
                break;
            }
        }
        if(account==null){
            System.out.println("Sorry! No account found!\n");
        }else{
            String balance=account.getBalance().toString();
            System.out.println("Your account no: "+account.getNo()+" has been deleted & "+account.getBalance()+"$ withdrawn.\n");
        }
    }

    public void searchAccount(){
        System.out.println("Type your account no:");
        Scanner input=new Scanner(System.in);
        Account account=null;
        String accountNo=input.nextLine();
        while (!accountNo.matches("\\d+")){
            System.out.println("Account no consist of only digits");
            accountNo=input.nextLine();
        }
        Iterator<Account> iterator=AllAccounts.iterator();
        while(iterator.hasNext()){
            Account acc=iterator.next();
            if(accountNo.equals(acc.getNo())){
                account=acc;
                break;
            }
        }
        if(account==null){
            System.out.println("Sorry! No account found!\n");
        }else{
            System.out.println("Account no : "+account.getNo());
            System.out.println("Account name : "+account.getName());
            System.out.println("Account branch : "+account.getBranch());
            System.out.println("Account type : "+account.getType());
            System.out.println("Account balance : "+account.getBalance().toString());
            System.out.println("Account phone : "+account.getPhone());
            System.out.println("Account address : "+account.getAddress());
            System.out.println("Account createdAt : "+account.getCreatedAt());
            System.out.println();
        }
    }
    

}
