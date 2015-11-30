/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dsl;

import java.util.Arrays;

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
    
    
    public static void main(String argv[]){
        Transkrip t = new Transkrip("nim:13512081 semester:2 dengan_syarat nilai:ab");
        System.out.println(t.getNim());
        System.out.println(t.getNilai());
        System.out.println(t.getSKS());
        System.out.println(t.getSemesterAwal());
        System.out.println(t.getSemesterAkhir());
        System.out.println(Arrays.toString(t.getKodeKuliah()));
        /*t.setNim("13512098");
        t.setSemesterAwal(1);
        t.setNilai("A");
        t.setSKS(3);*/
        
        System.out.println(dslToSQL(t));
    }
}
