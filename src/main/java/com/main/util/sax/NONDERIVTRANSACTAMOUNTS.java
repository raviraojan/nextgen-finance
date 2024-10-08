//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.0 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.09.23 at 07:16:49 PM EDT 
//


package com.main.util.sax;

import lombok.ToString;


/**
 * 
 *                 Must provide a number of shares.
 *                 Must provide a dollar value and/or a footnote.
 *                 Must provide a code indicating Acquired or Disposed Of.
 *             
 * 
 * <p>Java class for NONDERIV_TRANSACT_AMOUNTS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NONDERIV_TRANSACT_AMOUNTS"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="transactionShares" type="{}NUMBER_WITH_FOOTNOTE"/&gt;
 *         &lt;element name="transactionPricePerShare" type="{}OPT_NUMBER_WITH_FOOTNOTE"/&gt;
 *         &lt;element name="transactionAcquiredDisposedCode" type="{}ACQ_DISP_CODE"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@ToString
public class NONDERIVTRANSACTAMOUNTS {

    protected NUMBERWITHFOOTNOTE transactionShares;
    protected OPTNUMBERWITHFOOTNOTE transactionPricePerShare;
    protected ACQDISPCODE transactionAcquiredDisposedCode;

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
     *     {@link ACQDISPCODE }
     *     
     */
    public ACQDISPCODE getTransactionAcquiredDisposedCode() {
        return transactionAcquiredDisposedCode;
    }

    /**
     * Sets the value of the transactionAcquiredDisposedCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link ACQDISPCODE }
     *     
     */
    public void setTransactionAcquiredDisposedCode(ACQDISPCODE value) {
        this.transactionAcquiredDisposedCode = value;
    }

}
