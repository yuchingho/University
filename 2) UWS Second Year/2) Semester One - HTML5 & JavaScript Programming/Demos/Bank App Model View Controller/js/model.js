/**
 * Created with JetBrains WebStorm.
 * User: alistair
 * Date: 26/08/2012
 * Time: 15:49
 * To change this template use File | Settings | File Templates.
 */

var BankAccount = function(initialDeposit){
    this.balance = initialDeposit;
};

BankAccount.prototype.deposit = function(amount){
    this.balance += amount;
    return true;
};

BankAccount.prototype.withdraw = function(amount){
    if(this.balance >= amount){
        this.balance -= amount;
        return true;
    } else {
        return false;
    }
};


