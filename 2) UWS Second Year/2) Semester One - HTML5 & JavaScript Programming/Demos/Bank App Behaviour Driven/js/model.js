/**
 * Created with JetBrains WebStorm.
 * User: alistair
 * Date: 16/08/2012
 * Time: 20:12
 * To change this template use File | Settings | File Templates.
 */

// Define BankAccount as a constructor...
var BankAccount = function(initialDeposit){
    this.transactions = [];
    this.transactions.push(initialDeposit);
};

BankAccount.prototype.getBalance = function(){
    var bal = 0;
    for (transaction in this.transactions){
        bal += this.transactions[transaction];
    }
    return bal;
}

BankAccount.prototype.deposit = function(amount){
    this.transactions.push(amount);
    return true;
};

BankAccount.prototype.withdraw = function(amount){
    if(this.getBalance() >= amount){
        this.transactions.push(-amount);
        return true;
    } else {
        return false;
    }
};

BankAccount.prototype.getStatement = function(){
    var lines = [];
    for(transaction in this.transactions){
        lines.push(this.transactions[transaction].toString() + "<p/>");
    };
    return {
        statementLines: lines,
        balance: this.getBalance(),
        count: lines.length
    };
};