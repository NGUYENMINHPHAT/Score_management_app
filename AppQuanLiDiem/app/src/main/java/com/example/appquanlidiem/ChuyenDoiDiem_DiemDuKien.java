package com.example.appquanlidiem;

public class ChuyenDoiDiem_DiemDuKien {
    public static String ChuyenDoiDiemChu(float sodiemnhapvao) {
        String diemchu = "t";

        if (sodiemnhapvao < 4.0 ){
            diemchu = "F";
        }
        else if (sodiemnhapvao > 4.0 && sodiemnhapvao <4.9 ){
            diemchu = "D";
        }
        else if (sodiemnhapvao > 4.8 && sodiemnhapvao <5.6 ){
            diemchu = "D+";
        }
        else if (sodiemnhapvao > 5.4 && sodiemnhapvao < 6.5 ){
            diemchu = "C";
        }
        else if (sodiemnhapvao > 6.4 && sodiemnhapvao < 7.0 ){
            diemchu = "C+";
        }
        else if (sodiemnhapvao > 6.9 && sodiemnhapvao < 8.0 ){
            diemchu = "B";
        }
        else if (sodiemnhapvao > 7.9 && sodiemnhapvao < 9.0 ){
            diemchu = "B+";
        }
        else if (sodiemnhapvao > 8.9 && sodiemnhapvao < 10.1 ){
            diemchu = "A";
        }
        return diemchu;
    }

    public static Float Diemhe10SangDiemHe4(float sodiemdoi) {
        float diemheso4 = 0.0F;

        if (sodiemdoi < 4.0 ){
            diemheso4 = 0.0F;
        }
        else if (sodiemdoi > 4.0 && sodiemdoi <4.9 ){
            diemheso4 = 1.0F;
        }
        else if (sodiemdoi > 4.8 && sodiemdoi <5.6 ){
            diemheso4 = 1.5F;
        }
        else if (sodiemdoi > 5.4 && sodiemdoi < 6.5 ){
            diemheso4 = 2.0F;
        }
        else if (sodiemdoi > 6.4 && sodiemdoi < 7.0 ){
            diemheso4 = 2.5F;
        }
        else if (sodiemdoi > 6.9 && sodiemdoi < 8.0 ){
            diemheso4 = 3.0F;
        }
        else if (sodiemdoi > 7.9 && sodiemdoi < 9.0 ){
            diemheso4 = 3.5F;
        }
        else if (sodiemdoi > 8.9 && sodiemdoi < 10.1 ){
            diemheso4 = 4.0F;
        }
        return diemheso4;
    }

    public static float ChuyenDoiDiemHe4(String kitudiemnhapvao) {
        float diemhe4 = 0.1F;

        switch (kitudiemnhapvao) {
            case " F":
                diemhe4 = 0.0F;
                break;
            case " D":
                diemhe4 = 1.0F;
                break;
            case " D+":
                diemhe4 = 1.5F;
                break;
            case " C":
                diemhe4 = 2.0F;
                break;
            case " C+":
                diemhe4 = 2.5F;
                break;
            case " B":
                diemhe4 = 3.0F;
                break;
            case " B+":
                diemhe4 = 3.5F;
                break;
            case " A":
                diemhe4 = 4.0F;
                break;
        }
        return diemhe4;

    }


}
