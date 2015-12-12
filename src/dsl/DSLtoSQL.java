package dsl;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

/**
 *
 * @author Riady
 */
public class DSLtoSQL {
    // Fungsi untuk mengubah input DSL dari user menjadi query SQL
    public static String dslToSQL(Transkrip input){
        // Membuat default query
        String ret = "SELECT * FROM mahasiswa, mata_kuliah, mengambil WHERE mahasiswa.NIM='" + input.getNim() + "' AND mahasiswa.nim=mengambil.nim AND mata_kuliah.kode_matkul=mengambil.kode_matkul";
        String queryNim = "";
        
        // Membuat query tambahan apabila user ingin melihat transkrip dengan jumlah SKS tertentu
        int sks = input.getSKS();
        String querySks = "";
        if(sks != 0){
            querySks = "AND SKS=" + sks;
        }
        
        // Membuat query tambahan apabila user ingin melihat transkrip dengan nilai tertentu
        String nilai = input.getNilai();
        String queryNilai = "";        
        if(nilai != null){
            queryNilai = "AND nilai='" + nilai+"'";
        }
        
        // Membuat query tambahan apabila user ingin melihat transkrip dengan kode kuliah tertentu
        String[] kodekul = input.getKodeKuliah();
        String queryKodekul = "";
        if(kodekul != null){
            for(int i = 0; i < kodekul.length; i++){
                if(i == 0){
                    queryKodekul = "AND (mata_kuliah.kode_matkul='" + kodekul[i] + "'";
                }
                else if(i == kodekul.length - 1){
                    queryKodekul += " OR mata_kuliah.kode_matkul='" + kodekul[i] + "')";
                }
                else{
                    queryKodekul += " OR mata_kuliah.kode_matkul='" + kodekul[i] + "'";
                }
                
            }
        }
        
        // Membuat query tambahan apabila user ingin melihat transkrip pada semester tertentu
        int semesterAwal = input.getSemesterAwal();
        int semesterAkhir = input.getSemesterAkhir();
        String querySemester = "";
        if(semesterAwal != 0 && semesterAkhir != 0){
            querySemester = "AND mata_kuliah.semester>=" + semesterAwal + " AND mata_kuliah.semester<=" + semesterAkhir;
        }
        else if(semesterAwal != 0 && semesterAkhir == 0){
            querySemester = "AND mata_kuliah.semester=" + semesterAwal;
        }
        
        ret += " " + querySks + " " + queryKodekul + " " + queryNilai + " " + querySemester;
        return ret;
    }
    
    // Prosedur untuk mengambil data dari basis data menggunakan query yang sudah di-generate oleh fungsi dslToSQL
    public void writeToExternalFile(String Query){
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        ArrayList<String> arrString = new ArrayList<String>();
        ArrayList<String> columnName = new ArrayList<String>();
        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", "root");
        connectionProps.put("password", "");
        
        Statement stmt = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null; 

        try {
            conn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/dsl", connectionProps);
            
            stmt = conn.createStatement();
            rs = stmt.executeQuery(Query);
            rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            for (int i = 1; i <= columnsNumber; i++){
                columnName.add(rsmd.getColumnName(i));
            }
            while(rs.next()){
                arrString = new ArrayList<String>();
                for (int i = 1; i <= columnsNumber; i++){
                    arrString.add(rs.getString(i));
                }
                result.add(arrString);
            }
        } catch (SQLException ex) {
            // Handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            ex.printStackTrace();
                    
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { } // Ignore
                rs = null;
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // Ignore
                stmt = null;
            }
        }
        
        FileWriter fw;
        try {
            fw = new FileWriter("D:\\DSLtoSQL.html");
            PrintWriter pw = new PrintWriter(fw);
            
            // Write to file line by line
            pw.println("<!DOCTYPE html>");
            pw.println("<html>");
            pw.println("<head>");
            pw.println("<title>Transkrip Mahasiswa</title>");
            pw.println("</head>");
            pw.println("<body>");
            if(result.size() > 0) { // Apabila hasil query tidak kosong
                // Mencetak data mahasiswa                
                pw.println("<h2 align=\"center\">Display Status Mahasiswa</h2><hr align='center' width='800'>");
                pw.println("<table align=\"center\" dir=\"ltr\" width=\"500\" border=\"0\" >");
                pw.println("<tr>");
                pw.println("<td colspan=\"1\" rowspan=\"1\" >NIM/No Reg/Nama</td>");
                pw.println("<td colspan=\"1\" rowspan=\"1\" >:</td>");
                pw.println("<td colspan=\"1\" rowspan=\"1\" >" + result.get(0).get(0) + " / " + result.get(0).get(1) + " / " + result.get(0).get(2) + "</td>");
                pw.println("</tr>");
                pw.println("<tr >");
                pw.println("<td colspan=\"1\" rowspan=\"1\" >NIP/Dosen Wali</td>");
                pw.println("<td colspan=\"1\" rowspan=\"1\" >:</td>");
                pw.println("<td colspan=\"1\" rowspan=\"1\" >" + result.get(0).get(6) + " / " + result.get(0).get(7) + "</td>");
                pw.println("</tr>");
                pw.println("<tr >");
                pw.println("<td colspan=\"1\" rowspan=\"1\" >Fakultas</td>");
                pw.println("<td colspan=\"1\" rowspan=\"1\" >:</td>");
                pw.println("<td colspan=\"1\" rowspan=\"1\" >" + result.get(0).get(8) + "</td>");
                pw.println("</tr>");
                pw.println("<tr >");
                pw.println("<td colspan=\"1\" rowspan=\"1\" >Program Studi</td>");
                pw.println("<td colspan=\"1\" rowspan=\"1\" >:</td>");
                pw.println("<td colspan=\"1\" rowspan=\"1\" >" + result.get(0).get(9) + "</td>");
                pw.println("</tr>");
                pw.println("<tr >");
                pw.println("<td colspan=\"1\" rowspan=\"1\" >Alamat / Kode Pos</td>");
                pw.println("<td colspan=\"1\" rowspan=\"1\" >:</td>");
                pw.println("<td colspan=\"1\" rowspan=\"1\" >" + result.get(0).get(3) + " / " + result.get(0).get(4) + "</td>");
                pw.println("</tr>");
                pw.println("<tr >");
                pw.println("<td colspan=\"1\" rowspan=\"1\" >Total SKS</td>");
                pw.println("<td colspan=\"1\" rowspan=\"1\" >:</td>");
                pw.println("<td colspan=\"1\" rowspan=\"1\" >" + result.get(0).get(10) + "</td>");
                pw.println("</tr>");
                pw.println("</table>");
                pw.println("<table align=\"center\" dir=\"ltr\" width=\"500\" border=\"2\" >");
                pw.println("<h3 align=\"center\"> Transkrip Nilai Mahasiswa");
                pw.println("</h3>");
                
//            pw.println("  <caption>Berikut adalah hasil eksekusi query: ");
//            pw.println(Query);
//            pw.println("<br>");
//            pw.println("<br>");
//            pw.println("<br>");
//            pw.println("  </caption>");
                
                // Mencetak transkrip mahasiswa
                pw.println("	<thead>");
                pw.println("		<tr>");
                for(int i = 11; i < columnName.size(); i++){
                    pw.println("			<th scope=\"col\">" + columnName.get(i) + "</th>");
                }
                pw.println("		</tr>");
                pw.println("	</thead>");
                pw.println("	<tbody>");
                for(int i = 0; i < result.size(); i++){
                    pw.println("		<tr>");
                    for(int j = 11; j < result.get(i).size(); j++){
                        pw.println("			<td>" + result.get(i).get(j) + "</td>");
                    }
                    pw.println("		</tr>");
                }
                pw.println("	</tbody>");            
                pw.println("</table>");
            }
            else { // Apabila hasil query kosong
                pw.println("<h1 align=\"center\"> Hasil Query Kosong");
                pw.println("</h1>");
            }
            pw.println("</body>");
            pw.println("</html>");

            //Flush the output to the file
            pw.flush();

            //Close the Print Writer
            pw.close();

            //Close the File Writer
            fw.close();
            System.out.println("HTML created in D: ");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public static void main(String argv[]){
        Scanner reader = new Scanner(System.in);
        String query = reader.nextLine();       
        Transkrip t = new Transkrip(query);
//        System.out.println(t.getNim());
//        System.out.println(t.getNilai());
//        System.out.println(t.getSKS());
//        System.out.println(t.getSemesterAwal());
//        System.out.println(t.getSemesterAkhir());
//        System.out.println(Arrays.toString(t.getKodeKuliah()));
//        t.setNim("13512098");
//        t.setSemesterAwal(1);
//        t.setNilai("A");
//        t.setSKS(3);
        DSLtoSQL dts = new DSLtoSQL();
        System.out.println(dslToSQL(t));
        dts.writeToExternalFile(dslToSQL(t));
    }
}