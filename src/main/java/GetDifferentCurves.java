import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 * 类名称: GetDifferentCurves
 * 类描述: 将数据处理操作封装为一个类共主类调用

 * 作者: LTW
 * 创建时间: 2023年-07月-25日 16:28
*/
public class GetDifferentCurves{
     /**
      * 方法名称: GeneratedData
      * 方法描述: 做数据插值的函数操作 ,传入文件地址,可以简便用户操作
      * 参数: String path
      * 返回值:
      * 作者: LTW
      * 日期 2023年-07月-25日 16:30
     */
public static void GeneratedData(String path) throws FileNotFoundException {
//            要分析的表数据地址
        String fcell = null; //表第一行第一个数据
        String scell = null;;  //表第一行第二个数据
        double x=1;
//            申明一个二维数组用于接受表每行的空喉半径与汞增量；
        double[][] p;
        OperationalData operationalData =new OperationalData();
        double d =operationalData.DataOperat(1.412,0.722,0,0.06,1);
        System.out.println("测试数据x="+x+"得到结果为：y="+d);
        //创建工作簿对象
    XSSFWorkbook xssfWorkbook = null;
    try {
        xssfWorkbook = new XSSFWorkbook(new FileInputStream(path));
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
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
        p=new double[maxRow+1][2];
        for (int row = 0; row <= maxRow; row++) {
        //获取最后单元格num，即总单元格数 ***注意：此处从1开始计数***
        int maxRol = sheet.getRow(row).getLastCellNum();
        System.out.println("--------第" + row + "行的数据如下--------");
        for (int rol = 0; rol < maxRol; rol++){
        if(row==0&&rol==0){
        fcell=sheet.getRow(row).getCell(rol).toString();
        } else if (row==0&&rol==1) {
        scell=sheet.getRow(row).getCell(rol).toString();
        }
        if(rol==5||rol==6) {
        p[row][rol - 5] = Double.parseDouble(sheet.getRow(row).getCell(rol).toString());
//                                System.out.print(sheet.getRow(row).getCell(rol) + "  ");
//        System.out.println(p[row][rol-5]);
        }
        }

        }
//        System.out.println("++++++++++++++++++++++++分析后的数据前部++++++++++++++++++++++++++++");
//            处理数据
        //todo 要将给定的孔喉半径存在数组里面
//            声明一个数组用于存放要插值的x值（即孔喉半径）
        double [] list={0.005,0.0075,0.01 ,0.02, 0.05, 0.075, 0.1 ,0.2 ,0.5 ,0.75, 1 ,1.2 ,1.5 ,1.75 ,2, 2.2,2.5, 2.75, 3 ,3.2, 3.5 ,3.75 ,4, 4.2, 4.5 ,4.75, 5 ,5.2 ,5.5 ,5.75, 6 ,6.2 ,6.5, 6.75 ,7, 7.2, 7.5, 7.75, 8 ,8.2 ,8.5 ,8.75, 9, 9.2, 9.5, 9.75, 10 ,12, 15 ,20 ,30 ,40 ,50 ,60 ,70 ,80, 90, 100};
        double max=0;//用于接受p数组中最大的值
        double min=100;//用于接受p数组中最小的值
        List <Double> arrayList=new ArrayList<>();//用于存大于s的所有数据
           /* for (int n=0;n<list.length;n++){
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
            System.out.println(Collections.min(arrayList));*/
        //创建工作簿对象
//            XSSFWorkbook Interpolateddata =new XSSFWorkbook(path);
        Sheet Interpolateddatatable = xssfWorkbook.createSheet("插值数据表") ;
        Row frow = Interpolateddatatable.createRow(0);
        Cell cell0 = frow.createCell(0);
        Cell cell1 = frow.createCell(1);
        cell0.setCellValue("井号");
        cell1.setCellValue("序号");
        for(int i = 0;i < list.length;i++){
        Cell cell=frow.createCell(i+2);
        cell.setCellValue(list[i]);
//        System.out.println(list[i]);
        }

//        System.out.println("++++++++++++++++++++++++分析后的数据后部++++++++++++++++++++++++++++");
//            处理数据
        //todo 要将给定的孔喉半径存在数组里面
        for (int i=0;i<=maxRow;i++) {
        if(p[i][0]>=max){
        max=p[i][0];
        }
        if(p[i][0]<=min){
        min=p[i][0];
        }
        }
        System.out.println("最大最小值");
        System.out.println(max);
        System.out.println(min);
        for (int n=0;n<list.length;n++){
        if(list[n]<min){
        arrayList.add(0.0);
        }
        for (int i=maxRow-1;i>=1;i--) {
        if(p[i][0]<list[n]&&list[n]<=p[i-1][0]) {
        double x1 = p[i][0];
        double x2 = p[i-1][0];
        double y1 = p[i][1];
        double y2 = p[i-1][1];
        arrayList.add(operationalData.DataOperat(x1, x2, y1, y2, list[n]));
        // System.out.println(operationalData.DataOperat(x1, x2, y1, y2, list[n]));
        }
        }
        if(list[n]>max){
        arrayList.add(0.0);
        }
        }
        Row srow = Interpolateddatatable.createRow(1);
        Cell scell0 = srow.createCell(0);
        Cell scell1 = srow.createCell(1);
        scell0.setCellValue(fcell);
        scell1.setCellValue(scell);
        for (int i=0;i<arrayList.size();i++){
        Cell cell = srow.createCell(i+2);
        cell.setCellValue(arrayList.get(i));
//        System.out.println(arrayList.get(i));
        }
//下面给出文件和输出流，然后把excel数据写入
        File file=new File(path);
        if(!file.exists()) {
        try {
        file.createNewFile();
        } catch (IOException e) {
        throw new RuntimeException(e);
        }
        }
        try(OutputStream ops=new FileOutputStream(file)){
        xssfWorkbook.write(ops);
        xssfWorkbook.close();
        //这里关闭Workbook或者关闭OutputStream都可以，应该是Workbook关闭的时候顺带关闭了OutputStream
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

}
}