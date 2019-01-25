package e.ychenbrian.currencyconverter;

/**
 * @author ychenbrian
 * @version 1.0
 * @since 5/3/2018.
 */

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

import e.ychenbrian.currencyconverter.API.ObtainData;
import e.ychenbrian.currencyconverter.Currency.Currency;
import e.ychenbrian.currencyconverter.Currency.CurrencyPicker;
import e.ychenbrian.currencyconverter.Currency.OnCurrencyPickerListener;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnCurrencyPickerListener {

    private final int MAXLENGTH = 14;
    private Double commissionRate;
    private boolean flag = true;
    /**
     * oneOrTwo is for the first or the second currency that need
     * to be changed by the user.
     * <p>
     * organisation is the selection of the four bank organisations,
     * 0 means Visa, 1 means Mastercard, 2 means JCB  and 3 means DinersClub
     */
    private Integer oneOrTwo, organisation;
    private Currency currencyOne, currencyTwo;
    private Double midRateOne, midRateTwo;
    private Double finalRateOne, finalRateTwo;

    // the widget variable that matches all widget in the layout
    private TextView currencyRateOne, currencyRateTwo;
    private ImageView currencyFlagImageViewOne, currencyFlagImageViewTwo;
    private Button currencyPickButtonOne, currencyPickButtonTwo;
    private CurrencyPicker currencyPickerOne, currencyPickerTwo;
    private EditText inputETOne, inputETTwo;
    private Button bankButtonOne, bankButtonTwo, bankButtonThree, bankButtonFour, bankButtonFive;
    private EditText bankCommission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialise();
        setListener();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        /*if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Set the listener for the change of currency.
     * When press the currencies displayed on the home page, a
     * new fragment will be displayed for the currency selection.
     * <p>
     * Also set the button for the selection of four bank organisations.
     */
    private void setListener() {
        currencyPickButtonOne.setTag(1);
        currencyPickButtonTwo.setTag(2);

        currencyPickButtonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oneOrTwo = 1;
                currencyPickerOne.showDialog(getSupportFragmentManager());
            }
        });
        currencyPickButtonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oneOrTwo = 2;
                currencyPickerTwo.showDialog(getSupportFragmentManager());
            }
        });

        TextWatcher twOne = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //currencyResultOne.setHint("");

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String decodeS = s.toString();
                if (decodeS.equals("")) decodeS = "0";
                Double resultTwo = Double.parseDouble(decodeS) * finalRateOne * (1.0 + commissionRate);
                if (resultTwo > 99999999999.0) {
                    resultTwo = -1.0;
                }
                final String finStrTwo;
                DecimalFormat resultFormat = new DecimalFormat("0.000");
                finStrTwo = resultFormat.format(resultTwo);
                int subOne = finStrTwo.length() <= 11 ? finStrTwo.length() : 11;
                if (flag) {
                    flag = false;
                    if (decodeS.equals("0")) {
                        inputETTwo.setText("");
                    } else {
                        inputETTwo.setText(finStrTwo.substring(0, subOne));
                    }
                } else {
                    flag = true;
                }
            }

        };
        inputETOne.addTextChangedListener(twOne);

        TextWatcher twTwo = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String decodeS = s.toString();
                if (decodeS.equals("")) decodeS = "0";
                Double resultOne = Double.parseDouble(decodeS) * finalRateTwo * (1.0 + commissionRate);
                if (resultOne > 99999999999.0) {
                    resultOne = -1.0;
                }
                final String finStrOne;
                DecimalFormat resultFormat = new DecimalFormat("0.000");
                finStrOne = resultFormat.format(resultOne);
                int subTwo = finStrOne.length() <= 11 ? finStrOne.length() : 11;
                if (flag) {
                    flag = false;
                    if (decodeS.equals("0")) {
                        inputETOne.setText("");
                    } else {
                        inputETOne.setText(finStrOne.substring(0, subTwo));
                    }
                } else {
                    flag = true;
                }
            }
        };
        inputETTwo.addTextChangedListener(twTwo);

        TextWatcher twCommission = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                flag = false;
                inputETOne.setText("");
                flag = false;
                inputETTwo.setText("");

                if (s.toString().equals("")) {
                    commissionRate = 0.0;
                } else {
                    commissionRate = Double.parseDouble(s.toString()) / 100.0;
                }
            }
        };
        bankCommission.addTextChangedListener(twCommission);

        bankButtonOne.setOnClickListener(new View.OnClickListener() { // Mastercard
            @Override
            public void onClick(View v) {
                bankButtonOne.setBackgroundResource(R.drawable.button_draw_two);
                bankButtonTwo.setBackgroundResource(R.drawable.button_draw_empty);
                bankButtonThree.setBackgroundResource(R.drawable.button_draw_empty);
                //bankButtonFour.setBackgroundResource(R.drawable.button_draw_empty);
                bankButtonFive.setBackgroundResource(R.drawable.button_draw_empty);

                organisation = 1;

                flag = false;
                inputETOne.setText("");
                flag = false;
                inputETTwo.setText("");

                if (currencyOne.getCode().toLowerCase().equals("usd")) {
                    midRateOne = 1.0;
                } else {
                    midRateOne = Double.parseDouble(currencyOne.getRates().getRate("MasterCard"));
                }
                if (currencyTwo.getCode().toLowerCase().equals("usd")) {
                    midRateTwo = 1.0;
                } else {
                    midRateTwo = Double.parseDouble(currencyTwo.getRates().getRate("MasterCard"));
                }

                showRates();
            }
        });

        bankButtonTwo.setOnClickListener(new View.OnClickListener() { // Visa
            @Override
            public void onClick(View v) {
                bankButtonTwo.setBackgroundResource(R.drawable.button_draw_two);
                bankButtonOne.setBackgroundResource(R.drawable.button_draw_empty);
                bankButtonThree.setBackgroundResource(R.drawable.button_draw_empty);
                //bankButtonFour.setBackgroundResource(R.drawable.button_draw_empty);
                bankButtonFive.setBackgroundResource(R.drawable.button_draw_empty);

                organisation = 0;

                // clear the editTexts
                flag = false;
                inputETOne.setText("");
                flag = false;
                inputETTwo.setText("");

                if (currencyOne.getCode().toLowerCase().equals("usd")) {
                    midRateOne = 1.0;
                } else {
                    midRateOne = Double.parseDouble(currencyOne.getRates().getRate("Visa"));
                }
                if (currencyTwo.getCode().toLowerCase().equals("usd")) {
                    midRateTwo = 1.0;
                } else {
                    midRateTwo = Double.parseDouble(currencyTwo.getRates().getRate("Visa"));
                }

                showRates();
            }
        });

        bankButtonThree.setOnClickListener(new View.OnClickListener() { // DinersClub
            @Override
            public void onClick(View v) {
                bankButtonThree.setBackgroundResource(R.drawable.button_draw_two);
                bankButtonTwo.setBackgroundResource(R.drawable.button_draw_empty);
                bankButtonOne.setBackgroundResource(R.drawable.button_draw_empty);
                //bankButtonFour.setBackgroundResource(R.drawable.button_draw_empty);
                bankButtonFive.setBackgroundResource(R.drawable.button_draw_empty);

                organisation = 3;

                // clear the editTexts
                flag = false;
                inputETOne.setText("");
                flag = false;
                inputETTwo.setText("");

                if (currencyOne.getCode().toLowerCase().equals("usd")) {
                    midRateOne = 1.0;
                } else {
                    midRateOne = Double.parseDouble(currencyOne.getRates().getRate("DinersClub"));
                }
                if (currencyTwo.getCode().toLowerCase().equals("usd")) {
                    midRateTwo = 1.0;
                } else {
                    midRateTwo = Double.parseDouble(currencyTwo.getRates().getRate("DinersClub"));
                }

                showRates();
            }
        });

        // UnionPay will be enabled in the next version.
        /*bankButtonFour.setOnClickListener(new View.OnClickListener() { // UnionPay*/

        bankButtonFive.setOnClickListener(new View.OnClickListener() { // JCB
            @Override
            public void onClick(View v) {
                bankButtonFive.setBackgroundResource(R.drawable.button_draw_two);
                bankButtonTwo.setBackgroundResource(R.drawable.button_draw_empty);
                bankButtonThree.setBackgroundResource(R.drawable.button_draw_empty);
                //bankButtonFour.setBackgroundResource(R.drawable.button_draw_empty);
                bankButtonOne.setBackgroundResource(R.drawable.button_draw_empty);

                organisation = 2;

                // clear the editTexts
                flag = false;
                inputETOne.setText("");
                flag = false;
                inputETTwo.setText("");

                if (currencyOne.getCode().toLowerCase().equals("usd")) {
                    midRateOne = 1.0;
                } else {
                    midRateOne = Double.parseDouble(currencyOne.getRates().getRate("JCB"));
                }
                if (currencyTwo.getCode().toLowerCase().equals("usd")) {
                    midRateTwo = 1.0;
                } else {
                    midRateTwo = Double.parseDouble(currencyTwo.getRates().getRate("JCB"));
                }

                showRates();
            }
        });
    }

    /**
     * Initialise all widget variables and set the default values of all
     * variables that used in the calculation and selection.
     */
    private void initialise() {

        // find all widget variables by the id given in the layout
        currencyPickButtonOne = findViewById(R.id.pickButtonOne);
        currencyPickButtonTwo = findViewById(R.id.pickButtonTwo);
        currencyFlagImageViewOne = findViewById(R.id.flagViewOne);
        currencyFlagImageViewTwo = findViewById(R.id.flagViewTwo);
        currencyRateOne = findViewById(R.id.rateOne);
        currencyRateTwo = findViewById(R.id.rateTwo);
        inputETOne = findViewById(R.id.inputOne);
        inputETTwo = findViewById(R.id.inputTwo);
        bankCommission = findViewById(R.id.commission);

        // default bank organisation selected is Visa
        organisation = 0;
        // default commission rate is set to be 0.0
        commissionRate = 0.0;

        // initialise the CurrencyPicker object
        currencyPickerOne =
                new CurrencyPicker.Builder().with(this)
                        .listener(this)
                        .build();
        currencyPickerTwo =
                new CurrencyPicker.Builder().with(this)
                        .listener(this)
                        .build();

        /**
         * Initialise the four bank buttons, the UnionPay may be applied
         * in next version of this application due to crawler is different
         * to other four bank organisations.
         */
        bankButtonOne = findViewById(R.id.bankMastercardButton);
        bankButtonTwo = findViewById(R.id.bankVisaButton);
        bankButtonThree = findViewById(R.id.bankDinersClubButton);
        //bankButtonFour = findViewById(R.id.bankUnionPayButton);
        bankButtonFive = findViewById(R.id.bankJCBButton);

        // set Visa button to be the default selected button
        bankButtonTwo.setBackgroundResource(R.drawable.button_draw_two);

        // initial the default currencies, which are CNY & USD
        new Thread(new Runnable() {
            @Override
            public void run() {

                currencyOne = new Currency("USD", "United States Dollar", R.drawable.flag_usd);
                currencyTwo = new Currency("CNY", "Chinese Yuan Renminbi", R.drawable.flag_cny);
                String startRate[] = ObtainData.getByCurrencyCode("cny");
                currencyTwo.setRates(startRate);

                midRateOne = 1.0;
                midRateTwo = Double.parseDouble(currencyTwo.getRates().getRate("Visa"));

                showRates();
            }
        }).start();
    }

    /**
     * Override the interface for the new selected currency.
     * It will clear the two editTexts and obtain the new
     * currency rate based on the selected bank organisation.
     *
     * @param currency the selected currency by the user
     */
    @Override
    public void onSelectCurrency(final Currency currency) {

        // clear two editTexts
        flag = false;
        inputETOne.setText("");
        flag = false;
        inputETTwo.setText("");

        if (oneOrTwo == 1) {
            currencyOne = currency;
            currencyFlagImageViewOne.setImageResource(currency.getFlag());
            final String buttonOneString = "         " + currency.getCode();
            currencyPickButtonOne.setText(buttonOneString);

            // a new thread for the decoder of html file
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String selectRate;
                    if (currency.getCode().toLowerCase().equals("usd")) {
                        selectRate = "1";
                    } else {
                        String cur[] = ObtainData.getByCurrencyCode(currency.getCode().toLowerCase());
                        currency.setRates(cur);
                        if (organisation == 0) {
                            selectRate = currency.getRates().getRate("Visa");
                        } else if (organisation == 1) {
                            selectRate = currency.getRates().getRate("MasterCard");
                        } else if (organisation == 2) {
                            selectRate = currency.getRates().getRate("JCB");
                        } else {
                            selectRate = currency.getRates().getRate("DinersClub");
                        }
                    }

                    midRateOne = Double.parseDouble(selectRate);

                    showRates();
                }
            }).start();
        } else {
            currencyTwo = currency;
            currencyFlagImageViewTwo.setImageResource(currency.getFlag());
            final String buttonTwoString = "         " + currency.getCode();
            currencyPickButtonTwo.setText(buttonTwoString);

            // a new thread for the decoder of html file
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String selectRate;
                    if (currency.getCode().toLowerCase().equals("usd")) {
                        selectRate = "1";
                    } else {
                        String cur[] = ObtainData.getByCurrencyCode(currency.getCode().toLowerCase());
                        currency.setRates(cur);
                        if (organisation == 0) {
                            selectRate = currency.getRates().getRate("Visa");
                        } else if (organisation == 1) {
                            selectRate = currency.getRates().getRate("MasterCard");
                        } else if (organisation == 2) {
                            selectRate = currency.getRates().getRate("JCB");
                        } else {
                            selectRate = currency.getRates().getRate("DinersClub");
                        }
                    }

                    midRateTwo = Double.parseDouble(selectRate);

                    showRates();
                }
            }).start();
        }
    }

    /**
     * Display the two rates below the editText, it will use the
     * middle rate got from CurrencyPicker. The maximum length of
     * the currency rates are controlled by the variable (14 include
     * "1 = ").
     */
    public void showRates() {
        finalRateOne = midRateOne / midRateTwo;
        finalRateTwo = 1.0 / finalRateOne;
        final String finStrOne = "1 = " + String.valueOf(finalRateOne);
        final int subOne = finStrOne.length() <= MAXLENGTH ? finStrOne.length() : MAXLENGTH;
        final String finStrTwo = "1 = " + String.valueOf(finalRateTwo);
        final int subTwo = finStrTwo.length() <= MAXLENGTH ? finStrTwo.length() : MAXLENGTH;
        currencyRateOne.post(new Runnable() {
            @Override
            public void run() {
                currencyRateOne.setText(finStrOne.substring(0, subOne));
            }
        });
        currencyRateTwo.post(new Runnable() {
            @Override
            public void run() {
                currencyRateTwo.setText(finStrTwo.substring(0, subTwo));
            }
        });
    }
}
