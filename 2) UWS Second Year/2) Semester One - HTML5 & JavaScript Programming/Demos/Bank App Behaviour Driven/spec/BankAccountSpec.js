/**
 * Created with JetBrains WebStorm.
 * User: alistair
 * Date: 16/08/2012
 * Time: 20:12
 * To change this template use File | Settings | File Templates.
 */

describe( "BankAccount Test Suite", function(){

        var account,
            initialDeposit = 100.0;

        beforeEach(function(){
            account = new BankAccount(initialDeposit);
        });

        it("contains an amount equal to the initial deposit amount", function(){
            expect(account).toBeDefined();
            expect(account.getBalance()).toEqual(initialDeposit);
        });

        it("correctly deposits a specified amount", function(){
            var depositAmount = 50;
            var result = account.deposit(depositAmount);
            //expect(result).toBeTruthy();
            expect(account.getBalance()).toEqual(initialDeposit + depositAmount);
        });

        it("correctly handles a valid withdrawal", function(){
            var withdrawalAmount = 50;
            account.withdraw(withdrawalAmount);
            var remainingBalance = initialDeposit - withdrawalAmount;
            expect(account.getBalance()).toEqual(remainingBalance);
        });

        it("correctly handles an invalid withdrawal", function(){
            var withdrawalAmount = 110;
            var initialBalance = account.getBalance();
            account.withdraw(withdrawalAmount);
            expect(account.getBalance()).toEqual(initialBalance);
        });

        it("provides a statement of transactions for the account", function(){
            account.deposit(25.0);
            account.withdraw(50.0);
            account.withdraw(25.0);
            var statement = account.getStatement();
            expect(statement.count).toEqual(4);
            expect(statement.balance).toEqual(50.0);
        });
    }
);