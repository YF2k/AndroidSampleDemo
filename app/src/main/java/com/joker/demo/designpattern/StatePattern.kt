package com.joker.demo.designpattern

//状态模式---当一个对象的内在状态改变时允许改变其行为，这个对象看起来像是改变了其类
//使用场景：一个对象的行为取决于它的状态，并且它必须在运行时根据状态改变它的行为。
//代码中包含大量与对象状态有关的条件语句，例如，一个操作中含有庞大的分支语句 (if-else / switch-case) , 且这些分支依赖于该对象的状态。
fun main() {

    test(1000, 500, 200, "123")
    System.out.println("-------");
}

@Throws(java.lang.Exception::class)
fun test(totalAmount: Int, balance: Int, amount: Int, pwd: String) {
    //创建ATM
    val atm: ATM
    atm = ATM(totalAmount, balance, amount, pwd)
    //输出初始状态
    println(atm.toString())
    atm.insertCard()
    atm.submitPwd()
    atm.getCash()
    //输出结束状态
    println(atm.toString())
}

/**
 * 定义ATM机状态
 * @author ayqy
 */
interface ATMState {
    /**
     * 插卡
     */
    fun insertCard()

    /**
     * 提交密码
     */
    fun submitPwd()

    /**
     * 取款
     */
    fun getCash()

    /**
     * 查询余额
     */
    fun queryBalance()

    /**
     * 取卡
     */
    fun ejectCard()

}

class ReadyState(val atm: ATM) : ATMState {


    override fun insertCard() {
        System.out.println("插卡完成");
    }

    override fun submitPwd() {
        System.out.println("密码提交完成");
        //验证密码并做相应处理
        if ("123".equals(atm.getPwd())) {
            System.out.println("密码验证通过");
        } else {
            System.out.println("密码验证失败");
            //弹出卡片
            ejectCard();
        }
    }

    override fun getCash() {
        if (atm.getTotalAmount() >= atm.getAmount() && atm.getBalance() >= atm.getAmount()) {
            //更新账户余额
            atm.setBalance(atm.getBalance() - atm.getAmount());
            //更新机内现钞总数
            atm.setTotalAmount(atm.getTotalAmount() - atm.getAmount());
            System.out.println("吐出￥" + atm.getAmount());
            System.out.println("取款完成");
            //弹出卡片
            ejectCard();
            //检查机内余钞
            if(atm.getTotalAmount()==0){//若无钞，切换到NoService状态
                atm.setCurrState(atm.getNoCashState())//我们并不是直接new具体状态对象，而是使用了ATM提供的set接口，这样做是为了尽量的解耦（兄弟对象彼此之间并不认识），获取更多的弹性
                System.out.println("无钞信息已经发送至银行");
            }
        } else {
            System.out.println("取款失败，余额不足");
            //弹出卡片
            ejectCard();
        }
    }

    override fun queryBalance() {
        System.out.println("余额￥" + atm.getBalance());
        System.out.println("余额查询完成");
    }

    override fun ejectCard() {
        System.out.println("取卡完成");
    }

}

class NoCashState(val atm: ATM):ATMState{
    override fun insertCard() {
        System.out.println("插卡完成");
    }

    override fun submitPwd() {
        System.out.println("密码提交完成");
        //验证密码并做相应处理
        if ("123".equals(atm.getPwd())) {
            System.out.println("密码验证通过");
        } else {
            System.out.println("密码验证失败");
            //弹出卡片
            ejectCard();
        }
    }

    override fun getCash() {
        System.out.println("取款失败，机内无钞");
    }

    override fun queryBalance() {
        System.out.println("余额￥" + atm.getBalance());
        System.out.println("余额查询完成");
    }

    override fun ejectCard() {
        System.out.println("取卡完成");
    }

}

class NoServiceState(val atm: ATM):ATMState{
    override fun insertCard() {
        System.out.println("插卡失败，机器发生了故障");
    }

    override fun submitPwd() {
        System.out.println("密码提交失败，机器发生了故障");
    }

    override fun getCash() {
        System.out.println("取款失败，机器发生了故障");
    }

    override fun queryBalance() {
        System.out.println("余额查询失败，机器发生了故障");
    }

    override fun ejectCard() {
        System.out.println("取卡失败，机器发生了故障");
    }

}


class ATM(private var totalAmount:Int,private var balance:Int,private var amount:Int,private var pwd:String/* 测试时用的临时变量*/) {
    /*所有状态*/
    //初始化所有状态
    private var readyState = ReadyState(this)
    private var noCashState = NoCashState(this)
    private var noServiceState = NoServiceState(this)

    private var currState : ATMState? = null

    init{
        if(totalAmount>0){
            currState=readyState
        }else if(totalAmount==0){
            currState=noCashState
        }else{
            throw  Exception()
        }
    }

/*把具体行为委托给状态对象*/
    /**
     * 插卡
     */
    fun insertCard(){
        currState?.insertCard()
    }

    /**
     * 提交密码
     */
    fun submitPwd() {
        currState?.submitPwd()
    }

    /**
     * 取款
     */
    fun getCash() {
        currState?.getCash()
    }

    /**
     * 查询余额
     */
    fun queryBalance() {
        currState?.queryBalance()
    }

    /**
     * 取卡
     */
    fun ejectCard() {
        currState?.ejectCard()
    }

    override fun toString(): String {
        return "现钞总数￥$totalAmount"
    }

    fun getPwd():String {
        return pwd
    }

    fun getTotalAmount(): Int {
        return totalAmount;
    }

    fun getAmount(): Int {
        return amount;
    }

    fun getBalance(): Int {
        return balance;
    }

    fun setBalance(balance: Int){
        this.balance=balance
    }

    fun setTotalAmount(totalAmount: Int){
        this.totalAmount=totalAmount
    }

    fun setCurrState(currState:ATMState){
        this.currState=currState
    }

    fun getNoCashState():ATMState{
        return noCashState
    }
}


