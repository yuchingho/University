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

    // The transactions element (a table)...
    this.transactions = document.getElementById("transactions");

    // The buttons...
    this.depositButton = document.getElementById("deposit");
    this.withdrawButton = document.getElementById("withdraw");


    this.clearOptions = function(){
        this.depositButton.checked = false;
        this.withdrawButton.checked = false;
    };

    this.update = function(model){
        var row, cell, transaction;
        while(i = this.transactions.rows.length > 0){
            this.transactions.deleteRow(i-1)
        }
        this.transactions.rows.length = 0;
        for(transaction in model.transactions){
            row = this.transactions.insertRow(-1);
            cell = row.insertCell(0);
            cell.innerHTML = transaction;
            cell = row.insertCell(1);
            cell.innerHTML = model.transactions[transaction].toFixed(2);
        }
        row = this.transactions.insertRow(-1);
        cell = row.insertCell(0);
        cell.innerHTML = "Balance";
        cell = row.insertCell(1);
        cell.innerHTML = model.getBalance().toFixed(2);

        this.balanceElement.innerText = model.getBalance().toFixed(2);
        this.amountBox.value = "0.0";
    };
};
