package models;

import java.util.ArrayList;
import java.util.Map;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import core.Hero;

public class HeroComboBoxModel extends AbstractListModel<Hero> implements ComboBoxModel<Hero> {

	private static final long serialVersionUID = -5398761852991588727L;
	private ArrayList<Hero> heroList;
	private Hero selected;
	
	public HeroComboBoxModel(Map<String, Hero> heroes) {
		this.heroList = new ArrayList<Hero>();
		heroList.addAll(heroes.values());
	}
	
	@Override
	public int getSize() {
		return heroList.size();
	}

	@Override
	public Hero getElementAt(int index) {
		return heroList.get(index);
	}

	@Override
	public void setSelectedItem(Object anItem) {
		selected = (Hero) anItem;		
	}
	
	@Override
	public Object getSelectedItem() {
		return selected;
	}

}
