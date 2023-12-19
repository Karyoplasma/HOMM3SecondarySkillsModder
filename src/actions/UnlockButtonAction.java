package actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import gui.Heroes3HeroEditor;

public class UnlockButtonAction extends AbstractAction {
	
	private static final long serialVersionUID = -6909528140663229842L;
	private Heroes3HeroEditor gui;
	
	public UnlockButtonAction(Heroes3HeroEditor gui) {
		putValue(Action.NAME, "Unlock");
		this.gui = gui;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (gui.getBtnUnlock().getText().equals("Unlock")) {
			gui.getBtnUnlock().setText("Lock");
			gui.getBtnWriteFile().setEnabled(true);
		} else {
			gui.getBtnUnlock().setText("Unlock");
			gui.getBtnWriteFile().setEnabled(false);
		}

	}

}
