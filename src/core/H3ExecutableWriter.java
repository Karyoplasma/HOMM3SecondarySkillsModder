package core;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

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
			long offset = hero.getOffset() + 0xC;
			ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES * 4);
			buffer.putInt(skillChange.getChanged1().getTrait().getBytes());
			buffer.putInt(skillChange.getChanged1().getLevel().getBytes());
			buffer.putInt(skillChange.getChanged2().getTrait().getBytes());
			buffer.putInt(skillChange.getChanged2().getLevel().getBytes());

			System.out.println(String.format("I write hero %s: %s (%s), %s (%s)", hero.getName(),
					skillChange.getChanged1().getTrait(), skillChange.getChanged1().getLevel(),
					skillChange.getChanged2().getTrait(), skillChange.getChanged2().getLevel()));
			buffer.flip();
			fileChannel.write(buffer, offset);
		}

		fileChannel.close();
		return true;
	}
}
