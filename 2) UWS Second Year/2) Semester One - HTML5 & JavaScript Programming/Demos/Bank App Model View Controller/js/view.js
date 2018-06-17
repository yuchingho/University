/**
 * Created with JetBrains WebStorm.
 * User: alistair
 * Date: 26/08/2012
 * Time: 15:49
 * To change this template use File | Settings | File Templates.
 */

var AccountView = function(){
    // This function will create a view object, including the html
    // user interface elements that belong to it.
    this.viewForm = document.getElementById("viewForm");

    // The amount box..
    this.amountBox = document.getElementById("amount");

    // The balance element...
    this.balanceElement =  document.getElementById("balance");

    // The buttons...
    this.depositButton = document.getElementById("deposit");
    this.withdrawButton = document.getElementById("withdraw");


    this.clearOptions = function(){
        this.depositButton.checked = false;
        this.withdrawButton.checked = false;
    };

    this.update = function(model){
        this.balanceElement.innerText = model.balance.toFixed(2);
        this.amountBox.value = "0.0";
    };
};
