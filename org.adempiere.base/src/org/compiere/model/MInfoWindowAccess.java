/**
 * 
 */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * @author hengsin
 *
 */
public class MInfoWindowAccess extends X_AD_InfoWindow_Access {

	/**
	 * generated serial id
	 */
	private static final long serialVersionUID = -5134731157350014858L;

    /**
    * UUID based Constructor
    * @param ctx  Context
    * @param AD_InfoWindow_Access_UU  UUID key
    * @param trxName Transaction
    */
    public MInfoWindowAccess(Properties ctx, String AD_InfoWindow_Access_UU, String trxName) {
        super(ctx, AD_InfoWindow_Access_UU, trxName);
    }

	/**
	 * @param ctx
	 * @param ignored
	 * @param trxName
	 */
	public MInfoWindowAccess(Properties ctx, int ignored, String trxName) {
		super(ctx, 0, trxName);
		if (ignored != 0)
			throw new IllegalArgumentException("Multi-Key");
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MInfoWindowAccess(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * 	Parent Constructor
	 *	@param parent parent
	 *	@param AD_Role_ID role id
	 */
	public MInfoWindowAccess (MInfoWindow parent, int AD_Role_ID)
	{
		super (parent.getCtx(), 0, parent.get_TrxName());
		MRole role = MRole.get(parent.getCtx(), AD_Role_ID);
		setClientOrg(role);
		setAD_InfoWindow_ID(parent.getAD_InfoWindow_ID());
		setAD_Role_ID (AD_Role_ID);
	}	//	MInfoWindowAccess

}
