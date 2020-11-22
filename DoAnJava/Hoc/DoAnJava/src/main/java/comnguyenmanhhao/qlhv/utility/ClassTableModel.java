    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comnguyenmanhhao.qlhv.utility;

import com.nguyenmanhhao.qlhv.model.HocVien;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HAO
 */
public class ClassTableModel {

    public DefaultTableModel setTableHocVien(List<HocVien> listItem, String[] listColumn)
    {
        
        DefaultTableModel dtm = new DefaultTableModel() 
        {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 7 ? Boolean.class : String.class;
            }
        };
        dtm.setColumnIdentifiers(listColumn);
        int columns = listColumn.length;
        Object[] obj = null;
        int rows = listItem.size();
        {
            for (int i = 0; i < rows; i++) 
            {
                HocVien hocVien = listItem.get(i); 
                obj = new Object[columns];
                obj[0] = hocVien.getma_hoc_sinh();
                //obj[1] = (i + 1);
                obj[1] = hocVien.getho_ten();
                obj[2] = hocVien.getngay_sinh();
                obj[3] = hocVien.gioi_tinh() == true ? "Nam" : "Nữ";
                obj[4] = hocVien.getso_dien_thoai();
                obj[5] = hocVien.getdia_chi();
                obj[6] = hocVien.tinh_trang();
                dtm.addRow(obj);
            }
            return dtm;
        }
    }
    
}


