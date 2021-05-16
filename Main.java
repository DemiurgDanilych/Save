import java.io.*;
import java.util.*;
import java.util.zip.*;

public class Main {
	
	public static int version = 0;
	public static String path = "\\Games\\savegames\\";
	public static List<String> routeSavesArr = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		

		GameProgress save1 = new GameProgress(100, 2, 3, 558);
		GameProgress save2 = new GameProgress(75, 1, 8, 621);
		GameProgress save3 = new GameProgress(20, 10, 10, 730);
		
		saveGame(path, save1);
		saveGame(path, save2);
		saveGame(path, save3);

		zipFiles(path,routeSavesArr);
	}
	
	private static void saveGame(String path, GameProgress save) {
		try (FileOutputStream fos = new FileOutputStream(path + "save" + (version = (version + 1)) + ".dat");
			 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(save);
			routeSavesArr.add(path + "save" + version + ".dat");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	private static void zipFiles(String path, List<String> routeSavesArr) throws IOException {
		ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(path + "savesZip.zip"));
		
			for (String routeSave : routeSavesArr) {
				ZipEntry entry = new ZipEntry(routeSave);
				zout.putNextEntry(entry);
				File delSave = new File(routeSave);
				delSave.delete();
			}
			zout.close();
		}
	}
