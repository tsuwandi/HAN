/*
 *  Copyright 2010 Daniele.
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */
package main.component.menu;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

public class AccordionRootItem extends AccordionItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private AccordionBranch branchPanel;

	/**
	 * Simple constructor. Create a void JPanel, it will contains menu leafs.
	 * 
	 * @param text
	 *            Title of menu.
	 */
	public AccordionRootItem(String text) {
		super(text);
		this.branchPanel = new AccordionBranch();
	}

	/**
	 * Sets default mouse events. Show <code>HAND_CURSOR</code> when mouse is
	 * over.
	 * 
	 * @return {@link MouseAdapter} object.
	 */
	@Override
	public MouseAdapter getDefaultMouseActions() {
		return new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		};
	}

	/**
	 * Creates an <code>IconImage</code> for when menu is collapsed.
	 * 
	 * @return {@ IconImage} object containing image reference.
	 */
	@Override
	public ImageIcon getDefaultNormalIcon() {
		return new ImageIcon(this.getClass().getResource("resources/list_plus.png"));
	}

	/**
	 * Creates an <code>IconImage</code> for when menu is expanded.
	 * 
	 * @return {@ IconImage} object containing image reference.
	 */
	@Override
	public ImageIcon getDefaultSelectedIcon() {
		return new ImageIcon(this.getClass().getResource("resources/list_minus.png"));
	}

	/**
	 * Creates a {@link GradientPaint} surface for background; 3D effect for
	 * menu item.
	 * 
	 * @return
	 */
	@Override
	public Paint getDefaultBackgroundPaint() {
		Color c1, c2;
		if (isSelected()) {
			c2 = getBackground();
			c1 = c2.darker();
		} else {
			c1 = getBackground();
			c2 = c1.darker();
		}
		return new GradientPaint(0, 0, c1, 0, getHeight(), c2);
	}

	/**
	 *
	 * @return JPanel object that is leaf containter. It is used as an handle to
	 *         relative panel, rather than create it externally and bind them
	 *         togheter.
	 */
	public AccordionBranch getBranchPanel() {
		return branchPanel;
	}
}
