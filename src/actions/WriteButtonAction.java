package actions;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import core.H3ExecutableWriter;
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
		Path executable = gui.getExecutable();
		if (!this.backupExecutable(executable)) {
			gui.getBtnUnlock().setText("Unlock");
			gui.getBtnWriteFile().setEnabled(false);
			JOptionPane.showMessageDialog(gui.getFrame(),
					"Could not write backup. Aborting...\nYour executable has not been changed.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		} else {
			try {
				if (H3ExecutableWriter.writeAllChanges(gui.getChanges(), executable)) {
					gui.getBtnUnlock().setText("Unlock");
					gui.getBtnWriteFile().setEnabled(false);
					JOptionPane.showMessageDialog(gui.getFrame(), "Changes written to the executable.", "Success",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(gui.getFrame(),
							"Something undetermined went wrong while patching the executable.\nPlease report this error on GitHub.",
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(gui.getFrame(),
						"IOException while patching the executable.\nPlease report this error on GitHub.\n"
								+ ex.getMessage(),
						"Error", JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
		}
	}

	private boolean backupExecutable(Path executablePath) {
		File executable = executablePath.toFile();
		if (!executable.exists()) {
			return false;
		}
		File parentDirectory = executable.getParentFile();
		File backupFolder = new File(parentDirectory, "backupSkillModder");
		if (!backupFolder.exists() && !backupFolder.mkdir()) {
			JOptionPane.showMessageDialog(gui.getFrame(),
					"Failed to create backup folder. Please create a folder within your save folder and name it \"backupSkillModder\".",
					"Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		File backupFile = new File(backupFolder, executable.getName() + "_" + Instant.now().getEpochSecond());
		try {
			Path destinationPath = backupFile.toPath();
			Files.copy(executablePath, destinationPath);
			return true;
		} catch (IOException e) {
			return false;
		}
	}

}
