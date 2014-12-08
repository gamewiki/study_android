设置bar的使用
简单介绍两种样式，默认和条形进度

设置style的时候是以？开始不是@开始

    <!-- 设置style是以？开头不是以@开头的 -->
    <ProgressBar 
        android:id="@+id/bar1"
        style="?android:attr/progressBarStyleHorizontal"
        android:layout_width="200dp"
        android:layout_height="wrap_content"
        android:visibility="gone"/>
        
        
进度调整：

        button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(i==0){
					bar1.setVisibility(ProgressBar.VISIBLE);
					bar2.setVisibility(ProgressBar.VISIBLE);
				}else if(i<bar1.getMax()){
					bar1.setProgress(i);
					bar1.setSecondaryProgress(i+10);
					bar2.setProgress(i);
				}else{
					bar1.setVisibility(ProgressBar.GONE);
					bar2.setVisibility(ProgressBar.GONE);
				}
				i = i+10;
			}
		});