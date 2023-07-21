import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class GetDifferentCurves {
    public List GetDifferentCurves() {
        try {
            int k = 0;
            double x = 1;
//            申明一个二维数组用于接受表每行的空喉半径与汞增量；
            double[][] p;
            //创建工作簿对象
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream("E:\\IDEA.2022.3.2\\Workplace\\DateProcess\\src\\main\\resources\\DataList\\WC8-3N-1(1-5).xlsx"));
            //获取工作簿下sheet的个数
            int sheetNum = xssfWorkbook.getNumberOfSheets();
            System.out.println("该excel文件中总共有：" + sheetNum + "个sheet");
//                //读取第4个工作表
            System.out.println("读取第4个sheet");
            XSSFSheet sheet = xssfWorkbook.getSheetAt(3);
            //获取最后一行的num，即总行数。此处从0开始
            int maxRow = sheet.getLastRowNum();
//                初始化一个数组
            p = new double[maxRow][2];
            for (int row = 1; row <= maxRow; row++) {

                //获取最后单元格num，即总单元格数 ***注意：此处从1开始计数***
                int maxRol = sheet.getRow(row).getLastCellNum();
//                    System.out.println("--------第" + row + "行的数据如下--------");
                for (int rol = 0; rol < maxRol; rol++) {
                    if (rol == 5 || rol == 6) {
                        p[row][rol - 5] = Double.parseDouble(sheet.getRow(row).getCell(rol).toString());
//                                System.out.print(sheet.getRow(row).getCell(rol) + "  ");
                        System.out.println(p[row][rol - 5]);
                    }
                }
//                    根据不同井号，判断不同曲线
                if (sheet.getRow(row + 1).getCell(0) != sheet.getRow(row + 2).getCell(0)) {
                    System.out.println(sheet.getRow(row).getCell(0));
                    System.out.println(sheet.getRow(row + 1).getCell(0));
//                              根据不同序号，判断不同曲线
                    if (sheet.getRow(row + 1).getCell(1) != sheet.getRow(row + 2).getCell(1)) {
                        System.out.println("第" + k++ + "条曲线");
                        System.out.println(sheet.getRow(row).getCell(1));
                        System.out.println(sheet.getRow(row + 1).getCell(1));
                    }
                }
//                    System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
