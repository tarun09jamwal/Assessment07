package Enum;

public enum ProducBuyEnum
{
    PRODUCT("Computers "),
    CATEGORIES(" Notebooks "),
    OPTIONS("Price: Low to High"),
    DISPLAYSIZE("9"),
    PRODUCTNAME("Apple MacBook Pro 13-inch"),
    CHECKOUT("Checkout "),
    REGISTER("Register"),
    FIRSTNAME("FirstName"),
    LASTNAME("LastName"),
    EMAIL("Email"),
    COMPANY("Company"),
    PASSWORD("Password"),
    CONFIRMPASSWORD("ConfirmPassword"),
    CITY("BillingNewAddress.City"),
    ADDRESS("BillingNewAddress.Address1"),
    POSTALCODE("BillingNewAddress.ZipPostalCode"),
    PHONENUMBER("BillingNewAddress.PhoneNumber"),
    BILLINGBUTTON("Billing.save()"),
    SHIPPINGBUTTON("ShippingMethod.save()"),
    PAYMENTBUTTON("PaymentMethod.save()"),
    PAYMENTINOFOBUTTON("PaymentInfo.save()"),
    CONFIRMORDERBUTTON("ConfirmOrder.save()");




    private String name;
    private ProducBuyEnum(String name) {
        this.name = name;

    }


    public String getResourcesName() {
        return name;
    }
}
