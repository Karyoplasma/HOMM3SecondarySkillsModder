package models;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import core.SkillChange;
import gui.Heroes3HeroEditor;

public class ChangesTableModel extends AbstractTableModel implements Observer {
	private final String[] columnNames = { "Hero", "Skill 1", "Skill 2", "Change 1", "Change 2" };
	final Heroes3HeroEditor gui;
	
	public ChangesTableModel(Heroes3HeroEditor gui) {
		this.gui = gui;
	}

	@Override
	public int getRowCount() {
		return gui.getChanges().size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		//TODO implement this 
		return null;
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("ChangesTableModel got notified");
	}

}
