package core;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class Heroes3HeroEditor implements ActionListener{

	private JFrame frmHotaSecondarySkill;
	private JTextField textField_speciality;
	private JTextField textField_gender;
	private JTextField textField_race;
	private JTextField textField_profession;
	private List<Hero> heroes;
	private RandomAccessFile h3executable;
	private JButton btnUpdateHero;
	private JButton btnWriteFile;
	private JScrollPane scrollPane;
	private JTextArea textArea_changes;
	private JButton btnUnlock;
	private HashMap<Integer,String> changes;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox_skill1lvl, comboBox_hero, comboBox_skill1, comboBox_skill2lvl, comboBox_skill2;
	private JButton btnSave;
	private JButton btnLoad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Heroes3HeroEditor window = new Heroes3HeroEditor();
					window.frmHotaSecondarySkill.setVisible(true);
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		
		frmHotaSecondarySkill = new JFrame();
		frmHotaSecondarySkill.setTitle("HotA Secondary Skill Modifier");
		frmHotaSecondarySkill.setBounds(100, 100, 771, 507);
		frmHotaSecondarySkill.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHotaSecondarySkill.getContentPane().setLayout(null);
		
		comboBox_hero = new JComboBox();
		comboBox_hero.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBox_hero.setBounds(10, 11, 188, 33);
		comboBox_hero.addActionListener(this);
		frmHotaSecondarySkill.getContentPane().add(comboBox_hero);
		
		JLabel lblSpeciality = new JLabel("Speciality: ");
		lblSpeciality.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblSpeciality.setBounds(226, 22, 52, 14);
		frmHotaSecondarySkill.getContentPane().add(lblSpeciality);
		
		textField_speciality = new JTextField();
		textField_speciality.setEditable(false);
		textField_speciality.setBounds(288, 19, 188, 20);
		frmHotaSecondarySkill.getContentPane().add(textField_speciality);
		textField_speciality.setColumns(10);
		
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblGender.setBounds(10, 78, 46, 14);
		frmHotaSecondarySkill.getContentPane().add(lblGender);
		
		textField_gender = new JTextField();
		textField_gender.setEditable(false);
		textField_gender.setBounds(66, 75, 86, 20);
		frmHotaSecondarySkill.getContentPane().add(textField_gender);
		textField_gender.setColumns(10);
		
		JLabel lblRace = new JLabel("Race: ");
		lblRace.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblRace.setBounds(226, 78, 31, 14);
		frmHotaSecondarySkill.getContentPane().add(lblRace);
		
		textField_race = new JTextField();
		textField_race.setEditable(false);
		textField_race.setBounds(288, 75, 114, 20);
		frmHotaSecondarySkill.getContentPane().add(textField_race);
		textField_race.setColumns(10);
		
		JLabel lblProfession = new JLabel("Profession: ");
		lblProfession.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblProfession.setBounds(454, 78, 57, 14);
		frmHotaSecondarySkill.getContentPane().add(lblProfession);
		
		textField_profession = new JTextField();
		textField_profession.setEditable(false);
		textField_profession.setBounds(521, 75, 224, 20);
		frmHotaSecondarySkill.getContentPane().add(textField_profession);
		textField_profession.setColumns(10);
		
		JLabel lblSkill = new JLabel("Skill 1:");
		lblSkill.setBounds(10, 127, 46, 14);
		frmHotaSecondarySkill.getContentPane().add(lblSkill);
		
		comboBox_skill1lvl = new JComboBox();
		comboBox_skill1lvl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_skill1lvl.setModel(new DefaultComboBoxModel(new String[] {"Basic", "Advanced", "Expert"}));
		comboBox_skill1lvl.setBounds(10, 152, 188, 33);
		frmHotaSecondarySkill.getContentPane().add(comboBox_skill1lvl);
		
		comboBox_skill1 = new JComboBox();
		comboBox_skill1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_skill1.setModel(new DefaultComboBoxModel(HeroTrait.values()));
		comboBox_skill1.setBounds(217, 152, 295, 33);
		frmHotaSecondarySkill.getContentPane().add(comboBox_skill1);
		
		JLabel lblSkill_1 = new JLabel("Skill 2:");
		lblSkill_1.setBounds(10, 214, 46, 14);
		frmHotaSecondarySkill.getContentPane().add(lblSkill_1);
		
		comboBox_skill2lvl = new JComboBox();
		comboBox_skill2lvl.setModel(new DefaultComboBoxModel(new String[] {"Basic", "Advanced", "Expert"}));
		comboBox_skill2lvl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_skill2lvl.setBounds(10, 239, 188, 33);
		frmHotaSecondarySkill.getContentPane().add(comboBox_skill2lvl);
		
		comboBox_skill2 = new JComboBox();
		comboBox_skill2.setModel(new DefaultComboBoxModel(HeroTrait.values()));
		comboBox_skill2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_skill2.setBounds(217, 239, 295, 33);
		frmHotaSecondarySkill.getContentPane().add(comboBox_skill2);
		
		btnWriteFile = new JButton("Write file");
		btnWriteFile.setBounds(10, 435, 89, 23);
		btnWriteFile.addActionListener(this);
		btnWriteFile.setEnabled(false);
		frmHotaSecondarySkill.getContentPane().add(btnWriteFile);
		
		getH3Executable();
		readHeroes(this.h3executable);
		
		comboBox_hero.setModel(new DefaultComboBoxModel<Object>(heroes.toArray()));
		comboBox_hero.setSelectedIndex(0);	
		
		btnUpdateHero = new JButton("Update Hero");
		btnUpdateHero.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUpdateHero.setBounds(66, 301, 380, 46);
		btnUpdateHero.addActionListener(this);
		frmHotaSecondarySkill.getContentPane().add(btnUpdateHero);
		
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(531, 116, 214, 342);
		frmHotaSecondarySkill.getContentPane().add(scrollPane);
		
		textArea_changes = new JTextArea();
		textArea_changes.setEditable(false);
		scrollPane.setViewportView(textArea_changes);
		
		btnUnlock = new JButton("Unlock");
		btnUnlock.setBounds(122, 435, 89, 23);
		btnUnlock.addActionListener(this);
		frmHotaSecondarySkill.getContentPane().add(btnUnlock);
		
		btnSave = new JButton("Save");
		btnSave.setBounds(521, 18, 89, 23);
		btnSave.addActionListener(this);
		frmHotaSecondarySkill.getContentPane().add(btnSave);
		
		btnLoad = new JButton("Load");
		btnLoad.setBounds(656, 18, 89, 23);
		btnLoad.addActionListener(this);
		frmHotaSecondarySkill.getContentPane().add(btnLoad);
		
		this.changes = new HashMap<Integer,String>();
		
	}

	private void getH3Executable() {
		File file = null;
		File workingDirectory = new File(System.getProperty("user.dir"));
		JFileChooser fileChooser = new JFileChooser("Select Heroes 3 or HotA executable");
		fileChooser.setCurrentDirectory(workingDirectory);
		int ret = fileChooser.showOpenDialog(null);
		if (ret == (JFileChooser.APPROVE_OPTION)) {
			file = fileChooser.getSelectedFile();
			if (!(file.getName().toLowerCase().matches("h3hota(\\shd)?.exe") || file.getName().toLowerCase().matches("heroes3(\\shd)?.exe"))) {
				getH3Executable();
				return;
			}
		}
		try {
			this.h3executable = new RandomAccessFile(file, "rw");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
	}

	private void readHeroes(RandomAccessFile h3executable) {
		this.heroes = new ArrayList<Hero>();
		try {
			File herolist = new File ("res/herolist.txt");
			BufferedReader reader = new BufferedReader(new FileReader(herolist));
			String in = reader.readLine();
			while ((in = reader.readLine()) != null) {
				String[] insplit = in.split(";");
				Hero hero = new Hero(h3executable, insplit[0].trim(), insplit[1], Long.decode(insplit[2].trim()));
				this.heroes.add(hero);
			}
			reader.close();
			
		} catch	(IOException e) {
			e.printStackTrace();
		}
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == comboBox_hero) {
			loadHero(comboBox_hero.getSelectedIndex());
		}
		if (e.getSource() == comboBox_skill1) {
			if (comboBox_skill1.getSelectedItem().equals(HeroTrait.None)) {
				comboBox_skill1lvl.setEnabled(false);
			} else {
				comboBox_skill1lvl.setEnabled(true);
			}
		}
		if (e.getSource() == comboBox_skill2) {
			if (comboBox_skill2.getSelectedItem().equals(HeroTrait.None)) {
				comboBox_skill2lvl.setEnabled(false);
			} else {
				comboBox_skill2lvl.setEnabled(true);
			}
		}
		if (e.getSource() == btnUpdateHero) {
			updateHero(comboBox_hero.getSelectedIndex());
		}
		
		if (e.getSource() == btnUnlock) {
			if (btnUnlock.getText().equals("Unlock")) {
				btnUnlock.setText("Lock");
				btnWriteFile.setEnabled(true);
			} else {
				btnUnlock.setText("Unlock");
				btnWriteFile.setEnabled(false);
			}
		}
		
		if (e.getSource() == btnWriteFile) {
			for (Map.Entry<Integer, String> entry : changes.entrySet()) {
				Hero hero = heroes.get(entry.getKey());
				hero.writeHero();
			}
			JOptionPane.showMessageDialog(null, String.format("%d changes have been written to file.", changes.size()), "Information", JOptionPane.INFORMATION_MESSAGE);
			btnUnlock.setText("Unlock");
			btnWriteFile.setEnabled(false);
		}
		if (e.getSource() == btnSave) {
			saveHeroConfiguration();
		}
		if (e.getSource() == btnLoad) {
			loadHeroConfiguration();
			applyHeroConfiguration();
		}
		
	}

	private void updateHero(int selectedIndex) {
		heroes.get(selectedIndex).secondary1.setTrait((HeroTrait) comboBox_skill1.getSelectedItem());
		if (comboBox_skill1lvl.isEnabled()) {
			heroes.get(selectedIndex).secondary1.setLevel((String) comboBox_skill1lvl.getSelectedItem());
		} else {
			heroes.get(selectedIndex).secondary1.setLevel(0x00000000);
		}
		heroes.get(selectedIndex).secondary2.setTrait((HeroTrait) comboBox_skill2.getSelectedItem());
		if (comboBox_skill2lvl.isEnabled()) {
			heroes.get(selectedIndex).secondary2.setLevel((String) comboBox_skill2lvl.getSelectedItem());
		} else {
			heroes.get(selectedIndex).secondary2.setLevel(0x00000000);
		}
		String changed = changes.put(selectedIndex, String.format("%d;%d;%d;%d", heroes.get(selectedIndex).secondary1.trait.id, heroes.get(selectedIndex).secondary1.lvl, heroes.get(selectedIndex).secondary2.trait.id, heroes.get(selectedIndex).secondary2.lvl));
		String update = (changed == null) ? String.format("Made changes to %s", heroes.get(selectedIndex).name) : String.format("Updated changes to %s", heroes.get(selectedIndex).name);
		textArea_changes.append(update);
		textArea_changes.append(System.getProperty("line.separator"));
	}
	
	private void saveHeroConfiguration() {
		StringBuffer buffer = new StringBuffer();
		for(Map.Entry<Integer, String> entry : changes.entrySet()) {
			buffer.append(entry.getKey()).append(";").append(entry.getValue()).append(System.getProperty("line.separator"));
		}
		File outputFile = new File("res/changes.txt");
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
			writer.write(buffer.toString());
			writer.flush();
			writer.close();
			JOptionPane.showMessageDialog(null, String.format("%d changes saved.", changes.size()), "Information", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void loadHeroConfiguration() {
		this.changes = new HashMap<Integer, String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader("res/changes.txt"));
			String in;
			while ((in = reader.readLine()) != null) {
				if (!in.contains(";")) {
					continue;
				}
				String[] data = in.split(";");
				String value =  data[1] + ";" +  data[2] + ";" +  data[3] + ";" +  data[4];
				changes.put(Integer.parseInt(data[0]), value);
			}
			reader.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void applyHeroConfiguration() {
		if (changes.size() < 1) {
			return;
		} else {
			for (Map.Entry<Integer, String> entry : changes.entrySet()) {
				String[] data = entry.getValue().split(";");
				modifyHero(entry.getKey(), Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2]), Integer.parseInt(data[3]));
			}
		}
		comboBox_hero.setSelectedIndex(0);
		JOptionPane.showMessageDialog(null, String.format("%d changes loaded.", changes.size()), "Information", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void modifyHero(Integer index, int skill1, int skill1lvl, int skill2, int skill2lvl) {
		heroes.get(index).setSecondary1(skill1, skill1lvl);
		heroes.get(index).setSecondary2(skill2, skill2lvl);
		textArea_changes.append(String.format("Loaded changes to %s", heroes.get(index).name));
		textArea_changes.append(System.getProperty("line.separator"));
	}

	private void loadHero(int selectedIndex) {
		Hero hero = heroes.get(selectedIndex);
		removeActionListeners();
		this.textField_speciality.setText(hero.speciality);
		this.textField_gender.setText(hero.gender.toString());
		this.textField_race.setText(hero.race.toString());
		this.textField_profession.setText(hero.profession.toString());
		this.comboBox_skill1.setSelectedItem(hero.secondary1.trait);
		this.comboBox_skill1lvl.setSelectedItem(hero.secondary1.getLvlString());
		this.comboBox_skill2.setSelectedItem(heroes.get(selectedIndex).secondary2.trait);
		if (this.comboBox_skill2.getSelectedItem().equals(HeroTrait.None)) {
			this.comboBox_skill2lvl.setSelectedItem("None");
			this.comboBox_skill2lvl.setEnabled(false);
		} else {
			this.comboBox_skill2lvl.setSelectedItem(hero.secondary2.getLvlString());
			this.comboBox_skill2lvl.setEnabled(true);
		}
		
		addActionListeners();
	}
	
	private void addActionListeners() {
		this.comboBox_skill1.addActionListener(this);
		this.comboBox_skill2.addActionListener(this);
	}
	private void removeActionListeners() {
		this.comboBox_skill1.removeActionListener(this);
		this.comboBox_skill2.removeActionListener(this);
	}
}
