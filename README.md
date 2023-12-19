# HOMM3SecondarySkillsModder

## 2023 Update

Wanted to just update the GUI, but then completely rewrote basically everything. The program was pretty janky and held together by duct tape. I also found many edge cases that were not covered, so I hope I didn't ruin too many HOMM3 executables. All this jank is fixed now with this update.

Feature list of the 2023 Update:

+ Slick GUI: managed gui that resizes properly and displays more info
	+ A table that accurately keeps track of you current changes
	+ You can double-click on a hero name in the table to fast-scroll to that hero for quick changing
	+ More info about a hero in the info box
	+ Prevents accidental bogus configurations (like empty first skill or two times the same skill)
	+ Buttons are disabled when clicking them would fail anyway
	+ The program now starts up first before asking you to browse to an .exe
+ Auto-backup: before any attempt to change the executable, a backup will be created
	+ Backups are in your heroes3/h3hota.exe folder, in a subfolder called "backupSkillModder"
	+ The backup exe will be named heroes3/h3hota.exe_unixtimestamp
+ Save and load: properly redone the save/load function
	+ Fixed many, many bugs with the load feature
	+ Fixed not quite as many bugs with the save feature
	+ Saving now names your save file with a timestamp (yyyyMMdd_HHmmss.mod)
+ Easier browsing: made it easier to navigate through the file system to select your executable
	+ The program now remembers the directory of your last opened executable and starts the prompt there
	+ The program now automatically prompts you to the resources folder when loading a saved configuration
	+ Mistakenly selecting a wrong .exe is now handled properly and prompts for a retry instead of just failing
	+ Files with the wrong extension are now filtered out from the file view
+ Better internal workings: Java8 should be standard everywhere now, so it's now based on that
	+ For self-builders: included .project and .classpath file
	+ For people that like to fiddle around with code: better code structure, you should find it easier to navigate.
+ Many, many bug fixes: I'll list the most egregious one I've fixed
	+ Loading a different executable after loading the first was leading to bogus data in the data lists
	+ Selecting a file when the program started (any file, not just an exe) allocated a RandomAccessFile that was never closed
	+ Pretty sure the old version wrote the skills to the executable in the wrong order
	+ The update list did not accurately keep track of your changes
	+ in longer modding sessions, saves and writes were essentially random

And probably more that I missed to put in this list. It's a complete rewrite of an old idea.

Enjoy:

[Link to latest release](toAdd)

## Original readme.md

Tool with a basic GUI, able to mod the secondary skills of heroes in HOMM3 (the HotA-exclusive heroes from Cove town are not included - maybe in a future update =))

Open the jar-File, navigate to your h3hota.exe or Heroes3.exe and press open. Edit the hero, then press "Update Hero" to save the changes you made. It will show a confirmation in the text area on the right. At this point, your executable will still be unchanged, so if you wish to discard your changes, just exit the program. To write the changes to the exe, press "Unlock" and then press "Write file".

# MAKE A BACKUP BEFORE USING THIS IN CASE SOMETHING GOES WRONG

