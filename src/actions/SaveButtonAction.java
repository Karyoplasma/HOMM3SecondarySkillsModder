package actions;

import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import core.SkillChange;
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
		Map<String, SkillChange> changes = gui.getChanges();
		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("resources/changes.txt"),
				StandardCharsets.UTF_8)) {
			for (SkillChange value : changes.values()) {
				writer.write(value.toString());
				writer.newLine();
			}
			JOptionPane.showMessageDialog(gui.getFrame(), String.format("%d changes saved.", changes.size()),
					"Information", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(gui.getFrame(), "Could not write the file, please retry.", "Error",
					JOptionPane.WARNING_MESSAGE);
			ex.printStackTrace();
		}
	}
}
