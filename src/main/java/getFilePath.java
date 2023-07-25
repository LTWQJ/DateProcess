import java.io.File;
import java.util.ArrayList;
import java.util.List;
/**
 * 类名称: getFilePath
 * 类描述: 获取文件路径类

 * 作者: LTW
 * 创建时间: 2023年-07月-25日 16:06
*/
public class getFilePath {
     /**
      * 方法名称: getFilePath
      * 方法描述: 获取文件路径的类方法
      * 参数:
      * 返回值:
      * 作者: LTW
      * 日期 2023年-07月-25日 16:07
     */
    public static List<String> getFilePath(String folderPath) {
        File folder = new File(folderPath);
        List<String> filePathList = new ArrayList<>();
        String rootPath;
        if (folder.exists()) {
            String[] fileNameList = folder.list();
            if (null != fileNameList && fileNameList.length > 0) {
                if (folder.getPath().endsWith(File.separator)) {
                    rootPath = folder.getPath();
                } else {
                    rootPath = folder.getPath() + File.separator;
                }
                for (String fileName : fileNameList) {
                    filePathList.add(rootPath + fileName);
                }
            }
        }
        return filePathList;
    }
}
