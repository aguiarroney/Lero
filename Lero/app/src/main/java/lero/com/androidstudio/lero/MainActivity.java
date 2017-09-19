
package lero.com.androidstudio.lero;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;



public class MainActivity extends Activity {

    private CallbackManager mCallbackManager;
    private LoginButton botaoLoginFB;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();



        /*botaoLoginFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, EscolherPerfil.class);
                startActivity(i);
            }
        });*/

        Toast.makeText(MainActivity.this, "DEU BUENO ANTES", Toast.LENGTH_SHORT).show();

        mCallbackManager = CallbackManager.Factory.create();
        botaoLoginFB = (LoginButton) findViewById(R.id.botaoLoginFBID);
        botaoLoginFB.setReadPermissions("email", "public_profile");








        botaoLoginFB.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {





            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("bao dms \n", "facebook:onSuccess:" + loginResult);
                Toast.makeText(MainActivity.this, "DEU BUENO", Toast.LENGTH_SHORT).show();
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Toast.makeText(MainActivity.this, "NO DEU BUENO", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(MainActivity.this, "CANCELOU", Toast.LENGTH_SHORT).show();
            }
        });


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("Usuario logado\n", "onAuthStateChanged:signed_in:" + user.getUid());
                    proximaTela();
                } else {
                    // User is signed out
                    Log.d("Usuario deslogado\n", "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }


    private void handleFacebookAccessToken(AccessToken token) {
        Log.d("HANDLE\n", "handleFacebookAccessToken:" + token);

        Toast.makeText(MainActivity.this, "entrooooooou", Toast.LENGTH_SHORT).show();


        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("ON COMPLETE HANDLE \n", "signInWithCredential:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w("SUCESSFULL HANDLE \n", "signInWithCredential", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void proximaTela(){
        Intent i = new Intent(MainActivity.this, EscolherPerfil.class);
        startActivity(i);
    }

}




