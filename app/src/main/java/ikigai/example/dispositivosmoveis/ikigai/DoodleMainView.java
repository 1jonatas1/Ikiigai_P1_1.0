package ikigai.example.dispositivosmoveis.ikigai;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

public class DoodleMainView  extends AppCompatActivity {
    Button btTip;
    Button btStart;
    int color;
    Dialog d,d1,d2,d3;
    Dialog d4;
    private DoodleView doodleView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);
        doodleView = (DoodleView) findViewById(R.id.doodleView);
        Toolbar toolbar = findViewById(R.id.toolBar2);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.BLACK);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        d = gerarDialog();
        d1 = gerarDialog1();
        d2 = gerarDialog2();
        d3 = gerarDialog3();
        d4 = gerarDialog4();
//        c1 = findViewById(R.id.circle1);
//        c2 = findViewById(R.id.circle2);
//        c3 = findViewById(R.id.circle3);
//        c4 =findViewById(R.id.circle4);
//        c1.setOnClickListener(v -> doodleView.);
//        c2.setOnClickListener(v -> d1.dismiss());
//        c3.setOnClickListener(v -> d1.dismiss());
//        c4.setOnClickListener(v -> d1.dismiss());

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.doodle_fragment_menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        //noinspection SimplifiableIfStatement
        else if (id == R.id.C1) {
            d1.show();
        }
        else if (id == R.id.C2) {
            d2.show();
        }
        else if (id == R.id.C3) {
            d3.show();
        }
        else if (id == R.id.C4) {
            d4.show();
        }
        else if (id == R.id.C5) {
            d.show();
        }

        return super.onOptionsItemSelected(item);
    }
    private Dialog gerarDialog(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.fragment_text);
        dialog.setTitle("Selecione a cor");
        EditText ed1 = (EditText)dialog.findViewById(R.id.ed1);
        EditText ed2 = (EditText)dialog.findViewById(R.id.ed2);
        EditText ed3 = (EditText)dialog.findViewById(R.id.ed3);
        EditText ed4 = (EditText)dialog.findViewById(R.id.ed4);
        EditText ed5 = (EditText)dialog.findViewById(R.id.ed5);
        EditText ed6 = (EditText)dialog.findViewById(R.id.ed6);
        EditText ed7 = (EditText)dialog.findViewById(R.id.ed7);
        EditText ed8 = (EditText)dialog.findViewById(R.id.ed8);
        Button buttonOk = (Button) dialog.findViewById(R.id.button2);
        Button buttonCancel = (Button) dialog.findViewById(R.id.button);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ed1.getText().toString().isEmpty()) doodleView.setS1(ed1.getText().toString());
                if(!ed2.getText().toString().isEmpty()) doodleView.setS2(ed2.getText().toString());
                if(!ed3.getText().toString().isEmpty()) doodleView.setS3(ed3.getText().toString());
                if(!ed4.getText().toString().isEmpty()) doodleView.setS4(ed4.getText().toString());
                if(!ed5.getText().toString().isEmpty()) doodleView.setS5(ed5.getText().toString());
                if(!ed6.getText().toString().isEmpty()) doodleView.setS6(ed6.getText().toString());
                if(!ed7.getText().toString().isEmpty()) doodleView.setS7(ed7.getText().toString());
                if(!ed8.getText().toString().isEmpty()) doodleView.setS8(ed8.getText().toString());
                doodleView.invalidate();
                dialog.dismiss();
            }
        });
        return dialog;
    }

    private Dialog gerarDialog1(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.fragment_color);
        dialog.setTitle("Selecione a cor");
        SeekBar redSeekBar =
                dialog.findViewById(R.id.redSeekBar);
        SeekBar greenSeekBar =
                dialog.findViewById(R.id.greenSeekBar);
        SeekBar blueSeekBar =
                dialog.findViewById(R.id.blueSeekBar);
        View colorView = dialog.findViewById(R.id.colorView);
        Button buttonOk = (Button) dialog.findViewById(R.id.button2);
        Button buttonCancel = (Button) dialog.findViewById(R.id.button);
        final SeekBar.OnSeekBarChangeListener colorChangedListener =
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean
                            fromUser) {
                        color = Color.argb(
                                122,
                                redSeekBar.getProgress(),
                                greenSeekBar.getProgress(),
                                blueSeekBar.getProgress()
                        );
                        colorView.setBackgroundColor(color);
                    }
                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                };
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doodleView.setC1(color);
                doodleView.invalidate();
                dialog.dismiss();
            }
        });
        //registra o observador em todas as SeekBars
        redSeekBar.setOnSeekBarChangeListener(colorChangedListener);
        greenSeekBar.setOnSeekBarChangeListener(colorChangedListener);
        blueSeekBar.setOnSeekBarChangeListener(colorChangedListener);

        //pega o DoodleView para pegar a cor atual e configurar
        //configura o progresso de cada barra
        redSeekBar.setProgress(Color.red(color));
        greenSeekBar.setProgress(Color.green(color));
        blueSeekBar.setProgress(Color.blue(color));
        return dialog;
    }

    private Dialog gerarDialog2(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.fragment_color);
        dialog.setTitle("Selecione a cor");
        SeekBar redSeekBar =
                dialog.findViewById(R.id.redSeekBar);
        SeekBar greenSeekBar =
                dialog.findViewById(R.id.greenSeekBar);
        SeekBar blueSeekBar =
                dialog.findViewById(R.id.blueSeekBar);
        View colorView = dialog.findViewById(R.id.colorView);
        Button buttonOk = (Button) dialog.findViewById(R.id.button2);
        Button buttonCancel = (Button) dialog.findViewById(R.id.button);
        final SeekBar.OnSeekBarChangeListener colorChangedListener =
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean
                            fromUser) {
                        color = Color.argb(
                                122,
                                redSeekBar.getProgress(),
                                greenSeekBar.getProgress(),
                                blueSeekBar.getProgress()
                        );
                        colorView.setBackgroundColor(color);
                    }
                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                };
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doodleView.setC2(color);
                doodleView.invalidate();
                dialog.dismiss();
            }
        });
        //registra o observador em todas as SeekBars
        redSeekBar.setOnSeekBarChangeListener(colorChangedListener);
        greenSeekBar.setOnSeekBarChangeListener(colorChangedListener);
        blueSeekBar.setOnSeekBarChangeListener(colorChangedListener);

        //pega o DoodleView para pegar a cor atual e configurar
        //configura o progresso de cada barra
        redSeekBar.setProgress(Color.red(color));
        greenSeekBar.setProgress(Color.green(color));
        blueSeekBar.setProgress(Color.blue(color));
        return dialog;
    }

    private Dialog gerarDialog3(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.fragment_color);
        dialog.setTitle("Selecione a cor");
        SeekBar redSeekBar =
                dialog.findViewById(R.id.redSeekBar);
        SeekBar greenSeekBar =
                dialog.findViewById(R.id.greenSeekBar);
        SeekBar blueSeekBar =
                dialog.findViewById(R.id.blueSeekBar);
        View colorView = dialog.findViewById(R.id.colorView);
        Button buttonOk = (Button) dialog.findViewById(R.id.button2);
        Button buttonCancel = (Button) dialog.findViewById(R.id.button);
        final SeekBar.OnSeekBarChangeListener colorChangedListener =
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean
                            fromUser) {
                        color = Color.argb(
                                122,
                                redSeekBar.getProgress(),
                                greenSeekBar.getProgress(),
                                blueSeekBar.getProgress()
                        );
                        colorView.setBackgroundColor(color);
                    }
                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                };
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doodleView.setC3(color);
                doodleView.invalidate();
                dialog.dismiss();
            }
        });
        //registra o observador em todas as SeekBars
        redSeekBar.setOnSeekBarChangeListener(colorChangedListener);
        greenSeekBar.setOnSeekBarChangeListener(colorChangedListener);
        blueSeekBar.setOnSeekBarChangeListener(colorChangedListener);

        //pega o DoodleView para pegar a cor atual e configurar
        //configura o progresso de cada barra
        redSeekBar.setProgress(Color.red(color));
        greenSeekBar.setProgress(Color.green(color));
        blueSeekBar.setProgress(Color.blue(color));
        return dialog;
    }

    private Dialog gerarDialog4(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.fragment_color);
        dialog.setTitle("Selecione a cor");
        SeekBar redSeekBar =
                dialog.findViewById(R.id.redSeekBar);
        SeekBar greenSeekBar =
                dialog.findViewById(R.id.greenSeekBar);
        SeekBar blueSeekBar =
                dialog.findViewById(R.id.blueSeekBar);
        View colorView = dialog.findViewById(R.id.colorView);
        Button buttonOk = (Button) dialog.findViewById(R.id.button2);
        Button buttonCancel = (Button) dialog.findViewById(R.id.button);
        final SeekBar.OnSeekBarChangeListener colorChangedListener =
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean
                            fromUser) {
                        color = Color.argb(
                                122,
                                redSeekBar.getProgress(),
                                greenSeekBar.getProgress(),
                                blueSeekBar.getProgress()
                        );
                        colorView.setBackgroundColor(color);
                    }
                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                };
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doodleView.setC4(color);
                doodleView.invalidate();
                dialog.dismiss();
            }
        });
        //registra o observador em todas as SeekBars
        redSeekBar.setOnSeekBarChangeListener(colorChangedListener);
        greenSeekBar.setOnSeekBarChangeListener(colorChangedListener);
        blueSeekBar.setOnSeekBarChangeListener(colorChangedListener);

        //pega o DoodleView para pegar a cor atual e configurar
        //configura o progresso de cada barra
        redSeekBar.setProgress(Color.red(color));
        greenSeekBar.setProgress(Color.green(color));
        blueSeekBar.setProgress(Color.blue(color));
        return dialog;
    }

}
