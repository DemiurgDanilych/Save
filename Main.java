import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
	
	public static int version = 0;
	public static String route = "C:\\Games\\savegames\\";
	public static List<String> routeSavesArr = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		

		GameProgress save1 = new GameProgress(100, 2, 3, 558);
		GameProgress save2 = new GameProgress(75, 1, 8, 621);
		GameProgress save3 = new GameProgress(20, 10, 10, 730);
		
		saveGame(route, save1);
		saveGame(route, save2);
		saveGame(route, save3);
		
		
		System.out.println(routeSavesArr.toString());
		zipFiles(route,routeSavesArr);
	}
	
	private static void saveGame(String route, GameProgress save) {
		try (FileOutputStream fos = new FileOutputStream(route + "save" + (version = (version + 1)) + ".dat");
			 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(save);
			routeSavesArr.add(route + "save" + version + ".dat");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	private static void zipFiles(String route, List<String> routeSavesArr) throws IOException {
		ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(route + "savesZip.zip"));
		
			for (String routeSave : routeSavesArr) {
				ZipEntry entry = new ZipEntry(routeSave);
				zout.putNextEntry(entry);
				File delSave = new File(routeSave);
				delSave.delete();
			}
			zout.close();
		}
	}
	
