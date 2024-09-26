//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.0 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.09.23 at 07:16:49 PM EDT 
//


package com.main.util.sax;

import java.util.ArrayList;
import java.util.List;
import lombok.ToString;


/**
 * <p>Java class for ACQ_DISP_CODE complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ACQ_DISP_CODE"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="value" type="{}ACQ_DISP_CODE_PICKLIST"/&gt;
 *         &lt;element name="footnoteId" type="{}FOOTNOTE_ID" maxOccurs="99" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */

@ToString
public class ACQDISPCODE {

    protected String value;
    protected List<FOOTNOTEID> footnoteId;

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the footnoteId property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the footnoteId property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFootnoteId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FOOTNOTEID }
     * 
     * 
     */
    public List<FOOTNOTEID> getFootnoteId() {
        if (footnoteId == null) {
            footnoteId = new ArrayList<FOOTNOTEID>();
        }
        return this.footnoteId;
    }

}
