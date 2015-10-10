/*******************************************************************************
 * Copyright (c) 2015 hangum.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v2.1
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     hangum - initial API and implementation
 ******************************************************************************/
package com.hangum.tadpole.commons.admin.core.actions;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;

import com.hangum.tadpole.commons.admin.core.Activator;
import com.hangum.tadpole.commons.admin.core.editors.sqlaudit.AdminSQLAuditEditor;
import com.hangum.tadpole.commons.admin.core.editors.sqlaudit.AdminSQLAuditEditorInput;
import com.hangum.tadpole.commons.exception.dialog.ExceptionDetailsErrorDialog;
import com.swtdesigner.ResourceManager;

/**
 * Admin SQL Audit action
 * 
 * @author hangum
 *
 */
public class AdminSQLAuditAction extends Action implements ISelectionListener, IWorkbenchAction {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AdminSQLAuditAction.class);
	private final static String ID = "com.hangum.tadpole.commons.admin.core.actions.admin.global.SQLAuditAction"; //$NON-NLS-1$
	private final IWorkbenchWindow window;
	
	public AdminSQLAuditAction(IWorkbenchWindow window) {
		this.window = window;
		
		setId(ID);
		setText("Admin SQL Audit");
		setToolTipText("Admin SQL Audit");
		setImageDescriptor(ResourceManager.getPluginImageDescriptor(Activator.PLUGIN_ID, "resources/icons/sqlaudit.png")); //$NON-NLS-1$
		setEnabled(true);
	}
	
	@Override
	public void run() {
		try {
			AdminSQLAuditEditorInput sqlAuditInput = new AdminSQLAuditEditorInput();
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(sqlAuditInput, AdminSQLAuditEditor.ID);
		} catch (PartInitException e) {
			logger.error("Admin SQL Audit editor", e); //$NON-NLS-1$
			
			Status errStatus = new Status(IStatus.ERROR, Activator.PLUGIN_ID, e.getMessage(), e); //$NON-NLS-1$
			ExceptionDetailsErrorDialog.openError(null, "Error", "Admin SQL Audit Action", errStatus); //$NON-NLS-1$
		}
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
	}

	@Override
	public void dispose() {
	}

}
