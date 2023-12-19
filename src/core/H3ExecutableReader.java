package core;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.LinkedHashMap;
import java.util.Map;
import enums.Gender;
import enums.HeroHeader;
import enums.HeroTrait;
import enums.Profession;
import enums.Race;
import enums.SkillLevel;

public class H3ExecutableReader {

	private H3ExecutableReader() {

	}

	public static Map<String, Hero> readHeroes(Path executable) throws IOException {
		Map<String, Hero> heroes = new LinkedHashMap<String, Hero>();
		FileChannel fileChannel = FileChannel.open(executable, StandardOpenOption.READ);

		for (HeroHeader header : HeroHeader.values()) {
			long offset = header.getOffset();
			int rawData = -1;
			ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES * 9);

			// Process the data
			fileChannel.read(buffer, offset);
			buffer.flip();
			rawData = buffer.getInt();
			Gender gender = (rawData == 0x00000000) ? Gender.MALE : Gender.FEMALE;
			rawData = buffer.getInt();
			Race race = Race.values()[Integer.reverseBytes(rawData)];
			rawData = buffer.getInt();
			Profession profession = Profession.values()[Integer.reverseBytes(rawData)];
			rawData = buffer.getInt();
			HeroTrait skill1 = rawData == 0xFFFFFFFF ? HeroTrait.NONE
					: HeroTrait.values()[Integer.reverseBytes(rawData)];
			rawData = buffer.getInt();
			SecondarySkill secondary1 = new SecondarySkill(skill1, SkillLevel.values()[Integer.reverseBytes(rawData)]);
			rawData = buffer.getInt();
			HeroTrait skill2 = rawData == 0xFFFFFFFF ? HeroTrait.NONE
					: HeroTrait.values()[Integer.reverseBytes(rawData)];
			rawData = buffer.getInt();
			SecondarySkill secondary2 = new SecondarySkill(skill2, SkillLevel.values()[Integer.reverseBytes(rawData)]);
			rawData = buffer.getInt();
			boolean hasSpellBook = rawData == 0x01000000;
			rawData = buffer.getInt();
			Hero hero = new Hero(header, secondary1, secondary2, null, gender, race, profession,
					hasSpellBook, rawData);
			heroes.put(header.getName(), hero);

		}
		fileChannel.close();
		return heroes;
	}
}
