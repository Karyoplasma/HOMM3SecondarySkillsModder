package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import actions.OpenExecutableButtonAction;
import actions.SaveButtonAction;
import actions.UnlockButtonAction;
import actions.LoadButtonAction;
import actions.WriteButtonAction;
import actions.UpdateHeroButtonAction;
import core.Hero;
import core.SkillChange;
import enums.HeroTrait;
import models.ChangesTableModel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTable;
import javax.swing.JCheckBox;

public class Heroes3HeroEditor extends Observable {

	private JFrame frmHommSecondarySkill;
	private Map<String, Hero> heroes;
	private String executableDirectory;
	private JButton btnUpdateHero;
	private JButton btnWriteFile;
	private JScrollPane scrollPaneChanges;
	private JButton btnUnlock;
	private HashMap<String, SkillChange> changes;
	private JComboBox<HeroTrait> comboBoxSkill1, comboBoxSkill2;
	private JComboBox<String> comboBoxSkill1lvl, comboBoxSkill2lvl;
	private JComboBox<Hero> comboBoxHero;
	private JButton btnSave;
	private JButton btnLoad;
	private JButton btnOpenExectutable;
	private JScrollPane scrollPaneHeroInfo;
	private JTable tableChanges;
	private JTable tableHeroInfo;
	private JCheckBox chckbxSavePath;
	private ChangesTableModel changesTableModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Heroes3HeroEditor window = new Heroes3HeroEditor();
					window.frmHommSecondarySkill.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Heroes3HeroEditor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		this.changes = new HashMap<String, SkillChange>();
		this.executableDirectory = this.readExecutableDirectory();
		SkillBoxListener skillBoxListener = new SkillBoxListener(this);
		
		frmHommSecondarySkill = new JFrame();
		frmHommSecondarySkill.setTitle("HOMM3 Secondary Skill Modifier");
		frmHommSecondarySkill.setBounds(100, 100, 771, 507);
		frmHommSecondarySkill.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHommSecondarySkill.getContentPane().setLayout(new MigLayout("", "[][][grow][][]", "[][:100px:200px][][][][][][][][grow][]"));
		
		btnOpenExectutable = new JButton(new OpenExecutableButtonAction(this));
		btnOpenExectutable.setFont(new Font("Tahoma", Font.BOLD, 14));
		frmHommSecondarySkill.getContentPane().add(btnOpenExectutable, "cell 0 0,grow");
		
		btnUnlock = new JButton(new UnlockButtonAction(this));
		btnUnlock.setFont(new Font("Tahoma", Font.BOLD, 14));
		frmHommSecondarySkill.getContentPane().add(btnUnlock, "cell 1 10,grow");
				
		btnLoad = new JButton(new LoadButtonAction(this));
		btnLoad.setFont(new Font("Tahoma", Font.BOLD, 14));
		frmHommSecondarySkill.getContentPane().add(btnLoad, "cell 3 10,grow");
		
		btnUpdateHero = new JButton(new UpdateHeroButtonAction(this));
		btnUpdateHero.setFont(new Font("Tahoma", Font.BOLD, 16));
		frmHommSecondarySkill.getContentPane().add(btnUpdateHero, "cell 0 8 2 1,grow");
		
		btnSave = new JButton(new SaveButtonAction(this));
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 14));
		frmHommSecondarySkill.getContentPane().add(btnSave, "cell 4 10,grow");
		
		btnWriteFile = new JButton(new WriteButtonAction(this));
		btnWriteFile.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnWriteFile.setEnabled(false);
		frmHommSecondarySkill.getContentPane().add(btnWriteFile, "cell 0 10,grow");
		
		chckbxSavePath = new JCheckBox("Save path");
		chckbxSavePath.setFont(new Font("Tahoma", Font.BOLD, 14));
		chckbxSavePath.setSelected(true);
		frmHommSecondarySkill.getContentPane().add(chckbxSavePath, "cell 1 0");
		
		comboBoxHero = new JComboBox<Hero>();
		comboBoxHero.addItemListener(new HeroBoxListener(this));
		comboBoxHero.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frmHommSecondarySkill.getContentPane().add(comboBoxHero, "cell 0 1,growx,aligny center");

		JLabel lblSkill = new JLabel("Skill 1:");
		lblSkill.setFont(new Font("Tahoma", Font.BOLD, 14));
		frmHommSecondarySkill.getContentPane().add(lblSkill, "cell 0 2,grow");
		
		comboBoxSkill1 = new JComboBox<HeroTrait>();
		comboBoxSkill1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBoxSkill1.addItemListener(skillBoxListener);
		comboBoxSkill1.setModel(new DefaultComboBoxModel<HeroTrait>(HeroTrait.values()));
		frmHommSecondarySkill.getContentPane().add(comboBoxSkill1, "cell 1 3,grow");
		
		comboBoxSkill1lvl = new JComboBox<String>();
		comboBoxSkill1lvl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBoxSkill1lvl.setModel(new DefaultComboBoxModel<String>(new String[] {"Basic", "Advanced", "Expert"}));
		frmHommSecondarySkill.getContentPane().add(comboBoxSkill1lvl, "cell 0 3,grow");

		JLabel lblSkill_1 = new JLabel("Skill 2:");
		lblSkill_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		frmHommSecondarySkill.getContentPane().add(lblSkill_1, "cell 0 5,grow");
		
		comboBoxSkill2 = new JComboBox<HeroTrait>();
		comboBoxSkill2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBoxSkill2.addItemListener(skillBoxListener);
		comboBoxSkill2.setModel(new DefaultComboBoxModel<HeroTrait>(HeroTrait.values()));	
		frmHommSecondarySkill.getContentPane().add(comboBoxSkill2, "cell 1 6,grow");
		
		comboBoxSkill2lvl = new JComboBox<String>();
		comboBoxSkill2lvl.setModel(new DefaultComboBoxModel<String>(new String[] {"Basic", "Advanced", "Expert"}));
		comboBoxSkill2lvl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frmHommSecondarySkill.getContentPane().add(comboBoxSkill2lvl, "cell 0 6,grow");
		
		scrollPaneHeroInfo = new JScrollPane();
		frmHommSecondarySkill.getContentPane().add(scrollPaneHeroInfo, "cell 2 1 3 1,grow");
		
		tableHeroInfo = new JTable();
		tableHeroInfo.setFont(new Font("Monospaced", Font.PLAIN, 13));
		tableHeroInfo.setFillsViewportHeight(true);
		tableHeroInfo.setRowSelectionAllowed(false);
		tableHeroInfo.setShowVerticalLines(false);
		tableHeroInfo.setShowHorizontalLines(false);
		tableHeroInfo.setShowGrid(false);
		scrollPaneHeroInfo.setViewportView(tableHeroInfo);
		
		JLabel lblHeroInfo = new JLabel("Hero Info:");
		lblHeroInfo.setFont(new Font("Tahoma", Font.BOLD, 14));
		frmHommSecondarySkill.getContentPane().add(lblHeroInfo, "cell 1 1,alignx center,growy");
		
		scrollPaneChanges = new JScrollPane();
		frmHommSecondarySkill.getContentPane().add(scrollPaneChanges, "cell 2 3 3 7,grow");
		
		changesTableModel = new ChangesTableModel(this);
		addObserver(changesTableModel);
		tableChanges = new JTable();
		tableChanges.setModel(changesTableModel);
		tableChanges.setRowSelectionAllowed(false);
		tableChanges.setShowGrid(false);
		tableChanges.setShowVerticalLines(false);
		tableChanges.setShowHorizontalLines(false);
		tableChanges.setFillsViewportHeight(true);
		tableChanges.setFont(new Font("Monospaced", Font.PLAIN, 14));
		scrollPaneChanges.setViewportView(tableChanges);
		
			
	}

	private String readExecutableDirectory() {
		try (BufferedReader reader = new BufferedReader(new FileReader("resources/directoryPath.txt"))) {
            return reader.readLine();
        } catch (IOException e) {
        	return "";
		}
	}
	
	public JButton getBtnUnlock() {
		return btnUnlock;
	}

	public JButton getBtnWriteFile() {
		return btnWriteFile;
	}

	public String getExecutableDirectory() {
		return executableDirectory;
	}

	public void setExecutableDirectory(String executableDirectory) {
		this.executableDirectory = executableDirectory;
	}

	public JFrame getFrame() {
		return this.frmHommSecondarySkill;
	}

	public Map<String, Hero> getHeroes() {
		return heroes;
	}

	public HashMap<String, SkillChange> getChanges() {
		return changes;
	}

	public JComboBox<Hero> getComboBoxHero() {
		return comboBoxHero;
	}

	public JComboBox<String> getComboBoxSkill1lvl() {
		return comboBoxSkill1lvl;
	}

	public JComboBox<HeroTrait> getComboBoxSkill1() {
		return comboBoxSkill1;
	}

	public JComboBox<String> getComboBoxSkill2lvl() {
		return comboBoxSkill2lvl;
	}

	public JComboBox<HeroTrait> getComboBoxSkill2() {
		return comboBoxSkill2;
	}
	
	public boolean savePath() {
		return chckbxSavePath.isSelected();
	}
	
	public void setHeroes(Map<String, Hero> heroes) {
		this.heroes = heroes;
		setChanged();
		notifyObservers(heroes);
	}

	public void setChanges(HashMap<String, SkillChange> changes) {
		this.changes = changes;
		setChanged();
		notifyObservers(changes);
	}
	
	public void putChanges(String string, SkillChange skillChange) {
		this.changes.put(string, skillChange);
		setChanged();
		notifyObservers(skillChange);
	}
}
