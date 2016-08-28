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
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.event.MouseAdapter;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("rawtypes")
public abstract class AccordionItem extends JLabel implements Comparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Boolean parameter binds state of element. SELECTED or NOT. */
	protected boolean selected = false;
	/** <code>IconImage</code> used when item is not selected. */
	protected ImageIcon normalIcon;
	/** <code>IconImage</code> used when item is selected. */
	protected ImageIcon selectedIcon;
	/** Index value for ordering items in menu tree */
	protected int index;
	/** String value for ordering items in menu tree */
	protected String module;

	/**
	 * Default Constructor with defualt Title string.
	 *
	 * @param text
	 *            Title String displayed.
	 */
	public AccordionItem(String text) {
		super(text);
		setOpaque(false);
		addMouseListener(getDefaultMouseActions());
		setNormalIcon(getDefaultNormalIcon());
		setSelectedIcon(getDefaultSelectedIcon());
		setSelected(false);
	}

	/**
	 * Derived classes must implement Mouse events.
	 * 
	 * @return {@link MouseAdapter} with only used events.
	 */
	public abstract MouseAdapter getDefaultMouseActions();

	/**
	 * Derived classes have to create {@link ImageIcon} displayed when item is
	 * not selected.
	 * 
	 * @return <code>ImageIcon</code> object.
	 */
	public abstract ImageIcon getDefaultNormalIcon();

	/**
	 * Derived classes have to create {@link ImageIcon} displayed when item is
	 * selected.
	 * 
	 * @return <code>ImageIcon</code> object.
	 */
	public abstract ImageIcon getDefaultSelectedIcon();

	/**
	 * Derived classes have to create a {@link Paint} object drawn in
	 * background. Like {@link GradientPaint}.
	 * 
	 * @return <code>Paint</code> object.
	 */
	public abstract Paint getDefaultBackgroundPaint();

	/**
	 * Simple switch of state. TRUE to FALSE, FALSE to TRUE.
	 */
	public final void switchState() {
		setSelected(!isSelected());
	}

	/**
	 *
	 * @return <code>TRUE</code> if item is selected; <code>FALSE</code>
	 *         otherwise.
	 */
	public boolean isSelected() {
		return selected;
	}

	/**
	 * Changes state of object. If <code>TRUE</code> sets the appropriate image
	 * and BOLD font. The same thing otherwise.
	 * 
	 * @param state
	 *            Boolean state of object.
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
		if (selected) {
			setIcon(selectedIcon);
			setFont(getFont().deriveFont(Font.BOLD));
		} else {
			setIcon(normalIcon);
			setFont(getFont().deriveFont(Font.PLAIN));
		}
	}

	/**
	 * Draws {@link Paint} object on background if any.
	 * 
	 * @param g
	 *            <code>Graphics</code> object of JComponent.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		if (getDefaultBackgroundPaint() != null) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setPaint(getDefaultBackgroundPaint());
			g2d.setColor(new Color(214,	214, 214));
			g2d.fillRect(0, 0, getWidth(), getHeight());
		}
		super.paintComponent(g);
	}

	/**
	 * @return <code>ImageIcon</code> object of Unselected image status.
	 */
	public ImageIcon getNormalIcon() {
		return normalIcon;
	}

	/**
	 * Sets <code>ImageIcon</code> object for Unselected status.
	 */
	public void setNormalIcon(ImageIcon normalIcon) {
		this.normalIcon = normalIcon;
		setSelected(selected);
	}

	/**
	 * @return <code>ImageIcon</code> object of Selected image status.
	 */
	public ImageIcon getSelectedIcon() {
		return selectedIcon;
	}

	/**
	 * Sets <code>ImageIcon</code> object for Selected status.
	 */
	public void setSelectedIcon(ImageIcon selectedIcon) {
		this.selectedIcon = selectedIcon;
		setSelected(selected);
	}

	/**
	 * 
	 * @return Position in menu tree.
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Sets position in menu tree.
	 * 
	 * @param index
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * Implements <code>Comparable</code> interface. Items are ordered by their
	 * index value.
	 * 
	 * @param o
	 *            Target AccordionItem to compare.
	 * @return See {@link Comparable} usage.
	 */
	public int compareTo(Object o) {
		AccordionItem target = (AccordionItem) o;
		if (getIndex() == target.getIndex()) {
			return 0;
		} else if (getIndex() > target.getIndex()) {
			return 1;
		} else {
			return -1;
		}
	}
	
	/**
	 * Sets module in menu tree.
	 * 
	 * @param module
	 */
	public void setModule(String module) {
		this.module = module;
	}

	public String getModule() {
		return module;
	}
}
