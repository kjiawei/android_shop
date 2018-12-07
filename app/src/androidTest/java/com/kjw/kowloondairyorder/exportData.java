public void ExportToCSV(Cursor c, String fileName) {
 
    int rowCount = 0;
    int colCount = 0;
    FileWriter fw;
    BufferedWriter bfw;
    File sdCardDir = Environment.getExternalStorageDirectory();
    File saveFile = new File(sdCardDir, fileName);
    try {

        rowCount = c.getCount();//c为传入的游标
        colCount = c.getColumnCount();
        fw = new FileWriter(saveFile);
        bfw = new BufferedWriter(fw);//用于保存将要写到CSV的数据
        if (rowCount > 0) {
            c.moveToFirst();
            // 写入表头
            for (int i = 0; i < colCount; i++) {
                if (i != colCount - 1)
                   bfw.write(c.getColumnName(i) + ',');
                else
                   bfw.write(c.getColumnName(i));
            }
            bfw.newLine();// 写好表头后换行
            // 写入数据
            for (int i = 0; i < rowCount; i++) {
                c.moveToPosition(i);
                // Toast.makeText(mContext, "正在导出第"+(i+1)+"条",
                // Toast.LENGTH_SHORT).show();
                Log.v("导出数据", "正在导出第" + (i + 1) + "条");
                for (int j = 0; j < colCount; j++) {
                    if (j != colCount - 1)
                        bfw.write(c.getString(j) + ',');
                    else
                       bfw.write(c.getString(j));
                }
                // 写好每条记录后换行
                bfw.newLine();
            }
        }
        // 将缓存数据写入文件
        bfw.flush();
        // 释放缓存
        bfw.close();
        // Toast.makeText(mContext, "导出完毕！", Toast.LENGTH_SHORT).show();
        Log.v("导出数据", "导出完毕！");
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } finally {
        c.close();
    }
}
