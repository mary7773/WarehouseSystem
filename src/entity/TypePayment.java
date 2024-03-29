package entity;


// Generated Mar 1, 2014 7:49:42 PM by Hibernate Tools 4.0.0

import java.io.Serializable;

import java.util.HashSet;
import java.util.Set;


/**
 * TypePayment generated by hbm2java
 */
public class TypePayment
    implements Serializable
{

    /** field <code>serialVersionUID</code> */
    private static final long serialVersionUID = 1L;
    private int typePaymId;
    private String typePayment;
    private Set<Payment> payments = new HashSet<Payment>(0);


    public TypePayment()
    {}


    public TypePayment(int typePaymId, String typePayment)
    {
        this.typePaymId = typePaymId;
        this.typePayment = typePayment;
    }


    public TypePayment(int typePaymId, String typePayment, Set<Payment> payments)
    {
        this.typePaymId = typePaymId;
        this.typePayment = typePayment;
        this.payments = payments;
    }


    public int getTypePaymId()
    {
        return this.typePaymId;
    }


    public void setTypePaymId(int typePaymId)
    {
        this.typePaymId = typePaymId;
    }


    public String getTypePayment()
    {
        return this.typePayment;
    }


    public void setTypePayment(String typePayment)
    {
        this.typePayment = typePayment;
    }


    public Set<Payment> getPayments()
    {
        return this.payments;
    }


    public void setPayments(Set<Payment> payments)
    {
        this.payments = payments;
    }

}
