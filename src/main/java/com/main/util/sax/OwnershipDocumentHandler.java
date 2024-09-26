package com.main.util.sax;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.main.util.sax.*;



public class OwnershipDocumentHandler extends DefaultHandler {

	// This is the OwnershipDocument which shall be populated while parsing the XML.
	private OwnershipDocument ownershipDocument = new OwnershipDocument();

	// As we read any XML element we will push that in this stack
	private Stack elementStack = new Stack();

	// value tag is present multiple places, to differentiate, we can
	// either add code in serial order or some pointer to distinguish,
	// I have decided to go use pointer, so that serial execution is not require

	public void startDocument() throws SAXException {

	}

	public void endDocument() throws SAXException {

	}

	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// Push it in element stack
		//System.out.println("qName qName " + qName);
		this.elementStack.push(qName);

		// "value tag is present in multiple places, so make sure, which one the pointer
		// or sax is processing, added duplicate main tag.

		// If this is start of 'user' element then prepare a new User instance and push
		// it in object stack
		// is unique, or used only one time.
		if ("ownershipDocument".equals(qName)) {
			// New ownershipDocument instance
			ownershipDocument = new OwnershipDocument();
		}

		// is unique, or used only one time.
		if ("issuer".equalsIgnoreCase(qName)) {
			ISSUER issuer = new ISSUER();

			if (this.ownershipDocument != null) {
				ownershipDocument.setIssuer(issuer);
			}
		}

		// reportingOwner is unique, or used only one time.
		if ("reportingOwner".equalsIgnoreCase(qName)) {
			// reporting ownerList is already created in ownershipDocument in get method by
			// xml generated.
			REPORTINGOWNER reportingOwner = new REPORTINGOWNER();
			ownershipDocument.getReportingOwner().add(reportingOwner);
		}
		if ("reportingOwnerId".equalsIgnoreCase(qName)) {
			REPORTINGID reportingOwnerId = new REPORTINGID();

			if (ownershipDocument != null && ownershipDocument.getReportingOwner() != null) {
				REPORTINGOWNER currReportingOwner = getLastInList(ownershipDocument.getReportingOwner());

				if (currReportingOwner != null) {
					currReportingOwner.setReportingOwnerId(reportingOwnerId);
				}

			}
		}

		if ("reportingOwnerRelationship".equalsIgnoreCase(qName)) {
			REPORTINGRELATIONSHIP reportingRelationShip = new REPORTINGRELATIONSHIP();

			if (ownershipDocument != null && ownershipDocument.getReportingOwner() != null) {
				REPORTINGOWNER currReportingOwner = getLastInList(ownershipDocument.getReportingOwner());

				if (currReportingOwner != null) {
					currReportingOwner.setReportingOwnerRelationship(reportingRelationShip);
				}

			}
		}

		// is unique, or used only one time.
		if ("nonDerivativeTable".equalsIgnoreCase(qName)) {
			NONDERIVATIVETABLE nonDerivativeTable = new NONDERIVATIVETABLE();

			ownershipDocument.setNonDerivativeTable(nonDerivativeTable);
		}

		if ("nonDerivativeTransaction".equalsIgnoreCase(qName)) {
			NONDERIVATIVETRANSACTION nonDerivativeTransaction = new NONDERIVATIVETRANSACTION();

			ownershipDocument.getNonDerivativeTable().getNonDerivativeTransactionOrNonDerivativeHolding()
					.add(nonDerivativeTransaction);
		}
		
		if ("transactionCoding".equalsIgnoreCase(qName) && getStringFromStack().contains("nonDerivativeTransactiontransactionCoding")) {
	
			NONDERIVATIVETRANSACTION nonDerivativeTrans = (NONDERIVATIVETRANSACTION) getLastInList(
					ownershipDocument.getNonDerivativeTable().getNonDerivativeTransactionOrNonDerivativeHolding());

			TRANSACTIONCODINGFOR4 transactionCoding = new TRANSACTIONCODINGFOR4();

			nonDerivativeTrans.setTransactionCoding(transactionCoding);
		}

		if ("transactionAmounts".equalsIgnoreCase(qName)
				&& getStringFromStack().contains("nonDerivativeTransactiontransactionAmounts")) {

			NONDERIVATIVETRANSACTION nonDerivativeTrans = (NONDERIVATIVETRANSACTION) getLastInList(
					ownershipDocument.getNonDerivativeTable().getNonDerivativeTransactionOrNonDerivativeHolding());

			NONDERIVTRANSACTAMOUNTS nonDerivtransAmnts = new NONDERIVTRANSACTAMOUNTS();

			nonDerivativeTrans.setTransactionAmounts(nonDerivtransAmnts);

		}

		if ("transactionShares".equalsIgnoreCase(qName)
				&& getStringFromStack().contains("nonDerivativeTransactiontransactionAmounts")) {

			NONDERIVATIVETRANSACTION nonDerivativeTrans = (NONDERIVATIVETRANSACTION) getLastInList(
					ownershipDocument.getNonDerivativeTable().getNonDerivativeTransactionOrNonDerivativeHolding());

			NUMBERWITHFOOTNOTE transactionShares = new NUMBERWITHFOOTNOTE();

			nonDerivativeTrans.getTransactionAmounts().setTransactionShares(transactionShares);

			return; // do not need to execute below stmts;
		}

		if ("transactionPricePerShare".equalsIgnoreCase(qName) && getStringFromStack()
				.contains("nonDerivativeTransactiontransactionAmountstransactionPricePerShare")) {

			NONDERIVATIVETRANSACTION nonDerivativeTrans = (NONDERIVATIVETRANSACTION) getLastInList(
					ownershipDocument.getNonDerivativeTable().getNonDerivativeTransactionOrNonDerivativeHolding());
			OPTNUMBERWITHFOOTNOTE transactionPricePerShare = new OPTNUMBERWITHFOOTNOTE();

			nonDerivativeTrans.getTransactionAmounts().setTransactionPricePerShare(transactionPricePerShare);
			return; // do not need to execute below stmts;
		}

		if ("transactionAcquiredDisposedCode".equalsIgnoreCase(qName) && getStringFromStack()
				.contains("nonDerivativeTransactiontransactionAmountstransactionAcquiredDisposedCode")) {

			NONDERIVATIVETRANSACTION nonDerivativeTrans = (NONDERIVATIVETRANSACTION) getLastInList(
					ownershipDocument.getNonDerivativeTable().getNonDerivativeTransactionOrNonDerivativeHolding());

			ACQDISPCODE transactionAcquiredDisposedCode = new ACQDISPCODE();

			nonDerivativeTrans.getTransactionAmounts()
					.setTransactionAcquiredDisposedCode(transactionAcquiredDisposedCode);
			return; // do not need to execute below stmts;
		}

		if ("postTransactionAmounts".equalsIgnoreCase(qName)
				&& getStringFromStack().contains("nonDerivativeTransactionpostTransactionAmounts")) {

			NONDERIVATIVETRANSACTION nonDerivativeTrans = (NONDERIVATIVETRANSACTION) getLastInList(
					ownershipDocument.getNonDerivativeTable().getNonDerivativeTransactionOrNonDerivativeHolding());

			POSTTRANSACTIONAMOUNTS postTranscationAmounts = new POSTTRANSACTIONAMOUNTS();

			nonDerivativeTrans.setPostTransactionAmounts(postTranscationAmounts);

		}

	}

	public void endElement(String uri, String localName, String qName) throws SAXException {
		// Remove last added element
		this.elementStack.pop();

		// User instance has been constructed so pop it from object stack and push in
		// userList
		/*
		 * if ("user".equals(qName)) { User object = (User) this.objectStack.pop();
		 * this.userList.add(object); }
		 */
	}

	/**
	 * This will be called everytime parser encounter a value node
	 */
	public void characters(char[] ch, int start, int length) throws SAXException {
		String value = new String(ch, start, length).trim();

		if (value.length() == 0) {
			return; // ignore white space
		}

		//System.out.println("current element is  " + currentElement());

		// is unique, or used only one time.
		if ("issuerCik".equals(currentElement())) {
			ownershipDocument.getIssuer().setIssuerCik(value);
			return; // do not need to execute below stmts;
		}
		if ("issuerName".equals(currentElement())) {
			ownershipDocument.getIssuer().setIssuerName(value);
			return; // do not need to execute below stmts;
		}
		if ("issuerTradingSymbol".equals(currentElement())) {
			ownershipDocument.getIssuer().setIssuerTradingSymbol(value);
			return; // do not need to execute below stmts;
		}

		if ("rptOwnerCik".equals(currentElement())) {

			getLastInList(ownershipDocument.getReportingOwner()).getReportingOwnerId().setRptOwnerCik(value);

		}
		if ("rptOwnerCcc".equals(currentElement())) {
			getLastInList(ownershipDocument.getReportingOwner()).getReportingOwnerId().setRptOwnerCcc(value);
			return; // do not need to execute below stmts;
		}
		if ("rptOwnerName".equals(currentElement())) {
			getLastInList(ownershipDocument.getReportingOwner()).getReportingOwnerId().setRptOwnerName(value);
			return; // do not need to execute below stmts;
		}

		if ("isDirector".equalsIgnoreCase(currentElement())) {
			getLastInList(ownershipDocument.getReportingOwner()).getReportingOwnerRelationship()
					.setIsDirector(convertToBoolean(value));
			return; // do not need to execute below stmts;
		}

		if ("isOfficer".equalsIgnoreCase(currentElement())) {
			getLastInList(ownershipDocument.getReportingOwner()).getReportingOwnerRelationship()
					.setIsOfficer(convertToBoolean(value));
			return; // do not need to execute below stmts;

		}

		if ("isTenPercentOwner".equalsIgnoreCase(currentElement())) {
			getLastInList(ownershipDocument.getReportingOwner()).getReportingOwnerRelationship()
					.setIsTenPercentOwner(convertToBoolean(value));
		}

		if ("isOther".equalsIgnoreCase(currentElement())) {
			getLastInList(ownershipDocument.getReportingOwner()).getReportingOwnerRelationship()
					.setIsOther(convertToBoolean(value));
			return; // do not need to execute below stmts;
		}

		if ("officerTitle".equalsIgnoreCase(currentElement())) {
			getLastInList(ownershipDocument.getReportingOwner()).getReportingOwnerRelationship().setOfficerTitle(value);
			return; // do not need to execute below stmts;
		}

		if ("otherText".equalsIgnoreCase(currentElement())) {
			getLastInList(ownershipDocument.getReportingOwner()).getReportingOwnerRelationship().setOtherText(value);
			return; // do not need to execute below stmts;
		}

		// "value and securityTitle tags are used multiple tags, need to differentiate
		// it, that is the reason for getStringFromStack method to get current node, or
		// context
		if ("value".equalsIgnoreCase(currentElement())
				&& getStringFromStack().contains("nonDerivativeTablenonDerivativeTransactionsecurityTitle")) {

			NONDERIVATIVETRANSACTION nonDerivativeTrans = (NONDERIVATIVETRANSACTION) getLastInList(
					ownershipDocument.getNonDerivativeTable().getNonDerivativeTransactionOrNonDerivativeHolding());

			SECURITYTITLE securityTitle = new SECURITYTITLE();
			securityTitle.setValue(value);
			nonDerivativeTrans.setSecurityTitle(securityTitle);

			return; // do not need to execute below stmts;
		}
		
		if ("transactionCode".equalsIgnoreCase(currentElement()) && getStringFromStack().contains("nonDerivativeTransactiontransactionCoding")) {
	
			NONDERIVATIVETRANSACTION nonDerivativeTrans = (NONDERIVATIVETRANSACTION) getLastInList(
					ownershipDocument.getNonDerivativeTable().getNonDerivativeTransactionOrNonDerivativeHolding());

			nonDerivativeTrans.getTransactionCoding().setTransactionCode(value);
		}

		// "value and securityTitle tags are used multiple tags, need to differentiate
		// it.
		if ("value".equalsIgnoreCase(currentElement())
				&& getStringFromStack().contains("nonDerivativeTransactiontransactionDatevalue")) {

			NONDERIVATIVETRANSACTION nonDerivativeTrans = (NONDERIVATIVETRANSACTION) getLastInList(
					ownershipDocument.getNonDerivativeTable().getNonDerivativeTransactionOrNonDerivativeHolding());
			DATEWITHFOOTNOTE transactionDate = new DATEWITHFOOTNOTE();
			transactionDate.setValue(value);
			nonDerivativeTrans.setTransactionDate(transactionDate);
			return; // do not need to execute below stmts;
		}

		if ("value".equalsIgnoreCase(currentElement())
				&& getStringFromStack().contains("nonDerivativeTransactiontransactionAmountstransactionShares")) {

			NONDERIVATIVETRANSACTION nonDerivativeTrans = (NONDERIVATIVETRANSACTION) getLastInList(
					ownershipDocument.getNonDerivativeTable().getNonDerivativeTransactionOrNonDerivativeHolding());

			nonDerivativeTrans.getTransactionAmounts().getTransactionShares().setValue(new BigDecimal(value));
			return; // do not need to execute below stmts;
		}

		if ("value".equalsIgnoreCase(currentElement()) && getStringFromStack()
				.contains("nonDerivativeTransactiontransactionAmountstransactionPricePerShare")) {

			NONDERIVATIVETRANSACTION nonDerivativeTrans = (NONDERIVATIVETRANSACTION) getLastInList(
					ownershipDocument.getNonDerivativeTable().getNonDerivativeTransactionOrNonDerivativeHolding());

			nonDerivativeTrans.getTransactionAmounts().getTransactionPricePerShare().setValue(new BigDecimal(value));
			return; // do not need to execute below stmts;
		}

		if ("value".equalsIgnoreCase(currentElement()) && getStringFromStack()
				.contains("nonDerivativeTransactiontransactionAmountstransactionAcquiredDisposedCode")) {

			NONDERIVATIVETRANSACTION nonDerivativeTrans = (NONDERIVATIVETRANSACTION) getLastInList(
					ownershipDocument.getNonDerivativeTable().getNonDerivativeTransactionOrNonDerivativeHolding());

			nonDerivativeTrans.getTransactionAmounts().getTransactionAcquiredDisposedCode().setValue(value);

			return; // do not need to execute below stmts;
		}

		if ("value".equalsIgnoreCase(currentElement()) && getStringFromStack()
				.contains("nonDerivativeTransactionpostTransactionAmountssharesOwnedFollowingTransaction")) {

			NONDERIVATIVETRANSACTION nonDerivativeTrans = (NONDERIVATIVETRANSACTION) getLastInList(
					ownershipDocument.getNonDerivativeTable().getNonDerivativeTransactionOrNonDerivativeHolding());

			POSTTRANSACTIONAMOUNTS postTransactionAmounts = new POSTTRANSACTIONAMOUNTS();

			NUMBERWITHFOOTNOTE sharesOwnedFollowingTransaction = new NUMBERWITHFOOTNOTE();

			sharesOwnedFollowingTransaction.setValue(new BigDecimal(value));

			postTransactionAmounts.setSharesOwnedFollowingTransaction(sharesOwnedFollowingTransaction);

			nonDerivativeTrans.setPostTransactionAmounts(postTransactionAmounts);
			return; // do not need to execute below stmts;
		}

	}

	private String getStringFromStack() {
		// TODO Auto-generated method stub

		// this is to track current tree context because value tag and security title
		// and some other tags have been re-used
		// in other nodes, to distinguish on which tag we are on and to write/update/add
		// correct data we need this,
		// this will basically creates string where we can track were we are in current
		// position.

		StringBuilder currentPathStr = new StringBuilder();

		Iterator iterator = elementStack.iterator();
		while (iterator.hasNext()) {
			currentPathStr.append(iterator.next());
		}

		return currentPathStr.toString();
	}

	/**
	 * Utility method for getting the current element in processing
	 */
	private String currentElement() {
		return (String) this.elementStack.peek();
	}

	public OwnershipDocument getOwnershipDocument() {
		return this.ownershipDocument;
	}

	public static <T> T getLastInList(List<T> list) {

		return list != null && !list.isEmpty() ? list.get(list.size() - 1) : null;
	}

	private boolean convertToBoolean(String value) {
		boolean returnValue = false;
		if ("1".equalsIgnoreCase(value) || "yes".equalsIgnoreCase(value) || "true".equalsIgnoreCase(value)
				|| "on".equalsIgnoreCase(value))
			returnValue = true;
		return returnValue;
	}

}