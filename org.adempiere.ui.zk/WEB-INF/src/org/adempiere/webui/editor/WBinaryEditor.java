/******************************************************************************
 * Copyright (C) 2008 Low Heng Sin  All Rights Reserved.                      *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/

package org.adempiere.webui.editor;


import java.util.logging.Level;

import org.adempiere.webui.LayoutUtils;
import org.adempiere.webui.adwindow.ADWindow;
import org.adempiere.webui.adwindow.ADWindowContent;
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.event.DialogEvents;
import org.adempiere.webui.event.ValueChangeEvent;
import org.adempiere.webui.window.WMediaDialog;
import org.compiere.model.GridField;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;

/**
 * Default editor for {@link DisplayType#Binary}.<br/>
 * Implemented with {@link Button} component and {@link WMediaDialog} dialog.
 * @author Low Heng Sin
 */
public class WBinaryEditor extends WEditor
{
    private static final String[] LISTENER_EVENTS = {Events.ON_CLICK};
    
    /**	Logger			*/
	private static final CLogger log = CLogger.getCLogger(WBinaryEditor.class);
    
    private boolean         m_mandatory;
    /** Binary data */
    private Object          m_data;

    /** ADWindow instance that own this editor */
	private ADWindow adwindow;
   
	/**
	 * 
	 * @param gridField
	 */
	public WBinaryEditor(GridField gridField)
	{
		this(gridField, false, null);
	}
	
	/**
	 * 
	 * @param gridField
	 * @param tableEditor
	 * @param editorConfiguration
	 */
    public WBinaryEditor(GridField gridField, boolean tableEditor, IEditorConfiguration editorConfiguration)
    {
        super(new Button(), gridField, tableEditor, editorConfiguration);
        init();
    }

    /**
     * Init component
     */
    private void init()
    {
        label.setValue(" ");
        getComponent().setLabel("-");
        getComponent().setTooltiptext(gridField.getDescription());
    }

    @Override
    public String getDisplay()
    {
        return getComponent().getLabel();
    }

    @Override
    public Object getValue()
    {
        return m_data;
    }

    @Override
    public boolean isMandatory()
    {
        return m_mandatory;
    }
   
    
    @Override
    public void setMandatory(boolean mandatory)
    {
        m_mandatory = mandatory;
    }

    @Override
    public Button getComponent() {
    	return (Button) component;
    }
    
    @Override
	public boolean isReadWrite() {
		return getComponent().isEnabled();
	}

	@Override
	public void setReadWrite(boolean readWrite) {
		getComponent().setEnabled(readWrite);
	}

	@Override
    public void setValue(Object value)
    {
    	if (log.isLoggable(Level.CONFIG)) log.config("=" + value);
		m_data = value;
		if (m_data == null)
			getComponent().setLabel("-");
		else
		{
			String text = "?";
			if (m_data instanceof byte[])
			{
				byte[] bb = (byte[])m_data;
				text = "#" + bb.length;
			}
			else
			{
				text = m_data.getClass().getName();
				int index = text.lastIndexOf('.');
				if (index != -1)
					text = text.substring(index+1);
			}
			getComponent().setLabel(text);
		}
    }
    
    @Override
    public String[] getEvents()
    {
        return LISTENER_EVENTS;
    }

    @Override
	public void onEvent(Event event) throws Exception 
	{
		if (Events.ON_CLICK.equals(event.getName()))
		{
			final WMediaDialog dialog = new WMediaDialog(gridField.getHeader(), m_data);
			dialog.addEventListener(DialogEvents.ON_WINDOW_CLOSE, new EventListener<Event>() {

				@Override
				public void onEvent(Event event) throws Exception {
					if (adwindow != null) 
					{
						adwindow.getADWindowContent().hideBusyMask();
						adwindow = null;
					}
					if (!dialog.isCancel() && dialog.isChange())
					{
						Object oldValue = m_data;
						Object newValue = dialog.getData();
						if (oldValue != null && newValue != null && oldValue.equals(newValue)) {
				    	    return;
				    	}
				        if (oldValue == null && newValue == null) {
				        	return;
				        }
				        ValueChangeEvent changeEvent = new ValueChangeEvent(WBinaryEditor.this, getColumnName(), oldValue, newValue);
				        fireValueChange(changeEvent);
						setValue(newValue);
					}
				}				
			});			
			adwindow = ADWindow.findADWindow(getComponent());
			if (adwindow != null) {
				ADWindowContent content = adwindow.getADWindowContent();				
				content.getComponent().getParent().appendChild(dialog);
				content.showBusyMask(dialog);
				LayoutUtils.openOverlappedWindow(content.getComponent().getParent(), dialog, "middle_center");
				dialog.focus();
			} else {
				AEnv.showWindow(dialog);
			}			
		}
	}
}
