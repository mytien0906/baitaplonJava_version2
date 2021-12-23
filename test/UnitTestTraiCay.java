
import java.util.ArrayList;
import javaapplication7.TraiCay;
import javaapplication7.TraiCayModel;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Admin
 */
public class UnitTestTraiCay {

    TraiCayModel model = new TraiCayModel();
    TraiCay traiCay1 = new TraiCay("T1", "Táo đỏ", 20, 50);
    TraiCay traiCay2 = new TraiCay("T2", "Táo xanh", 50, 80);
    TraiCay traiCay3 = new TraiCay("T3", "Táo vàng", 10, 30);
    TraiCay traiCay4 = new TraiCay("T4", "Táo đen", 30, 70);

    /*
    Câu 1: Lấy danh sách tất cả trái cây
     */
    @Test
    public void testDanhSachTraiCay() {
        model.themTraiCay(traiCay1);
        model.themTraiCay(traiCay2);
        model.themTraiCay(traiCay3);
        model.themTraiCay(traiCay4);
        ArrayList<TraiCay> actual = model.layDanhSach();
        ArrayList<TraiCay> expect = new ArrayList<>();
        expect.add(traiCay1);
        expect.add(traiCay2);
        expect.add(traiCay3);
        expect.add(traiCay4);
        assertEquals(expect.size(), actual.size());
    }
    /*
    Câu 2: Thêm trái cây
     */
    @Test
    public void testThemTraiCay() {
        model.themTraiCay(traiCay1);
        model.themTraiCay(traiCay2);
        ArrayList<TraiCay> actual = model.layDanhSach();
        ArrayList<TraiCay> expect = new ArrayList<>();
        expect.add(traiCay1);
        expect.add(traiCay2);
        assertEquals(expect.size(), actual.size());
    }
    /*
    Câu 3: Xóa trái cây
     */
    @Test
    public void testXoaTraiCay() {
        model.themTraiCay(traiCay1);
        model.themTraiCay(traiCay2);
        model.themTraiCay(traiCay3);
        model.xoaTraiCay(traiCay1);
        ArrayList<TraiCay> actual = model.layDanhSach();
        ArrayList<TraiCay> expect = new ArrayList<>();
        expect.add(traiCay2);
        expect.add(traiCay3);
        assertEquals(expect.size(), actual.size());
    }
    /*
    4. Sửa thông tin trái cây
     */
    @Test
    public void testSuaTraiCay() {
        model.themTraiCay(traiCay1);
        model.themTraiCay(traiCay2);
        TraiCay duLieuMoi = new TraiCay("T1","Táo đỏ 1", 500, 500);
        model.suaTraiCay(duLieuMoi);
        ArrayList<TraiCay> actual = model.layDanhSach();
        ArrayList<TraiCay> expect = new ArrayList<>();
        expect.add(traiCay1);
        assertEquals(expect.get(0).getTenTraiCay().toString(), actual.get(0).getTenTraiCay().toString());
    }
    /*
    5. Sắp xếp danh sách theo tên tăng dần
     */
    @Test
    public void testSapXepTheoTenTangDan() {
        model.themTraiCay(traiCay1);
        model.themTraiCay(traiCay2);
        model.themTraiCay(traiCay3);
        ArrayList<TraiCay> actual = model.sapXepTenTangDan();
        ArrayList<TraiCay> expect = new ArrayList<>();
        expect.add(traiCay1);
        expect.add(traiCay2);
        expect.add(traiCay3);
        assertEquals(expect.get(1).getTenTraiCay().toString(), actual.get(1).getTenTraiCay().toString());
    }
    /*
    6. Sắp xếp danh sách với 2 tiêu chí: tên tăng dần và giá tăng dần
     */
    @Test
    public void testSapXepTheoTenVaGiaTangDan() {
        model.themTraiCay(traiCay1);
        model.themTraiCay(traiCay2);
        model.themTraiCay(traiCay3);
        ArrayList<TraiCay> actual = model.sapXepTheoTenRoiSapXepGia();
        ArrayList<TraiCay> expect = new ArrayList<>();
        expect.add(traiCay1);
        expect.add(traiCay2);
        expect.add(traiCay3);
        assertEquals(expect.get(1).getTenTraiCay().toString(), actual.get(1).getTenTraiCay().toString());
    }
    /*
    7. Tìm kiếm trái cây theo từ khóa gần đúng
     */
    @Test
    public void testTimKiemTheoMaTraiCay() {
        model.themTraiCay(traiCay1);
        model.themTraiCay(traiCay2);
        model.themTraiCay(traiCay3);
        String timKiem = "T1";
        int actual = model.timKiemMaTraiCay(timKiem).size();
        ArrayList<TraiCay> expect = new ArrayList<>();
        expect.add(traiCay1);
        assertEquals(expect.size(), actual);
    }
    /*
    8. Lưu danh sách trái cây xuống file
     */
    @Test
    public void testLuuDocFile() {
        model.themTraiCay(traiCay1);
        model.themTraiCay(traiCay2);
        model.themTraiCay(traiCay3);
        model.ghiFile("fileTraiCay.txt", model.layDanhSach());
        ArrayList<TraiCay> actual = model.layDanhSach();
        ArrayList<TraiCay> expect = new ArrayList<>();
        expect.add(traiCay1);
        expect.add(traiCay2);
        expect.add(traiCay3);
        assertEquals(expect.toString(), actual.toString());
    }
}
