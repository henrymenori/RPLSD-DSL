/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import java.util.Arrays;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Riady
 */
public class DSLtoSQL {
    public static String dslToSQL(Transkrip input){
        String ret = "SELECT * FROM mahasiswa,mata_kuliah,mengambil WHERE mahasiswa.NIM='"+input.getNim()+"' AND mahasiswa.nim=mengambil.nim AND mata_kuliah.kode_matkul=mengambil.kode_matkul";
        String queryNim = "";
        
        int sks = input.getSKS();
        String querySks = "";
        if(sks!=-1){
            querySks = "AND SKS="+sks;
        }
        
        String nilai = input.getNilai();
        String queryNilai = "";        
        if(nilai!=null){
            queryNilai = "AND nilai='"+nilai+"'";
        }
        
        String[] kodekul = input.getKodeKuliah();
        String queryKodekul = "";
        if(kodekul!=null){
            for(int i=0;i<kodekul.length;i++){
                if(i==0){
                    queryKodekul = "AND (mata_kuliah.kode_matkul='"+kodekul[i]+"'";
                }
                else if(i==kodekul.length-1){
                    queryKodekul += " OR mata_kuliah.kode_matkul='"+kodekul[i]+"')";
                }
                else{
                    queryKodekul += " OR mata_kuliah.kode_matkul='"+kodekul[i]+"'";
                }
                
            }
        }
        int semesterAwal = input.getSemesterAwal();
        int semesterAkhir = input.getSemesterAkhir();
        String querySemester = "";
        if(semesterAwal!=-1 && semesterAkhir!=-1){
            querySemester = "AND mata_kuliah.semester>="+semesterAwal+" AND mata_kuliah.semester<="+semesterAkhir;
        }
        else if(semesterAwal!=-1 && semesterAkhir==-1){
            querySemester = "AND mata_kuliah.semester="+semesterAwal;
        }
        
        ret+=" "+querySks+" "+queryKodekul+" "+queryNilai+" "+querySemester;
        return ret;
    }
    
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
//                System.out.println(columnName.get(i-1));
            }
            while(rs.next()){
                arrString = new ArrayList<String>();
                for (int i = 1; i <= columnsNumber; i++){
//                    System.out.println(rs.getString(i));
                    arrString.add(rs.getString(i));
                }
                result.add(arrString);
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            ex.printStackTrace();
                    
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { } // ignore
                rs = null;
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore
                stmt = null;
            }
        }
        FileWriter fw;
        try {
            fw = new FileWriter("D:\\DSLtoSQL.html");
            PrintWriter pw = new PrintWriter(fw);
             //Write to file line by line
            pw.println("<!DOCTYPE html>");
            pw.println("<html>");
            pw.println("<head>");
            pw.println("<title>Score Finder</title>");
            pw.println("</head>");
            pw.println("<body>");
            pw.println("<table dir=\"ltr\" width=\"500\" border=\"1\" summary=\"purpose/structure for speech output\">");
            pw.println("	<caption>Berikut adalah hasil eksekusi query " + Query);
            pw.println("	</caption>");
            pw.println("	<colgroup width=\"50%\" />");
            pw.println("	<colgroup id=\"colgroup\" class=\"colgroup\" align=\"center\" ");
            pw.println("			valign=\"middle\" title=\"title\" width=\"1*\" ");
            pw.println("			span=\"2\" style=\"background:#ddd;\" />");
            pw.println("	<thead>");
            pw.println("		<tr>");
            for(int i = 0; i < columnName.size(); i++){
                pw.println("			<th scope=\"col\">"+columnName.get(i)+"</th>");
            }
            pw.println("		</tr>");
            pw.println("	</thead>");
            pw.println("	<tbody>");
            for(int i = 0; i < result.size(); i++){
                pw.println("		<tr>");
                for(int j = 0; j < result.get(i).size(); j++){
                    pw.println("			<td>"+result.get(i).get(j)+"</td>");
                }
                pw.println("		</tr>");
            }
            pw.println("	</tbody>");            
            pw.println("</table>");
            pw.println("</body>");
            pw.println("</html>");

            //Flush the output to the file
            pw.flush();

            //Close the Print Writer
            pw.close();

            //Close the File Writer
            fw.close(); 
            } catch (IOException ex) {
                ex.printStackTrace();
            }
    }
    public static void main(String argv[]){
//        Transkrip t = new Transkrip("nim:13512081 semester:2 dengan_syarat nilai:ab");
//        System.out.println(t.getNim());
//        System.out.println(t.getNilai());
//        System.out.println(t.getSKS());
//        System.out.println(t.getSemesterAwal());
//        System.out.println(t.getSemesterAkhir());
//        System.out.println(Arrays.toString(t.getKodeKuliah()));
        /*t.setNim("13512098");
        t.setSemesterAwal(1);
        t.setNilai("A");
        t.setSKS(3);*/
//        System.out.println(dslToSQL(t));
        DSLtoSQL dts = new DSLtoSQL();
        dts.writeToExternalFile("SELECT * FROM mahasiswa,mata_kuliah,mengambil WHERE mahasiswa.NIM='13512098' AND mahasiswa.nim=mengambil.nim AND mata_kuliah.kode_matkul=mengambil.kode_matkul");

    }
}
