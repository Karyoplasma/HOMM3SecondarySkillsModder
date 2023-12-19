package actions;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import core.Hero;
import gui.Heroes3HeroEditor;

public class ChangesTableMouseAdapter extends MouseAdapter {

	private Heroes3HeroEditor gui;

	public ChangesTableMouseAdapter(Heroes3HeroEditor gui) {
		this.gui = gui;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			int row = gui.getTableChanges().getSelectedRow();
			int column = gui.getTableChanges().getSelectedColumn();
			if (row < 0 || column < 0) {
				return;
			}
			Object cellValue = gui.getTableChanges().getValueAt(row, column);

			Hero hero = gui.getHeroes().get(cellValue.toString());
			if (hero == null) {
				return;
			}
			gui.getComboBoxHero().setSelectedItem(hero);
			gui.getComboBoxHero().repaint();
		}
	}
}
