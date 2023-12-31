package org.idempiere.fa.process;

import org.compiere.model.MAssetAddition;
import org.compiere.model.MMatchInv;
import org.compiere.model.MProcessPara;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.idempiere.fa.exceptions.AssetException;



/**
 * Create asset from match invoice process
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 */
@org.adempiere.base.annotation.Process
public class A_Asset_CreateFromMatchInv extends SvrProcess {
	private int p_M_MatchInv_ID = -1;
	
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("M_MatchInv_ID"))
				p_M_MatchInv_ID = para[i].getParameterAsInt();
			else
				MProcessPara.validateUnknownParameter(getProcessInfo().getAD_Process_ID(), para[i]);
		}
	}	//	prepare 
	
	protected String doIt() throws Exception
	{
		MMatchInv match = new MMatchInv(getCtx(), p_M_MatchInv_ID, get_TrxName());
		if (match == null || match.get_ID() <= 0) {
			throw new AssetException("@NotFound@ @M_MatchInv_ID@=" + match + "(ID="+p_M_MatchInv_ID+")");
		}
		MAssetAddition assetAdd = MAssetAddition.createAsset(match);
		
		return "@A_Asset_Addition_ID@ - " + assetAdd;
	}
}
