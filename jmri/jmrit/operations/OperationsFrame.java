//OperationsFrame.java

package jmri.jmrit.operations;

import java.awt.GridBagConstraints;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


/**
 * Frame for operations
 * 
 * @author Dan Boudreau Copyright (C) 2008
 * @version $Revision: 1.6 $
 */

public class OperationsFrame extends jmri.util.JmriJFrame {

	static final ResourceBundle rb = ResourceBundle.getBundle("jmri.jmrit.operations.JmritOperationsBundle");


	public OperationsFrame(String s) {
		super(s);
	}
	
	public OperationsFrame() {
		super();
	}

	protected void addItem(JComponent c, int x, int y) {
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = x;
		gc.gridy = y;
		gc.weightx = 100.0;
		gc.weighty = 100.0;
		getContentPane().add(c, gc);
	}

	protected void addItemLeft(JComponent c, int x, int y) {
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = x;
		gc.gridy = y;
		gc.weightx = 100.0;
		gc.weighty = 100.0;
		gc.anchor = GridBagConstraints.WEST;
		getContentPane().add(c, gc);
	}
	protected void addItemWidth(JComponent c, int width, int x, int y) {
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = x;
		gc.gridy = y;
		gc.gridwidth = width;
		gc.weightx = 100.0;
		gc.weighty = 100.0;
		getContentPane().add(c, gc);
	}
	
	protected void addItem(JPanel p, JComponent c, int x, int y) {
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = x;
		gc.gridy = y;
		gc.weightx = 100.0;
		gc.weighty = 100.0;
		p.add(c, gc);
	}
	
	protected void addItemLeft(JPanel p, JComponent c, int x, int y) {
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = x;
		gc.gridy = y;
		gc.weightx = 100.0;
		gc.weighty = 100.0;
		gc.anchor = GridBagConstraints.WEST;
		p.add(c, gc);
	}
	
	protected void addItemWidth(JPanel p, JComponent c, int width, int x, int y) {
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = x;
		gc.gridy = y;
		gc.gridwidth = width;
		gc.weightx = 100.0;
		gc.weighty = 100.0;
		gc.anchor = GridBagConstraints.WEST;
		p.add(c, gc);
	}
	
	protected void addButtonAction(JButton b) {
		b.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				buttonActionPerformed(e);
			}
		});
	}
	
	protected void buttonActionPerformed(java.awt.event.ActionEvent ae) {
		log.debug("button action not overridden");
	}
	
	protected void addRadioButtonAction(JRadioButton b) {
		b.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				radioButtonActionPerformed(e);
			}
		});
	}
	
	protected void radioButtonActionPerformed(java.awt.event.ActionEvent ae) {
		log.debug("radio button action not overridden");
	}
	
	protected void addCheckBoxAction(JCheckBox b) {
		b.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				checkBoxActionPerformed(e);
			}
		});
	}
	
	protected void checkBoxActionPerformed(java.awt.event.ActionEvent ae) {
		log.debug("check box action not overridden");
	}
	
	protected void addComboBoxAction(JComboBox b) {
		b.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				comboBoxActionPerformed(e);
			}
		});
	}
	
	// location combo box
	protected void comboBoxActionPerformed(java.awt.event.ActionEvent ae) {
		log.debug("combo box action not overridden");
	}

	static org.apache.log4j.Logger log = org.apache.log4j.Logger
	.getLogger(OperationsFrame.class.getName());
}
