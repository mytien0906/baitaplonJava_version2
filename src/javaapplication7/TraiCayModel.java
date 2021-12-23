/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Admin
 */
public class TraiCayModel {

    ArrayList<TraiCay> danhSachTraiCay = new ArrayList<>();

    /*
    Câu 1: Lấy danh sách tất cả trái cây
     */
    public ArrayList<TraiCay> layDanhSach() {
        return danhSachTraiCay;
    }

    /*
    Câu 2: Thêm trái cây
     */
    public boolean themTraiCay(TraiCay traiCay) {
        boolean ketQua = true;
        if (!danhSachTraiCay.isEmpty()) {
            for (int i = 0; i < danhSachTraiCay.size(); i++) {
                if (danhSachTraiCay.get(i).getMaTraiCay() == traiCay.getMaTraiCay()) {
                    ketQua = false;
                    break;
                } else if (i == danhSachTraiCay.size() - 1) {
                    danhSachTraiCay.add(traiCay);
                }
            }
        } else {
            danhSachTraiCay.add(traiCay);
        }
        return ketQua;
    }

    /*
    Câu 3: Xóa trái cây
     */
    public boolean xoaTraiCay(TraiCay traiCay) {
        boolean ketQua = false;
        TraiCay tc = new TraiCay();
        for (TraiCay traiCay1 : danhSachTraiCay) {
            if (traiCay1.getMaTraiCay() == traiCay.getMaTraiCay()) {
                tc = traiCay1;
            }
        }
        if (tc != null) {
            danhSachTraiCay.remove(tc);
            ketQua = true;
        }
        return ketQua;
    }

    /*
    4. Sửa thông tin trái cây
     */
    public void suaTraiCay(TraiCay traiCay) {
        for (TraiCay traiCay1 : danhSachTraiCay) {
            if (traiCay1.getMaTraiCay().equals(traiCay.getMaTraiCay())) {
                traiCay1.setTenTraiCay(traiCay.getTenTraiCay());
                traiCay1.setSoLuong(traiCay.getSoLuong());
                traiCay1.setGiaBan(traiCay.getGiaBan());
            }
        }
    }

    /*
    5. Sắp xếp danh sách theo tên tăng dần
     */
    public ArrayList<TraiCay> sapXepTenTangDan() {
        Collections.sort(danhSachTraiCay, new Comparator<TraiCay>() {
            @Override
            public int compare(TraiCay o1, TraiCay o2) {
                if (o1.getTenTraiCay().compareTo(o2.getTenTraiCay()) > 1) {
                    return 1;
                } else if (o1.getTenTraiCay().compareTo(o2.getTenTraiCay())<0) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        return danhSachTraiCay;
    }

    /*
    6. Sắp xếp danh sách với 2 tiêu chí: tên tăng dần và giá tăng dần
     */
    public ArrayList<TraiCay> sapXepTheoTenRoiSapXepGia() {
        Collections.sort(danhSachTraiCay, new Comparator<TraiCay>() {
            @Override
            public int compare(TraiCay o1, TraiCay o2) {
                if (o1.getTenTraiCay().compareTo(o2.getTenTraiCay()) > 1) {
                    return 1;
                } else if (o1.getTenTraiCay().compareTo(o2.getTenTraiCay()) < 1) {
                    return -1;
                } else {
                    if (o1.getGiaBan() > o2.getGiaBan()) {
                        return 1;
                    } else if (o1.getGiaBan() == o2.getGiaBan()) {
                        return 0;
                    } else {
                        return -1;
                    }
                }
            }
        });
        return danhSachTraiCay;
    }

    /*
    7. Tìm kiếm trái cây theo từ khóa gần đúng
     */
    public ArrayList<TraiCay> timKiemMaTraiCay(String timKiem) {
        ArrayList<TraiCay> ketQua = new ArrayList<>();
        String chuoiTimKiem = ".*" + timKiem + ".*";
        for (int i = 0; i < danhSachTraiCay.size(); i++) {
            TraiCay traiCay = danhSachTraiCay.get(i);
            if (traiCay.getMaTraiCay().toLowerCase().matches(chuoiTimKiem.toLowerCase())) {
                ketQua.add(traiCay);
            }
        }
        System.out.println(ketQua.size());
        return ketQua;
    }

    /*
    8. Lưu danh sách trái cây xuống file
     */
    public boolean ghiFile(String s, ArrayList<TraiCay> listTraiCay) {
        boolean ketQua = true;
        File f = new File(s);
        try {
            FileWriter fw = new FileWriter(f, true);
            String value = "";

            for (TraiCay traiCay : listTraiCay) {
                value = traiCay.getMaTraiCay()+ "/" + traiCay.getTenTraiCay()+ "/" + traiCay.getSoLuong()+ "/" + traiCay.getGiaBan();
                value = value.replace(',', '.');
                fw.write(value + "\n");
            }
            fw.close();
        } catch (Exception ex) {
            ketQua = false;
            ex.printStackTrace();
        }
        return ketQua;
    }
    /*
    9. Đọc danh sách trái cây từ file
    */
    public ArrayList<TraiCay> docFile(String s) throws IOException {
        danhSachTraiCay.clear();
        File f = new File(s);
        FileReader fr;
        try {
            fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] values = null;
            while ((line = br.readLine()) != null) {
                values = line.split("/");
                TraiCay tc = new TraiCay(values[0], values[1], Integer.parseInt(values[2]), Double.parseDouble(values[3]));
                danhSachTraiCay.add(tc);
            }
            br.close();
            fr.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return danhSachTraiCay;
    }
    public int countList(){
        return danhSachTraiCay.size();
    }
}
