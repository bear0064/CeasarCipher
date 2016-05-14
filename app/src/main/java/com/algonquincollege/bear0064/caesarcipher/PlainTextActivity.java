package com.algonquincollege.bear0064.caesarcipher;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.CharacterPickerDialog;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import util.CaesarCipher;
import static com.algonquincollege.bear0064.caesarcipher.Constants.*;

/**
 *  Encrypt/Decrypt text using ROT13 encryption
 *  @author Caleb Bear bear0064@algonquinlive.com
 */

public class PlainTextActivity extends AppCompatActivity {

    private DialogFragment          mAboutDialog;
    private EditText                mPlainText;
    private int                     mRotation;
    private CharacterPickerDialog   mRotationDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plain_text);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              String plainText = mPlainText.getText().toString();
                Intent intent = new Intent(getApplicationContext(), EncryptedTextActivity.class);
                intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
                intent.putExtra( THE_MESSAGE, plainText);
                intent.putExtra( THE_ROTATION, mRotation);
                startActivity (intent);

            }
        });



        mAboutDialog = new AboutDialogFragment();
        //default rotation
        mRotation = ROT13;

        mRotationDialog = new CharacterPickerDialog(this, new View(this), null, ROTATIONS, false ){
            @Override
            public void onClick(View v){
                int index = ROTATIONS.indexOf( ((Button)v).getText().toString() );
                if ( index >= 0 ) {

                    mRotation = index;
                }
                dismiss();
            }
        };
//findViewById( R.id.etPlain)
        Button clearButton = (Button) findViewById(R.id.bClearPlain) ;
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlainText.setText( "" );
            }
        });

        mPlainText = (EditText) findViewById( R.id.etPlain);
        mPlainText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //No-Op
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
                //No-Op
            @Override
            public void afterTextChanged(Editable s) {
                fab.setEnabled( s.length() !=0);
            }
        });
            //get the bundle of extras that was sent to this activity
        Bundle bundle = getIntent().getExtras();
            if (bundle != null){
                String encryptedMessage = bundle.getString( THE_MESSAGE );
                int rotation = bundle.getInt( THE_ROTATION );
                if (DEBUG) {

                    Log.i(LOG_TAG, "DECRYPTING Encrypted Message (rotation: " + rotation + "): " + encryptedMessage);
                }
                mPlainText.setText( CaesarCipher.decrypt( encryptedMessage, rotation ) );
            }

        //init
        fab.setEnabled( mPlainText.length() !=0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            mAboutDialog.show( getFragmentManager(), ABOUT_DIALOG_TAG );
            return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            mRotationDialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
