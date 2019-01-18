package com.jockay.apmt.view.activities;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.NetworkOnMainThreadException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jockay.apmt.R;
import com.jockay.apmt.controller.SharedPref;
import com.jockay.apmt.view.dialogs.DialDividersSelector;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private static int WAIT_AFTER_TEXT_CHANGE = 500;

    public ViewGroup items;

    public TextView tvCostByPerson;
    public TextView tvCost;

    public DecimalFormat formatter;

    private Menu mMenu;
    private int newItemCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.newItemCounter = 1;
        this.formatter = new DecimalFormat("###,###.###");
        this.items = (ViewGroup) findViewById(R.id.llItems);
        this.tvCost = (TextView) findViewById(R.id.tvCost);
        this.tvCostByPerson = (TextView) findViewById(R.id.tvCostByPerson);
        if(new SharedPref(MainActivity.this).getPeopleNumber() == 0) {
            new SharedPref(MainActivity.this).storePeopleNumber(2);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        mMenu = menu;
        Integer peopleNum = new SharedPref(MainActivity.this).getPeopleNumber();
        setPeopleNumActionIcon(peopleNum);

        return true;
    }



    public void setPeopleNumActionIcon(String strPeopleNum) {
        Integer peopleNum = Integer.valueOf(strPeopleNum);
        if(peopleNum == 2) {
            mMenu.getItem(0).setIcon(R.drawable.ic_filter_2_white_24dp);
        } else if(peopleNum == 3) {
            mMenu.getItem(0).setIcon(R.drawable.ic_filter_3_white_24dp);
        } else if(peopleNum == 4) {
            mMenu.getItem(0).setIcon(R.drawable.ic_filter_4_white_24dp);
        } else if(peopleNum == 5) {
            mMenu.getItem(0).setIcon(R.drawable.ic_filter_5_white_24dp);
        } else if(peopleNum == 6) {
            mMenu.getItem(0).setIcon(R.drawable.ic_filter_6_white_24dp);
        } else if(peopleNum == 7) {
            mMenu.getItem(0).setIcon(R.drawable.ic_filter_7_white_24dp);
        } else if(peopleNum == 8) {
            mMenu.getItem(0).setIcon(R.drawable.ic_filter_8_white_24dp);
        } else if(peopleNum == 9) {
            mMenu.getItem(0).setIcon(R.drawable.ic_filter_9_white_24dp);
        } else if(peopleNum > 9) {
            mMenu.getItem(0).setIcon(R.drawable.ic_filter_9_plus_white_24dp);
        }
    }

    public void setPeopleNumActionIcon(Integer peopleNum) {
        if(peopleNum == 2) {
            mMenu.getItem(0).setIcon(R.drawable.ic_filter_2_white_24dp);
        } else if(peopleNum == 3) {
            mMenu.getItem(0).setIcon(R.drawable.ic_filter_3_white_24dp);
        } else if(peopleNum == 4) {
            mMenu.getItem(0).setIcon(R.drawable.ic_filter_4_white_24dp);
        } else if(peopleNum == 5) {
            mMenu.getItem(0).setIcon(R.drawable.ic_filter_5_white_24dp);
        } else if(peopleNum == 6) {
            mMenu.getItem(0).setIcon(R.drawable.ic_filter_6_white_24dp);
        } else if(peopleNum == 7) {
            mMenu.getItem(0).setIcon(R.drawable.ic_filter_7_white_24dp);
        } else if(peopleNum == 8) {
            mMenu.getItem(0).setIcon(R.drawable.ic_filter_8_white_24dp);
        } else if(peopleNum == 9) {
            mMenu.getItem(0).setIcon(R.drawable.ic_filter_9_white_24dp);
        } else if(peopleNum > 9) {
            mMenu.getItem(0).setIcon(R.drawable.ic_filter_9_plus_white_24dp);
        }
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        final String[] itemnames = new String[]{
                getString(R.string.item_gas),
                getString(R.string.item_electricity),
                getString(R.string.item_hot_water),
                getString(R.string.item_cold_water),
                getString(R.string.item_other)};
        switch (item.getItemId()) {
            case R.id.ac_new_item:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setSingleChoiceItems(itemnames, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        addItem(itemnames[i]);
                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .setTitle(R.string.add_item)
                .create()
                .show();
//                String[] texts = new String[]{"asd", "bgsadfdsfh", "sdfgsafdh fda", "VALAMI"};
//                int index = (int) (Math.random() * texts.length);
//                addItem(texts[index]);
                return true;
            case R.id.ac_new_item_simple:
//                builder = new AlertDialog.Builder(MainActivity.this);
//                builder.setSingleChoiceItems(itemnames, -1, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        dialogInterface.dismiss();
//                        addItemSimple(itemnames[i]);
//                    }
//                })
//                        .setNegativeButton(android.R.string.cancel, null)
//                        .setTitle(R.string.add_item_simple)
//                        .create()
//                        .show();
                addItemSimple("ÚJ TÉTEL" + " " + newItemCounter++);
                return true;
            case R.id.ac_people_number:
                new DialDividersSelector(MainActivity.this).show();
                return true;
            case R.id.ac_delete_items:
                builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.delete_items)
                .setMessage(R.string.ask_delete_items)
                .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        items.removeAllViews();
                    }
                })
                .setNegativeButton(getString(R.string.cancel), null)
                .create()
                .show();
                return true;
            case R.id.ac_settings:
                AlertDialog.Builder blr = new AlertDialog.Builder(MainActivity.this);
                blr.setItems(new CharSequence[]{"Lista mentése", "Lista betöltése", "Tételek szerkesztése"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                blr.setNegativeButton("Mégse", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                blr.create().show();


//
//                LayoutInflater li = getLayoutInflater();
//                View dialogView = li.inflate(R.layout.dialog_settings, null);
//
//                final Dialog dialog = new Dialog(this);
//                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                dialog.setContentView(dialogView);
//                dialog.show();
//
//                final SharedPref pref = new SharedPref(MainActivity.this);
//
//                final EditText gas = (EditText) dialogView.findViewById(R.id.etGas);
//                final EditText electricity = (EditText) dialogView.findViewById(R.id.etElectricity);
////                EditText heat = (EditText) dialogView.findViewById(R.id.etHeat);
//                final EditText coldWater = (EditText) dialogView.findViewById(R.id.etColdWater);
//                final EditText hotWater = (EditText) dialogView.findViewById(R.id.etHotWater);
//
//                Integer storedGas = pref.getGasUnitPrice();
//                if(storedGas == 0) { gas.setText(""); }
//                else { gas.setText(String.valueOf(storedGas)); }
//
//                Integer storedElectricity = pref.getElectricityUnitPrice();
//                if(storedElectricity == 0) { electricity.setText(""); }
//                else { electricity.setText(String.valueOf(storedElectricity)); }
//
//                Integer storedColdWater = pref.getColdWaterUnitPrice();
//                if(storedColdWater == 0) { coldWater.setText(""); }
//                else { coldWater.setText(String.valueOf(storedColdWater)); }
//
//                Integer storedHotWater = pref.getHotWaterUnitPrice();
//                if(storedHotWater == 0) { hotWater.setText(""); }
//                else { hotWater.setText(String.valueOf(storedHotWater)); }
//
//                ((Button) dialogView.findViewById(R.id.btPositive))
//                        .setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                String tGas = gas.getText().toString();
//                                String tElectricity = electricity.getText().toString();
//                                String tColdWater = coldWater.getText().toString();
//                                String tHotWater = hotWater.getText().toString();
//                                if(! "".equals(tGas))
//                                    pref.storeGasUnitPrice(Integer.valueOf(tGas));
//                                if(! "".equals(tElectricity))
//                                    pref.storeElectricityUnitPrice((Integer.valueOf(tElectricity)));
//                                if(! "".equals(tColdWater))
//                                    pref.storeColdWaterUnitPrice((Integer.valueOf(tColdWater)));
//                                if(! "".equals(tHotWater))
//                                    pref.storeHotWaterUnitPrice((Integer.valueOf(tHotWater)));
//                                dialog.dismiss();
//                            }
//                        });
//
//                ((Button) dialogView.findViewById(R.id.btNegative))
//                        .setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                dialog.dismiss();
//                            }
//                        });
//
//                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void addItem(final String title) {
        SharedPref pref = new SharedPref(MainActivity.this);

        final ViewGroup newItem = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.list_item_layout, items, false);
        final TextView itemTitle = (TextView) newItem.findViewById(R.id.tvTitle);
        ImageView ivItemLogo = (ImageView) newItem.findViewById(R.id.ivItemLogo);
        final EditText etClockStart = (EditText) newItem.findViewById(R.id.etClockStart);
        final EditText etClockEnd   = (EditText) newItem.findViewById(R.id.etClockEnd);
        final EditText etItemUnit = (EditText) newItem.findViewById(R.id.etItemUnit);
        final EditText etItemPrice = (EditText) newItem.findViewById(R.id.etItemPrice);
        ImageButton ibRemove = (ImageButton) newItem.findViewById(R.id.ibRemove);

        itemTitle.setText(title);

        ivItemLogo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                etItemPrice.setText("");
                etClockStart.setText("");
                etClockEnd.setText("");
                return true;
            }
        });

        ivItemLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, R.string.push_long_to_delete, Toast.LENGTH_SHORT).show();
            }
        });

        String gas = getString(R.string.item_gas);
        String electricity = getString(R.string.item_electricity);
        String hotWater = getString(R.string.item_hot_water); // fűtés volt
        String coldWater = getString(R.string.item_cold_water); // internet volt
        String other = getString(R.string.item_other);
        if(gas.equals(title)) {
            ivItemLogo.setBackgroundResource(R.drawable.gas_xxl);
            Integer unit = pref.getGasUnitPrice();
            etItemUnit.setText(unit == 0 ? "" :String.valueOf(unit));
        } else if(electricity.equals(title)) {
            ivItemLogo.setBackgroundResource(R.drawable.electricity_xxl);
            Integer unit = pref.getElectricityUnitPrice();
            etItemUnit.setText(unit == 0 ? "" :String.valueOf(unit));
        } else if(hotWater.equals(title)) {
            ivItemLogo.setBackgroundResource(R.drawable.red_gas_xxl);
            Integer unit = pref.getHotWaterUnitPrice();
            etItemUnit.setText(unit == 0 ? "" :String.valueOf(unit));
        } else if(coldWater.equals(title)) {
            ivItemLogo.setBackgroundResource(R.drawable.provider_internetsvg);
            Integer unit = pref.getColdWaterUnitPrice();
            etItemUnit.setText(unit == 0 ? "" :String.valueOf(unit));
        } else if(other.equals(title)) {
            ivItemLogo.setBackgroundResource(R.drawable.ic_action_photo);
        }

        if(other.equals(title)) {
            itemTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                            .setTitle(R.string.edit_title);

                    LinearLayout layout = new LinearLayout(MainActivity.this);
                    layout.setPadding(50, 0, 50, 0); // left top right bottom
                    layout.setGravity(Gravity.CENTER_HORIZONTAL);

                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            RelativeLayout.LayoutParams.MATCH_PARENT,
                            RelativeLayout.LayoutParams.MATCH_PARENT
                    );

                    final AppCompatEditText input = new AppCompatEditText(MainActivity.this);
                    input.setText(title);
                    input.setInputType(InputType.TYPE_CLASS_TEXT);
                    input.setGravity(Gravity.CENTER_HORIZONTAL);
                    input.setLayoutParams(params);
                    input.selectAll();
                    layout.addView(input);
                    builder.setView(layout);

                    builder.setNegativeButton(R.string.okay, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String inputText =  input.getText().toString();
                            if(! "".equals(inputText)) {
                                itemTitle.setText(inputText);
                            }
                        }
                    });
                    builder.setPositiveButton(R.string.cancel, null);
                    builder.create().show();
                }
            });
        }

        etClockStart.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String strClockEnd   = etClockEnd.getText().toString();
                String strClockStart = etClockStart.getText().toString();
                String strItemUnit   = etItemUnit.getText().toString();
                if( ! "".equals(strClockEnd) && ! "".equals(strClockStart) && ! "".equals(strItemUnit)) {
                    Integer clockStart = Integer.parseInt(strClockStart);
                    Integer clockEnd   = Integer.parseInt(strClockEnd);
                    Integer itemUnit   = Integer.parseInt(strItemUnit);
                    Integer itemPrice = (clockEnd - clockStart) * itemUnit;
                    if(itemPrice > 0) {
                        etItemPrice.setText(formatter.format(itemPrice));
                        calculateCosts(new SharedPref(MainActivity.this).getPeopleNumber());
                    }
                } else {
                    etItemPrice.setText("");
                    calculateCosts(new SharedPref(MainActivity.this).getPeopleNumber());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                Handler handler = new Handler();
                Runnable runnable = new Runnable() {
                    public void run() {
                        String strClockEnd   = etClockEnd.getText().toString();
                        String strClockStart = etClockStart.getText().toString();
                        if(!"".equals(strClockEnd) && !"".equals(strClockStart)) {
                            Integer clockEnd   = Integer.parseInt(strClockEnd);
                            Integer clockStart = Integer.parseInt(strClockStart);
                            if(clockEnd < clockStart) {
//                                Toast.makeText(getApplicationContext(), R.string.err_endclock_greater_than_start, Toast.LENGTH_SHORT).show();
                                etClockStart.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                                if( etClockStart.getError() != null && ! etClockStart.getError().equals(getString(R.string.err_endclock_greater_than_start)))
                                    etClockStart.setError(getString(R.string.err_endclock_greater_than_start));
                            } else {
                                etClockStart.setTextColor(getResources().getColor(android.R.color.white));
                                etClockStart.setError(null);
                            }
                        }
                    }
                };
                handler.postDelayed(runnable, WAIT_AFTER_TEXT_CHANGE);
            }
        });
        etClockEnd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String strClockEnd   = etClockEnd.getText().toString();
                String strClockStart = etClockStart.getText().toString();
                String strItemUnit   = etItemUnit.getText().toString();
                if( ! "".equals(strClockEnd) && ! "".equals(strClockStart) && ! "".equals(strItemUnit)) {
                    Integer clockStart = Integer.parseInt(strClockStart);
                    Integer clockEnd   = Integer.parseInt(strClockEnd);
                    Integer itemUnit   = Integer.parseInt(strItemUnit);
                    Integer itemPrice = (clockEnd - clockStart) * itemUnit;
                    if(itemPrice > 0) {
                        etItemPrice.setText(formatter.format(itemPrice));
                        calculateCosts(new SharedPref(MainActivity.this).getPeopleNumber());
                    }
                } else {
                    etItemPrice.setText("");
                    calculateCosts(new SharedPref(MainActivity.this).getPeopleNumber());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                Handler handler = new Handler();
                Runnable runnable = new Runnable() {
                    public void run() {
                        String strClockEnd   = etClockEnd.getText().toString();
                        String strClockStart = etClockStart.getText().toString();
                        if(!"".equals(strClockEnd) && !"".equals(strClockStart)) {
                            Integer clockEnd   = Integer.parseInt(strClockEnd);
                            Integer clockStart = Integer.parseInt(strClockStart);
                            if(clockEnd < clockStart) {
//                                Toast.makeText(getApplicationContext(), R.string.err_startclock_greater_than_end, Toast.LENGTH_SHORT).show();
                                etClockEnd.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                                if((etClockEnd.getError() != null) && ! etClockEnd.getError().equals(getString(R.string.err_startclock_greater_than_end)))
                                    etClockEnd.setError(getString(R.string.err_startclock_greater_than_end));
                            } else {
                                etClockEnd.setTextColor(getResources().getColor(android.R.color.white));
                                etClockEnd.setError(null);
                            }

                        }
                    }
                };
                handler.postDelayed(runnable, WAIT_AFTER_TEXT_CHANGE);
            }
        });
        etItemUnit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String strClockEnd   = etClockEnd.getText().toString();
                String strClockStart = etClockStart.getText().toString();
                String strItemUnit   = etItemUnit.getText().toString();
                if( ! "".equals(strClockEnd) && ! "".equals(strClockStart) && ! "".equals(strItemUnit)) {
                    Integer clockStart = Integer.parseInt(strClockStart);
                    Integer clockEnd   = Integer.parseInt(strClockEnd);
                    Integer itemUnit   = Integer.parseInt(strItemUnit);
                    Integer itemPrice = (clockEnd - clockStart) * itemUnit;
                    if(itemPrice > 0) {
                        etItemPrice.setText(formatter.format(itemPrice));
                        calculateCosts(new SharedPref(MainActivity.this).getPeopleNumber());
                    }
                } else {
                    etItemPrice.setText("");
                    calculateCosts(new SharedPref(MainActivity.this).getPeopleNumber());
                }

            }
            @Override
            public void afterTextChanged(Editable editable) {
                // végtelen ciklus
//                String strItemUnit   = etItemUnit.getText().toString();
//                if( ! "".equals(strItemUnit)) {
//                    Integer itemUnit = Integer.parseInt(strItemUnit);
//                    etItemUnit.setText(formatter.format(itemUnit));
//                }
            }
        });
        etItemPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                calculateCosts(new SharedPref(MainActivity.this).getPeopleNumber());
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        ibRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogInterface.OnClickListener onPositive = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        items.removeView(newItem);
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.delete_item)
                        .setMessage(R.string.ask_delete_item)
                        .setPositiveButton(android.R.string.ok, onPositive)
                        .setNegativeButton(android.R.string.cancel, null)
                        .create()
                        .show();
            }
        });

        items.addView(newItem, 0);
    }

    public void addItemSimple(final String title) {
        final ViewGroup newItem = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.list_item_simple_layout, items, false);
        final TextView itemTitle = (TextView) newItem.findViewById(R.id.tvTitle);
        ImageView ivItemLogo = (ImageView) newItem.findViewById(R.id.ivItemLogo);
        final EditText etItemPrice = (EditText) newItem.findViewById(R.id.etItemPrice);
        ImageButton ibRemove = (ImageButton) newItem.findViewById(R.id.ibRemove);
        itemTitle.setText(title);

        ivItemLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etItemPrice.setText("");
            }
        });

        itemTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                        .setTitle(R.string.edit_title);

                LinearLayout layout = new LinearLayout(MainActivity.this);
                layout.setPadding(50, 0, 50, 0); // left top right bottom
                layout.setGravity(Gravity.CENTER_HORIZONTAL);

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.MATCH_PARENT
                );

                final AppCompatEditText input = new AppCompatEditText(MainActivity.this);
                input.setText(itemTitle.getText().toString());
                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
                input.setGravity(Gravity.CENTER_HORIZONTAL);
                input.setLayoutParams(params);
                input.requestFocus();
                input.selectAll();
                layout.addView(input);
                builder.setView(layout);

                builder.setNegativeButton(R.string.okay, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String inputText =  input.getText().toString();
                        if(! "".equals(inputText)) {
                            itemTitle.setText(inputText);
                        }
                    }
                });
                builder.setPositiveButton(R.string.cancel, null);
                builder.create().show();
            }
        });

        itemTitle.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                final String[] titles = new String[] {
                        "LAKBÉR",
                        "KÖZÖSKÖLTSÉG",
                        "INTERNET",
                        "VILLANYSZÁMLA",
                        "TÁVHŐ",
                        "VÍZDÍJ",
                        "HÁLÓZATI DÍJ"
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setSingleChoiceItems(titles, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        itemTitle.setText(titles[i]);
                        dialogInterface.dismiss();
                    }
                })
                .create()
                .show();
                return false;
            }
        });

        etItemPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                calculateCosts(new SharedPref(MainActivity.this).getPeopleNumber());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        ibRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogInterface.OnClickListener onPositive = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        items.removeView(newItem);
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.delete_item)
                        .setMessage(R.string.ask_delete_item)
                        .setPositiveButton(android.R.string.ok, onPositive)
                        .setNegativeButton(android.R.string.cancel, null)
                        .create()
                        .show();
            }
        });
        items.addView(newItem, 0);
    }

    /**
     * 1. elem: full cost<br>
     * 2. elem: cost by one person
     *
     * @param divider people number to divide by
     * @return list of full cost and cost by one person
     */
//    public List<Integer> calculateCosts(Integer divider) {
//        List<Integer> result = new ArrayList<>();
//        Integer cost = 0;
//        for(int i = 0; i < items.getChildCount(); ++i) {
//            LinearLayout ll = (LinearLayout) items.getChildAt(i);
//            EditText et = (EditText) ll.findViewById(R.id.etItemPrice);
//            String etText = et.getText().toString();
//            if("".equals(etText)) {
//                Integer itemPrice = Integer.valueOf(etText);
//                cost += itemPrice;
//            }
//        }
//
//        result.add(cost / divider);
//        result.add(cost);
//        return result;
//    }

    public void calculateCosts(Integer divider) {
        Integer cost = 0;
        for(int i = 0; i < items.getChildCount(); ++i) {
            LinearLayout ll = (LinearLayout) items.getChildAt(i);
            EditText et = (EditText) ll.findViewById(R.id.etItemPrice);
            String etText = et.getText().toString();
            etText = etText.replaceAll("\\s+","");
            if( ! "".equals(etText)) {
                Integer itemPrice = Integer.valueOf(etText);
                cost += itemPrice;
            }
        }

        tvCostByPerson.setText(formatter.format(cost / divider) + " Ft");
        tvCost.setText(formatter.format(cost) + " Ft");
    }

    public int getIndexOfElement(String[] items, String element) {
        for(int i = 0; i < items.length; i++) {
            if(items[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

}
