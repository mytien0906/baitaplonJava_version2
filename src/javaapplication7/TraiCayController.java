/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication7;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class TraiCayController {

    TraiCayView view;
    TraiCayModel model;

    public TraiCayController(TraiCayView view, TraiCayModel model) {
        this.view = view;
        this.model = model;
        showTable(model.layDanhSach());
        view.getJButtonXoa().setEnabled(false);
        view.getJButtonSua().setEnabled(false);
        view.getJButtonGhiFile().setEnabled(false);
        view.getJButtonDocFile().setEnabled(true);
        view.getJButtonThem().addActionListener(themTraiCay());
        view.getJButtonSua().addActionListener(suaTraiCay());
        view.getJButtonXoa().addActionListener(xoaTraiCay());
        view.getSapXepTen().addActionListener(sapXepTheoTen());
        view.getSapXepTenVaGia().addActionListener(sapXepTheoTenVaGia());
        view.getJButtonTim().addActionListener(timKiemTheoMa());
        view.getJButtonGhiFile().addActionListener(luuFile());
        view.getJButtonDocFile().addActionListener(docFile());

        view.getTableTraiCay().addMouseListener(clickItemTable());
        view.setVisible(true);
    }

    /*
    Câu 1: Lấy danh sách tất cả trái cây
     */
    public void showTable(ArrayList<TraiCay> danhSach) {
        DefaultTableModel tableModel = (DefaultTableModel) view.getTableTraiCay().getModel();
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);
        tableModel.addColumn("Mã trái cây");
        tableModel.addColumn("Tên trái cây");
        tableModel.addColumn("Số lượng");
        tableModel.addColumn("Giá bán");
        DecimalFormat format = new DecimalFormat();
        for (TraiCay traiCay : danhSach) {
            tableModel.addRow(new Object[]{
                traiCay.getMaTraiCay(), traiCay.getTenTraiCay(), traiCay.getSoLuong(), format.format(traiCay.getGiaBan())
            });
        }
        view.getTableTraiCay().setModel(tableModel);
    }

    public void resetText() {

        view.getTextFieldSoLuong().setText(null);
        view.getTextFieldMaTraiCay().setText(null);
        view.getTextFieldTenTraiCay().setText(null);
        view.getTextFieldTimTheoMa().setText(null);
        view.getTextFieldGiaBan().setText(null);
        view.getTextFieldTimTheoMa().setText(null);
        view.getJButtonThem().setEnabled(true);
        view.getJButtonTim().setEnabled(true);
        view.getJButtonXoa().setEnabled(true);
        view.getJButtonSua().setEnabled(true);
        view.getJButtonDocFile().setEnabled(true);
        view.getJButtonGhiFile().setEnabled(true);
        if (model.countList() == 0) {
            view.getJButtonThem().setEnabled(true);
            view.getJButtonTim().setEnabled(true);
            view.getJButtonXoa().setEnabled(false);
            view.getJButtonSua().setEnabled(false);
            view.getJButtonDocFile().setEnabled(false);
            view.getJButtonGhiFile().setEnabled(false);
        }
    }

    /*
    Câu 2: Thêm trái cây
     */
    private ActionListener themTraiCay() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    TraiCay traiCay = new TraiCay(view.getTextFieldMaTraiCay().getText(), view.getTextFieldTenTraiCay().getText(), Integer.parseInt(view.getTextFieldSoLuong().getText()), Double.parseDouble(view.getTextFieldGiaBan().getText()));
                    model.themTraiCay(traiCay);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, ex.toString());
                }
                resetText();
                showTable(model.layDanhSach());
            }
        };
    }

    /*
    Câu 3: Xóa trái cây
     */
    private ActionListener xoaTraiCay() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TraiCay traiCay = new TraiCay(view.getTextFieldMaTraiCay().getText(), view.getTextFieldTenTraiCay().getText(), Integer.parseInt(view.getTextFieldSoLuong().getText()), Double.parseDouble(view.getTextFieldGiaBan().getText()));
                String maTC = view.getTextFieldMaTraiCay().getText();
                if (!(maTC.equals(""))) {
                    int confirm = JOptionPane.showConfirmDialog(view,
                            "Bạn muốn xóa trái cây có mã: '" + maTC + "' ?");
                    if (confirm == 0) {
                        try {
                            model.xoaTraiCay(traiCay);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(view, ex.toString());
                        }
                    }
                }
                resetText();
                showTable(model.layDanhSach());
            }
        };
    }

    /*
    4. Sửa thông tin trái cây
     */
    private ActionListener suaTraiCay() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    TraiCay traiCay = new TraiCay(view.getTextFieldMaTraiCay().getText(), view.getTextFieldTenTraiCay().getText(), Integer.parseInt(view.getTextFieldSoLuong().getText()), Double.parseDouble(view.getTextFieldGiaBan().getText()));
                    model.suaTraiCay(traiCay);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, ex.toString());
                }
                resetText();
                showTable(model.layDanhSach());
            }
        };
    }
    /*
    5. Sắp xếp danh sách theo tên tăng dần
     */
    private int clickCount = 0;

    private ActionListener sapXepTheoTen() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (view.getSapXepTen().isSelected()) {
                        resetText();
                        model.sapXepTenTangDan();
                        showTable(model.sapXepTenTangDan());
                        if (++clickCount % 2 == 0) {
                            view.getButtonGroup().clearSelection();
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, ex.toString());
                }
            }
        };
    }

    /*
    6. Sắp xếp danh sách với 2 tiêu chí: tên tăng dần và giá tăng dần
     */
    private ActionListener sapXepTheoTenVaGia() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (view.getSapXepTenVaGia().isSelected()) {
                        resetText();
                        model.sapXepTheoTenRoiSapXepGia();
                        showTable(model.sapXepTheoTenRoiSapXepGia());
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, ex.toString());
                }
            }
        };
    }

    /*
    7. Tìm kiếm trái cây theo từ khóa gần đúng
     */
    private ActionListener timKiemTheoMa() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tuKhoaTimKiem = view.getTextFieldTimTheoMa().getText();
                if (!(tuKhoaTimKiem.equals(""))) {
                    try {
//                        model.timKiemMaTraiCay(tuKhoaTimKiem);
                        if (model.timKiemMaTraiCay(tuKhoaTimKiem).size() == 0) {
                            JOptionPane.showMessageDialog(view, "Không tìm thấy từ khóa của bạn! Vui lòng nhập từ khác");
                            resetText();
                        } else {
                            model.timKiemMaTraiCay(tuKhoaTimKiem);
                            showTable(model.timKiemMaTraiCay(tuKhoaTimKiem));
                            resetText();
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(view, ex.toString());
                    }
                } else {
                    JOptionPane.showMessageDialog(view, "Bạn chưa nhập từ khóa để tìm kiếm");
                }
            }
        };
    }

    /*
    8. Lưu danh sách trái cây xuống file
     */
    private ActionListener luuFile() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(view, "Bạn muốn lưu danh sách trái cây ?", "", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    if (model.ghiFile("traicay.txt", model.layDanhSach())) {
                        JOptionPane.showMessageDialog(view, "Lưu danh sách thành công");
                    } else {
                        JOptionPane.showMessageDialog(view, "Lưu thất bại");
                    }
                }
            }
        };
    }

    /*
    9. Đọc danh sách trái cây từ file
     */
    private ActionListener docFile() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    model.docFile("traicay.txt");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, ex.toString());
                }
                showTable(model.layDanhSach());
            }
        };
    }
    public MouseAdapter clickItemTable() {
        return new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int dong = view.getTableTraiCay().getSelectedRow();
                view.getTextFieldMaTraiCay().setText(view.getTableTraiCay().getValueAt(dong, 0).toString());
                view.getTextFieldTenTraiCay().setText(view.getTableTraiCay().getValueAt(dong, 1).toString());
                view.getTextFieldSoLuong().setText(String.valueOf(view.getTableTraiCay().getValueAt(dong, 2).toString()));
                view.getTextFieldGiaBan().setText(String.valueOf(view.getTableTraiCay().getValueAt(dong, 3).toString()));
                view.getJButtonThem().setEnabled(false);
                view.getJButtonSua().setEnabled(true);
                view.getJButtonXoa().setEnabled(true);
                view.getJButtonTim().setEnabled(true);
                view.getJButtonDocFile().setEnabled(true);
                view.getJButtonGhiFile().setEnabled(true);
            }
        };
    }
}
