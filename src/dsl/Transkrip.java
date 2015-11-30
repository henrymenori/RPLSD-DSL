/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dsl;

import java.util.ArrayList;
import java.util.List;

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
    private List<String> kode;
    
    public Transkrip(String input) {
        kode = new ArrayList();
        if(isInput(input)) {
            System.out.println("Input benar");
            kodeKuliah = (String[]) kode.toArray();
        }
        else {
            System.out.println("Input salah");
        }
    }

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
    
    // method
    private boolean isSingleNumber(char c) {
        return (c - '0' <= 9 && c - '0' >= 0);
    }
    
    private boolean isSingleChar(char c) {
        return (c - 'a' <= 25 && c - 'a' >= 0);
    }
    
    private boolean isNum(String s) {
        if(s.isEmpty()) {
            return false;
        }
        else {
            if(s.length() == 1) {
                return isSingleNumber(s.charAt(0));
            }
            else {
                return isNum(s.substring(0, s.length() - 1)) && isSingleNumber(s.charAt(s.length() - 1));
            }
        }
    }
    
    private boolean isNilai(String s) {
        if(s.length() == 1) {
            //nilai = s;
            //return isSingleChar(s.charAt(0));
            if(isSingleChar(s.charAt(0))) {
                nilai = s;
                return true;
            }
            else {
                nilai = null;
                return false;
            }
        }
        else if(s.length() == 2) {
            if(isSingleChar(s.charAt(0)) && isSingleChar(s.charAt(1))) {
                nilai = s;
                return true;
            }
            else {
                nilai = null;
                return false;
            }
        }
        else {
            return false;
        }
    }
    
    private boolean isInputNilai(String s) {
        if(s.length() < 6) {
            return false;
        }
        else {
            if("nilai:".equals(s.substring(0, 6))) {
                return isNilai(s.substring(6));
            }
            else {
                return false;
            }
        }
    }
    
    private boolean isSKS(String s) {
        if(s.length() == 1) {
            if(isSingleNumber(s.charAt(0))) {
                SKS = Integer.parseInt(s);
                return true;
            }
            else {
                SKS = 0;
                return false;
            }
        }
        else {
            return false;
        }
    }
    
    private boolean isInputSKS(String s) {
        if(s.length() < 4) {
            return false;
        }
        else {
            if("sks:".equals(s.substring(0, 4))) {
                return isSKS(s.substring(4));
            }
            else {
                return false;
            }
        }
    }
    
    private boolean isKodeKuliah(String s) {
        if(s.length() != 6) {
            return false;
        }
        else {
            kode.add(s);
            return
                    isSingleChar(s.charAt(0)) &&
                    isSingleChar(s.charAt(1)) &&
                    isSingleNumber(s.charAt(2)) &&
                    isSingleNumber(s.charAt(3)) &&
                    isSingleNumber(s.charAt(4)) &&
                    isSingleNumber(s.charAt(5));
            
        }
    }
    
    private boolean isInputKodeKuliah(String s) {
        if(s.length() < 12) {
            return false;
        }
        else {
            int lastIndexOf = s.lastIndexOf(" ");
            if(lastIndexOf == -1) {
                if("kode_kuliah:".equals(s.substring(0, 12))) {
                    return isKodeKuliah(s.substring(12));
                }
                else {
                    return false;
                }
            }
            else {
                return isInputKodeKuliah(s.substring(0, lastIndexOf)) && isInputKodeKuliah(s.substring(lastIndexOf + 1));
            }
        }
    }
    
    private boolean isSemester(String s) {
        if(s.length() == 1) {
            if(isSingleNumber(s.charAt(0))) {
                semesterAwal = Integer.parseInt(s);
                semesterAkhir = Integer.parseInt(s);
                return true;
            }
            else {
                semesterAwal = 0;
                semesterAkhir = 0;
                return false;
            }
        }
        else if(s.length() == 3) {
            if(isSingleNumber(s.charAt(0)) && s.charAt(1) == '-' && isSingleNumber(s.charAt(2))) {
                semesterAwal = Integer.parseInt("" + s.charAt(0));
                semesterAkhir = Integer.parseInt("" + s.charAt(2));
                return true;
            }
            else {
                semesterAwal = 0;
                semesterAkhir = 0;
                return false;
            }
        }
        else {
            return false;
        }
    }
    
    private boolean isInputSemester(String s) {
        if(s.length() < 9) {
            return false;
        }
        else {
            if("semester:".equals(s.substring(0, 9))) {
                return isSemester(s.substring(9));
            }
            else {
                return false;
            }
        }
    }
    
    private boolean isSyarat(String s) {
        int indexOf = s.indexOf(" ");
        if(indexOf == -1) {
            return isInputNilai(s) || isInputSKS(s);
        }
        else {
            return isInputNilai(s.substring(0, indexOf)) && isInputSKS(s.substring(indexOf + 1));
        }
    }
    
    private boolean isInputSyarat(String s) {
        if(s.length() < 14) {
            return false;
        }
        else {
            if("dengan_syarat ".equals(s.substring(0, 14))) {
                return isSyarat(s.substring(14));
            }
            else {
                return false;
            }
        }
    }
    
    private boolean isA(String s) {
        int indexOf = s.indexOf(" ");
        if(indexOf == -1) {
            return isInputSemester(s) || isInputKodeKuliah(s);
        }
        else {
            return isInputKodeKuliah(s) || (isInputSemester(s.substring(0, indexOf)) && isInputSyarat(s.substring(indexOf + 1)));
        }
    }
    
    private boolean isNIM(String s) {
        if(s.length() == 8) {
            //nim = s;
            //return isNum(s);
            if(isNum(s)) {
                nim = s;
                return true;
            }
            else {
                nim = null;
                return false;
            }
        }
        else {
            return false;
        }
    }
    
    public boolean isInput(String s) {
        if(s.length() < 4) {
            return false;
        }
        else {
            if("nim:".equals(s.substring(0, 4))) {
                int indexOf = s.indexOf(" ");
                if(indexOf == -1) {
                    return isNIM(s.substring(4));
                }
                else {
                    return isNIM(s.substring(4, indexOf)) && isA(s.substring(indexOf + 1));
                }
            }
            else {
                return false;
            }
        }
    }
}
