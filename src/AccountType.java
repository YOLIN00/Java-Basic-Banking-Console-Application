public enum AccountType {
    Current(100),Savings(500),Salary(0);
    private int minimumBalance;

    private AccountType(int minimumBalance){
        this.minimumBalance=minimumBalance;
    }

    public int getMinimumBalance(){
        return minimumBalance;
    }
}
