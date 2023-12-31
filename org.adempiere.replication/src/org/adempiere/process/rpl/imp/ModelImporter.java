/**********************************************************************
 * This file is part of Adempiere ERP Bazaar                          * 
 * http://www.adempiere.org                                           * 
 *                                                                    * 
 * Copyright (C) Trifon Trifonov.                                     * 
 * Copyright (C) Contributors                                         * 
 *                                                                    * 
 * This program is free software; you can redistribute it and/or      * 
 * modify it under the terms of the GNU General Public License        * 
 * as published by the Free Software Foundation; either version 2     * 
 * of the License, or (at your option) any later version.             * 
 *                                                                    * 
 * This program is distributed in the hope that it will be useful,    * 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of     * 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the       * 
 * GNU General Public License for more details.                       * 
 *                                                                    * 
 * You should have received a copy of the GNU General Public License  * 
 * along with this program; if not, write to the Free Software        * 
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,         * 
 * MA 02110-1301, USA.                                                * 
 *                                                                    * 
 * Contributors:                                                      * 
 *  - Trifon Trifonov (trifonnt@users.sourceforge.net)                *
 *                                                                    *
 * Sponsors:                                                          *
 *  - E-evolution (http://www.e-evolution.com/)                       *
 **********************************************************************/
package org.adempiere.process.rpl.imp;

import java.io.File;
import java.util.logging.Level;

import org.adempiere.process.rpl.XMLHelper;
import org.compiere.model.MProcessPara;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.w3c.dom.Document;

/**
 * 
 * @author Trifon N. Trifonov
 * @author victor.perez@e-evolution.com 
 * FB  [1963487 ] Is necessary new process to export and import with an Export
 * @see  https://sourceforge.net/p/adempiere/feature-requests/423/
 * @version $Id:$
 */
@org.adempiere.base.annotation.Process
public class ModelImporter extends SvrProcess {

	/** Client Parameter */
	protected int p_AD_Client_ID = 0;

	/** Document Type Parameter */
	protected int p_C_DocType_ID = 0;

	/** Record ID */
	protected int p_Record_ID = 0;
	/** EXP_Format_ID */
	protected int p_EXP_Format_ID = 0;	
	/** File Name **/
	protected String p_FileName = "";
	
	/** Table ID */
	int AD_Table_ID = 0;
	
	
	/**
	 * Get Parameters
	 */
	protected void prepare() {

		p_Record_ID = getRecord_ID();
		if (p_AD_Client_ID == 0)
			p_AD_Client_ID = Env.getAD_Client_ID(getCtx());
		AD_Table_ID = getTable_ID();

		StringBuilder sb = new StringBuilder("AD_Table_ID=").append(AD_Table_ID);
		sb.append("; Record_ID=").append(getRecord_ID());
		// Parameter
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("EXP_Format_ID"))
				p_EXP_Format_ID = para[i].getParameterAsInt();
			else if (name.equals("FileName"))
				p_FileName = (String)para[i].getParameter();
			else
				MProcessPara.validateUnknownParameter(getProcessInfo().getAD_Process_ID(), para[i]);
		}
		
		if(p_EXP_Format_ID == 0)
			p_EXP_Format_ID = p_Record_ID;
		if(p_FileName == null)
		{
			// Load XML file and parse it
			String fileNameOr = org.compiere.util.Ini.findAdempiereHome()
			+ File.separator  
			+ "data"
			+ File.separator
			+ "ExportFile.xml";
			p_FileName = fileNameOr;
		}		
		
		if (log.isLoggable(Level.INFO)) log.info(sb.toString());
	}

	/**
	 * Process
	 * 
	 * @return info
	 */
	protected String doIt() throws Exception 
	{
		StringBuffer result = new StringBuffer("");
		
		// Load XML file and parse it
		/*String fileNameOr = org.compiere.util.Ini.findAdempiereHome()
		+ File.separator  
		+ "data"
		+ File.separator;
		
		String pathToXmlFile = fileNameOr+"XmlExport-test.xml";
		Document documentToBeImported = XMLHelper.createDocumentFromFile(pathToXmlFile);*/
		Document documentToBeImported = XMLHelper.createDocumentFromFile(p_FileName);
		
		ImportHelper impHelper = new ImportHelper(getCtx());
		impHelper.importXMLDocument(result, documentToBeImported, get_TrxName());

		addLog(0, null, null, Msg.getMsg(getCtx(), "ImportModelProcessResult") + "\n" + result.toString());
		return result.toString();
	}
}
