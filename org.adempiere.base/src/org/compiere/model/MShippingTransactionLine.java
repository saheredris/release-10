package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MShippingTransactionLine extends X_M_ShippingTransactionLine 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8148869412130244360L;

    /**
    * UUID based Constructor
    * @param ctx  Context
    * @param M_ShippingTransactionLine_UU  UUID key
    * @param trxName Transaction
    */
    public MShippingTransactionLine(Properties ctx, String M_ShippingTransactionLine_UU, String trxName) {
        super(ctx, M_ShippingTransactionLine_UU, trxName);
    }

	public MShippingTransactionLine(Properties ctx, int M_ShippingTransactionLine_ID, String trxName) 
	{
		super(ctx, M_ShippingTransactionLine_ID, trxName);
	}
	
	public MShippingTransactionLine(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}

	public MShippingTransactionLine(Properties ctx, int M_ShippingTransactionLine_ID, String trxName,
			String... virtualColumns) {
		super(ctx, M_ShippingTransactionLine_ID, trxName, virtualColumns);
	}

}
