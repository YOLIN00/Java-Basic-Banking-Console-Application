public enum BankBranch {
    DHAKA(111),SYLHET(222),CHITAGONG(333);
    private int code;
    BankBranch(int code){
        this.code=code;
    }
    public int getCode(){
        return code;
    }
}
