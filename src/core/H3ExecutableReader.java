package core;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

import enums.Gender;
import enums.HeroData;
import enums.HeroTrait;
import enums.Profession;
import enums.Race;

public class H3ExecutableReader {

	private H3ExecutableReader() {

	}

	public static Map<String, Hero> readHeroes(Path executable) throws IOException {
		Map<String, Hero> heroes = new HashMap<String, Hero>();
		FileChannel fileChannel = FileChannel.open(executable, StandardOpenOption.READ);

		for (HeroData heroData : HeroData.values()) {
			String name = heroData.getName();
			String speciality = heroData.getSpecialty();
			long offset = heroData.getOffset();
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
			SecondarySkill secondary1 = new SecondarySkill(skill1, rawData);
			rawData = buffer.getInt();
			HeroTrait skill2 = rawData == 0xFFFFFFFF ? HeroTrait.NONE
					: HeroTrait.values()[Integer.reverseBytes(rawData)];
			rawData = buffer.getInt();
			SecondarySkill secondary2 = new SecondarySkill(skill2, rawData);
			rawData = buffer.getInt();
			boolean hasSpellBook = rawData == 0x01000000;
			rawData = buffer.getInt();
			Hero hero = new Hero(name, speciality, secondary1, secondary2, gender, race, profession,
					hasSpellBook, rawData);
			heroes.put(name, hero);

		}
		fileChannel.close();
		return heroes;
	}
}
