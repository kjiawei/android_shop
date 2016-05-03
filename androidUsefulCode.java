一去除标题
requestWindowFeature(Window.FEATURE_NO_TITLE);//放在setContentView前

二GPS
//创建LocationManager对象
    lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
    //从GPS获取最近的定位信息
    Location lc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    //更新显示定位信息
    updateView(lc);
    //设置每3秒 获取一次GPS定位信息
    lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 8, new LocationListener() {
      @Override
      public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub
      }
      @Override
      public void onProviderEnabled(String provider) {
        // 当GPS LocationProvider可用时，更新定位
        updateView(lm.getLastKnownLocation(provider));
      }
      @Override
      public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub
        updateView(null);
      }
      @Override
      public void onLocationChanged(Location location) {
        // 当GPS定位信息发生改变时，更新定位
        updateView(location);
      }
    });
  }
  public void updateView(Location newLocation){
    if (newLocation != null) {
      StringBuilder sb = new StringBuilder();
      sb.append("实时位置信息：\n");
      sb.append("经度：\n");
      sb.append(newLocation.getLongitude());
      sb.append("\n纬度：");
      sb.append(newLocation.getLatitude());
      sb.append("\n高度：");
      sb.append(newLocation.getAltitude());
      sb.append("\n速度：");
      sb.append(newLocation.getSpeed());
      sb.append("\n方向：");
      sb.append(newLocation.getBearing());
      sb.append("\n定位精度：");
      sb.append(newLocation.getAccuracy());
      show.setText(sb.toString());
    } else {
      show.setText(null);
    }
  }
}



< uses-permission  android:name= "android.permission.ACCESS_FINE_LOCATION" />
