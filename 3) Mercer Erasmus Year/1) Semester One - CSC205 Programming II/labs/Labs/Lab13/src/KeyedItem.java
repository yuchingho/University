//package SearchKeys;

public class KeyedItem {
	private Comparable searchKey;
  
	public KeyedItem(Comparable key) {
		searchKey = key;
	}  // end constructor

	public Comparable getKey() {
		return searchKey;
	}  // end getKey

	public String toString() {
		return "" + searchKey;
	}
}  // end KeyedItem