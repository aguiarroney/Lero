
package lero.com.androidstudio.lero;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends FragmentActivity {

    private static final int RC_SIGN_IN = 9000;
    private CallbackManager mCallbackManager;
    private LoginButton botaoLoginFB ;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private SignInButton botaoLoginGoogle ;
    private GoogleApiClient mGoogleApiClient;
    private GoogleSignInOptions googleSignInOptions;
    private boolean autenticacaoFacebook = true;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();

        mGoogleApiClient = new GoogleApiClient.Builder(MainActivity.this).enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener(){
            @Override
            public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                Toast.makeText(MainActivity.this, "falhou botao ", Toast.LENGTH_SHORT).show();
            }


        }).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();

        botaoLoginGoogle = (SignInButton) findViewById(R.id.botaoLoginGoogle);

        botaoLoginGoogle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.botaoLoginGoogle:
                        autenticacaoFacebook = false;
                        signIn();
                        break;
                }
            }
        });

        mCallbackManager = CallbackManager.Factory.create();
        botaoLoginFB = (LoginButton) findViewById(R.id.botaoLoginFBID);
        botaoLoginFB.setReadPermissions("email", "public_profile");



        botaoLoginFB.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("Mensagem de sucesso \n", "facebook:onSuccess:" + loginResult);
                //Toast.makeText(MainActivity.this, "DEU BUENO", Toast.LENGTH_SHORT).show();
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                //Toast.makeText(MainActivity.this, "NO DEU BUENO", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                //Toast.makeText(MainActivity.this, "CANCELOU", Toast.LENGTH_SHORT).show();
            }
        });


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("Usuario logado\n", "onAuthStateChanged:signed_in:" + user.getUid());
                    proximaTela(user);
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

        if(autenticacaoFacebook){
            mCallbackManager.onActivityResult(requestCode, resultCode, data);
        }else{
            if(requestCode == RC_SIGN_IN){

                GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                Toast.makeText(MainActivity.this, "opa"+result, Toast.LENGTH_LONG).show();
                handleSignInResult(result);


            }else{
                Toast.makeText(MainActivity.this, "DEU RUIM na FUNÇÃO ONACTIVITYRESULT", Toast.LENGTH_SHORT).show();
            }
        }

    }


    private void handleSignInResult(GoogleSignInResult result) {

        Log.d("handle google\n", "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            GoogleSignInAccount acct = result.getSignInAccount();

            fireBaseAuthWithGoogle(acct);

        } else {
            Status status = result.getStatus();
            Toast.makeText(MainActivity.this, status.toString() , Toast.LENGTH_SHORT).show();
        }
    }

    private void fireBaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d("tag", "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("tag2", "signInWithCredential:onComplete:" + task.isSuccessful());

                        if (!task.isSuccessful()) {
                            Log.w("tag3", "signInWithCredential", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }


    private void handleFacebookAccessToken(AccessToken token) {
        Log.d("HANDLE\n", "handleFacebookAccessToken:" + token);

        //Toast.makeText(MainActivity.this, "entrou", Toast.LENGTH_SHORT).show();


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

    private void signIn(){
        Intent signIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signIntent, RC_SIGN_IN);
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

    public void proximaTela(FirebaseUser user){
        Intent i = new Intent(MainActivity.this, EscolherPerfil.class);

        String uidAtual =  user.getUid();
        Bundle bundle = new Bundle();
        bundle.putString("UID",uidAtual);
        i.putExtras(bundle);
        startActivity(i);

    }

}




