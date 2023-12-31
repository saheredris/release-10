/***********************************************************************
 * This file is part of iDempiere ERP Open Source                      *
 * http://www.idempiere.org                                            *
 *                                                                     *
 * Copyright (C) Contributors                                          *
 *                                                                     *
 * This program is free software; you can redistribute it and/or       *
 * modify it under the terms of the GNU General Public License         *
 * as published by the Free Software Foundation; either version 2      *
 * of the License, or (at your option) any later version.              *
 *                                                                     *
 * This program is distributed in the hope that it will be useful,     *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of      *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the        *
 * GNU General Public License for more details.                        *
 *                                                                     *
 * You should have received a copy of the GNU General Public License   *
 * along with this program; if not, write to the Free Software         *
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,          *
 * MA 02110-1301, USA.                                                 *
 *                                                                     *
 * Contributors:                                                       *
 * - hengsin                         								   *
 **********************************************************************/
package org.idempiere.test.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.compiere.model.MBPartner;
import org.compiere.model.MPayment;
import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfo;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;
import org.compiere.util.Util;
import org.compiere.wf.MWorkflow;
import org.idempiere.test.AbstractTestCase;
import org.idempiere.test.DictionaryIDs;
import org.junit.jupiter.api.Test;

/**
 * 
 * @author hengsin
 *
 */
public class PaymentTest extends AbstractTestCase {

	public PaymentTest() {
	}

	@Test
	public void testClearCreditCardFields() {
		MPayment payment = new MPayment(Env.getCtx(), 0, getTrxName());
		payment.setC_DocType_ID(true);
		payment.setC_BPartner_ID(DictionaryIDs.C_BPartner.JOE_BLOCK.id);
		payment.setTenderType(MPayment.TENDERTYPE_CreditCard);
		payment.setCreditCard(MPayment.TRXTYPE_Sales, MPayment.CREDITCARDTYPE_MasterCard, "5555555555554444", "123", "0122");
		int C_BankAccount_ID = DB.getSQLValueEx(getTrxName(), "SELECT C_BankAccount_ID FROM C_BankAccount WHERE IsActive='Y' AND AD_Client_ID=? "
				+ "AND IsDefault='Y' ORDER BY C_BankAccount_ID", getAD_Client_ID());
		payment.setC_BankAccount_ID(C_BankAccount_ID);
		payment.setC_Currency_ID(Env.getContextAsInt(Env.getCtx(), Env.C_CURRENCY_ID));
		payment.saveEx();
		assertEquals("123", payment.getCreditCardVV());
		
		payment.setTenderType(MPayment.TENDERTYPE_Check);
		payment.saveEx();
		assertTrue(Util.isEmpty(payment.getCreditCardVV()), "Credit card verification code not clear after change of tender type: "+payment.getCreditCardVV());
	}
	
	/**
	 * Test cases for Credit Check
	 */
	@Test
	public void testCreditCheckPayment()
	{
		Timestamp today = TimeUtil.getDay(System.currentTimeMillis());
		// Joe Block
		MBPartner bp = MBPartner.get(Env.getCtx(), DictionaryIDs.C_BPartner.JOE_BLOCK.id, getTrxName());
		bp.setSOCreditStatus(MBPartner.SOCREDITSTATUS_CreditStop);
		bp.saveEx();

		MPayment payment = new MPayment(Env.getCtx(), 0, getTrxName());
		payment.setC_BPartner_ID(bp.getC_BPartner_ID());
		payment.setC_BankAccount_ID(DictionaryIDs.C_BankAccount.HQ_POS_CASH.id);
		payment.setC_Currency_ID(DictionaryIDs.C_Currency.USD.id);
		payment.setAD_Org_ID(DictionaryIDs.AD_Org.HQ.id);
		payment.setC_DocType_ID(false);
		payment.setDateTrx(today);
		payment.setPayAmt(new BigDecimal(1000));
		payment.setDateAcct(today);
		payment.saveEx();

		payment.load(getTrxName());
		ProcessInfo info = MWorkflow.runDocumentActionWorkflow(payment, DocAction.ACTION_Prepare);
		assertTrue(info.isError(), info.getSummary());
		assertEquals(DocAction.STATUS_Invalid, payment.getDocStatus());

		bp.setSOCreditStatus(MBPartner.SOCREDITSTATUS_NoCreditCheck);
		bp.saveEx();

		info = MWorkflow.runDocumentActionWorkflow(payment, DocAction.ACTION_Complete);
		assertFalse(info.isError(), info.getSummary());
		assertEquals(DocAction.STATUS_Completed, payment.getDocStatus());

		info = MWorkflow.runDocumentActionWorkflow(payment, DocAction.ACTION_Reverse_Accrual);
		assertFalse(info.isError(), info.getSummary());
		assertEquals(DocAction.STATUS_Reversed, payment.getDocStatus());
	}
}
