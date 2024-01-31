import java.math.BigInteger;
import java.time.LocalDate;

public class Account {
    private String name;
    private String no;
    private AccountType type;
    private String address;
    private String phone;
    private BankBranch branch;
    private BigInteger balance;
    private LocalDate createdAt;

    Account(String name,BankBranch branch,AccountType type,String address,String phone){
        this.name=name;
        this.no=Integer.toString((int)Math.floor((Math.random()*100000)+100000));
        this.type=type;
        this.address=address;
        this.branch=branch;
        this.phone=phone;

        if(type==AccountType.Current){
            balance = new BigInteger("100");
        }else if(type==AccountType.Savings){
            balance = new BigInteger("500");
        } else if (type==AccountType.Salary) {
            balance = new BigInteger("0");
        }
        this.createdAt=LocalDate.now();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNo() {
        return no;
    }

    public AccountType getType() {
        return type;
    }

    public BankBranch getBranch() {
        return branch;
    }

    public void setBranch(BankBranch branch) {
        this.branch = branch;
    }

    public BigInteger getBalance() {
        return balance;
    }

    public int getMinBalance(){
        return type.getMinimumBalance();
    }

    public String deposit(String amount){
        balance=balance.add(new BigInteger(amount));
       return balance.toString();
    }
    public String withdraw(String amount){
        balance=balance.subtract(new BigInteger(amount));
        return balance.toString();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
