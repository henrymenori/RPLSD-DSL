/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dsl;

/**
 *
 * @author Riady
 */
public class Transkrip {
    private String nim;
    private String nilai;
    private int semesterAwal;
    private int semesterAkhir;
    private int SKS;
    private String[] kodeKuliah;

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNilai() {
        return nilai;
    }

    public void setNilai(String nilai) {
        this.nilai = nilai;
    }

    public int getSemesterAwal() {
        return semesterAwal;
    }

    public void setSemesterAwal(int semesterAwal) {
        this.semesterAwal = semesterAwal;
    }

    public int getSemesterAkhir() {
        return semesterAkhir;
    }

    public void setSemesterAkhir(int semesterAkhir) {
        this.semesterAkhir = semesterAkhir;
    }

    public int getSKS() {
        return SKS;
    }

    public void setSKS(int SKS) {
        this.SKS = SKS;
    }

    public String[] getKodeKuliah() {
        return kodeKuliah;
    }

    public void setKodeKuliah(String[] kodeKuliah) {
        this.kodeKuliah = kodeKuliah;
    }
    
    
}
