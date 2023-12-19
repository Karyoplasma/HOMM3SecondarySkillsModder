package models;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import core.Hero;
import core.SkillChange;

public class ChangesTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -1753585994126747934L;
	private final String[] columnNames = { "Hero", "Original", "Changed"};
	private final List<Hero> changes;
	
	public ChangesTableModel(List<Hero> changes) {
		this.changes = changes;
	}

	@Override
	public int getRowCount() {
		return changes.size();
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
		Hero hero = changes.get(rowIndex);
		SkillChange change = hero.getChange();		
		if (change == null) {
			return "Nothing changed";
		}
		
		switch (columnIndex) {
		case 0:
			return hero.getName();
		case 1:
			return String.format("%s, %s", hero.getSecondary1(), hero.getSecondary2());
		case 2:
			return String.format("%s, %s", change.getChanged1(), change.getChanged2());
		default:
			return "-";
		}
	}
}
