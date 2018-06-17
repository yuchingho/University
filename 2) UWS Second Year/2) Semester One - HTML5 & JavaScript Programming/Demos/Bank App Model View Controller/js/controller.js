/**
 * Created with JetBrains WebStorm.
 * User: alistair
 * Date: 26/08/2012
 * Time: 15:50
 * To change this template use File | Settings | File Templates.
 */

var AccountController = function(){
    alert("New account");
    this.model = null;
    this.view = null;
    var me = this;

    this.updateView = function(){
        this.view.update(this.model);
        this.view.clearOptions();
    };

    this.runDeposit = function(){
        var amount = parseFloat(me.view.amountBox.value);
        me.model.deposit(amount);
        me.updateView();
    };

    this.runWithdraw = function(){
        var amount = parseFloat(me.view.amountBox.value);
        if(!me.model.withdraw(amount)){
            alert("Insufficient balance in account for that transaction");
        }
        me.updateView();
    };


    this.init = function(){

        var initialBalance = parseFloat(prompt("Enter new account initial amount:", ""));
        this.model = new BankAccount(initialBalance);
        this.view = new AccountView();

        this.view.depositButton.onclick = this.runDeposit;
        this.view.withdrawButton.onclick = this.runWithdraw;

        document.getElementById("withdraw").onclick = this.runWithdraw;

        this.updateView();
    }
};



//var ctrl = new AccountController();

window.onload = function(){
    var ctrl = new AccountController();
    ctrl.init();
};


