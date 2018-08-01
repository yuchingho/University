//A cash register totals up sales and computes change due.

public class CashRegister {
	private double purchase;
	private double payment;
	private double taxRate;
	private double taxablePurchase;
	

	// Constructs a cash register with no money in it, the tax rate for taxable purchases
	public CashRegister(double rate) {
		purchase = 0;
		payment = 0;
		taxRate = rate;
	}

	// Records the sale of a tax-free item, the price of the item
	public void recordPurchase(double amount) {
		purchase = purchase + amount;
	}

	// Records the sale of a taxable item
	public void recordTaxablePurchase(double amount) {
		taxablePurchase = taxablePurchase + amount;
	}

	// Processes a payment received from the customer.
	public void receivePayment(double amount) {
		payment = payment + amount;
	}

	// Processes the sales tax due.
	public double getSalesTax() {
		return taxablePurchase * taxRate / 100;
	}

	// Computes the change due and resets the machine for the next customer.
	public double giveChange() {
		double change = payment - purchase - getSalesTax();
		purchase = 0;
		payment = 0;
		return change;
	}
}
