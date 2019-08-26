package srv.bank;

/*
查询余额函数public double queryBalance(BankInfo)
转账功能函数public boolean transfer(BankInfo)
修改个人信息public boolean modifyPassword(BankInfo)
 */
public class Bank {


    public double queryBalance(BankInfo info) {
        try {
            System.out.println(info.getBalance());
            return 0;
        } catch (Exception e) {
            System.out.println("Database exception");
            e.printStackTrace();
            return 0;
        }
    }

    public boolean transfer(BankInfo info, String to, double amount, int pwd) {
        try {
            if(pwd == info.getPassword()) {
                info.setTransferTo(to);
                info.setTransferAmount(amount);
                info.setBalance(info.getBalance() - info.getTransferAmount());//修改转账方的余额
                /*if ()//能从数据库里找到转账对象info.getTransferTo()
                {
                    BankInfo temp = new BankInfo();
                    temp.setBalance(temp.getBalance() + info.getTransferAmount());//修改转账对象的余额
                }
                else{
                    System.out.println("无法找到转账对象！");
                    return false;
                }*/
                return false;
            }
            else {
                System.out.println("交易密码错误！");
                return false;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean modifyPassword(BankInfo info, int oldpwd, int npwd, int npwdagain) {
        try {
            if (info.getPassword() == oldpwd)//输入密码以确认身份
            {
                if (npwd == npwdagain) {
                    info.setPassword(npwd);
                    System.out.println("修改密码成功！");
                    return true;
                }
                else {
                    System.out.println("两次密码不一致！");
                    return false;
                }
            }
            else{
                System.out.println("密码错误！");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
