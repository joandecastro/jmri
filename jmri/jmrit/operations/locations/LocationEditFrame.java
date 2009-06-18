// LocationEditFrame.java

package jmri.jmrit.operations.locations;

import jmri.jmrit.operations.setup.Setup;
import jmri.jmrit.operations.rollingstock.cars.CarManagerXml;
import jmri.jmrit.operations.rollingstock.cars.CarTypes;
import jmri.jmrit.operations.rollingstock.engines.EngineTypes;
import jmri.jmrit.operations.routes.RouteManagerXml;
import jmri.jmrit.operations.OperationsFrame;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;


/**
 * Frame for user edit of location
 * 
 * @author Dan Boudreau Copyright (C) 2008
 * @version $Revision: 1.4 $
 */

public class LocationEditFrame extends OperationsFrame implements java.beans.PropertyChangeListener {

	static final ResourceBundle rb = ResourceBundle.getBundle("jmri.jmrit.operations.locations.JmritOperationsLocationsBundle");
	
	YardTableModel yardModel = new YardTableModel();
	javax.swing.JTable yardTable = new javax.swing.JTable(yardModel);
	JScrollPane yardPane;
	SidingTableModel sidingModel = new SidingTableModel();
	javax.swing.JTable sidingTable = new javax.swing.JTable(sidingModel);
	JScrollPane sidingPane;
	InterchangeTableModel interchangeModel = new InterchangeTableModel();
	javax.swing.JTable interchangeTable = new javax.swing.JTable(interchangeModel);
	JScrollPane interchangePane;
	StagingTableModel stagingModel = new StagingTableModel();
	javax.swing.JTable stagingTable = new javax.swing.JTable(stagingModel);
	JScrollPane stagingPane;
	
	LocationManager manager;
	LocationManagerXml managerXml;

	Location _location = null;
	ArrayList<JCheckBox> checkBoxes = new ArrayList<JCheckBox>();
	JPanel panelCheckBoxes = new JPanel();
	JScrollPane typePane;
	JPanel directionPanel = new JPanel();

	// labels
	JLabel textName = new JLabel(rb.getString("Name"));
	JLabel textLength = new JLabel(rb.getString("Length"));
	JLabel textTrain = new JLabel(rb.getString("TrainLocation"));
	JLabel textLoc = new JLabel(rb.getString("Ops"));
	JLabel textType = new JLabel(rb.getString("Types"));
	JLabel textOptional = new JLabel("-------------------------------- Optional ------------------------------------");
	JLabel textComment = new JLabel(rb.getString("Comment"));

	// major buttons
	JButton clearButton = new JButton(rb.getString("Clear"));
	JButton setButton = new JButton(rb.getString("Select"));
	JButton saveLocationButton = new JButton(rb.getString("SaveLocation"));
	JButton deleteLocationButton = new JButton(rb.getString("DeleteLocation"));
	JButton addLocationButton = new JButton(rb.getString("AddLocation"));
	JButton addYardButton = new JButton(rb.getString("AddYard"));
	JButton addSidingButton = new JButton(rb.getString("AddSiding"));
	JButton addInterchangeButton = new JButton(rb.getString("AddInterchange"));
	JButton addStagingButton = new JButton(rb.getString("AddStaging"));
	
	// check boxes
	JCheckBox checkBox;
	JCheckBox northCheckBox = new JCheckBox(rb.getString("North"));
	JCheckBox southCheckBox = new JCheckBox(rb.getString("South"));
	JCheckBox eastCheckBox = new JCheckBox(rb.getString("East"));
	JCheckBox westCheckBox = new JCheckBox(rb.getString("West"));
	
	// radio buttons
    JRadioButton stageRadioButton = new JRadioButton(rb.getString("Staging"));
    JRadioButton interchangeRadioButton = new JRadioButton(rb.getString("Interchange"));
    JRadioButton yardRadioButton = new JRadioButton(rb.getString("Yards"));
    JRadioButton sidingRadioButton = new JRadioButton(rb.getString("Sidings"));
        
	// text field
	JTextField locationNameTextField = new JTextField(20);
	JTextField commentTextField = new JTextField(35);

	// for padding out panel
	JLabel space1 = new JLabel("     ");
	JLabel space2 = new JLabel("     ");
	JLabel space3 = new JLabel("     ");
	
	// combo boxes

	public static final String NAME = rb.getString("Name");
	public static final int MAX_NAME_LENGTH = 25;
	public static final String LENGTH = rb.getString("Length");
	public static final String DISPOSE = "dispose" ;

	public LocationEditFrame() {
		super();
	}

	public void initComponents(Location location) {
		_location = location;

		// load managers
		manager = LocationManager.instance();
		managerXml = LocationManagerXml.instance();
		
	   	// Set up the jtable in a Scroll Pane..
    	typePane = new JScrollPane(panelCheckBoxes);
    	typePane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    	//typePane.setMinimumSize(new Dimension (typePane.getWidth(), 100));
    	
    	yardPane = new JScrollPane(yardTable);
    	yardPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        	
    	sidingPane = new JScrollPane(sidingTable);
    	sidingPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
 	    
    	interchangePane = new JScrollPane(interchangeTable);
    	interchangePane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
 
    	stagingPane = new JScrollPane(stagingTable);
    	stagingPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
 		
		if (_location != null){
			enableButtons(true);
			locationNameTextField.setText(_location.getName());
			commentTextField.setText(_location.getComment());
	      	yardModel.initTable(yardTable, location);
	      	sidingModel.initTable(sidingTable, location);
	      	interchangeModel.initTable(interchangeTable, location);
	      	stagingModel.initTable(stagingTable, location);
			if (_location.getLocationOps() == Location.NORMAL){
				if (sidingModel.getRowCount()>0)
					sidingRadioButton.setSelected(true);
				else if (yardModel.getRowCount()>0)
					yardRadioButton.setSelected(true);
				else if (interchangeModel.getRowCount()>0)
					interchangeRadioButton.setSelected(true);
				else
					sidingRadioButton.setSelected(true);
			}else{
				stageRadioButton.setSelected(true);
			}
			setTrainDirectionBoxes();
		} else {
			enableButtons(false);
			sidingRadioButton.setSelected(true);
		}
		
		setVisibleLocations();
	
	    getContentPane().setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));

	    //      Set up the panels
    	JPanel p1 = new JPanel();
    	p1.setLayout(new GridBagLayout());
				
		// Layout the panel by rows
		// row 1
		addItem(p1, textName, 0, 1);
		addItemWidth(p1, locationNameTextField, 3, 1, 1);

		// row 2
    	directionPanel.setLayout(new GridBagLayout());
		addItem(directionPanel, textTrain, 0, 1);
		addItemLeft(directionPanel, northCheckBox, 1, 1);
		addItemLeft(directionPanel, southCheckBox, 2, 1);
		addItemLeft(directionPanel, eastCheckBox, 3, 1);
		addItemLeft(directionPanel, westCheckBox, 4, 1);
		Border border = BorderFactory.createEtchedBorder();
		directionPanel.setBorder(border);
		// row 3
		
		// row 4

		// row 5
	   	panelCheckBoxes.setLayout(new GridBagLayout());
		updateCheckboxes();

		// row 8
    	JPanel p3 = new JPanel();
    	p3.setLayout(new GridBagLayout());
    	int y=0;
		
		// row 9
		JPanel p = new JPanel();
		ButtonGroup opsGroup = new ButtonGroup();
		opsGroup.add(sidingRadioButton);
		opsGroup.add(yardRadioButton);
		opsGroup.add(interchangeRadioButton);
		opsGroup.add(stageRadioButton);
		p.add(textLoc);
		p.add(sidingRadioButton);
		p.add(yardRadioButton);
		p.add(interchangeRadioButton);
		p.add(stageRadioButton);
		addItemWidth(p3, p, 3, 0, ++y);
		
    	JPanel p4 = new JPanel();
    	p4.setLayout(new GridBagLayout());
		
		// row 10
		addItem (p4, space1, 0, ++y);
    	
		// row 11
		addItem(p4, textComment, 0, ++y);
		addItemWidth(p4, commentTextField, 3, 1, y);
				
		// row 12
		addItem(p4, space2, 0, ++y);
		// row 13
		addItem(p4, deleteLocationButton, 0, ++y);
		addItem(p4, addLocationButton, 1, y);
		addItem(p4, saveLocationButton, 3, y);
		
		getContentPane().add(p1);
		getContentPane().add(directionPanel);
		getContentPane().add(typePane);
		getContentPane().add(p3);
       	getContentPane().add(yardPane);
       	getContentPane().add(addYardButton);
       	getContentPane().add(sidingPane);
       	getContentPane().add(addSidingButton);
       	getContentPane().add(interchangePane);
       	getContentPane().add(addInterchangeButton);
       	getContentPane().add(stagingPane);
       	getContentPane().add(addStagingButton);
       	getContentPane().add(p4);
		
		// setup buttons
		addButtonAction(setButton);
		addButtonAction(clearButton);
		addButtonAction(deleteLocationButton);
		addButtonAction(addLocationButton);
		addButtonAction(saveLocationButton);
		addButtonAction(addYardButton);
		addButtonAction(addSidingButton);
		addButtonAction(addInterchangeButton);
		addButtonAction(addStagingButton);
		
		addRadioButtonAction(sidingRadioButton);
		addRadioButtonAction(yardRadioButton);
		addRadioButtonAction(interchangeRadioButton);
		addRadioButtonAction(stageRadioButton);
		
		addCheckBoxTrainAction(northCheckBox);
		addCheckBoxTrainAction(southCheckBox);
		addCheckBoxTrainAction(eastCheckBox);
		addCheckBoxTrainAction(westCheckBox);

		// add property listeners
		CarTypes.instance().addPropertyChangeListener(this);
		EngineTypes.instance().addPropertyChangeListener(this);
       	

		// build menu
//		JMenuBar menuBar = new JMenuBar();
//		JMenu toolMenu = new JMenu("Tools");
//		menuBar.add(toolMenu);
//		setJMenuBar(menuBar);
		addHelpMenu("package.jmri.jmrit.operations.Operations_Locations", true);

		//	 get notified if combo box gets modified
		
		// set frame size and location for display
		pack();
		if (manager.getLocationEditFrameSize()!= null){
			setSize(manager.getLocationEditFrameSize());
		} else {
			if((getWidth()<700)) setSize(700, getHeight());
			setSize(getWidth(), 700);
		}
		if (manager.getLocationEditFramePosition()!= null){
			setLocation(manager.getLocationEditFramePosition());
		}
		setVisible(true);
	}
	
	YardEditFrame yef = null;
	SidingEditFrame sef = null;
	InterchangeEditFrame ief = null;
	StagingEditFrame stef = null;
	
	// Save, Delete, Add 
	public void buttonActionPerformed(java.awt.event.ActionEvent ae) {
		if (ae.getSource() == addYardButton){
			yef = new YardEditFrame();
			yef.initComponents(_location, null);
			yef.setTitle(rb.getString("AddYard"));
		}
		if (ae.getSource() == addSidingButton){
			sef = new SidingEditFrame();
			sef.initComponents(_location, null);
			sef.setTitle(rb.getString("AddSiding"));
		}
		if (ae.getSource() == addInterchangeButton){
			ief = new InterchangeEditFrame();
			ief.initComponents(_location, null);
			ief.setTitle(rb.getString("AddInterchange"));
		}
		if (ae.getSource() == addStagingButton){
			stef = new StagingEditFrame();
			stef.initComponents(_location, null);
			stef.setTitle(rb.getString("AddStaging"));
		}

		if (ae.getSource() == saveLocationButton){
			log.debug("location save button actived");
			Location l = manager.getLocationByName(locationNameTextField.getText());
			if (_location == null && l == null){
				saveNewLocation();
			} else {
				if (l != null && l != _location){
					reportLocationExists(rb.getString("save"));
					return;
				}
				saveLocation();
			}
		}
		if (ae.getSource() == deleteLocationButton){
			log.debug("location delete button actived");
			Location l = manager.getLocationByName(locationNameTextField.getText());
			if (l == null)
				return;
			int rs = l.getNumberRS();
			if (rs > 0){
				if (JOptionPane.showConfirmDialog(this,
						MessageFormat.format(rb.getString("ThereAreCars"),new Object[]{Integer.toString(rs)}), rb.getString("deletelocation?"),
						JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION){
					return;
				}
			}
			
			manager.deregister(l);
			_location = null;
			selectCheckboxes(false);
			enableCheckboxes(false);
			enableButtons(false);
			// save location file
			managerXml.writeOperationsLocationFile();
			// save car file in case location had cars
			CarManagerXml.instance().writeOperationsCarFile();
		}
		if (ae.getSource() == addLocationButton){
			Location l = manager.getLocationByName(locationNameTextField.getText());
			if (l != null){
				reportLocationExists(rb.getString("add"));
				return;
			}
			saveNewLocation();
		}
		if (ae.getSource() == setButton){
			selectCheckboxes(true);
		}
		if (ae.getSource() == clearButton){
			selectCheckboxes(false);
		}
	}
	
	private void saveNewLocation(){
		if (!checkName(rb.getString("add")))
			return;
		Location location = manager.newLocation(locationNameTextField.getText());
		yardModel.initTable(yardTable, location);
      	sidingModel.initTable(sidingTable, location);
      	interchangeModel.initTable(interchangeTable, location);
      	stagingModel.initTable(stagingTable, location);
		_location = location;
		// enable checkboxes
		selectCheckboxes(true);
		enableCheckboxes(true);
		enableButtons(true);
		setTrainDirectionBoxes();
		saveLocation();
	}
	
	private void saveLocation (){
		if (!checkName(rb.getString("save")))
			return;
		_location.setName(locationNameTextField.getText());
		_location.setComment(commentTextField.getText());

		if (sidingRadioButton.isSelected() || yardRadioButton.isSelected() || interchangeRadioButton.isSelected()){
			_location.setLocationOps(Location.NORMAL);
		}
		if (stageRadioButton.isSelected()){
			_location.setLocationOps(Location.STAGING);
		}
		// save frame size and position
		manager.setLocationEditFrame(this);
		// save location file
		managerXml.writeOperationsLocationFile();
		// save car file in case location name changed
		CarManagerXml.instance().writeOperationsCarFile();
		// save route file in case location name changed
		RouteManagerXml.writeOperationsRouteFile();
	}
	

	/**
	 * 
	 * @return true if name is less than 26 characters
	 */
	private boolean checkName(String s){
		if (locationNameTextField.getText().trim().equals(""))
			return false;
		if (locationNameTextField.getText().length() > MAX_NAME_LENGTH){
			log.error("Location name must be less than "+ Integer.toString(MAX_NAME_LENGTH+1) +" characters");
			JOptionPane.showMessageDialog(this,
					MessageFormat.format(rb.getString("LocationNameLengthMax"),new Object[]{Integer.toString(MAX_NAME_LENGTH+1)}),
					MessageFormat.format(rb.getString("CanNotLocation"),new Object[]{s }),
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	private void reportLocationExists(String s){
		log.info("Can not " + s + ", location already exists");
		JOptionPane.showMessageDialog(this,
				rb.getString("LocationAlreadyExists"), MessageFormat.format(rb.getString("CanNotLocation"),new Object[]{s }),
				JOptionPane.ERROR_MESSAGE);
	}
	
	private void enableButtons(boolean enabled){
		northCheckBox.setEnabled(enabled);
		southCheckBox.setEnabled(enabled);
		eastCheckBox.setEnabled(enabled);
		westCheckBox.setEnabled(enabled);
		clearButton.setEnabled(enabled);
		setButton.setEnabled(enabled);
		addYardButton.setEnabled(enabled);
		addSidingButton.setEnabled(enabled);
		addInterchangeButton.setEnabled(enabled);
		addStagingButton.setEnabled(enabled);
		saveLocationButton.setEnabled(enabled);
		deleteLocationButton.setEnabled(enabled);
		// the inverse!
		addLocationButton.setEnabled(!enabled);
		// enable radio buttons
		sidingRadioButton.setEnabled(enabled);
		yardRadioButton.setEnabled(enabled);
		interchangeRadioButton.setEnabled(enabled);
		stageRadioButton.setEnabled(enabled);
		//
		yardTable.setEnabled(enabled);
	}
	
	public void radioButtonActionPerformed(java.awt.event.ActionEvent ae) {
		setVisibleLocations();
	}
	
	private void setVisibleLocations(){
		setEnabledLocations();
		interchangePane.setVisible(interchangeRadioButton.isSelected());
		addInterchangeButton.setVisible(interchangeRadioButton.isSelected());
		stagingPane.setVisible(stageRadioButton.isSelected());
		addStagingButton.setVisible(stageRadioButton.isSelected());
		yardPane.setVisible(yardRadioButton.isSelected());
		addYardButton.setVisible(yardRadioButton.isSelected());
		sidingPane.setVisible(sidingRadioButton.isSelected());
		addSidingButton.setVisible(sidingRadioButton.isSelected());
	}
	
	private void setEnabledLocations(){
		log.debug("set radio button");
		if (sidingModel.getRowCount()>0 || yardModel.getRowCount()>0 || interchangeModel.getRowCount()>0){
			stageRadioButton.setEnabled(false);
			if(stageRadioButton.isSelected())
				sidingRadioButton.setSelected(true);
		}
		else if (stagingModel.getRowCount()>0){
			stageRadioButton.setSelected(true);
			sidingRadioButton.setEnabled(false);
			yardRadioButton.setEnabled(false);
			interchangeRadioButton.setEnabled(false);
		} 
		else {
			sidingRadioButton.setEnabled(true);
			yardRadioButton.setEnabled(true);
			interchangeRadioButton.setEnabled(true);
			stageRadioButton.setEnabled(true);
		}
	}
	
	private void enableCheckboxes(boolean enable){
		for (int i=0; i < checkBoxes.size(); i++){
			checkBox = checkBoxes.get(i);
			checkBox.setEnabled(enable);
		}
	}
	
	private void selectCheckboxes(boolean enable){
		for (int i=0; i < checkBoxes.size(); i++){
			checkBox = checkBoxes.get(i);
			checkBox.setSelected(enable);
			if(_location != null){
				if (enable)
					_location.addTypeName(checkBox.getText());
				else
					_location.deleteTypeName(checkBox.getText());
			}
		}
	}
	
	private void updateCheckboxes(){
		 x = 0;
		 y = 0;
		checkBoxes.clear();
		panelCheckBoxes.removeAll();
		addItemWidth(panelCheckBoxes, textType, 3, 1, y++);
		loadTypes(CarTypes.instance().getNames());
		loadTypes(EngineTypes.instance().getNames());
    	addItem(panelCheckBoxes, clearButton, 1, ++y);
    	addItem(panelCheckBoxes, setButton, 4, y);
		Border border = BorderFactory.createEtchedBorder();
		panelCheckBoxes.setBorder(border);
		panelCheckBoxes.revalidate();
		repaint();
	}
	
	int x = 0;
	int y = 0;	// vertical position in panel
	private void loadTypes(String[] types){
		for (int i =0; i<types.length; i++){
			JCheckBox checkBox = new JCheckBox();
			checkBoxes.add(checkBox);
			checkBox.setText(types[i]);
			addCheckBoxAction(checkBox);
			addItemLeft(panelCheckBoxes, checkBox, x++, y);
			if (_location != null){
				if(_location.acceptsTypeName(types[i]))
					checkBox.setSelected(true);
			} else {
				checkBox.setEnabled(false);
			}
			// seven types per row
			if (x > 6){
				y++;
				x = 0;
			}
		}
	}
	
	public void checkBoxActionPerformed(java.awt.event.ActionEvent ae) {
		JCheckBox b =  (JCheckBox)ae.getSource();
		log.debug("checkbox change "+ b.getText());
		if (_location == null)
			return;
		if (b.isSelected()){
			_location.addTypeName(b.getText());
		}else{
			_location.deleteTypeName(b.getText());
		}
	}
	
	
	private void addCheckBoxTrainAction(JCheckBox b) {
		b.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				checkBoxActionTrainPerformed(e);
			}
		});
	}
	
	private void checkBoxActionTrainPerformed(java.awt.event.ActionEvent ae) {
		// save train directions serviced by this location
		if (_location == null)
			return;
		int direction = 0;
		if (northCheckBox.isSelected()){
			direction += Location.NORTH;
		}
		if (southCheckBox.isSelected()){
			direction += Location.SOUTH;
		}
		if (eastCheckBox.isSelected()){
			direction += Location.EAST;
		}
		if (westCheckBox.isSelected()){
			direction += Location.WEST;
		}
		_location.setTrainDirections(direction);
		
	}
	
	private void setTrainDirectionBoxes(){
		northCheckBox.setVisible((Setup.getTrainDirection() & Setup.NORTH)>0);
		southCheckBox.setVisible((Setup.getTrainDirection() & Setup.SOUTH)>0);
		eastCheckBox.setVisible((Setup.getTrainDirection() & Setup.EAST)>0);
		westCheckBox.setVisible((Setup.getTrainDirection() & Setup.WEST)>0);
		
		northCheckBox.setSelected((_location.getTrainDirections() & Location.NORTH)>0);
		southCheckBox.setSelected((_location.getTrainDirections() & Location.SOUTH)>0);
		eastCheckBox.setSelected((_location.getTrainDirections() & Location.EAST)>0);
		westCheckBox.setSelected((_location.getTrainDirections() & Location.WEST)>0);
	}
	
	public void dispose() {
		CarTypes.instance().removePropertyChangeListener(this);
		EngineTypes.instance().removePropertyChangeListener(this);
		yardModel.dispose();
		sidingModel.dispose();
		interchangeModel.dispose();
		stagingModel.dispose();
		super.dispose();
	}
	
 	public void propertyChange(java.beans.PropertyChangeEvent e) {
		if (log.isDebugEnabled()) 
			log.debug("Property change " +e.getPropertyName()+ " old: "+e.getOldValue()+ " new: "+e.getNewValue());
		if (e.getPropertyName().equals(CarTypes.CARTYPES_LENGTH_CHANGED_PROPERTY) ||
				e.getPropertyName().equals(EngineTypes.ENGINETYPES_LENGTH_CHANGED_PROPERTY)){
			updateCheckboxes();
		}
	}

	static org.apache.log4j.Logger log = org.apache.log4j.Logger
	.getLogger(LocationEditFrame.class.getName());
}
