import java.io.*;
import java.nio.file.*;
import java.util.*;

public class TeamBConverter {
    public static void main(String[] args) {
        String filePath = "C:/Users/ADMIN/Documents/TeamB/Textfile.txt"; // ระบุ path ให้ถูกต้อง
        try {
            // อ่านไฟล์ทั้งหมด
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            List<Map<String, String>> people = new ArrayList<>(); // เก็บข้อมูลแต่ละคน
            
            Map<String, String> person = new HashMap<>();
            for (String line : lines) {
                line = line.trim(); // ตัดช่องว่าง
                if (line.isEmpty() || line.equals("---------------------")) {
                    // หากพบตัวแบ่ง หรือบรรทัดว่าง ให้เพิ่มข้อมูลคนปัจจุบันในรายการ
                    if (!person.isEmpty()) {
                        people.add(person);
                        person = new HashMap<>();
                    }
                } else {
                    // แยก Key และ Value เช่น "Name: Emilai"
                    String[] parts = line.split(":", 2);
                    if (parts.length == 2) {
                        String key = parts[0].trim();
                        String value = parts[1].trim();
                        person.put(key, value);
                    }
                }
            }
            // เพิ่มข้อมูลคนสุดท้ายในรายการ
            if (!person.isEmpty()) {
                people.add(person);
            }

            // ตรวจสอบข้อมูลที่ถูกอ่าน
            System.out.println("Debug: ข้อมูลทั้งหมดที่อ่านได้");
            for (Map<String, String> p : people) {
                System.out.println(p);
            }

            System.out.println("\nผลลัพธ์การประมวลผลข้อมูล:\n");

            // ประมวลผลข้อมูลแต่ละคน
            for (Map<String, String> p : people) {
                String name = p.getOrDefault("Name", "Unknown");
                int age = Integer.parseInt(p.getOrDefault("Age", "0"));
                String score = p.getOrDefault("Software Testing Score", "N/A");

                // แปลงอายุเป็นพุทธศักราช
                int buddhistEra = 543 + (2024 - age);

                // แปลงคะแนนเป็น Rank
                HashMap<String, String> gradeToRank = new HashMap<>();
                gradeToRank.put("A", "High Distinction");
                gradeToRank.put("B+", "Distinction");
                gradeToRank.put("B", "Distinction");
                gradeToRank.put("C+", "Good");
                gradeToRank.put("C", "Good");
                gradeToRank.put("D+", "Normal");
                gradeToRank.put("D", "Normal");
                gradeToRank.put("F", "Failed");

                String rank = gradeToRank.getOrDefault(score, "Unknown");

                // แสดงผล
                System.out.println("Name: " + name);
                System.out.println("Buddhist Era: " + buddhistEra);
                System.out.println("Software Testing Rank: " + rank);
                System.out.println("-------------------------");
            }

        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
