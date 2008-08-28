/*
 * Copyright (c) 2008, IETR/INSA of Rennes
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 *   * Redistributions of source code must retain the above copyright notice,
 *     this list of conditions and the following disclaimer.
 *   * Redistributions in binary form must reproduce the above copyright notice,
 *     this list of conditions and the following disclaimer in the documentation
 *     and/or other materials provided with the distribution.
 *   * Neither the name of the IETR/INSA of Rennes nor the names of its
 *     contributors may be used to endorse or promote products derived from this
 *     software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY
 * WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 */
package net.sf.graphiti.ui.actions;

import net.sf.graphiti.ui.editors.GraphEditor;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.actions.RetargetAction;

/**
 * This class provides an automatic layout retarget action.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class AutomaticallyLayoutRetargetAction extends RetargetAction {

	public class MenuCreator implements IMenuCreator {
		
		public void dispose() {
		}

		public Menu getMenu(Control parent) {
			if (layoutMenu == null) {
				layoutMenu = new Menu(parent);
				createLayoutMenu();
			}
			return layoutMenu;
		}

		public Menu getMenu(Menu parent) {
			if (layoutMenu == null) {
				layoutMenu = new Menu(parent);
				createLayoutMenu();
			}
			return layoutMenu;
		}

	}

	private Menu layoutMenu;

	/**
	 * Constructs a AutomaticallyLayoutAction.
	 */
	public AutomaticallyLayoutRetargetAction() {
		super(AutomaticallyLayoutAction.getActionId(), "Layout",
				AS_DROP_DOWN_MENU);
		setToolTipText("Automatically layout the graph in the active editor");
		setMenuCreator(new MenuCreator());
	}

	private void createLayoutMenu() {
		MenuItem item = new MenuItem(layoutMenu, SWT.NONE);
		item.setText("Left-to-right");
		item.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (getActivePart() instanceof GraphEditor) {
					GraphEditor editor = (GraphEditor) getActivePart();
					editor.automaticallyLayout(PositionConstants.EAST);
				}
			}

		});

		item = new MenuItem(layoutMenu, SWT.NONE);
		item.setText("Top-to-bottom");
		item.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (getActivePart() instanceof GraphEditor) {
					GraphEditor editor = (GraphEditor) getActivePart();
					editor.automaticallyLayout(PositionConstants.SOUTH);
				}
			}

		});
	}

	public void dispose() {
		super.dispose();
		if (layoutMenu != null) {
			for (int i = 0; i < layoutMenu.getItemCount(); i++) {
				MenuItem menuItem = layoutMenu.getItem(i);
				menuItem.dispose();
			}
			layoutMenu.dispose();
			layoutMenu = null;
		}
	}
}
