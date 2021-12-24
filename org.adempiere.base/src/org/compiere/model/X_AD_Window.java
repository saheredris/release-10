/******************************************************************************
 * Product: iDempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2012 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for AD_Window
 *  @author iDempiere (generated) 
 *  @version Release 9 - $Id$ */
@org.adempiere.base.Model(table="AD_Window")
public class X_AD_Window extends PO implements I_AD_Window, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20211224L;

    /** Standard Constructor */
    public X_AD_Window (Properties ctx, int AD_Window_ID, String trxName)
    {
      super (ctx, AD_Window_ID, trxName);
      /** if (AD_Window_ID == 0)
        {
			setAD_Window_ID (0);
			setEntityType (null);
// @SQL=SELECT CASE WHEN '@P|AdempiereSys:N@'='Y' THEN 'D' ELSE get_sysconfig('DEFAULT_ENTITYTYPE','U',0,0) END FROM Dual
			setIsBetaFunctionality (false);
			setIsDefault (false);
			setIsSOTrx (true);
// Y
			setName (null);
			setWindowType (null);
// M
        } */
    }

    /** Load Constructor */
    public X_AD_Window (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 4 - System 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuilder sb = new StringBuilder ("X_AD_Window[")
        .append(get_ID()).append(",Name=").append(getName()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_Color getAD_Color() throws RuntimeException
	{
		return (org.compiere.model.I_AD_Color)MTable.get(getCtx(), org.compiere.model.I_AD_Color.Table_ID)
			.getPO(getAD_Color_ID(), get_TrxName());
	}

	/** Set System Color.
		@param AD_Color_ID Color for backgrounds or indicators
	*/
	public void setAD_Color_ID (int AD_Color_ID)
	{
		if (AD_Color_ID < 1)
			set_Value (COLUMNNAME_AD_Color_ID, null);
		else
			set_Value (COLUMNNAME_AD_Color_ID, Integer.valueOf(AD_Color_ID));
	}

	/** Get System Color.
		@return Color for backgrounds or indicators
	  */
	public int getAD_Color_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Color_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_AD_Image getAD_Image() throws RuntimeException
	{
		return (org.compiere.model.I_AD_Image)MTable.get(getCtx(), org.compiere.model.I_AD_Image.Table_ID)
			.getPO(getAD_Image_ID(), get_TrxName());
	}

	/** Set Image.
		@param AD_Image_ID Image or Icon
	*/
	public void setAD_Image_ID (int AD_Image_ID)
	{
		if (AD_Image_ID < 1)
			set_Value (COLUMNNAME_AD_Image_ID, null);
		else
			set_Value (COLUMNNAME_AD_Image_ID, Integer.valueOf(AD_Image_ID));
	}

	/** Get Image.
		@return Image or Icon
	  */
	public int getAD_Image_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Image_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Window.
		@param AD_Window_ID Data entry or display window
	*/
	public void setAD_Window_ID (int AD_Window_ID)
	{
		if (AD_Window_ID < 1)
			set_ValueNoCheck (COLUMNNAME_AD_Window_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_AD_Window_ID, Integer.valueOf(AD_Window_ID));
	}

	/** Get Window.
		@return Data entry or display window
	  */
	public int getAD_Window_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Window_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set AD_Window_UU.
		@param AD_Window_UU AD_Window_UU
	*/
	public void setAD_Window_UU (String AD_Window_UU)
	{
		set_Value (COLUMNNAME_AD_Window_UU, AD_Window_UU);
	}

	/** Get AD_Window_UU.
		@return AD_Window_UU	  */
	public String getAD_Window_UU()
	{
		return (String)get_Value(COLUMNNAME_AD_Window_UU);
	}

	/** Set Description.
		@param Description Optional short description of the record
	*/
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription()
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** EntityType AD_Reference_ID=389 */
	public static final int ENTITYTYPE_AD_Reference_ID=389;
	/** Set Entity Type.
		@param EntityType Dictionary Entity Type; Determines ownership and synchronization
	*/
	public void setEntityType (String EntityType)
	{

		set_Value (COLUMNNAME_EntityType, EntityType);
	}

	/** Get Entity Type.
		@return Dictionary Entity Type; Determines ownership and synchronization
	  */
	public String getEntityType()
	{
		return (String)get_Value(COLUMNNAME_EntityType);
	}

	/** Set Comment/Help.
		@param Help Comment or Hint
	*/
	public void setHelp (String Help)
	{
		set_Value (COLUMNNAME_Help, Help);
	}

	/** Get Comment/Help.
		@return Comment or Hint
	  */
	public String getHelp()
	{
		return (String)get_Value(COLUMNNAME_Help);
	}

	/** Set Beta Functionality.
		@param IsBetaFunctionality This functionality is considered Beta
	*/
	public void setIsBetaFunctionality (boolean IsBetaFunctionality)
	{
		set_Value (COLUMNNAME_IsBetaFunctionality, Boolean.valueOf(IsBetaFunctionality));
	}

	/** Get Beta Functionality.
		@return This functionality is considered Beta
	  */
	public boolean isBetaFunctionality()
	{
		Object oo = get_Value(COLUMNNAME_IsBetaFunctionality);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Default.
		@param IsDefault Default value
	*/
	public void setIsDefault (boolean IsDefault)
	{
		set_Value (COLUMNNAME_IsDefault, Boolean.valueOf(IsDefault));
	}

	/** Get Default.
		@return Default value
	  */
	public boolean isDefault()
	{
		Object oo = get_Value(COLUMNNAME_IsDefault);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Sales Transaction.
		@param IsSOTrx This is a Sales Transaction
	*/
	public void setIsSOTrx (boolean IsSOTrx)
	{
		set_Value (COLUMNNAME_IsSOTrx, Boolean.valueOf(IsSOTrx));
	}

	/** Get Sales Transaction.
		@return This is a Sales Transaction
	  */
	public boolean isSOTrx()
	{
		Object oo = get_Value(COLUMNNAME_IsSOTrx);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Name.
		@param Name Alphanumeric identifier of the entity
	*/
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName()
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** Set Predefined Context Variables.
		@param PredefinedContextVariables Predefined context variables to inject when opening a menu entry or a window
	*/
	public void setPredefinedContextVariables (String PredefinedContextVariables)
	{
		set_Value (COLUMNNAME_PredefinedContextVariables, PredefinedContextVariables);
	}

	/** Get Predefined Context Variables.
		@return Predefined context variables to inject when opening a menu entry or a window
	  */
	public String getPredefinedContextVariables()
	{
		return (String)get_Value(COLUMNNAME_PredefinedContextVariables);
	}

	/** Set Process Now.
		@param Processing Process Now
	*/
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing()
	{
		Object oo = get_Value(COLUMNNAME_Processing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Title Logic.
		@param TitleLogic The result determines the title to be displayed for this window
	*/
	public void setTitleLogic (String TitleLogic)
	{
		set_Value (COLUMNNAME_TitleLogic, TitleLogic);
	}

	/** Get Title Logic.
		@return The result determines the title to be displayed for this window
	  */
	public String getTitleLogic()
	{
		return (String)get_Value(COLUMNNAME_TitleLogic);
	}

	/** WindowType AD_Reference_ID=108 */
	public static final int WINDOWTYPE_AD_Reference_ID=108;
	/** Maintain = M */
	public static final String WINDOWTYPE_Maintain = "M";
	/** Query Only = Q */
	public static final String WINDOWTYPE_QueryOnly = "Q";
	/** Single Record = S */
	public static final String WINDOWTYPE_SingleRecord = "S";
	/** Transaction = T */
	public static final String WINDOWTYPE_Transaction = "T";
	/** Set WindowType.
		@param WindowType Type or classification of a Window
	*/
	public void setWindowType (String WindowType)
	{

		set_Value (COLUMNNAME_WindowType, WindowType);
	}

	/** Get WindowType.
		@return Type or classification of a Window
	  */
	public String getWindowType()
	{
		return (String)get_Value(COLUMNNAME_WindowType);
	}

	/** Set Window Height.
		@param WinHeight Window Height
	*/
	public void setWinHeight (int WinHeight)
	{
		set_Value (COLUMNNAME_WinHeight, Integer.valueOf(WinHeight));
	}

	/** Get Window Height.
		@return Window Height	  */
	public int getWinHeight()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_WinHeight);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Window Width.
		@param WinWidth Window Width
	*/
	public void setWinWidth (int WinWidth)
	{
		set_Value (COLUMNNAME_WinWidth, Integer.valueOf(WinWidth));
	}

	/** Get Window Width.
		@return Window Width	  */
	public int getWinWidth()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_WinWidth);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}