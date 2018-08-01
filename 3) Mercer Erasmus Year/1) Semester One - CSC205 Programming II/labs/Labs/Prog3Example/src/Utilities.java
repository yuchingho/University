// ----------------------------------------------------------
//                        Utilities
// ----------------------------------------------------------
//
// A "helper" class that provides class methods needed by the
// HourlyEmployee and SalariedEmployee classes

class Utilities {
  ///////////////////////////////////////////////////////////
  // NAME:       pad
  // BEHAVIOR:   Pads a string to a specified length by
  //             adding spaces to the end. If the string
  //             exceeds the specified length, it is
  //             truncated to that length.
  // PARAMETERS: str - the string to be padded
  //             n - the desired length after padding 
  // RETURNS:    str, with spaces added at the end so that
  //             the total length is n. If the length of str
  //             exceeds n, a truncated version of str is
  //             returned.
  ///////////////////////////////////////////////////////////
  public static String pad(String str, int n) {
    if (str.length() > n)
      return str.substring(0, n);
    while (str.length() < n)
      str += " ";
    return str;
  }

  ///////////////////////////////////////////////////////////
  // NAME:       toDollars
  // BEHAVIOR:   Converts a double value that represents a
  //             dollar amount to a string
  // PARAMETERS: amount - a dollar amount
  // RETURNS:    A string containing amount, with exactly two
  //             digits after the decimal point
  ///////////////////////////////////////////////////////////
  public static String toDollars(double amount) {
    long roundedAmount = Math.round(amount * 100);
    long dollars = roundedAmount / 100;
    long cents = roundedAmount % 100;

    if (cents <= 9)
      return dollars + ".0" + cents;
    else
      return dollars + "." + cents;
  }
}

