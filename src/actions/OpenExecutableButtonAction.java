package actions;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import core.H3ExecutableReader;
import core.Hero;
import gui.Heroes3HeroEditor;

public class OpenExecutableButtonAction extends AbstractAction {

	private static final long serialVersionUID = -7036375200450316463L;
	private Heroes3HeroEditor gui;

	public OpenExecutableButtonAction(Heroes3HeroEditor gui) {
		putValue(Action.NAME, "Open executable");
		this.gui = gui;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Path executable = this.getH3Executable();
		if (executable != null) {
			this.readExecutable(executable);
		}
	}

	private Path getH3Executable() {
		File executable;
		boolean saveDirectoyPreference = false;
		if (gui.getExecutableDirectory().isEmpty()) {
			executable = new File(System.getProperty("user.dir"));
			saveDirectoyPreference = true;
		} else {
			executable = new File(gui.getExecutableDirectory());
		}

		JFileChooser fileChooser = new JFileChooser("Select Heroes 3 or HotA executable");
		fileChooser.setFileFilter(new FileNameExtensionFilter("Executables", "exe"));
		fileChooser.setCurrentDirectory(executable.isDirectory() ? executable : executable.getParentFile());

		int ret = fileChooser.showOpenDialog(null);
		if (ret == (JFileChooser.APPROVE_OPTION)) {
			executable = fileChooser.getSelectedFile();
			if (!(executable.getName().toLowerCase().matches("h3hota(\\shd)?.exe")
					|| executable.getName().toLowerCase().matches("heroes3(\\shd)?.exe"))) {
				int option = JOptionPane.showConfirmDialog(null,
						"The selected file does not match the expected filename.\nDo you want to select a different file?",
						"Incorrect File", JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_OPTION) {
					return this.getH3Executable();
				} else {
					return null;
				}
			}
		} else {
			return null;
		}
		if (saveDirectoyPreference && gui.savePath()) {
			this.saveDirectoryPreference(executable.toPath());
		}

		return executable.toPath();

	}

	private void saveDirectoryPreference(Path path) {
		Path directory = path.getParent();
		String content = directory.toString();
		Path preferenceFilePath = Paths.get("resources/directoryPath.txt");
		if (!preferenceFilePath.getParent().toFile().exists()) {
			preferenceFilePath.getParent().toFile().mkdir();
		}
		try {
			Files.write(preferenceFilePath, content.getBytes(), StandardOpenOption.CREATE,
					StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void readExecutable(Path executable) {
		Map<String, Hero> heroes = new LinkedHashMap<String, Hero>();
		try {
			heroes = H3ExecutableReader.readHeroes(executable);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(gui.getFrame(),
					"Error reading heroes from the executable:\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}

		if (heroes == null) {
			JOptionPane.showMessageDialog(gui.getFrame(),
					"This should never happen. If you see this message, please create an issue on GitHub.\nThe heroes list is null.",
					"Fatal error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (!heroes.isEmpty()) {
			gui.setHeroes(heroes);
			gui.setExecutable(executable);
		}
	}
}
