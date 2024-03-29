package com.paymentic.wallet.domain;

import com.paymentic.wallet.domain.shared.BuyerInfo;
import com.paymentic.wallet.domain.shared.CheckoutId;
import com.paymentic.wallet.domain.shared.PaymentOrderId;
import com.paymentic.wallet.domain.shared.SellerInfo;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.UUID;

@Entity(name = "payment_seller_transaction")
public class PaymentTransaction {

  @Id
  @Column(name = "transaction_id")
  private UUID id;
  private String amount;
  private String currency;
  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name="id",column=@Column(name="checkout_id"))
  })
  private CheckoutId checkout;

  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name="document",column=@Column(name="buyer_document")),
      @AttributeOverride(name="name",column=@Column(name="buyer_name"))
  })
  private BuyerInfo buyer;

  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name="id",column=@Column(name="payment_order_id"))
  })
  private PaymentOrderId paymentOrder;

  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name="id",column=@Column(name="seller_id"))
  })
  private SellerInfo sellerInfo;
  public PaymentTransaction(){}
  public PaymentTransaction(UUID id, String amount, String currency, CheckoutId checkout, BuyerInfo buyer,
      PaymentOrderId paymentOrder, SellerInfo sellerInfo) {
    this.id = id;
    this.amount = amount;
    this.currency = currency;
    this.checkout = checkout;
    this.buyer = buyer;
    this.paymentOrder = paymentOrder;
    this.sellerInfo = sellerInfo;
  }
  public static PaymentTransaction newPaymentTransactionRegistered(UUID id, String amount, String currency, CheckoutId checkout, BuyerInfo buyer,
      PaymentOrderId paymentOrder,SellerInfo sellerInfo){
    return new PaymentTransaction(id,amount,currency,checkout,buyer,paymentOrder,sellerInfo);
  }
  public UUID getId() {
    return id;
  }
  public String getAmount() {
    return amount;
  }
  public String getCurrency() {
    return currency;
  }
  public CheckoutId getCheckout() {
    return checkout;
  }
  public BuyerInfo getBuyer() {
    return buyer;
  }
  public PaymentOrderId getPaymentOrder() {
    return paymentOrder;
  }
  public SellerInfo getSellerInfo() {
    return sellerInfo;
  }

}
