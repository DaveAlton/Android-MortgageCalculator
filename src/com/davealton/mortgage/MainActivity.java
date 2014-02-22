package com.davealton.mortgage;

import com.davealton.tipcalculator.R;

import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	private static final String LOAN_TOTAL = "Loan_TOTAL";
	private static final String CUSTOM_TERM = "CUSTOM_PERCENT";
	private double currentLoanTotal; // Loan amount entered by the user
	private int currentCustomPercent; // monthPay % set with the SeekBar
	private EditText monthPay10EditText; // displays 10% monthPay
	private EditText total10EditText; // displays total with 10% monthPay
	private EditText monthPay15EditText; // displays 15% monthPay
	private EditText total15EditText; // displays total with 15% monthPay
	private EditText purchasePriceEditText; // accepts user input for Loan total
	private EditText monthPay20EditText; // displays 20% monthPay
	private EditText total20EditText; // displays total with 20% monthPay
	private TextView custommonthPayTextView; // displays custom monthPay percentage
	private EditText monthPayCustomEditText; // displays custom monthPay amount
	private EditText totalCustomEditText; // displays total with custom monthPay
	private SeekBar customSeekBar; // used to generate custom monthPay percentage
	private EditText downPaymentEditText;
	private EditText interestRateEditText;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); // call superclass's version
		setContentView(R.layout.activity_main); // inflate the GUI
		// check if app just started or is being restored from memory
		if (savedInstanceState == null) // the app just started running
		{
			currentLoanTotal = 0.0; // initialize the Loan amount to zero
			currentCustomPercent = 18; // initialize the custom monthPay to 18%
		} // end if
		else // app is being restored from memory, not executed from scratch
		{
			// initialize the Loan amount to saved amount
			currentLoanTotal = savedInstanceState.getDouble(LOAN_TOTAL);
			// initialize the custom monthPay to saved monthPay percent
			currentCustomPercent = savedInstanceState.getInt(CUSTOM_TERM);
		} // end else
		// get references to the 10%, 15% and 20% monthPay and total EditTexts
		downPaymentEditText = (EditText) findViewById(R.id.downPaymentEditText);
		interestRateEditText = (EditText) findViewById(R.id.interestRateEditText);
		monthPay10EditText = (EditText) findViewById(R.id.tip10EditText);
		total10EditText = (EditText) findViewById(R.id.total10EditText);
		monthPay15EditText = (EditText) findViewById(R.id.tip15EditText);
		total15EditText = (EditText) findViewById(R.id.total15EditText);
		monthPay20EditText = (EditText) findViewById(R.id.tip20EditText);
		total20EditText = (EditText) findViewById(R.id.total20EditText);
		// get the TextView displaying the custom monthPay percentage
		custommonthPayTextView = (TextView) findViewById(R.id.customTipTextView);
		// get the custom monthPay and total EditTexts
		monthPayCustomEditText = (EditText) findViewById(R.id.tipCustomEditText);
		totalCustomEditText = (EditText) findViewById(R.id.totalCustomEditText);
		// get the LoanEditText
		purchasePriceEditText = (EditText) findViewById(R.id.billEditText);
		
		// LoanEditTextWatcher handles LoanEditText's onTextChanged event
		purchasePriceEditText.addTextChangedListener(new TextWatcher() {
			// called when the user enters a number
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// convert LoanEditText's text to a double
				try {
					currentLoanTotal = Double.parseDouble(s.toString());
				} // end try
				catch (NumberFormatException e) {
					currentLoanTotal = 0.0; // default if an exception occurs
				} // end catch
				// update the standard and custom monthPay EditTexts
				updateStandard(); // update the 10, 15 and 20% EditTexts
				updateCustom(); // update the custom monthPay EditTexts
			} // end method onTextChanged
			@Override
			public void afterTextChanged(Editable s) {
			} // end method afterTextChanged
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			} // end method beforeTextChanged
		}); // end LoanEditTextWatcher
		
		interestRateEditText.addTextChangedListener(new TextWatcher() {
			// called when the user enters a number
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// convert LoanEditText's text to a double
				try {
					currentLoanTotal = Double.parseDouble(s.toString());
				} // end try
				catch (NumberFormatException e) {
					currentLoanTotal = 0.0; // default if an exception occurs
				} // end catch
				// update the standard and custom monthPay EditTexts
				updateStandard(); // update the 10, 15 and 20% EditTexts
				updateCustom(); // update the custom monthPay EditTexts
			} // end method onTextChanged
			@Override
			public void afterTextChanged(Editable s) {
			} // end method afterTextChanged
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			} // end method beforeTextChanged
		}); // end LoanEditTextWatcher
		
		downPaymentEditText.addTextChangedListener(new TextWatcher() {
			// called when the user enters a number
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// convert LoanEditText's text to a double
				try {
					currentLoanTotal = Double.parseDouble(s.toString());
				} // end try
				catch (NumberFormatException e) {
					currentLoanTotal = 0.0; // default if an exception occurs
				} // end catch
				// update the standard and custom monthPay EditTexts
				updateStandard(); // update the 10, 15 and 20% EditTexts
				updateCustom(); // update the custom monthPay EditTexts
			} // end method onTextChanged
			@Override
			public void afterTextChanged(Editable s) {
			} // end method afterTextChanged
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			} // end method beforeTextChanged
		}); // end LoanEditTextWatcher
		
		// get the SeekBar used to set the custom monthPay amount
		customSeekBar = (SeekBar) findViewById(R.id.customSeekBar);
		customSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
		// update currentCustomPercent, then call updateCustom
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
			// sets currentCustomPercent to position of the SeekBar's thumb
			currentCustomPercent = seekBar.getProgress();
			updateCustom(); // update EditTexts for custom monthPay and total
			} // end method onProgressChanged
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			} // end method onStartTrackingTouch
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			} // end method onStopTrackingTouch
		}); // end OnSeekBarChangeListener
	}
	
	// save values of LoanEditText and customSeekBar
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putDouble(LOAN_TOTAL, currentLoanTotal);
		outState.putInt(CUSTOM_TERM, currentCustomPercent);
	} // end method onSaveInstanceState
	protected void updateStandard(){
		int principal = 0;
		int purchasePrice = Integer.parseInt(purchasePriceEditText.getText().toString());
		int downPayment = Integer.parseInt(downPaymentEditText.getText().toString());
		int interestRate = Integer.parseInt(interestRateEditText.getText().toString());
		principal = purchasePrice - downPayment;
		monthPay10EditText.setText(Double.toString(principal*(interestRate/100)/(1-Math.pow(1/(1+interestRate),10))));
				//where term is the number of years
		
		
	}
	protected void updateCustom(){

		
		
	}
	
}
