设置radio的使用和checkbox的使用

		radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(radioman.getId()==checkedId){
					text.setText("男");
					Toast.makeText(Radio.this, "I am man", Toast.LENGTH_SHORT).show();
				}else if(radiowoman.getId()==checkedId){
					text.setText("女");
					Toast.makeText(Radio.this, "I am woman", Toast.LENGTH_SHORT).show();
				}
			}
		});
		

        swim.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					text.setText("swim is onchecked");
					Toast.makeText(Radio.this, "I am swimming", Toast.LENGTH_SHORT).show();
				}else{
					text.setText("swim is not onchecked");
				}
			}
		});