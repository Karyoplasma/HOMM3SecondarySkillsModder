package actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import gui.Heroes3HeroEditor;

public class WriteButtonAction extends AbstractAction {
	
	private static final long serialVersionUID = 715415617407834660L;
	private Heroes3HeroEditor gui;
	
	public WriteButtonAction(Heroes3HeroEditor gui) {
		putValue(Action.NAME, "Write");
		this.gui = gui;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
//		for (Map.Entry<Integer, String> entry : gui.getChanges().entrySet()) {
//			Hero hero = gui.getHeroes().get(entry.getKey());
//			hero.writeHero();
//		}
//		JOptionPane.showMessageDialog(null, String.format("%d changes have been written to the executable.", gui.getChanges().size()), "Information", JOptionPane.INFORMATION_MESSAGE);
		gui.getBtnUnlock().setText("Unlock");
		gui.getBtnWriteFile().setEnabled(false);

	}

}
