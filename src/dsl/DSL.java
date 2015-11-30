/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsl;

/**
 *
 * @author henry
 */
public class DSL {

    // attribute
    private static String nim;
    
    public static void main(String[] args) {        
        System.out.println(isInput("nim:13512082 semester:2 dengan_syarat nilai:a sks:4"));
        System.out.println(nim);
    }
    
    static public boolean isSingleNumber(char c) {
        return (c - '0' <= 9 && c - '0' >= 0);
    }
    
    static public boolean isSingleChar(char c) {
        return (c - 'a' <= 25 && c - 'a' >= 0);
    }
    
    static public boolean isNum(String s) {
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
    
    static public boolean isNilai(String s) {
        if(s.length() == 1) {
            return isSingleChar(s.charAt(0));
        }
        else if(s.length() == 2) {
            return isSingleChar(s.charAt(0)) && isSingleChar(s.charAt(1));
        }
        else {
            return false;
        }
    }
    
    static public boolean isInputNilai(String s) {
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
    
    static public boolean isSKS(String s) {
        if(s.length() == 1) {
            return isSingleNumber(s.charAt(0));
        }
        else {
            return false;
        }
    }
    
    static public boolean isInputSKS(String s) {
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
    
    static public boolean isKodeKuliah(String s) {
        if(s.length() != 6) {
            return false;
        }
        else {
            return
                    isSingleChar(s.charAt(0)) &&
                    isSingleChar(s.charAt(1)) &&
                    isSingleNumber(s.charAt(2)) &&
                    isSingleNumber(s.charAt(3)) &&
                    isSingleNumber(s.charAt(4)) &&
                    isSingleNumber(s.charAt(5));
        }
    }
    
    static public boolean isInputKodeKuliah(String s) {
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
    
    static public boolean isSemester(String s) {
        if(s.length() == 1) {
            return isSingleNumber(s.charAt(0));
        }
        else if(s.length() == 3) {
            return isSingleNumber(s.charAt(0)) && s.charAt(1) == '-' && isSingleNumber(s.charAt(2));
        }
        else {
            return false;
        }
    }
    
    static public boolean isInputSemester(String s) {
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
    
    static public boolean isSyarat(String s) {
        int indexOf = s.indexOf(" ");
        if(indexOf == -1) {
            return isInputNilai(s) || isInputSKS(s);
        }
        else {
            return isInputNilai(s.substring(0, indexOf)) && isInputSKS(s.substring(indexOf + 1));
        }
    }
    
    static public boolean isInputSyarat(String s) {
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
    
    static public boolean isA(String s) {
        int indexOf = s.indexOf(" ");
        if(indexOf == -1) {
            return isInputSemester(s) || isInputKodeKuliah(s);
        }
        else {
            return isInputKodeKuliah(s) || (isInputSemester(s.substring(0, indexOf)) && isInputSyarat(s.substring(indexOf + 1)));
        }
    }
    
    static public boolean isNIM(String s) {
        if(s.length() == 8) {
            nim = s;
            return isNum(s);
        }
        else {
            return false;
        }
    }
    
    static public boolean isInput(String s) {
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
