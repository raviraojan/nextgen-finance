//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.0 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.09.23 at 07:16:49 PM EDT 
//


package com.main.util.sax;




/**
 * 
 *                 Must provide a number of shares or a total dollar value.
 *                 Must provide a price per share and/or a footnote.
 *                 Must provide a code indicating Acquired or Disposed Of.
 *             
 * 
 * <p>Java class for DERIV_TRANSACT_NO_FOOT complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DERIV_TRANSACT_NO_FOOT"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;choice&gt;
 *           &lt;element name="transactionShares" type="{}NUMBER_WITH_FOOTNOTE"/&gt;
 *           &lt;element name="transactionTotalValue" type="{}NUMBER_WITH_FOOTNOTE"/&gt;
 *         &lt;/choice&gt;
 *         &lt;element name="transactionPricePerShare" type="{}OPT_NUMBER_WITH_FOOTNOTE"/&gt;
 *         &lt;element name="transactionAcquiredDisposedCode" type="{}ACQ_DISP_NO_FOOT"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
public class DERIVTRANSACTNOFOOT {

    protected NUMBERWITHFOOTNOTE transactionShares;
    protected NUMBERWITHFOOTNOTE transactionTotalValue;
    protected OPTNUMBERWITHFOOTNOTE transactionPricePerShare;
    protected ACQDISPNOFOOT transactionAcquiredDisposedCode;

    /**
     * Gets the value of the transactionShares property.
     * 
     * @return
     *     possible object is
     *     {@link NUMBERWITHFOOTNOTE }
     *     
     */
    public NUMBERWITHFOOTNOTE getTransactionShares() {
        return transactionShares;
    }

    /**
     * Sets the value of the transactionShares property.
     * 
     * @param value
     *     allowed object is
     *     {@link NUMBERWITHFOOTNOTE }
     *     
     */
    public void setTransactionShares(NUMBERWITHFOOTNOTE value) {
        this.transactionShares = value;
    }

    /**
     * Gets the value of the transactionTotalValue property.
     * 
     * @return
     *     possible object is
     *     {@link NUMBERWITHFOOTNOTE }
     *     
     */
    public NUMBERWITHFOOTNOTE getTransactionTotalValue() {
        return transactionTotalValue;
    }

    /**
     * Sets the value of the transactionTotalValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link NUMBERWITHFOOTNOTE }
     *     
     */
    public void setTransactionTotalValue(NUMBERWITHFOOTNOTE value) {
        this.transactionTotalValue = value;
    }

    /**
     * Gets the value of the transactionPricePerShare property.
     * 
     * @return
     *     possible object is
     *     {@link OPTNUMBERWITHFOOTNOTE }
     *     
     */
    public OPTNUMBERWITHFOOTNOTE getTransactionPricePerShare() {
        return transactionPricePerShare;
    }

    /**
     * Sets the value of the transactionPricePerShare property.
     * 
     * @param value
     *     allowed object is
     *     {@link OPTNUMBERWITHFOOTNOTE }
     *     
     */
    public void setTransactionPricePerShare(OPTNUMBERWITHFOOTNOTE value) {
        this.transactionPricePerShare = value;
    }

    /**
     * Gets the value of the transactionAcquiredDisposedCode property.
     * 
     * @return
     *     possible object is
     *     {@link ACQDISPNOFOOT }
     *     
     */
    public ACQDISPNOFOOT getTransactionAcquiredDisposedCode() {
        return transactionAcquiredDisposedCode;
    }

    /**
     * Sets the value of the transactionAcquiredDisposedCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link ACQDISPNOFOOT }
     *     
     */
    public void setTransactionAcquiredDisposedCode(ACQDISPNOFOOT value) {
        this.transactionAcquiredDisposedCode = value;
    }

}
