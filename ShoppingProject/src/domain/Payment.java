package domain;

public class Payment {

    int paymentid;

    int userid;

    int productid;

    int numbers;

    String address;

    String contact;

    String creditcardnumber;

    String creditcardpassword;

    public int getPaymentid() {
		return paymentid;
	}

	public void setPaymentid(int paymentid) {
		this.paymentid = paymentid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getProductid() {
		return productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public int getNumbers() {
		return numbers;
	}

	public void setNumbers(int numbers) {
		this.numbers = numbers;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getCreditcardnumber() {
		return creditcardnumber;
	}

	public void setCreditcardnumber(String creditcardnumber) {
		this.creditcardnumber = creditcardnumber;
	}

	public String getCreditcardpassword() {
		return creditcardpassword;
	}

	public void setCreditcardpassword(String creditcardpassword) {
		this.creditcardpassword = creditcardpassword;
	}

	public Payment(int paymentid, int userid, int productid, int numbers, String address, String contact, String creditcardnumber, String creditcardpassword) {

        this.paymentid = paymentid;

        this.userid = userid;

        this.productid = productid;

        this.numbers = numbers;

        this.address = address;

        this.contact = contact;

        this.creditcardnumber = creditcardnumber;

        this.creditcardpassword = creditcardpassword;

    }

}