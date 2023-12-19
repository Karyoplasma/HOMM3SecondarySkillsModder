package core;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Map;

public class H3ExecutableWriter {

	private H3ExecutableWriter() {

	}

	public static boolean writeAllChanges(List<Hero> changes, Path executable) throws IOException {
		if (executable == null) {
			return false;
		}
		if (!executable.toFile().exists()) {
			return false;
		}
		if (executable.toFile().isDirectory()) {
			return false;
		}
		FileChannel fileChannel = FileChannel.open(executable, StandardOpenOption.WRITE);
		for (Hero hero : changes) {
			SkillChange skillChange = hero.getChange();
			System.out.println(String.format("I write hero %s: %s (%s), %s (%s)", hero.getName(),
					skillChange.getChanged1().getTrait(), skillChange.getChanged1().getLevel(),
					skillChange.getChanged2().getTrait(), skillChange.getChanged2().getLevel()));
			writeHero();
		}

		fileChannel.close();
		return false;
	}
	
	private static boolean writeHero() {
//		try {
//			file.seek(this.offset + 0xc);
//			file.writeInt(this.secondary1.getTrait().getID());
//			file.seek(this.offset + 0x10);
//			file.writeInt(this.secondary1.getLvl());
//			file.seek(this.offset + 0x14);
//			file.writeInt(this.secondary2.getTrait().getID());
//			file.seek(this.offset + 0x18);
//			file.writeInt(this.secondary2.getLvl());
//			file.seek(this.offset);
//			
//			return true;
//		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return false;
//		}
	}
}
