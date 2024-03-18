
package service;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for fetchTransactions complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="fetchTransactions">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="transactionsList" type="{http://service/}transactions" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fetchTransactions", propOrder = {
    "transactionsList"
})
public class FetchTransactions {

    @XmlElement(nillable = true)
    protected List<Transactions> transactionsList;

    /**
     * Gets the value of the transactionsList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the transactionsList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTransactionsList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Transactions }
     * 
     * 
     */
    public List<Transactions> getTransactionsList() {
        if (transactionsList == null) {
            transactionsList = new ArrayList<Transactions>();
        }
        return this.transactionsList;
    }

}
