package com.sid.datapicker;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

public class DataPickerActivity extends Activity {
	private Button showDateButton = null;
	private Button showTimeButton = null;
	private AutoCompleteTextView autoText = null;
	private static final int DATE_PICKER_ID = 1;
	private static final int TIME_PICKER_ID = 2;
	static final String[] COUNTRIES = new String[] {
		  "Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra",
		  "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina",
		  "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan",
		  "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium",
		  "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia",
		  "Bosnia and Herzegovina", "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory",
		  "British Virgin Islands", "Brunei", "Bulgaria", "Burkina Faso", "Burundi",
		  "Cote d'Ivoire", "Cambodia", "Cameroon", "Canada", "Cape Verde",
		  "Cayman Islands", "Central African Republic", "Chad", "Chile", "China",
		  "Christmas Island", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo",
		  "Cook Islands", "Costa Rica", "Croatia", "Cuba", "Cyprus", "Czech Republic",
		  "Democratic Republic of the Congo", "Denmark", "Djibouti", "Dominica", "Dominican Republic",
		  "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea",
		  "Estonia", "Ethiopia", "Faeroe Islands", "Falkland Islands", "Fiji", "Finland",
		  "Former Yugoslav Republic of Macedonia", "France", "French Guiana", "French Polynesia",
		  "French Southern Territories", "Gabon", "Georgia", "Germany", "Ghana", "Gibraltar",
		  "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea", "Guinea-Bissau",
		  "Guyana", "Haiti", "Heard Island and McDonald Islands", "Honduras", "Hong Kong", "Hungary",
		  "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Israel", "Italy", "Jamaica",
		  "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Kuwait", "Kyrgyzstan", "Laos",
		  "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg",
		  "Macau", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands",
		  "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico", "Micronesia", "Moldova",
		  "Monaco", "Mongolia", "Montserrat", "Morocco", "Mozambique", "Myanmar", "Namibia",
		  "Nauru", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand",
		  "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "North Korea", "Northern Marianas",
		  "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru",
		  "Philippines", "Pitcairn Islands", "Poland", "Portugal", "Puerto Rico", "Qatar",
		  "Reunion", "Romania", "Russia", "Rwanda", "Sqo Tome and Principe", "Saint Helena",
		  "Saint Kitts and Nevis", "Saint Lucia", "Saint Pierre and Miquelon",
		  "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Saudi Arabia", "Senegal",
		  "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands",
		  "Somalia", "South Africa", "South Georgia and the South Sandwich Islands", "South Korea",
		  "Spain", "Sri Lanka", "Sudan", "Suriname", "Svalbard and Jan Mayen", "Swaziland", "Sweden",
		  "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "The Bahamas",
		  "The Gambia", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey",
		  "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Virgin Islands", "Uganda",
		  "Ukraine", "United Arab Emirates", "United Kingdom",
		  "United States", "United States Minor Outlying Islands", "Uruguay", "Uzbekistan",
		  "Vanuatu", "Vatican City", "Venezuela", "Vietnam", "Wallis and Futuna", "Western Sahara",
		  "Yemen", "Yugoslavia", "Zambia", "Zimbabwe"
		};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_picker);
        showDateButton = (Button)findViewById(R.id.showDateButton);
        showDateButton.setOnClickListener(new ShowDateButton());
        showTimeButton = (Button)findViewById(R.id.showTimeButton);
        showTimeButton.setOnClickListener(new ShowTimeButton());
        
        //输入两个或两个以上的字符才会进行提示
        autoText = (AutoCompleteTextView)findViewById(R.id.autoTextId);
        List<String> list = new ArrayList<String>();
        list.add("abce");
        list.add("test");
        //list也可以换成字符串数组
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
        									this, 
        									R.layout.list_item, 
        									list);
        autoText.setAdapter(arrayAdapter);
        
    }
    
    class ShowDateButton implements OnClickListener{
		@Override
		public void onClick(View v) {
			showDialog(DATE_PICKER_ID);
		}
    }
    
    class ShowTimeButton implements OnClickListener{
		@Override
		public void onClick(View v) {
			showDialog(TIME_PICKER_ID);
		}
    }

    //监听器；用户监听用户点击datepickerDialog的set按钮时所设置的日期信息
    //月份是从0开始计算的
    DatePickerDialog.OnDateSetListener onDateSetListener = 
    		new DatePickerDialog.OnDateSetListener() {
				@Override
				public void onDateSet(DatePicker view, int year, int monthOfYear,
						int dayOfMonth) {
					System.out.println(year+"-"+monthOfYear+"-"+dayOfMonth);
				}
			};
			
	TimePickerDialog.OnTimeSetListener onTimeSetListener =
				    new TimePickerDialog.OnTimeSetListener() {
				        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
							System.out.println(hourOfDay+":"+minute);
				        }
				    };
    
    
    @Override
    protected Dialog onCreateDialog(int id) {
    	switch(id){
    	case DATE_PICKER_ID:
    		return new DatePickerDialog(this, 
    				onDateSetListener, 
    				new Date().getYear(), 
    				new Date().getMonth(), 
    				new Date().getDay());
    	case TIME_PICKER_ID:
    		return new TimePickerDialog(this,
    				onTimeSetListener, 
    				new Date().getHours(), 
    				new Date().getMinutes(), true);
    	}
    	
    	return null;
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_data_picker, menu);
        return true;
    }
}
