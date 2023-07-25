/**
 * 类名称: OperationalData
 * 类描述: 数据处理类
         
 * 作者: LTW        
 * 创建时间: 2023年-07月-25日 15:07         
*/
public class OperationalData {
     /**
      * 方法名称: DataOperat
      * 方法描述: 通过两个坐标点,及第三个点的x值算出其对应的y值
      * 参数: double x1, double x2, double y1, double y2, double x
      * 返回值:  
      * 作者: LTW        
      * 日期 2023年-07月-25日 15:25
     */
    public double DataOperat(double x1, double x2, double y1, double y2, double x) {
        double y = 0;
//       连接成三角形的底边为L1；竖边为L2
        double L1 = x2 - x1;
        double L2 = y2 - y1;
        double m = (x - x1) * L2 / L1;
        y = m + y1;
//        double l1 = Math.abs(L1);
//        double l2 = Math.abs(L2);
////        根据上述数据算出给出x点的y属性增量m
//        if (x1 < x2) {
//            double m = (x - x1) * l2 / l1;
//            if (y1 < y2) {
//                y = m + y1;
//            } else {
//                y = m + y2;
//            }
//        } else {
//            double m = (x - x2) * l2 / l1;
//            if (y1 < y2) {
//                y = m + y1;
//            } else {
//                y = m + y2;
//            }
//        }
        return y;
    }
}
