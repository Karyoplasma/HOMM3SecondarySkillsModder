package actions;

import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import core.Hero;
import gui.Heroes3HeroEditor;

public class SaveButtonAction extends AbstractAction {

	private static final long serialVersionUID = 2349318213613428462L;
	private Heroes3HeroEditor gui;

	public SaveButtonAction(Heroes3HeroEditor gui) {
		putValue(Action.NAME, "Save");
		this.gui = gui;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		List<Hero> changes = gui.getChanges();
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String fileName = "resources/" + timestamp + ".mod";
		File file = new File(fileName);

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			// Write each change to the file
			for (Hero hero : changes) {
				String heroString = String.format("%s;%s", hero.getName(), hero.getChange().toString());
				writer.write(heroString);
				writer.newLine();
			}
			JOptionPane.showMessageDialog(gui.getFrame(),
					String.format("%d changes written to file!\n%s", changes.size(), file.getAbsolutePath()), "Success",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException ex) {
			// Handle IO exception
			JOptionPane.showMessageDialog(gui.getFrame(),
					"Could not write changes to file. Try again.\n" + ex.getMessage(), "Exception",
					JOptionPane.ERROR_MESSAGE);

			System.err.println("Error writing changes to file: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}
