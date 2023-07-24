import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ReadExcel {

    public static void main(String[] args) {
        try {
            int k=0;
            double x=1;
//            申明一个二维数组用于接受表每行的空喉半径与汞增量；
            double[][] p;
            OperationalData operationalData =new OperationalData();
            double d =operationalData.DataOperat(1.412,0.722,0,0.06,1);
            System.out.println("测试数据x="+x+"得到结果为：y="+d);
            //创建工作簿对象
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream("E:\\IDEA.2022.3.2\\Workplace\\算法练习\\DateProcess\\src\\main\\resources\\DataList\\WC8-3N-1(1-5).xlsx"));
            //获取工作簿下sheet的个数
            int sheetNum = xssfWorkbook.getNumberOfSheets();
            System.out.println("该excel文件中总共有："+sheetNum+"个sheet");
            //遍历工作簿中的所有数据
//            for(int i = 0;i<sheetNum;i++) {
//                //读取第i个工作表
          System.out.println("读取第1个sheet");
                XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
                //获取最后一行的num，即总行数。此处从0开始
                int maxRow = sheet.getLastRowNum();
            System.out.println(maxRow);
//                初始化一个数组
                p=new double[maxRow+2][2];
                for (int row = 0; row <= maxRow; row++) {
                    //获取最后单元格num，即总单元格数 ***注意：此处从1开始计数***
                    int maxRol = sheet.getRow(row).getLastCellNum();
                    System.out.println("--------第" + row + "行的数据如下--------");
                      for (int rol = 0; rol < maxRol; rol++){

                            if(rol==5||rol==6) {
                                p[row+1][rol - 5] = Double.parseDouble(sheet.getRow(row).getCell(rol).toString());
//                                System.out.print(sheet.getRow(row).getCell(rol) + "  ");
                                System.out.println(p[row][rol-5]);
                                }
                              }

                }
            System.out.println("++++++++++++++++++++++++分析后的数据前部++++++++++++++++++++++++++++");
//            处理数据
            //todo 要将给定的孔喉半径存在数组里面
//            声明一个数组用于存放要插值的x值（即孔喉半径）
            double [] list={0.005,0.0075,0.01 ,0.02, 0.05, 0.075, 0.1 ,0.2 ,0.5 ,0.75, 1 ,1.2 ,1.5 ,1.75 ,2, 2.2,2.5, 2.75, 3 ,3.2, 3.5 ,3.75 ,4, 4.2, 4.5 ,4.75, 5 ,5.2 ,5.5 ,5.75, 6 ,6.2 ,6.5, 6.75 ,7, 7.2, 7.5, 7.75, 8 ,8.2 ,8.5 ,8.75, 9, 9.2, 9.5, 9.75, 10 ,12, 15 ,20 ,30 ,40 ,50 ,60 ,70 ,80, 90, 100};
            double s=0;//用于接受p数组中最大的值
            List <Double> arrayList=new ArrayList<>();//用于存大于s的所有数据
            for (int n=0;n<list.length;n++){
                  for (int i=maxRow;i>=1;i--) {
                      if(p[i][0]>=s){
                          s=p[i][0];
                      }
                    if(p[i][0]<=list[n]&&list[n]<=p[i-1][0]) {
                        double x1 = p[i][0];
                        double x2 = p[i-1][0];
                        double y1 = p[i][1];
                        double y2 = p[i-1][1];
                       System.out.println(list[n]);
                    }
                  }
                  if(list[n]>s){
                     arrayList.add(list[n]);
                  }
                }
            System.out.println(Collections.min(arrayList));
            System.out.println("++++++++++++++++++++++++分析后的数据后部++++++++++++++++++++++++++++");
//            处理数据
            //todo 要将给定的孔喉半径存在数组里面
//            声明一个数组用于存放要插值的x值（即孔喉半径）
            for (int n=0;n<list.length;n++){
                for (int i=maxRow;i>=1;i--) {
                    if(p[i][0]<=list[n]&&list[n]<=p[i-1][0]) {
                        double x1 = p[i][0];
                        double x2 = p[i-1][0];
                        double y1 = p[i][1];
                        double y2 = p[i-1][1];
                        System.out.println(operationalData.DataOperat(x1, x2, y1, y2, list[n]));
                    }
                }
            }
            System.out.println(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
