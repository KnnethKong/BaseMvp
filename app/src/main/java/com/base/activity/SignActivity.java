package com.base.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.base.db.WalletEntity;
import com.base.mvp.R;
import com.base.tools.WalletUtils;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.flexbox.FlexboxLayoutManager;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.zip.Inflater;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SignActivity extends AppCompatActivity implements View.OnClickListener, ClickNormal, ClickSelect {
    private EditText editName, editPwd;
    private TextView txtSign;
    private String TAG = "SignActivity";
    private RecyclerView recyclerView1, recyclerView2;
    private ZJcSelectAdapter zJcSelectAdapter;
    private ZJcNormalAdapter zJcNormalAdapter;
    private List<String> normalDatas = new ArrayList<>(12);
    private List<WoldEntity> woldEntities = new ArrayList<>(12);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_zjc_sign);
        editName = (EditText) findViewById(R.id.sign_name);
        editPwd = (EditText) findViewById(R.id.sign_pwd);
        findViewById(R.id.sign_ok).setOnClickListener(this);
        txtSign = (TextView) findViewById(R.id.sign_info);
        recyclerView1 = (RecyclerView) findViewById(R.id.sign_recycle1);
        zJcSelectAdapter = new ZJcSelectAdapter(this);
        zJcNormalAdapter = new ZJcNormalAdapter(this);
        FlexboxLayoutManager layoutManager1 = new FlexboxLayoutManager(this);
        layoutManager1.setFlexWrap(FlexWrap.WRAP);
        layoutManager1.setAlignItems(AlignItems.STRETCH);
        recyclerView1.setLayoutManager(layoutManager1);
        recyclerView1.setAdapter(zJcNormalAdapter);
        recyclerView2 = (RecyclerView) findViewById(R.id.sign_recycle2);
        FlexboxLayoutManager layoutManager2 = new FlexboxLayoutManager(this);
        layoutManager2.setFlexWrap(FlexWrap.WRAP);
        layoutManager2.setAlignItems(AlignItems.STRETCH);
        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView2.setAdapter(zJcSelectAdapter);


    }

    @Override
    public void onClick(View view) {
        final String pwd = editPwd.getText().toString().trim();
        final String name = editName.getText().toString().trim();
        Observable.create(new ObservableOnSubscribe<WalletEntity>() {
            @Override
            public void subscribe(ObservableEmitter<WalletEntity> e) throws Exception {
                WalletEntity walletEntity = new WalletEntity();//WalletUtils.generateMnemonic(name, pwd);
                walletEntity.setZjc("sdue-dkeu-siwudh-dkusn-druen-dksudn-dkei-so38-sjahd-dksjxs-asmfhs-pisyg");
                Log.e(TAG, "onClick: " + walletEntity.toString());
                e.onNext(walletEntity);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<WalletEntity>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(WalletEntity walletEntity) {
                txtSign.setText(walletEntity.toString());
                List<String> temp = Arrays.asList(walletEntity.getZjc().split("-"));
                for (String s : temp) {
                    WoldEntity woldEntity = new WoldEntity();
                    woldEntity.setValue(s);
                    woldEntity.setShow(false);
                    woldEntities.add(woldEntity);
                }
                Log.e(TAG, "woldEntities: " + woldEntities);
                Log.e(TAG, "normalDatas: " + normalDatas);
                zJcNormalAdapter.notifyDataSetChanged();
                zJcSelectAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    private int normalTxt = 0x6483, seletTxt = 0x6458;

    @Override
    public void normalItemClick(int i, View view) {
        TextView txt = (TextView) view;
        String wold = txt.getText().toString().trim();
        normalDatas.remove(wold);
        for(int j=0;j<woldEntities.size();j++){
            WoldEntity woldEntity = woldEntities.get(j);
            if (woldEntity.getValue().equals(wold)) {
                zJcSelectAdapter.unSetSelect(j);
            }
        }
        zJcNormalAdapter.notifyDataSetChanged();

    }

    @Override
    public void selectItemClick(int i, View view) {
        WoldEntity woldEntity = woldEntities.get(i);
        TextView txt = (TextView) view;
        String word = txt.getText().toString().trim();
        String v = woldEntity.getValue();
        if (normalDatas.contains(word)) {
            Log.e(TAG, normalDatas + "  contains  true  : " + word + "   v  " + v);
            return;
        }
        zJcSelectAdapter.setSelect(i);
        normalDatas.add(word);
        zJcNormalAdapter.notifyDataSetChanged();
    }

    class ZJcNormalAdapter extends RecyclerView.Adapter<ZJcNormalAdapter.ZJCHolder> {
        private ClickNormal clickNormal;

        public ZJcNormalAdapter(ClickNormal clickNormal) {
            this.clickNormal = clickNormal;
        }

        @Override
        public ZJCHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(parent.getContext(), R.layout.list_item_mnemoic, null);
            return new ZJCHolder(view);
        }

        @Override
        public void onBindViewHolder(ZJCHolder holder, final int position) {
            holder.txt.setText(normalDatas.get(position));
            holder.txt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickNormal.normalItemClick(position, view);
                }
            });
        }

        @Override
        public int getItemCount() {
            return normalDatas == null ? 0 : normalDatas.size();
        }

        class ZJCHolder extends RecyclerView.ViewHolder {
            private TextView txt;

            public ZJCHolder(View itemView) {
                super(itemView);
                txt = (TextView) itemView.findViewById(R.id.tv_mnemonic_word);
//                txt = (TextView) itemView.findViewById(normalTxt);
            }
        }
    }

    class ZJcSelectAdapter extends RecyclerView.Adapter<ZJcSelectAdapter.ZJCHolder> {
        private ClickSelect clickSelect;

        public ZJcSelectAdapter(ClickSelect clickSelect) {
            this.clickSelect = clickSelect;
        }

        @Override
        public ZJCHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(parent.getContext(), R.layout.list_item_mnemoic_selected, null);
            return new ZJCHolder(view);
        }

        @Override
        public void onBindViewHolder(ZJCHolder holder, final int position) {
            WoldEntity woldEntity = woldEntities.get(position);
            Log.i(TAG, "onBindViewHolder: " + woldEntity.toString());
            if (woldEntity.isShow())
                holder.txt.setBackgroundColor(getColor(R.color.blue));
            else
                holder.txt.setBackgroundColor(getColor(R.color.azure));
            holder.txt.setText(woldEntity.getValue());
            holder.txt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickSelect.selectItemClick(position, view);
                }
            });
        }

        @Override
        public int getItemCount() {
            return woldEntities == null ? 0 : woldEntities.size();
        }

        class ZJCHolder extends RecyclerView.ViewHolder {
            private TextView txt;

            public ZJCHolder(View itemView) {
                super(itemView);
                txt = (TextView) itemView.findViewById(R.id.tv_mnemonic_selected_word);
            }
        }

        public boolean unSetSelect(int i) {
            WoldEntity woldEntity = woldEntities.get(i);
            if (!woldEntity.isShow())
                return false;
            woldEntity.setShow(false);
            Collections.shuffle(woldEntities);
            notifyDataSetChanged();
            return true;
        }

        public boolean setSelect(int i) {

            WoldEntity woldEntity = woldEntities.get(i);
            if (woldEntity.isShow())
                return false;
            woldEntity.setShow(true);
            Collections.shuffle(woldEntities);
            notifyDataSetChanged();
            return true;
        }
    }
}

interface ClickSelect {
    void selectItemClick(int i, View view);
}

interface ClickNormal {
    void normalItemClick(int i, View view);
}

class WoldEntity {

    private String value;
    private boolean isShow;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    @Override
    public String toString() {
        return "WoldEntity{" +
                "value='" + value + '\'' +
                ", isShow=" + isShow +
                '}';
    }
}
